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
 * Structure for storing XYData of equalizer.
 * Author: Dmitry Vaguine
 * Date: 02.05.2004
 * Time: 12:00:29
 */
public class XYData {
    /**
     * X data
     */
    public double x[] = new double[3]; /* x[n], x[n-1], x[n-2] */
    /**
     * Y data
     */
    public double y[] = new double[3]; /* y[n], y[n-1], y[n-2] */

    /**
     * Constructs new XYData object
     */
    public XYData() {
        zero();
    }

    /**
     * Fills all content with zero
     */
    public void zero() {
        for (int i = 0; i < 3; i++) {
            x[i] = 0;
            y[i] = 0;
        }
    }
}
