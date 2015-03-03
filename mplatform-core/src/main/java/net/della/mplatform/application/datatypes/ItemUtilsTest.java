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

package net.della.mplatform.application.datatypes;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

public class ItemUtilsTest extends TestCase {

   public void testPopulate() throws Exception {
      ArrayList returnList = new ArrayList();
      Collection childsToExplore = ItemUtils.pupulate(new NullItem(), returnList);
      assertTrue(returnList.size() == 0);
      assertTrue(childsToExplore.size() == 0);

      int quantity = 5;
      returnList = new ArrayList();
      ItemSet itemSet = new FileItemSet();
      populateItemSet(itemSet, new NullItem(), quantity);
      childsToExplore = ItemUtils.pupulate(itemSet, returnList);
      assertTrue(returnList.size() == quantity);
      assertTrue(childsToExplore.size() == 0);

      returnList = new ArrayList();
      ItemSet rootSet = new FileItemSet();
      ItemSet firstLevelSet = new FileItemSet();
      populateItemSet(firstLevelSet, new NullItem(), quantity);
      populateItemSet(rootSet, firstLevelSet, quantity);
      childsToExplore = ItemUtils.pupulate(rootSet, returnList);
      assertEquals(0, returnList.size());
      assertEquals(quantity, childsToExplore.size());

   }

   public void testFindLeafs() throws Exception {
      ItemSet rootSet = new FileItemSet();
      ItemSet firstLevelSet = new FileItemSet();
      populateItemSet(firstLevelSet, new NullItem(), 5);
      populateItemSet(rootSet, firstLevelSet, 5);
      Collection leafs = ItemUtils.findLeafs(rootSet);
      assertEquals(25, leafs.size());
   }

   private void populateItemSet(ItemSet itemSet, Item childItem, int quantity) {
      for (int i = 0; i < quantity; i++) {
         itemSet.addItem(childItem);
      }
   }
}
