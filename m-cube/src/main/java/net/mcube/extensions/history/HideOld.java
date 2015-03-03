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
 * Created on 31-ott-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.history;

import java.util.prefs.Preferences;

import javax.swing.AbstractButton;

import net.della.mplatform.application.datatypes.ObservableItem;
import della.swaf.extensions.gui.AbstractCellListView;
import della.swaf.extensions.util.glazedlists.CustomFilter;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class HideOld {

    private static final String RANGE_REG_KEY = "oldness";

    private CustomFilter filter;

    private int range;

    private AbstractButton hideButton;

    private final AbstractCellListView view;

    private Preferences prefs;

    private static final String HIDE_OLD_REG_KEY = "hide old";

    public HideOld(AbstractButton button, AbstractCellListView view) {
        this.hideButton = button;
        this.view = view;
        filter = new CustomFilter() {

            public boolean filterMatches(Object element) {
                if (range == 0)
                    return true;
                ObservableItem item = (ObservableItem) element;
                return History.howManyWeeksAgoHasBeenAdded(item) < range;
            }
        };
//        view = (AbstractListView) RuntimeEnvironment.getCurrentApplication().getWindow().getView(
//                AlbumExtension.VIEW_ID);

        prefs = Preferences.userNodeForPackage(getClass());
        range = prefs.getInt(RANGE_REG_KEY, 0);
        setRange(range);

        // hideButton.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // toggleFilter();
        // prefs.putBoolean(HIDE_OLD_REG_KEY, hideButton.isSelected());
        // }
        // });

        // hideButton.setSelected(prefs.getBoolean(HIDE_OLD_REG_KEY, false));
        // toggleFilter();

    }

    void setRange(int newRange) {
        this.range = newRange;

        updateButtonUI(newRange);
        // if (hideButton.isSelected())
        updateFiltering();
        prefs.putInt(RANGE_REG_KEY, newRange);
    }

    private void updateFiltering() {
        view.removeCustomFilter(filter);
        view.addCustomFilter(filter);
    }

    private void updateButtonUI(int newRange) {
        String buttonText = "Show Last " + newRange + " ";

        switch (newRange) {
        case 0:
            buttonText = "Show All";
            break;
        case 1:
            buttonText += "Week";
            break;
        default:
            buttonText += "Weeks";
            break;
        }

        hideButton.setText(buttonText);
    }

    private void toggleFilter() {
        if (hideButton.isSelected())
            view.addCustomFilter(filter);
        else
            view.removeCustomFilter(filter);
    }

    public void _removeFilter() {
        view.removeCustomFilter(filter);
        prefs.putInt(RANGE_REG_KEY, 0);
    }

    public int getRange() {
        return range;
    }
}