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

/**
 * 
 */
package net.mcube.extensions.externalPlayerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class PlayerController implements ActionListener {
	
	

        private IMediaPlayer player;
		public static final String PAUSE = "pause";
		public static final String PLAY = "play";
		public static final String NEXT = "next";
		public static final String PREVIOUS = "previous";
        
        public PlayerController(IMediaPlayer player) {
        	this.player = player;
		}

		public void actionPerformed(ActionEvent e) {

            // RuntimeEnvironment.getCurrentApplication().getLogger().info(e.getActionCommand());
            //
            // if (!WinampNative.checkInstance()) {
            // JOptionPane.showMessageDialog(null,
            // "Couldnt connect to Winamp player, make sure it is running.");
            // RuntimeEnvironment.getCurrentApplication().getLogger().info(
            // "Couldnt connect to Winamp player, make sure it is running.");
            // return;
            // }

            String cmd = e.getActionCommand();
            
            if (!player.isRunning())
            	player.init();

            if (PLAY.equals(cmd))
                player.play();
            else if (NEXT.equals(cmd))
            	player.next();
            else if (PREVIOUS.equals(cmd))
            	player.previous();
            else if (PAUSE.equals(cmd))
            	player.pause();
        }
    }