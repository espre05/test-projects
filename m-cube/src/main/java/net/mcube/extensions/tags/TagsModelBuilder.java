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

/**
 * 
 */
package net.mcube.extensions.tags;

import java.util.Collection;
import java.util.Iterator;

import net.della.mplatform.core.datatypes.Item;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;


public final class TagsModelBuilder implements ModelBuilder {
	private final String bindProperty;

	TagsModelBuilder(String bindProperty) {
		super();
		this.bindProperty = bindProperty;
	}

	public Model createModel(Object object) {
		Item item = (Item) object;
		TagsModel model = new TagsModel(bindProperty);
		model.setTags(parseCollection(item.get(bindProperty)));
		return model;
	}

	public PresentationModel createPresentationModel(Model model) {
		return new PresentationModel(model);
	}
	
	private String parseCollection(Object object) {
		Collection tagsVector = (Collection) object;
		if (tagsVector == null)
			return "";
		StringBuffer sb = new StringBuffer();
		for (Iterator it = tagsVector.iterator(); it.hasNext();) {
			String s = (String) it.next();
			sb.append(s);
			sb.append(", ");
		}
		return sb.toString();
	}
}