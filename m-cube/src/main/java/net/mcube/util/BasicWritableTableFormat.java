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

package net.mcube.util;

import ca.odell.glazedlists.gui.WritableTableFormat;

/*
 * Created on 24-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code
 * and Comments
 */

public class BasicWritableTableFormat implements WritableTableFormat {

	private String title;

	public BasicWritableTableFormat(String title) {
		this.title = title;
	}

	public int getColumnCount() {
		return 1;
	}

	public String getColumnName(int column) {
		return title;
	}

	public Object getColumnValue(Object baseObject, int column) {
		return baseObject;
	}

    public boolean isEditable(Object arg0, int arg1) {
        return true;
    }

    public Object setColumnValue(Object arg0, Object arg1, int arg2) {
        // TODO Auto-generated method stub
        return null;
    }
}
