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

package net.della.mplatform.docking.application;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;

import net.della.mplatform.application.extensions.DefaultPageExtension;
import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.mplatform.application.gui.structure.Page;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.docking.DockableAbstractView;
import net.della.mplatform.docking.DockableApplicationWindow;
import net.della.mplatform.docking.DockablePage;
import net.della.stuff.gui.swing.AdapterFactory;

import org.flexdock.docking.Dockable;
import org.flexdock.docking.DockableFactory;
import org.flexdock.docking.DockingManager;
import org.flexdock.docking.drag.effects.EffectsManager;
import org.flexdock.docking.drag.preview.GhostPreview;
import org.flexdock.docking.state.PersistenceException;
import org.flexdock.perspective.PerspectiveManager;
import org.flexdock.perspective.persist.FilePersistenceHandler;
import org.flexdock.perspective.persist.PersistenceHandler;
import org.flexdock.perspective.persist.xml.XMLPersister;

public class ApplicationWindowBuilder extends
      net.della.mplatform.application.core.ApplicationWindowBuilder {

   private static final String PERSPECTIVE_FILE = "application_layout.xml";

   protected void initMainWindow() {
      super.initMainWindow();
      final DockableApplicationWindow dockWindow = getApplicationWindow();
      JButton viewsButton = new JButton("Views");
      JPopupMenu viewPopup = new JPopupMenu();
      viewsButton.addMouseListener(AdapterFactory.newShowMenuAdapter(viewPopup));
      dockWindow.addViewMenu(viewPopup);
      dockWindow.addTopPanelPermanentComponent(viewsButton);

      final JComboBox pagesComboBox = new JComboBox(dockWindow.getPagesIdsArray());
      pagesComboBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Page newPage = dockWindow.getPage((String) pagesComboBox.getSelectedItem());
            dockWindow.loadPage(newPage);
         }
      });

      dockWindow.addTopPanelPermanentComponent(pagesComboBox);

      DefaultPageExtension extension = new DefaultPageExtension();
      extension.init(application);
      dockWindow.setDefaultPage(DefaultPageExtension.ID);
   }

   protected void postLoading() {
      super.postLoading();
      DockableApplicationWindow dockWindow = getApplicationWindow();
      DockablePage defaultPage = (DockablePage) dockWindow.getDefaultPage();
      dockWindow.loadPage(defaultPage);
      try {
         boolean loadedCorrectly = DockingManager.loadLayoutModel(true);
         if (!loadedCorrectly) {
            return;
         }
         Set dockableIds = DockingManager.getDockableIds();
         for (Iterator it = dockableIds.iterator(); it.hasNext();) {
            String id = (String) it.next();
            View view = appWindow.getView(id);
            boolean isDisplayed = DockingManager.isDocked(((DockableAbstractView) view)
                  .getDockableComponent());
            view.setEnabled(isDisplayed);
            if (isDisplayed) {
               defaultPage.addView(view);
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      } catch (PersistenceException e) {
         e.printStackTrace();
      }
   }

   protected void createWindow() {
      DockableApplicationWindow dockableWindow = new DockableApplicationWindow();
      configureDocking(dockableWindow);
      appWindow = dockableWindow;
   }

   protected void configureDocking(final ApplicationWindow dockableWindow) {
      DockingManager.setDockableFactory(new DockableFactory() {

         public Component getDockableComponent(String dockableId) {
            DockableAbstractView view = (DockableAbstractView) dockableWindow.getView(dockableId);
            if (view == null)
               return null;
            return view.getDockableComponent();
         }

         public Dockable getDockable(String dockableId) {
            // DockableAbstractView view = (DockableAbstractView)
            // getView(dockableId);
            // if (view == null)
            // return null;
            // return view.getDockable();
            return null;
         }

      });
      DockingManager.setFloatingEnabled(false);
      EffectsManager.setPreview(new GhostPreview());

      PersistenceHandler persister = new FilePersistenceHandler(new File("config/", PERSPECTIVE_FILE),
            XMLPersister.newDefaultInstance());
      PerspectiveManager.setPersistenceHandler(persister);
      DockingManager.setAutoPersist(true);

   }

   protected DockableApplicationWindow getApplicationWindow() {
      return (DockableApplicationWindow) super.getApplicationWindow();
   }
}
