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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.mcube.extensions.externalPlayerController.ExternalPlayerControllerExtension;

public class MediaPlayer {

	private String command;

	private String enqueueOption;

	public MediaPlayer() {
		enqueueOption = "";
	}

	public void play(List list, boolean enqueue) {
		PlayThread playThread = new PlayThread(list, enqueue);
		playThread.start();
		// playImpl(list, enqueue);
	}

	void playImpl(List list, boolean enqueue) {

		Iterator it = list.iterator();
		String songPath = (String) it.next();

		String executionString[];
		if (enqueue)
			executionString = enqueueString(songPath);
		else
			executionString = playString(songPath);
		if (executionString[0] == "")
			return;
		try {
			run(executionString);
			Thread.sleep(500);
		} catch (Exception e) {
			System.out.println(e);
		}

		while (it.hasNext()) {
			songPath = (String) it.next();
			run(enqueueString(songPath));
			try {
				Thread.sleep(350);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void run(String[] cmdArray) {
		try {
			Runtime.getRuntime().exec(cmdArray);

		} catch (Exception exc) {
			exc.printStackTrace();
		}

		// if (isWindows()) {
		// StringBuffer cmd = new StringBuffer();
		// cmd.append(cmdArray[0]);
		// cmd.append(" ");
		// cmd.append(cmdArray[1]);
		// cmd.append(" ");
		// cmd.append(cmdArray[2]);
		// runWin32(cmd.toString());
		// runLinux(cmdArray);
		// } else if (isLinux() || isMac()) {
		// runLinux(cmdArray);
		// }
	}

	private boolean isMac() {
		String os = System.getProperty("os.name");
		if (os.indexOf("Mac") != -1)
			return true;
		return false;
	}

	private boolean isLinux() {
		String os = System.getProperty("os.name");
		if (os.indexOf("Linux") != -1)
			return true;
		return false;
	}

	private boolean isWindows() {
		String os = System.getProperty("os.name");
		if (os.indexOf("Windows") != -1)
			return true;
		return false;
	}

	protected void runLinux(String[] cmdArray) {
		try {
			Runtime.getRuntime().exec(cmdArray);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void runWin32(String command) {
		try {
			Runtime.getRuntime().exec(command);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private String[] playString(String songPath) {
		String[] playString = new String[3];
		playString[0] = getCommand();
		playString[2] = songPath;
		playString[1] = "";
		return playString;
	}

	private String[] enqueueString(String songPath) {
		String[] enqueueString = new String[3];
		enqueueString[0] = getCommand();
		enqueueString[2] = songPath;
		enqueueString[1] = getEnqueueOption();
		return enqueueString;
	}

	class PlayThread extends Thread {

		private List list;

		private boolean enqueue;

		public PlayThread(List list, boolean enqueue) {
			this.list = list;
			this.enqueue = enqueue;
		}

		// @Override
		public void run() {

			super.run();
			playImpl(list, enqueue);
		}

	}

	void playImpl2(List paths, boolean enqueue) {
		IMediaPlayer player = ExternalPlayerControllerExtension.getPlayer();
		if (!player.isRunning())
			player.init();
//		Wrap wrap = new Wrap(URL.class);
//		Collection urls = wrap.runOn(paths);
		List urls = new ArrayList();
		for (Iterator iter = paths.iterator(); iter.hasNext();) {
			String path = (String) iter.next();
			try {
				urls.add(new URL("file:///" + path));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		if (!enqueue)
			player.destroy();
			player.startPlayerProcess();
		for (Iterator it = urls.iterator(); it.hasNext();) {
			URL url = (URL) it.next();
			player.setMediaLocation(url);
		}		
		player.play();

	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setEnqueueOption(String enqueueOption) {
		this.enqueueOption = enqueueOption;
	}

	public String getCommand() {
		return command;
	}

	public String getEnqueueOption() {
		return enqueueOption;
	}

}