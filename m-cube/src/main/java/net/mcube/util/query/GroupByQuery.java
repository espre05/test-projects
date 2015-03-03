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

/*
 * Created on 29-gen-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package net.mcube.util.query;

import java.util.*;

import net.della.mplatform.application.datatypes.Item;
import net.mcube.datatypes.ItemSetBuilder;
import ca.odell.glazedlists.EventList;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class GroupByQuery extends Query {

   private String groupByAttribute;

   private final Aggregator aggregator;

   public GroupByQuery(String groupByParameter) {
      this(groupByParameter, new StringAggregator(groupByParameter));
   }

   public GroupByQuery(String groupByParameter, Aggregator aggregator) {
      this.aggregator = aggregator;
      this.groupByAttribute = groupByParameter;
   }

   public void execute(EventList baseList) {
      try {
         executImpl(baseList);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void executImpl(EventList baseList) {
      Map map = aggregator.collectData(getSource());

      List newList = new LinkedList();
      for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
         String key = (String) iter.next();
         List aggregateList = (List) map.get(key);
         newList.add(createGroupItem(key, aggregateList));
      }
      /*
       * this is an optimizations due to the fact that baseList is an EventList
       * and propagate it's changes to all it's listeners So it is better to add
       * all items in one single shot
       */
      baseList.clear();
      baseList.addAll(newList);
   }

   private Object createGroupItem(String key, List aggregateList) {
      ItemSetBuilder groupItemBuilder = GroupItemBuilderFactory.getBuilder(groupByAttribute);
      Item groupItem = groupItemBuilder.createItemSet(aggregateList, groupByAttribute, key);
      return groupItem;
   }
}