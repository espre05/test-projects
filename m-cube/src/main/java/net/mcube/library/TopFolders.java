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
 * Created on 14-mag-2005 
 * 
 */
package net.mcube.library;

import java.io.*;
import java.util.*;

import net.della.mplatform.application.datatypes.FileItemAttributes;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.stuff.generic.file.FileUtils;



/**
 * @author della
 * 
 */
public class TopFolders {

   private final Properties topFoldersMap;
   private File storeFile;

   TopFolders(String dbFilePath) throws Exception {
      this();
      storeFile = new File(dbFilePath);
      if (!storeFile.exists()) {
         try {
            storeFile.createNewFile();
         } catch (IOException e) {
            throw new Exception("Impossible to create store file for TopFolders: "
                  + storeFile.getAbsolutePath(), e);
         }
      }
   }

   public TopFolders() {
      topFoldersMap = new Properties();
   }

   void addAll(Collection newTopFolders) {
      for (Iterator iter = newTopFolders.iterator(); iter.hasNext();) {
         ObservableItem folderItem = (ObservableItem) iter.next();
         String topFolderPath = folderItem.getString(FileItemAttributes.ABSOLUTE_PATH);
         String topFolderId = folderItem.getString(FileItemAttributes.VIRTUAL_PATH);
         addTopFolder(topFolderId, topFolderPath);
      }
   }

   /**
    * @deprecated
    */
   private String addTopFolder(String topFolderId, String newFolder) {
      String formattedNewFolder = FileUtils.normalizeFilePath(newFolder);
      // String formattedNewFolder = newFolder.replace('\\', '/');
      if (isFolderManaged(formattedNewFolder))
         return "$$";
      List topFoldersToMerge = searchTopFoldersUnder(formattedNewFolder);
      removeAll(topFoldersToMerge);
      topFolderId = new String("$" + topFoldersMap.size());
      topFoldersMap.put(topFolderId, formattedNewFolder);
      return topFolderId;
   }

   String createTopFolder(String newFolder) {
      String normalizedNewFolder = FileUtils.normalizeFilePath(newFolder);
      if (isFolderManaged(normalizedNewFolder))
         return "$$";
      List topFoldersToMerge = searchTopFoldersUnder(normalizedNewFolder);
      removeAll(topFoldersToMerge);
      String topFolderId = new String("$" + topFoldersMap.size());
      topFoldersMap.put(topFolderId, normalizedNewFolder);
      return topFolderId;
   }

   private void removeAll(List folders) {
      for (Iterator it = folders.iterator(); it.hasNext();) {
         String folder = (String) it.next();
         removeTopFolder(folder);
      }
   }

   public boolean isFolderManaged(final String folderPath) {
      File folder = new File(folderPath);
      if (isATopFolder(folderPath))
         return true;
      for (Iterator it = topFoldersMap.values().iterator(); it.hasNext();) {
         String topFolder = (String) it.next();
         if (FileUtils.isInASubfolder(folder, new File(topFolder)))
            return true;
      }
      return false;
   }

   public boolean isATopFolder(String folderPath) {
      return topFoldersMap.values().contains(folderPath);
   }

   List searchTopFoldersUnder(String folder) {
      List subFolderOfNewFolder = new ArrayList();
      for (Iterator it = topFoldersMap.values().iterator(); it.hasNext();) {
         String topFolder = (String) it.next();
         if (FileUtils.isInASubfolder(new File(topFolder), new File(folder)))
            subFolderOfNewFolder.add(topFolder);
      }
      return subFolderOfNewFolder;
   }

   public String getID(String path) {
      if (!isFolderManaged(path))
         throw new IllegalArgumentException(path + " is not managed by TopFolders");
      String id = getKey(path);
      if (id == null)
         throw new IllegalStateException("TopFolders does not have been assigned IDs");
      return id;

   }

   /**
    * @param newPath
    * @return key starting from the value passed
    */
   private String getKey(String value) {
      for (Iterator iter = topFoldersMap.keySet().iterator(); iter.hasNext();) {
         String key = (String) iter.next();
         if (topFoldersMap.getProperty(key).equals(value))
            return key;
      }
      return null;
   }

   /**
    * 
    */
   void load() {
      FileInputStream fileInputStream = null;
      try {
         fileInputStream = new FileInputStream(storeFile);
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         return;
      }
      try {
         topFoldersMap.clear();
         topFoldersMap.load(fileInputStream);
         // normalize();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   /**
    * 
    */
   void save() {
      try {
         topFoldersMap.store(new FileOutputStream(storeFile), "");
      } catch (FileNotFoundException e) {
         throw new RuntimeException("Unable to store TopFolders: ", e);
      } catch (IOException e) {
         throw new RuntimeException("Unable to store TopFolders: ", e);
      }

   }

   /**
    * @param id
    * @return
    */
   public String getPath(String id) {
      return topFoldersMap.getProperty(id);
   }

   public String getParentTopFolder(String path) {
      String topFolderPath = "";
      String normalizedPath = FileUtils.normalizeFilePath(path);
      for (Iterator it = topFoldersMap.values().iterator(); it.hasNext();) {
         topFolderPath = (String) it.next();
         if (normalizedPath.equals(topFolderPath))
            return topFolderPath;
         if (FileUtils.isInASubfolder(new File(normalizedPath), new File(topFolderPath)))
            return topFolderPath;
      }
      return null;
      // throw new IllegalArgumentException("file: " + path
      // + " is not managed. Managed Folders are: " + topFoldersMap);
   }

   public List getTopFoldersPaths() {
      List list = new LinkedList();
      for (Iterator it = topFoldersMap.values().iterator(); it.hasNext();) {
         String folderPath = (String) it.next();
         // list.add(new File(folderPath).getPath());
         list.add(folderPath);
      }
      return list;
   }

   int count() {
      return topFoldersMap.size();
   }

   void removeTopFolder(String folderPath) {
      String id = getID(folderPath);
      topFoldersMap.remove(id);
      normalize();
   }

   void normalize() {
      List paths = new ArrayList();
      for (Iterator it = topFoldersMap.values().iterator(); it.hasNext();) {
         String path = (String) it.next();
         paths.add(path);
      }
      // Collections.reverse(paths);
      topFoldersMap.clear();
      for (Iterator iter = paths.iterator(); iter.hasNext();) {
         String path = (String) iter.next();
         String id = "$" + topFoldersMap.size();
         topFoldersMap.setProperty(id, path);
      }
   }

   void removeAll() {
      topFoldersMap.clear();
   }

   public Iterator pathsIterator() {
      return getTopFoldersPaths().iterator();
   }

   /**
    * This method looks for a Top Folder that is a parent folder of the path
    * that is passed as a parameter.
    * 
    * @param path
    * @return The path relative to one of the Top Folders managed by the
    *         instance of TopFolders.
    */
   public String extractRelativePath(String path) {
      return path.replaceAll(getParentTopFolder(path), "");
   }

}
