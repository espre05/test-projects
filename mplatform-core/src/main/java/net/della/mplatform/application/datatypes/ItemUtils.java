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

import java.util.*;

import net.della.mplatform.application.persistence.BasicLibrary;

public class ItemUtils {

   public static void enableCollectionProperty(Item item, String propertyName) {
      Object property = item.get(propertyName);
      if (property == null)
         item.put(propertyName, new HashSet());
   }

   public static void addToCollection(Item item, String propertyName, Object value) {
      Collection collection = (Collection) item.get(propertyName);
      if (value instanceof Collection) {
         collection.addAll((Collection) value);
      } else
         collection.add(value);
      // item.firePropertyChange(propertyName, null, value);
      BasicLibrary.getDefault().update(item, propertyName, value, collection);
   }

   public static void removeFromCollection(Item item, String propertyName, Object value) {
      Collection collection = (Collection) item.get(propertyName);
      if (value instanceof Collection) {
         collection.removeAll((Collection) value);
      } else
         collection.remove(value);
      // item.firePropertyChange(propertyName, value, collection);
      BasicLibrary.getDefault().update(item, propertyName, value, collection);
   }

   public static void replace(Object oldValue, Object text, Item item, String propertyName) {
      Collection collection = (Collection) item.get(propertyName);
      collection.remove(oldValue);
      collection.add(text);
   }

   static Collection pupulate(Item selectedItem, Collection returnList) {
      Collection toExploreList = new ArrayList();
      for (Iterator iter = selectedItem.childIterator(); iter.hasNext();) {
         Item item = (Item) iter.next();
         if (item.countChilds() == 0)
            returnList.add(item);
         else
            toExploreList.add(item);
      }
      return toExploreList;
   }

   public static Collection findLeafs(Item selectedItem) {
      Collection returnList = new ArrayList();
      if (selectedItem.countChilds() == 0)
         returnList.add(selectedItem);
      else {
         List toExplore = new ArrayList();
         toExplore.add(selectedItem);
         while (!toExplore.isEmpty()) {
            toExplore.addAll(ItemUtils.pupulate((Item) toExplore.get(0), returnList));
            toExplore.remove(0);
         }
      }
      return returnList;
   }

}
