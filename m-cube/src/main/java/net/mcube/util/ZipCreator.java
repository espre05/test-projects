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

package net.mcube.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCreator {

	private ZipOutputStream out;
	private final byte[] bufs;

	public ZipCreator(String zipName) {
		out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipName));
//			out.setMethod(ZipOutputStream.STORED);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufs = new byte[200000];
	}

	public void addToZip(String filename) throws FileNotFoundException, IOException {
		File file = new File(filename);
		FileInputStream in = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(in);
		ZipEntry zipEntry = new ZipEntry(filename);
//		zipEntry.setMethod(ZipOutputStream.STORED);
//		zipEntry.setSize(file.length());
//		CRC32 crc = new CRC32();
//		crc.
//		zipEntry.setCrc(crc.getValue());
		out.putNextEntry(zipEntry);
	
		int len;
		while ((len = bis.read(bufs)) > 0) {
			out.write(bufs, 0, len);
		}
	
		out.closeEntry();
//		in.close();
		bis.close();
	}

	public void close() {
		try {
			out.close();
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}

}
