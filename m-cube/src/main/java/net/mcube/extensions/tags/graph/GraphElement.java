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

package net.mcube.extensions.tags.graph;

import java.util.List;

import della.application.datatypes.Item;
import della.application.datatypes.ItemSet;
import della.application.datatypes.ObservableItem;

public class GraphElement extends ItemSet {

	static final String NAME = "name";

	// private String name;
	// private Collection links;
	static public final String NUMBER_OF_RELATED_ITEMS = "number of related items";

	private ObservableItem ancestorItem;

	private GraphElement() {
		// links = new ArrayList();
		setMainAttribute(NAME);
	}

	public void setName(String name) {
		// this.name = name;
		put(NAME, name);
	}

	public String getName() {
		return getString(NAME);
	}

	public void addLink(Item fighterItem) {
		// links.add(fighterItem);
		super.addItem(fighterItem);
	}

	static GraphElement newGraphElement(ObservableItem item) {
		GraphElement graphElement = new GraphElement();
		graphElement.setAncestorItem(item);
		graphElement.setName((String) item.get(item.getMainAttribute()));
		graphElement.setChildNumber(item.countChilds());
		return graphElement;
	}

	private void setChildNumber(int i) {
		put(NUMBER_OF_RELATED_ITEMS, i + "");
	}

	public List getLinks() {
		return listChilds();
	}

	private void setAncestorItem(ObservableItem item) {
		this.ancestorItem = item;
	}

	public ObservableItem getAncestorItem() {
		return ancestorItem;
	}

}
