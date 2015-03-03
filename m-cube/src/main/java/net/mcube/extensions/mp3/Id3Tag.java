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

package net.mcube.extensions.mp3;

import java.util.Properties;
import java.util.Set;

import net.mcube.extensions.songs.SongAttributes;

public class Id3Tag {

	private static final Properties mappaCorrispondenze = new Properties();

	static void load() {
		mappaCorrispondenze.setProperty(SongAttributes.ARTIST, "author");
		mappaCorrispondenze.setProperty(SongAttributes.ALBUM, "album");
		mappaCorrispondenze.setProperty(SongAttributes.TITLE, "title");
		mappaCorrispondenze.setProperty(SongAttributes.YEAR, "date");
		mappaCorrispondenze.setProperty(SongAttributes.TRACK_NUMBER, "mp3.id3tag.track");
		mappaCorrispondenze.setProperty(SongAttributes.GENRE, "mp3.id3tag.genre");
		mappaCorrispondenze.setProperty(SongAttributes.COMMENT, "comment");
		mappaCorrispondenze.setProperty(SongAttributes.DISC, "mp3.id3tag.disc");
		mappaCorrispondenze.setProperty(SongAttributes.ENCODED_BY, "mp3.id3tag.encoded");
		mappaCorrispondenze.setProperty(SongAttributes.BIT_RATE, "mp3.bitrate.nominal.bps");
		mappaCorrispondenze.setProperty(SongAttributes.COMPOSER, "mp3.id3tag.composer");
		mappaCorrispondenze.setProperty(SongAttributes.LENGTH, "duration");
	}
	
	static String getProperty(String key) {
		return mappaCorrispondenze.getProperty(key);
	}

	public static Set keySet() {
		return mappaCorrispondenze.keySet();
	}

}
