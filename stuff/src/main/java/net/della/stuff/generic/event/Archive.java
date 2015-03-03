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

import java.util.*;

/**
 * @author Daniele
 */
class Archive {

	private HashMap map;
	
	Archive() {
		map = new HashMap();
	}

	boolean contains(EventListener listener) {
		return map.containsKey(listener);
	}

	Set getListeners() {
		return map.keySet();
	}
	
	void addListener(EventListener sl1, EventFilter filter1) {		
		map.put(sl1, filter1);
	}

	void removeListener(EventListener sl2) {
		map.remove(sl2);
	}

	Set getListeners(Event e) {
		Set results = new HashSet();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			EventListener el = (EventListener)it.next();
			EventFilter f = (EventFilter)map.get(el);
			if (f.apply(e)) 
				results.add(el);
		}
		return results;
	}

	void addListener(EventListener sl1) {
		EventFilter f = new EventFilter();
		addListener(sl1, f);
	}

}
