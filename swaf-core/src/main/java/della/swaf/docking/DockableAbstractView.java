package della.swaf.docking;

import javax.swing.JComponent;

import org.flexdock.docking.Dockable;
import org.flexdock.docking.DockingManager;

import della.swaf.application.gui.structure.AbstractView;
import della.swaf.gui.components.WidgetFactory;
import della.swaf.util.Command;

public abstract class DockableAbstractView extends AbstractView {
   private static final String CLOSE_VIEW = "close view";

   protected void init() {
      super.init();
      addButton(WidgetFactory.createToggleToolBarButton("", CLOSE_VIEW, "Close", "Close"),
            new CloseViewCommand());
   }

   public void setId(String id) {
      setViewComponent(new DockInternalFrame(id));
      init();
      super.setId(id);
   }

   public JComponent getDockableComponent() {
      return getViewComponent();
   }

   private final class CloseViewCommand implements Command {
      public Object run() {
         Dockable dockable = DockingManager.getDockable(getId());
         DockingManager.close(dockable);
         setEnabled(false);
         return null;
      }
   }

}
