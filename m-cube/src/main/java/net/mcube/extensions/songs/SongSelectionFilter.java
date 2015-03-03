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
 * Created on 6-ago-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.songs;

import java.util.Iterator;
import java.util.List;

import net.della.mplatform.application.datatypes.ObservableItem;
import net.mcube.util.SelectionFilter;


public class SongSelectionFilter extends SelectionFilter {

	public SongSelectionFilter(String fieldName, List selectionList) {
		super(fieldName, selectionList);
	}

	public boolean filterMatches(Object element) {

		if (element == null)
			return false;
		if (selectionList.isEmpty())
			return false;
		ObservableItem actualSong = (ObservableItem) element;
		for (Iterator it = selectionList.iterator(); it.hasNext();) {
			SongSet selectedSongSet = (SongSet) it.next();
			if (actualSong.getString(fieldName).equals(selectedSongSet.getString(fieldName)))
				return true;
		}
		return false;

	}
}