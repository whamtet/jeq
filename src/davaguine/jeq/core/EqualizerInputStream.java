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

import java.io.IOException;
import java.io.InputStream;

/**
 * The EqualizerInputStream class
 * Author: Dmitry Vaguine
 * Date: 02.05.2004
 * Time: 12:00:29
 */
public class EqualizerInputStream extends InputStream {
    private InputStream stream;
    private IIR iir;

    private final static int BUFFER_SIZE = 65536;
    private byte[] inbuf = new byte[BUFFER_SIZE];
    private int[] workbuf = new int[BUFFER_SIZE];
    private byte[] outbuf = new byte[BUFFER_SIZE];
    private int inpos = 0;
    private int inlen = 0;
    private int outpos = 0;
    private int outlen = 0;

    private boolean signed;
    private int samplesize;
    private boolean bigendian;

    /**
     * Constructs new EqualizerInputStream object
     *
     * @param stream     is an input stream for pcm data
     * @param samplerate is a sample rate of input data
     * @param channels   is the number of channels
     * @param signed     indicates that the data is signed
     * @param samplesize is the sample bit size of data
     * @param bigendian  indicates that the dat is in "big endian" encoding
     * @param bands      is the number of bands
     */
    public EqualizerInputStream(InputStream stream, float samplerate, int channels, boolean signed, int samplesize, boolean bigendian, int bands) {
        this.stream = stream;
        this.iir = new IIR(bands, samplerate, channels);
        this.signed = signed;
        this.samplesize = samplesize;
        this.bigendian = bigendian;

        if (!isParamsSupported(samplerate, channels, samplesize, bands))
            throw new IllegalArgumentException("Unsupported sample bit size");
    }

    /**
     * Returns Controls of equalizer
     *
     * @return Controls of equalizer
     */
    public IIRControls getControls() {
        return iir.getControls();
    }

    /**
     * This is special method for checking of supported parameters of equalizer
     *
     * @param bands      is the number of bands
     * @param samplerate is the sample rate of data
     * @param channels   is the number of channels
     * @param samplesize is the size of sample in bits
     * @return true if parameters are supported
     */
    public static boolean isParamsSupported(float samplerate, int channels, int samplesize, int bands) {
        switch (samplesize) {
            case 8:
            case 16:
            case 24:
                break;
            default:
                return false;
        }

        return IIR.isParamsSupported(bands, samplerate, channels);
    }

    private boolean fillInBuffer() throws IOException {
        if (inpos != 0 && inlen > 0)
            System.arraycopy(inbuf, inpos, inbuf, 0, inlen);
        inpos = 0;
        int num;
        boolean eof = false;
        while (inlen != inbuf.length) {
            num = stream.read(inbuf, inlen, inbuf.length - inlen);
            if (num < 0) {
                eof = true;
                break;
            }
            inlen += num;
        }
        return eof;
    }

    private void fillOutBuffer() {
        if (outpos != 0 && outlen > 0)
            System.arraycopy(outbuf, outpos, outbuf, 0, outlen);
        outpos = 0;
        int len = outbuf.length - outlen;
        len = inlen < len ? inlen : len;
        len = convertToInt(len);
        if (len > 0) {
            iir.iir(workbuf, len);
            len = convertToByte(outbuf, outlen, len);
            outlen += len;
        }
    }

    private int convertToInt(int length) {
        int l = length;
        int temp;
        byte a1[];
        switch (samplesize) {
            case 8: {
                if (length > 0) {
                    System.arraycopy(inbuf, 0, workbuf, 0, length);
                    inpos += length;
                    inlen -= length;
                }
                break;
            }
            case 16: {
                l = length >> 1;
                if (l > 0) {
                    if (bigendian)
                        for (int i = 0; i < l; i++) {
                            temp = (((a1 = inbuf)[inpos++] & 0xff) << 8) | (a1[inpos++] & 0xff);
                            workbuf[i] = signed && temp > 32767 ? temp - 65536 : temp;
                        }
                    else
                        for (int i = 0; i < l; i++) {
                            temp = ((a1 = inbuf)[inpos++] & 0xff) | ((a1[inpos++] & 0xff) << 8);
                            workbuf[i] = signed && temp > 32767 ? temp - 65536 : temp;
                        }
                    inlen -= inpos;
                }
                break;
            }
            case 24: {
                l = length / 3;
                if (l > 0) {
                    if (bigendian)
                        for (int i = 0; i < l; i++) {
                            temp = ((a1 = inbuf)[inpos++] & 0xff) | ((a1[inpos++] & 0xff) << 8) | ((a1[inpos++] & 0xff) << 16);
                            workbuf[i] = signed && temp > 8388607 ? temp - 16777216 : temp;
                        }
                    else
                        for (int i = 0; i < l; i++) {
                            temp = (((a1 = inbuf)[inpos++] & 0xff) << 16) | ((a1[inpos++] & 0xff) << 8) | (a1[inpos++] & 0xff);
                            workbuf[i] = signed && temp > 8388607 ? temp - 16777216 : temp;
                        }
                    inlen -= inpos;
                }
                break;
            }
        }
        return l;
    }

