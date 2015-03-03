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
 * Created on 14-mag-2005 
 * 
 */
package net.mcube.library;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.della.stuff.generic.loader.TextLoader;



final class FolderTextLoader extends TextLoader {

	private TopFolders topFolders;

	public FolderTextLoader(TopFolders topFolders) {
		this.topFolders = topFolders;
	}

	public void saveStringList(List list, File saveFile) {
		/*
		 * ogni cartella cerca indexOf per tutti i path di crossfolder, fino a
		 * quello che matcha con quello fai key = topFolders.getPathID
		 * sostituisci il path con la key
		 */
		List newList = new LinkedList();
		for (Iterator it = list.iterator(); it.hasNext();) {
			String folder = (String) it.next();
			if (topFolders.isFolderManaged(folder)) {				
				String topFolderPath = topFolders.getParentTopFolder(folder);
				String id = topFolders.getID(topFolderPath);
				newList.add(folder.replace(topFolderPath, id));
			}
		}
		super.saveStringList(newList, saveFile);
	}

	public List loadStringList(File file) {
		List list = super.loadStringList(file);
		// List realList = new LinkedList();
		List newList = new LinkedList();
		for (Iterator it = list.iterator(); it.hasNext();) {
			String virtualFolder = (String) it.next();
			int slashIndex;
			slashIndex = findSlashIndex(virtualFolder);
			if (slashIndex == -1)
				slashIndex = virtualFolder.length();
			String folderID = virtualFolder.substring(0, slashIndex);
			String realPath = topFolders.getPath(folderID);
			String realFolder = virtualFolder.replace(folderID, realPath);
			newList.add(realFolder);
		}
		return newList;
	}

	private int findSlashIndex(String virtualFolder) {
		int slashIndex;
		int slashIndex1 = virtualFolder.indexOf('/');
		int slashIndex2 = virtualFolder.indexOf('\\');
		if (slashIndex1 != -1)
			slashIndex = slashIndex1;
		else
			slashIndex = slashIndex2;
		return slashIndex;
	}
}