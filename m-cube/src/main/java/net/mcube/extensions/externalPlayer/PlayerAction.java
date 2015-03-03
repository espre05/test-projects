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
 * Created on 11-gen-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.externalPlayer;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.LinkedList;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.mcube.extensions.songs.Song;
import net.mcube.extensions.songs.SongSet;
import net.mcube.gui.GlobalCollectionAction;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class PlayerAction extends GlobalCollectionAction {

	protected boolean enqueue;

	public PlayerAction(Application application, boolean enqueue) {
		super(application);
		this.enqueue = enqueue;
		if (enqueue)
			putValue(Action.NAME, "Enqueue");
		else
			putValue(Action.NAME, "Play");
	}


	public void actionPerformed(ActionEvent e) {

		Object o = getCollection().get(0);
		if (o instanceof Song)
			executeSongs();
		else if (o instanceof SongSet)
			executeSongSets();

	}

	private void executeSongs() {

		LinkedList songList = new LinkedList();

		Iterator it = getCollection().iterator();
		while (it.hasNext()) {
			Song song = (Song) it.next();
			songList.add(song.getPath());
		}
		ExternalPlayerExtension.getDefault().play(songList, enqueue);
	}

	private void executeSongSets() {

		LinkedList songList = new LinkedList();

		for (Iterator it = getCollection().iterator(); it.hasNext();) {

			SongSet songSet = (SongSet) it.next();
			Iterator iter = songSet.listChilds().iterator();
			while (iter.hasNext()) {
				Song song = (Song) iter.next();
				songList.add(song.getPath());
			}

		}

		ExternalPlayerExtension.getDefault().play(songList, enqueue);
	}

	protected void execute(ObservableItem item) {

	}

}