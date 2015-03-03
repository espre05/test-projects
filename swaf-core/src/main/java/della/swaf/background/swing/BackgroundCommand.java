package della.swaf.background.swing;

import javax.swing.Action;


public class BackgroundCommand {

	public static SwingBackgroundTask newIndeterminate(final Action action) {
		return new IndeterminateBackgroundTask() {
			
			protected Object executeIndeterminateOperation() {
				action.actionPerformed(null);
				return null;
			}		
		};
	}

}
