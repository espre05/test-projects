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

package net.mcube.datatypes;

import java.util.*;

import net.della.mplatform.application.datatypes.ItemSet;
import net.della.mplatform.application.datatypes.ObservableItem;

public class DefaultItemSetBuilder implements ItemSetBuilder {

   private final List decorators;

   public DefaultItemSetBuilder() {
      decorators = new ArrayList();
   }

   public final ObservableItem createItemSet(List list, String mainAttribute, String name) {
      ItemSet itemSet = createItemSet();
      itemSet.setMainAttribute(mainAttribute);
      itemSet.put(mainAttribute, name);
      itemSet.addAll(list);
      buildItemSet(itemSet);
      return itemSet;
   }

   protected void buildItemSet(ItemSet itemSet) {
      try {
         applyDecorations(itemSet);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void applyDecorations(ItemSet itemSet) {
      for (Iterator it = decorators.iterator(); it.hasNext();) {
         BuilderDecorator decorator = (BuilderDecorator) it.next();
         decorator.apply(itemSet);
      }
   }

   protected ItemSet createItemSet() {
      return new ItemSet();
   }

   public boolean add(BuilderDecorator o) {
      return decorators.add(o);
   }

   public boolean remove(BuilderDecorator o) {
      return decorators.remove(o);
   }

   public void buildItemSet(ItemSet itemSet, Map albumProps) {
      try {
         applyDecorations(itemSet, albumProps);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void applyDecorations(ItemSet itemSet, Map props) {
      for (Iterator it = decorators.iterator(); it.hasNext();) {
         BuilderDecorator decorator = (BuilderDecorator) it.next();
         decorator.apply(itemSet, props);
      }
   }

}
