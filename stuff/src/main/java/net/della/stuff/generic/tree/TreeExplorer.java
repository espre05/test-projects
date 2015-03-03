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
import java.util.Iterator;
import java.util.List;

import net.della.stuff.generic.lang.ClassHelper;
import net.della.stuff.generic.lang.ReflectionException;


public class TreeExplorer {

  private List listeners;
  private final Object topLevelElement;
  private final Class<? extends TreeNode> modelClass;

  public TreeExplorer(Class<? extends TreeNode> model, Object topLevelElement) {
    this.modelClass = model;
    this.topLevelElement = topLevelElement;
    this.listeners = new ArrayList();
  }

  public List scan() {
    return scan(false);
  }

  public List scanRecursively() {
    return scan(true);
  }

  private List performIteration(TreeNode element) {
    List iterationChilds = element.listContentAsNodes();
    notifyListeners(element);
    return iterationChilds;
  }

  public List scan(boolean recursive) {
    TreeNode topLevelModel = newModelInstance(topLevelElement, 0);
    if (!recursive)
      return performIteration(topLevelModel);

    List result = new ArrayList();
    List remainingToScan = new ArrayList();
    remainingToScan.add(topLevelModel);
    while (remainingToScan.size() > 0) {
      TreeNode folder = (TreeNode) remainingToScan.get(0);
      List iterationChilds = performIteration(folder);
      remainingToScan.addAll(iterationChilds);
      remainingToScan.remove(0);
      result.add(folder);
    }
    result.remove(0);
    return result;
  }

  private TreeNode newModelInstance(Object treeElement, int depthLevel) {
    ClassHelper helper = new ClassHelper(modelClass);
    try {
      TreeNode instance = (TreeNode) helper.createObject();
      instance.setElement(treeElement);
      instance.setDepthLevel(depthLevel);
      return instance;
    } catch (ReflectionException e) {
      throw new RuntimeException(e);
    }
  }

  private void notifyListeners(TreeNode discoveredNode) {
    for (Iterator it = listeners.iterator(); it.hasNext();) {
      TreeExplorerListener listener = (TreeExplorerListener) it.next();
      listener.nodeDiscovered(discoveredNode);
    }
  }

  public void addIterationListener(TreeExplorerListener iterationOperation) {
    listeners.add(iterationOperation);
  }
}
