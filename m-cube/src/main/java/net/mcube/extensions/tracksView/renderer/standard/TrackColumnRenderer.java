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

/*
 * Created on 30-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

package net.mcube.extensions.tracksView.renderer.standard;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrackColumnRenderer extends DefaultTableCellRenderer {

	private final StringBuffer displayed;

	
	public TrackColumnRenderer() {
		displayed = new StringBuffer();
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
			   	    
		displayed.setLength(0);
		displayed.append((String) value);		
		
		if (displayed.length() > 0) {
			if (displayed.charAt(0) == '0')
				displayed.replace(0, 1, "");
		} else
			displayed.append(" - ");
		
		Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		setValue(displayed.toString());
		//setBackground(UIManager.getColor("Table.background"));
		return renderer;
		
	}
}