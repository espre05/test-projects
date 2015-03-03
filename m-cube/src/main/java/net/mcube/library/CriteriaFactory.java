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

package net.mcube.library;

import java.io.File;

import jin.collection.core.Criteria;
import net.della.mplatform.application.datatypes.FileItem;
import net.della.mplatform.application.persistence.Context;
import net.della.stuff.generic.file.FileUtils;



public class CriteriaFactory {

   public static Criteria createFolderExistsAndNotEmptyCriteria() {
      return new Criteria() {

         public boolean match(Object element) {
            File folder = new File((String) element);
            if (!folder.exists())
               return false;
            return !FileUtils.isFolderEmpty(folder);
         }

      };
   }

   public static Criteria createAlwaysFalseCriteria() {
      return new Criteria() {
         public boolean match(Object element) {
            return false;
         }
      };
   }

   public static Criteria createItemExistsCriteria() {
      return new Criteria() {

         public boolean match(Object element) {
            FileItem fileItem = (FileItem) (element);
            File file = new File(fileItem.getPath());
            return file.exists();
         }

      };
   }

   public static Criteria createItemInsideTopFoldersCriteria(final Context context) {
      return new Criteria() {

         public boolean match(Object element) {
            FileItem fileItem = (FileItem) (element);
            return null != context.getTopFolder(fileItem.getPath());
         }

      };
   }

   public static Criteria createFileUnderTopFoldersCriteria(final Context context) {
      return new Criteria() {

         public boolean match(Object element) {
            String path = (String) element;
            return null != context.getTopFolder(path);
         }

      };
   }

}
