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

package net.mcube.util.xml;

import java.util.Iterator;

import junit.framework.TestCase;

public class SimpleXMLParserTest extends TestCase {

	private SimpleXMLParser parser;

	protected void setUp() throws Exception {
		super.setUp();
		parser = new SimpleXMLParser();
	}

	public void testGetTagName() throws Exception {
		String tagName = parser.getTagName("<tag>");
		assertEquals("tag", tagName);
		tagName = parser.getTagName("<tag>testo</tag>");
		assertEquals("tag", tagName);
	}

	public void testGetTag() throws Exception {
		String tag = parser.getTag("<tag>testo</tag>");
		assertEquals("<tag>", tag);
	}

	public void testGetCloseTag() throws Exception {
		String closeTag = parser.getCloseTag("<tag>testo</tag>");
		assertEquals("</tag>", closeTag);
		closeTag = parser.getCloseTag("<tag>testo</tag><pippo>testo</pippo>");
		assertEquals("</tag>", closeTag);
	}

	public void testGetValue() throws Exception {
		String value = parser.getValue("<tag>testo</tag>");
		assertEquals("testo", value);
		value = parser.getValue("<albumID>235401</albumID>");
		assertEquals("235401", value);
	}

	public void testGetElement() throws Exception {
		String element = parser.getElement("<tag>testo</tag><tag>testo</tag>");
		assertEquals("<tag>testo</tag>", element);
	}
	
	public void testGetSecondElement() throws Exception {
		String element = parser.getSecondElement("<tag>testo</tag><pippo>testopippo</pippo>");
		assertEquals("<pippo>testopippo</pippo>", element);
		element = parser.getSecondElement("<tag>testo</tag><pippo>testopippo</pippo><terzo>terzotesto</terzo>");
		assertEquals("<pippo>testopippo</pippo>", element);
	}
	
	public void testGetElementAt() throws Exception {
		String string = "<tag>testo</tag><pippo>testopippo</pippo><terzo>terzotesto</terzo>";
		String element = parser.getElementAt(string, 2);
		assertEquals("<pippo>testopippo</pippo>", element);		
	}
	
	public void testGetElements() throws Exception {
		Iterator elements = parser.getElements("<tag>testo</tag><pippo>testopippo</pippo><terzo>terzotesto</terzo>");
		assertTrue(elements.hasNext());
		elements.next();
		assertTrue(elements.hasNext());
		elements.next();
		assertTrue(elements.hasNext());
		elements.next();
		assertFalse(elements.hasNext());
	}
	
	public void testGetElementByTag() throws Exception {
		String string = "<tag>testo</tag><pippo>testopippo</pippo><terzo>terzotesto</terzo>";
		String element = parser.getElement(string, "pippo");
		assertEquals("<pippo>testopippo</pippo>", element);
		
		element = parser.getElement(string, "terzo");
		assertEquals("<terzo>terzotesto</terzo>", element);
		
		element = parser.getElement(string, "mimmo");
		assertEquals("", element);		
	}
}
