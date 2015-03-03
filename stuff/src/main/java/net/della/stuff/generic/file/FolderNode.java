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

package net.della.stuff.generic.file;

import java.io.File;
import java.util.List;

import jin.collection.core.Iter;

public class FolderNode extends FileNode {
   public FolderNode() {
   }

   public FolderNode(File element) {
      super(element);
   }

   public FolderNode(Object element, int i) {
      super(element, i);
   }

   public List<? extends FolderNode> listContentAsNodes() {
      List subFolders = FileUtils.listSubFolders((File) wrapperElement);
      return (List) Iter.collect(subFolders, new ToFolderModel(this));
   }

   public List<File> listContentAsFiles() {
      return FileUtils.listFiles(getFile(), FileFilters.AllFileFilter);
   }

   final class ToFolderModel extends ToFileModel {
      public ToFolderModel(FolderNode owner) {
         super(owner);
      }

      protected FileNode create() {
         return new FolderNode();
      }
   }

}
