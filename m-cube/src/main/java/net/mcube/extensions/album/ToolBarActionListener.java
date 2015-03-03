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
 * Created on 30-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.album;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.AbstractButton;

import net.della.mplatform.application.datatypes.FileItemAttributes;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.extensions.songs.comparators.ComparatorFactory;
import ca.odell.glazedlists.impl.sort.ReverseComparator;
import della.swaf.extensions.gui.TableView;

public final class ToolBarActionListener implements ActionListener {

    private TableView view;

    public ToolBarActionListener(TableView view) {

        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        // Handle each button.
        if (SongAttributes.YEAR.equals(cmd)) {
            toggleSorting(e, SongAttributes.YEAR);
        } else if (FileItemAttributes.DATE_ADDED.equals(cmd)) {
            toggleSorting(e, AlbumAttributes.DATE_ADDED);
        } else if (SongAttributes.ARTIST.equals(cmd)) {
            toggleSorting(e, SongAttributes.USEFUL_ARTIST);
        } else if (AlbumExtension.SHOW_EXTENDED_PANEL.equals(cmd)) {
            AlbumView albumView = (AlbumView) view;
            albumView.toggleExtendedPanel();
        }
    }

    private void toggleSorting(ActionEvent e, String sortBy) {
        AbstractButton source = (AbstractButton) e.getSource();
        Comparator comparator = ComparatorFactory.getInstance().createSongSetComparator(sortBy);
        if (source.isSelected()) {
            view.changeComparator(comparator);
        } else
            view.changeComparator(new ReverseComparator(comparator));
        source.setSelected(!source.isSelected());
        view.repaint();
    }
}