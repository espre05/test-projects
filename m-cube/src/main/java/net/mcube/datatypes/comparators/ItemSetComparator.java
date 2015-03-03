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

package net.mcube.datatypes.comparators;

import java.util.Comparator;

import net.della.mplatform.application.datatypes.ItemSet;


/*
 * Created on 27-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ItemSetComparator implements Comparator {

   private String compareField;
   private String compareField2;
   private String compareField3;

   public ItemSetComparator(String compareField) {
      this.compareField = compareField;
   }

   public ItemSetComparator(String compareField1, String compareField2) {
      this(compareField1);
      this.compareField2 = compareField2;
   }

   public ItemSetComparator(String compareField1, String compareField2, String compareField3) {
      this(compareField1, compareField2);
      this.compareField3 = compareField3;
   }

   public int compare(Object o1, Object o2) {
      ItemSet itemSet1 = (ItemSet) o1;
      ItemSet itemSet2 = (ItemSet) o2;

      int i = itemSet1.compare(itemSet2, compareField);
      if (i != 0)
         return i;
      if (compareField2 == null)
         return i;
      int j = itemSet1.compare(itemSet2, compareField2);
      ;
      if (j != 0)
         return j;
      if (compareField3 == null)
         return i;
      return itemSet1.compare(itemSet2, compareField3);

   }

   /**
    * 
    */

   public String toString() {
      return compareField;
   }

}
