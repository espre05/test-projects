/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

/*
 * Created on 1-giu-2005 
 * 
 */
package net.mcube.extensions.tracksView;

import java.util.Iterator;
import java.util.List;

import net.della.mplatform.application.datatypes.ItemSet;
import net.mcube.extensions.album.AlbumAttributes;
import della.swaf.extensions.gui.TableView;
import della.swaf.extensions.gui.ViewBuilder;
import della.swaf.extensions.util.glazedlists.CustomFilter;

/**
 * @author Della
 * 
 */
public class TracksView extends TableView {

	private TracksMultipleDiscBarBuilder barBuilder;

	private List baseList;

	private PersistentTableFormat currentTableFormat;

	public TracksView(List sourceList) {
		
		barBuilder = new TracksMultipleDiscBarBuilder(this);
		this.baseList = sourceList;
	}

	private final class AlwaysFalseFilter extends CustomFilter {
		public boolean filterMatches(Object element) {
			return false;
		}
	}

	public void loadList(List selectionList, PersistentTableFormat newTableFormat) {
		if (currentTableFormat != null)
			saveToDisk(currentTableFormat.getPersistenceFilename());
		ViewBuilder.updateTableView(this, newTableFormat);
		loadFromDisk(newTableFormat.getPersistenceFilename());
		currentTableFormat = newTableFormat;
		
		baseList.clear();
		for (Iterator it = selectionList.iterator(); it.hasNext();) {
			ItemSet songSet = (ItemSet) it.next();
			baseList.addAll(songSet.listChilds());
		}
		if (selectionList.size() == 0 || selectionList.size() > 1) {
			barBuilder.setVisible(false);
			this.revalidateAndRepaint();
			return;
		}
		ItemSet album = (ItemSet) selectionList.get(0);
		String numberOfDiscsString = album.getString(AlbumAttributes.NUMBER_OF_DISCS);
		if (numberOfDiscsString.equals(""))
			return;
		int numberOfDiscs = Integer.parseInt(numberOfDiscsString);
		if (numberOfDiscs <= 1) {
			barBuilder.setVisible(false);
			this.revalidateAndRepaint();
			return;
		}
		this.setStatusBar(barBuilder.updateBarButtons(numberOfDiscs));
		barBuilder.setVisible(true);
		this.revalidateAndRepaint();
	}

	public List getDisplayedList() {
		return baseList;
	}
}
