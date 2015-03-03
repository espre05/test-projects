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
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package net.mcube.extensions.songs;

import javax.swing.Action;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.mcube.gui.GlobalCollectionAction;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SongActionToolkit {

    public static Action formatData(Application application) {
        // final StringFormatter sf = new StringFormatter();
        /*
         * sf.addLower("and"); sf.addLower("at"); sf.addLower("by");
         * sf.addLower("of"); sf.addLower("from"); sf.addLower("in");
         * sf.addLower("the"); sf.addLower("and"); sf.addLower("feat.");
         * sf.addLower("featuring"); sf.addLower("feat"); sf.addLower("a");
         * sf.addLower("on");
         */
        // sf.setStyle(StringFormatter.FIRST_CAPITAL);
        // sf.setForceFirstCapital(true);
        return new GlobalCollectionAction(application) {
            protected void execute(ObservableItem item) {
                item.formatData();
            }
        };
    }

    public static Action convertID3Tag(Application application) {

        return new GlobalCollectionAction(application) {
            protected void execute(ObservableItem item) {
                Song song = (Song) item;
                song.removeID3v1();
                song.updateID3Tag(true);
            }
        };
    }

    public static Action renameFileFromTag(Application application) {

        return new GlobalCollectionAction(application) {
            protected void execute(ObservableItem item) {
                item.renameFileFromData();
            }
        };
    }

    public static Action removeID3Tag(Application application) {

        return new GlobalCollectionAction(application) {
            protected void execute(ObservableItem item) {
                Song song = (Song) item;
                song.removeTag();
            }
        };
    }

    public static Action updateID3Tag(Application application) {

        return new GlobalCollectionAction(application) {
            protected void execute(ObservableItem item) {
                Song song = (Song) item;
                song.updateID3Tag(true);
            }
        };
    }

//    public static Action reReadInfo(final LibraryImpl library, AbstractListView view) {
//        return new SelectionAction(view) {
//
//            public void actionPerformed(ActionEvent e) {
//
//                for (Iterator it = getSelectionList().iterator(); it.hasNext();) {
//                    Item item = (Item) it.next();
//                    try {
//						ItemBuilderFacade.updateItemFromFile(item, library.getContext());
//					} catch (ContextException e1) {					
//						LogFactory.getLog(this.getClass()).error(e1);
//					}
//                }
//                MMEvent event = new MMEvent();
//                event.setType(View.DATA_MODIFIED);
//                RuntimeEnvironment.getCurrentApplication().notifyEvent(event);
//
//            }
//            protected void execute(Item item) {
//            }
//
//        };
//    }

}