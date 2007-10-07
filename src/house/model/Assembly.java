/*
 * NewInterface.java
 *
 * Created on 21 June 2006, 18:02
 */

package house.model;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author kgaughan
 */
public abstract class Assembly {

    /**
     * The point of this class is to ensure that whatever number is passed
     * into Assemblies.get(), there'll always be a preceding Assembly instance;
     * this way, we don't have to worry about figuring out what to do to get
     * the number of seats gained for the first election. Aren't edge cases
     * fun!
     */
    private static final Assembly NULL = new Assembly() {

        public void add(final Group group) {
            // Do nothing
        }

        public int getGroupSeats(final Group other) {
            return 0;
        }

        public int getTotalSeats() {
            return 0;
        }

        public String getName() {
            return "[null]";
        }

        public String getSubtitle() {
            return "[null]";
        }

        public Iterator iterator() {
            return null;
        }

        public Iterator iterator(Comparator c) {
            return null;
        }
    };

    /**
     */
    public abstract void add(final Group group);

    /**
     */
    public abstract int getGroupSeats(final Group other);

    /**
     *
     */
    public abstract int getTotalSeats();

    /**
     */
    public abstract String getName();

    /**
     */
    public abstract String getSubtitle();

    /**
     */
    public abstract Iterator iterator();

    /**
     */
    public abstract Iterator iterator(Comparator c);

    /**
     *
     */
    public static Assembly getNull() {
        return NULL;
    }
}
