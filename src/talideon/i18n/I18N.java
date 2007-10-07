/*
 * I18N.java
 *
 * Created on 21 June 2006, 03:35
 */

package talideon.i18n;

import java.util.ResourceBundle;

/**
 *
 * @author kgaughan
 */
public abstract class I18N {

    /**
     *
     */
    private static I18N _source = new NullI18N();

    /**
     *
     */
    protected I18N() {}

    /**
     *
     */
    protected abstract String handleGet(final String key);

    /**
     *
     */
    public static String get(final String key) {
        return _source.handleGet(key);
    }

    /**
     *
     */
    public static void use(final String name) {
        _source = new ResourceBundleI18N(name);
    }

    /**
     *
     */
    public static void use(final ResourceBundle bundle) {
        _source = new ResourceBundleI18N(bundle);
    }
}

/**
 *
 */
class NullI18N extends I18N {

    /**
     *
     */
    protected String handleGet(final String key) {
        return key;
    }
}

/**
 *
 */
class ResourceBundleI18N extends I18N {

    /**
     *
     */
    private ResourceBundle _bundle;

    /**
     *
     */
    public ResourceBundleI18N(final String name) {
        _bundle = ResourceBundle.getBundle(name);
    }

    /**
     *
     */
    public ResourceBundleI18N(final ResourceBundle bundle) {
        _bundle = bundle;
    }

    /**
     *
     */
    protected String handleGet(final String key) {
        return _bundle.getString(key);
    } 
}
