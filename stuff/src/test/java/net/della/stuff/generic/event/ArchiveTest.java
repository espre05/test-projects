/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

/*
 * Created on 24-set-2003
 *
 */
package net.della.stuff.generic.event;

import java.util.Set;

import net.della.stuff.generic.event.*;


import junit.framework.TestCase;

/**
 * @author Daniele
 */
public class ArchiveTest extends TestCase {

	private ListenerArchive archive;

	private Set results;

	private EventListener sl1;

	private EventListener sl2;

	private EventListener sl3;

	/**
	 * Constructor for ArchiveTest.
	 * @param arg0
	 */
	public ArchiveTest(String arg0) {
		super(arg0);
	}

	public void setUp() {
		archive = new ListenerArchive();
		sl1 = new SimpleListener();
		sl2 = new SimpleListener();
		sl3 = new SimpleListener();
	}

	public void testAddAndRemove() {

		EventFilter filter1 = new EventFilter();
		archive.addListener(sl1, filter1);

		results = archive.getListeners();
		assertTrue(results.size() == 1);
		assertTrue(results.contains(sl1));

		archive.addListener(sl2, new EventFilter());
		archive.addListener(sl3, new EventFilter());
		results = archive.getListeners();
		assertTrue(results.size() == 3);
		assertTrue(results.contains(sl1));
		assertTrue(results.contains(sl2));
		assertTrue(results.contains(sl3));

		archive.removeListener(sl2);
		results = archive.getListeners();
		assertTrue(results.size() == 2);
		assertTrue(results.contains(sl1));
		assertFalse(results.contains(sl2));
		assertTrue(results.contains(sl3));

		archive.addListener(sl3, new EventFilter());
		results = archive.getListeners();
		assertTrue(results.size() == 2);

	}

	public void testContains() {

		archive.addListener(sl1, new EventFilter());
		archive.addListener(sl2, new EventFilter());
		archive.addListener(sl3, new EventFilter());

		assertTrue(archive.contains(sl1));
		assertTrue(archive.contains(sl2));
		assertTrue(archive.contains(sl3));

	}

	public void testNewArchive() {

		results = archive.getListeners(new Event());
		assertNotNull(results);
		assertTrue(results.size() == 0);

		assertFalse(archive.contains(new SimpleListener()));

	}
	
	public void testGetListenersFilteredSimple1() {
		
		EventFilter f1 = new EventFilter();
		
		f1.set("type", "move");
		archive.addListener(sl1, f1);
		
		Event e = new Event();
		results = archive.getListeners(e);
		assertEquals(0, results.size());
		assertFalse(results.contains(sl1));
		
		e.set("type", "move");
		results = archive.getListeners(e);
		assertEquals(1, results.size());
		assertTrue(results.contains(sl1));		
	}

	
	public void testGetListenersFiltered() {
		
		EventFilter f1 = new EventFilter();
		EventFilter f2 = new EventFilter();
		
		f1.set("type", "move");
		f2.set("type", "hit");

		archive.addListener(sl1, f1);		
		archive.addListener(sl2, f1);
		archive.addListener(sl3, f2);		
		
		Event e = new Event();
		e.set("type", "move");
		
		results = archive.getListeners(e);
		assertEquals(2, results.size());
		assertTrue(results.contains(sl1));
		assertTrue(results.contains(sl2));
		assertFalse(results.contains(sl3));
	}
	
	public void testAddListener() {
		archive.addListener(sl1);
		results = archive.getListeners(new Event());
		assertEquals(1, results.size());
		assertTrue(results.contains(sl1));		
	}


	class SimpleListener implements EventListener {
		public void eventHappened(Event event) {
		}
	}

}
