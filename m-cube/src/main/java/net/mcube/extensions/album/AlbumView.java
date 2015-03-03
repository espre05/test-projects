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

package net.mcube.extensions.album;

import java.util.Comparator;
import java.util.HashMap;

import della.swaf.extensions.gui.TableView;

public class AlbumView extends TableView {

   public static final String SORT_BY_DATE_ADDED = "sorted by date added";
   static public final String SORT_BY_YEAR = "sorted by year";
   private AlbumExtension album;
   private boolean showArtist;

   public AlbumView(AlbumExtension album) {
      this.album = album;
      comparators = new HashMap();
      showArtist = true;
   }

   public void toggleExtendedPanel() {
      album.setUseExtendedCellPanel(!album.isUseExtendedCellPanel());
      album.storePrefs();

   }

   public void useComparator(String comparatorName) {
      changeComparator((Comparator) comparators.get(comparatorName));
      album.storePrefs(AlbumExtension.COMPARATOR_NAME);
   }

   public Object addComparator(String id, Comparator c) {
      return comparators.put(id, c);
   }

   public void setShowArtist(boolean b) {
      showArtist = b;
   }

   public boolean showArtist() {
      return showArtist;
   }

}
