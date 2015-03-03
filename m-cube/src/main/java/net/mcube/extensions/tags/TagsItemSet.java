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

package net.mcube.extensions.tags;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import net.della.mplatform.core.datatypes.Item;
import net.della.mplatform.core.datatypes.ItemSet;


public class TagsItemSet extends ItemSet {
	
	public void update() {
		Iterator it = listChilds().iterator();
		while (it.hasNext()) {
			Item item = (Item) it.next();
			String bindProperty = TagsExtension.TAGS;
			Collection tagsToAdd = (Collection) this.get(bindProperty);
//			item.setData(bindProperty, tagsToAdd);
			
			Collection tags = (Collection) item.get(bindProperty);			
			if (tags == null) {
				tags = new HashSet();
				item.put(bindProperty, tags);
			}
			tags.addAll(tagsToAdd);
			//TODO: this is better
//			ItemUtils.addToCollection(item, bindProperty, tagsToAdd);
		}
	}

}
