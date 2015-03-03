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

import java.util.ArrayList;
import java.util.List;

import net.della.stuff.generic.tree.TreeNode;

/**
 * The idea is that if you give 4 the first sub level contains "1, 2, 3". Then 3
 * will contain just "1, 2" and so on.
 */
public final class NumberTreeModel extends TreeNode {
  public List<TreeNode> listContentAsNodes() {
    int n = (Integer) wrapperElement;
    ArrayList childs = new ArrayList();
    for (int i = n - 1; i >= 1; i--) {
      NumberTreeModel numberTreeModel = new NumberTreeModel();
      numberTreeModel.setElement(i);
      childs.add(numberTreeModel);
    }
    return childs;
  }
}