
package della.swaf.extensions.util.glazedlists;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.migrationkit.AbstractFilterList;

/*
 * Created on 30-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */

public class CustomFilteredList extends AbstractFilterList {

	private List customFilters;

	public CustomFilteredList(EventList source) {
		super(source);
		customFilters = new LinkedList();

		handleFilterChanged();
	}

	public boolean filterMatches(Object element) {
		/*
		 * for (Iterator it = customFilters.iterator(); it.hasNext();) {
		 * CustomFilter filter = (CustomFilter) it.next(); if
		 * (!filter.filterMatches(element)) return false; } return true;
		 */

		source.getReadWriteLock().readLock().lock();
		try {
			for (Iterator it = customFilters.iterator(); it.hasNext();) {
				CustomFilter filter = (CustomFilter) it.next();
				if (!filter.filterMatches(element))
					return false;
			}
			return true;
		} finally {
			source.getReadWriteLock().readLock().unlock();
		}

	}

	public void addCustomFilter(CustomFilter filter) {
		customFilters.add(filter);
		handleFilterChanged();
	}

    /**
     * 
     * @param filter
     * refresh of the list is forced
     * 
     */
	public void removeCustomFilter(CustomFilter filter) {
		customFilters.remove(filter);
		handleFilterChanged();
	}

    
    /**
     * 
     * @param filter
     * @param forceRefresh
     */
	public void removeCustomFilter(CustomFilter filter, boolean forceRefresh) {
		customFilters.remove(filter);
		if (forceRefresh)
			handleFilterChanged();
	}
    
    public void refilter() {
        handleFilterChanged();
    }


    public boolean hasFilters() {
        return customFilters.size() != 0;
    }


    public void addCustomFilter(CustomFilter filter, boolean forceRefresh) {
        customFilters.add(filter);
        if (forceRefresh)
            handleFilterChanged();
    }

}