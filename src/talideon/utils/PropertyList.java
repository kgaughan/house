/*
 * PropertyList.java
 *
 * Created on 20 June 2006, 21:17
 */

package talideon.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

/**
 *
 * @author kgaughan
 */
public class PropertyList {

    private final Properties _properties;

    public PropertyList(String url) throws MalformedURLException, IOException {
        _properties = internalLoad(url);
    }

    /**
     *
     */
    public String getString(final String name) {
        return (String) _properties.get(name);
    }

    /**
     *
     */
    public int getInt(final String name) {
        return Integer.parseInt(getString(name));
    }

    /**
     *
     */
    public String[] getStringArray(final String name) {
        return split(getString(name), ',');
    }

    /**
     *
     */
    public int[] getIntArray(final String name) {
        return toIntArray(getStringArray(name));
    }

    /**
     *
     */
    private static String[] toStringArray(final Object[] objs) {
        String[] strs = new String[objs.length];
        for (int i = 0; i < objs.length; ++i) {
            strs[i] = objs[i].toString();
        }
        return strs;
    }

    /**
     *
     */
    private static int[] toIntArray(final Object[] objs) {
        int[] ints = new int[objs.length];
        for (int i = 0; i < objs.length; ++i) {
            ints[i] = Integer.parseInt(objs[i].toString());
        }
        return ints;
    }

    /**
     *
     */
    private static String[] split(final String str, final char separator) {
        java.util.Collection parts = new java.util.LinkedList();

        int iStart = 0;
        int iEnd = 0;
        while (iEnd != -1) {
            iEnd = str.indexOf(separator, iStart);
            if (iEnd == -1) {
                parts.add(str.substring(iStart));
            } else {
                parts.add(str.substring(iStart, iEnd));
                iStart = iEnd + 1;
            }
        }

        return toStringArray(parts.toArray());
    }

    /**
     *
     */
    private static Properties internalLoad(final String url) throws MalformedURLException, IOException {
        final Properties properties = new Properties();
        java.io.InputStream inStream = null;
        try {
            inStream = new java.net.URL(url).openStream();
            properties.load(inStream);
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }
        return properties;
    }

    public static PropertyList load(final String url) {
        try {
            return new PropertyList(url);
        } catch (java.net.MalformedURLException ex) {
            talideon.ui.Utils.showError("Bad URL: " + url);
        } catch (java.io.IOException ex) {
            talideon.ui.Utils.showError("Could not load data from" + url);
        }
        return null;
    }
}
