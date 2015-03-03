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

/**
 * 
 */
package net.mcube.library.operations;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import net.della.stuff.generic.file.FileUtils;
import net.della.stuff.generic.file.InputFileReader;



class Tracklist extends ArrayList {

	String album;

	String artist;

	String year;

	// private Collection titles;

	public void loadFromFile(File file) {
		if (!FileUtils.getExtension(file).equals("txt"))
			return;
		InputFileReader inputFileReader = new InputFileReader() {

			public Object parseLine(String line) {
				return line;
			}
		};
		this.addAll(inputFileReader.load(file.getAbsolutePath()));
		Properties listProps = new Properties();
		try {
			listProps.load(new FileInputStream(file));
			album = listProps.getProperty("album");
			artist = listProps.getProperty("artist");
			year = listProps.getProperty("year");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAlbum() {
		if (album == null)
			return "";
		return album;
	}

	public String getArtist() {
		if (artist == null)
			return "";
		return artist;
	}

	public String getYear() {
		if (year == null)
			return "";
		return year;
	}
}