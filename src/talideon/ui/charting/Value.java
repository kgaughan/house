/*
 * Value.java
 *
 * Created on 23 June 2006, 22:34
 */

package talideon.ui.charting;

import java.awt.Color;

/**
 *
 * @author kgaughan
 */
public interface Value {

    /**
     *
     */
    String getName();

    /**
     *
     */
    double getValue();

    /**
     *
     */
    Color getColor();
}
