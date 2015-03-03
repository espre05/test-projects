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

package net.della.stuff.generic.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ListParser {

	private String separator;

	public ListParser(String separator) {
		this.separator = separator;
	}

	public Collection parse(String s) {
		Class collectionType = HashSet.class;
		return parseList(s, collectionType);
	}

	public Collection parseList(String s, Class collectionType) {
		StringTokenizer tokenizer = new StringTokenizer(s, separator);		
		Collection coll = newCollection(collectionType);
		while (tokenizer.hasMoreElements()) {
			coll.add(tokenizer.nextToken().trim());
		}
		return coll;
	}

	public static Collection newCollection(Class collectionType) {
		Collection coll = null;
		try {
			coll= (Collection) collectionType.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return coll;
	}

}
