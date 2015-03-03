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

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PlayerDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
        List players = MediaPlayerService.getInstance().getMediaPlayers();

        for (Iterator iter = players.iterator(); iter.hasNext();) {
            IMediaPlayer player = (IMediaPlayer) iter.next();
			
            player.init();
            if (!player.isRunning()) {
                if (player.startPlayerProcess()) {
                    System.out.println(
                            "Player:" + player.getDescription() + " started");
                } else {
                    System.out.println(
                            "Player:" + player.getDescription()
                            + " failed to start");
					
                }
            } else {
                System.out.println(
                        "Player:" + player.getDescription() + " already running");
            }
            player.destroy();

        }
	}

}
