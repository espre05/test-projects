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
 * Created on 7-ago-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.songs;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.stuff.generic.util.StringFormatter;
import net.mcube.datatypes.DefaultItemBuilder;



/**
 * @author Daniele
 * 
 */
public class DefaultSongBuilder extends DefaultItemBuilder {

	public static final String SINGLE = "Single";

	public static final String EP = "EP";

	public static final String ALBUM = "Album";

	public static final String SOUNDTRACK = "Soundtrack";

	public static final String LIVE = "Live";

	public static final String COMPILATION = "Compilation";

	private Properties albumTypesMap;

	private final List possibleCollectionsAlbumName;

	public static final String BOOTLEG = "Bootleg";

	private final SongGuesser guesser;

	public static final String DEFAULT_ALBUM_TYPE = "Album";

	public DefaultSongBuilder() {

		super();

		possibleCollectionsAlbumName = new LinkedList();
		possibleCollectionsAlbumName.add(COMPILATION);
		possibleCollectionsAlbumName.add("Greatest Hits");
		possibleCollectionsAlbumName.add("Anthology");
		possibleCollectionsAlbumName.add("Best Of");
		possibleCollectionsAlbumName.add("The Best Of");
		possibleCollectionsAlbumName.add("Collection");
		possibleCollectionsAlbumName.add("Box Set");
		possibleCollectionsAlbumName.add("Boxed Set");

		albumTypesMap = new Properties();
		albumTypesMap.setProperty(EP, EP);
		albumTypesMap.setProperty(SINGLE, SINGLE);

		for (Iterator it = possibleCollectionsAlbumName.iterator(); it.hasNext();) {
			String key = (String) it.next();
			albumTypesMap.setProperty(key, COMPILATION);
		}

		albumTypesMap.setProperty(LIVE, LIVE);
		albumTypesMap.setProperty(BOOTLEG, BOOTLEG);
		albumTypesMap.setProperty(SOUNDTRACK, SOUNDTRACK);
		albumTypesMap.setProperty("OST", SOUNDTRACK);

		guesser = new SongGuesser();

	}

	public void createItem() {
		item = new DefaultSong();
	}

	public void fixSomeProblems() {
		fixCollectionsProblem(item);
		formatData();
	}

	private void formatData() {
		format(SongAttributes.ARTIST, StringFormatter.ALL_CAPITAL);
		format(SongAttributes.ALBUM, StringFormatter.ALL_CAPITAL);
		format(SongAttributes.TITLE, StringFormatter.FIRST_CAPITAL);
	}

	private void format(String attribute, int formatType) {
		String value = item.getString(attribute);
		value = StringFormatter.format(value, formatType);
		item.put(attribute, value);
	}

	private void fixCollectionsProblem(ObservableItem song) {

		for (Iterator it = possibleCollectionsAlbumName.iterator(); it.hasNext();) {
			String key = (String) it.next();
			if (song.getString(SongAttributes.ALBUM).equalsIgnoreCase(key))
				song.put(SongAttributes.ALBUM, song.getString(SongAttributes.ALBUM) + " - "
						+ song.getString(SongAttributes.ARTIST));
		}

	}

	public void extractInfoFromGatheredData() {

		guessAlbumTypeFromAlbumTitle(item);

		if (!item.getString(SongAttributes.ALBUM_TYPE).equals(""))
			return;

		String genre = item.getString(SongAttributes.GENRE);
		if (genre.equalsIgnoreCase("soundtrack")) {
			item.put(SongAttributes.ALBUM_TYPE, SOUNDTRACK);
			return;
		}
		item.put(SongAttributes.ALBUM_TYPE, DEFAULT_ALBUM_TYPE);

	}

	private void guessAlbumTypeFromAlbumTitle(ObservableItem song) {
		String album = song.getString(SongAttributes.ALBUM).toLowerCase();
		if (album.equals(""))
			return;
		for (Iterator it = albumTypesMap.keySet().iterator(); it.hasNext();) {
			String albumType = (String) it.next();
			if (checkAlbumType(album, albumType))
				song.put(SongAttributes.ALBUM_TYPE, albumTypesMap.getProperty(albumType));
		}
	}

	private boolean checkAlbumType(String album, String albumType) {
		int index = album.indexOf(albumType.toLowerCase());
		if (index == -1)
			return false;
		if (!isAcceptableAlbumTypeString(album, index, albumType.length()))
			return false;

		return true;
	}

	private boolean isAcceptableAlbumTypeString(String album, int index, int range) {

		Pattern noWordPattern = Pattern.compile("\\W");
		Pattern blankPattern = Pattern.compile("\\p{Blank}");
		Matcher matcher;
		CharSequence valueToCheck;

		if (index > 0) {
			valueToCheck = album.subSequence(index - 1, index);
			matcher = noWordPattern.matcher(valueToCheck);
			if (!matcher.matches())
				return false;
			// matcher = blankPattern.matcher(valueToCheck);
			// if (!matcher.matches())
			// return false;
		}

		if (index + range + 1 < album.length()) {
			valueToCheck = album.subSequence(index + range, index + range + 1);
			matcher = noWordPattern.matcher(valueToCheck);
			if (!matcher.matches())
				return false;
			// matcher = blankPattern.matcher(valueToCheck);
			// if (!matcher.matches())
			// return false;
		}
		return true;

	}

	public void fillCustomFields(File file) {
		super.fillCustomFields(file);
		item.put(SongAttributes.DISC, "");
		item.put(SongAttributes.ALBUM_TYPE, "");
	}

	public void guessMissingData(File file) {
		guesser.setFile(file);

		if (item.getString(SongAttributes.TITLE).equals(""))
			item.put(SongAttributes.TITLE, guesser.guessTitle());

		if (item.getString(SongAttributes.ARTIST).equals(""))
			item.put(SongAttributes.ARTIST, guesser.guessArtist());

		if (item.getString(SongAttributes.YEAR).equals(""))
			item.put(SongAttributes.YEAR, guesser.guessYear(file));

		if (item.getString(SongAttributes.TRACK_NUMBER).equals("")
				|| item.getString(SongAttributes.TRACK_NUMBER).equals("0"))
			item.put(SongAttributes.TRACK_NUMBER, guesser.guessTrack(file));

		if (item.getString(SongAttributes.ALBUM).equals(""))
			item.put(SongAttributes.ALBUM, guesser.guessAlbum());
	}

}