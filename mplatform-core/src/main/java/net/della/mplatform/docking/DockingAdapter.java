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

/**
 * 
 */
package net.della.mplatform.docking;

import org.flexdock.docking.event.DockingEvent;
import org.flexdock.docking.event.DockingListener;

public abstract class DockingAdapter implements DockingListener {
	public void undockingStarted(DockingEvent evt) {
		logDocking();
	}

	private void logDocking() {
		System.out.println("dock");
	}

	public void undockingComplete(DockingEvent evt) {
		// TODO Auto-generated method stub
		logDocking();

	}

	public void dropStarted(DockingEvent evt) {
		// TODO Auto-generated method stub
		logDocking();

	}

	public void dragStarted(DockingEvent evt) {
		// TODO Auto-generated method stub

		logDocking();
	}

	public void dockingCanceled(DockingEvent evt) {
		// TODO Auto-generated method stub
		logDocking();

	}

	public void dockingComplete(DockingEvent evt) {
		logDocking();
		// TODO Auto-generated method stub

	}
}