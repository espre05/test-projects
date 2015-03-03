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
 * Created on Jul 7, 2005
 */
package net.della.mplatform.docking;

import java.awt.Component;

import net.della.mplatform.gui.components.SimpleInternalFrame;

import org.flexdock.docking.DockingStub;


/**
 * @deprecated
 */
class DockableInternalFrame extends SimpleInternalFrame implements DockingStub {
	private String dockingId;

	DockableInternalFrame(String id) {
		super(id);
		dockingId = id;

	}
	
	public Component getDragSource() {
		return getToolBar();
	}
	
	public Component getFrameDragSource() {
		return getToolBar();
	}
	
	public String getPersistentId() {
		return dockingId;
	}
	
	public String getTabText() {
		return getTitle();
	}

}
