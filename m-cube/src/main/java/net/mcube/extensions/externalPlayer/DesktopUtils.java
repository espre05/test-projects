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

package net.mcube.extensions.externalPlayer;

import java.util.HashMap;
import java.util.Map;

public class DesktopUtils {

	static Map mimeTable;

	static String jarFilename;

	static {
		mimeTable = new HashMap();
		mimeTable.put("mp3", "audio/x-mp3");
		mimeTable.put("wav", "audio/x-wav");
		mimeTable.put("wma", "audio/x-wma");
		mimeTable.put("mpc", "audio/x-mpc");
		mimeTable.put("ape", "audio/x-ape");
		mimeTable.put("ogg", "application/ogg");
	}

	static String getCommandWin32(String ext, String verb) {
		AssociationService service;
		try {
			service = DesktopUtils.loadAssociationService();
		} catch (LibraryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		Association fileExtensionAssociation = null;
		try {
			fileExtensionAssociation = service.getFileExtensionAssociation(ext);
		} catch (UnsatisfiedLinkError e) {
			// e.printStackTrace();
		} catch (NoClassDefFoundError e) {
			// e.printStackTrace();
		}
		if (fileExtensionAssociation == null)
			return "";
		org.jdesktop.jdic.filetypes.Action action = fileExtensionAssociation.getActionByVerb(verb);
		if (action == null)
			return "";
		return action.getCommand();
	}

	static String getCommandLinux(String ext, String verb) {
		AssociationService service;
		try {
			service = DesktopUtils.loadAssociationService();
		} catch (LibraryNotFoundException e) {
			e.printStackTrace();
			return "";
		}
		String mimeType = (String) mimeTable.get(ext);
		Association fileExtensionAssociation = null;		
		try {
			fileExtensionAssociation = service.getMimeTypeAssociation(mimeType);			
		} catch (Exception e) {
		}
		if (fileExtensionAssociation == null)
			return "";

		org.jdesktop.jdic.filetypes.Action action = fileExtensionAssociation.getActionByVerb(verb);
		if (action == null)
			return "";
		return action.getCommand();
	}

	static AssociationService loadAssociationService() throws LibraryNotFoundException {

		String className = "org.jdesktop.jdic.filetypes.AssociationService";
		Class cls = null;
		try {

			URL[] urlsToLoadFrom = new URL[] { new URL("file:" + jarFilename) };
			ClassLoader cl = new JarClassLoader(urlsToLoadFrom);

			cls = cl.loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return (AssociationService) cls.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new LibraryNotFoundException("cannot find " + className + "in " + jarFilename);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new LibraryNotFoundException("cannot find " + className + "in " + jarFilename);
		}

	}

	public static String getCommand(String verb, String ext) {
		String command = "";
		String os = System.getProperty("os.name");
		if (os.indexOf("Windows") != -1)
			command = getCommandWin32(ext, verb);
		if (os.indexOf("Linux") != -1)
			command = getCommandLinux(ext, verb);
		if (os.indexOf("Mac") != -1)
			command = getCommandWin32(ext, verb);
		return command;
	}

}
