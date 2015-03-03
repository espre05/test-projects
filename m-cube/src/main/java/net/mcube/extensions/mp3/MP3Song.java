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

import java.io.FileNotFoundException;
import java.io.IOException;

import net.della.stuff.generic.util.StringFormatter;
import net.mcube.extensions.songs.DefaultSong;
import net.mcube.extensions.songs.SongAttributes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Daniele
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class MP3Song extends DefaultSong {

	public void removeID3v1() {
		try {
			MP3File mp3 = new MP3File(getPath());
			mp3.removeTags(MP3File.ID3);
			mp3.writeTags();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeTag() {
		try {
			MP3File mp3 = new MP3File(getPath());
			mp3.removeTags(MP3File.BO);
			mp3.writeTags();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateID3Tag(boolean ID3v2Only) {
		String path = "";
		Log log = LogFactory.getLog(this.getClass());
		try {
			MP3 mp3 = new MP3(getPath());
			if (ID3v2Only)
				mp3.setTaggingType(MP3File.ID3MP3);
			path = mp3.getPath();
			mp3.setTaggingType(MP3File.ID3MP3);
			updateID3TagFromData(mp3);
		} catch (ID3v2FormatException e) {
			log.info("unable to create valid rapresentation of this file " + path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoMPEGFramesException e) {
			log.info("unable to create valid rapresentation of this file: " + path);
		} catch (CorruptHeaderException e) {
			log.info("header is corrupted for file: " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// private void updateID3TagFromData(MP3File mp3) {
	// mp3.setAllProperties(getAllData());
	// }

	private void updateID3TagFromData(MP3 mp3) {
		boolean modified = false;
		Log log = LogFactory.getLog(this.getClass());
		try {
			String newValue = getString(SongAttributes.ARTIST);
			if (!mp3.getArtist().equals(newValue)) {
				mp3.setArtist(newValue);
				modified = true;
			}
			newValue = getString(SongAttributes.TITLE);
			if (!mp3.getTitle().equals(newValue)) {
				mp3.setTitle(newValue);
				modified = true;
			}
			newValue = getString(SongAttributes.ALBUM);
			if (!mp3.getAlbum().equals(newValue)) {
				mp3.setAlbum(newValue);
				modified = true;
			}
			newValue = getString(SongAttributes.GENRE);
			if (!mp3.getGenre().equals(newValue)) {
				mp3.setGenre(newValue);
				modified = true;
			}
			newValue = getString(SongAttributes.YEAR);
			if (!mp3.getYear().equals(newValue)) {
				mp3.setYear(newValue);
				modified = true;
			}
			newValue = getString(SongAttributes.TRACK_NUMBER);
			if (!mp3.getTrackString().equals(newValue)) {
				mp3.setTrack(newValue);
				modified = true;
			}
			newValue = getString(SongAttributes.COMMENT);
			if (!mp3.getComment().equals(newValue)) {
				mp3.setComment(newValue);
				modified = true;
			}

			if (mp3.getTaggingType() != MP3File.ID3MP3) {
				newValue = getString(SongAttributes.ENCODED_BY);
				if (!mp3.getEncodedBy().equals(newValue)) {
					mp3.setEncodedBy(newValue);
					modified = true;
				}
				newValue = getString(SongAttributes.ORIGINAL_ARTIST);
				if (!mp3.getOriginalArtist().equals(newValue)) {
					mp3.setOriginalArtist(newValue);
					modified = true;
				}
			}
			if (modified)
				mp3.writeTags();
		} catch (Exception e) {
			log.info("unable to write id3 tag for file, I'll try the hard way... ");
			recreateTag(mp3);
		}
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (ID3v2FormatException e) {
		// log.info("unable to write id3 tag for file: ");
		// }
	}

	private void recreateTag(MP3 mp3) {
		Log log = LogFactory.getLog(this.getClass());
		try {
			mp3.removeTags(MP3File.BOMP3);
			mp3.writeTags();
			log.info("...done!");
		} catch (Exception e) {
			log.info("ok, no chance to change id3 tag on this file :( " + mp3.getPath());
		}
	}

	public void formatData() {
		super.formatData();
		updateID3Tag(true);
	}

	public void formatData(StringFormatter sf) {
		super.formatData(sf);
		updateID3Tag(true);
	}
	
	public String getBitRate() {
		net.mcube.extensions.mp3.MP3 file;
		try {
			file = new net.mcube.extensions.mp3.MP3(getPath());
			return file.getBitRate();
		} catch (FileTagReadException e) {
			e.printStackTrace();
		}
		return "";
	}

	/*
	 * void renameFileFromTag() { try { MP3File mp3 = new MP3File(getPath());
	 * mp3 = MP3FileUtil.renameFromTag(mp3); setData(SongAttributes.PATH,
	 * mp3.getPath()); } catch (Exception exc) { exc.printStackTrace(); } }
	 */

}