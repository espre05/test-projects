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

package net.mcube.library;

import java.io.File;

import junit.framework.TestCase;
import net.della.mplatform.application.persistence.BasicLibrary;

public class LibraryImplTest extends TestCase {

	private LibraryImpl library;
	private String testHomePath;
	private String databaseFolderPath;

	protected void setUp() throws Exception {
		super.setUp();
//		String basePath = "/test/resources/mp3/";
//		library = LibraryImpl.newLibrary("");
//		String archive = "archive/";
//		String elephantFolder = "/The White Stripes/Elephant/";
//		String elephant = elephantFolder + "The White Stripes - Elephant";
//		commonPath = basePath + archive + elephant;
		testHomePath = "test/";
		databaseFolderPath = testHomePath + "database";

	}

	protected void tearDown() throws Exception {
		new File(databaseFolderPath).delete();
	}

	public void testSetLibraryHome() {
		File databaseFolder = new File(databaseFolderPath);
		assertFalse(databaseFolder.exists());
		library = (LibraryImpl) BasicLibrary.newLibrary(testHomePath, "");		
		assertTrue(databaseFolder.exists());
		assertTrue(library.countItems() == 0);
	}


}
