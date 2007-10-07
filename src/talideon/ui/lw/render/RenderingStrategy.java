/*
 * RenderingStrategy.java
 *
 * Created on 17 June 2006, 22:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package talideon.ui.lw.render;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author kgaughan
 */
public interface RenderingStrategy {
    void border(Component c, Graphics g);
    void button(Component c, Graphics g, String text, boolean isActive);
    void horizontalRule(Component c, Graphics g);
    void verticalRule(Component c, Graphics g);
    void horizontalSlider(Component c, Graphics g, float ratio);
    Dimension horizontalSliderMinimumSize();
    float ordinateToRatio(int size, int ord);
}