    private int wrap8Bit(int data) {
        if (data > 127)
            data = 127;
        else if (data < -128)
            data = -128;
        if (data < 0)
            data += 256;
        return data;
    }

    private int wrap16Bit(int data) {
        if (data > 32767)
            data = 32767;
        else if (data < -32768)
            data = -32768;
        if (data < 0)
            data += 65536;
        return data;
    }

    private int wrap24Bit(int data) {
        if (data > 8388607)
            data = 8388607;
        else if (data < -8388608)
            data = -8388608;
        if (data < 0)
            data += 16777216;
        return data;
    }

    private int convertToByte(byte[] b, int off, int length) {
        int p = off;
        int d;
        switch (samplesize) {
            case 8: {
                for (int i = 0; i < length; i++)
                    b[p++] = (byte) (wrap8Bit(workbuf[i]) & 0xff);
                break;
            }
            case 16: {
                if (bigendian) {
                    for (int i = 0; i < length; i++) {
                        d = wrap16Bit(workbuf[i]);
                        b[p++] = (byte) ((d & 0xff00) >> 8);
                        b[p++] = (byte) (d & 0xff);
                    }
                } else {
                    for (int i = 0; i < length; i++) {
                        d = wrap16Bit(workbuf[i]);
                        b[p++] = (byte) (d & 0xff);
                        b[p++] = (byte) ((d & 0xff00) >> 8);
                    }
                }
                break;
            }
            case 24: {
                if (bigendian) {
                    for (int i = 0; i < length; i++) {
                        d = wrap24Bit(workbuf[i]);
                        b[p++] = (byte) (d & 0xff);
                        b[p++] = (byte) ((d & 0xff00) >> 8);
                        b[p++] = (byte) ((d & 0xff0000) >> 16);
                    }
                } else {
                    for (int i = 0; i < length; i++) {
                        d = wrap24Bit(workbuf[i]);
                        b[p++] = (byte) ((d & 0xff0000) >> 16);
                        b[p++] = (byte) ((d & 0xff00) >> 8);
                        b[p++] = (byte) (d & 0xff);
                    }
                }
                break;
            }
        }
        return p - off;
    }

    /**
     * Returns the number of bytes that can be read (or skipped over) from
     * this input stream without blocking by the next caller of a method for
     * this input stream.  The next caller might be the same thread or
     * another thread.
     *
     * @return the number of bytes that can be read from this input stream
     *         without blocking.
     * @throws IOException if an I/O error occurs.
     */
    public int available() throws IOException {
        return outlen;
    }

    /**
     * Closes this input stream and releases any system resources associated
     * with the stream.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void close() throws IOException {
        stream.close();
    }

    /**
     * <p> The <code>mark</code> method of <code>EqualizerInputStream</code> does
     * nothing.
     *
     * @param readlimit the maximum limit of bytes that can be read before
     *                  the mark position becomes invalid.
     */
    public synchronized void mark(int readlimit) {
    }

    /**
     * Tests if this input stream supports the <code>mark</code> and
     * <code>reset</code> methods. Whether or not <code>mark</code> and
     * <code>reset</code> are supported is an invariant property of a
     * particular input stream instance. The <code>markSupported</code> method
     * of <code>EqualizerInputStream</code> returns <code>false</code>.
     *
     * @return false
     */
    public boolean markSupported() {
        return false;
    }

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an <code>int</code> in the range <code>0</code> to
     * <code>255</code>. If no byte is available because the end of the stream
     * has been reached, the value <code>-1</code> is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * @return the next byte of data, or <code>-1</code> if the end of the
     *         stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    public int read() throws IOException {
        if (outlen == 0) {
            boolean eof = fillInBuffer();
            fillOutBuffer();
            if (outlen == 0 && eof)
                return -1;
            if (outlen == 0 && !eof)
                throw new IOException("Impossible state");
        }
        int b = outbuf[outpos++];
        outlen--;
        return b;
    }

