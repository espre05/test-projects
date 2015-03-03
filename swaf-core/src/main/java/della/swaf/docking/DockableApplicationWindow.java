package della.swaf.docking;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.*;

import org.flexdock.docking.DockingManager;
import org.flexdock.docking.state.PersistenceException;

import della.swaf.application.application.DefaultApplicationWindow;
import della.swaf.application.gui.structure.Page;
import della.swaf.application.gui.structure.View;

public class DockableApplicationWindow extends DefaultApplicationWindow {

   private String defaultPageId;

   private JPopupMenu viewPopup;

   public boolean registerView(final View view) {
      boolean result = super.registerView(view);
      if (!result)
         return false;
      final DockableAbstractView dockableView = (DockableAbstractView) view;
      // dockableView.setId(id);
      DockingManager.registerDockable(dockableView.getDockableComponent());
      Action action = new AbstractAction(view.getId()) {

         public void actionPerformed(ActionEvent e) {
            boolean isEnabled = view.isEnabled();
            if (isEnabled) {
               getCurrentPage().removeView(view);
            } else {
               getCurrentPage().addView(view);
            }
            view.setEnabled(!isEnabled);
            try {
               DockingManager.storeLayoutModel();
            } catch (IOException e1) {
               e1.printStackTrace();
            } catch (PersistenceException e1) {
               e1.printStackTrace();
            }
         }
      };

      JMenuItem menuItem = new JCheckBoxMenuItem(action);
      menuItem.setModel(new JToggleButton.ToggleButtonModel() {

         public boolean isSelected() {
            return view.isEnabled();
         }

      });
      viewPopup.add(menuItem);
      return true;
   }

   public void setDefaultPage(String pageId) {
      defaultPageId = pageId;
   }

   public Page getDefaultPage() {
      return getPage(defaultPageId);
   }

   public void addViewMenu(JPopupMenu viewPopup) {
      this.viewPopup = viewPopup;
   }

}
