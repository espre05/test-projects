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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SimpleXMLParser {

	public String getTagName(String string) {
		int beginIndex = string.indexOf('<');
		int endIndex = string.indexOf('>');
		return string.substring(beginIndex + 1, endIndex);
	}

	public String getValue(String string) {
		String tag = getTag(string);
		String closeTag = getCloseTag(string);
		String result = string.replaceAll(tag, "");
		result = result.replaceAll(closeTag, "");
		return result;

	}

	public String getTag(String string) {
		int beginIndex = string.indexOf('<');
		int endIndex = string.indexOf('>');
		String result = null;
		result = string.substring(beginIndex, endIndex + 1);
		// try {
		// } catch (StringIndexOutOfBoundsException e) {
		// }
		return result;
	}

	public String getCloseTag(String string) {
		String tag = getTag(string);
		String sub = tag.substring(1);
		int index = string.indexOf(sub, tag.length());
		int closeTagBeginIndex = index - 2;
		int closeTagEndIndex = closeTagBeginIndex + tag.length() + 1;
		return string.substring(closeTagBeginIndex, closeTagEndIndex);
	}

	public String getElement(String string) {
		String closeTag = getCloseTag(string);
		return string.substring(0, string.indexOf(closeTag) + closeTag.length());
	}

	public String getSecondElement(String string) {
		String element = getElement(string);
		String second = string.replaceAll(element, "");
		return getElement(second);
	}

	public Iterator getElements(String string) {
		string = string.trim();
		Collection col = new ArrayList();
		String element = getElement(string);
		while (!element.equals("")) {
			col.add(element);
//			string = string.replaceAll(element, "");
			string = string.substring(element.length(), string.length()).trim();
//			string = string.replace(new CharSequence(), element)
			if (string.equals(""))
				element = "";
			else
				element = getElement(string);
		}
		return col.iterator();
	}

	public String getElementAt(String xml, int i) {
		Iterator elements = getElements(xml);
		for (int j = 0; j < i - 1; j++) {
			elements.next();
		}
		return (String) elements.next();
	}

	public String getElement(String xml, String tag) {
		Iterator elements = getElements(xml);
		while (elements.hasNext()) {
			String element = (String) elements.next();
			String tagName = getTagName(element);
			if (tagName.equals(tag))
				return element;
		}
		return "";
	}

}
