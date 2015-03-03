
package della.swaf.extensions.util.glazedlists;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.odell.glazedlists.EventList;

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

public class SelectionCustomFilteredList extends CustomFilteredList
		implements
			ListSelectionListener {

	private int lastSelection = -1;

	public SelectionCustomFilteredList(EventList source) {
		super(source);
	}

	public void valueChanged(ListSelectionEvent e) {
	
		if (e.getFirstIndex() == lastSelection)
			return;
		
		lastSelection = e.getFirstIndex();

		handleFilterChanged();
	}

}