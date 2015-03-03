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

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Daniele
 */
class Dispatcher {

	void notifyEvents(Event e, Collection c) {
		Iterator it = c.iterator();
		while (it.hasNext()) {
			EventListener el = (EventListener)it.next();
			el.eventHappened(e);
		}
	}

}
