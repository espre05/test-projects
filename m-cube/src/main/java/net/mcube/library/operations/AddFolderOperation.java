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

package net.mcube.library.operations;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.della.mplatform.application.persistence.OldLibrary;
import net.della.mplatform.background.AbstractJob;
import net.della.mplatform.background.TaskList;
import net.della.mplatform.background.swing.SwingBackgroundTask;
import net.mcube.extensions.songs.DefaultSongBuilder;
import net.mcube.extensions.songs.SongAttributes;

import org.apache.commons.logging.Log;



public class AddFolderOperation extends AbstractJob {

   OldLibrary library;

   private File folderToAdd;

   private List subfolders;

   private List files;

   private boolean dateTagFlagEnabled;

   private boolean trustId3Tag;

   private boolean trustTextFile;

   private String userTag;

   private Long dateAddedMillis;

   private Boolean decadeTagFlagEnabled;

   static public final String USE_TEXT_FILE = "use text file";

   public static final String TAG_WITH_DATE = "tag with date";

   public static final String USER_TAG = "user tag";

   public static final String NEW_FOLDER = "new folder";

   static public final String DATE_ADDED = "date added";

   public static final String TAG_WITH_DECADE = "tag with decade";;

   public AddFolderOperation(OldLibrary library, File folder) {
      this.library = library;
      this.folderToAdd = folder;

      subfolders = new ArrayList();
      files = new ArrayList();
      dateTagFlagEnabled = false;
      decadeTagFlagEnabled = true;
      trustId3Tag = true;
      trustTextFile = false;
      userTag = "";
   }

   protected TaskList getThreadPool() {
      final Log log = LogFactory.getLog(this.getClass());
      if (!library.isFolderManaged(folderToAdd)) {
         library.addTopFolder(folderToAdd.getAbsolutePath());
      }

      SwingBackgroundTask scanForSubfolders = createScanSubfoldersTask();

      SwingBackgroundTask scanForFiles = createScanFilesTask();

      final AddItemsProxy op = new AddItemsProxy(library);

      SwingBackgroundTask createItemsFromFiles = createBuildItemsTask(log, op);

      SwingBackgroundTask addItemsToLibrary = createAddToLibraryTask(op);

      TaskList pool = TaskList.simpleThreadPool();
      pool.add(scanForSubfolders);
      pool.add(scanForFiles);
      pool.add(createItemsFromFiles);
      pool.add(addItemsToLibrary);
      return pool;

   }

   private SwingBackgroundTask createAddToLibraryTask(final AddItemsProxy op) {
      SwingBackgroundTask addItemsToLibrary = new IndeterminateBackgroundTask() {

         protected Object executeIndeterminateOperation() {
            try {
               op.run();
            } catch (Exception e) {
               e.printStackTrace();
            }
            return null;
         }

      };
      addItemsToLibrary.setName("Adding item to library...");
      return addItemsToLibrary;
   }

