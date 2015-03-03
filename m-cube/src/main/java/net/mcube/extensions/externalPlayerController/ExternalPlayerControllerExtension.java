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

package net.mcube.extensions.externalPlayerController;

import java.awt.Container;
import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToolBar;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.mplatform.docking.defaults.DefaultDockableView;
import net.della.mplatform.util.WidgetFactory;


public class ExternalPlayerControllerExtension implements Extension  {

	public static final String ID = "extPlayerController";

	public static final String VIEW_ID = ID + "View";

	private static IMediaPlayer playerInstance;

	private DefaultDockableView view;

	private PlayerController playerController;

	private IMediaPlayer player;

	private Application application;

	public void init(Application application) {

		this.application = application;
		ApplicationWindow mainWindow = application.getWindow();
		view = new DefaultDockableView("Control External Player");
		view.setId(VIEW_ID);
		view.setName("Player");
		mainWindow.registerView(VIEW_ID, view);

		File pathsFile = new File(application.getExtensionsFolder() + File.separator
				+ "externalPlayer" + File.separator + "players.properties");
		if (pathsFile.exists()) {
			Properties props = new Properties();
			try {
				props.load(new FileInputStream(pathsFile));
				// String winampPath = "c:/program
				// files/multimedia/winamp/winamp.exe";
				String winampPath = props.getProperty("winamp");
				System.setProperty("org.jdesktop.jdic.mpcontrol.winamp.path", winampPath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// view.addButton(new JButton("Reload"), new Command() {
		//
		// public void run() {
		// init();
		// }
		//
		// });
	}

	private void load() throws IOException {
		List players = MediaPlayerService.getInstance().getMediaPlayers();
        if (players.size() == 0) return;
		player = (IMediaPlayer) players.get(0);
		playerInstance = player;
		playerController = new PlayerController(player);
		PlayerUI playerUI = new PlayerUI(player);

		playerUI.setButtonBar(createButtonBar());

		view.setContent(playerUI);

		GuiUpdater guiUpdater = new GuiUpdater(playerUI, player);
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(guiUpdater, 2, 1,
				TimeUnit.SECONDS);
	}

	JComponent createButtonBar() {
		JToolBar toolBar = new JToolBar();
		addButton(PlayerController.PREVIOUS, "previous track", toolBar);
		addButton(PlayerController.PLAY, "play", toolBar);
		addButton(PlayerController.PAUSE, "pause", toolBar);
		addButton(PlayerController.NEXT, "next track", toolBar);
		return toolBar;
	}

	private void addButton(String command, String toolTipText, Container toolBar) {
		String resourcePath = "." + File.separator + "resources" + File.separator;
		AbstractButton button = WidgetFactory.createToolBarButton(resourcePath + command + ".gif",
				command, toolTipText, command);
		toolBar.add(button);
		button.addActionListener(playerController);
	}

	public String getID() {
		return ID;
	}

	public static IMediaPlayer getPlayer() {
		return playerInstance;
	}

}
