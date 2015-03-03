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

/*
 * Created on 3-feb-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.export;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.background.AbstractJob;
import net.della.mplatform.background.swing.CollectionBackgroundTask;
import net.della.mplatform.background.swing.SwingBackgroundTask;
import net.della.mplatform.util.WidgetFactory;
import net.mcube.extensions.album.AlbumExtension;
import net.mcube.extensions.authors.ArtistExtension;
import net.mcube.extensions.compilations.MyCompilations;
import net.mcube.extensions.tracksView.TracksExtension;
import della.swaf.extensions.gui.TableView;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ExporterExtension implements Extension {

   private Application application;

   private JFileChooser fc;

   private File targetFolder;

   public void init(Application application) {
      this.application = application;

      fc = WidgetFactory.getInstance().createFolderChooser();
      applyExportToView(TracksExtension.VIEW_ID, new CopySong());
      applyExportToView(AlbumExtension.VIEW_ID, new CopySongSet());
      applyExportToView(ArtistExtension.VIEW_ID, new CopySongSet());
      applyExportToView(MyCompilations.VIEW_ID, new CopySongSet());

      applyExportZipToView(AlbumExtension.VIEW_ID);
      // applyExportZipToView(MyCompilations.VIEW_ID);

   }

   private void applyExportZipToView(String viewId) {
      TableView view = (TableView) application.getWindow().getView(viewId);
      JMenuItem zipMenuItem = new JMenuItem(new CreateZipFileAction(application, view));
      view.addToPopup(View.CONTEXT_MENU, zipMenuItem);
      addSeparator(view);
   }

   private void addSeparator(TableView view) {
      view.addToPopup(View.CONTEXT_MENU, new JPopupMenu.Separator());
   }

   private void applyExportToView(String viewId, CopyItem copy) {
      TableView view = (TableView) application.getWindow().getView(viewId);
      addSeparator(view);
      JMenuItem menuItem = new JMenuItem(new ExportFilesAction(this, copy));
      // JMenuItem menuItem = new JMenuItem(new ExportPlaylistAction(this,
      // copy));
      view.addToPopup(View.CONTEXT_MENU, menuItem);
   }

   public String getId() {
      return "Exporter";
   }

   void openExportDialog(final CopyItem copy) {
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      fc.setApproveButtonText("Export");
      fc.setApproveButtonToolTipText("Copy all selected files to target directory");
      // JComponent viewComponent =
      // application.getWindow().getCurrentPage().getFocusedView()
      // .getViewComponent();
      int returnVal = fc.showDialog(null, null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         targetFolder = fc.getSelectedFile();
         fc.setCurrentDirectory(targetFolder);

         List childs = getSelectionList();

         final SwingBackgroundTask task = new CollectionBackgroundTask(childs) {

            protected void applyTo(Object singleElement) {
               copy.execute((ObservableItem) singleElement, targetFolder);
            }
         };
         AbstractJob op = AbstractJob.newSimpleJob(task, "Copying files", "files");
         application.runOperation(op);
      }
   }

   private List getSelectionList() {
      return application.getWindow().getCurrentPage().getFocusedView().getSelection();
   }
}
