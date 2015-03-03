package della.swaf.background.swing;



public abstract class IndeterminateBackgroundTask extends SwingBackgroundTask {
	
	public static final String INDETERMINATE = "indeterminate";

	protected final Object executeInBackground() throws Exception {
		firePropertyChange(INDETERMINATE, "", true);
		Object result = executeIndeterminateOperation();
		firePropertyChange(INDETERMINATE, true, false);
		return result;
	}

	protected abstract Object executeIndeterminateOperation();

}
