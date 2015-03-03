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
 * Created on 5-gen-2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package net.mcube.extensions.songs;

import java.util.*;

import net.della.mplatform.application.datatypes.ObservableItem;




/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
/**
 * 
 * @deprecated
 */
public class SongSetBuilder {

	public static SongSet buildEditSongSet(List itemList) {
		if (itemList.get(0) instanceof SongSet)
			return (SongSet) itemList.get(0);
		return createSongSetFromSongsList(itemList);
	}

	private static SongSet createSongSetFromSongsList(List songsList) {
		SongSet songSet = new SongSet();
		Map map = initEditMap();
		for (Iterator it = songsList.iterator(); it.hasNext();) {
			DefaultSong song = (DefaultSong) it.next();
			addSongDataToMap(map, song);
			songSet.addItem(song);
		}
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			Object attribute = iter.next();
			String value = chooseRightValueFor((List) map.get(attribute));
			songSet.put((String) attribute, value);
		}
		return songSet;
	}


	/**
	 * populate map with values from this song the map will be like: key="artist"
	 * value=["Led Zeppelin", "Pink Floyd", "R.E.M."] it is the row map from
	 * where we choose the SongSet real attributes
	 */
	private static void addSongDataToMap(Map map, ObservableItem song) {
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			Object key = iter.next();
			List traks = (List) map.get(key);
			traks.add(song.getString((String) key));
		}
		//addDerivedData(map, song);
	}

	private static String chooseRightValueFor(List list) {
		String value1 = "";
		if (hasAllElementsTheSameValue(list))
			value1 = (String) list.get(0);
		return value1;
	}

	private static Map initEditMap() {
		Map newMap = new HashMap();
		newMap.put(SongAttributes.ARTIST, new LinkedList());
		newMap.put(SongAttributes.ALBUM, new LinkedList());
		newMap.put(SongAttributes.TITLE, new LinkedList());
		newMap.put(SongAttributes.GENRE, new LinkedList());
		newMap.put(SongAttributes.YEAR, new LinkedList());
		newMap.put(SongAttributes.TRACK_NUMBER, new LinkedList());
		newMap.put(SongAttributes.COMMENT, new LinkedList());
		newMap.put(SongAttributes.DISC, new LinkedList());
		newMap.put(SongAttributes.ALBUM_TYPE, new LinkedList());
		return newMap;
	}

	static boolean hasAllElementsTheSameValue(List list) {
		String attr = (String) list.get(0);
		for (Iterator it = list.iterator(); it.hasNext();) {
			String value = (String) it.next();
			if (!value.equals(attr))
				return false;
		}
		return true;
	}

}