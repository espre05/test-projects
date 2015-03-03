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

package net.della.stuff.generic.tree;

import java.util.List;

import jin.collection.core.ReadAccessor;

public abstract class TreeNode {

   protected Object wrapperElement;
   protected TreeNode parent;
   protected int depthLevel;

   public TreeNode() {
   }

   public TreeNode(Object treeElement) {
      this(treeElement, 0);
   }

   public TreeNode(Object treeElement, int depthLevel) {
      setDepthLevel(depthLevel);
      setElement(treeElement);
   }

   public abstract List<? extends TreeNode> listContentAsNodes();

   public void setDepthLevel(int depthLevel) {
      this.depthLevel = depthLevel;
   }

   public void setElement(Object treeElement) {
      this.wrapperElement = treeElement;
   }

   public int getDepthLevel() {
      return depthLevel;
   }

   public TreeNode getParent() {
      return parent;
   }

   public void setParent(TreeNode parent) {
      this.parent = parent;
   }

   protected abstract class ToTreeNode implements ReadAccessor {
      private final TreeNode owner;

      public ToTreeNode(TreeNode owner) {
         this.owner = owner;
      }

      public final TreeNode getValue(Object element) {
         TreeNode fileModel = create();
         fileModel.setElement(element);
         fileModel.setDepthLevel(getDepthLevel() + 1);
         fileModel.setParent(owner);
         return fileModel;
      }

      protected abstract TreeNode create();
   }

}
