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

import junit.framework.TestCase;
import net.della.mplatform.application.datatypes.Item;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.persistence.BasicLibrary;
import net.mcube.library.LibraryImpl;

public class ProxyTest extends TestCase {

   private LibraryImpl library;

   private String commonPath;

   protected void setUp() throws Exception {
      super.setUp();
      String basePath = "/test/resources/mp3/";
      library = (LibraryImpl) BasicLibrary.getDefault();
      String archive = "archive/";
      String elephantFolder = "/The White Stripes/Elephant/";
      String elephant = elephantFolder + "The White Stripes - Elephant";
      commonPath = basePath + archive + elephant;

   }

   protected void tearDown() throws Exception {
      String databaseFolderPath = "test/database";
      File databaseFolder = new File(databaseFolderPath);
      databaseFolder.delete();

      library.clear();
   }

   public void testAddItems() {
      AddItemsProxy op = new AddItemsProxy(library);

      Item nullItem1 = SimpleItemBuilder.createItemFromPath(commonPath + " - 01 - Seven Nation Army.mp3");
      Item nullItem2 = SimpleItemBuilder.createItemFromPath(commonPath + " - 02 - Black Math.mp3");

      op.add(nullItem1);
      op.add(nullItem2);

      assertFalse(library.contains(commonPath + " - 01 - Seven Nation Army.mp3"));
      assertFalse(library.contains(commonPath + " - 02 - Black Math.mp3"));
      assertEquals(0, library.countItems());

      assertEquals(2, op.run());
      assertTrue(library.contains(commonPath + " - 01 - Seven Nation Army.mp3"));
      assertTrue(library.contains(commonPath + " - 02 - Black Math.mp3"));
      assertEquals(2, library.countItems());

      assertEquals(0, op.run());
      assertEquals(2, library.countItems());
   }

   public void testRemoveItems() {
      AddItemsProxy op = new AddItemsProxy(library);

      ObservableItem nullItem1 = SimpleItemBuilder.createItemFromPath(commonPath
            + " - 01 - Seven Nation Army.mp3");
      ObservableItem nullItem2 = SimpleItemBuilder.createItemFromPath(commonPath + " - 02 - Black Math.mp3");

      op.add(nullItem1);
      op.add(nullItem2);
      op.run();

      RemoveItemsProxy removeOp = new RemoveItemsProxy(library);
      removeOp.add(nullItem2);

      assertEquals(1, removeOp.run());
      assertEquals(1, library.countItems());
      assertFalse(library.contains(nullItem2));
      assertTrue(library.contains(nullItem1));

      assertEquals(0, removeOp.run());
      assertEquals(1, library.countItems());

      removeOp.add(nullItem1);
      assertEquals(1, removeOp.run());
      assertEquals(0, library.countItems());

   }

}
