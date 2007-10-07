/*
 * ComponentChart.java
 *
 * Created on 23 June 2006, 22:43
 */

package talideon.ui.charting;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author kgaughan
 */
public class ComponentChart implements Chart {

    private Collection _values = new java.util.LinkedList();
    private double     _total  = 0;

    public void clear() {
        _values.clear();
        _total = 0;
    }

    public void add(final Value value) {
        _values.add(value);
        _total += value.getValue();
    }

    public void plot(final java.awt.image.BufferedImage img) {
        final Graphics2D g = img.createGraphics();

        final int area  = img.getWidth() - _values.size() + 1;
        Iterator  iter  = _values.iterator();
        double    error = 0;
        int       left  = 0;
        while (iter.hasNext()) {
            Value value = (Value) iter.next();

            double trueSize  = value.getValue() * area / _total;
            int    fixedSize = (int) Math.floor(trueSize);
            // Distribute the scaling error.
            error += trueSize - fixedSize;
            if (error >= 0.5) {
                fixedSize++;
                error--;
            }

            g.setColor(value.getColor());
            drawBar(g, left, fixedSize, img.getHeight());

            left += fixedSize + 1;
        }

        g.dispose();
    }

    private void drawBar(final Graphics2D g, final int x, final int w, final int h) {
        g.fillRect(x, 0, w, h);
        g.setColor(g.getColor().darker());
        g.drawRect(x, 0, w - 1 , h - 1);
    }
}
