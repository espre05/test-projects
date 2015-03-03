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
 * Created on 13-feb-2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package net.mcube.extensions.authors;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JLabel;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.gui.structure.Page;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.application.persistence.OldLibrary;
import net.della.mplatform.gui.renderer.DataRenderer;
import net.della.mplatform.util.WidgetFactory;
import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventListener;
import net.della.stuff.gui.swing.util.SwingUtil;
import net.mcube.extensions.album.AlbumExtension;
import net.mcube.extensions.album.AlbumView;
import net.mcube.extensions.album.ToolBarActionListener;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.extensions.songs.comparators.ComparatorFactory;
import net.mcube.library.LibraryImpl;
import net.mcube.util.query.GroupByQuery;
import net.mcube.util.query.GroupItemBuilderFactory;
import net.mcube.util.query.QueryList;
import della.swaf.extensions.gui.*;
import della.swaf.extensions.util.glazedlists.BasicTableFormat;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ArtistExtension implements Extension {

   public static final String VIEW_ID = "Artists";

   private TableView view;

   private QueryList artistQueryList;

   private ItemSetSelectionFilter songSetSelectionFilter;

   private Application application;

   public ArtistExtension() {
   }

   public void init(Application application) {
      this.application = application;

      // GroupItemCreatorFactory.addCreator(SongAttributes.ALBUM, new
      // AlbumBuilder());
      GroupItemBuilderFactory.addBuilder(SongAttributes.ARTIST, new ArtistBuilder());

      setUpView(application);

      application.addListener(new EventListener() {

         public void eventHappened(Event event) {
            String type = (String) event.get(Event.TYPE);
            // if (type.equals(Library.DATA_MODIFIED) ||
            // type.equals(LibraryImpl.ITEM_ADDED)
            // || type.equals(LibraryImpl.ITEM_REMOVED))
            if (type.equals(OldLibrary.DATA_MODIFIED))
               if (event.get(SongAttributes.ARTIST) != null)
                  handleDataModifiedEvent();
         }

      });

      application.getWindow().registerView(getID(), view);

      final AlbumView albumView = (AlbumView) application.getWindow().getView(AlbumExtension.VIEW_ID);
      songSetSelectionFilter = new ItemSetSelectionFilter(SongAttributes.ARTIST, albumView.getSelection());
      view.addPropertyChangeListener(new PropertyChangeListener() {

         public void propertyChange(PropertyChangeEvent evt) {
            if (!evt.getPropertyName().equals(View.SELECTION_CHANGED))
               return;
            List list = (List) evt.getNewValue();
            // updateAfterArtistSelection(list);

            if (list.size() == 0) {
               // view.changeComparator(new ReverseComparator(
               // dateAddedComparator));
               albumView.useComparator(AlbumView.SORT_BY_DATE_ADDED);
               albumView.removeCustomFilter(songSetSelectionFilter);
               // LibraryImpl.getDefault().removeCustomFilter(songSetSelectionFilter);
               albumView.setShowArtist(true);
               // filteredForArtist = false;
               return;
            }
            albumView.removeCustomFilter(songSetSelectionFilter, false);
            // LibraryImpl.getDefault().removeCustomFilter(songSetSelectionFilter,
            // false);
            songSetSelectionFilter.setSelectionList(list);
            albumView.addCustomFilter(songSetSelectionFilter);
            // LibraryImpl.getDefault().addCustomFilter(songSetSelectionFilter);
            if (!albumView.getComparatorName().equals(AlbumView.SORT_BY_YEAR))
               albumView.useComparator(AlbumView.SORT_BY_YEAR);
            // filteredForArtist = true;
            albumView.setShowArtist(false);
         }
      });

   }

   private void setUpView(Application application) {
      // albumQueryList =
      // LibraryImpl.createQueryList(application.getLibrary(),
      // new GroupByQuery(SongAttributes.ALBUM));
      // albumQueryList.setName("query: album (for artist)");
      LibraryImpl library = (LibraryImpl) application.getLibrary();
      QueryList albumQueryList = library.getQueryResponse(SongAttributes.ALBUM);

      GroupByQuery query = new GroupByQuery(SongAttributes.ARTIST);
      // query.setSource(albumQueryList);
      artistQueryList = new QueryList(albumQueryList, query);
      artistQueryList.setName("query: artist");

      String viewTitle = "Artists";
      view = new TableView();
      ViewBuilder.initTableView(view, artistQueryList, new BasicTableFormat(" "), ComparatorFactory
            .getInstance().createMultiValueSongSetComparator(SongAttributes.USEFUL_ARTIST), viewTitle);

      // view.setComparator(0,
      // ComparatorFactory.getInstance().createMultiValueSongSetComparator(
      // SongAttributes.USEFUL_ARTIST));

      view.setName(getID());

      buildGUI(viewTitle);

      view.setTableHeader(null);

      SwitcherTableCellRenderer panelTableCellRenderer = new SwitcherTableCellRenderer(buildRenderer());
      view.setCellRenderer(panelTableCellRenderer);
      view.addListSelectionListener(panelTableCellRenderer);
      view.setRowHeight(30);
   }

   private void buildGUI(String viewTitle) {

      // SimpleInternalFrame mainComponent = new
      // SimpleInternalFrame(viewTitle);

      // JToolBar toolBar = WidgetFactory.buildToolBar();
      // mainComponent.setToolBar(toolBar);

      String imageName = "image";
      AbstractButton sortByArtist = WidgetFactory.createToolBarButton(application.getApplicationHome()
            + "toolbarButtonGraphics/navigation/" + imageName + ".gif", SongAttributes.ARTIST,
            "Sort by Artist", "by Artist");
      sortByArtist.addActionListener(new ToolBarActionListener(view));
      // toolBar.add(sortByArtist);
      view.addToolbarButton(sortByArtist);

      // JScrollPane scrollPane = view.createScrollPane();
      // mainComponent.add(scrollPane, BorderLayout.CENTER);
      // view.setMainComponent(mainComponent);

   }

   protected void handleDataModifiedEvent() {
      // int selectedRowIndex = view.getSelectedRow();
      // albumQueryList.refresh(true);
      view.removeSelection();
      artistQueryList.refresh(true);
      // view.setSelectionInterval(selectedRowIndex, selectedRowIndex);
      // view.centerOnSelection();
   }

   private DataRenderer buildRenderer() {
      DefaultDataRenderer panel = new DefaultDataRenderer();
      final JLabel artistLabel = new JLabel();
      final JLabel numberLabel = new JLabel();

      // panel.setBackground(Color.WHITE);

      artistLabel.setFont(artistLabel.getFont().deriveFont(Font.BOLD, 12));
      numberLabel.setFont(numberLabel.getFont().deriveFont(Font.PLAIN, 10));

      panel.add(artistLabel);
      panel.add(numberLabel);

      panel.setUpdater(new DataRendererUpdater() {
         public void updateFor(ObservableItem item) {
            String artistText = item.getString(SongAttributes.ARTIST);
            artistLabel.setText(SwingUtil.clipText(artistLabel, artistText, (int) (view.getWidth() / 1.2)));
            numberLabel.setText(item.getString(SongAttributes.TOTAL_TRACKS));
         }
      });

      return panel;

   }

   public View getView() {
      return view;
   }

   public String getId() {
      return VIEW_ID;
   }

   public Page getPage() {
      // TODO Auto-generated method stub
      return null;
   }

}