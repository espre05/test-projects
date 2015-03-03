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
package net.mcube.extensions.album;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JToolBar;

import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.util.WidgetFactory;
import net.mcube.extensions.songs.DefaultSongBuilder;
import net.mcube.extensions.songs.SongAttributes;
import della.swaf.extensions.gui.AbstractCellListView;
import della.swaf.extensions.util.glazedlists.CustomFilter;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
class AlbumTypeBarBuilder {

	private final ButtonGroup group;

	private final JToolBar albumTypeBar;

	private final AlbumTypeToolbarActionListener albumTypeToolbarActionListener;

	private AbstractCellListView view;

	AlbumTypeFilter filter;

	private Map searchTextMap;

	public static final String EP_SINGLE = "EP/Single";

	AlbumTypeBarBuilder(AbstractCellListView view) {
		this.view = view;
		group = new ButtonGroup();
		filter = new AlbumTypeFilter();
		searchTextMap = new HashMap();
		initSearchTextMap();
		albumTypeBar = WidgetFactory.buildToolBar();
		albumTypeToolbarActionListener = new AlbumTypeToolbarActionListener();
	}

	JToolBar createAlbumTypeSelectionToolbar() {
		addButtonToToolbar(WidgetFactory.createToggleToolBarButton("", "", "", "All"));
		addButtonToToolbar(WidgetFactory.createToggleToolBarButton("", DefaultSongBuilder.ALBUM,
				"", "Albums"));
		addButtonToToolbar(WidgetFactory.createToggleToolBarButton("", DefaultSongBuilder.LIVE, "",
				"Live"));

		addButtonToToolbar(WidgetFactory.createToggleToolBarButton("",
				AlbumTypeBarBuilder.EP_SINGLE, "", "EPs/Singles"));
		addButtonToToolbar(WidgetFactory.createToggleToolBarButton("",
				DefaultSongBuilder.COMPILATION, "", "Compilations"));
		addButtonToToolbar(WidgetFactory.createToggleToolBarButton("",
				DefaultSongBuilder.SOUNDTRACK, "", "Soundtracks"));

		return albumTypeBar;
	}

	private void addButtonToToolbar(AbstractButton button) {
		button.addActionListener(albumTypeToolbarActionListener);
		albumTypeBar.add(button);
		albumTypeBar.addSeparator();
		group.add(button);

	}

	private final class AlbumTypeFilter extends CustomFilter {

		// private String searchText;
		private List searchTextList;

		public void setSearchText(String text) {
			// searchText = text;
		}

		public boolean filterMatches(Object element) {
			// if (searchText.equals(""))
			// return true;
			if (searchTextList.isEmpty())
				return true;
			ObservableItem item = (ObservableItem) element;
			// if (item.getData(SongAttributes.ALBUM_TYPE).equals(searchText))
			// return true;
			for (Iterator it = searchTextList.iterator(); it.hasNext();) {
				String searchText = (String) it.next();
				if (item.getString(SongAttributes.ALBUM_TYPE).equals(searchText))
					return true;
			}
			return false;
		}

		public void setSearchStrings(List textList) {
			this.searchTextList = textList;
		}
	}

	void applyFilter(String actionCommand) {
		view.removeCustomFilter(filter, false);
		List searchTextList = (List) searchTextMap.get(actionCommand);
		filter.setSearchStrings(searchTextList);
		view.addCustomFilter(filter);
	}

	private void initSearchTextMap() {
		ArrayList emptyList = new ArrayList();
		searchTextMap.put("", emptyList);
		ArrayList epSingleList = new ArrayList();
		epSingleList.add(DefaultSongBuilder.EP);
		epSingleList.add(DefaultSongBuilder.SINGLE);
		searchTextMap.put(AlbumTypeBarBuilder.EP_SINGLE, epSingleList);
		ArrayList albumList = new ArrayList();
		albumList.add(DefaultSongBuilder.ALBUM);
		searchTextMap.put(DefaultSongBuilder.ALBUM, albumList);
		ArrayList liveList = new ArrayList();
		liveList.add(DefaultSongBuilder.BOOTLEG);
		liveList.add(DefaultSongBuilder.LIVE);
		searchTextMap.put(DefaultSongBuilder.LIVE, liveList);
		ArrayList compilationList = new ArrayList();
		compilationList.add(DefaultSongBuilder.COMPILATION);
		searchTextMap.put(DefaultSongBuilder.COMPILATION, compilationList);
		ArrayList soundtrackList = new ArrayList();
		soundtrackList.add(DefaultSongBuilder.SOUNDTRACK);
		searchTextMap.put(DefaultSongBuilder.SOUNDTRACK, soundtrackList);
	}

	private final class AlbumTypeToolbarActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			applyFilter(e.getActionCommand());
		}

	}

}
