/*
 * Utils.java
 *
 * Created on 18 June 2006, 09:02
 */

package talideon;

/**
 *
 * @author kgaughan
 */
public class Utils {

    //    0     1     2     3     4     5    6      7     8     9
    private static final String[] ORDINALS = {
        "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"
    };

    private Utils() {
    }

    public static String toOrdinal(int n) {
        String suffix;

        // Special case for the teens
        if (n > 10 && n < 14) {
            suffix = "th";
        } else {
            suffix = ORDINALS[n % 10];
        }

        return Integer.toString(n) + suffix;
    }

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
}
