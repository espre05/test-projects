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

package net.mcube.util;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Delayer {

	private boolean scheduled;

	private Timer timer;

	private Random random;

	private final Log log;

	private int delay;

	public Delayer(int delay) {
		timer = new Timer();
		random = new Random();
		log = LogFactory.getLog(this.getClass());
		this.delay = delay;
	}

	protected final void handleUpdate() {
		if (scheduled) {
			timer.cancel();
			log.debug("schedule cancelled...");
			scheduled = false;
		}
		log.debug("scheduling refresh...");
		timer = new Timer();
		timer.schedule(new RefreshTask(), delay);
		scheduled = true;
//		long fromLast = Delay.fromLast();
//		if (fromLast > delay)
//			timer.schedule(new RefreshTask(), 100l);
	}

	private class RefreshTask extends TimerTask {

		public void run() {
			log.debug("refreshing...");
			refresh();
			scheduled = false;
		}
	}

	protected abstract void refresh();

	public void setDelay(int delay) {
		this.delay = delay;		
	}
}
