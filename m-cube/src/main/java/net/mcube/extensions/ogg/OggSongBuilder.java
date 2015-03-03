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
package net.mcube.extensions.ogg;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.mcube.extensions.songs.DefaultSongBuilder;
import net.mcube.extensions.songs.SongAttributes;

import org.apache.commons.logging.LogFactory;

/**
 * @author Daniele
 * 
 */
public class OggSongBuilder extends DefaultSongBuilder {

    public void extractSpecificFileInfo(File file) {
        try {

            OggFile oggFile = new OggFile(file.getPath());

            item.put(SongAttributes.ARTIST, oggFile.getArtist().trim());
            item.put(SongAttributes.TITLE, oggFile.getTitle().trim());
            item.put(SongAttributes.ALBUM, oggFile.getAlbum().trim());

            String genre = oggFile.getGenre().trim();
            genre = extractGenre(genre);
            item.put(SongAttributes.GENRE, genre);

            // year
            String year = oggFile.getYear().trim();
            Pattern yearPattern = Pattern.compile("\\d{4}");
            Matcher m = yearPattern.matcher(year);
            if (m.matches())
                item.put(SongAttributes.YEAR, year);
            else
                item.put(SongAttributes.YEAR, "");

            // track #
            String trackTag = oggFile.getTrackString().trim();
            String track = extractTrackNumber(trackTag).trim();
            Pattern trackPattern = Pattern.compile("\\d{1,2}");
            m = trackPattern.matcher(track);
            if (m.matches())
                item.put(SongAttributes.TRACK_NUMBER, track);
            else
                item.put(SongAttributes.TRACK_NUMBER, "");

            String numberOfTracks = extractNumberOfTracks(trackTag);
            item.put(SongAttributes.TOTAL_TRACKS, numberOfTracks.trim());

            item.put(SongAttributes.COMMENT, oggFile.getComment().trim());
            item.put(SongAttributes.COMPOSER, oggFile.getComposer().trim());
            // song.setData(SongAttributes.ORIGINAL_ARTIST,
            // mp3.getOriginalArtist().trim());
            item.put(SongAttributes.ENCODED_BY, oggFile.getEncodedBy().trim());

            long playingTime = oggFile.getPlayingTime();
            item.put(SongAttributes.LENGTH, playingTime + "");
            item.put(SongAttributes.LENGTH_STRING, oggFile.getPlayingTimeString());
//            item.setData(SongAttributes.BIT_RATE, oggFile.getBitRate() + "");
            item.put(SongAttributes.DISC, oggFile.getDisc() + "");

		} catch (Exception e) {
			LogFactory.getLog(this.getClass()).info(
					"Impossibile leggere le informazioni dal tag per il file "
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
        if (rowGenre.indexOf("(") != -1) {
            String s = rowGenre.substring(1, 3);
            if (s.indexOf(")") != -1)
                s = s.substring(0, 1);
            int id = (new Integer(s)).intValue();
            genre = NullsoftID3GenreTable.getGenre(id);
        }
        return genre;
    }

    public void createItem() {
        item = new OggSong();
    }

}