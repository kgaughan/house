/*
 * Utils.java
 *
 * Created on 19 June 2006, 14:43
 */

package talideon.ui;

import java.awt.Color;

/**
 * Miscellaneous utilities used by the rest of the code.
 *
 * @author kgaughan
 */
public class Utils {

    // Prevents instantiation.
    private Utils() {}

    /**
     *
     */
    public static void showError(final String message) {
        javax.swing.JOptionPane.showMessageDialog(null, message);
    }

    /**
     *
     */
    public static Color getAWTColor(final String colorName) {
        final Color color = Color.decode(colorName);
        if (color == null) {
            // Something suitably garish and unlikely to show the colour
            // couldn't be decoded without having to throw an exception or
            // something.
            return Color.magenta;
        }
        return color;
    }
}
