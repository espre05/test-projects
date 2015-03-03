/**
 * 
 */
package della.swaf.docking;

import org.flexdock.docking.event.DockingEvent;
import org.flexdock.docking.event.DockingListener;

abstract class DockingAdapter implements DockingListener {
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