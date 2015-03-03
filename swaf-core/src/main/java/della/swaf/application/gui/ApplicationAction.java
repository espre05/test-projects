package della.swaf.application.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import della.swaf.application.application.Application;
import della.swaf.background.AbstractJob;
import della.swaf.background.swing.BackgroundCommand;
import della.swaf.background.swing.SwingBackgroundTask;

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
