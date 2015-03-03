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

package db4o;

import java.io.File;

import net.mcube.library.Database;

import com.db4o.ObjectSet;
import com.sourcesense.stuff.file.FileUtils;

import della.application.datatypes.FileItem;
import della.application.datatypes.Item;

public class DB4OFileItemTest extends DB4OTestCase {

	public void testResultSetDifferentValuesFileItem() throws Exception {
		String folder = "folder";
		String song = "song";

		createFileItem(song, 4);
		createFileItem(folder, 3);

		FileItem queryBean = new FileItem();
		queryBean.setType(folder);
		ObjectSet<Object> resultSet = getFromDB(queryBean);
		assertEquals(3, resultSet.size());

		queryBean = new FileItem();
		queryBean.setType(song);
		resultSet = getFromDB(queryBean);
		assertEquals(4, resultSet.size());

		queryBean = new FileItem();
		queryBean.setType(song);
		queryBean.setRelativePath(2 + "");
		resultSet = getFromDB(queryBean);
		assertEquals(1, resultSet.size());
	}

	private void createFileItem(String song, int n) {
		for (int i = 0; i < n; i++) {
			FileItem bean = new FileItem();
			bean.setType(song);
			bean.setRelativePath(i + "");
			addToDB(bean);
		}
	}

	public void testRealDB() throws Exception {
		String databaseFolderAbsolutPath = FileUtils.getCurrentFolderAbsolutePath()
				+ File.separator + "database";
		Database db = new Database(databaseFolderAbsolutPath, "items");
		Item song = new FileItem();
		ObjectSet<Object> resultSet = db.getFromDB(song);
		System.out.println(resultSet.size());
	}

}
