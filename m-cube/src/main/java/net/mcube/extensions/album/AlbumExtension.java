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

package net.mcube.extensions.album;

import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Comparator;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.application.persistence.BasicLibrary;
import net.della.mplatform.application.persistence.OldLibrary;
import net.della.mplatform.gui.renderer.DataRenderer;
import net.della.mplatform.util.WidgetFactory;
import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventListener;
import net.della.stuff.gui.swing.util.SwingUtil;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.extensions.songs.comparators.ComparatorFactory;
import net.mcube.extensions.tracksView.TracksExtension;
import net.mcube.extensions.tracksView.TracksTableFormat;
import net.mcube.extensions.tracksView.TracksView;
import net.mcube.gui.GlobalCollectionAction;
import net.mcube.library.LibraryImpl;
import net.mcube.util.query.GroupByQuery;
import net.mcube.util.query.GroupItemBuilderFactory;
import net.mcube.util.query.QueryList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.impl.sort.ReverseComparator;
import della.swaf.extensions.gui.*;
import della.swaf.extensions.util.glazedlists.BasicTableFormat;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class AlbumExtension implements Extension {

   private final class TracksLoader implements ListSelectionListener {
      private final TracksTableFormat tracksTableFormat;

      private TracksLoader() {
         tracksTableFormat = new TracksTableFormat();
      }

      public void valueChanged(ListSelectionEvent e) {
         List selection = albumView.getSelection();
         if (selection.size() == 0) {
            return;
         }
         TracksView tracksView = (TracksView) application.getWindow().getView(TracksExtension.VIEW_ID);
         tracksView.loadList(selection, tracksTableFormat);
      }
   }

   private AlbumView albumView;

   public static final String CONFIG_MENU = "config menu";

   private QueryList queryList;

   private Application application;

   private String viewTitle;

   static final String SHOW_EXTENDED_PANEL = "show extended panel";

   private JPopupMenu configMenu;

   private SwitcherTableCellRenderer cellRenderer;

   private static final String SORT_MENU = "sort menu";

   private final Comparator dateAddedComparator;

   private final Comparator yearComparator;

   private Preferences prefs;

   public static final String VIEW_ID = "Albums";

   static final String USE_EXTENDED_PANEL = "use extended panel";

   public static final String COMPARATOR_NAME = "comparator name";

   public static final String ALBUM_PROFILE = "album profile";

   public AlbumExtension() {
      dateAddedComparator = ComparatorFactory.getInstance().createSongSetComparator(
            AlbumAttributes.DATE_ADDED);
      yearComparator = ComparatorFactory.getInstance().createSongSetComparator(SongAttributes.YEAR);
   }

   public void init(Application application) {
      this.application = application;

      final AlbumBuilder albumBuilder = new AlbumBuilder();
      GroupItemBuilderFactory.addBuilder(SongAttributes.ALBUM, albumBuilder);

      initViewContents();

      cellRenderer = new SwitcherTableCellRenderer(albumView, buildSimpleDataRenderer(),
            createExtendedDataRenderer());
      prefs = Preferences.systemNodeForPackage(this.getClass());
      setUseExtendedCellPanel(prefs.getBoolean(USE_EXTENDED_PANEL, true));

      initViewGUI();

      albumView.addComparator(AlbumView.SORT_BY_DATE_ADDED, new ReverseComparator(dateAddedComparator));
      albumView.addComparator(AlbumView.SORT_BY_YEAR, new ReverseComparator(yearComparator));

      registerNewComponents();

      application.addListener(new EventListener() {

         public void eventHappened(Event event) {
            String type = (String) event.get(Event.TYPE);
            if (type.equals(OldLibrary.DATA_MODIFIED))
               if (event.get(SongAttributes.ALBUM) != null)
                  handleDataModifiedEvent();
         }
      });
   }

   private void registerNewComponents() {
      application.getWindow().registerView(getID(), albumView);
      LibraryImpl library = (LibraryImpl) application.getLibrary();
      library.registerQuery(SongAttributes.ALBUM, queryList);
   }

   private void initViewContents() {
      LibraryImpl library = (LibraryImpl) BasicLibrary.getDefault();
      queryList = library.createCustomFilteredQueryList(new GroupByQuery(SongAttributes.ALBUM));
      queryList.setName("query: album");
      viewTitle = VIEW_ID;
      albumView = new AlbumView(this);
      ViewBuilder.initTableView(albumView, queryList, new BasicTableFormat(SongAttributes.ALBUM),
            new ReverseComparator(dateAddedComparator), viewTitle);
      albumView.setName(getID());

   }

   private void initViewGUI() {
      albumView.setTableHeader(null);

      configMenu = new JPopupMenu();
      AbstractButton confMenuButton = WidgetFactory.createMenuButton("Configure", configMenu);
      createConfigMenu(configMenu);
      albumView.addToolbarButton(confMenuButton);
      albumView.addPopupMenu(AlbumExtension.CONFIG_MENU, configMenu);

      JPopupMenu popupMenu = new JPopupMenu();
      AbstractButton sortMenuButton = WidgetFactory.createMenuButton("Sort By", popupMenu);
      createSortMenu(popupMenu);
      albumView.addToolbarButton(sortMenuButton);
      albumView.addPopupMenu(AlbumExtension.SORT_MENU, configMenu);

      AlbumTypeBarBuilder barBuilder = new AlbumTypeBarBuilder(albumView);
      albumView.setStatusBar(barBuilder.createAlbumTypeSelectionToolbar());

      albumView.addListEventListener(new ListEventListener() {
         public void listChanged(ListEvent listChanges) {
            albumView.setName(viewTitle + ": " + albumView.numberOfElements());
         }
      });

      albumView.addListSelectionListener(new TracksLoader());

      addPopupMenuItems();

   }

   public boolean isUseExtendedCellPanel() {
      return cellRenderer.isUseExtendedCellPanel();
   }

   public void setUseExtendedCellPanel(boolean useExtendedCellPanel) {
      cellRenderer.setUseExtendedCellPanel(useExtendedCellPanel);
   }

   private DataRenderer createExtendedDataRenderer() {
      AlbumDataRenderer albumDataRenderer = new AlbumDataRenderer();
      application.getWindow().registerDataRenderer("album widget", albumDataRenderer);
      return albumDataRenderer;
   }

   private void createConfigMenu(JPopupMenu menu) {
      String imageName = "image";
      String imagePath = application.getResourcesFolder() + imageName + ".gif";

      JCheckBoxMenuItem showExtendedPanel = (JCheckBoxMenuItem) WidgetFactory.createCheckMenuItem(imagePath,
            SHOW_EXTENDED_PANEL, "", "Show Extended Info");
      showExtendedPanel.setSelected(isUseExtendedCellPanel());
      showExtendedPanel.addActionListener(new ToolBarActionListener(albumView));
      menu.add(showExtendedPanel);
   }

   private void createSortMenu(JPopupMenu menu) {

      // ButtonGroup group = new ButtonGroup();

      String imageName = "image";
      String imagePath = application.getResourcesFolder() + imageName + ".gif";

      AbstractButton sortByDateAdded = WidgetFactory.createMenuItem(imagePath, AlbumAttributes.DATE_ADDED,
            "Sort by Date Added", "by Date Added");
      sortByDateAdded.addActionListener(new ToolBarActionListener(albumView));
      menu.add(sortByDateAdded);
      // group.add(sortByDateAdded);

      // toolBar.addSeparator();

      AbstractButton sortByYear = WidgetFactory.createMenuItem(imagePath, SongAttributes.YEAR, "by Year",
            "by Year");
      sortByYear.addActionListener(new ToolBarActionListener(albumView));
      menu.add(sortByYear);
      // group.add(sortByYear);

   }

   private void addPopupMenuItems() {
      OldLibrary library = application.getLibrary();

      GlobalCollectionAction removeAction = AlbumActionToolkit.removeFromLibrary(library, application);
      JMenuItem removeMenuItem = WidgetFactory.createItem(removeAction, KeyEvent.VK_DELETE);
      removeMenuItem.setText("Remove Album from Library");
      removeAction.putValue(Action.NAME, removeMenuItem.getText());
      albumView.addToPopup(View.CONTEXT_MENU, removeMenuItem);

      Action deleteAction = AlbumActionToolkit.removeAndDelete(library, application);
      JMenuItem deleteMenuItem = WidgetFactory.createItem(deleteAction, KeyEvent.VK_DELETE,
            InputEvent.SHIFT_DOWN_MASK);
      deleteMenuItem.setText("Delete Album from Disk");
      deleteAction.putValue(Action.NAME, deleteMenuItem.getText());
      albumView.addToPopup(View.CONTEXT_MENU, deleteMenuItem);
      albumView.addToPopup(View.CONTEXT_MENU, new JPopupMenu.Separator());

      InputMap inputMap = albumView.getInputMap();
      ActionMap actionMap = albumView.getActionMap();
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), removeAction.getValue(Action.NAME));
      actionMap.put(removeAction.getValue(Action.NAME), removeAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK), deleteAction
            .getValue(Action.NAME));
      actionMap.put(deleteAction.getValue(Action.NAME), deleteAction);

   }

   void handleDataModifiedEvent() {
      // int selectedRowIndex = view.getSelectedRow();
      queryList.refresh(true);
      albumView.removeSelection();
      // view.setSelectionInterval(selectedRowIndex, selectedRowIndex);
   }

   private DataRenderer buildSimpleDataRenderer() {

      final DefaultDataRenderer renderer = new DefaultDataRenderer();
      final JLabel artistLabel = new JLabel();
      final JLabel titleLabel = new JLabel();
      final JLabel yearLabel = new JLabel();
      titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 12));
      yearLabel.setFont(yearLabel.getFont().deriveFont(Font.PLAIN, 10));
      artistLabel.setFont(artistLabel.getFont().deriveFont(Font.PLAIN, 10));

      renderer.add(artistLabel);
      renderer.add(titleLabel);
      renderer.add(yearLabel);

      renderer.setUpdater(new DataRendererUpdater() {
         public void updateFor(ObservableItem item) {

            String artistText = "";
            if (albumView.showArtist()) {
               artistText = item.getString(SongAttributes.ARTIST);
            }
            artistText = SwingUtil.clipText(artistLabel, artistText, albumView.getWidth() / 3);
            artistLabel.setText(artistText);

            String titleText = item.getString(SongAttributes.ALBUM);
            titleText = SwingUtil.clipText(titleLabel, titleText, albumView.getWidth() / 2);
            titleLabel.setText(titleText);

            yearLabel.setText(item.getString(SongAttributes.YEAR));
         }

      });

      return renderer;

   }

   public String getId() {
      return VIEW_ID;
   }

   public void storePrefs(String comparatorName) {
      prefs.put(COMPARATOR_NAME, comparatorName);
   }

   public void storePrefs() {
      prefs.putBoolean(USE_EXTENDED_PANEL, isUseExtendedCellPanel());
   }
}