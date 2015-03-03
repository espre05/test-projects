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
 * Created on 30-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.album;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.persistence.OldLibrary;
import net.mcube.gui.GlobalCollectionAction;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class AlbumActionToolkit {

   public static GlobalCollectionAction removeFromLibrary(final OldLibrary library,
         Application application) {
      return new GlobalCollectionAction(application) {

         protected void execute(ObservableItem item) {
            library.removeAll(item.listChilds());
         }
      };
   }

   public static GlobalCollectionAction removeAndDelete(final OldLibrary library,
         Application application) {

      return new GlobalCollectionAction(application) {

         public void actionPerformed(ActionEvent e) {
            // List albums = new ArrayList(getSelectionList());
            int result = JOptionPane.showConfirmDialog(null,
                  "Are you sure you want to permanently delete selected albums from your disk?",
                  "Delete confirmation request", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.NO_OPTION)
               return;
            super.actionPerformed(e);
            // for (Iterator it = albums.iterator(); it.hasNext();) {
            // ItemSet item = (ItemSet) it.next();
            // item.deleteFromDisk();
            // }
            // for (Iterator iter = albums.iterator(); iter.hasNext();) {
            // SongSet album = (SongSet) iter.next();
            // library.removeAll(album.getChilds());
            // }

         }

         protected void execute(ObservableItem item) {
            library.removeAll(item.listChilds());
            item.deleteFromDisk();
         }
      };
   }

}