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

package net.della.mplatform.application.core;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventFactory;
import net.della.stuff.gui.swing.PanelMediator;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

public class ApplicationWindowBuilder {

   protected DefaultApplicationWindow appWindow;

   protected Application application;

   public void loadWindow(final DefaultApplication application) {

      this.application = application;

      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      appWindow.setFrame(frame);

      // appWindow.setNewLookAndFeel(new NimbusLookAndFeel());
      appWindow.setNewLookAndFeel(new Plastic3DLookAndFeel());

   }

   protected void createWindow() {
      appWindow = new DefaultApplicationWindow();
   }

   protected DefaultApplicationWindow getApplicationWindow() {
      return appWindow;
   }

   protected void initMainWindow() {
      appWindow.useTopPanel();
      appWindow.useBottonPanel();

      JPanel perspectiveRelatedComponentPanel = new JPanel();
      PanelMediator perspectivePanelMediator = new PanelMediator(perspectiveRelatedComponentPanel);
      appWindow.setPerspectivePanelMediator(perspectivePanelMediator);

      JPanel applicationRelatedComponentPanel = new JPanel();
      appWindow.setApplicationRelatedComponentPanel(applicationRelatedComponentPanel);

      File saveFile = new File(application.getConfigFolder() + File.separator + "frame.xml");
      appWindow.enablePersistence(saveFile);

      appWindow.addWindowListener(new WindowAdapter() {

         public void windowClosed(WindowEvent e) {
            Event ge = EventFactory.createEvent(this, Application.CLOSED);
            application.fireEvent(ge);
         }

         public void windowClosing(WindowEvent e) {
            Event ge = EventFactory.createEvent(this, Application.CLOSING);
            application.fireEvent(ge);
         }
      });

   }

   protected void showMainWindow() {
      if (!appWindow.isVisible())
         appWindow.revalidateAndRepaint();
      appWindow.setVisible(true);
   }

   protected void postLoading() {
   }

   public DefaultApplicationWindow getAppWindow() {
      return appWindow;
   }

}
