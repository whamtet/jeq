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
 * Constols of equalizer
 * Author: Dmitry Vaguine
 * Date: 02.05.2004
 * Time: 12:00:29
 */
public class IIRControls {
    /**
     * Volume gain
     * values should be between 0.0 and 1.0
     */
    private float preamp[];
    /**
     * Gain for each band
     * values should be between -0.2 and 1.0
     */
    private float bands[][];

    /**
     * Creates new IIRControls object for given number of bands
     *
     * @param bandsnum is the number of bands
     * @param channels is the number of channels
     */
    public IIRControls(int bandsnum, int channels) {
        preamp = new float[channels];
        bands = new float[bandsnum][channels];
        for (int j = 0; j < channels; j++) {
            preamp[j] = 1.0f;
            for (int i = 0; i < bandsnum; i++)
                bands[i][j] = 0f;
        }
    }

    /**
     * Returns the maximum value for band control
     *
     * @return the maximum value for band control
     */
    public float getMaximumBandValue() {
        return 1.0f;
    }

    /**
     * Returns the minimum value for band control
     *
     * @return the minimum value for band control
     */
    public float getMinimumBandValue() {
        return -0.2f;
    }

    /**
     * Returns the maximum value for band control (in Db)
     *
     * @return the maximum value for band control
     */
    public float getMaximumBandDbValue() {
        return 12;
    }

    /**
     * Returns the minimum value for band control (in Db)
     *
     * @return the minimum value for band control
     */
    public float getMinimumBandDbValue() {
        return -12f;
    }

    /**
     * Returns the maximum value for preamp control
     *
     * @return the maximum value for preamp control
     */
    public float getMaximumPreampValue() {
        return 1.0f;
    }

    /**
     * Returns the minimum value for preamp control
     *
     * @return the minimum value for preamp control
     */
    public float getMinimumPreampValue() {
        return 0f;
    }

    /**
     * Returns the maximum value for preamp control (in Db)
     *
     * @return the maximum value for preamp control
     */
    public float getMaximumPreampDbValue() {
        return 12f;
    }

    /**
     * Returns the minimum value for preamp control (in Db)
     *
     * @return the minimum value for preamp control
     */
    public float getMinimumPreampDbValue() {
        return -12f;
    }

    /**
     * Returns bands array
     *
     * @return bands array
     */
    float[][] getBands() {
        return bands;
    }

    /**
     * Returns preamp array
     *
     * @return preamp array
     */
    float[] getPreamp() {
        return preamp;
    }

    /**
     * Returns value of control for given band and channel
     *
     * @param band    is the index of band
     * @param channel is the index of channel
     * @return the value
     */
    public float getBandValue(int band, int channel) {
        return bands[band][channel];
    }

    /**
     * Setter for value of control for given band and channel
     *
     * @param band    is the index of band
     * @param channel is the index of channel
     * @param value   is the new value
     */
    public void setBandValue(int band, int channel, float value) {
        bands[band][channel] = value;
    }

    /**
     * Setter for value of control for given band and channel (in Db)
     *
     * @param band    is the index of band
     * @param channel is the index of channel
     * @param value   is the new value
     */
    void setBandDbValue(int band, int channel, float value) {
        /* Map the gain and preamp values */
        /* -12dB .. 12dB mapping */
        bands[band][channel] = (float) (2.5220207857061455181125E-01 *
                Math.exp(8.0178361802353992349168E-02 * value)
                - 2.5220207852836562523180E-01);
    }

    /**
     * Returns value of preamp control for given channel
     *
     * @param channel is the index of channel
     * @return the value
     */
    public float getPreampValue(int channel) {
        return preamp[channel];
    }

    /**
     * Setter for value of preamp control for given channel
     *
     * @param channel is the index of channel
     * @param value   is the new value
     */
    public void setPreampValue(int channel, float value) {
        preamp[channel] = value;
    }

    /**
     * Setter for value of preamp control for given channel (in Db)
     *
     * @param channel is the index of channel
     * @param value   is the new value
     */
    public void setPreampDbValue(int channel, float value) {
        /* -12dB .. 12dB mapping */
        preamp[channel] = (float) (9.9999946497217584440165E-01 *
                Math.exp(6.9314738656671842642609E-02 * value)
                + 3.7119444716771825623636E-07);
    }
}
