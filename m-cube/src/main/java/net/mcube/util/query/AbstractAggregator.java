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

package net.mcube.util.query;

import java.util.*;

import net.della.mplatform.application.datatypes.Item;

public abstract class AbstractAggregator implements Aggregator {

   private Map map;

   private String groupByAttribute;

   public AbstractAggregator(String groupByAttribute) {
      this.groupByAttribute = groupByAttribute;
   }

   public Map collectData(List sourceList) {
      map = new HashMap();
      for (Iterator it = sourceList.iterator(); it.hasNext();) {
         Item item = (Item) it.next();
         Object groupByValue = item.get(groupByAttribute);
         if (groupByValue != null)
            addItemToAggregates(groupByValue, item);
      }
      return map;
   }

   protected abstract void addItemToAggregates(Object groupByValue, Item item);

   protected void add(Object key, Item value) {
      Collection coll;
      if (map.containsKey(key)) {
         coll = (List) map.get(key);
      } else {
         coll = addEntry(key);
      }
      coll.add(value);
   }

   private Collection addEntry(Object groupByValue) {
      Collection newList = new LinkedList();
      map.put(groupByValue, newList);
      return newList;
   }

}
