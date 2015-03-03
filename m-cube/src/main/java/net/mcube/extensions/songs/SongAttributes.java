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

package net.mcube.extensions.songs;

import net.della.mplatform.application.datatypes.FileItemAttributes;

/*
 * Created on 26-mar-2003
 *
 */
/**
 * @author Daniele
 */
public interface SongAttributes extends FileItemAttributes {		
	
	String ARTIST = "Artist";
	String TITLE = "Title";
	String ALBUM = "Album";
	String GENRE = "Genre";
	String YEAR = "Year";
	String TRACK_NUMBER = "Track #";
	//String TRACK_NO_COMPLETE = "Track # complete";
	String COMMENT = "Comment";
	String COMPOSER = "Composer";
	String ORIGINAL_ARTIST = "Original Artist";
	String ENCODED_BY = "Encoded by";
	String TOTAL_TRACKS = "Total Tracks";
		
	String DISC = "Disc";
	String ALBUM_TYPE = "Album Type";
	
	/**
	 * Length in seconds
	 */
	String LENGTH = "Length (sec)";
	/**
	 * Length in minutes:seconds 
	 */
	String LENGTH_STRING = "Length";
	/**
	 * 
	 * @deprecated
	 */
	String BIT_RATE = "Bit Rate";
	String USEFUL_ARTIST = "Complete Artist";
	/**
	 * 
	 * @deprecated
	 */
    String ALL_ARTISTS = "All Artists";
	String ALBUM_COVER_FRONT_PATH = "Album Cover - Front";
	String ALBUM_COVER_FRONT = "Album Cover - Front - Image";
	String ALBUM_COVER_FRONT_THUMBNAIL_PATH = "Album Cover - Front - Thumbnail - Path";
	String ALBUM_COVER_FRONT_THUMBNAIL = "Album Cover - Front - Thumbnail";
}
