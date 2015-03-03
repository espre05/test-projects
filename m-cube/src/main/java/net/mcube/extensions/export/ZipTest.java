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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;

import net.della.mplatform.application.datatypes.FileItem;
import net.della.mplatform.application.datatypes.FileItemAttributes;
import net.della.mplatform.background.TaskAdapter;
import net.della.stuff.generic.file.FileUtils;



public class ZipTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new ZipTest().start();		
	}

	private void start() throws FileNotFoundException, IOException {
		CreateZipTask task = new CreateZipTask("pippo");
		task.setName("creating zip");
		Collection items = new ArrayList();
		String basePath = FileUtils.getCurrentFolderAbsolutePath()
				+ "/test/resources/mp3/archive/The White Stripes/Elephant/";
		createAndAddNewItem(items, basePath + "The White Stripes - Elephant - 01 - Seven Nation Army.mp3");
		createAndAddNewItem(items, basePath + "The White Stripes - Elephant - 02 - Black Math.mp3");
		task.setCollection(items);
		Executors.newSingleThreadExecutor().execute(task);
		task.addListener(new TaskAdapter() {
		
			public void onTerminate() {		
				System.exit(0);
			}		
		});		
	}

	private void createAndAddNewItem(Collection items, String filename) {
		FileItem item = new FileItem();
		item.put(FileItemAttributes.PATH, filename);
		items.add(item);
	}

}
