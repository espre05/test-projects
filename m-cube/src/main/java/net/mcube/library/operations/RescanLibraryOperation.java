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

package net.mcube.library.operations;

import java.io.File;
import java.util.Iterator;

import net.della.mplatform.background.AbstractJob;
import net.della.mplatform.background.TaskList;
import net.della.mplatform.background.swing.SwingBackgroundTask;
import net.mcube.library.CriteriaFactory;
import net.mcube.library.LibraryImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class RescanLibraryOperation extends AbstractJob {

   private LibraryImpl library;

   public RescanLibraryOperation(LibraryImpl library) {
      this.library = library;

   }

   protected TaskList getThreadPool() {
      final Log log = LogFactory.getLog(this.getClass());
      String labelText = "Rescan...";
      TaskList pool = TaskList.simpleThreadPool();

      CleanLibraryOperation cleanOp = new CleanLibraryOperation(library);
      cleanOp.setProgressBar(getProgressBar());
      cleanOp.setFeedbackLabel(getFeedbackLabel());
      pool.addAll(cleanOp.getThreadPool());

      // the one and only new part
      SwingBackgroundTask cleanFoldersOutsideTopFoldersTask = library.getCleanFoldersTask(CriteriaFactory
            .createFileUnderTopFoldersCriteria(library.getContext()));
      cleanFoldersOutsideTopFoldersTask.setName(labelText);
      // attachStandardListenersTo(cleanFoldersOutsideTopFoldersTask,
      // labelText);

      SwingBackgroundTask cleanItemsOutsideTopFoldersTask = library.getCleanItemsTask(CriteriaFactory
            .createItemInsideTopFoldersCriteria(library.getContext()));
      cleanItemsOutsideTopFoldersTask.setName(labelText);
      // attachStandardListenersTo(cleanItemsOutsideTopFoldersTask, labelText);

      pool.add(cleanFoldersOutsideTopFoldersTask);
      pool.add(cleanItemsOutsideTopFoldersTask);

      // reload topFolders from file
      // DiskArchiveManager diskArchiveManager =
      // DiskArchiveManager.getDefault();
      // diskArchiveManager.loadTopFolders();

      for (Iterator it = library.getTopFolders().pathsIterator(); it.hasNext();) {
         String folder = (String) it.next();
         AddFolderOperation addfolder = new AddFolderOperation(library, new File(folder));
         addfolder.setProgressBar(getProgressBar());
         addfolder.setFeedbackLabel(getFeedbackLabel());
         pool.addAll(addfolder.getThreadPool());
      }
      return pool;

   }

}
