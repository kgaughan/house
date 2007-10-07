/*
 * House.java
 *
 * Created on 17 June 2006, 09:12
 */

package house;

import house.model.Assemblies;
import house.model.Assembly;
import talideon.i18n.I18N;
import talideon.utils.PropertyList;
import house.Utils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author  kgaughan
 */
public class House extends JApplet {

    /**
     *
     */
    private static final int DELAY   = 2000;

    /**
     *
     */
    private static final int PADDING = 4;

    /**
     *
     */
    private Assemblies _assemblies;

    /**
     *
     */
    private final Display _pnlDisplay = new Display();

    /**
     *
     */
    private final JSlider _sldTime = new JSlider();

    /**
     *
     */
    private final JLabel _lblTitle = new JLabel();

    /**
     *
     */
    private final JLabel _lblSubtitle = new JLabel();

    /**
     *
     */
    private final JButton _btnAutomate = new JButton(new AbstractAction(I18N.get("SlideshowStart")) {
        public void actionPerformed(ActionEvent e) {
            if (_slideshow.isRunning()) {
                stopSlideshow();
            } else {
                _btnAutomate.setText(I18N.get("SlideshowStop"));
                startSlideshow();
            }
        }
    });

    /**
     *
     */
    private final Timer _slideshow = new Timer(DELAY, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int current = _sldTime.getValue();
            if (current < _sldTime.getMaximum()) {
                _sldTime.setValue(current + 1);
            } else {
                stopSlideshow();
            }
        }
    });

    /**
     *
     */
    static {
        I18N.use("house/Strings");
    }

    /**
     *
     */
    public void init() {
        PropertyList house = Utils.loadPropertyList(getParameter("url"));
        if (house == null) {
            return;
        }
        _assemblies = new Assemblies(house);

        _sldTime.setMaximum(_assemblies.getCount() - 1);
        _sldTime.setValue(_assemblies.getCount() - 1);
        _sldTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                updateDisplay();
            }
        });

        _sldTime.setSnapToTicks(true);
        _sldTime.setMajorTickSpacing(1);
        _sldTime.setPaintTicks(true);

        JPanel pnlDetails = new JPanel(new BorderLayout());
        pnlDetails.setBackground(Color.white);
        pnlDetails.add(_lblTitle, BorderLayout.NORTH);
        pnlDetails.add(_lblSubtitle, BorderLayout.CENTER);
        _lblTitle.setFont(new Font("Dialog", Font.PLAIN, 24));
        _lblSubtitle.setFont(new Font("Dialog", Font.PLAIN, 12));
        _lblTitle.setForeground(Color.black);
        _lblSubtitle.setForeground(Color.black);

        JPanel pnlControls = new JPanel(new BorderLayout(PADDING, PADDING));
        pnlControls.setBorder(
            new CompoundBorder(
                new LineBorder(Color.gray),
                new EmptyBorder(PADDING, PADDING, PADDING, PADDING)));
        pnlControls.add(_sldTime, BorderLayout.CENTER);
        pnlControls.add(_btnAutomate, BorderLayout.EAST);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnlDetails, BorderLayout.NORTH);
        cp.add(pnlControls, BorderLayout.SOUTH);
        cp.add(_pnlDisplay, BorderLayout.CENTER);

        updateDisplay();
    }

    /**
     *
     */
    public String[][] getParameterInfo() {
        return new String[][] {
            { "url", "url", I18N.get("UrlParamDesc") }
        };
    }

    /**
     *
     */
    private void stopSlideshow() {
        _btnAutomate.setText(I18N.get("SlideshowStart"));
        _slideshow.stop();
    }

    /**
     *
     */
    private void startSlideshow() {
        _sldTime.setValue(0);
        _slideshow.start();
    }

    /**
     *
     */
    private void updateDisplay() {
        int i = _sldTime.getValue();
        Assembly assembly = _assemblies.get(i);
        _lblTitle.setText(assembly.getName());
        _lblSubtitle.setText(assembly.getSubtitle());
        _pnlDisplay.set(assembly, _assemblies.get(i - 1));
    }
}
