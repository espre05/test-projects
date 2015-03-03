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

package net.mcube.extensions.externalPlayer;

import java.awt.event.*;
import java.io.File;
import java.util.*;

import javax.swing.*;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.BootLoader;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.mplatform.application.gui.structure.Page;
import net.della.mplatform.application.gui.structure.View;
import net.della.stuff.generic.config.ConfigElement;
import net.della.stuff.generic.config.Configuration;
import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventListener;
import net.della.stuff.generic.file.FileUtils;
import net.mcube.ApplicationEvents;
import net.mcube.extensions.album.AlbumExtension;
import net.mcube.extensions.compilations.MyCompilations;
import net.mcube.extensions.songs.Song;
import net.mcube.extensions.songs.SongSet;
import net.mcube.extensions.tracksView.TracksExtension;

import org.apache.commons.logging.LogFactory;

import della.swaf.extensions.gui.AbstractCellListView;

public class ExternalPlayerExtension implements Extension {

   private static ExternalPlayerExtension instance;

   private Application application;

   // public static final String ALBUM_PLAYED = "album played";

   // public static final String SONG_PLAYED = "song played";

   public static final String SONGS_TO_PLAY = "songs to play";

   private final Map playersMap;

   private Configuration config;

   public static ExternalPlayerExtension getDefault() {
      return instance;
   }

   public ExternalPlayerExtension() {

      playersMap = new HashMap();

   }

   public void init(Application application) {
      this.application = application;
      ApplicationWindow appWindow = application.getWindow();
      DesktopUtils.jarFilename = BootLoader.getOSSpecificLibPath() + File.separator + "jdic.jar";

      configureView((AbstractCellListView) appWindow.getView(AlbumExtension.VIEW_ID));
      configureView((AbstractCellListView) appWindow.getView(TracksExtension.VIEW_ID));
      configureView((AbstractCellListView) appWindow.getView(MyCompilations.VIEW_ID));

      instance = this;

      if (!checkNativeSupport()) {
         LogFactory.getLog(this.getClass()).info(
               "No native support. mCube will not play music with default player");
         return;
      }

      String folder = application.getExtensionsFolder() + File.separator + "externalPlayer" + File.separator;
      try {
         config = Configuration.getDefaultInFolder(folder);
         addNewPlayer("mp3");
         addNewPlayer("ogg");
         addNewPlayer("wav");
         addNewPlayer("ape");
         addNewPlayer("mpc");
         addNewPlayer("wma");
      } catch (Exception e) {
         LogFactory
               .getLog(this.getClass())
               .info(
                     "due to some configuration file loading problem mCube will not be able to launch the external media player to play songs");
         e.printStackTrace();
         return;
      }

      application.addListener(new EventListener() {

         public void eventHappened(Event event) {
            String type = (String) event.get(Event.TYPE);
            if (type.equals(ApplicationEvents.PLAY_REQUEST)) {
               List songs = (List) event.get(SONGS_TO_PLAY);
               ExternalPlayerExtension.getDefault().play(songs, false);
            }
         }

      });

      // AbstractPlayer oggPlayer = new AbstractPlayer();
      // oggPlayer.setCommand(DesktopUtils.getCommand("open", "ogg"));
      // playersMap.put(mimeTable.get("ogg"), oggPlayer);
   }

   private boolean checkNativeSupport() {
      AssociationService service;
      try {
         service = DesktopUtils.loadAssociationService();
      } catch (LibraryNotFoundException e) {
         return false;
      }
      Association fileExtensionAssociation = null;
      try {
         fileExtensionAssociation = service.getFileExtensionAssociation("mp3");
      } catch (UnsatisfiedLinkError e) {
         return false;
      } catch (NoClassDefFoundError e) {
         return false;
      }
      return true;
   }

   private void addNewPlayer(String ext) {
      MediaPlayer player = new MediaPlayer();
      String command = "";
      try {
         command = DesktopUtils.getCommand("open", ext);
      } catch (Exception e) {
         e.printStackTrace();
         return;
      }
      command = cleanCommandString(command);
      command = cleanVirgolette(command);

      player.setCommand(command);
      playersMap.put(DesktopUtils.mimeTable.get(ext), player);

      File cmdFile = new File(command);
      // command = cmdFile.getName();
      command = FileUtils.fileNameWithNoExtension(cmdFile);
      command = command.toLowerCase();
      ConfigElement element = config.getElementById("player", command);
      if (element == null)
         return;
      String enqueueOption = element.getAttribute("enqueue");
      player.setEnqueueOption(enqueueOption);

   }

   private String cleanVirgolette(String command) {
      if (command.length() == 0)
         return command;
      StringBuffer sb = new StringBuffer(command);
      if (sb.charAt(0) == '"')
         sb.replace(0, 1, "");
      if (sb.charAt(sb.length() - 1) == '"')
         sb.replace(command.length() - 2, command.length() - 1, "");
      return sb.toString();
   }

   public void configureView(AbstractCellListView view) {

      final PlayerAction playAction = new PlayerAction(application, false);
      PlayerAction enqueueAction = new PlayerAction(application, true);

      InputMap inputMap = view.getInputMap();
      ActionMap actionMap = view.getActionMap();
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), playAction.getValue(Action.NAME));
      actionMap.put(playAction.getValue(Action.NAME), playAction);
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.SHIFT_DOWN_MASK), enqueueAction
            .getValue(Action.NAME));
      actionMap.put(enqueueAction.getValue(Action.NAME), enqueueAction);

      view.addMouseListener(new MouseAdapter() {

         public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
               playAction.actionPerformed(null);
            }
         }
      });

      view.addToPopup(View.CONTEXT_MENU, new JMenuItem(playAction));
      view.addToPopup(View.CONTEXT_MENU, new JMenuItem(enqueueAction));
      view.addToPopup(View.CONTEXT_MENU, new JPopupMenu.Separator());

   }

   private static String cleanCommandString(String command) {
      if (command.length() == 0)
         return command;
      int i = command.indexOf('%');
      char s = command.charAt(i + 1);
      command = command.replaceAll(" \"%" + s + "\"", "");
      return command;
   }

   public String getId() {
      return "Player";
   }

   public View getView() {
      // TODO Auto-generated method stub
      return null;
   }

   public Page getPage() {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * 
    * @param songList:
    *           list of String objects rapresenting path of files to play
    * @param enqueue
    */
   public void play(List songPathList, boolean enqueue) {
      String song = (String) songPathList.get(0);
      String ext = FileUtils.getExtension(new File(song));
      // String ext = song.getData(ItemAttributes.EXTENSION);
      MediaPlayer player = (MediaPlayer) playersMap.get(DesktopUtils.mimeTable.get(ext));
      player.play(songPathList, enqueue);
   }

   public void play(SongSet songSet, boolean enqueue) {
      List songPathList = new ArrayList();
      for (Iterator it = songSet.childIterator(); it.hasNext();) {
         Song element = (Song) it.next();
         songPathList.add(element.getPath());
      }
      play(songPathList, enqueue);
   }

}