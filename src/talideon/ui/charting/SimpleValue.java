/*
 * SimpleValue.java
 *
 * Created on 23 June 2006, 23:07
 */

package talideon.ui.charting;

import java.awt.Color;

/**
 *
 * @author kgaughan
 */
public class SimpleValue implements Value {

    private final String _name;
    private final double _value;
    private final Color  _color;

    /** Creates a new instance of SimpleValue */
    public SimpleValue(final String name, final double value, final Color color) {
        _name  = name;
        _value = value;
        _color = color;
    }

    public String getName() {
        return _name;
    }

    public double getValue() {
        return _value;
    }

    public Color getColor() {
        return _color;
    }
}
