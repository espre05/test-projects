/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.stuff.generic.file;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import jin.collection.core.Iter;
import jin.collection.core.Operation;
import net.della.stuff.generic.tree.TreeExplorer;


/*
 * Created on 9-nov-2003

 */

/**
 * @author Daniele
 */
public class FileUtils {

   public static final void copy(File file, File newFile) {
      BufferedInputStream bis = null;
      BufferedOutputStream bos = null;
      try {
         bis = new BufferedInputStream(new FileInputStream(file));
         bos = new BufferedOutputStream(new FileOutputStream(newFile));
         copy(bis, bos);
      } catch (IOException e) {
         throw new RuntimeIOException("Copy operation failed: ", e);
      } finally {
         try {
            bis.close();
            bos.close();
         } catch (IOException e) {
            throw new RuntimeIOException("Error closing stream: ", e);
         } catch (NullPointerException e) {
            throw new RuntimeIOException(
                  "Probably this streams were not initialized properly or the resource file does not exists: "
                        + file.getAbsolutePath() + " Error during copy operation: ", e);
         }
      }
   }

   public static final void copy(File file, Folder targetFolder) {
      copy(file, targetFolder, file.getName());
   }

   public static void copy(File file, Folder targeFolder, String newFileName) {
      copy(file, new File(targeFolder, newFileName));
   }

   public static void copy(InputStream in, File newFile) {
      try {
         FileOutputStream out = new FileOutputStream(newFile);
         copy(in, out);
         in.close();
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static final void copy(InputStream in, OutputStream out) throws IOException {

      synchronized (in) {
         synchronized (out) {
            byte[] buffer = new byte[256];
            while (true) {
               int bytesread = in.read(buffer);
               if (bytesread == -1)
                  break;
               out.write(buffer, 0, bytesread);
            }
         }
      }
   }

   /**
    * Not Recursive!
    */
   public static final List listSubFolders(File folder) {
      return listFiles(folder, FileFilters.DirectoryFileFilter);
   }

   /**
    * Not Recursive!
    */
   public static final File[] subFolders(File folder) {
      return folder.listFiles(FileFilters.DirectoryFileFilter);
   }

   /**
    * Not Recursive!
    */
   public static final List<File> listFiles(File folder, FileFilter fileFilter) {
      File[] files = folder.listFiles(fileFilter);
      List result = new LinkedList();
      if (files != null)
         for (int i = 0; i < files.length; i++) {
            result.add(files[i]);
         }
      return result;
   }

   public static List listSubFoldersRecursive(File folderToScan) {
      return listSubFolders(folderToScan, true);
   }

   public static List listSubFolders(File targetFolder, boolean recursive) {
      return new TreeExplorer(FolderNode.class, targetFolder).scan(recursive);
   }

   public static final String getExtension(File file) {
      return getExtension(file.getName());
   }

   public static String getExtension(String filename) {
      String ext = new String(filename);
      int i = filename.lastIndexOf('.');

      if (i > 0 && i < filename.length() - 1) {
         ext = filename.substring(i + 1);
      }
      return ext;
   }

   public static String fileNameWithNoExtension(File file) {
      return fileNameWithNoExtension(file.getName());
   }

   public static String fileNameWithNoExtension(String filename) {
      String ext = getExtension(filename);
      String result = filename.replaceAll("." + ext, "");
      return result;
   }

   /**
    * @param fileToCheck
    * @param folder
    * @return true if first File is in a subfolder of {@link #folder(File)}
    */
   public static boolean isInASubfolder(final File fileToCheck, File folder) {
      File parentFile = fileToCheck.getParentFile();
      while (parentFile != null) {
         if (parentFile.equals(folder))
            return true;
         parentFile = parentFile.getParentFile();
      }
      return false;
   }

   public static File getCurrentFolder() {
      return new File("");
   }

   public static String getCurrentFolderAbsolutePath() {
      return getCurrentFolder().getAbsolutePath().replace('\\', '/');
   }

   public static String normalizeFilePath(String path) {
      String result = path.replace('\\', '/');
      result = result.replace("//", "/");
      return result;
   }

   public static List listFiles(File folder) {
      return listFiles(folder, new FileFilter() {
         public boolean accept(File pathname) {
            return true;
         }
      });
   }

   public static boolean isFolderEmpty(File folder) {
      return folder.listFiles().length == 0;
   }

   // public static void setExtension(String ext) {
   // // TODO: implement
   // }

   public static File createFolder(File parentFolder, String folderName) {
      if (!parentFolder.exists()) {
         throw new RuntimeException(parentFolder + " does not exists");
      }
      if (!parentFolder.isDirectory()) {
         throw new RuntimeException(parentFolder + " is not a directory");
      }
      File folder = new File(parentFolder.getAbsolutePath() + File.separator + folderName);
      folder.mkdir();
      return folder;
   }

   public static void deleteFoldersRecursively(File dir) throws IOException {
      org.apache.commons.io.FileUtils.deleteDirectory(dir);
   }

   public static void deleteChildFoldersRecursively(File dir) {
      if (dir.isDirectory()) {
         Iter.forEach(listSubFolders(dir), new Operation() {
            public void execute(Object element) {
               File folder = (File) element;
               try {
                  org.apache.commons.io.FileUtils.deleteDirectory(folder);
               } catch (IOException e) {
                  throw new NonBlockingIOException("Error deleting directory: "
                        + folder.getAbsolutePath(), e);
               }
            }
         });
      }
   }

   private static void delete(File file) throws IOException {
      if (file.exists() && !file.delete()) {
         throw new IOException("Cannot delete " + file);
      }
   }

   /**
    * This is NOT recursive and does NOT count the folder passed as parameter
    */
   public static int countSubFolders(File folder) {
      return subFolders(folder).length;
   }

   public static int countSubFoldersRecursive(File folder) {
      return listSubFolders(folder, true).size();
   }

   public static boolean isLeaf(File folder) {
      if (countSubFolders(folder) == 0) {
         return true;
      }
      return false;
   }

   public static File mkdirs(File parentFolder, String directoryPath) throws IOException {
      File folder = new File(parentFolder, directoryPath);
      org.apache.commons.io.FileUtils.forceMkdir(folder);
      return folder;
   }

   public static Folder folder(File directory) {
      return new Folder(directory);
   }

   public static void copy(String originalFilePath, String targetFilePath) {
      copy(new File(originalFilePath), new File(targetFilePath));
   }

   public static void deleteFoldersRecursively(String dir) throws IOException {
      FileUtils.deleteFoldersRecursively(new File(dir));
   }

   public static URL toUrl(String path) throws IOException {
      URL[] ls = org.apache.commons.io.FileUtils.toURLs(new File[] { new File(path) });
      URL url = ls[0];
      return url;
   }

}
