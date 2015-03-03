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

import com.db4o.ObjectSet;

public class DB4OLearningTest extends DB4OTestCase {

	public void testAddVoidBean() throws Exception {
		MyVoidBean bean = new MyVoidBean();
		addToDB(bean);
		ObjectSet result = getFromDB(bean);
		assertEquals(1, result.size());
		assertEquals(bean, result.next());

	}

	public void testAddWrongImplementationBean() throws Exception {
		MyWrongImplementationBean bean = new MyWrongImplementationBean();
		addToDB(bean);
		ObjectSet result = getFromDB(new MyWrongImplementationBean());
		assertEquals(1, result.size());
		assertEquals(bean, result.next());

	}

	public void testAddBean() throws Exception {
		MyBean bean = new MyBean();
		bean.setArtist("anArtist");
		addToDB(bean);
		// be careful: if getArtist returns null, db40 returns *all* entries
		ObjectSet result = getFromDB(new MyBean());
		assertEquals(1, result.size());
		assertEquals(bean, result.next());

		MyBean queryBean = new MyBean();
		queryBean.setArtist("");
		result = getFromDB(queryBean);
		assertEquals(0, result.size());
		assertNotSame(bean, result.next());
	}

	public void testAddMultipleEqualsBean() throws Exception {
		MyBean bean = new MyBean();
		bean.setArtist("anArtist");
		addToDB(bean);
		addToDB(bean);
		ObjectSet result = getFromDB(new MyBean());
		assertEquals(1, result.size());
		assertEquals(bean, result.next());
	}

	public void testAddMultipleDifferentBean() throws Exception {
		MyBean bean1 = new MyBean();
		bean1.setArtist("anArtist1");
		addToDB(bean1);
		MyBean bean2 = new MyBean();
		bean2.setArtist("anArtist2");
		addToDB(bean2);

		MyBean queryBean = new MyBean();
		queryBean.setArtist("anArtist1");
		ObjectSet result = getFromDB(queryBean);
		assertEquals(1, result.size());
		MyBean resultBean = (MyBean) result.next();
		assertEquals(bean1, resultBean);
		assertEquals("anArtist1", resultBean.getArtist());

		queryBean.setArtist("wrongArtist");
		result = getFromDB(queryBean);
		assertEquals(0, result.size());
	}

	public void testDeleteWithDifferentInstance() throws Exception {
		String artistName = "anArtist";
		MyBean bean = new MyBean();
		bean.setArtist(artistName);
		addToDB(bean);
		MyBean deleteBean = new MyBean();
		bean.setArtist(artistName);
		// be carefull: delete must receive the right instance, so we must first
		// take it from DB
		ObjectSet result = getFromDB(deleteBean);
		deleteFromDB(result.next());

		MyBean queryBean = new MyBean();
		queryBean.setArtist(artistName);
		result = getFromDB(queryBean);
		assertEquals(0, result.size());

	}

	public void testQuery() throws Exception {
		MyBean bean = new MyBean();
		String artistName = "anArtist";
		bean.setArtist(artistName);
		String pathName = "aPath";
		bean.setPath(pathName);
		addToDB(bean);

		MyGenericBean queryBean = new MyGenericBean();
		queryBean.setPath(pathName);
		ObjectSet resultSet = getFromDB(queryBean);
		assertEquals(1, resultSet.size());
		MyBean resultBean = (MyBean) resultSet.next();
		assertEquals(artistName, resultBean.getArtist());
	}

	public void testResultSetDifferentValues() throws Exception {
		String artistName = "anArtist";
		String pathName = "aPath";
		createArtistBeans(5, artistName);
		createArtistBeans(3, pathName);

		MyBean queryBean = new MyBean();
		queryBean.setArtist(artistName);
		ObjectSet<Object> resultSet = getFromDB(queryBean);
		assertEquals(5, resultSet.size());

		queryBean = new MyBean();
		queryBean.setArtist(pathName);
		resultSet = getFromDB(queryBean);
		assertEquals(3, resultSet.size());

	}

