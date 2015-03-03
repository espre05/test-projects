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

package net.mcube.library.operations;

import java.util.ArrayList;
import java.util.List;

import net.della.mplatform.application.persistence.OldLibrary;
import net.della.mplatform.core.datatypes.Item;


public class RemoveItemsProxy {

	private OldLibrary library;
	private List itemsList;

	public RemoveItemsProxy(OldLibrary library) {
		this.library = library;
		itemsList = new ArrayList();
	}

	public void add(Item item) {
		itemsList.add(item);
	}

	public int run() {
//		int counter = 0;
//		for (Iterator it = itemsList.iterator(); it.hasNext();) {
//			Item item = (Item) it.next();
//			library.remove(item);
//			it.remove();
//			counter++;
//		}
//		return counter;
		library.removeAll(itemsList);
		return itemsList.size();
		
	}

}
