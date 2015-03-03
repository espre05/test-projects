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

import net.della.stuff.generic.tree.TreeNode;

public class FileNode extends TreeNode {

   public FileNode() {
   }

   public FileNode(Object element) {
      super(element);
   }

   public FileNode(Object element, int i) {
      super(element, i);
   }

   @Override
   public List<? extends FileNode> listContentAsNodes() {
      return null;
   }

   public File getFile() {
      return (File) wrapperElement;
   }

   @Override
   public String toString() {
      File file = (File) wrapperElement;
      return file.getAbsolutePath();
   }

   public String getFilename() {
      return getFile().getName();
   }

   protected class ToFileModel extends TreeNode.ToTreeNode {

      public ToFileModel(FileNode owner) {
         super(owner);
      }

      protected FileNode create() {
         return new FileNode();
      }
   }

}
