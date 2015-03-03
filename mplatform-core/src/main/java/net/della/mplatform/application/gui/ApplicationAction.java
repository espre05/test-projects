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

package net.della.mplatform.application.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.background.AbstractJob;
import net.della.mplatform.background.swing.BackgroundCommand;
import net.della.mplatform.background.swing.SwingBackgroundTask;


@SuppressWarnings("serial")
public class ApplicationAction extends AbstractAction{

	public static final String USER_INPUT = "user input";
	protected final Application application;
	private final Action action;
	

	/**
	 * 
	 * 
	 * @param application
	 * @param This Action is used as a generic Command framework, the use of the Swing Command Framework  
	 * is used just because it already exists and has some already implemented features known by many 
	 *  
	 */
	protected ApplicationAction(Application application, Action action) {
		this.application = application;
		this.action = action;		
	}

	public void actionPerformed(ActionEvent e) {
		if (showDialog() == getApproveCode()) {
			Object userInput = parseUserInput();
			run(userInput);
		}
	}
	
	protected final void run(Object userInput) {
		action.putValue(USER_INPUT, userInput);
		SwingBackgroundTask task = BackgroundCommand.newIndeterminate(action);
		AbstractJob operation = AbstractJob.newSimpleJob(task,
				(String) action.getValue(Action.NAME), "");
		application.runOperation(operation);
	}

	protected Object parseUserInput() {
		return null;
	}

	protected int getApproveCode() {
		return 0;
	}

	protected int showDialog() {
		return 0;
	}


}
