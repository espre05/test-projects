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

import net.della.mplatform.background.AbstractJob;
import net.della.mplatform.background.TaskList;
import net.della.mplatform.background.swing.SwingBackgroundTask;
import net.mcube.library.CriteriaFactory;
import net.mcube.library.LibraryImpl;

public class CleanLibraryOperation extends AbstractJob {

   private LibraryImpl library;

   public CleanLibraryOperation(LibraryImpl library) {
      this.library = library;
   }

   protected TaskList getThreadPool() {

      SwingBackgroundTask cleanItemsTask = library.getCleanItemsTask(CriteriaFactory
            .createItemExistsCriteria());
      cleanItemsTask.setName("Cleaning");
      cleanItemsTask.setElementsType("items");
      SwingBackgroundTask cleanFoldersTask = library.getCleanFoldersTask(CriteriaFactory
            .createFolderExistsAndNotEmptyCriteria());
      cleanFoldersTask.setName("Cleaning");
      cleanFoldersTask.setElementsType("items");

      TaskList pool = TaskList.simpleThreadPool();
      pool.add(cleanFoldersTask);
      pool.add(cleanItemsTask);
      return pool;

   }

}
