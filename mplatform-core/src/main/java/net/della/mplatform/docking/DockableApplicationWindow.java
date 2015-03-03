/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.mplatform.docking;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.*;

import net.della.mplatform.application.core.DefaultApplicationWindow;
import net.della.mplatform.application.gui.structure.Page;
import net.della.mplatform.application.gui.structure.View;

import org.flexdock.docking.DockingManager;
import org.flexdock.docking.state.PersistenceException;

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
