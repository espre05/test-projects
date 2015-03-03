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

package net.mcube.extensions.compilations;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import net.della.mplatform.application.gui.KeyEventListener;
import net.della.mplatform.application.gui.structure.AbstractView;
import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.application.persistence.BasicLibrary;
import net.della.mplatform.application.persistence.OldLibrary;
import net.della.mplatform.core.application.Application;
import net.della.mplatform.core.application.Extension;
import net.della.mplatform.core.datatypes.*;
import net.della.mplatform.core.gui.util.Command;
import net.della.mplatform.core.gui.util.KeyEventUtils;
import net.della.mplatform.core.gui.util.SwingUtil;
import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventListener;
import net.della.stuff.generic.util.NullComparator;
import net.mcube.extensions.album.AlbumExtension;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.extensions.songs.SongAttributesAliases;
import net.mcube.extensions.songs.SongSet;
import net.mcube.extensions.tracksView.TracksExtension;
import net.mcube.extensions.tracksView.TracksTableFormat;
import net.mcube.extensions.tracksView.TracksView;
import net.mcube.library.LibraryImpl;
import net.mcube.util.query.*;
import old.form.*;

import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;

import della.swaf.extensions.gui.*;
import della.swaf.extensions.util.glazedlists.BasicTableFormat;

public class MyCompilations implements Extension {

   private final class TracksLoader implements ListSelectionListener {
      private final TracksView view;

      private TracksTableFormat compilationTracksTableFormat;

      private TracksLoader(TracksView view) {
         super();
         this.view = view;
         SongAttributesAliases.getInstance().addAlias(COMPILATION_TRACK_NUMBER, "#");
         compilationTracksTableFormat = new TracksTableFormat() {
            protected void createStandardColumns() {
               visibleColumns.add(COMPILATION_TRACK_NUMBER);
               visibleColumns.add(SongAttributes.TITLE);
               visibleColumns.add(SongAttributes.LENGTH_STRING);
            }

            public String getPersistenceFilename() {
               return "compilation.xml";
            }
         };
      }

      public void valueChanged(ListSelectionEvent e) {
         loadList();
      }

      private void loadList() {
         List selection = compilationsView.getSelection();
         if (selection.size() == 0)
            return;
         // ViewBuilder.updateTableView(view, compilationTracksTableFormat);
         ItemSet itemSet = (ItemSet) selection.get(0);
         SongSet selectedCompilation = (SongSet) compilationsView.getSelected();
         if (selectedCompilation == null)
            return;
         String compilationName = selectedCompilation.getString(selectedCompilation.getMainAttribute());
         for (Iterator it = itemSet.childIterator(); it.hasNext();) {
            Item item = (Item) it.next();
            Set compilations = (Set) item.get(COMPILATION);
            CompilationInfo info = findCompilationInfo(compilations, compilationName);
            item.put(COMPILATION_TRACK_NUMBER, info.position + "");
         }
         view.loadList(selection, compilationTracksTableFormat);
      }

      private CompilationInfo findCompilationInfo(Set compilations, String compilationName) {
         for (Iterator iter = compilations.iterator(); iter.hasNext();) {
            CompilationInfo info = (CompilationInfo) iter.next();
            if (info.compilationName.equals(compilationName))
               return info;
         }
         return null;
      }
   }

   private final class CompilationAggregator extends CollectionAggregator {
      private CompilationAggregator(String attribute) {
         super(attribute);
      }

      protected void addSingleItem(Object key, Item item) {
         CompilationInfo info = (CompilationInfo) key;
         super.addSingleItem(info.compilationName, item);
      }
   }

   private final class RemoveFromCompilationCommand implements Command {

      private final AbstractCellListView compilationsView;

      private RemoveFromCompilationCommand(AbstractCellListView compilationsView) {
         super();
         this.compilationsView = compilationsView;
      }

      public Object run() {

         List selection = recoverSelection();
         if (selection.size() == 0)
            return null;

         SongSet selectedCompilation = (SongSet) compilationsView.getSelected();
         if (selectedCompilation == null)
            return null;
         String compilationName = selectedCompilation.getString(selectedCompilation.getMainAttribute());
         int size = selectedCompilation.size();

         for (Iterator it = selection.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            CompilationInfo compilationInfo = new CompilationInfo(compilationName, size);
            ItemUtils.removeFromCollection(item, MyCompilations.COMPILATION, compilationInfo);
         }
         return null;
      }
   }

   private final class AddToCompilationCommand implements Command {
      private final AbstractCellListView compilationsView;

      private String compilationName;

      private AddToCompilationCommand(AbstractCellListView compilationsView) {
         super();
         this.compilationsView = compilationsView;
      }

      public void setCompilationName(String compilationName) {
         this.compilationName = compilationName;
      }

      public Object run() {
         List selection = recoverSelection();
         if (selection.size() == 0)
            return null;
         int size = 0;
         SongSet selectedCompilation = (SongSet) compilationsView.getSelected();
         if (selectedCompilation == null) {
            compilationName = "new compilation";
         } else {
            size = selectedCompilation.size();
            compilationName = selectedCompilation.getString(selectedCompilation.getMainAttribute());
         }

         for (Iterator it = selection.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            ItemUtils.enableCollectionProperty(item, COMPILATION);
            CompilationInfo compilationInfo = new CompilationInfo(compilationName, ++size);
            ItemUtils.addToCollection(item, MyCompilations.COMPILATION, compilationInfo);
         }
         return null;
      }
   }

   private static final String ID = "myCompilations";

   static public final String COMPILATION = "compilation";

   private ApplicationWindow window;

