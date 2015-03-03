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
 * Created on 13-feb-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.songs;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.util.WidgetFactory;
import net.mcube.datatypes.ItemBuilderFactory;
import net.mcube.extensions.tracksView.TracksExtension;
import net.mcube.library.LibraryImpl;
import della.swaf.extensions.gui.AbstractCellListView;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SongsExtension implements Extension {

   static public final String ID = "songs";
   public static final String SONG_TYPE = "song";

   public void init(Application application) {
      LibraryImpl library = (LibraryImpl) application.getLibrary();

      ItemBuilderFactory.getInstance().addBuilder("mpc", new DefaultSongBuilder());
      ItemBuilderFactory.getInstance().addBuilder("ape", new DefaultSongBuilder());
      ItemBuilderFactory.getInstance().addBuilder("wma", new DefaultSongBuilder());
      ItemBuilderFactory.getInstance().addBuilder("wav", new DefaultSongBuilder());
      ItemBuilderFactory.getInstance().addBuilder("ogg", new DefaultSongBuilder());
      ItemBuilderFactory.getInstance().addBuilder("mp3", new DefaultSongBuilder());

      // library.addDb(ID, new SongSerializerLoader(library.getTopFolders(),
      // "songs.mml"));

      AbstractCellListView view = (AbstractCellListView) application.getWindow().getView(
            TracksExtension.VIEW_ID);
      JMenu subMenu = new JMenu("Standard Operations");
      // popup.add(new JPopupMenu.Separator());
      subMenu.add(WidgetFactory.createItem(SongActionToolkit.formatData(application), "format data"));
      subMenu.add(new JPopupMenu.Separator());
      subMenu.add(WidgetFactory.createItem(SongActionToolkit.renameFileFromTag(application),
            "rename file from tag"));
      subMenu.add(new JPopupMenu.Separator());
      subMenu.add(WidgetFactory.createItem(SongActionToolkit.removeID3Tag(application), "remove ID3 Tag"));
      subMenu.add(WidgetFactory.createItem(SongActionToolkit.convertID3Tag(application),
            "convert ID3 Tag to v2"));
      subMenu.add(WidgetFactory.createItem(SongActionToolkit.updateID3Tag(application), "update ID3 Tag"));
      subMenu.add(new JPopupMenu.Separator());
      // subMenu.add(WidgetFactory.createItem(SongActionToolkit.reReadInfo((LibraryImpl)
      // library,
      // view), "re-read info from file"));
      subMenu.add(new JPopupMenu.Separator());

      view.addToPopup(View.CONTEXT_MENU, subMenu);
      Action removeAction = LibraryActionToolkit.removeFromLibrary(library, application);
      view
            .addToPopup(View.CONTEXT_MENU, WidgetFactory.createItem(removeAction, KeyEvent.VK_DELETE,
                  "remove"));
      Action deleteAction = LibraryActionToolkit.deleteFile(library, application);
      view.addToPopup(View.CONTEXT_MENU, WidgetFactory.createItem(deleteAction, KeyEvent.VK_DELETE,
            InputEvent.SHIFT_DOWN_MASK, "remove and delete"));
      view.addToPopup(View.CONTEXT_MENU, new JPopupMenu.Separator());

      InputMap inputMap = view.getInputMap();
      ActionMap actionMap = view.getActionMap();
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), removeAction.getValue(Action.NAME));
      actionMap.put(removeAction.getValue(Action.NAME), removeAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK), deleteAction
            .getValue(Action.NAME));
      actionMap.put(deleteAction.getValue(Action.NAME), deleteAction);
   }

   public String getId() {
      return ID;
   }

}
