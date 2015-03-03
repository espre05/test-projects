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

package net.mcube.library;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.*;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jin.collection.core.Criteria;
import net.della.mplatform.application.persistence.BasicLibrary;
import net.della.mplatform.application.persistence.Context;
import net.della.mplatform.application.persistence.Library;
import net.della.mplatform.core.background.swing.CollectionSingleBackgroundTask;
import net.della.mplatform.core.background.swing.SwingBackgroundTask;
import net.della.mplatform.core.datatypes.*;
import net.della.stuff.generic.event.*;
import net.della.stuff.generic.file.FileUtils;
import net.mcube.extensions.songs.DefaultSong;
import net.mcube.util.Delayer;
import net.mcube.util.query.Query;
import net.mcube.util.query.QueryList;
import old.Database;
import old.DiskArchiveManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;


import della.swaf.extensions.util.glazedlists.CustomFilter;
import della.swaf.extensions.util.glazedlists.CustomFilteredList;

/*
 * Created on 29-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class LibraryImpl extends BasicLibrary {

   public static final String SONG = "Song";

   public static final String SONG_SET = "SongSet";

   private BasicEventList items;

   private CustomFilteredList customFilteredItems;

   static public final String DB_VERSION_KEY = "mcube db version";

   static public final String LIBRARY_LOADED = "lybrary loaded";

   private Map queryListsMap;

   private PropertyChangeSupport propertyChangeSupport;

   private JTextField filterEdit;

   private FilterList textFilterList;

   private TextComponentMatcherEditor textComponentMatcherEditor;

   private DelayerPropertyChangeForwarder delayerPropertyChangeForwarder;

   private net.della.stuff.generic.event.EventMulticaster eventMulticaster;

   private TopFolders topFolders;

   private String dbHome;

   private String picturesHome;

   private Database folderDB;

   private Database itemsDB;

   protected void init() {
      items = new BasicEventList();

      filterEdit = new JTextField();
      textComponentMatcherEditor = new TextComponentMatcherEditor(filterEdit, DefaultSong
            .createSongFilterator(), false);
      textFilterList = new FilterList(items, textComponentMatcherEditor);
      filterEdit.getDocument().addDocumentListener(new DelayedDocumentListener());

      customFilteredItems = new CustomFilteredList(textFilterList);

      queryListsMap = new HashMap();
      propertyChangeSupport = new PropertyChangeSupport(this);
      // idIndex = new Properties();
      delayerPropertyChangeForwarder = new DelayerPropertyChangeForwarder(700);
      eventMulticaster = new EventMulticaster();
   }

   protected void setLibraryHome(String homePath) {
      items.clear();
      // TODO: remove any reference to DiskArchiveManager
      // diskArchiveManager = new DiskArchiveManager(homePath);
   }

   private void firePropertyChange(String propertyName) {
      firePropertyChange(propertyName, null, null);
   }

   public JTextPane getFilteredNumberTextPane() {
      return createTextPane(customFilteredItems);
   }

   public JTextPane getTotalNumnerTextPane() {
      return createTextPane(items);
   }

   private JTextPane createTextPane(final EventList list) {
      final JTextPane textPane = new JTextPane();
      textPane.setEditable(false);
      textPane.setText(list.size() + "");
      list.addListEventListener(new ListEventListener() {

         public void listChanged(ListEvent listChanges) {
            textPane.setText(list.size() + "");
         }
      });
      return textPane;
   }

   public JTextField getFilterEdit() {
      return filterEdit;
   }

   private void notifyFileRemoved() {
      firePropertyChange(Library.ITEM_REMOVED);
   }

   private void notifyFileAdded() {
      firePropertyChange(Library.ITEM_ADDED);
   }

   public QueryList createQueryList(Query query) {
      return new QueryList(items, query);
   }

   public QueryList createCustomFilteredQueryList(Query query) {
      return new QueryList(customFilteredItems, query);
   }

   private QueryList createTextFilteredQueryList(Query query) {
      return new QueryList(textFilterList, query);
   }

   public boolean contains(ObservableItem item) {
      if (!contains(item.getString(FileItemAttributes.RELATIVE_PATH)))
         return false;
      return true;

   }

   public boolean contains(String path) {
      ObjectSet<Object> resultSet = getResultSetByPath(path);
      if (resultSet.size() == 1)
         return true;
      if (resultSet.size() > 1) {
         LogFactory.getLog(this.getClass()).info(
               "Problem: there are more than one element that refers to location '" + path + "'");
         return true;
      }
      LogFactory.getLog(this.getClass()).info(
            "Problem: cannot find element that refers to location '" + path + "'");
      return false;

   }

   public void addAll(Collection<ObservableItem> newItemsList) {
      items.getReadWriteLock().writeLock().lock();
      try {
         ObservableItem item = null;
         for (Iterator it = newItemsList.iterator(); it.hasNext();) {
            try {
               item = (ObservableItem) it.next();
               if (contains(item))
                  it.remove();
            } catch (ClassCastException e) {
               System.out.println("Input list must contains Item object");
               System.out.println("Object" + item + "will not be added in Library");
               it.remove();
            }
         }
         for (Iterator it = newItemsList.iterator(); it.hasNext();) {
            item = (ObservableItem) it.next();
            // updatePathIndex(item);
            // addChangeListener(item);
            item.setPersistent(true);
            itemsDB.add(item);
            // item.setPersistent(true);
         }
         items.addAll(newItemsList);
         notifyFileAdded();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         items.getReadWriteLock().writeLock().unlock();
      }
   }

   // private void addChangeListener(Item item) {
   // item.addPropertyChangeListener(delayerPropertyChangeForwarder);
   // }

   // private void updatePathIndex(Item item) {
   // idIndex.put(item.getData(ItemAttributes.PATH), item);
   // }

   public void removeAll(Collection itemsToRemove) {
      items.getReadWriteLock().writeLock().lock();
      try {
         for (Iterator it = itemsToRemove.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            // idIndex.remove(item.getData(ItemAttributes.PATH));
            itemsDB.delete(item);
         }
         items.removeAll(itemsToRemove);
         notifyFileRemoved();
      } finally {
         items.getReadWriteLock().writeLock().unlock();
      }
   }

   public Item getItemByPath(String path) {
      ObjectSet<Object> resultSet = getResultSetByPath(path);
      return (Item) resultSet.next();
   }

   private ObjectSet<Object> getResultSetByPath(String path) {
      String normalizedFilePath = FileUtils.normalizeFilePath(path);
      FileItem fileItem = newQueryItem();
      fileItem.setRelativePath(path);
      ObjectSet<Object> resultSet = itemsDB.getFromDB(fileItem);
      return resultSet;
   }

   public void registerQuery(Object key, QueryList queryList) {
      queryListsMap.put(key, queryList);
   }

   public QueryList getQueryResponse(Object key) {
      return (QueryList) queryListsMap.get(key);
   }

   public void addCustomFilter(CustomFilter filter) {
      customFilteredItems.addCustomFilter(filter);
   }

   public int countItems() {
      return items.size();
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      propertyChangeSupport.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      propertyChangeSupport.removePropertyChangeListener(listener);
   }

   void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
      propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
   }

   public Context getContext() {
      return new DatabaseContext(topFolders);
   }

   public void addTopFolder(String folderPath) {
      String id = topFolders.createTopFolder(folderPath);
      FileItem fileItem = newFolderItem();
      folderDB.add(fileItem);
      fileItem.setType(DiskArchiveManager.TOP_FOLDER_TYPE);
      fileItem.put(FileItemAttributes.ABSOLUTE_PATH, folderPath);
      fileItem.put(FileItemAttributes.VIRTUAL_PATH, id);

   }

   private FileItem newFolderItem() {
      FileItem fileItem = new FileItem();
      fileItem.setPersistent(true);
      return fileItem;
   }

   public void addManagedFolder(String folder) {
      FileItem fileItem = newFolderItem();
      folderDB.add(fileItem);
      fileItem.setType(DiskArchiveManager.FOLDER_TYPE);
      fileItem.setRelativePath(folder);

   }

   private final class DelayerPropertyChangeForwarder extends Delayer implements PropertyChangeListener {
      private Collection eventsBuffer;

      public DelayerPropertyChangeForwarder(int delay) {
         super(delay);
         eventsBuffer = new ArrayList();
      }

      public void propertyChange(PropertyChangeEvent evt) {
         LogFactory.getLog(this.getClass()).debug("property changed: " + evt.getNewValue());
         eventsBuffer.add(evt);
         handleUpdate();
      }

      protected void refresh() {
         LogFactory.getLog(this.getClass()).debug("data modified fired");
         Event event = EventFactory.createEvent(this, DATA_MODIFIED);
         for (Iterator it = eventsBuffer.iterator(); it.hasNext();) {
            PropertyChangeEvent evt = (PropertyChangeEvent) it.next();
            event.set(evt.getPropertyName(), evt.getNewValue());
            try {
               update((ObservableItem) evt.getSource());
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
         eventsBuffer.clear();
         fireEvent(event);
      }

   }

   void update(ObservableItem item) {
      itemsDB.update(item);
   }

   private final class DelayedDocumentListener implements DocumentListener {

      private boolean scheduled;

      private Timer timer;

      private Random random;

      public DelayedDocumentListener() {
         timer = new Timer();
         random = new Random();
      }

      public void changedUpdate(DocumentEvent e) {
      }

      public void insertUpdate(DocumentEvent e) {
         handleUpdate();

      }

      public void removeUpdate(DocumentEvent e) {
         handleUpdate();
      }

      private void handleUpdate() {

         if (scheduled) {
            timer.cancel();
            scheduled = false;
         }
         // System.out.println("scheduling refresh...");
         timer = new Timer();
         int delay = 350 + random.nextInt(50);
         timer.schedule(new RefreshTask(), delay);
         scheduled = true;

      }

      private class RefreshTask extends TimerTask {

         public void run() {
            Log log = LogFactory.getLog(this.getClass());
            log.info("refreshing...");
            // log.info("filteredList count: " + filteredItems.size());
            try {
               textComponentMatcherEditor.setFilterText(filterEdit.getText().split("[ \t]"));
            } catch (Exception e) {
               e.printStackTrace();
            }
            scheduled = false;
         }

      }
   }

   public SwingBackgroundTask getCleanItemsTask(final Criteria criteria) {
      CollectionSingleBackgroundTask cleanTask = new CollectionSingleBackgroundTask() {

         protected void apply() {
            Collection itemsToRemove = iter.filter(col, criteria);
            removeAll(itemsToRemove);
         }

      };
      cleanTask.setCollection(items);
      return cleanTask;
   }

   void fireEvent(Event event) {
      eventMulticaster.notifyEvent(event);
   }

   public SwingBackgroundTask getCleanFoldersTask(final Criteria criteria) {
      CollectionSingleBackgroundTask cleanTask = new CollectionSingleBackgroundTask() {

         protected void apply() {
            Collection foldersToRemove = iter.filter(col, criteria);
            diskArchiveManager.removeManagedFolders(foldersToRemove);
            // col.removeAll(foldersToRemove);
         }

      };
      cleanTask.setCollection(diskArchiveManager.getManagedFoldersPath());
      return cleanTask;
   }

   public void removeCustomFilter(CustomFilter tagSelectionFilter) {
      customFilteredItems.removeCustomFilter(tagSelectionFilter);
   }

   public boolean isFolderManaged(File folder) {
      // com.db4o.query.Query query = folderDB.newQuery(Item.class);
      // query.constrain(TypeEvaluation.folderTypeEvaluation);
      // query.constrain(new Evaluation() {
      //		
      // public void evaluate(Candidate candidate) {
      // FileItem archivedItem = (FileItem) candidate.getObject();
      // candidate.include(archivedItem.getType().toLowerCase().equals(queryValue));
      // }
      //		
      // });
      // ObjectSet resultSet = query.execute();
      FileItem queryItem = newQueryItem();
      queryItem.setRelativePath(folder.getAbsolutePath());
      ObjectSet<Object> resultSet = folderDB.getFromDB(queryItem);
      if (resultSet.size() > 1)
         throw new IllegalStateException(
               "A managed folder is stored more than one times in Folders Database: "
                     + folder.getAbsolutePath());
      return resultSet.size() == 1;
   }

   private FileItem newQueryItem() {
      // TODO: understand why I Cannot use a FileItem instance while there is no
      // difference between DefaultSong and its subtypes
      FileItem fileItem = new DefaultSong();
      return fileItem;
   }

   public void addEventListener(EventListener listener) {
      eventMulticaster.addListener(listener);
   }

   public void clear() {
      // idIndex.clear();
      items.clear();
   }

   public TopFolders getTopFolders() {
      return topFolders;
   }

   public String getDatabaseHome() {
      return dbHome;
   }

   public void removeCustomFilter(ItemSetSelectionFilter songSetSelectionFilter, boolean forceRefresh) {
      customFilteredItems.removeCustomFilter(songSetSelectionFilter, forceRefresh);
   }

   public void load() {
      String databaseFolderAbsolutPath = FileUtils.getCurrentFolderAbsolutePath() + File.separator
            + "database";
      load(databaseFolderAbsolutPath);
   }

   public void load(String dbHome) {

      folderDB = new Database(dbHome, "folders");
      com.db4o.query.Query query = folderDB.newQuery(ObservableItem.class);
      query.constrain(TypeEvaluation.topFolderTypeEvaluation);
      ObjectSet resultSet = query.execute();
      topFolders = new TopFolders();
      topFolders.addAll(resultSet);

      System.out.println("load DB..." + new Date());
      itemsDB = new Database(dbHome, "items");
      itemsDB.configure().objectClass(DefaultSong.class).objectField("type").indexed(true);
      itemsDB.configure().objectClass(DefaultSong.class).objectField("relativePath").indexed(true);
      query = itemsDB.newQuery(ObservableItem.class);
      System.out.println("load items..." + new Date());
      resultSet = query.execute();
      System.out.println("attaching listener..." + new Date());
      // for (Iterator it = resultSet.iterator(); it.hasNext();) {
      // Item item = (Item) it.next();
      // item.addPropertyChangeListener(delayerPropertyChangeForwarder);
      // }
      System.out.println("adding items to library..." + new Date());
      items.addAll(resultSet);
      System.out.println("library loading complete." + new Date());

      this.dbHome = dbHome;
      setPicturesHome(dbHome + File.separator + "artworks");
      fireEvent(EventFactory.createEvent(this, LIBRARY_LOADED));

   }

   public static void main(String[] args) {
      BasicEventList songList = new BasicEventList();
      Database db = new Database("database", "items");
      com.db4o.query.Query query = db.newQuery(ObservableItem.class);
      query.constrain(TypeEvaluation.songTypeEvaluation);
      ObjectSet resultSet = query.execute();
      songList.addAll(resultSet);

      BasicEventList folderList = new BasicEventList();
      query = db.newQuery(ObservableItem.class);
      query.constrain(TypeEvaluation.folderTypeEvaluation);
      resultSet = query.execute();
      folderList.addAll(resultSet);

      System.out.println(songList.size());
      System.out.println(folderList.size());

      db.close();
   }

   public String getPicturesHome() {
      return picturesHome;
   }

   public final void setPicturesHome(String picturesHome) {
      this.picturesHome = picturesHome;
   }

   public void update(Item item, String propertyName, Object oldValue, Object newValue) {
      delayerPropertyChangeForwarder.propertyChange(new PropertyChangeEvent(item, propertyName, oldValue,
            newValue));
   }

   public void close() {
      itemsDB.close();
      folderDB.close();
   }

}