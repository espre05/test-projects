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
 * Created on 24-lug-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.songs.comparators;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import net.mcube.extensions.songs.DefaultSong;
import net.mcube.extensions.songs.SongAttributes;

/**
 * @author Daniele
 * 
 */
public class TrackNumberSongComparatorTest extends TestCase {

   public void testSongs() {
      TrackNumberSongComparator c = new TrackNumberSongComparator();
      DefaultSong song1 = new DefaultSong();
      song1.put(SongAttributes.TRACK_NUMBER, "4");
      DefaultSong song2 = new DefaultSong();
      song2.put(SongAttributes.TRACK_NUMBER, "3");

      List list = new LinkedList();
      list.add(song2);
      list.add(song1);
      List sortedList = new LinkedList();
      sortedList.add(song1);
      sortedList.add(song2);

      assertNotSame(sortedList, list);

      Collections.sort(list, c);
      assertNotSame(sortedList, list);

      song2.put(SongAttributes.TRACK_NUMBER, "11");
      Collections.sort(list, c);
      assertEquals(sortedList, list);

   }

}