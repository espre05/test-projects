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

import jin.collection.core.Criteria;
import junit.framework.TestCase;
import net.della.mplatform.application.datatypes.Item;
import net.della.stuff.generic.file.FileUtils;

public class CriteriaFactoryTest extends TestCase {

   public void testCleanManagedFoldersCriteria() throws Exception {
      Criteria criteria = CriteriaFactory.createFolderExistsAndNotEmptyCriteria();
      assertFalse(criteria.match(FileUtils.getCurrentFolderAbsolutePath()
            + "/test/resources/folders/empty folder/"));
      assertFalse(criteria.match(FileUtils.getCurrentFolderAbsolutePath()
            + "/test/resources/folders/empty folder2/"));
      assertTrue(criteria.match(FileUtils.getCurrentFolderAbsolutePath()
            + "/test/resources/folders/topFolder/"));
      assertFalse(criteria.match(FileUtils.getCurrentFolderAbsolutePath()
            + "/test/resources/folders/topFolder/subFolder1"));
   }

   public void testCleanItemsCriteria() throws Exception {
      Criteria criteria = CriteriaFactory.createItemExistsCriteria();
      String path1 = FileUtils.getCurrentFolderAbsolutePath()
            + "/test/resources/mp3/artist/Beck/Mellow Gold/Beck - Mellow Gold - 01 - Loser.mp3";
      Item item1 = SimpleItemBuilder.createItemFromPath(path1);
      assertTrue(criteria.match(item1));

      String path2 = FileUtils.getCurrentFolderAbsolutePath()
            + "/test/resources/mp3/artist/Beck/Mellow Gold/Beck - Mellow Gold - 04 - Fourth.mp3";
      Item item2 = SimpleItemBuilder.createItemFromPath(path2);
      assertFalse(criteria.match(item2));

      String path3 = FileUtils.getCurrentFolderAbsolutePath()
            + "/test/resources/mp3/archive/The White Stripes/Elephant/The White Stripes - Elephant - 01 - Seven Nation Army.mp3";
      Item item3 = SimpleItemBuilder.createItemFromPath(path3);
      assertTrue(criteria.match(item3));

      // TopFolders topFolders = new TopFolders("topFolders.txt");
      // topFolders.addTopFolder(FileUtils.getCurrentFolderAbsolutePath()
      // + "/test/resources/mp3/artist/");
      // LibraryContext context = new LibraryContext(topFolders);
      // criteria = CriteriaFactory.createItemInsideTopFoldersCriteria(context);
      // assertTrue(criteria.match(item1));
      // assertTrue(criteria.match(item2));
      // assertFalse(criteria.match(item3));

   }
}
