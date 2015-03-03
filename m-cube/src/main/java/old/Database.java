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

package old;

import java.io.File;

import net.della.mplatform.core.datatypes.FileItemAttributes;
import net.della.mplatform.core.datatypes.Item;
import net.della.mplatform.core.datatypes.ObservableItem;
import net.della.stuff.generic.config.Configuration;
import net.mcube.util.query.Query;

import com.sun.org.apache.xml.internal.security.signature.ObjectContainer;


public class Database {

   private static final String DEFAULT_EXTENSION = ".yap";

   private ObjectContainer db;

   public Database(String parentFolderName, String fileName) {
      String parentFolderPath = File.separator + parentFolderName;
      File parentFolder = new File(parentFolderPath);
      if (!parentFolder.exists())
         parentFolder.mkdir();
      db = Db4o.openFile(parentFolderPath + File.separator + fileName + DEFAULT_EXTENSION);
   }

   public void update(ObservableItem dbEntry) {
      ObjectSet result = getFromDB(dbEntry);
      String relPath = dbEntry.getString(FileItemAttributes.RELATIVE_PATH);
      if (result.size() == 0)
         throw new IllegalStateException("no match found for " + relPath);
      if (result.size() > 1)
         throw new IllegalStateException("found more than one match for " + relPath);
      Object resultItem = result.next();
      assert resultItem.equals(dbEntry);
      add((Item) resultItem);

   }

   public void close() {
      db.close();
   }

   public void add(Item item) {
      db.set(item);
   }

   public final ObjectSet<Object> getFromDB(Object bean) {
      return db.get(bean);
   }

   protected Query newQuery() {
      return db.query();
   }

   protected Query newQuery(Class className) {
      Query query = newQuery();
      query.constrain(className);
      return query;
   }

   public Configuration configure() {
      return Db4o.configure();
   }

   public void delete(Item item) {
      db.delete(item);
   }

}
