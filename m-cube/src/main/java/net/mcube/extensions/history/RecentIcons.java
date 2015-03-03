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
 * Created on 17-ago-2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

package net.mcube.extensions.history;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.AbstractButton;

import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.gui.renderer.CellDecorator;
import net.della.mplatform.gui.renderer.DataRenderer;
import net.mcube.extensions.album.AlbumAttributes;
import net.mcube.util.ImageIconScalable;
import della.swaf.extensions.gui.TableView;

public final class RecentIcons {

    private CellDecorator decorator;

    private TableView view;

    private AbstractButton button;

    private boolean decorationEnabled;

    private static String SHOW_ICONS_REGKEY = "show icons";

    public RecentIcons(AbstractButton button, TableView view) {
        this.button = button;
        button.setText("Show History Icons");

        this.view = view;
//        view = (TableView) RuntimeEnvironment.getCurrentApplication().getWindow().getView(AlbumExtension.VIEW_ID);
        decorator = new CellDecorator() {

            public void decore(DataRenderer panel, int row) {
                applyDecoration(panel, row);
            }
        };
        view.addCellRendererDecorator(decorator);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleShowIcons();
            }
        });
        
        Preferences prefs = Preferences.userNodeForPackage(getClass());        
        button.setSelected(prefs.getBoolean(SHOW_ICONS_REGKEY, false));
        toggleShowIcons();
    }

    private void toggleShowIcons() {
        if (button.isSelected()) {
            decorationEnabled = true;
            view.repaint();
        } else {
            decorationEnabled = false;
            view.repaint();
        }
        makeChangePersistent(button.isSelected());
    }
    
    private void makeChangePersistent(boolean selected) {
        Preferences prefs = Preferences.userNodeForPackage(getClass());        
        prefs.putBoolean(SHOW_ICONS_REGKEY, selected);
    }

    void applyDecoration(DataRenderer panel, int row) {
        if (!decorationEnabled) {
            removeIcon(panel);
            return;
        }        
        ObservableItem item = view.getElementAtRow(row);
        if (History.howManyWeeksAgoHasBeenAdded(item) < 1) {
            applyIcon(panel, "./resources/RedCircle.gif", "Added on " + item.getString(AlbumAttributes.DATE_ADDED));
            return;
        } else if (History.howManyWeeksAgoHasBeenAdded(item) < 4) {
            applyIcon(panel, "./resources/YellowCircle.gif", "Added on " + item.getString(AlbumAttributes.DATE_ADDED));
            return;
        }
        removeIcon(panel);
    }

    private void removeIcon(DataRenderer panel) {
        panel.removeIcon("History");
    }

    private void applyIcon(DataRenderer renderer, String imageFilename, String toolTipText) {
        ImageIconScalable icon = new ImageIconScalable(imageFilename);
        icon.setScaledSize(10, 10);
        renderer.setIcon("History", icon, "Added on");
    }

}