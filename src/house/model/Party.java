/*
 * Party.java
 *
 * Created on 18 June 2006, 07:30
 */

package house.model;

/**
 *
 * @author kgaughan
 */
public class Party {

    public final String name;
    public final String color;
    public final int    order;

    /**
     * Creates a new instance of Party.
     */
    public Party(final String name, final String color, final int order) {
        this.name  = name;
        this.color = color;
        this.order = order;
    }
}
