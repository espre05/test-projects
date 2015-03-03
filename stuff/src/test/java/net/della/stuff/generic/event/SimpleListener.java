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

import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventListener;

/**
 * @author Daniele
 */
class SimpleListener implements EventListener {

	boolean called = false;
	int callCounter = 0;

	public void eventHappened(Event event) {
		called = true;
		callCounter++;
	}
}