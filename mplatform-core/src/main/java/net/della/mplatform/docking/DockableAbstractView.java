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

import javax.swing.JComponent;

import net.della.mplatform.application.gui.structure.AbstractView;
import net.della.stuff.generic.util.Command;
import net.della.stuff.gui.components.WidgetFactory;

import org.flexdock.docking.Dockable;
import org.flexdock.docking.DockingManager;


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
