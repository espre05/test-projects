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
package net.mcube.extensions.export;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import net.della.stuff.generic.file.FileUtils;



/**
 * @author Daniele
 *
 */
public class PlaylistExporter {
	
	public static void main(String[] args) {
		PlaylistExporter exporter = new PlaylistExporter();
		exporter.export("d:/cd/playlist.m3u");
	}

	/**
	 * @param string
	 */
	private void export(String playlistPath) {
		Playlist playlist = new Playlist();
		File playlistFile = new File(playlistPath);		
		String fileName = FileUtils.fileNameWithNoExtension(playlistFile);
		File newFolder = new File(playlistFile.getParent(), fileName);
		newFolder.mkdir();
		try {
			playlist.loadFromFile(new File(playlistPath), Playlist.WINAMP_FORMAT);
			for (Iterator it = playlist.iterator(); it.hasNext();) {
				String entry = (String) it.next();
				File entryFile = new File(entry);				
				FileUtils.fileNameWithNoExtension(entryFile);
				FileUtils.copy(entryFile, new File(newFolder, entryFile.getName()));
			}
		} catch (PlaylistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
