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

import java.io.File;
import java.util.Map;

import net.della.mplatform.application.datatypes.FileItemAttributes;
import net.della.mplatform.application.datatypes.Item;
import net.della.mplatform.application.persistence.Context;

public class ItemBuilderFacade {

   public static Item createItemFromProperties(Map attrs, Context context) {
      DefaultItemBuilder builder = ItemBuilderFactory.getInstance().getBuilderFor(
            new File((String) attrs.get(FileItemAttributes.PATH)));
      ItemCreatorDirector director = new ItemCreatorDirector(builder, context);
      Item item = director.construct(attrs);
      // LogFactory.getLog("mcube").info(item);
      return item;
   }
}
