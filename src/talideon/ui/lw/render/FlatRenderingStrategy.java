/*
 * FlatRenderingStrategy.java
 *
 * Created on 17 June 2006, 22:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package talideon.ui.lw.render;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;

/**
 *
 * @author kgaughan
 */
public class FlatRenderingStrategy implements RenderingStrategy {
    private static final int SIZE = 10;

    /** Creates a new instance of FlatRenderingStrategy */
    public FlatRenderingStrategy() {
    }

    public void border(Component c, Graphics g) {
        Dimension d = c.getSize();
        g.setColor(c.getBackground().darker());
        g.drawRoundRect(0, 0, d.width - 1, d.height - 1, SIZE, SIZE);
    }

    public void button(Component c, Graphics g, String text, boolean isActive) {
    }

    public void horizontalRule(Component c, Graphics g) {
        Dimension d = c.getSize();
        g.setColor(c.getBackground().darker());
        g.drawLine(SIZE, d.height / 2, d.width - SIZE - 1, d.height / 2);
    }

    public void verticalRule(Component c, Graphics g) {
        Dimension d = c.getSize();
        g.setColor(c.getBackground().darker());
        g.drawLine(d.width / 2, SIZE, d.width / 2, d.height - SIZE - 1);
    }

    public void horizontalSlider(Component c, Graphics g, float ratio) {
        Dimension d = c.getSize();
        int x = ratioToOrdinate(d.width, ratio);
        g.setColor(c.getBackground().darker());
        g.fillRoundRect(
                x - SIZE / 2 + 1, SIZE / 2 + 1,
                SIZE, d.height - SIZE - 1,
                SIZE, SIZE);
    }

    public Dimension horizontalSliderMinimumSize() {
        return new Dimension(SIZE * 3, SIZE * 4);
    }

    public float ordinateToRatio(int size, int ord) {
        if (size <= SIZE * 2 + 1) {
            return 0;
        }
        float ratio = (ord - SIZE) / (float) (size - SIZE * 2 - 1);
        if (ratio > 1) {
            ratio = 1;
        }
        return ratio;
    }

    private int ratioToOrdinate(int size, float ratio) {
        return Math.round((size - SIZE * 2 - 1) * ratio) + SIZE;
    }
}
