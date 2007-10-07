/*
 * Display.java
 *
 * Created on 17 June 2006, 09:25
 */

package house;

import talideon.ui.Utils;
import talideon.ui.charting.Chart;
import talideon.ui.charting.ComponentChart;
import talideon.ui.charting.DifferenceChart;
import talideon.ui.charting.SimpleValue;
import house.model.Assembly;
import house.model.Group;
import house.model.GovernmentComparator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 *
 * @author  kgaughan
 */
public class Display extends javax.swing.JPanel {

    private Assembly   _current;
    private Assembly   _previous;
    private final Font _fntNormal    = new Font("SansSerif", Font.PLAIN, 12);
    private final int  _heightNormal = getLineHeight(_fntNormal);
    private final int  _rightWidth   = _heightNormal * 15;

    /**
     *
     */
    public void paint(Graphics g) {
        super.paint(g);
        final BufferedImage buffer = (BufferedImage) createImage(getWidth(), getHeight());
        final Graphics2D g2 = buffer.createGraphics();

        g2.setBackground(Color.white);
        g2.clearRect(0, 0, getWidth(), getHeight());

        drawLegend(g2);

        ComponentChart seats = new ComponentChart();
        populate(seats, _current.iterator());
        g2.drawImage(plot(seats, 30), _rightWidth, 0, null);

        DifferenceChart change = new DifferenceChart();
        Iterator iter = _current.iterator();
        while (iter.hasNext()) {
            Group current = (Group) iter.next();
            int diff = current.getSeats() - _previous.getGroupSeats(current);
            Color color = Utils.getAWTColor(current.getColor());
            change.add(new SimpleValue(current.getName(), diff, color));
        }
        g2.drawImage(plot(change, (int) 100), _rightWidth, 50, null);

        g2.dispose();
        g.drawImage(buffer, 0, 0, null);
    }

    private Image plot(final Chart c, int height) {
        BufferedImage buffer = (BufferedImage) createImage(getWidth() - _rightWidth, height);
        c.plot(buffer);
        return buffer;
    }

    private void populate(final ComponentChart c, final Iterator iter) {
        while (iter.hasNext()) {
            Group group = (Group) iter.next();
            Color color = Utils.getAWTColor(group.getColor());
            c.add(new SimpleValue(group.getName(), group.getSeats(), color));
        }
    }

    private void drawLegend(final Graphics2D g) {
        boolean governmentParties = true;
        int base = _heightNormal / 2;
        Iterator iter = _current.iterator(new GovernmentComparator());
        while (iter.hasNext()) {
            Group group = (Group) iter.next();
            if (governmentParties && !group.isInGoverment()) {
                g.setColor(Color.gray);
                g.drawLine(0, base, _rightWidth - _heightNormal, base);
                base += 4;
                governmentParties = false;
            }
            Color color = Utils.getAWTColor(group.getColor());
            g.setColor(color);
            g.fillRect(4, base, _heightNormal - 5, _heightNormal - 5);
            g.setColor(color.darker());
            g.drawRect(4, base, _heightNormal - 5, _heightNormal - 5);
            g.setColor(Color.black);
            base += _heightNormal / 2;
            g.drawString(group.toString(), _heightNormal + 5, base + 3);
            base += _heightNormal / 2;
        }
    }

    /**
     * 
     */
    public void set(final Assembly current, final Assembly previous) {
        _current = current;
        _previous = previous;
        repaint();
    }

    /**
     *
     */
    private int getLineHeight(Font font) {
        return getFontMetrics(font).getHeight();
    }
}
