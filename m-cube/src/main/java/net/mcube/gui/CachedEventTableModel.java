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

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.EventTableModel;

public class CachedEventTableModel extends EventTableModel {

	private int max;
	private EventList source;
			
	public CachedEventTableModel(EventList sortedList, TableFormat tableFormat) {
		super(sortedList, tableFormat);
		max = sortedList.size();
		source = sortedList;
	}
	
	public void listChanged(ListEvent listChanges) {	
		
//		if (source.size() == max) {
//			baseList.clear();
//			baseList.addAll(cachedList);
//			return;
//		}
		try {
//			LogFactory.getLog(this.getClass()).info(
//					name + " updates: " + " - " + Thread.currentThread().getName());
//			long start = System.currentTimeMillis();
			super.listChanged(listChanges);
//			long end = System.currentTimeMillis();
//			long time = end - start;
//			LogFactory.getLog(this.getClass()).info(name + ": time elapsed: " + time);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		if (source.size() > max) {
//			max = source.size();
//			cachedList = new BasicEventList();
//			cachedList.addAll(baseList);
//		}
	}

}
