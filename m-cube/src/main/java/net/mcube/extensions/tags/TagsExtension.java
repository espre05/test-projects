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

package net.mcube.extensions.tags;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.datatypes.ItemSet;
import net.della.mplatform.application.datatypes.ItemUtils;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.gui.KeyEventListener;
import net.della.mplatform.application.gui.structure.AbstractView;
import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.application.persistence.BasicLibrary;
import net.della.mplatform.application.persistence.OldLibrary;
import net.della.mplatform.background.AbstractJob;
import net.della.mplatform.background.swing.IndeterminateBackgroundTask;
import net.della.mplatform.background.swing.SwingBackgroundTask;
import net.della.mplatform.docking.defaults.DefaultDockableView;
import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventListener;
import net.della.stuff.generic.util.MathUtil;
import net.della.stuff.gui.swing.util.KeyEventUtils;
import net.mcube.datatypes.BuilderDecorator;
import net.mcube.datatypes.DefaultItemSetBuilder;
import net.mcube.extensions.album.AlbumBuilder;
import net.mcube.extensions.album.AlbumExtension;
import net.mcube.extensions.authors.ArtistExtension;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.extensions.tags.graph.GraphCreator;
import net.mcube.extensions.tracksView.TracksExtension;
import net.mcube.library.LibraryImpl;
import net.mcube.util.MiscUtils;
import net.mcube.util.query.*;
import old.form.FormDialogShower;

import org.apache.commons.logging.LogFactory;



public class TagsExtension implements Extension {

   public static final String TAGS = "tags";

   private static final String ID = "tags";

   private ApplicationWindow appWindow;

   private AbstractView view;

   private QueryList queryList;

   private Application application;

   static final String FREQUENCY = TAGS + ".frequency";

   private TagCosmos tagCosmos;

   public void init(Application application) {

      this.application = application;
      tagCosmos = new TagCosmos();
      GroupItemBuilderFactory.addBuilder(TAGS, new DefaultItemSetBuilder() {

         protected ItemSet createItemSet() {
            return new TagsItemSet();
         }

      });
      AlbumBuilder albumBuilder = (AlbumBuilder) GroupItemBuilderFactory.getBuilder(SongAttributes.ALBUM);
      albumBuilder.add(new BuilderDecorator() {

         public void apply(ItemSet itemSet) {
            ItemUtils.enableCollectionProperty(itemSet, TAGS);
         }

         public void apply(ItemSet itemSet, Map props) {
         }

      });
      appWindow = application.getWindow();
      appWindow.addKeyEventListener(new KeyEventListener() {

         public void keyEventHappened(KeyEvent e) {
            if (KeyEventUtils.isCtrlTReleased(e)) {
               openDialog();
            }
         }
      });
      initView();

      initExternalView(TracksExtension.VIEW_ID);
      initExternalView(AlbumExtension.VIEW_ID);
      initExternalView(ArtistExtension.VIEW_ID);

      application.addListener(new EventListener() {

         public void eventHappened(Event event) {
            String type = (String) event.get(Event.TYPE);
            if (type.equals(OldLibrary.DATA_MODIFIED)) {
               Object newTags = event.get(TAGS);
               if (newTags == null)
                  return;
               handleDataModifiedEvent();
               createGraph();
            } else if (type.equals(OldLibrary.ITEM_ADDED) || type.equals(OldLibrary.ITEM_REMOVED)
                  || type.equals(LibraryImpl.LIBRARY_LOADED)) {
               createGraph();
            }

         }
      });
   }

   private void initExternalView(String viewId) {
      View view = appWindow.getView(viewId);
      view.addToPopup(View.CONTEXT_MENU, new JPopupMenu.Separator());
      view.addToPopup(View.CONTEXT_MENU, newMenuItem());
   }

   private JMenuItem newMenuItem() {
      JMenuItem menuItem = new JMenuItem(createOpenDialogAction());
      menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
      return menuItem;
   }

   void createGraph() {

      SwingBackgroundTask task = new IndeterminateBackgroundTask() {

         protected Object executeIndeterminateOperation() {
            updateCosmos();
            return null;
         }

      };
      application.runOperation(AbstractJob.newSimpleJob(task, "updating tag cosmos...", ""));
   }

   private AbstractAction createOpenDialogAction() {
      AbstractAction abstractAction = new AbstractAction("Edit Tags...") {

         public void actionPerformed(ActionEvent e) {
            openDialog();
         }
      };
      return abstractAction;
   }

   void handleDataModifiedEvent() {
      // int selectedRowIndex = view.getSelectedRow();
      queryList.refresh(true);
      // view.removeSelection();
      // view.setSelectionInterval(selectedRowIndex, selectedRowIndex);
   }

   private void initView() {
      LibraryImpl library = (LibraryImpl) BasicLibrary.getDefault();
      queryList = library.createQueryList(new GroupByQuery(TAGS, new CollectionAggregator(TAGS)));
      queryList.setName("query: tags");
      String viewTitle = ID;
      view = new DefaultDockableView("TagView");
      view.setId(getId());
      view.setContent(tagCosmos.getPanel());
      appWindow.registerView(view.getId(), view);
   }

   public String getId() {
      return ID;
   }

   private void openDialog() {
      LogFactory.getLog(this.getClass()).debug("tags edit dialog opening...");
      FormDialogShower dialogShower = new FormDialogShower();
      dialogShower.setPropertyListenerFactory(new ItemPropertyListenerFactory() {

         public PropertyChangeListener newChangeAdapter(ObservableItem item) {
            return newCollectionChangeAdapter(item);
         }
      });
      dialogShower.openDialog(TAGS, appWindow.getCurrentPage().getFocusedView(), new TagsModelBuilder(TAGS));
   }

   void updateCosmos() {
      queryList.getReadWriteLock().readLock().lock();
      try {
         long start = System.currentTimeMillis();

         GraphCreator creator = new GraphCreator();
         List graphList = creator.createTagsAssociations(queryList);
         tagCosmos.update(graphList);

         long end = System.currentTimeMillis();
         long time = end - start;
         LogFactory.getLog(this.getClass()).info("create graph: " + time);
      } catch (Exception e) {
         LogFactory.getLog(this.getClass()).info("Error updating tag cosmos");
         e.printStackTrace();
      } finally {
         queryList.getReadWriteLock().readLock().unlock();
      }
   }

   public static void addDateTag(ObservableItem item) {
      String dateAddedString = item.getString(SongAttributes.YEAR);
      if (!MathUtil.isInteger(dateAddedString))
         return;
      int date = Integer.parseInt(dateAddedString);
      if (date < 1910 || date > 2010)
         return;
      ItemUtils.addToCollection(item, TAGS, dateAddedString);
   }

   public static void addDecadeTag(ObservableItem item) {
      String dateAddedString = item.getString(SongAttributes.YEAR);
      if (!MathUtil.isInteger(dateAddedString))
         return;
      int date = Integer.parseInt(dateAddedString);
      String decade = MiscUtils.getDecade(date);
      if (!"".equals(decade))
         ItemUtils.addToCollection(item, TAGS, decade + "s");
   }

}
