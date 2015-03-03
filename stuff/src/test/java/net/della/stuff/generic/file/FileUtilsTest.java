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

import java.io.File;

import junit.framework.TestCase;

import net.della.stuff.generic.file.FileUtils;

import org.junit.Test;

public class FileUtilsTest extends TestCase {

   private File testFolder;

   public void testJavaVersion() throws Exception {
      System.out.println(System.getProperty("java.version"));
   }

   @Override
   protected void setUp() throws Exception {
      super.setUp();
      if (testFolder == null || !testFolder.exists()) {
         String absolutePath = new File("").getAbsolutePath();
         testFolder = FileUtils.createFolder(new File(absolutePath), "test");
         testFolder.deleteOnExit();
      }

      FileUtils.deleteChildFoldersRecursively(testFolder);
      assertTrue("testFolder non è vuota: ", testFolder.listFiles().length == 0);
   }

   public void testNormalizeFilePath() {
      String filePath = "//test\\resources/mp3";
      assertEquals("/test/resources/mp3", FileUtils.normalizeFilePath(filePath));
      assertEquals("//test\\resources/mp3", filePath);
   }

   public void testCountSubfolders() {
      assertEquals(0, numberOfSubfoldersOfTestFolder());
      File folder1 = createASubFolder();
      FileUtils.createFolder(testFolder, "folder2");
      FileUtils.createFolder(testFolder, "folder3");
      FileUtils.createFolder(folder1, "folder11");

      assertEquals(3, FileUtils.countSubFolders(testFolder));
      assertEquals(4, FileUtils.countSubFoldersRecursive(testFolder));
   }

   public File createASubFolder() {
      return FileUtils.createFolder(testFolder, "folder1");
   }

   public void testDeleteAndSubdirs() throws Exception {
      File folder1 = createASubFolder();
      FileUtils.createFolder(folder1, "11");
      File folder12 = FileUtils.createFolder(folder1, "12");
      FileUtils.createFolder(folder12, "folder121");
      assertEquals(4, numberOfSubfoldersOfTestFolder());
      FileUtils.deleteFoldersRecursively(folder1);
      assertEquals(0, testFolder.listFiles().length);
   }

   public void testDeleteFoldersRecursively() throws Exception {
      File folder1 = createASubFolder();
      File folder11 = FileUtils.createFolder(folder1, "11");
      new File(folder11, "11.ext").createNewFile();
      assertEquals(1, folder11.listFiles().length);

      FileUtils.deleteFoldersRecursively(folder11);

      assertTrue("dovresti cancellare anche il contenuto delle cartelle", !folder11.exists());
      assertEquals(1, numberOfSubfoldersOfTestFolder());
   }

   public void testDeleteChildFoldersRecursively() throws Exception {
      File folder1 = createASubFolder();
      FileUtils.createFolder(folder1, "11");
      FileUtils.createFolder(folder1, "12");
      new File(folder1, "1.ext").createNewFile();
      assertEquals(3, folder1.list().length);
      assertEquals(2, FileUtils.countSubFolders(folder1));

      FileUtils.deleteChildFoldersRecursively(folder1);

      assertTrue(folder1.exists());
      assertEquals(1, numberOfSubfoldersOfTestFolder());
      assertEquals(0, FileUtils.countSubFolders(folder1));
      assertEquals(1, folder1.list().length);
      assertEquals("1.ext", folder1.list()[0]);
   }

   private int numberOfSubfoldersOfTestFolder() {
      return FileUtils.countSubFoldersRecursive(testFolder);
   }

   public void testDeleteSubDir() throws Exception {
      File folder1 = FileUtils.createFolder(testFolder, "prova");
      FileUtils.createFolder(folder1, "11");

      FileUtils.deleteChildFoldersRecursively(folder1);

      assertEquals(1, testFolder.listFiles().length);
   }

   public void testCountSubFolders() throws Exception {
      File folder1 = FileUtils.createFolder(testFolder, "1");
      FileUtils.createFolder(folder1, "11");
      FileUtils.createFolder(testFolder, "2");
      File folder3 = FileUtils.createFolder(testFolder, "3");
      FileUtils.createFolder(folder3, "31");
      assertEquals(3, FileUtils.countSubFolders(testFolder));
      assertEquals(5, FileUtils.countSubFoldersRecursive(testFolder));
   }

   public void testIsSubFolder() throws Exception {
      String currentPath = FileUtils.getCurrentFolderAbsolutePath();
      String archivePath = currentPath + "/test/topFolders.txt";
      String basePath = "/test/resources/mp3/";

      File basePathFolder = new File(currentPath + basePath);
      File archiveFolder = new File(currentPath + basePath + "archive" + "");
      File whiteStripesFolder = new File(currentPath + basePath + "archive" + "/The White Stripes");
      assertTrue(FileUtils.isInASubfolder(archiveFolder, basePathFolder));
      assertTrue(FileUtils.isInASubfolder(whiteStripesFolder, archiveFolder));
      assertTrue(FileUtils.isInASubfolder(whiteStripesFolder, basePathFolder));
      assertFalse(FileUtils.isInASubfolder(archiveFolder, whiteStripesFolder));
      assertFalse(FileUtils.isInASubfolder(basePathFolder, archiveFolder));

      File whiteFolder = new File(currentPath + basePath + "archive" + "/The White");
      assertFalse(FileUtils.isInASubfolder(whiteFolder, whiteStripesFolder));
      assertFalse(FileUtils.isInASubfolder(whiteStripesFolder, whiteFolder));
   }

   @Test
   public void testGetFilenameWithNoExtension() throws Exception {
      assertEquals("ARTOF90S", FileUtils.fileNameWithNoExtension("ARTOF90S.JPG"));
   }

}
