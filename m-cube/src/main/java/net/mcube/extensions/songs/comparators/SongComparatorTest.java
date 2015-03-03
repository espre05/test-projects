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
 * Created on 9-gen-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.songs.comparators;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import net.della.mplatform.application.datatypes.FileItemAttributes;
import net.mcube.extensions.songs.DefaultSong;
import net.mcube.extensions.songs.SongAttributes;

/**
 * @author Daniele
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SongComparatorTest extends TestCase {
	
	public void testSort() {
		List list = new LinkedList();
		DefaultSong song1 = new DefaultSong();
		DefaultSong song2 = new DefaultSong();
		song1.put(FileItemAttributes.PATH, "song1");
		song2.put(FileItemAttributes.PATH, "song2");
		
		list.add(song2);
		list.add(song1);
		
		List sortedList = new LinkedList();
		sortedList.add(song1);
		sortedList.add(song2);
		
		assertFalse(sortedList.equals(list));
		
		song1.put(SongAttributes.TRACK_NUMBER, "5");
		song2.put(SongAttributes.TRACK_NUMBER, "10");
		
		SongComparator c = new SongComparator(SongAttributes.TRACK_NUMBER);
		Collections.sort(list, c);
		assertEquals(sortedList, list);
	}
	
	public void testSortTwoConditions() {
		List list = new LinkedList();
		DefaultSong song1 = new DefaultSong();
		DefaultSong song2 = new DefaultSong();
		DefaultSong song3 = new DefaultSong();
		song1.put(FileItemAttributes.PATH, "song1");
		song2.put(FileItemAttributes.PATH, "song2");
		song3.put(FileItemAttributes.PATH, "song3");
		
		list.add(song2);
		list.add(song1);
		list.add(song3);
				
		song1.put(SongAttributes.TRACK_NUMBER, "5");		
		song2.put(SongAttributes.TRACK_NUMBER, "10");
		song3.put(SongAttributes.TRACK_NUMBER, "07");
		song1.put(SongAttributes.ARTIST, "abramo");
		song2.put(SongAttributes.ARTIST, "abramo");
		song3.put(SongAttributes.ARTIST, "abramo");
		
		List sortedList = new LinkedList();
		sortedList.add(song1);
		sortedList.add(song3);
		sortedList.add(song2);
		
		SongComparator c = new SongComparator(SongAttributes.ARTIST, SongAttributes.TRACK_NUMBER);
		Collections.sort(list, c);
		assertEquals(sortedList, list);
	}

}
