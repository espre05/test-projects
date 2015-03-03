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

package net.mcube.extensions.rym;

import java.util.Iterator;
import java.util.List;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.datatypes.FileItem;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.persistence.OldLibrary;
import net.della.mplatform.background.AbstractJob;
import net.della.mplatform.background.TaskAdapter;
import net.della.mplatform.background.TaskList;
import net.della.mplatform.background.swing.CollectionBackgroundTask;
import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventListener;
import net.mcube.datatypes.ImageProperty;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.library.LibraryImpl;
import net.mcube.util.Delayer;
import net.mcube.util.query.Query;
import net.mcube.util.query.QueryList;
import old.form.*;
import ca.odell.glazedlists.EventList;

import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;


public class RateYourMusicExtension implements Extension {

   private final class RYMFetcherDelayer extends Delayer implements EventListener {
      private RYMFetcherDelayer(int delay) {
         super(delay);
      }

      protected void refresh() {
         beginFetchSession();
      }

      public void eventHappened(Event event) {
         Object type = event.get(Event.TYPE);
         if (type.equals(OldLibrary.DATA_MODIFIED) || type.equals(OldLibrary.ITEM_ADDED))
            if (!isFetching()) {
               handleUpdate();
            }
         // TODO: else schedule future fetching
         // else {
         //						
         // }
      }
   }

   private final class RTMAlreadyCheckedQuery extends Query {

      private RTMAlreadyCheckedQuery() {
         super();
      }

      public void execute(EventList baseList) {
         baseList.clear();
         List source = getSource();
         for (Iterator iter = source.iterator(); iter.hasNext();) {
            ObservableItem item = (ObservableItem) iter.next();
            if (!"y".equals(item.getString(RYM)))
               baseList.add(item);
            // else
            // ;
            // LogFactory.getLog(this.getClass()).info(
            // "questo album � gi� stato checkato su RYM");
         }
      }
   }

   private final class HasImagesQuery extends Query {
      public void execute(EventList baseList) {
         baseList.clear();
         List source = getSource();
         for (Iterator iter = source.iterator(); iter.hasNext();) {
            ObservableItem item = (ObservableItem) iter.next();
            if (!hasImages(item))
               baseList.add(item);
         }
      }

      private boolean hasImages(ObservableItem item) {
         if (item.getString(SongAttributes.ALBUM_COVER_FRONT_PATH).equals(
               ImageProperty.DEFAULT_CD_COVER_FILE_NAME))
            return false;
         if (item.getString(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL_PATH).equals(
               ImageProperty.DEFAULT_CD_COVER_THUMBNAIL_FILE_NAME))
            return false;
         return true;
      }
   }

   private static final String ID = "RateYourMusic";

   public static final String RYM = "rym info";

   private Application application;

   LibraryImpl library;

   private boolean fetching;

   private String username;

   public String getId() {
      return ID;
   }

   public void init(Application application) {
      this.application = application;
      library = (LibraryImpl) application.getLibrary();
      fetching = false;
      application.addListener(new RYMFetcherDelayer(1000));
      username = "";

      // AlbumBuilder albumBuilder = (AlbumBuilder) GroupItemBuilderFactory
      // .getBuilder(SongAttributes.ALBUM);
      // albumBuilder.add(new BuilderDecorator() {
      //
      // public void apply(ItemSet itemSet) {
      //
      // }
      //
      // public void apply(ItemSet itemSet, Map props) {
      // itemSet.setData(RYM, props.get(RYM));
      // }
      //
      // });
   }

   void beginFetchSession() {
      setFetching(true);

      if (!username.equals(""))
         runUpdateCoversTask(username);
      else
         requestUsername();
   }

   private void requestUsername() {
      final String bindProperty = "username";
      final ObservableItem fileItem = new FileItem();
      ModelBuilder modelBuilder = new PasswordModelBuilder(bindProperty);
      PropertyListenerFactory itemPropertyListenerFactory = new ItemPropertyListenerFactory();

      DataInputForm form = JFormPane.newForm(bindProperty, fileItem, modelBuilder,
            itemPropertyListenerFactory);

      String dialogTitle = "Insert your RateYourMusic username";
      FormDialogBuilder dialogBuilder = new PlainFormDialogBuilder();

      JFormPane.showDialog(form, dialogTitle, dialogBuilder);

      form.addListener(new FormListener() {

         public void okEventHappened() {
            username = fileItem.getString(bindProperty);
            if (!username.equals(""))
               runUpdateCoversTask(username);
         }

         public void cancelEventHappened() {
         }

      });

   }

   void runUpdateCoversTask(String username) {

      final RYMConnection rym = RYMConnection.getDefault(username);
      final CollectionBackgroundTask task = new CollectImagesTask(library, rym);
      task.setPausePeriod(5);
      task.setName("Fetching images from RYM...");
      task.setElementsType("albums");
      task.setPriority(Thread.MIN_PRIORITY);
      QueryList albums = library.getQueryResponse(SongAttributes.ALBUM);
      QueryList albumsWithoutImages = new QueryList(albums, new HasImagesQuery());
      QueryList albumsNeverCheckedOnRYM = new QueryList(albumsWithoutImages, new RTMAlreadyCheckedQuery());
      task.setCollection(albumsNeverCheckedOnRYM);
      AbstractJob abstractOperation = new AbstractJob() {

         protected TaskList getThreadPool() {
            TaskList scheduledThreadPool = TaskList.scheduledThreadPool(5);
            scheduledThreadPool.add(task);
            return scheduledThreadPool;
         }

      };
      task.addListener(new TaskAdapter() {
         public void onTerminate() {
            setFetching(false);
         }
      });
      // task.addPropertyChangeListener(new PropertyChangeListener() {
      //		
      // public void propertyChange(PropertyChangeEvent evt) {
      // if (evt.getPropertyName().)
      // }
      // });
      application.runScheduledOperation(abstractOperation);
   }

   public boolean isFetching() {
      return fetching;
   }

   public void setFetching(boolean fetching) {
      this.fetching = fetching;
   }

}
