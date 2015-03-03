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
 * Created on 30-set-2003
 *
 */
package net.della.stuff.generic.event;

import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventFilter;

import junit.framework.TestCase;

/**
 * @author Daniele
 */
public class EventFilterTest extends TestCase {

	private EventFilter filter;

	private Event evt;

	/**
	 * Constructor for FilterTest.
	 * @param arg0
	 */
	public EventFilterTest(String arg0) {
		super(arg0);
	}

	public void setUp() {
		filter = new EventFilter();
		evt = new Event();
	}
	
	public void testDefaultBehavior2() {		
		assertTrue(filter.apply(evt));

	}
	
}
