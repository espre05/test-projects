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
 * Created on 30-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.songs;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.della.mplatform.core.datatypes.ItemSet;
import net.della.mplatform.util.NullComparator;


/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SongSet extends ItemSet implements Comparable {

	public SongSet(Comparator c) {
		super(c);
	}

	public SongSet() {
		this(new NullComparator());
	}

	public static boolean updateID3 = true;

	public void update() {
		super.update();
//		for (Iterator it = getChilds().iterator(); it.hasNext();) {
//			Item item = (Item) it.next();
//			Map data = new HashMap(getData());
//			data.remove(SongAttributes.ALL_ARTISTS);
//			item.setAllData(data, true);
//		}
		Iterator it = listChilds().iterator();
		if (updateID3)
			while (it.hasNext()) {
				Song song = (Song) it.next();
				song.updateID3Tag(true);
			}
	}

	public void addItem(DefaultSong item) {
		super.addItem(item);
	}

	public void addAll(List songs) {
		for (Iterator it = songs.iterator(); it.hasNext();) {
			addItem((DefaultSong) it.next());
		}
		sort();
	}

	public String toString() {
		return getString(getMainAttribute());
	}

	public int compareTo(Object o) {
		SongSet otherLanguage = (SongSet) o;
		return getString(getMainAttribute()).compareToIgnoreCase(
				otherLanguage.getString(getMainAttribute()));

		// ItemSet itemSet = (ItemSet) o;
		// return
		// getData(getMainAttribute()).compareToIgnoreCase(itemSet.getData(getMainAttribute()));

	}

}
