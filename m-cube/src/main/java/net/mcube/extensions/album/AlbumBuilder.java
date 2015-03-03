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
 * Created on 29-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.album;

import java.util.*;

import net.della.mplatform.core.datatypes.FileItemAttributes;
import net.della.mplatform.core.datatypes.Item;
import net.della.mplatform.core.datatypes.ItemSet;
import net.mcube.datatypes.DefaultItemSetBuilder;
import net.mcube.datatypes.ImageProperty;
import net.mcube.extensions.songs.DefaultSongBuilder;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.extensions.songs.SongSet;
import net.mcube.extensions.songs.comparators.ComparatorFactory;
import net.mcube.util.Merger;

public final class AlbumBuilder extends DefaultItemSetBuilder {
	final String AUTORI_VARI = "AA.VV.";

	final String SOUNDTRACK = "Soundtrack";

	private Map rawDataMap;

	private SongSet album;

	private final Merger merger;

	public AlbumBuilder() {
		merger = new Merger();
	}

	protected ItemSet createItemSet() {
		album = new SongSet(ComparatorFactory.getInstance().trackNumberSongComparator());
		return album;
	}

	public void buildItemSet(ItemSet itemSet) {

		List songList = itemSet.listChilds();
		rawDataMap = initRawDataMap();
		album.setMainAttribute(SongAttributes.ALBUM);

		populateRawMapWithSongsInfo(songList);

		String attribute = SongAttributes.ALBUM;

		// attribute = SongAttributes.ALL_ARTISTS;
		// String artist = merger.putAllStringFrom((List)
		// rawDataMap.get(SongAttributes.ARTIST));
		// album.setData(attribute, artist);

		attribute = SongAttributes.ARTIST;
		String artist = merger
				.chooseRightValueFor((List) rawDataMap.get(SongAttributes.ARTIST), "");
		if (artist.equals(""))
			artist = AUTORI_VARI;
		album.put(attribute, artist);
		attribute = SongAttributes.GENRE;
		album.put(attribute, merger.chooseRightValueFor((List) rawDataMap.get(attribute), ""));
		attribute = SongAttributes.YEAR;
		album.put(attribute, merger.chooseRightValueFor((List) rawDataMap.get(attribute), ""));
		int l = 0;
		List list = (List) rawDataMap.get(SongAttributes.LENGTH);
		for (Iterator it = list.iterator(); it.hasNext();) {
			String length = (String) it.next();
			if (length.equals(""))
				l = 0;
			else
				l += (new Integer(length)).intValue();
		}
		String totalLength = l + "";
		album.put(AlbumAttributes.LENGTH, totalLength);
		album.put(SongAttributes.TOTAL_TRACKS, new String(((List) rawDataMap
				.get(SongAttributes.TOTAL_TRACKS)).size()
				+ ""));

		album.put(AlbumAttributes.DATE_ADDED, merger.pickOlderDateAddedFor((List) rawDataMap
				.get(FileItemAttributes.DATE_ADDED)));

		album.put(AlbumAttributes.NUMBER_OF_DISCS, merger.pickMaxValueFor((List) rawDataMap
				.get(SongAttributes.DISC)));

		super.buildItemSet(itemSet);

		// Map albumDb = library.getDbIndexedBy(AlbumExtension.VIEW_ID,
		// SongAttributes.ALBUM);
		// Map albumProps = (Map)
		// albumDb.get(album.getString(SongAttributes.ALBUM));
//		ImageCache imageCache = LibraryImpl.getDefault().getImageCache();

		assignProperty(SongAttributes.ALBUM_TYPE, DefaultSongBuilder.DEFAULT_ALBUM_TYPE);
		// updateChilds(SongAttributes.ALBUM_TYPE);

		assignProperty(SongAttributes.ALBUM_COVER_FRONT_PATH, ImageProperty.DEFAULT_CD_COVER_FILE_NAME);
		// updateChilds(SongAttributes.ALBUM_COVER_FRONT_PATH);
		album.put(SongAttributes.ALBUM_COVER_FRONT, ImageProperty.getInstance());

		assignProperty(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL_PATH,
				ImageProperty.DEFAULT_CD_COVER_THUMBNAIL_FILE_NAME);
		// updateChilds(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL_PATH);
		album.put(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL, ImageProperty.getInstance());

		// super.buildItemSet(album, albumProps);
	}

	private void assignProperty(String propertyName, String defaultValue) {
		String value = merger.chooseRightValueFor((List) rawDataMap.get(propertyName), "");
		// if (value == null || value.equals("")) {
		// value = (String) albumProps.get(propertyName);
		// }
		if (value == null || value.equals("")) {
			value = defaultValue;
		}

		album.put(propertyName, value);
	}

	private void populateRawMapWithSongsInfo(List mp3SongsList) {
		for (Iterator it = mp3SongsList.iterator(); it.hasNext();) {
			Item song = (Item) it.next();
			addSongDataToMap(song);
		}
	}

	private static Map initRawDataMap() {
		Map newMap = new HashMap();
		newMap.put(SongAttributes.ARTIST, new LinkedList());
		newMap.put(SongAttributes.ALBUM, new LinkedList());
		newMap.put(SongAttributes.GENRE, new LinkedList());
		newMap.put(SongAttributes.YEAR, new LinkedList());
		newMap.put(SongAttributes.TOTAL_TRACKS, new LinkedList());
		newMap.put(SongAttributes.LENGTH, new LinkedList());
		newMap.put(SongAttributes.DISC, new LinkedList());
		newMap.put(FileItemAttributes.DATE_ADDED, new LinkedList());
		// newMap.put(ItemAttributes.FILE_LAST_MODIFIED, new LinkedList());
		newMap.put(SongAttributes.ALBUM_TYPE, new LinkedList());
		// newMap.put(SongAttributes.ALBUM_COVER_FRONT, new LinkedList());
		newMap.put(SongAttributes.ALBUM_COVER_FRONT_PATH, new LinkedList());
		// newMap.put(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL, new
		// LinkedList());
		newMap.put(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL_PATH, new LinkedList());
		return newMap;
	}

	/**
	 * populate map with values from this song the map will be like:
	 * key="artist" value=["Led Zeppelin", "Pink Floyd", "R.E.M."] it is the row
	 * map from where we choose the SongSet real attributes
	 */
	private void addSongDataToMap(Item song) {
		for (Iterator iter = rawDataMap.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			List dataList = (List) rawDataMap.get(key);
			Object value = song.get(key);
			if (value != null)
				dataList.add(value);
		}
	}

}