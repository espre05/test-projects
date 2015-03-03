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

package net.mcube.datatypes;

import java.io.File;

import net.della.mplatform.application.datatypes.FileItem;
import net.della.mplatform.application.datatypes.FileItemAttributes;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.stuff.generic.file.FileUtils;



public class DefaultItemBuilder {

	protected ObservableItem item;

	public void createItem() {
		item = new FileItem();
	}

	public FileItem getItem() {
		return (FileItem) item;
	}

	public void buildPath(File file, File topFolder, String virtualPath) {
		if (!file.isAbsolute() || !topFolder.isAbsolute())
			throw new IllegalArgumentException();
		if (!FileUtils.isInASubfolder(file, topFolder))
			throw new IllegalArgumentException();

		String topFolderPath = topFolder.getAbsolutePath();
		topFolderPath = topFolderPath.replace('\\', '/');

		String path = file.getPath();
		path = path.replace('\\', '/');
		String relativePath = path.replaceAll(topFolderPath, "");

		item.put(FileItemAttributes.VIRTUAL_PATH, virtualPath);
		FileItem fileItem = (FileItem) item;
		fileItem.setRelativePath(relativePath);
//		item.putData(ItemAttributes.PATH, topFolderPath + relativePath);

	}

	public void fillCustomFields(File file) {
	}

	public void extractSpecificFileInfo(File file) {
		
	}

	public void guessMissingData(File file) {
		
	}

	public void extractInfoFromGatheredData() {
	
	}

	public void fixSomeProblems() {
	
	}

}
