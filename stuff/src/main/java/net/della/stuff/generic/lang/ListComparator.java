/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.stuff.generic.lang;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListComparator {

	
	public static boolean match(List<String[]> first, List<String[]> second) {
		boolean sameSize = first.size() == second.size();
		if (!sameSize) {
			return false;
		}
		for (Iterator iter = first.iterator(); iter.hasNext();) {
			String[] actual = (String[]) iter.next();
			if (!contains(second, actual)) {
				return false;
			}
		}
		return true;
	}

	public static boolean contains(List<String[]> struct, String[] element) {
		for (Iterator iter = struct.iterator(); iter.hasNext();) {
			String[] oneElement = (String[]) iter.next();
			if (match(oneElement, element))
				return true;
		}
		return false;
	}

	public static boolean match(String[] first, String[] second) {
		return Arrays.deepEquals(first, second) ;
	}

}
