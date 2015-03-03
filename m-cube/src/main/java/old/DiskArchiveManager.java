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
 * Created on 24-lug-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package old;

import java.io.File;
import java.util.*;

import net.mcube.library.TopFolders;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Daniele
 * @deprecated
 */
public class DiskArchiveManager {

	private static DiskArchiveManager instance;

	private final ManagedFolders managedFolders;

	private final TopFolders topFolders;

	private String managedFoldersArchiveName;

	private String topFoldersArchiveName;

	private String archiveFolderName;

	private String databaseFolderPath;

	private Map archivers;

	public static final String FOLDER_TYPE = "system folder";

	public static final String LIBRARY_SAVED = "library saved";

	public static final String TOP_FOLDER_TYPE = "archive top folder";

	DiskArchiveManager(String homePath) {

		archiveFolderName = "database";
		managedFoldersArchiveName = "folders.txt";
		topFoldersArchiveName = "topFolders.txt";

		databaseFolderPath = homePath + File.separator + archiveFolderName + File.separator;
		File file = new File(databaseFolderPath);
		if (!file.exists())
			file.mkdirs();

		// songsFilePath = databaseFolderPath + songsArchiveName;
		// managedFoldersFilePath = databaseFolderPath +
		// managedFoldersArchiveName;

		topFolders = new TopFolders((databaseFolderPath + topFoldersArchiveName));
		managedFolders = new ManagedFolders(databaseFolderPath + managedFoldersArchiveName,
				new FolderTextLoader(topFolders));
		// songsArchiver = new ItemsArchiver(songsFilePath, new
		// SongSerializerLoader(topFolders));
		archivers = new HashMap();
	}

	public void load() {
		Log log = LogFactory.getLog(this.getClass());
		log.info("Library loading...");
		log.debug("Library loading top folders...");
		topFolders.load();
		log.debug("Library loading managed folders...");
		managedFolders.load();
		LogFactory.getLog(this.getClass()).info(
				"library manages " + countManagedFolders() + " folders under " + countTopFolders()
						+ " top Folders");
		log.debug("Library loading items...");
		loadItemsArchivers();
		log.info("Library loaded!");
		// return rawList;
	}
	
	private void loadItemsArchivers() {
		for (Iterator it = archivers.keySet().iterator(); it.hasNext();) {
			String id = (String) it.next();
			ItemsArchiver archiver = (ItemsArchiver) archivers.get(id);
			List list = archiver.load();
			if (list != null)
				LogFactory.getLog(this.getClass()).info(
						"archive '" + id + "' contains " + list.size() + " items");
		}
	}

	public int countManagedFolders() {
		return managedFolders.size();
	}

	public TopFolders getTopFolders() {
		return topFolders;
	}

	public int countTopFolders() {
		return topFolders.count();
	}

	/**
	 * 
	 * @deprecated
	 */
	public List getManagedFoldersPath() {
		return managedFolders.getPaths();
	}

	public void removeManagedFolders(Collection foldersToRemove) {
		managedFolders.removeAll(foldersToRemove);
	}

	public void addDb(String id, DataProcesserSerializerLoader loader) {
		archivers.put(id, new ItemsArchiver(databaseFolderPath + loader.getFileName(), loader));
	}

	/**
	 * 
	 * @param id:
	 *            id of database
	 * @return a collection of data mantained by this Database
	 */
	public Collection getDb(String id) {
		ItemsArchiver archiver = (ItemsArchiver) archivers.get(id);
		return archiver.getCollection();
	}

	public void loadTopFolders() {
		topFolders.load();
	}

	public static DiskArchiveManager getDefault() {
		return instance;
	}

	public static DiskArchiveManager newInstance(String homePath) {
		if (instance != null)
			return instance;
		instance = new DiskArchiveManager(homePath);
		return instance;
	}

	public void backupAll() {
		String songsArchiveName = "songs.mml";
		File topFoldersFile = new File(databaseFolderPath + topFoldersArchiveName);
		File managedFoldersFile = new File(databaseFolderPath + managedFoldersArchiveName);
		File songsFoldersFile = new File(databaseFolderPath + songsArchiveName);
		String backupFolderName = "backup";
		String backupPath = databaseFolderPath + backupFolderName + File.separator;
		new File(backupPath).mkdir();
		topFoldersFile.renameTo(new File(backupPath + topFoldersArchiveName));
		managedFoldersFile.renameTo(new File(backupPath + managedFoldersArchiveName));
		songsFoldersFile.renameTo(new File(backupPath + songsArchiveName));		
	}

}