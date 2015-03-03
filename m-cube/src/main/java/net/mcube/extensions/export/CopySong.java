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

package net.mcube.extensions.export;

import java.io.File;

import net.della.mplatform.application.datatypes.ObservableItem;


public class CopySong extends CopyItem {

	public void execute(ObservableItem item, File targetFolder) {
		FileItem fileItem = (FileItem) item;
		File file = new File(fileItem.getPath());
		String targetPath = targetFolder.getAbsolutePath();

		DefaultSong song = (DefaultSong) item;
		String filaname = song.getArtist() + " - " + song.getAlbum() + " - " + song.getTrack() + " - "
				+ song.getTitle() + "." + song.getExtension();
		File newFile = new File(targetPath, filaname);

		FileUtils.copy(file, newFile);
	}

}
