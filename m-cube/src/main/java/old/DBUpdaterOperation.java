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
package old;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



import net.della.stuff.generic.util.event.Event;
import net.della.stuff.generic.util.event.EventFactory;
import net.della.stuff.generic.util.event.EventListener;

import org.apache.commons.logging.LogFactory;
import org.flexdock.docking.DockingManager;
import org.flexdock.docking.state.PersistenceException;


import della.application.application.Application;
import della.application.persistence.Library;
import della.util.background.AbstractOperation;
import della.util.background.BackgroundTask;
import della.util.background.ThreadPool;

/**
 * 
 * @deprecated
 */
public final class DBUpdaterOperation extends AbstractJob {

	private boolean modified;

	private OldLibrary library;

	private Application application;

	public DBUpdaterOperation(Application application) {
		this.application = application;
		this.library = application.getLibrary();
		modified = false;
		library.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(OldLibrary.ITEM_ADDED)
						|| evt.getPropertyName().equals(OldLibrary.ITEM_REMOVED)) {
					setModified(true);
				}
			}
		});

		application.addListener(new EventListener() {

			public void eventHappened(Event event) {
				Object type = event.get(Event.TYPE);
				if (type.equals(OldLibrary.DATA_MODIFIED) || type.equals(OldLibrary.ITEM_ADDED)
						|| type.equals(OldLibrary.ITEM_REMOVED))
					setModified(true);
				if (type.equals(Application.CLOSING)) {
					Executors.newSingleThreadExecutor().execute(new SaveLibraryTask());
				}
			}
		});
		Executors.newSingleThreadExecutor().execute(new SaveLibraryRunnable());
	}

	protected TaskList getThreadPool() {
		TaskList pool = TaskList.scheduledThreadPool(5, 10);
//		ThreadPool pool = ThreadPool.simpleThreadPool();
		SaveLibraryTask saveLibraryTask = new SaveLibraryTask();
//		Runnable saveLibraryTask = new SaveLibraryRunnable();
		saveLibraryTask.setName("save library");
		// attachStandardListenersTo(saveLibraryTask, "Saving Library...");
		pool.add(saveLibraryTask);
		return pool;
	}

	protected void setModified(boolean b) {
		// LogFactory.getLog(this.getClass()).info("library has been
		// modified in memory, will be saved on disk");
		this.modified = b;
	}

	void saveLibrary() {
		LogFactory.getLog(this.getClass()).info(new Date() + " - checking library for scheduled saving...");
//		try {
//			boolean success = DockingManager.storeLayoutModel();
//			LogFactory.getLog(this.getClass()).info("view settings stered: " + success);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (PersistenceException e) {
//			e.printStackTrace();
//		}
		if (modified) {
//			library.save();			
			setModified(false);
		} else {
			application.fireEvent(EventFactory.createEvent(library, DiskArchiveManager.LIBRARY_SAVED));
		}
		LogFactory.getLog(this.getClass()).info(new Date() + " - settings saved.");
	}
	
	final class SaveLibraryRunnable implements Runnable {

		public void run() {
			Thread.currentThread().setName("save library");
			int saveDelay = 180;
			try {
				Thread.sleep(saveDelay*1000);
			} catch (InterruptedException e1) {			
				e1.printStackTrace();
			}
			
			while (true) {
				LogFactory.getLog(this.getClass()).debug("begin background save...");
				saveLibrary();
				LogFactory.getLog(this.getClass()).debug("end background save...");
				try {
					Thread.sleep(saveDelay*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		}
	}

	final class SaveLibraryTask extends SwingBackgroundTask {

		protected Object executeInBackground() throws Exception {
			LogFactory.getLog(this.getClass()).debug("begin background save...");
			saveLibrary();
			LogFactory.getLog(this.getClass()).debug("end background save...");
			return null;
		}
	}

}