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

package net.mcube.extensions.songs;

import java.io.File;
import java.util.List;

import net.della.mplatform.core.datatypes.*;
import net.della.stuff.generic.util.MathUtil;
import net.della.stuff.generic.util.StringFormatter;

import ca.odell.glazedlists.TextFilterable;
import ca.odell.glazedlists.TextFilterator;



/**
 * @author Daniele
 * 
 */
public class DefaultSong extends FileItem implements Song, TextFilterable, Comparable {

	public DefaultSong() {
		setMainAttribute(SongAttributes.TITLE);
	}

	public void removeID3v1() {
	}

	public void removeTag() {
	}

	public void updateID3Tag(boolean ID3v2Only) {
	}

	public void renameFileFromData() {
		String newFileName = extractFilenameFromData();
		try {
			File oldFile = new File(getPath());
			String dir = oldFile.getParent();
			File newFile = new File(dir + File.separator + newFileName);
			oldFile.renameTo(newFile);
			//TODO fix according to relative path
//			setPath(newFile.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String extractFilenameFromData() {

		String album = StringFormatter.clean(getAlbum());
		String artist = StringFormatter.clean(getArtist());
		String title = StringFormatter.clean(getTitle());
		String track = getTrack();
		if (track.length() == 1)
			track = "0" + track;

		StringBuffer sb = new StringBuffer();
		String ext = getExtension();
		sb.append(artist + " - " + album + " - " + track + " - " + title + "." + ext);
		//bovati version
//		sb.append(album + " - " + title + "." + ext);
		return sb.toString();
	}

	public void formatData() {
		setTitle(StringFormatter.format(getTitle(), StringFormatter.FIRST_CAPITAL));
	}

	public void formatData(StringFormatter sf) {
		setTitle(sf.format(getTitle()));
	}

	void setArtist(String value) {
		put(SongAttributes.ARTIST, value);
	}

	void setAlbum(String value) {
		put(SongAttributes.ALBUM, value);
	}

	void setTitle(String value) {
		put(SongAttributes.TITLE, value);
	}

	void setTrack(String value) {
		put(SongAttributes.TRACK_NUMBER, value);
	}

	void setYear(String value) {
		put(SongAttributes.YEAR, value);
	}

	void setDisc(String value) {
		put(SongAttributes.DISC, value);
	}

	void setLive(String value) {
		put(SongAttributes.DISC, value);
	}

	public String getTitle() {
		return getString(SongAttributes.TITLE);
	}

	public String getAlbum() {
		return getString(SongAttributes.ALBUM);
	}

	public String getArtist() {
		return getString(SongAttributes.ARTIST);
	}

	public String getTrack() {
		return getString(SongAttributes.TRACK_NUMBER);
	}

	public String getYear() {
		return getString(SongAttributes.YEAR);
	}

	public String getDisc() {
		return getString(SongAttributes.DISC);
	}

	public String isLive() {
		return getString(SongAttributes.ALBUM_TYPE);
	}

	public String toString() {
		return "filename: " + new File(getPath()).getName();
	}

	public int compare(ObservableItem song, String compareField) {
		String value1String = getString(compareField);
		String value2String = song.getString(compareField);
		if (compareField.equals(SongAttributes.TRACK_NUMBER)
				|| compareField.equals(SongAttributes.YEAR)) {
			if (!value1String.equals("") && !value2String.equals(""))
				return MathUtil.compareIntValue(value1String, value2String);
			return MathUtil.compareStringValue(value1String, value2String);
		}
		return MathUtil.compareStringValue(value1String, value2String);

	}

	public int compareTo(Object o) {
		Song otherLanguage = (Song) o;
		return getPath().compareToIgnoreCase(otherLanguage.getPath());
	}

	public void getFilterStrings(List baseList) {
		baseList.add(getString(SongAttributes.ARTIST));
		baseList.add(getString(SongAttributes.ALBUM));
		baseList.add(getString(SongAttributes.TITLE));
		baseList.add(getString(SongAttributes.YEAR));
		baseList.add(getString(SongAttributes.GENRE));
		baseList.add(getString(SongAttributes.ALBUM_TYPE));
		// baseList.add(attrs.getProperty(SongAttributes.PATH));

	}

	public String getId() {
		return getPath();
	}

	public void setAlbumType(String value) {
		put(SongAttributes.ALBUM_TYPE, value);
	}

	public int countChilds() {
		return 0;
	}

	public Item getChild(int index) {
		return new NullItem();
	}

	public static TextFilterator createSongFilterator() {
		return new TextFilterator() {

			public void getFilterStrings(List baseList, Object element) {
				ObservableItem item = (ObservableItem) element;
				baseList.add(item.getString(SongAttributes.ARTIST));
				baseList.add(item.getString(SongAttributes.ALBUM));
				baseList.add(item.getString(SongAttributes.TITLE));
				baseList.add(item.getString(SongAttributes.YEAR));
				baseList.add(item.getString(SongAttributes.GENRE));
				baseList.add(item.getString(SongAttributes.ALBUM_TYPE));
			}

		};
	}

	public String getBitRate() {
		return "";
	}

}