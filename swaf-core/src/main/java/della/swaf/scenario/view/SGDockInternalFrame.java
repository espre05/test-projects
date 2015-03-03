package della.swaf.scenario.view;

import java.awt.Component;

import org.flexdock.docking.DockingStub;

public class SGDockInternalFrame extends SGInternalFrame implements DockingStub {

   private String dockingId;

   public SGDockInternalFrame(String id) {
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
