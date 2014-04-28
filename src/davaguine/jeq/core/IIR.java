/*
 *  21.04.2004 Original verion. davagin@udm.ru.
 *-----------------------------------------------------------------------
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *----------------------------------------------------------------------
 */
package davaguine.jeq.core;

/**
 * Generic wrapper around IIR algorithm.
 * Author: Dmitry Vaguine
 * Date: 02.05.2004
 * Time: 12:00:29
 */
public class IIR extends IIRBase {
    /**
     * Max number of channels supported
     */
    public final static int EQ_MAX_CHANNELS = 2;
    /**
     * Max bands supported by the code
     */
    public final static int EQ_MAX_BANDS = 31;
    /**
     * Supported sample rates
     */
    public final static float EQ_11025_RATE = 11025;
    public final static float EQ_22050_RATE = 22050;
    public final static float EQ_44100_RATE = 44100;
    public final static float EQ_48000_RATE = 48000;
    /**
     * Supported number of bands
     */
    public final static int EQ_10_BANDS = 10;
    public final static int EQ_15_BANDS = 15;
    public final static int EQ_25_BANDS = 25;
    public final static int EQ_31_BANDS = 31;

    /* Indexes for the history arrays
     * These have to be kept between calls to this function
     * hence they are static */
    private int i;
    private int j;
    private int k;

    /* History for two filters */
    private XYData[][] dataHistory = new XYData[EQ_MAX_BANDS][EQ_MAX_CHANNELS];
    private XYData[][] dataHistory2 = new XYData[EQ_MAX_BANDS][EQ_MAX_CHANNELS];

    /* Coefficients */
    private IIRCoefficients[] iircf;

    /* Equalizer config */
    private IIRControls eqcfg;
    /* rate */
    private float rate;
    /* channels */
    private int channels;
    /* bands */
    private int bands;

    /**
     * Constructs equalizer with given config
     *
     * @param bands    is the number of bands to be used
     * @param rate     is the sample rate of equalizer
     * @param channels is the number of channels
     */
    public IIR(int bands, float rate, int channels) {
        this.rate = rate;
        this.channels = channels;
        this.bands = bands;
        this.eqcfg = new IIRControls(bands, channels);

        if (!isParamsSupported(bands, rate, channels))
            throw new IllegalArgumentException("Unsupported parameters");

        initIIR();
    }

    /**
     * Returns Controls of equalizer
     *
     * @return Controls of equalizer
     */
    public IIRControls getControls() {
        return eqcfg;
    }

    /**
     * This is special method for checking of supported parameters of equalizer
     *
     * @param bands    is the number of bands
     * @param rate     is the sample rate of data
     * @param channels is the number of channels
     * @return true if parameters are supported
     */
    public static boolean isParamsSupported(int bands, float rate, int channels) {
        if (rate != EQ_11025_RATE && rate != EQ_22050_RATE && rate != EQ_44100_RATE && rate != EQ_48000_RATE)
            return false;

        switch (bands) {
            case EQ_10_BANDS:
            case EQ_15_BANDS:
            case EQ_25_BANDS:
            case EQ_31_BANDS:
                break;
            default:
                return false;
        }

        switch (channels) {
            case 1:
            case 2:
                break;
            default:
                return false;
        }

        return (rate != EQ_11025_RATE && rate != EQ_22050_RATE) || bands == EQ_10_BANDS;
    }

    /* Init the filters */
    private void initIIR() {
        setFilters();
        for (int ii = 0; ii < EQ_MAX_BANDS; ii++)
            for (int jj = 0; jj < EQ_MAX_CHANNELS; jj++) {
                dataHistory[ii][jj] = new XYData();
                dataHistory2[ii][jj] = new XYData();
            }
        i = 0;
        j = 2;
        k = 1;
    }

    private void setFilters() {
        if (rate == EQ_11025_RATE)
            iircf = iir_cf10_11k_11025;
        else if (rate == EQ_22050_RATE)
            iircf = iir_cf10_22k_22050;
        else if (rate == EQ_44100_RATE) {
            switch (bands) {
                case 31:
                    iircf = iir_cf31_44100;
                    break;
                case 25:
                    iircf = iir_cf25_44100;
                    break;
                case 15:
                    iircf = iir_cf15_44100;
                    break;
                default:
                    iircf = iir_cf10_44100;
                    break;
            }
        } else if (rate == EQ_48000_RATE) {
            switch (bands) {
                case 31:
                    iircf = iir_cf31_48000;
                    break;
                case 25:
                    iircf = iir_cf25_48000;
                    break;
                case 15:
                    iircf = iir_cf15_48000;
                    break;
                default:
                    iircf = iir_cf10_48000;
                    break;
            }
        }
    }

    /**
     * Clear filter history.
     */
    public void cleanHistory() {
        /* Zero the history arrays */
        for (int ii = 0; ii < EQ_MAX_BANDS; ii++)
            for (int jj = 0; jj < EQ_MAX_CHANNELS; jj++) {
                dataHistory[ii][jj].zero();
                dataHistory2[ii][jj].zero();
            }
        i = 0;
        j = 2;
        k = 1;
    }

    /**
     * Main filtering method.
     *
     * @param data   - data to be filtered
     * @param length - length of data in buffer
     */
    public void iir(int[] data, int length) {
        int index, band, channel;
        float eqpreamp[] = eqcfg.getPreamp();
        float eqbands[][] = eqcfg.getBands();
        double pcm, out;

        /**
         * IIR filter equation is
         * y[n] = 2 * (alpha*(x[n]-x[n-2]) + gamma*y[n-1] - beta*y[n-2])
         *
         * NOTE: The 2 factor was introduced in the coefficients to save
         * 			a multiplication
         *
         * This algorithm cascades two filters to get nice filtering
         * at the expense of extra CPU cycles
         */
        IIRCoefficients tempcf;
        XYData tempd;
        for (index = 0; index < length; index += channels) {
            /* For each channel */
            for (channel = 0; channel < channels; channel++) {
                /* Preamp gain */
                pcm = data[index + channel] * eqpreamp[channel];

                out = 0f;
                /* For each band */
                for (band = 0; band < bands; band++) {
                    /* Store Xi(n) */
                    tempd = dataHistory[band][channel];
                    tempd.x[i] = pcm;
                    /* Calculate and store Yi(n) */
                    tempcf = iircf[band];
                    tempd.y[i] =
                            (
                                    /* 		= alpha * [x(n)-x(n-2)] */
                                    tempcf.alpha * (pcm - tempd.x[k])
                                            /* 		+ gamma * y(n-1) */
                                            + tempcf.gamma * tempd.y[j]
                                            /* 		- beta * y(n-2) */
                                            - tempcf.beta * tempd.y[k]
                            );
                    /*
                     * The multiplication by 2.0 was 'moved' into the coefficients to save
                     * CPU cycles here */
                    /* Apply the gain  */
                    out += (tempd.y[i] * eqbands[band][channel]); // * 2.0;
                } /* For each band */

                /* Volume stuff
                   Scale down original PCM sample and add it to the filters
                   output. This substitutes the multiplication by 0.25
                   Go back to use the floating point multiplication before the
                   conversion to give more dynamic range
                   */
                out += (pcm * 0.25);

                /* Normalize the output */
                out *= 4;

                /* Round and convert to integer */
                data[index + channel] = (int) out;
            } /* For each channel */

            i++;
            j++;
            k++;

            /* Wrap around the indexes */
            if (i == 3)
                i = 0;
            else if (j == 3)
                j = 0;
            else
                k = 0;


        }/* For each pair of samples */
    }
}
