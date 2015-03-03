/*
 * Created on Jul 7, 2005
 */
package della.swaf.docking;

import java.awt.Component;

import org.flexdock.docking.DockingStub;

import della.swaf.gui.components.SimpleInternalFrame;

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
