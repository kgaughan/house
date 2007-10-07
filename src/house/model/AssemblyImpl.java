/*
 * Assembly.java
 *
 * Created on 19 June 2006, 06:14
 */

package house.model;

import talideon.i18n.I18N;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author kgaughan
 */
public class AssemblyImpl extends Assembly {

    private final String     _name;
    private final String     _date;
    private final Collection _groups = new TreeSet();

    // Caches to avoid recalculation.
    private int _seats           = 0;
    private int _governmentSeats = 0;

    /**
     * Creates a new instance of House
     */
    public AssemblyImpl(final String name, final String dt) {
        _name = name;
        _date = dt;
    }

    /**
     *
     */
    public void add(final Group group) {
        _groups.add(group);
        invalidateCache();
    }

    /**
     *
     */
    public int getGroupSeats(final Group other) {
        // TODO: Think of a better way of writing this.
        Iterator iter = _groups.iterator();
        while (iter.hasNext()) {
            Group group = (Group) iter.next();
            if (group.equals(other)) {
                return group.getSeats();
            }
        }
        return 0;
    }

    /**
     *
     */
    public int getTotalSeats() {
        return _seats;
    }

    /**
     *
     */
    public Iterator iterator() {
        return _groups.iterator();
    }

    public Iterator iterator(final Comparator c) {
        Collection collection = new TreeSet(c);
        collection.addAll(_groups);
        return collection.iterator();
    }

    public String getName() {
        return _name;
    }

    public String getSubtitle() {
        recalculateCache();

        StringBuffer buf = new StringBuffer();
        buf.append(_date);
        buf.append(I18N.get("Separator"));
        buf.append(_seats);

        if (_groups.size() == 1) {
            buf.append(I18N.get("SingleParty"));
        } else {
            int plurality = _governmentSeats - (_seats / 2);
            if (plurality != 0) {
                if (plurality >= 0) {
                    buf.append(I18N.get("MajorityBy"));
                } else {
                    buf.append(I18N.get("MinorityBy"));
                }
                buf.append(Math.abs(plurality));
            } else {
                buf.append(I18N.get("EvenSplit"));
            }
        }
        return buf.toString();
    }

    private void invalidateCache() {
        _seats = 0;
    }

    private void recalculateCache() {
        if (_seats == 0) {
            _governmentSeats = 0;

            Iterator iter = _groups.iterator();
            while (iter.hasNext()) {
                Group group = (Group) iter.next();
                _seats += group.getSeats();
                if (group.isInGoverment()) {
                    _governmentSeats += group.getSeats();
                }
            }
        }
    }
}
