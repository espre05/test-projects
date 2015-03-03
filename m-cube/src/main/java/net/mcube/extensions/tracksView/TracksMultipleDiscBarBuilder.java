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
 * Created on 15-feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.tracksView;

import javax.swing.AbstractButton;
import javax.swing.JToolBar;

import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.util.WidgetFactory;
import net.della.stuff.generic.util.Command;
import net.della.stuff.gui.swing.util.ToolbarMediator;
import net.mcube.extensions.songs.SongAttributes;
import della.swaf.extensions.gui.AbstractCellListView;
import della.swaf.extensions.util.glazedlists.CustomFilter;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
class TracksMultipleDiscBarBuilder {

    private final JToolBar albumTypeBar;

    private AbstractCellListView view;

    private AlbumTypeFilter filter;

    private ToolbarMediator mediator;

    TracksMultipleDiscBarBuilder(AbstractCellListView view) {
        this.view = view;
        filter = new AlbumTypeFilter();

        albumTypeBar = WidgetFactory.buildToolBar();
        mediator = new ToolbarMediator(albumTypeBar, true);

    }

    JToolBar updateBarButtons(int buttonNumber) {
        mediator.removeAll();

        String filterString = "";
        mediator.addButton(WidgetFactory.createToggleToolBarButton("", filterString, "", "All"),
                new FilterCommand(filterString));
        for (int i = 1; i <= buttonNumber; i++) {
            filterString = i + "";
            mediator.addButton(WidgetFactory.createToggleToolBarButton("", filterString, "",
                    "Disc " + i), new FilterCommand(filterString));
        }

        return albumTypeBar;
    }

    private final class FilterCommand implements Command {

        private String filter;

        public FilterCommand(String filter) {
            this.filter = filter;
        }

        public Object run() {
            applyFilter(filter);
            return null;
        }
    }

    private final class AlbumTypeFilter extends CustomFilter {

        private String actionCommand;

        public void setActionCommand(String actionCommand) {
            this.actionCommand = actionCommand;
        }

        public boolean filterMatches(Object element) {
            if (actionCommand.equals(""))
                return true;
            ObservableItem item = (ObservableItem) element;
            if (item.getString(SongAttributes.DISC).equals(actionCommand))
                return true;
            return false;
        }
    }

    public void setVisible(boolean b) {
        albumTypeBar.setVisible(b);
        if (!b)
            view.removeCustomFilter(filter);
        else {
            AbstractButton button = (AbstractButton) albumTypeBar.getComponent(2);
            button.setSelected(true);
            applyFilter(button.getActionCommand());
        }
    }

    private void applyFilter(final String actionCommand) {
        filter.setActionCommand(actionCommand);
        view.removeCustomFilter(filter, false);
        view.addCustomFilter(filter);
    }

}
