/*
 * HouseComparator.java
 *
 * Created on 23 June 2006, 23:32
 */

package house.model;

import house.Utils;
import java.util.Comparator;

/**
 *
 * @author kgaughan
 */
public class GovernmentComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Group g1 = (Group) o1;
        Group g2 = (Group) o2;

        if (g1.isInGoverment() != g2.isInGoverment()) {
            // Government parties are ordered before opposition parties.
            return g1.isInGoverment() ? -1 : 1;
        }

        return g1.compareTo(g2);
    }
}
