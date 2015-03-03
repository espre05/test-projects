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

/**
 * 
 */
package net.mcube.util.query;

import java.util.Collection;
import java.util.Iterator;

import net.della.mplatform.application.datatypes.Item;

public class CollectionAggregator extends AbstractAggregator {

   public CollectionAggregator(String groupByAttribute) {
      super(groupByAttribute);
   }

   protected void addItemToAggregates(Object keys, Item item) {
      Collection coll = (Collection) keys;
      for (Iterator it = coll.iterator(); it.hasNext();) {
         Object key = it.next();
         addSingleItem(key, item);
      }
   }

   protected void addSingleItem(Object key, Item item) {
      add(key, item);
   }
}