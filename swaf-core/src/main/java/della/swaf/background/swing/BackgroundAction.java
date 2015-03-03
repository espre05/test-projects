package della.swaf.background.swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


public abstract class BackgroundAction extends AbstractAction {

	public void actionPerformed(ActionEvent e) {
		SwingBackgroundTask task = createBackgroundTask();
		runTask(task);
	}

	protected abstract void runTask(final SwingBackgroundTask task);

	protected abstract SwingBackgroundTask createBackgroundTask();	

}
