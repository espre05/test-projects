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
 * Created on 19-nov-2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.della.stuff.generic.event;

import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventFilter;

import junit.framework.TestCase;

/**
 * @author Daniele
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EventFilterTestAnd extends TestCase {

	private EventFilter filter;
	private Event e;
	private Object value2;
	private Object value1;
	private String key1;
	private String key2;

	public void setUp() {
		filter = new EventFilter();
		e = new Event();
		value1 = new Object();
		value2 = new Object();
		key1 = "key1";
		e.set(key1, value1);
		key2 = "key2";
		e.set(key2, value2);
	}
	
	public void testApply1() {		
		filter.set(key1, value1);
		assertTrue(filter.apply(e));		
	}
	
	public void testApply2() {
		filter.set(key2, value2);
		assertTrue(filter.apply(e));
	}
	
	public void testApply3() {
		filter.set(key1, value1);
		filter.set(key2, value2);
		assertTrue(filter.apply(e));
	}
	
	public void testApplyWrong1() {
		filter.set(key2, value1);
		assertFalse(filter.apply(e));		
	}
	
	public void testApplyWrong2() {
		filter.set(key2, new Object());
		assertFalse(filter.apply(e));		
	}
	
	
	public void testApplyWrong3() {
		filter.set(key1, value1);
		filter.set(key2, new Object());
		assertFalse(filter.apply(e));		
	}
	
	
}
