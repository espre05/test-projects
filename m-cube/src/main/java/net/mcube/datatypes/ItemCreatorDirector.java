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

package net.mcube.datatypes;

import java.io.File;
import java.util.Map;

import net.della.mplatform.application.datatypes.Item;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.persistence.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class ItemCreatorDirector {

   private DefaultItemBuilder builder;

   private Context context;

   public ItemCreatorDirector(DefaultItemBuilder builder, Context context) {
      this.builder = builder;
      this.context = context;
   }

   // this class would be the Creator in Factory pattern and the director for
   // Builder pattern
   // basicItemBuilder is the Builder that builds a part of the whole item.
   public ObservableItem construct(File file) throws ContextException {
      Log log = LogFactory.getLog(this.getClass());
      log.debug("creating item for: " + file.getAbsolutePath());
      builder.createItem();
      // builder.buildBasicInfo(file);
      String topFolder = context.getTopFolder(file.getAbsolutePath());
      if (topFolder == null)
         throw new ContextException(
               "Library cannot handle this file cause it is not under any of the Top Managed Folders. Please, add to the Library, as Top Folder, a parent folder of this file before creating a corresponging Item");
      builder.buildPath(file, new File(topFolder), context.getID(topFolder));

      builder.fillCustomFields(file);
      log.debug("extract tag...");
      builder.extractSpecificFileInfo(file);
      log.debug("tag extracted...");
      builder.guessMissingData(file);
      builder.extractInfoFromGatheredData();
      builder.fixSomeProblems();

      log.debug("item created succesfully");

      return builder.getItem();
   }

   public Item construct(Map attrs) {
      builder.createItem();
      ObservableItem item = builder.getItem();
      item.setAllData(attrs);
      createDerivedProperties(item, context);
      return item;
   }

   private void createDerivedProperties(Item item, Context context) {
      // String imagePath =
      // item.getString(SongAttributes.ALBUM_COVER_FRONT_PATH);
      // item.setData(SongAttributes.ALBUM_COVER_FRONT, new
      // ImageProperty(imagePath, context.getImageCache()));
   }

}
