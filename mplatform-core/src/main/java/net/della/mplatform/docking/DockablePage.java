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

/*
 * Created on 11-giu-2005 
 * 
 */
package net.della.mplatform.docking;

import java.io.IOException;

import javax.swing.JComponent;

import net.della.mplatform.application.gui.structure.AbstractPage;
import net.della.mplatform.application.gui.structure.View;

import org.flexdock.docking.Dockable;
import org.flexdock.docking.DockingManager;
import org.flexdock.docking.DockingPort;
import org.flexdock.docking.defaults.DefaultDockingPort;
import org.flexdock.docking.event.DockingEvent;
import org.flexdock.docking.state.PersistenceException;

/**
 * @author Della
 * 
 */
public class DockablePage extends AbstractPage {

   private DockingPort dockingPort;

   public DockablePage() {
      dockingPort = new DefaultDockingPort();
      dockingPort.addDockingListener(new DockingAdapter() {

         public void undockingComplete(DockingEvent evt) {
            storeDockingLayout();
         }

         public void dockingComplete(DockingEvent evt) {
            storeDockingLayout();
         }

      });
      System.out.println(dockingPort.getClass().getName() + "@" + Integer.toHexString(hashCode()));

   }

   public void addView(View view, boolean floating) {
      super.addView(view, floating);
      DockableAbstractView dockableView = (DockableAbstractView) view;
      DockingManager.display(dockableView.getId());
   }

   public void removeView(View view) {
      super.removeView(view);
      DockableAbstractView dockableView = (DockableAbstractView) view;
      Dockable dockable = DockingManager.getDockable(dockableView.getId());
      DockingManager.close(dockable);
   }

   public JComponent getComponent() {

      return (JComponent) dockingPort;
   }

   public void revalidateAndRepaint() {
      JComponent c = (JComponent) dockingPort;
      c.revalidate();
      c.repaint();
   }

   void storeDockingLayout() {
      try {
         DockingManager.storeLayoutModel();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (PersistenceException e) {
         e.printStackTrace();
      }
   }
}
