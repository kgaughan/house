/*
 * DifferenceChart.java
 *
 * Created on 23 June 2006, 23:21
 */

package talideon.ui.charting;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author kgaughan
 */
public class DifferenceChart implements Chart {

    private Collection _values = new java.util.LinkedList();
    private double     _absMax = 0;

    public void clear() {
        _values.clear();
        _absMax = 0;
    }

    public void add(Value value) {
        _values.add(value);
        if (Math.abs(value.getValue()) > _absMax) {
            _absMax = Math.abs(value.getValue());
        }
    }

    /**
     *
     */
    public double getMax() {
        return _absMax;
    }

    public void plot(java.awt.image.BufferedImage img) {
        final java.awt.Graphics2D g = img.createGraphics();
        final Font fnt = new Font("SansSerif", Font.PLAIN, 10);
        g.setFont(fnt);

        AffineTransform at = g.getTransform();
        g.translate(0, img.getHeight() / 2);
        int left = 4;
        Iterator iter = _values.iterator();
        while (iter.hasNext()) {
            Value value = (Value) iter.next();
            double height = value.getValue() * (img.getHeight() - 1) / (_absMax * 2);
            if (height < 0) {
                g.setColor(value.getColor());
                g.fillRect(left, 0, 32, (int) -height);
                g.setColor(g.getColor().darker());
                g.drawRect(left, 0, 32, (int) -height);
                Rectangle2D bounds =
                    fnt.getStringBounds("" + (int) value.getValue(), g.getFontRenderContext());
                LineMetrics lm =
                    fnt.getLineMetrics("" + (int) value.getValue(), g.getFontRenderContext());
                g.setColor(Color.black);
                g.drawString("" + (int) value.getValue(), left + (int) (32 - bounds.getWidth()) / 2, (int) -lm.getDescent());
            } else if (height > 0) {
                g.setColor(value.getColor());
                g.fillRect(left, (int) -height, 32, (int) height);
                g.setColor(g.getColor().darker());
                g.drawRect(left, (int) -height, 32, (int) height);
                Rectangle2D bounds =
                    fnt.getStringBounds("+" + (int) value.getValue(), g.getFontRenderContext());
                LineMetrics lm =
                    fnt.getLineMetrics("+" + (int) value.getValue(), g.getFontRenderContext());
                g.setColor(Color.black);
                g.drawString("+" + (int) value.getValue(), left + (int) (32 - bounds.getWidth()) / 2, (int) lm.getAscent());
            } else {
                continue;
            }
            left += 36;
        }
        g.setTransform(at);

        g.setColor(Color.black);
        g.drawLine(0, img.getHeight() / 2, img.getWidth(), img.getHeight() / 2);
        g.drawRect(0, 0, 0, img.getHeight());
    }
}
