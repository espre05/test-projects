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

import net.della.mplatform.core.datatypes.Item;

import della.swaf.extensions.util.glazedlists.CustomFilter;

public class TagSelectionFilter extends CustomFilter {

	private String selectedTag;

	public TagSelectionFilter(String text) {
		selectedTag = text;
	}

	public boolean filterMatches(Object element) {
		Item item = (Item) element;

		Collection tags = (Collection) item.get(TagsExtension.TAGS);
		if (tags == null)
			return false;
		if (tags.contains(selectedTag))
			return true;
		return false;

	}

}
