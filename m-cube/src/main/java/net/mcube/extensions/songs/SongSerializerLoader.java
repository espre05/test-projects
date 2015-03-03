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

import java.util.Map;

import net.della.mplatform.application.datatypes.FileItemAttributes;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.mcube.library.TopFolders;
import old.DataProcesserSerializerLoader;

public class SongSerializerLoader extends DataProcesserSerializerLoader {

	private final TopFolders topFolders;

	public SongSerializerLoader(TopFolders topFolders, String filename) {
		super(filename);
		this.topFolders = topFolders;
	}

	protected void processDataOnSave(Map props) {
		String path = (String) props.get(FileItemAttributes.PATH);
		String topFolderPath = topFolders.getParentTopFolder(path);
		String vPath = topFolders.getID(topFolderPath);
		String rPath = path.replaceAll(topFolderPath, "");
		props.put(FileItemAttributes.VIRTUAL_PATH, vPath);
		props.put(FileItemAttributes.RELATIVE_PATH, rPath);
		props.remove(FileItemAttributes.PATH);
//		props.remove(SongAttributes.ALBUM_TYPE);
		props.remove(SongAttributes.ALBUM_COVER_FRONT);		
		props.remove(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL);
//		props.remove(SongAttributes.ALBUM_COVER_FRONT_PATH);
//		props.remove(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL_PATH);

	}

	protected void processDataOnLoad(Map props) {
		String vPath = (String) props.get(FileItemAttributes.VIRTUAL_PATH);
		String rPath = (String) props.get(FileItemAttributes.RELATIVE_PATH);
		props.put(FileItemAttributes.PATH, topFolders.getPath(vPath) + rPath);
	}

	protected boolean isToSave(ObservableItem item) {
		return topFolders.isFolderManaged(item.getString(FileItemAttributes.PATH));
	}

}
