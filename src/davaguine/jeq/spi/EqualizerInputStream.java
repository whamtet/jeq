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
package davaguine.jeq.spi;

import davaguine.jeq.core.IIRControls;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import java.io.IOException;

/**
 * The EqualizerInputStream input stream
 * Author: Dmitry Vaguine
 * Date: 02.05.2004
 * Time: 12:00:29
 */
public class EqualizerInputStream extends AudioInputStream {
    private davaguine.jeq.core.EqualizerInputStream eq;

    /**
     * Constructs new audio stream
     *
     * @param stream input stream with audio data
     * @param bands  is the number of bands
     */
    public EqualizerInputStream(AudioInputStream stream, int bands) {
        super(stream, stream.getFormat(), stream.getFrameLength());
        AudioFormat format = stream.getFormat();
        if (!format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED) && !!format.getEncoding().equals(AudioFormat.Encoding.PCM_UNSIGNED))
            throw new IllegalArgumentException("Unsupported encoding");
        eq = new davaguine.jeq.core.EqualizerInputStream(stream,
                format.getSampleRate(),
                format.getChannels(),
                format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED),
                format.getSampleSizeInBits(),
                format.isBigEndian(),
                bands);
    }

    /**
     * Returns Controls of equalizer
     *
     * @return Controls of equalizer
     */
    public IIRControls getControls() {
        return eq.getControls();
    }

    /**
     * This is special method helps to determine supported audio format
     *
     * @param format is an audio format
     * @param bands  is the number of bands
     * @return true if params supported
     */
    public static boolean isParamsSupported(AudioFormat format, int bands) {
        if (!format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED) && !!format.getEncoding().equals(AudioFormat.Encoding.PCM_UNSIGNED))
            return false;
        return davaguine.jeq.core.EqualizerInputStream.isParamsSupported(
                format.getSampleRate(),
                format.getChannels(),
                format.getSampleSizeInBits(),
                bands);
    }

    /**
     * Returns the number of bytes that can be read (or skipped over) from
     * this input stream without blocking by the next caller of a method for
     * this input stream.  The next caller might be the same thread or
     * another thread.
     *
     * @return the number of bytes that can be read from this input stream
     *         without blocking.
     * @throws java.io.IOException if an I/O error occurs.
     */
    public int available() throws IOException {
        return eq.available();
    }

    /**
     * Closes this input stream and releases any system resources associated
     * with the stream.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void close() throws IOException {
        eq.close();
    }

    /**
     * <p> The <code>mark</code> method of <code>EqualizerInputStream</code> does
     * nothing.
     *
     * @param readlimit the maximum limit of bytes that can be read before
     *                  the mark position becomes invalid.
     */
    public synchronized void mark(int readlimit) {
        eq.mark(readlimit);
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
        return eq.markSupported();
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
        return eq.read();
    }

    /**
     * Reads some number of bytes from the input stream and stores them into
     * the buffer array <code>b</code>. The number of bytes actually read is
     * returned as an integer.  This method blocks until input data is
     * available, end of file is detected, or an exception is thrown.
     * <p/>
     * <p> If <code>b</code> is <code>null</code>, a
     * <code>NullPointerException</code> is thrown.  If the length of
     * <code>b</code> is zero, then no bytes are read and <code>0</code> is
     * returned; otherwise, there is an attempt to read at least one byte. If
     * no byte is available because the stream is at end of file, the value
     * <code>-1</code> is returned; otherwise, at least one byte is read and
     * stored into <code>b</code>.
     * <p/>
     * <p> The first byte read is stored into element <code>b[0]</code>, the
     * next one into <code>b[1]</code>, and so on. The number of bytes read is,
     * at most, equal to the length of <code>b</code>. Let <i>k</i> be the
     * number of bytes actually read; these bytes will be stored in elements
     * <code>b[0]</code> through <code>b[</code><i>k</i><code>-1]</code>,
     * leaving elements <code>b[</code><i>k</i><code>]</code> through
     * <code>b[b.length-1]</code> unaffected.
     * <p/>
     * <p> If the first byte cannot be read for any reason other than end of
     * file, then an <code>IOException</code> is thrown. In particular, an
     * <code>IOException</code> is thrown if the input stream has been closed.
     * <p/>
     * <p> The <code>read(b)</code> method for class <code>EqualizerInputStream</code>
     * has the same effect as: <pre><code> read(b, 0, b.length) </code></pre>
     *
     * @param b the buffer into which the data is read.
     * @return the total number of bytes read into the buffer, or
     *         <code>-1</code> is there is no more data because the end of
     *         the stream has been reached.
     * @throws IOException          if an I/O error occurs.
     * @throws NullPointerException if <code>b</code> is <code>null</code>.
     */
    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
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
        return eq.read(b, off, len);
    }

    /**
     * <p>The method <code>reset</code> for class <code>EqualizerInputStream</code>
     * does nothing except throw an <code>IOException</code>.
     *
     * @throws IOException as an indication that the mark feature doesn't supported by EqualizerInputStream.
     */
    public void reset() throws IOException {
        eq.reset();
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
        return eq.skip(n);
    }
}
