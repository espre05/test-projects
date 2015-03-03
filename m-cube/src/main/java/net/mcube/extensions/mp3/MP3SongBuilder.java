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
package net.mcube.extensions.mp3;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.della.stuff.generic.util.MathUtil;
import net.mcube.extensions.songs.DefaultSongBuilder;
import net.mcube.extensions.songs.SongAttributes;

import org.apache.commons.logging.LogFactory;


/**
 * @author Daniele
 * 
 */
public class MP3SongBuilder extends DefaultSongBuilder {

	public void createItem() {
		item = new MP3Song();
	}

	public void extractSpecificFileInfo(File file) {
		try {

			MP3 mp3 = new MP3(file.getPath());

			item.put(SongAttributes.ARTIST, mp3.getArtist().trim());
			item.put(SongAttributes.TITLE, mp3.getTitle().trim());
			item.put(SongAttributes.ALBUM, mp3.getAlbum().trim());

			String genre = mp3.getGenre().trim();
			genre = extractGenre(genre);
			item.put(SongAttributes.GENRE, genre);

			// year
			String year = mp3.getYear().trim();
			Pattern yearPattern = Pattern.compile("\\d{4}");
			Matcher m = yearPattern.matcher(year);
			if (m.matches())
				item.put(SongAttributes.YEAR, year);
			else
				item.put(SongAttributes.YEAR, "");

			// track #
			String trackTag = mp3.getTrackString().trim();
			String track = extractTrackNumber(trackTag).trim();
			Pattern trackPattern = Pattern.compile("\\d{1,2}");
			m = trackPattern.matcher(track);
			if (m.matches())
				item.put(SongAttributes.TRACK_NUMBER, track);
			else
				item.put(SongAttributes.TRACK_NUMBER, "");

			String numberOfTracks = extractNumberOfTracks(trackTag);
			item.put(SongAttributes.TOTAL_TRACKS, numberOfTracks.trim());

			item.put(SongAttributes.COMMENT, mp3.getComment().trim());
			item.put(SongAttributes.COMPOSER, mp3.getComposer().trim());
			// song.setData(SongAttributes.ORIGINAL_ARTIST,
			// mp3.getOriginalArtist().trim());
			item.put(SongAttributes.ENCODED_BY, mp3.getEncodedBy().trim());

			long playingTime = mp3.getPlayingTime();
			item.put(SongAttributes.LENGTH, playingTime + "");
			item.put(SongAttributes.LENGTH_STRING, mp3.getPlayingTimeString());
//			item.setData(SongAttributes.BIT_RATE, mp3.getBitRate() + "");
			String discTag = mp3.getDisc() + "";
			discTag = extractTrackNumber(discTag);
			item.put(SongAttributes.DISC, discTag);

		} catch (FileTagReadException e) {
			LogFactory.getLog(this.getClass()).info(
					"Impossibile leggere le informazioni dal tag id3 per il file "
							+ file.getAbsolutePath());
		}
	}

	private static String extractTrackNumber(String track) {
		String str = new String(track);
		if (str.indexOf("/") != -1)
			str = str.substring(0, str.indexOf("/"));
		return str;
	}

	private static String extractNumberOfTracks(String track) {
		String str = new String(track);
		if (str.indexOf("/") != -1)
			str = str.substring(str.indexOf("/") + 1, str.length());
		return str;
	}

	private static String extractGenre(String rowGenre) {
		String genre = new String(rowGenre);
		try {
			if (rowGenre.indexOf("(") != -1) {
				String s = rowGenre.substring(1, 3);
				if (s.indexOf(")") != -1)
					s = s.substring(0, 1);
				if (MathUtil.isInteger(s)) {
					int id = (new Integer(s)).intValue();
					genre = NullsoftID3GenreTable.getGenre(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genre;
	}
}