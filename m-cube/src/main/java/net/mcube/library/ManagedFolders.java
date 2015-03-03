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
import java.util.*;

import net.della.stuff.generic.loader.TextLoader;



/**
 * @author della
 * 
 */
class ManagedFolders {

	private List managedFolders;

	private final String dbFilePath;

	private TextLoader textLoader;

	ManagedFolders(String dbFilePath, TextLoader loader) {
		managedFolders = new ArrayList();
		this.dbFilePath = dbFilePath;
		textLoader = loader;
	}

	void load() {
		managedFolders = textLoader.loadStringList(new File(dbFilePath));
	}

	void add(String folder) {
//		String formattedNewFolder = FileUtils.normalizeFilePath(folder);
		String formattedNewFolder = folder.replace('\\', '/');
		if (!managedFolders.contains(formattedNewFolder))
			managedFolders.add(formattedNewFolder);
	}

	void addAll(List list) {
		for (Iterator it = list.iterator(); it.hasNext();) {
			String folder = "";
			try {
				folder = (String) it.next();
				add(folder);
			} catch (ClassCastException e) {
				System.out.println("Folders list must contains String object, not File.");
				System.out.println("Folder " + folder + "will not be added in ManagedFolders");
				e.printStackTrace();
			}
		}
		// managedFolders.addAll(list);
	}

	boolean contains(String folder) {
		return managedFolders.contains(folder);
	}

	void save() {
//		ListUtils.print(managedFolders);
		textLoader.saveStringList(managedFolders, new File(dbFilePath));

	}

	int size() {
		return managedFolders.size();
	}

	public List getPaths() {
		return managedFolders;
	}

	public void removeAll(Collection foldersToRemove) {
		managedFolders.removeAll(foldersToRemove);
	}

}