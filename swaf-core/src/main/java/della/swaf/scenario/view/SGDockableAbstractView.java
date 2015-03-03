package della.swaf.scenario.view;

import com.sun.scenario.scenegraph.SGGroup;

import della.swaf.docking.DockableAbstractView;

public abstract class SGDockableAbstractView extends DockableAbstractView {
   private static final String CLOSE_VIEW = "close view";
   private SGGroup rootNode;

   public void setId(String id) {
      rootNode = new SGGroup();
      SGDockInternalFrame panel = new SGDockInternalFrame(id);
      panel.setScene(rootNode);
      setViewComponent(panel);
      init();
      super.setId(id);
   }

}
