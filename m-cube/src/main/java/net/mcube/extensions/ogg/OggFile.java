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
 * Created on 9-feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.ogg;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.*;

import net.mcube.extensions.mp3.FileTagReadException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class OggFile {

	private Map properties;

	private AudioFileFormat baseFileFormat;

	public OggFile(String path) throws FileTagReadException {

		File file = new File(path);

		baseFileFormat = null;
		AudioFormat baseFormat = null;
		try {
			baseFileFormat = AudioSystem.getAudioFileFormat(file);
		} catch (UnsupportedAudioFileException e) {
			// e.printStackTrace();
			throw new FileTagReadException();
		} catch (IOException e) {
			e.printStackTrace();
			// return;
		}
		baseFormat = baseFileFormat.getFormat();
		// TAudioFileFormat properties
		if (baseFileFormat instanceof TAudioFileFormat) {
			properties = ((TAudioFileFormat) baseFileFormat).properties();
		}
		// TAudioFormat properties
		// if (baseFormat instanceof TAudioFormat) {
		// Map properties = ((TAudioFormat) baseFormat).properties();
		// String key = "bitrate";
		// Integer val = (Integer) properties.get(key);
		// }

	}

	public String getArtist() {
		return getString("author");
	}

	public String getTitle() {
		return getString("title");
	}

	public String getAlbum() {
		return getString("album");
	}

	public String getGenre() {
		return getString("mp3.id3tag.genre");
	}

	public String getYear() {
		return getString("date");
	}

	public String getTrackString() {
		String value = getString("ogg.comment.track");
		if (value.trim().equals("32"))
			return "";
		return value;
	}

	public String getComment() {
		return getString("comment");
	}

	public long getPlayingTime() {
		Long duration = getLong("duration");
		return duration.longValue() / 1000000l;
	}

	public String getPlayingTimeString() {
		Long duration = getLong("duration");
		long durationInSec = duration.longValue() / 1000000l;

		int min = (int) (durationInSec / 60);
		int sec = (int) (durationInSec % 60);
		String leadingZero = "";
		if (sec < 10)
			leadingZero = "0";
		StringBuffer sb = new StringBuffer();
		sb.append(min + ":" + leadingZero + sec);

		return sb.toString();
	}

	public String getBitRate() {
		Integer bitRate = getInteger("ogg.bitrate.nominal.bps");
		return bitRate.toString();
	}

	public String getComposer() {
		return "";
	}

	private Integer getInteger(String key) {
		Integer value = (Integer) properties.get(key);
		if (value == null)
			return new Integer(0);
		return value;
	}

	private Long getLong(String key) {
		Long value = (Long) properties.get(key);
		if (value == null)
			return new Long(0);
		return value;
	}

	private String getString(String key) {
		String value = (String) properties.get(key);
		if (value == null)
			return "";
		return value;
	}

	public void setData(String key, String value) {
		String libSpecificKey = OggTag.getProperty(key);
		put(libSpecificKey, value);
	}

	public void put(String key, String value) {
		properties.put(key, value);
	}

	public void setAllProperties(Properties sourceProperties) {
		Set keySet = OggTag.keySet();
		for (Iterator it = keySet.iterator(); it.hasNext();) {
			String key = (String) it.next();
			String libSpecificKey = OggTag.getProperty(key);
			String newValue = sourceProperties.getProperty(key);
			String oldValue = (String) properties.get(libSpecificKey);
			if (!newValue.equals(oldValue))
				properties.put(libSpecificKey, newValue);
		}
		// baseFileFormat.
	}

	public String getEncodedBy() {
		return getString("ogg.comment.encodedby");
	}

	public String getDisc() {
		return "";
	}

}