   private QueryList compilationsQueryList;

   public static final String VIEW_ID = ID;

   protected static final String COMPILATION_TRACK_NUMBER = "compilation track number";

   private TableView compilationsView;

   private AddToCompilationCommand addCommand;

   private RemoveFromCompilationCommand removeCommand;

   public void init(Application application) {
      window = application.getWindow();
      GroupItemBuilderFactory.addBuilder(COMPILATION, new CompilationBuilder());
      try {
         initViewContent();
      } catch (Exception e) {
         e.printStackTrace();
      }
      window.registerView(compilationsView.getId(), compilationsView);
      application.addListener(new EventListener() {

         public void eventHappened(Event event) {
            String type = (String) event.get(Event.TYPE);
            if (type.equals(OldLibrary.DATA_MODIFIED))
               if (event.get(COMPILATION) != null)
                  handleDataModifiedEvent();
         }

      });

      application.getWindow().addKeyEventListener(new KeyEventListener() {

         public void keyEventHappened(KeyEvent e) {
            if (KeyEventUtils.isCtrlPlusReleased(e)) {
               executeAddCommand("");
            }
         }

      });

      Action action = new AbstractAction() {

         public void actionPerformed(ActionEvent e) {
            showFormDialog();
         }
      };

      compilationsView.addMenuItem(action, "Edit...", KeyEvent.VK_F2);

      final TracksView tracksView = (TracksView) application.getWindow().getView(TracksExtension.VIEW_ID);
      JMenuItem addToCompilationMenuItem = new JMenuItem("Add to current compilation");
      addToCompilationMenuItem.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            executeAddCommand("");
         }

      });
      tracksView.addToPopup(View.CONTEXT_MENU, addToCompilationMenuItem);
      JMenuItem removeFromCompilationMenuItem = new JMenuItem("remove from current compilation");
      removeFromCompilationMenuItem.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            removeCommand.run();
         }

      });
      tracksView.addToPopup(View.CONTEXT_MENU, removeFromCompilationMenuItem);
      tracksView.addToPopup(View.CONTEXT_MENU, new JPopupMenu.Separator());

      // tracksView.addViewToListen(compilationsView);
      compilationsView.addListSelectionListener(new TracksLoader(tracksView));

      initViewAppearence();

      application.getWindow().registerView(VIEW_ID, compilationsView);

      addCommand = new AddToCompilationCommand(compilationsView);
      removeCommand = new RemoveFromCompilationCommand(compilationsView);

   }

   void handleDataModifiedEvent() {
      int selectedRowIndex = compilationsView.getSelectedRow();
      compilationsQueryList.refresh(true);
      // compilationsView.removeSelection();
      compilationsView.setSelectionInterval(selectedRowIndex, selectedRowIndex);
   }

   private void initViewContent() {
      LibraryImpl library = (LibraryImpl) BasicLibrary.getDefault();
      compilationsQueryList = library.createQueryList(new GroupByQuery(COMPILATION,
            new CompilationAggregator(COMPILATION)));
      compilationsQueryList.setName("query: compilations");
      library.registerQuery(COMPILATION, compilationsQueryList);

      compilationsView = new TableView() {

         protected void handleDrop(int selectedIndex) {
            ObservableItem item = (ObservableItem) compilationsQueryList.get(selectedIndex);
            executeAddCommand(item.getString(item.getMainAttribute()));
         }

      };
      ViewBuilder.initTableView(compilationsView, compilationsQueryList, new BasicTableFormat(COMPILATION),
            new NullComparator(), getID());
   }

   private void initViewAppearence() {
      compilationsView.setTableHeader(null);

      DefaultDataRenderer dataRenderer = new DefaultDataRenderer();
      final JLabel label = new JLabel();
      label.setFont(label.getFont().deriveFont(Font.BOLD, 12));
      dataRenderer.add(label);

      dataRenderer.setUpdater(new DataRendererUpdater() {
         public void updateFor(ObservableItem item) {
            String text = item.getString(item.getMainAttribute());
            text = SwingUtil.clipText(label, text, compilationsView.getWidth() / 2);
            label.setText(text);
         }
      });

      TableCellRenderer cellRenderer = new DefaultTableCellRenderer(dataRenderer);
      compilationsView.setCellRenderer(cellRenderer);
      compilationsView.setRowHeight(30);
   }

   public String getId() {
      return ID;
   }

   void executeAddCommand(String compilationName) {
      try {
         addCommand.setCompilationName(compilationName);
         addCommand.run();
      } catch (Exception exc) {
         exc.printStackTrace();
      }
   }

   private void showFormDialog() {

      final String bindProperty = COMPILATION;
      ObservableItem item = (ObservableItem) compilationsView.getSelected();

      ModelBuilder modelBuilder = new CompilationModelBuilder(bindProperty);
      PropertyListenerFactory itemPropertyListenerFactory = new ItemPropertyListenerFactory();

      DataInputForm form = JFormPane.newForm(bindProperty, item, modelBuilder, itemPropertyListenerFactory);

      String dialogTitle = "Insert your RateYourMusic username";
      FormDialogBuilder dialogBuilder = new PlainFormDialogBuilder();

      JFormPane.showDialog(form, dialogTitle, dialogBuilder);

   }

   List recoverSelection() {
      View focusedView = window.getCurrentPage().getFocusedView();
      List selection = new ArrayList();
      if ((focusedView.getId().equals(TracksExtension.VIEW_ID))
            || (focusedView.getId().equals(AlbumExtension.VIEW_ID))) {
         AbstractView aView = (AbstractView) focusedView;
         selection = aView.getSelection();
      }
      return selection;
   }

}
