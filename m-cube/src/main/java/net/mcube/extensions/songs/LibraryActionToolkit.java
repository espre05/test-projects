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
package net.mcube.extensions.songs;

import javax.swing.Action;
import javax.swing.JOptionPane;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.application.persistence.OldLibrary;
import net.mcube.gui.GlobalCollectionAction;
import net.mcube.library.operations.RemoveItemsProxy;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class LibraryActionToolkit {

    public static Action removeFromLibrary(final OldLibrary library, Application application) {
        return new GlobalCollectionAction(application) {
            protected void execute(ObservableItem song) {
            	RemoveItemsProxy proxy = new RemoveItemsProxy(library);
            	proxy.add(song);
            	proxy.run();
            }
        };
    }

    public static Action deleteFile(final OldLibrary library, Application application) {

        return new GlobalCollectionAction(application) {
            protected void execute(ObservableItem song) {
                int result = JOptionPane
                        .showConfirmDialog(
                                null,
                                "Are you sure you want to permanently delete selected songs from your disk?",
                                "Delete confirmation request", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.NO_OPTION)
                    return;
                RemoveItemsProxy proxy = new RemoveItemsProxy(library);
            	proxy.add(song);
            	proxy.run();
                song.deleteFromDisk();
            }
        };
    }

}