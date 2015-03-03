/*
 * Created on 19-feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.background.swing;

import java.util.Collection;
import java.util.Iterator;



/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class CollectionBackgroundTask extends SwingBackgroundTask {

	protected Collection col;
	private Iterator iter;
	private int period = 0;
	public CollectionBackgroundTask() {
	}

	public CollectionBackgroundTask(Collection collection) {
		this.col = collection;
		this.iter = col.iterator();
	}
	
	public CollectionBackgroundTask(Iterator iter) {
		this.iter = iter;
	}

	protected Object executeInBackground() throws Exception {
		int i = 0;
		int size = col.size();
 		firePropertyChange("max", 0, size);		 	
		for (Iterator it = col.iterator(); it.hasNext();) {
			try {
				firePropertyChange(NAME, "", name);
				applyTo(it.next());
				if (period > 0)
					Thread.sleep(period * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			setProgress(100 * ++i / size);
//			setReachedPoint(i);
		}
		return null;
	}
	

	protected Object executeInBackground2() throws Exception {
		int i = 0;
		int size = col.size();
		firePropertyChange("max", 0, size);		
		for (Iterator it = iter; it.hasNext();) {
			try {
				applyTo(it.next());
				if (period > 0)
					Thread.sleep(period * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			setProgress(100 * ++i / size);
//			setReachedPoint(i);
		}
		return null;
	}
	
//    protected final void setReachedPoint(int progress) {
//        if (progress < 0 || progress > col.size()) {
//            throw new IllegalArgumentException("the value should be from 0 to " + col.size());
//        }
//        int oldProgress = this.iterationState;
//        this.iterationState = progress;
//        synchronized (this) {
//            if (doNotifyIterationChange == null) {
//            	doNotifyIterationChange = 
//                    new AccumulativeRunnable<Integer>() {
//                        @Override
//                        public void run(Integer... args) {
//                            firePropertyChange("iterationState", 
//                               args[0], 
//                               args[args.length - 1]);
//                        }
//                    };
//            }
//        }
//        doNotifyIterationChange.add(oldProgress, progress);
//    }

	protected abstract void applyTo(Object singleElement);

	public final void setCollection(Collection col) {
		this.col = col;
		this.iter = iter;
	}

	public void setPausePeriod(int i) {
		this.period = i;
	}

}