    /**
     * Reads up to <code>len</code> bytes of data from the input stream into
     * an array of bytes.  An attempt is made to read as many as
     * <code>len</code> bytes, but a smaller number may be read.
     * The number of bytes actually read is returned as an integer.
     * <p/>
     * <p> This method blocks until input data is available, end of file is
     * detected, or an exception is thrown.
     * <p/>
     * <p> If <code>b</code> is <code>null</code>, a
     * <code>NullPointerException</code> is thrown.
     * <p/>
     * <p> If <code>off</code> is negative, or <code>len</code> is negative, or
     * <code>off+len</code> is greater than the length of the array
     * <code>b</code>, then an <code>IndexOutOfBoundsException</code> is
     * thrown.
     * <p/>
     * <p> If <code>len</code> is zero, then no bytes are read and
     * <code>0</code> is returned; otherwise, there is an attempt to read at
     * least one byte. If no byte is available because the stream is at end of
     * file, the value <code>-1</code> is returned; otherwise, at least one
     * byte is read and stored into <code>b</code>.
     * <p/>
     * <p> The first byte read is stored into element <code>b[off]</code>, the
     * next one into <code>b[off+1]</code>, and so on. The number of bytes read
     * is, at most, equal to <code>len</code>. Let <i>k</i> be the number of
     * bytes actually read; these bytes will be stored in elements
     * <code>b[off]</code> through <code>b[off+</code><i>k</i><code>-1]</code>,
     * leaving elements <code>b[off+</code><i>k</i><code>]</code> through
     * <code>b[off+len-1]</code> unaffected.
     * <p/>
     * <p> In every case, elements <code>b[0]</code> through
     * <code>b[off]</code> and elements <code>b[off+len]</code> through
     * <code>b[b.length-1]</code> are unaffected.
     * <p/>
     * <p> If the first byte cannot be read for any reason other than end of
     * file, then an <code>IOException</code> is thrown. In particular, an
     * <code>IOException</code> is thrown if the input stream has been closed.
     *
     * @param b   the buffer into which the data is read.
     * @param off the start offset in array <code>b</code>
     *            at which the data is written.
     * @param len the maximum number of bytes to read.
     * @return the total number of bytes read into the buffer, or
     *         <code>-1</code> if there is no more data because the end of
     *         the stream has been reached.
     * @throws IOException          if an I/O error occurs.
     * @throws NullPointerException if <code>b</code> is <code>null</code>.
     */
    public int read(byte[] b, int off, int len) throws IOException {
        if (outlen < len) {
            boolean eof = fillInBuffer();
            fillOutBuffer();
            if (outlen == 0 && eof)
                return -1;
            if (outlen == 0 && !eof)
                throw new IOException("Impossible state");
        }
        len = outlen < len ? outlen : len;
        if (len > 0) {
            System.arraycopy(outbuf, outpos, b, off, len);
            outpos += len;
            outlen -= len;
        }
        return len;
    }

    /**
     * <p>The method <code>reset</code> for class <code>EqualizerInputStream</code>
     * does nothing except throw an <code>IOException</code>.
     *
     * @throws IOException as an indication that the mark feature doesn't supported by EqualizerInputStream.
     */
    public void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    /**
     * Skips over and discards <code>n</code> bytes of data from this input
     * stream. The <code>skip</code> method may, for a variety of reasons, end
     * up skipping over some smaller number of bytes, possibly <code>0</code>.
     * This may result from any of a number of conditions; reaching end of file
     * before <code>n</code> bytes have been skipped is only one possibility.
     * The actual number of bytes skipped is returned.  If <code>n</code> is
     * negative, no bytes are skipped.
     *
     * @param n the number of bytes to be skipped.
     * @return the actual number of bytes skipped.
     * @throws IOException if an I/O error occurs.
     */
    public long skip(long n) throws IOException {
        int l;
        if (n <= outlen) {
            outpos += n;
            outlen -= n;
            return n;
        }
        n -= outlen;
        l = outlen;
        outlen = 0;
        outpos = 0;
        if (n <= inlen) {
            inpos += n;
            inlen -= n;
            return l + n;
        }
        n -= inlen;
        l += inlen;
        inlen = 0;
        inpos = 0;
        return stream.skip(n) + l;
    }

}
