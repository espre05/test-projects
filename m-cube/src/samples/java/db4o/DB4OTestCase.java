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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.ExtObjectContainer;
import com.db4o.query.Query;

public class DB4OTestCase extends TestCase {
	
	private ObjectContainer db;
	private List addedBeans;
	private final String filename = "db4otest.yap";

	protected void setUp() throws Exception {	
		super.setUp();
		addedBeans = new ArrayList();
		openDB();		
	}

	private void openDB() {
		db = Db4o.openFile(filename);
	}
	
	protected void tearDown() throws Exception {	
		super.tearDown();
		for (Iterator it = addedBeans.iterator(); it.hasNext();) {
			Object o = it.next();
			db.delete(o);
		}
		closeDB();
	}
	
	protected final void addToDB(Object bean) {
		db.set(bean);
		addedBeans.add(bean);
	}

	protected final ObjectSet<Object> getFromDB(Object bean) {
		return db.get(bean);
	}

	protected final void deleteFromDB(Object deleteBean) {
		db.delete(deleteBean);
		addedBeans.remove(deleteBean);
	}

	protected Query newQuery() {
		return db.query();
	}

	protected Query newQuery(Class className) {
		Query query = newQuery();
		query.constrain(className);
		return query;
	}

	protected void restartDB() throws Exception {
		closeDB();
		openDB();
	}
		

	protected void commit() {
		db.commit();
	}

	private void closeDB() {
		db.close();
	}

}
