/*
 * Group.java
 *
 * Created on 19 June 2006, 06:45
 */

package house.model;

import house.Utils;

/**
 *
 * @author kgaughan
 */
public class Group implements Comparable {

    private final Party   _party;
    private final int     _nSeats;
    private final boolean _inGovernment;

    /**
     * Creates a new instance of Group
     */
    public Group(final Party party, final int nSeats, final boolean inGovernment) {
        _party        = party;
        _nSeats       = nSeats;
        _inGovernment = inGovernment;
    }

    /**
     *
     */
    public String getName() {
        return _party.name;
    }

    /**
     *
     */
    public int getSeats() {
        return _nSeats;
    }

    /**
     *
     */
    public boolean isInGoverment() {
        return _inGovernment;
    }

    /**
     *
     */
    public String toString() {
        return _party.name + " (" + _nSeats + ")";
    }

    public String getColor() {
        return _party.color;
    }

    public int compareTo(Object o) {
        Group other = (Group) o;
        return _party.order - other._party.order;
    }

    public boolean equals(final Object o) {
        final Group other = (Group) o;
        return _party == other._party;
    }
}
