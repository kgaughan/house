/*
 * Utils.java
 *
 * Created on 19 June 2006, 14:43
 */

package house;

import java.awt.Color;
import talideon.utils.PropertyList;

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
    public static boolean contains(final Object[] haystack, final Object needle) {
        for (int i = 0; i < haystack.length; ++i) {
            if (haystack[i].equals(needle)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     */
    public static PropertyList loadPropertyList(final String url) {
        PropertyList list = null;
        try {
            list = new PropertyList(url);
        } catch (java.net.MalformedURLException ex) {
            showError("Bad URL: " + url);
        } catch (java.io.IOException ex) {
            showError("Could not load data from" + url);
        }
        return list;
    }

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
