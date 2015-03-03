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
import java.io.FileNotFoundException;
import java.io.IOException;

import net.della.mplatform.core.background.TaskAdapter;
import net.della.mplatform.core.background.swing.CollectionBackgroundTask;
import net.della.mplatform.core.datatypes.FileItem;
import net.della.mplatform.core.datatypes.Item;
import net.mcube.util.ZipCreator;

import org.apache.commons.logging.LogFactory;


public class CreateZipTask extends CollectionBackgroundTask {

	private String zipName;

	private ZipCreator zipCreator;

	/**
	 * 
	 * @param zipName:
	 *            name without extension
	 */
	public CreateZipTask(String filename) {
		this.zipName = filename + ".zip";
		zipCreator = new ZipCreator(zipName);
		addListener(new TaskAdapter() {

			public void onTerminate() {
				zipCreator.close();
			}

		});
	}

	public File getZip() {
		return new File(zipName);
	}

	protected void applyTo(Object singleElement) {
		try {
			Item item = (Item) singleElement;
			FileItem fileItem = (FileItem) item;
			String filename = fileItem.getPath();
			LogFactory.getLog(this.getClass()).debug("adding " + filename);
			zipCreator.addToZip(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