   private SwingBackgroundTask createBuildItemsTask(final Log log, final AddItemsProxy op) {
      SwingBackgroundTask createItemsFromFiles = new CollectionBackgroundTask(getFoldersContext()) {

         protected void applyTo(Object singleElement) {

            final FolderContext folderContext = (FolderContext) singleElement;

            if (trustTextFile && isTextFileValid(folderContext)) {
               int i = 0;
               for (Iterator it = folderContext.getFiles().iterator(); it.hasNext();) {
                  String title = (String) folderContext.getTracklist().get(i);
                  File file = (File) it.next();
                  DefaultItemBuilder builder = createSongBuilder(folderContext);

                  Context context = library.getContext();

                  String path = file.getAbsolutePath();
                  String topFolderPath = context.getTopFolder(path);
                  String vPath = context.getID(topFolderPath);
                  String rPath = path.replaceAll(topFolderPath, "");

                  if (!library.contains(rPath)) {
                     Item newItem = buildItem(folderContext, file, builder);
                     try {
                        newItem.put(SongAttributes.TITLE, title);
                        newItem.put(SongAttributes.TRACK_NUMBER, i + 1 + "");
                     } catch (Exception e) {
                        log.info("casini con la Tracklist caricata da file");
                     }
                     op.add(newItem);
                  }
                  i++;
               }
            } else {
               for (Iterator it = folderContext.getFiles().iterator(); it.hasNext();) {
                  File file = (File) it.next();
                  DefaultItemBuilder builder = ItemBuilderFactory.getInstance().getBuilderFor(file);
                  Context context = library.getContext();

                  String path = file.getAbsolutePath();
                  path = FileUtils.normalizeFilePath(path);
                  String topFolderPath = context.getTopFolder(path);
                  String vPath = context.getID(topFolderPath);
                  String rPath = path.replaceAll(topFolderPath, "");

                  if (!library.contains(rPath)) {
                     Item newItem = buildItem(folderContext, file, builder);
                     op.add(newItem);
                  }
               }
            }
         }

         private boolean isTextFileValid(FolderContext folderContext) {
            return (folderContext.getFiles().size() == folderContext.getTracklist().size());
         }

         private Item buildItem(final FolderContext folderContext, File file, DefaultItemBuilder builder) {
            ItemCreatorDirector director = new ItemCreatorDirector(builder, library.getContext());
            ObservableItem item = createItem(director, file);
            addUserSelectedTags(item);
            // String frontImagePath =
            // folderContext.getCoverInfo().getFrontImagePath();
            // if (frontImagePath.equals(""))
            // frontImagePath = FileUtils.getCurrentFolderAbsolutePath()
            // + ImageProperty.DEFAULT_CD_COVER_PATH;
            // item.setData(SongAttributes.ALBUM_COVER_FRONT_PATH,
            // frontImagePath);
            item.put(SongAttributes.ALBUM_COVER_FRONT, ImageProperty.getInstance());
            if (dateAddedMillis == null)
               dateAddedMillis = System.currentTimeMillis();
            item.put(FileItemAttributes.DATE_ADDED, Long.toString(dateAddedMillis));
            return item;
         }

         private void addUserSelectedTags(ObservableItem item) {
            ItemUtils.enableCollectionProperty(item, TagsExtension.TAGS);
            if (isDateTagFlagEnabled())
               TagsExtension.addDateTag(item);
            if (isDecadeTagFlagEnabled())
               TagsExtension.addDecadeTag(item);
            if (!"".equals(getUserTag()))
               ItemUtils.addToCollection(item, TagsExtension.TAGS, getUserTag());
         }

         private ObservableItem createItem(ItemCreatorDirector director, File file) {
            ObservableItem item = SimpleItemBuilder.createItemFromPath(file.getAbsolutePath());
            try {
               item = director.construct(file);
            } catch (ContextException e) {
               log.info("file not added due to LibraryContext problem");
               log.info("file: " + file.getAbsolutePath());
               log.info("problem: " + e.toString());
            } catch (Exception e) {
               log.info("casini nella costruzione di Item");
               e.printStackTrace();
            }
            return item;
         }

      };
      createItemsFromFiles.setName("Creating library items...");
      createItemsFromFiles.setElementsType("folders");
      // attachStandardListenersTo(createItemsFromFiles, "Creating library
      // items...", "folders");
      return createItemsFromFiles;
   }

   protected boolean isDecadeTagFlagEnabled() {
      return decadeTagFlagEnabled;
   }

   private SwingBackgroundTask createScanFilesTask() {
      SwingBackgroundTask scanForFiles = new CollectionBackgroundTask(getSubFolders()) {

         protected void applyTo(Object singleElement) {
            File folder = (File) singleElement;
            addFolderContext(FolderInfo.createFolderContext(folder));
            library.addManagedFolder(folder.getAbsolutePath());
         }

      };
      scanForFiles.setName("Searching for audio files...");
      return scanForFiles;
   }

   private SwingBackgroundTask createScanSubfoldersTask() {
      SwingBackgroundTask scanForSubfolders = new IndeterminateBackgroundTask() {

         protected Object executeIndeterminateOperation() {
            List subFolders = FileUtils.listSubFolders(folderToAdd, true);
            subFolders.add(folderToAdd);
            addSubFolders(subFolders);
            return subFolders;
         }

      };
      scanForSubfolders.setName("Searching for audio files...");
      return scanForSubfolders;
   }

   protected boolean isDateTagFlagEnabled() {
      return dateTagFlagEnabled;
   }

   public void setDateTagFlagEnabled(boolean dateTagFlagEnabled) {
      this.dateTagFlagEnabled = dateTagFlagEnabled;
   }

   private Collection getFoldersContext() {
      return files;
   }

   private Collection getSubFolders() {
      return subfolders;
   }

   protected void addFolderContext(FolderContext context) {
      files.add(context);

   }

   protected void addSubFolders(List list) {
      subfolders.addAll(list);
   }

   public boolean isTrustId3Tag() {
      return trustId3Tag;
   }

   public void setTrustId3Tag(boolean trustId3Tag) {
      this.trustId3Tag = trustId3Tag;
   }

   public boolean isTrustTextFile() {
      return trustTextFile;
   }

   public void setTrustTextFile(boolean trustTextFile) {
      this.trustTextFile = trustTextFile;
   }

   public String getUserTag() {
      return userTag;
   }

   public void setUserTag(String userTag) {
      this.userTag = userTag;
   }

   private static DefaultSongBuilder createSongBuilder(final FolderContext folderContext) {
      return new DefaultSongBuilder() {

         public void extractSpecificFileInfo(File file) {
            item.put(SongAttributes.ALBUM, folderContext.getTracklist().getAlbum());
            item.put(SongAttributes.ARTIST, folderContext.getTracklist().getArtist());
            item.put(SongAttributes.YEAR, folderContext.getTracklist().getYear());
         }
      };
   }

   public void setDateAdded(Long dateAddedMillis) {
      this.dateAddedMillis = dateAddedMillis;
   }

   public void setDecadeTagFlagEnabled(Boolean b) {
      this.decadeTagFlagEnabled = b;
   }

}
