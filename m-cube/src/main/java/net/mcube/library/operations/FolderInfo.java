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
import java.io.FileFilter;
import java.util.List;

import jin.collection.core.Criteria;
import jin.collection.core.Iter;
import net.della.stuff.generic.file.FileUtils;


public class FolderInfo {

   static FolderContext createFolderContext(File folder) {
      FolderContext folderContext = new FolderContext();
      folderContext.setFiles(FileUtils.listFiles(folder,
            net.della.stuff.generic.file.FileFilters.MediaFileFilter));
      folderContext.setTracklist(loadExternalPlaylist(folder));
      folderContext.setCoverInfo(loadCoverInfo(folder));
      return folderContext;
   }

   private static CoverInfo loadCoverInfo(File folder) {
      final List imageFiles = FileUtils.listFiles(folder,
            net.della.stuff.generic.file.FileFilters.ImageFileFilter);
      final CoverInfo coverInfo = new CoverInfo();
      if (imageFiles.size() == 1)
         coverInfo.setFront((File) imageFiles.get(0));
      if (imageFiles.size() > 1) {
         List frontList = (List) new Iter().extract(imageFiles, createIsFrontImageCriteria());
         if (frontList.size() > 0)
            coverInfo.setFront((File) frontList.get(0));
         else
            coverInfo.setFront((File) imageFiles.get(0));
      }
      return coverInfo;
   }

   private static Criteria createIsFrontImageCriteria() {
      return new Criteria() {

         public boolean match(Object element) {
            File file = (File) element;
            if (file.getAbsolutePath().toLowerCase().indexOf("front") == -1)
               return false;
            return true;
         }

      };
   }

   private static Tracklist loadExternalPlaylist(final File folder) {
      Tracklist tracklist = new Tracklist();
      List externalPlaylistFiles = FileUtils.listFiles(folder, new FileFilter() {

         public boolean accept(File pathname) {
            if (FileUtils.getExtension(pathname).equals("txt"))
               return true;
            return false;
         }
      });
      if (externalPlaylistFiles.size() > 0) {
         File tracklistFile = (File) externalPlaylistFiles.get(0);
         tracklist.loadFromFile(tracklistFile);
      }
      return tracklist;
   }

}
