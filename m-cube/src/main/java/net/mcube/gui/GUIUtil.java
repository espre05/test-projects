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

package net.mcube.gui;

import java.util.List;

import net.della.mplatform.application.datatypes.ItemSet;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.gui.structure.View;
import net.mcube.datatypes.DefaultItemSetBuilder;
import net.mcube.datatypes.ItemSetBuilder;
import net.mcube.util.query.GroupItemBuilderFactory;

public class GUIUtil {

   public static ObservableItem recoverSelectedItem(View focusedView, String mainProperty) {
      if (focusedView == null)
         return null;
      // TODO: insert next two lines
      // if (!focusedView.containsItem())
      // return null;
      List selection = focusedView.getSelection();
      if (selection.isEmpty())
         return null;

      if (selection.size() == 1) {
         Object selectedObject = selection.get(0);
         if (selectedObject instanceof ObservableItem)
            return (ObservableItem) selectedObject;
         return null;
      }

      Object selectedObject = selection.get(0);
      ItemSetBuilder builder;
      if (selectedObject instanceof ItemSet)
         builder = new DefaultItemSetBuilder();
      else if (selectedObject instanceof ObservableItem)
         builder = GroupItemBuilderFactory.getBuilder(mainProperty);
      else
         return null;
      return builder.createItemSet(selection, mainProperty, null);

   }

}
