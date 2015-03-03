/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

/*
 * Created on 30-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package net.mcube.gui;

import java.util.List;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.mplatform.background.AbstractJob;
import net.della.mplatform.background.swing.CollectionBackgroundAction;
import net.della.mplatform.background.swing.SwingBackgroundTask;


/**
 * @author Daniele
 * 
 * This class is for Actions that affect all Song selected in a table.
 * 
 * 
 */
public abstract class GlobalCollectionAction extends CollectionBackgroundAction {

	private ApplicationWindow appWindow;

	Application application;

	public GlobalCollectionAction(Application application) {
		this.application = application;
		this.appWindow = application.getWindow();
	}

	protected List getCollection() {
		return appWindow.getCurrentPage().getFocusedView().getSelection();
	}

	protected void runTask(final SwingBackgroundTask task) {
		AbstractJob op = AbstractJob.newSimpleJob(task,
				"background operation...", "item");
		application.runOperation(op);
	}

}