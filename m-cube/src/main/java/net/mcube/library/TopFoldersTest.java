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

import java.util.List;

import junit.framework.TestCase;
import net.della.stuff.generic.file.FileUtils;


public class TopFoldersTest extends TestCase {

   private String currentPath;

   private String archiveFilePath;

   private TopFolders topFolders;

   private String mp3Path;

   private String resourcesPath;

   public void setUp() throws Exception {
      currentPath = FileUtils.getCurrentFolderAbsolutePath();
      archiveFilePath = currentPath + "/test/topFolders.txt";
      topFolders = new TopFolders(archiveFilePath);
      resourcesPath = "/test/resources/";
      mp3Path = resourcesPath + "mp3/";
   }

   public void testCreateTopFolder() {
      String id = topFolders.createTopFolder(currentPath + mp3Path + "archive");
      assertEquals("$0", id);
      id = topFolders.createTopFolder(currentPath + mp3Path + "archive");
      assertEquals("$$", id);
      id = topFolders.createTopFolder(currentPath + mp3Path + "artist");
      assertEquals("$1", id);
      id = topFolders.createTopFolder(currentPath + mp3Path + "albums");
      assertEquals("$2", id);
   }

   public void testDelete() throws Exception {
      topFolders.createTopFolder(currentPath + mp3Path + "artist");
      topFolders.createTopFolder(currentPath + mp3Path + "archive");
      topFolders.createTopFolder(currentPath + mp3Path + "albums");
      topFolders.removeTopFolder(currentPath + mp3Path + "artist");
      try {
         topFolders.getID(currentPath + mp3Path + "artist");
         fail("Should raise an IllegalArgumentException");

      } catch (IllegalArgumentException expected) {
         assertTrue(true);
      }

      assertEquals("$0", topFolders.getID(currentPath + mp3Path + "archive"));
      assertEquals("$1", topFolders.getID(currentPath + mp3Path + "albums"));

      assertEquals("$2", topFolders.createTopFolder(currentPath + mp3Path + "artist"));
   }

   public void testSave() throws Exception {
      topFolders.createTopFolder(currentPath + mp3Path + "artist");
      topFolders.createTopFolder(currentPath + mp3Path + "archive");
      topFolders.createTopFolder(currentPath + mp3Path + "albums");
      topFolders.save();
      topFolders = new TopFolders(archiveFilePath);
      topFolders.load();
      assertEquals(3, topFolders.count());
      assertEquals("$0", topFolders.getID(currentPath + mp3Path + "artist"));
      assertEquals("$1", topFolders.getID(currentPath + mp3Path + "archive"));
      assertEquals("$2", topFolders.getID(currentPath + mp3Path + "albums"));
   }

   public void _testNormalizeMapTopFolder() throws Exception {

   }

   public void testAddWithMerge() {
      topFolders.createTopFolder(currentPath + mp3Path + "archive");
      topFolders.createTopFolder(currentPath + mp3Path + "artist");

      String newTopFolder = currentPath + mp3Path;
      topFolders.createTopFolder(newTopFolder);

      assertEquals("$0", topFolders.getID(newTopFolder));
      assertEquals(1, topFolders.count());
      assertEquals(newTopFolder, topFolders.getPath("$0"));
      assertNotATopFolder(currentPath + mp3Path + "artist");
      assertNotATopFolder(currentPath + mp3Path + "archive");
   }

   private void assertNotATopFolder(String path) {
      try {
         topFolders.getID(path);
         fail("Should raise an IllegalStateException");

      } catch (IllegalStateException expected) {
         assertTrue(true);
      }
   }

   public void testAddWithMergeComplex() {
      String mp3Parent = currentPath + mp3Path;
      topFolders.createTopFolder(mp3Parent + "archive");
      topFolders.createTopFolder(mp3Parent + "artist");

      String albumsParent = currentPath + resourcesPath + "albums";
      assertEquals("$2", topFolders.createTopFolder(albumsParent));

      assertEquals("$1", topFolders.createTopFolder(mp3Parent));
      assertEquals("$0", topFolders.getID(albumsParent));
      assertEquals(2, topFolders.count());
   }

   public void testGetVirtualID() {

      topFolders.createTopFolder(currentPath + mp3Path + "archive");

      String virtualID = topFolders.getID(currentPath + mp3Path + "archive");
      assertEquals("$0", virtualID);

      try {
         topFolders.getID(currentPath + mp3Path + "artist");
         fail("Should raise an IllegalArgumentException");

      } catch (IllegalArgumentException expected) {
         assertTrue(true);
      }

   }

   public void testIsFolderManaged() {
      topFolders.createTopFolder(currentPath + mp3Path + "archive");

      String filePath = currentPath
            + mp3Path
            + "/archive/The White Stripes/Elephant/The White Stripes - Elephant - 01 - Seven Nation Army.mp3";
      assertTrue(topFolders.isFolderManaged(filePath));

      filePath = currentPath + mp3Path
            + "/artist/Beck/Mellow Gold/Beck - Mellow Gold - 01 - Loser.mp3";
      assertFalse(topFolders.isFolderManaged(filePath));
   }

   /**
    * 
    */
   public void testGetParentTopFolder() {
      String filePath1 = currentPath
            + mp3Path
            + "archive/The White Stripes/Elephant/The White Stripes - Elephant - 01 - Seven Nation Army.mp3";

      assertNull(topFolders.getParentTopFolder(filePath1));

      topFolders.createTopFolder(currentPath + mp3Path + "archive");
      topFolders.createTopFolder(currentPath + mp3Path + "artist");
      assertEquals(currentPath + mp3Path + "archive", topFolders.getParentTopFolder(filePath1));

      String filePath2 = currentPath + mp3Path
            + "/artist/Beck/Mellow Gold/Beck/Beck - Mellow Gold - 01 - Loser.mp3";
      assertEquals(currentPath + mp3Path + "artist", topFolders.getParentTopFolder(filePath2));

      String folderPath1 = currentPath + mp3Path + "/artist/Beck/Mellow Gold/Beck/";
      assertEquals(currentPath + mp3Path + "artist", topFolders.getParentTopFolder(folderPath1));

      topFolders.removeAll();
      String folderPath2 = currentPath + mp3Path + "archive/The White/";
      String folderPath3 = currentPath + mp3Path + "archive/The White Stripes/";

      topFolders.createTopFolder(folderPath2);
      topFolders.createTopFolder(folderPath3);
      assertEquals(folderPath2, topFolders.getParentTopFolder(folderPath2
            + "The White Stripes - Elephant - 01 - Seven Nation Army.mp3"));
      assertEquals(folderPath3, topFolders.getParentTopFolder(folderPath3 + "Elephant/"
            + "The White Stripes - Elephant - 01 - Seven Nation Army.mp3"));

   }

   public void testSearchTopFoldersUnder() throws Exception {
      topFolders.createTopFolder(currentPath + mp3Path + "archive" + "/The White Stripes");
      List topFoldersToMerge = topFolders.searchTopFoldersUnder(currentPath + mp3Path + "archive"
            + "/The White");
      assertEquals(0, topFoldersToMerge.size());
   }

}
