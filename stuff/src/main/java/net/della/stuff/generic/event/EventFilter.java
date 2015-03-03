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

import java.util.*;

/**
 * @author Daniele
 * 
 * le condizioni sono in AND
 * le condizioni con la stessa chiave sono in OR tra di loro
 * 
 * 
 */
public class EventFilter {

	private Map map;

	public EventFilter() {
		map = new HashMap();

	}

	public boolean apply(Event e) {
		if (map.isEmpty()) return true;
		return verifyCondition(e);
	}

	public boolean verifyCondition(Event e) {
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String)it.next();
			if (!(get(key)).contains(e.get(key))) return false;
		}
		return true;
	}
	
	public void set(String key, Object value) {
		if (map.containsKey(key)) {
			List values = (List)map.get(key);
			values.add(value);
		} else {
			List values = new ArrayList();
			values.add(value);
			map.put(key, values);
		}
	}

	public List get(String key) {
		return (List)map.get(key);
	}

}
