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
 * Created on 30-ott-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.history;

import java.awt.event.ActionEvent;

import javax.swing.*;

import net.mcube.extensions.album.AlbumAttributes;
import net.mcube.extensions.album.AlbumExtension;
import della.application.application.Application;
import della.application.application.Extension;
import della.application.datatypes.ObservableItem;
import della.application.gui.ApplicationWindow;
import della.application.gui.Page;
import della.application.gui.View;
import della.swaf.extensions.gui.TableView;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class History implements Extension  {

    public static final long millisecInOneWeek = 604800000;

    public static final long millisecInTwoWeeks = millisecInOneWeek * 2;

    public static final long millisecInFourWeeks = millisecInTwoWeeks * 2;

    public static final long millisecInEightWeeks = millisecInFourWeeks * 2;

	private Application application;

	private TableView view;

    public History() {

    }
    
    public void init(Application application) {    
        this.application = application;
        ApplicationWindow window = application.getWindow();
        view = (TableView) window.getView(AlbumExtension.VIEW_ID);
		//        view.addToolbarButton(createConfigurationButton());
        view.addToPopup(AlbumExtension.CONFIG_MENU, createShowIconsButtons());
        view.addToPopup(AlbumExtension.CONFIG_MENU, createHideOlderButton());
        // window.getTopPanel().add(createShowIconsButtons());
        // window.getTopPanel().add(createHideOlderButton());
    }

    private AbstractButton createShowIconsButtons() {
        AbstractButton showIconsButton = new JCheckBoxMenuItem();
        RecentIcons recentIcons = new RecentIcons(showIconsButton, (TableView) application.getWindow().getView(AlbumExtension.VIEW_ID));
        return showIconsButton;
    }

    private AbstractButton createHideOlderButton() {
        JMenu hideOlderMenu = new JMenu();
        // hideOlderButton.setToolTipText("Right click to change Recent
        // Options");

        HideOld hideOld = new HideOld(hideOlderMenu, view);

        JPopupMenu popup = new JPopupMenu();
        ButtonGroup group = new ButtonGroup();

        hideOlderMenu.add(createGroupPopupItem(hideOld, popup, group, 0));
        hideOlderMenu.addSeparator();
        hideOlderMenu.add(createGroupPopupItem(hideOld, popup, group, 1));
        hideOlderMenu.add(createGroupPopupItem(hideOld, popup, group, 2));
        hideOlderMenu.add(createGroupPopupItem(hideOld, popup, group, 4));
        hideOlderMenu.add(createGroupPopupItem(hideOld, popup, group, 8));
        hideOlderMenu.add(createGroupPopupItem(hideOld, popup, group, 16));

        // hideOlderButton.addMouseListener(new PopupListener(popup));

        return hideOlderMenu;
    }

    private AbstractButton createGroupPopupItem(final HideOld hideOld, JPopupMenu popup,
            ButtonGroup group, final int range) {
        final AbstractButton menuItem = new JCheckBoxMenuItem();
        menuItem.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                hideOld.setRange(range);
            }
        });
        menuItem.setText(range + " Week");
        if (range == 0)
            menuItem.setText("Show All");
        group.add(menuItem);
        popup.add(menuItem);
        return menuItem;
    }

    public String getID() {
        return "History";
    }

    public View getView() {
        // TODO Auto-generated method stub
        return null;
    }

    public Page getPage() {
        // TODO Auto-generated method stub
        return null;
    }

    static int howManyWeeksAgoHasBeenAdded(ObservableItem item) {
        String dateAdded = item.getString(AlbumAttributes.DATE_ADDED);
        long dateAddedMillis = Long.parseLong(dateAdded);
        long weeksAgoInMillis = System.currentTimeMillis() - dateAddedMillis;
        return (int) (weeksAgoInMillis / (1000 * 60 * 60 * 24 * 7));
    }

}