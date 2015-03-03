package della.swaf.docking;

import java.awt.Component;

import org.flexdock.docking.DockingStub;

import della.swaf.gui.components.SimpleInternalFrame;

public class DockInternalFrame extends SimpleInternalFrame implements DockingStub {

	private String dockingId;

	public DockInternalFrame(String id) {
		super(id);
		dockingId = id;
	}
	
	public String getPersistentId() {
		return dockingId;
	}

	public Component getDragSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public Component getFrameDragSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTabText() {
		return getTitle();
	}

}
