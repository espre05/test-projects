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

import static org.junit.Assert.*;

import java.util.ArrayList;

import net.della.stuff.generic.lang.ListComparator;

import org.junit.Test;

public class ListComparatorTest {
	
	@Test
	public void detectDifferentLenghtLists() {
		String[] first = new String[] {"a"};
		String[] second = new String[] {"a"};
		ArrayList firstList = new ArrayList();
		ArrayList secondList = new ArrayList();
		firstList.add(first);
		secondList.add(second);
		assertTrue(ListComparator.match(firstList, secondList));

		secondList.add(second);
		assertFalse(ListComparator.match(firstList, secondList));
	}
	
	@Test
	public void testMatchListsWithOnlyOneArray() throws Exception {
		String[] first = new String[] { "a", "b"};
		String[] second = new String[] { "b", "a"};
		ArrayList firstList = new ArrayList();
		ArrayList secondList = new ArrayList();
		firstList.add(first);
		secondList.add(second);

		assertFalse(ListComparator.match(firstList, secondList));
		
		
		second[0] = "a";
		second[1] = "b";
		assertTrue(ListComparator.match(firstList, secondList));
	}
	
	@Test
	public void testMatchListsWithManyArrays() throws Exception {
		String[] first = new String[] { "a", "b"};
		String[] second = new String[] { "c", "d"};
		ArrayList firstList = new ArrayList();
		ArrayList secondList = new ArrayList();
		firstList.add(first);
		firstList.add(second);
		
		secondList.add(second);
		secondList.add(first);

		assertTrue(ListComparator.match(firstList, secondList));

	}
}
