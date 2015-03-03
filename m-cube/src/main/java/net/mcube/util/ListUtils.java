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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUtils {

	public static List createPopulatedList(String baseContent) {
		List baseList = new ArrayList();
		ListUtils.populateList(baseList, baseContent);
		return baseList;
	}

	public static void populateList(List baseList, String baseContent) {
		for (int i = 0; i < 100; i++) {
			baseList.add(baseContent + i);
		}
	}

	public static void print(List list) {
		for (Iterator it = list.iterator(); it.hasNext();) {
			System.out.println("elem: " + it.next());
		}
	}

	public static String toString(List<String> avatarsText) {
		StringBuffer sb = new StringBuffer();
		for (Iterator it = avatarsText.iterator(); it.hasNext();) {
			sb .append((String) it.next());
			sb .append(", ");
		}
		return sb.toString();
	}

}
