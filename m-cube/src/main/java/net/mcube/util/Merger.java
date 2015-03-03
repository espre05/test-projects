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

package net.mcube.util;

import java.util.Iterator;
import java.util.List;

import net.della.stuff.generic.util.StringListUtil;


public class Merger {

   public String pickMaxValueFor(List list) {
      int maxValueFound = 0;
      for (Iterator it = list.iterator(); it.hasNext();) {
         String valueAsString = (String) it.next();
         if (!valueAsString.equals("")) {
            int valueInt = Integer.parseInt(valueAsString);
            if (valueInt > maxValueFound)
               maxValueFound = valueInt;
         }
      }
      return maxValueFound + "";
   }

   public String chooseRightValueFor(List list, String defaultValue) {
      String value = "";
      if (list.size() == 0)
         return value;
      if (StringListUtil.areAllElementsEmpty(list))
         return defaultValue;
      if (StringListUtil.haveAllElementsTheSameValue(list))
         value = (String) list.get(0);
      return value;
   }

   public String pickOlderDateAddedFor(List list) {
      return (String) list.get(0);
   }

   public String putAllStringFrom(List list) {
      // TODO: optimize: insert any string just one times
      return list.toString();
   }

}
