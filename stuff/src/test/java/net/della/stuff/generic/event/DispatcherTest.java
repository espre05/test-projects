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
 * Created on 12-ott-2003
 *
 */
package net.della.stuff.generic.event;

import java.util.HashSet;
import java.util.Set;

import net.della.stuff.generic.event.Dispatcher;
import net.della.stuff.generic.event.Event;


import junit.framework.TestCase;

/**
 * @author Daniele
 */
public class DispatcherTest extends TestCase {

	private Dispatcher disp;

	public void setUp() {
		disp = new Dispatcher();
	}
	
	public void testNotifyEvent() {
		Set listeners = new HashSet();
		SimpleListener el1 = new SimpleListener();
		SimpleListener el2 = new SimpleListener();
		SimpleListener el3 = new SimpleListener();
		listeners.add(el1);
		listeners.add(el2);
		listeners.add(el3);
		
		Event e = new Event();
		disp.notifyEvents(e, listeners);
		
		assertTrue(el1.called);
		assertTrue(el2.called);
		assertTrue(el3.called);
		
		disp.notifyEvents(e, listeners);
		
		assertEquals(2, el1.callCounter);
		assertEquals(2, el2.callCounter);
		assertEquals(2, el3.callCounter);
		
		listeners.remove(el2);
		disp.notifyEvents(e, listeners);
		assertEquals(3, el1.callCounter);
		assertEquals(2, el2.callCounter);
		assertEquals(3, el3.callCounter);
	}

}
