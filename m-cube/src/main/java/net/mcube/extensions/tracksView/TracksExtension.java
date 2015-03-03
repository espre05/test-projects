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
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package net.mcube.extensions.tracksView;

import java.io.File;

import javax.swing.AbstractButton;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.util.WidgetFactory;
import net.della.stuff.generic.util.Command;
import net.mcube.extensions.externalPlayer.ExternalPlayerExtension;
import net.mcube.extensions.songs.SongSet;
import net.mcube.extensions.songs.comparators.ComparatorFactory;
import net.mcube.extensions.tracksView.renderer.standard.GenericTracksColumnRenderer;
import net.mcube.extensions.tracksView.renderer.standard.TrackColumnRenderer;
import ca.odell.glazedlists.BasicEventList;
import della.swaf.extensions.gui.ViewBuilder;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class TracksExtension implements Extension {

   private BasicEventList sourceList;

   protected TracksView view;

   protected String saveFilePath;

   private static final String PLAY = "play";

   public static final String VIEW_ID = "Tracks";

   public void init(Application application) {
      saveFilePath = application.getConfigFolder() + File.separator + getId() + "_Columns.xml";
      sourceList = new BasicEventList();
      initView();
      application.getWindow().registerView(getId(), view);

   }

   protected void initView() {

      view = new TracksView(sourceList);
      ViewBuilder.initTableView(view, sourceList, new TracksTableFormat(), ComparatorFactory.getInstance()
            .trackNumberSongComparator(), VIEW_ID);

      view.setName(getId());
      view.loadFromDisk(saveFilePath);

      view.setCellRenderer(0, new TrackColumnRenderer());
      view.setCellRenderer(1, new GenericTracksColumnRenderer());
      view.setCellRenderer(2, new GenericTracksColumnRenderer());
      view.enableSorter();
      view.setComparator(0, ComparatorFactory.getInstance().trackNumberSongComparator());

      AbstractButton playButton = WidgetFactory.createToolBarButton("", PLAY, "Play Selection", "Play");

      view.addButton(playButton, new Command() {

         public Object run() {
            SongSet songSet = new SongSet(ComparatorFactory.getInstance().trackNumberSongComparator());
            songSet.addAll(view.getDisplayedList());
            ExternalPlayerExtension.getDefault().play(songSet, false);
            return null;
         }

      });

   }

   public String getId() {
      return VIEW_ID;
   }

}