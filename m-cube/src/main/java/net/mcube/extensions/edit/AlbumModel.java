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

package net.mcube.extensions.edit;

import java.util.HashMap;
import java.util.Map;

import net.mcube.extensions.songs.SongAttributes;

public class AlbumModel extends MultiPurposeModel {

	private String artist;

	private boolean artistEditEnabled;

	private String album;

	private boolean albumEditEnabled;

	private String genre;

	private boolean genreEditEnabled;

	private String year;

	private boolean yearEditEnabled;

	private String albumType;

	private boolean albumTypeEditEnabled;

	private boolean updateFileTag;

	private final Map props;

	public AlbumModel() {
		props = new HashMap();
	}

	public String getArtist() {
		return getStringProperty(SongAttributes.ARTIST);
	}

	public void setArtist(String artist) {
		putProperty(SongAttributes.ARTIST, getArtist(), artist);
	}

	public String getAlbumType() {
		return getStringProperty(SongAttributes.ALBUM_TYPE);
	}

	public void setAlbumType(String albumType) {
		putProperty(SongAttributes.ALBUM_TYPE, getAlbumType(), albumType);
	}

	public String getGenre() {
		return getStringProperty(SongAttributes.GENRE);
	}

	public void setGenre(String genre) {
		putProperty(SongAttributes.GENRE, getGenre(), genre);
	}

	public String getAlbum() {
		return getStringProperty(SongAttributes.ALBUM);
	}

	public void setAlbum(String album) {
		putProperty(SongAttributes.ALBUM, getAlbum(), album);
	}

	public String getYear() {
		return getStringProperty(SongAttributes.YEAR);
	}

	public void setYear(String year) {
		putProperty(SongAttributes.YEAR, getYear(), year);
	}

	public Boolean getUpdateFileTag() {
		return (Boolean) getProperty(AlbumPresentationModel.UPDATE_FILE_TAG);
	}

	public void setUpdateFileTag(Boolean updateTag) {
		putProperty(AlbumPresentationModel.UPDATE_FILE_TAG, getUpdateFileTag(), updateTag);
	}

	public boolean isAlbumEditEnabled() {
		return albumEditEnabled;
	}

	public void setAlbumEditEnabled(boolean albumEditEnabled) {
		this.albumEditEnabled = albumEditEnabled;
	}

	public boolean isAlbumTypeEditEnabled() {
		return albumTypeEditEnabled;
	}

	public void setAlbumTypeEditEnabled(boolean albumTypeEditEnabled) {
		this.albumTypeEditEnabled = albumTypeEditEnabled;
	}

	public boolean isArtistEditEnabled() {
		return artistEditEnabled;
	}

	public void setArtistEditEnabled(boolean artistEditEnabled) {
		this.artistEditEnabled = artistEditEnabled;
	}

	public boolean isGenreEditEnabled() {
		return genreEditEnabled;
	}

	public void setGenreEditEnabled(boolean genreEditEnabled) {
		this.genreEditEnabled = genreEditEnabled;
	}

	public boolean isYearEditEnabled() {
		return yearEditEnabled;
	}

	public void setYearEditEnabled(boolean yearEditEnabled) {
		this.yearEditEnabled = yearEditEnabled;
	}

}