	public void testResultSetDifferentValuesBeanItem() throws Exception {
		String folder = "folder";
		String song = "song";

		BeanItem bean1 = new BeanItem();
		bean1.setType(folder);
		bean1.setId("id1");
		addToDB(bean1);

		BeanItem bean2 = new BeanItem();
		bean2.setType(song);
		bean2.setId("id2");
		addToDB(bean2);

		BeanItem queryBean = new BeanItem();
		queryBean.setType(folder);
		ObjectSet<Object> resultSet = getFromDB(queryBean);
		assertEquals(1, resultSet.size());

		queryBean = new BeanItem();
		queryBean.setType(song);
		resultSet = getFromDB(queryBean);
		assertEquals(1, resultSet.size());

		queryBean = new BeanItem();
		queryBean.setId("id2");
		resultSet = getFromDB(queryBean);
		BeanItem resultBean1 = (BeanItem) resultSet.next();
		BeanItem resultBean2 = (BeanItem) resultSet.next();
		System.out.println(resultBean1.getId());
		System.out.println(resultBean1.getType());
		// System.out.println(resultBean2.getId());
		// System.out.println(resultBean2.getType());
		assertEquals(1, resultSet.size());
		assertEquals(bean2, resultBean1);
	}
	
	public void testBeanItem() {
		
		int n = 5;
		String song = "song";
		createBeanItem(song, n);
		BeanItem queryBean = new BeanItem();
		queryBean.setId("1");
		ObjectSet<Object> resultSet = getFromDB(queryBean);
		assertEquals(1, resultSet.size());

		queryBean = new BeanItem();
		queryBean.setType(song);
		resultSet = getFromDB(queryBean);
		assertEquals(5, resultSet.size());
	}

	private void createBeanItem(String song, int n) {
		for (int i = 0; i < n; i++) {
			BeanItem bean = new BeanItem();
			bean.setType(song);
			bean.setId(i+"");
			addToDB(bean);
		}
	}

	public void testResultSetDifferentProperties() throws Exception {
		String artistName = "anArtist";
		String pathName = "aPath";
		createArtistBeans(5, artistName);
		createPathBeans(3, pathName);

		MyBean queryBean = new MyBean();
		queryBean.setArtist(artistName);
		ObjectSet<Object> resultSet = getFromDB(queryBean);
		assertEquals(5, resultSet.size());

		queryBean = new MyBean();
		queryBean.setPath(pathName);
		resultSet = getFromDB(queryBean);
		assertEquals(3, resultSet.size());

	}

	private void createPathBeans(int n, String string) {
		for (int j = 0; j < n; j++) {
			MyBean bean = new MyBean();
			bean.setPath(string);
			addToDB(bean);
		}
	}

	private void createArtistBeans(int n, String string) {
		for (int j = 0; j < n; j++) {
			MyBean bean = new MyBean();
			bean.setArtist(string);
			addToDB(bean);
		}
	}

	public void testModifyAlreadyExistentData() throws Exception {
		MyBean bean = new MyBean();
		String artistName = "anArtist";
		bean.setArtist(artistName);
		addToDB(bean);

		String newArtistName = "aNewArtist";
		bean.setArtist(newArtistName);

		// a query with the old value will correctly return no result
		MyBean queryBean = new MyBean();
		queryBean.setArtist(artistName);
		ObjectSet<Object> resultSet = getFromDB(artistName);
		assertEquals(0, resultSet.size());

		// bean is updated, after all DB has a reference to my bean instance...
		queryBean = new MyBean();
		resultSet = getFromDB(queryBean);
		MyBean resultBean = (MyBean) resultSet.next();
		assertEquals(1, resultSet.size());
		assertEquals(newArtistName, resultBean.getArtist());

		// ... but a query will not find it
		queryBean.setArtist(newArtistName);
		resultSet = getFromDB(queryBean);
		resultBean = (MyBean) resultSet.next();
		assertEquals(0, resultSet.size());

		// I need to re-set the bean in order to make query work.
		addToDB(bean);
		resultSet = getFromDB(queryBean);
		resultBean = (MyBean) resultSet.next();
		assertEquals(1, resultSet.size());
		assertEquals(newArtistName, resultBean.getArtist());
		assertSame(bean, resultBean);
	}
	
	public void testWhenQueryEngineIsUpdated() throws Exception {
		MyBean bean = new MyBean();
		String artistName = "anArtist";
		bean.setArtist(artistName);
		addToDB(bean);

		String newArtistName = "aNewArtist";
		bean.setArtist(newArtistName);
		addToDB(bean);
		
//		restartDB();
		// even if DB is restarted the query will not work
//		MyBean queryBean = new MyBean();
//		queryBean.setArtist(newArtistName);
//		ObjectSet<Object> resultSet = getFromDB(artistName);
//		assertEquals(1, resultSet.size());
		
		restartDB();
//		
		MyBean queryBean = new MyBean();
		queryBean.setArtist(newArtistName);
		ObjectSet<Object> resultSet = getFromDB(queryBean);
		assertEquals(1, resultSet.size());
		
		
	}

}
