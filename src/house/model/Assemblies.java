/*
 * Assemblies.java
 *
 * Created on 19 June 2006, 22:38
 */

package house.model;

import talideon.utils.PropertyList;
import house.Utils;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kgaughan
 */
public class Assemblies {

    private Assembly[] _assemblies;
    private Map        _parties = new HashMap();

    /** Creates a new instance of Assemblies */
    public Assemblies(PropertyList details) {
        final int nAssemblies = details.getInt("assemblies");
        _assemblies = new AssemblyImpl[nAssemblies];

        for (int i = 1; i <= nAssemblies; ++i) {
            _assemblies[i - 1] = extract(i, details);
        }
    }

    /**
     *
     */
    public int getCount() {
        return _assemblies.length;
    }

    /**
     *
     */
    public Assembly get(final int i) {
        if (i < 0 || i >= _assemblies.length) {
            return Assembly.getNull();
        }
        return _assemblies[i];
    }

    /**
     *
     */
    private Assembly extract(final int i, final PropertyList details) {
        final String   name       = details.getString(i + ".name");
        final String   date       = details.getString(i + ".date");
        final String[] government = details.getStringArray(i + ".government");
        final String[] parties    = details.getStringArray(i + ".parties");
        final int[]    seats      = details.getIntArray(i + ".seats");

        final Assembly assembly = new AssemblyImpl(name, date);
        for (int iParty = 0; iParty < parties.length; ++iParty) {
            final Party   party        = findParty(parties[iParty], details);
            final boolean inGovernment = Utils.contains(government, parties[iParty]);
            assembly.add(new Group(party, seats[iParty], inGovernment));
        }
        return assembly;
    }

    /**
     *
     */
    private Party findParty(final String id, PropertyList details) {
        if (!_parties.containsKey(id)) {
            final String name  = details.getString(id + ".name");
            final String color = details.getString(id + ".colour");
            final int    order = details.getInt(id + ".order");
            _parties.put(id, new Party(name, color, order));
        }
        return (Party) _parties.get(id);
    }    
}
