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
 * Created on 1-giu-2005 
 * 
 */
package net.mcube.gui;

import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.mcube.util.SelectionFilter;
import della.swaf.extensions.gui.AbstractCellListView;

public final class ApplyFilterListSelectionListener implements ListSelectionListener {
    private final List selectionList;

    private final SelectionFilter songSelectionFilter;

    private final AbstractCellListView view;

    public ApplyFilterListSelectionListener(AbstractCellListView targetView, List selectionList, SelectionFilter filter) {
        super();
        this.selectionList = selectionList;
        this.songSelectionFilter = filter;
        this.view = targetView;
    }

    public void valueChanged(ListSelectionEvent e) {
        if (selectionList.size() == 0) {
            view.removeCustomFilter(songSelectionFilter);
            return;
        }
        view.removeCustomFilter(songSelectionFilter);
        view.addCustomFilter(songSelectionFilter);
    }
}