/*
 * Chart.java
 *
 * Created on 23 June 2006, 22:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package talideon.ui.charting;

/**
 *
 * @author kgaughan
 */
public interface Chart {

    /**
     * 
     */
    void clear();

    /**
     *
     */
    void add(Value value);

    /**
     *
     */
    void plot(java.awt.image.BufferedImage img);
}
