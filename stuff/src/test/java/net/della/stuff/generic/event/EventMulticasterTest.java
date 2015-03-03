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
 * Created on Sep 14, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package net.della.stuff.generic.event;

import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventFilter;
import net.della.stuff.generic.event.EventMulticaster;

import junit.framework.TestCase;

/**
 * @author trz
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EventMulticasterTest extends TestCase {

	private SimpleListener sl1;

	private EventMulticaster em;

	private Event evt;

	public void setUp() {
		sl1 = new SimpleListener();
		em = new EventMulticaster();
		evt = new Event();
	}

	public void testNotifyEvent() {

		EventFilter f1 = new EventFilter();
		em.addListener(sl1, f1);

		em.notifyEvent(evt);
		assertEquals(1, sl1.callCounter);
		
		f1.set("type", "move");
		em.addListener(sl1, f1);
		em.notifyEvent(evt);
		assertEquals(1, sl1.callCounter);
		
		evt.set("type", "move");
		em.notifyEvent(evt);
		assertEquals(2, sl1.callCounter);
		
	}
	
	public void testNotifyEventWithoutFilter() {		
		em.addListener(sl1);
		em.notifyEvent(evt);
		assertEquals(1, sl1.callCounter);
	}

	
}
