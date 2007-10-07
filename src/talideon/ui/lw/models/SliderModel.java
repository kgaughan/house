/*
 * ChangeableModel.java
 *
 * Created on 17 June 2006, 23:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package talideon.ui.lw.models;

import talideon.ui.lw.Slider;

/**
 *
 * @author kgaughan
 */
public class SliderModel {
    private int _value;
    private int _minimum;
    private int _maximum;

    public SliderModel(int minimum, int maximum, int initialValue) {
        _minimum = minimum;
        _maximum = maximum;
        _value   = initialValue;
        onChange();
    }

    public final int getValue() {
        return _value;
    }

    public final float getValueRatio() {
        return (_value - _minimum) / (float) (_maximum - _minimum);
    }

    private int ratioToValue(float ratio) {
        int value = Math.round(ratio * (_maximum - _minimum)) + _minimum;
        if (value < _minimum) {
            return _minimum;
        }
        if (value > _maximum) {
            return _maximum;
        }
        return value;
    }

    public final boolean onChange(Slider slider, float ratio) {
        int oldValue = _value;
        _value = ratioToValue(ratio);
        if (_value != oldValue) {
            onChange();
            return true;
        }
        return false;
    }

    protected void onChange() {
    }
}
