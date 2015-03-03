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

import java.awt.Color;
import java.awt.Font;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;

import net.mcube.extensions.tags.graph.GraphElement;
import della.swaf.extensions.util.glazedlists.CustomFilter;

public class CosmosComponent {

	private final GraphElement graphElement;

	private final CustomFilter tagSelectionFilter;

	private float baseSize;

	private final JLabel label;

	private final CustomFilter tagExclusionFilter;

	private CosmosComponent(GraphElement graphElement, JLabel label) {
		this.graphElement = graphElement;
		this.label = label;
		baseSize = 18;
		tagSelectionFilter = new TagSelectionFilter(graphElement.getName());
		tagExclusionFilter = new ReverseFilter(tagSelectionFilter);
	}

	public static CosmosComponent newComponent(GraphElement graphElement, JLabel label) {
		CosmosComponent cosmosComponent = new CosmosComponent(graphElement, label);
		label.setText(graphElement.getName());
		return cosmosComponent;

	}

	public GraphElement getItem() {
		return graphElement;
	}

	public Collection getRelated() {
		return graphElement.getLinks();
	}

	public void switchTo(String newStatus) {
		if (TagCosmos.SELECTED.equals(newStatus)) {
			label.setForeground(Color.BLUE.darker());
			Font font = label.getFont();
			label.setFont(font.deriveFont(font.getStyle(), baseSize * 3f));
		} else if (TagCosmos.FRIEND.equals(newStatus)) {
			label.setForeground(Color.GREEN.darker());
			Font font = label.getFont();
			label.setFont(font.deriveFont(font.getStyle(), baseSize * 1.5f));
		} else if (TagCosmos.NORMAL.equals(newStatus)) {
			label.setForeground(Color.BLACK);
			Font font = label.getFont();
			label.setFont(font.deriveFont(font.getStyle(), baseSize * 1f));
		} else if (TagCosmos.EXCLUDED.equals(newStatus)) {
			label.setForeground(Color.RED.darker());
			Font font = label.getFont();
			label.setFont(font.deriveFont(font.getStyle(), baseSize * 0.6f));
		}
	}

	public CustomFilter getFilter() {
		return tagSelectionFilter;
	}
	
	public CustomFilter getExclusionFilter() {
		return tagExclusionFilter;
	}

	public String[] getRelatedIds() {
		List links = graphElement.getLinks();
		String[] relatedIds = new String[links.size()];
		int i = 0;
		for (Iterator it = links.iterator(); it.hasNext();) {
			GraphElement element = (GraphElement) it.next();
			relatedIds[i++] = element.getName();
		}
		return relatedIds;
	}

}
