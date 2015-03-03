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

public class CommonsUtil {

	public static String extractFirstWordFrom(String title) {
		int indexApix = title.indexOf("'");
		indexApix = (indexApix == -1) ? title.length() : indexApix;
		int indexBlank = title.indexOf(" ");
		indexBlank = (indexBlank == -1) ? title.length() : indexBlank;
	
		return title.substring(0, Math.min(indexApix, indexBlank));
	}

}
