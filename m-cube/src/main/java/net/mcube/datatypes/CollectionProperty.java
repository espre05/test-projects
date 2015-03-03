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

import java.util.Collection;

import net.della.mplatform.application.datatypes.Property;

public abstract class CollectionProperty implements Property {

   private Collection collection;

   public Collection getValue() {
      return collection;
   }

   /**
    * value will be added to the collection in the right way, despite it is a
    * Collection or a generic Object
    */
   public void setValue(Object value) {
      if (collection == null)
         collection = createCollection();
      if (value instanceof Collection) {
         collection.addAll((Collection) value);
         return;
      }
      collection.add(value);
   }

   public void removeValue(Object value) {
      if (value instanceof Collection) {
         collection.removeAll((Collection) value);
         return;
      }
      collection.remove(value);
   }

   protected abstract Collection createCollection();

}
