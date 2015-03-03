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

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.della.mplatform.core.datatypes.FileItem;
import net.della.mplatform.core.datatypes.Item;

import org.apache.commons.logging.LogFactory;


public class CreateZipRunnable implements Runnable {

	private String zipName;

	private ZipOutputStream out;

	private Collection col;

	/**
	 * 
	 * @param zipName: name without extension
	 */
	public CreateZipRunnable(String filename) {
		this.zipName = filename + ".zip";
		out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipName));
//			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public File getZip() {
		return new File(zipName);
	}

	protected void applyTo(Object singleElement) {
		try {
			Item item = (Item) singleElement;
			FileItem fileItem = (FileItem) item;
			String filename = fileItem.getPath();
			LogFactory.getLog(this.getClass()).info("adding " + filename);
			addToZip(filename, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void addToZip(String filename, ZipOutputStream out) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(filename);
		byte[] buf = new byte[1024];
		out.putNextEntry(new ZipEntry(filename));

		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}

		out.closeEntry();
		in.close();
	}

	public void run() {
		System.out.println("inizio");
		for (Iterator it = col.iterator(); it.hasNext();) {
			applyTo(it.next());			
		}
		System.out.println("fine");
	}

	public void setCol(Collection col) {
		this.col = col;
	}

}
