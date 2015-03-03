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

import static org.junit.Assert.assertEquals;

import java.util.List;

import net.della.stuff.generic.tree.TreeExplorer;
import net.della.stuff.generic.tree.TreeExplorerListener;
import net.della.stuff.generic.tree.TreeNode;

import org.junit.Test;

public class TreeExplorerTest {

  private TreeExplorer explorer;

  @Test
  public void testListFirstLevelChilds() throws Exception {
    explorer = new TreeExplorer(NumberTreeModel.class, 3);
    List listChilds = explorer.scan();
    assertEquals(2, listChilds.size());
  }

  public void testtestListChildsRecursively() throws Exception {
    explorer = new TreeExplorer(NumberTreeModel.class, 4);
    List listChildsRecursively = explorer.scanRecursively();
    assertEquals(3, listChildsRecursively.size());
    listChildsRecursively = explorer.scanRecursively();
    assertEquals(7, listChildsRecursively.size());
  }

  @Test
  public void shouldNotifyEachIteration() throws Exception {
    int topLevel = 4;
    explorer = new TreeExplorer(NumberTreeModel.class, topLevel);
    CallsCounter iterationOperation = new CallsCounter(topLevel);
    explorer.addIterationListener(iterationOperation);
    explorer.scan();
    assertEquals(1, iterationOperation.countCalls());
    iterationOperation.reset();
    explorer.scanRecursively();
    assertEquals(8, iterationOperation.countCalls());
  }

  @Test
  public void shouldNotifyIterationLevel() throws Exception {
    int topLevel = 3;
    explorer = new TreeExplorer(NumberTreeModel.class, topLevel);
    TreeExplorerListener iterationOperation = new TreeExplorerListener() {
      public void nodeDiscovered(TreeNode node) {
        assertEquals(0, node.getDepthLevel());
      }
    };
    explorer.addIterationListener(iterationOperation);
    explorer.scan();
    explorer.scan();
  }

  class CallsCounter implements TreeExplorerListener {

    private int i;
    private int calls;
    private final int max;

    public CallsCounter(int max) {
      this.max = max;
      this.i = max;
    }

    public void reset() {
      calls = 0;
      i = max;
    }

    public void nodeDiscovered(TreeNode node) {
      calls++;
      StringBuffer childs = new StringBuffer();
      List actualChilds = node.listContentAsNodes();
      for (int i = 0; i < actualChilds.size(); i++) {
        childs.append(actualChilds.get(i));
      }
      System.out.println(calls + ": " + actualChilds.size() + " - " + childs.toString());
    }

    public Object countCalls() {
      return calls;
    }

  }
}
