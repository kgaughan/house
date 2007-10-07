/*
 * Slider.java
 *
 * Created on 17 June 2006, 09:49
 */

package talideon.ui.lw;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import talideon.ui.lw.models.SliderModel;
import talideon.ui.lw.render.FlatRenderingStrategy;
import talideon.ui.lw.render.RenderingStrategy;

/**
 *
 * @author kgaughan
 */
public class Slider extends Canvas {
    private static final RenderingStrategy _rs = new FlatRenderingStrategy();

    private final SliderModel _model;

    /** Creates a new instance of Slider */
    public Slider(final SliderModel model) {
        super();
        _model = model;
        /*
        setMinimumSize(_rs.horizontalSliderMinimumSize());
        setPreferredSize(_rs.horizontalSliderMinimumSize());
        */
    }

    public void paint(Graphics g) {
        super.paint(g);
        _rs.border(this, g);
        _rs.horizontalRule(this, g);
        _rs.horizontalSlider(this, g, _model.getValueRatio());
    }

    public boolean mouseDown(Event e, int x, int y) {
        return mouseDrag(e, x, y);    
    }

    public boolean mouseDrag(Event e, int x, int y) {
        if (_model.onChange(this, _rs.ordinateToRatio(getSize().width, x))) {
            repaint();
        }
        return true;
    }
}
