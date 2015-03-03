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
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

public class DB4OQueryTest extends DB4OTestCase {

	public void testEvaluationQuery() throws Exception {
		MyBean myBean = new MyBean();
		final String name = "name";
		myBean.setArtist(name);
		MyBean beat2 = new MyBean();
		beat2.setArtist(name);

		addToDB(myBean);
		addToDB(beat2);

		Query q = newQuery();
		q.constrain(MyBean.class);
		q.constrain(new Evaluation() {
			public void evaluate(Candidate candidate) {
				MyBean bean = (MyBean) candidate.getObject();
				candidate.include(bean.getArtist().toLowerCase().equals(name));
			}
		});
		assertEquals(2, q.execute().size());
	}

	public void testEveluationAgainstReturnValue() throws Exception {
		BeanItem myBean = new BeanItem();
		final String name = "name";
		myBean.setId(name);
		BeanItem bean2 = new BeanItem();
		bean2.setId(name);

		addToDB(myBean);
		addToDB(bean2);
		
		Query q = newQuery();
		q.constrain(BeanItem.class);
		q.constrain(new Evaluation() {
			public void evaluate(Candidate candidate) {
				BeanItem bean = (BeanItem) candidate.getObject();
				candidate.include(bean.getId().toLowerCase().equals(name));
			}
		});
		assertEquals(2, q.execute().size());
		
		q = newQuery();
		q.constrain(BeanItem.class);
		assertEquals(2, q.execute().size());
		
	}
	
	public void testPerformance() throws Exception {
		long start = 0l;
		long end = 0l;
		start = time();
		int numberOfEntries = 10000;
		for (int i = 0; i < numberOfEntries; i++) {
			addToDB(newBeanItem(i));
		}
		end = time();
		System.out.println("add " + numberOfEntries + " entries: " + (end - start) + " millisec");
		
		start = time();
		Query query = newQuery(BeanItem.class);
		ObjectSet set = query.execute();
		assertEquals(numberOfEntries, set.size());
		end = time();
		System.out.println("retrieve " + numberOfEntries + " entries: " + (end - start) + " millisec");
				
	}
	
	public void testQueryWithSlash() throws Exception {
		BeanItem beanItem = newBeanItem(1);
		beanItem.setValue("/pippo");
		addToDB(beanItem);
		
		BeanItem queryItem = new BeanItem();
		queryItem.setValue("/pippo");
		ObjectSet<Object> resultSet = getFromDB(queryItem);
		BeanItem resultItem = (BeanItem) resultSet.next();
		assertEquals(1, resultSet.size());
	}

	private long time() {
		return System.currentTimeMillis();
	}

	private BeanItem newBeanItem(int id) {
		BeanItem item = new BeanItem();
		item.setId(id + "");
		item.setType("song");
		return item;
	}

}
