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
 * Created on 8-ago-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package net.mcube.extensions.songs.comparators;

import java.util.Comparator;
import java.util.Properties;

import net.mcube.datatypes.comparators.ItemSetComparator;
import net.mcube.extensions.songs.SongAttributes;

/**
 * @author Daniele
 *  
 */
public class ComparatorFactory {

	private static Properties secondaryTable = new Properties();
	private static Properties thirdTable = new Properties();
	private static ComparatorFactory instance;
    private final TrackNumberSongComparator trackNumberSongComparator;

	private ComparatorFactory() {
		secondaryTable.setProperty(SongAttributes.ARTIST, SongAttributes.ALBUM);
		secondaryTable.setProperty(SongAttributes.ALBUM, SongAttributes.DISC);
		thirdTable.setProperty(SongAttributes.ARTIST, SongAttributes.TRACK_NUMBER);
		thirdTable.setProperty(SongAttributes.ALBUM, SongAttributes.TRACK_NUMBER);
        trackNumberSongComparator = new TrackNumberSongComparator();
	}

	public static ComparatorFactory getInstance() {
		if (instance == null)
			instance = new ComparatorFactory();
		return instance;
	}

	public Comparator trackNumberSongComparator() {

		return trackNumberSongComparator;
	}

	public Comparator createSongComparator(String columnName) {
		return new SongComparator(columnName, secondaryTable.getProperty(columnName), thirdTable
				.getProperty(columnName));
	}
	
	public Comparator createSongSetComparator(String columnName) {
		return new ItemSetComparator(columnName);
	}

	public Comparator createMultiValueSongSetComparator(String columnName) {
		return new ItemSetComparator(columnName, secondaryTable.getProperty(columnName), thirdTable
				.getProperty(columnName));
	}

}