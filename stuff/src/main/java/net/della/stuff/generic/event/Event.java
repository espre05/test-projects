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
 * Created on 10-set-2003
 *
 */
package net.della.stuff.generic.event;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Daniele
 */
public class Event {
	
	//private int type;
	private Map map;
	public static final String TYPE = "type";
	public static final String SOURCE = "source";
	
	Event() {
		map = new HashMap();
	}
	
	public Object get(String key) {
		return map.get(key);
	}

	public void set(String key,  Object value) {
		map.put(key, value);
	}
	
	public boolean contains(String key) {
		return map.containsKey(key);
	}
	

}
