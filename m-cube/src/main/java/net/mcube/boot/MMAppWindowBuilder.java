/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package net.mcube.boot;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import net.della.mplatform.background.TaskListenerFactory;
import net.della.mplatform.docking.DockableApplicationWindow;
import net.della.mplatform.docking.application.ApplicationWindowBuilder;
import net.della.mplatform.util.WidgetFactory;
import net.mcube.extensions.defaultPerspective.DefaultMcubeExtension;
import net.mcube.library.LibraryImpl;

public class MMAppWindowBuilder extends ApplicationWindowBuilder {

   public void initMainWindow() {

      super.initMainWindow();
      final DockableApplicationWindow dockWindow = getApplicationWindow();
      dockWindow.setDefaultPage(DefaultMcubeExtension.PAGE_ID);
      dockWindow.setTitle("mCube Music Manager");

      JLabel userFeedbackLabel = WidgetFactory.createLabel("Done");
      dockWindow.addToBottomPanel(userFeedbackLabel);
      JProgressBar progressBar = WidgetFactory.createProgressBar();
      dockWindow.addToBottomPanel(progressBar);
      application.addPropertyChangeListener(TaskListenerFactory.createProgressBarListener(progressBar));
      application.addPropertyChangeListener(TaskListenerFactory
            .createFeedbackLabelListener(userFeedbackLabel));

      LibraryImpl library = (LibraryImpl) application.getLibrary();
      dockWindow.addToBottomPanel(library.getFilteredNumberTextPane());
      dockWindow.addToBottomPanel(new JLabel("filtered songs out of "));
      dockWindow.addToBottomPanel(library.getTotalNumnerTextPane());
      dockWindow.addToBottomPanel(new JLabel("total songs in library"));

   }

}
