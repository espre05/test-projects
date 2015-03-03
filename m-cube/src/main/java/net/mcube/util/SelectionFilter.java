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
 * Created on 15-gen-2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package net.mcube.util;

import java.util.List;

import della.swaf.extensions.util.glazedlists.CustomFilter;



public abstract class SelectionFilter extends CustomFilter {

	protected List selectionList;
	protected String fieldName;

	public SelectionFilter(String fieldName, List selectionList) {
		this.fieldName = fieldName;
        this.selectionList = selectionList;
	}

	public final void setSelectionList(List list) {
		selectionList = list;	
	}

}