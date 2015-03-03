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

package net.mcube.extensions.musicbrainz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.sound.midi.Track;
import javax.swing.*;

import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.application.persistence.OldLibrary;
import net.della.mplatform.core.application.Application;
import net.della.mplatform.core.application.Extension;
import net.della.mplatform.core.background.AbstractJob;
import net.della.mplatform.core.background.swing.CollectionBackgroundTask;
import net.della.mplatform.core.datatypes.Item;
import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventFactory;
import net.mcube.extensions.album.AlbumExtension;
import net.mcube.extensions.songs.Song;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.extensions.songs.SongSet;

import com.jgoodies.binding.beans.Model;


public class MusicBrainzExtension implements Extension {

   private static final String ID = "musicbrainz";

   private Application application;

   private View albumView;

   private ApplicationWindow window;

   public void init(Application application) {
      this.application = application;
      window = application.getWindow();
      albumView = window.getView(AlbumExtension.VIEW_ID);
      AbstractAction updateAction = new AbstractAction("Update data from MusicBrainz") {

         public void actionPerformed(ActionEvent e) {
            updateAlbum();
         }

      };
      albumView.addToPopup(View.CONTEXT_MENU, new JMenuItem(updateAction));
   }

   void updateAlbum() {
      List albums = albumView.getSelection();
      CollectionBackgroundTask task = new CollectionBackgroundTask(albums) {

         protected void applyTo(Object singleElement) {
            try {
               checkAndUpdateAlbum(singleElement);
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      };
      application
            .runOperation(AbstractJob.newSimpleJob(task, "retriving album info from MusicBrainz...", ""));
   }

   void checkAndUpdateAlbum(Object singleElement) throws IOException {
      final SongSet songSet = (SongSet) singleElement;
      String albumName = songSet.getString(SongAttributes.ALBUM);
      final MusicBrainzImpl server = new MusicBrainzImpl();
      final List albums;
      Model model = server.findAlbumByName(albumName, 4, 10);
      albums = BeanPopulator.getAlbums(model);
      if (albums.size() == 1)
         updateData(songSet, (Album) albums.get(0));
      else if (albums.size() > 1) {
         JDialog dialog = new JDialog();
         DefaultListModel listModel = new DefaultListModel();
         final JList list = new JList(listModel);
         for (Iterator it = albums.iterator(); it.hasNext();) {
            Album album = (Album) it.next();
            ReleaseDate date = null;
            List releaseDates = album.getReleaseDates();
            if (releaseDates != null && releaseDates.size() > 0)
               date = (ReleaseDate) releaseDates.get(0);
            StringBuffer sb = new StringBuffer();
            sb.append(album.getArtist().getName() + " - " + album.getName() + ", " + album.getTracks().size()
                  + "tracks");
            if (date != null)
               sb.append(", released in " + date.getDate() + ", " + date.getCountry());
            listModel.addElement(sb.toString());
         }
         JPanel panel = new JPanel(new BorderLayout());
         panel.add(list, BorderLayout.CENTER);
         OkCancelButtonBar buttonBar = new OkCancelButtonBar(dialog);
         buttonBar.addOkActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               updateData(songSet, (Album) albums.get(list.getSelectedIndex()));
            }
         });
         panel.add(buttonBar, BorderLayout.SOUTH);
         dialog.setContentPane(panel);
         dialog.setAlwaysOnTop(true);
         window.showDialog(dialog);
      }
   }

   private void updateData(SongSet songSet, Album album) {
      // MusicBrainzTest.printTracks(album.getTracks());
      List tracks = album.getTracks();
      if (tracks == null)
         return;
      if (tracks.size() != songSet.countChilds()) {
         showUpdateFailedDialog(tracks);
         return;
      }
      int i = 0;
      for (Iterator it = tracks.iterator(); it.hasNext();) {
         Track track = (Track) it.next();
         Item item = songSet.getChild(i++);
         item.put(SongAttributes.ARTIST, track.getArtist().getName());
         item.put(SongAttributes.TITLE, track.getName());
         item.put(SongAttributes.TRACK_NUMBER, track.getNumber() + "");
         List releaseDates = album.getReleaseDates();
         if (releaseDates != null && releaseDates.size() > 0) {
            ReleaseDate date = (ReleaseDate) releaseDates.get(0);
            item.put(SongAttributes.YEAR, date.getDate());
         }
         Song song = (Song) item;
         song.updateID3Tag(true);
      }
      Event event = EventFactory.createEvent(this, OldLibrary.DATA_MODIFIED);
      event.set(SongAttributes.ALBUM, "");
      application.fireEvent(event);
   }

   private void showUpdateFailedDialog(Collection tracks) {
      // JOptionPane
      // .showConfirmDialog(
      // JOptionPane.getFrameForComponent(albumView.getViewComponent()),
      // "Request was a success but the number of file for this album in your
      // library is different from the number I found on MusicBrainz database,
      // so I cannot update your data.",
      // "Update Failed...", JOptionPane.OK_OPTION,
      // JOptionPane.INFORMATION_MESSAGE);
      JDialog dialog = new JDialog();
      JLabel label = new JLabel(
            "The number of file for this album in your library is different from the number I found on MusicBrainz database, so I cannot update your data.");
      Vector tracksVector = new Vector(tracks);
      DefaultListModel listModel = new DefaultListModel();
      final JList list = new JList(listModel);
      for (Iterator it = tracks.iterator(); it.hasNext();) {
         Track track = (Track) it.next();
         listModel.addElement(track.getName());
      }
      JPanel panel = new JPanel(new BorderLayout());
      OkCancelButtonBar buttonBar = new OkCancelButtonBar(dialog);
      panel.add(label, BorderLayout.NORTH);
      panel.add(list, BorderLayout.CENTER);
      panel.add(buttonBar, BorderLayout.SOUTH);
      dialog.setContentPane(panel);
      dialog.setAlwaysOnTop(true);
      window.showDialog(dialog);

   }

   private JToolBar createOkCancelButtonBar() {
      JToolBar buttonBar = new JToolBar();
      JButton okButton = new JButton();
      buttonBar.add(okButton);
      JButton cancelButton = new JButton();
      buttonBar.add(cancelButton);
      return buttonBar;
   }

   public String getId() {
      return ID;
   }

}
