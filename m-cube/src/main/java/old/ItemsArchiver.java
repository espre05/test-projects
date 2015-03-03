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

package old;

import java.io.File;
import java.util.*;

import net.della.stuff.generic.file.FileUtils;

import org.apache.commons.logging.LogFactory;



class ItemsArchiver {

	private final String dbFilePath;

	private SerializerLoader loader;

	private List list;

	private Map maps;

	ItemsArchiver(String songsFilePath, SerializerLoader loader) {
		this.dbFilePath = songsFilePath;
		this.loader = loader;
		this.maps = new HashMap();
	}

	/**
	 * @param list
	 */
	void save(List list) {
		File oldFile = new File(dbFilePath);
		File newFile = new File(dbFilePath + "_temp_backup");
		try {
			FileUtils.copy(oldFile, newFile);
			loader.save(list, dbFilePath);
			newFile.deleteOnExit();
		} catch (LoaderException e) {
			LogFactory.getLog(this.getClass()).info(
					"Errors in saving library. Old one is used instead");
			FileUtils.copy(newFile, oldFile);
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	List load() {
		list = loader.loadList(dbFilePath);
		return list;
	}

	public Collection getCollection() {
		return list;
	}

	public Map getMap(String index) {
		if (maps.containsKey(index))
			return (Map) maps.get(index);
		return createIndexedMap(index);
	}

	private Map createIndexedMap(String index) {
		Map indexedMap = new HashMap();
		Collection col = getCollection();
		if (col != null) {
			for (Iterator it = col.iterator(); it.hasNext();) {
				Map props = (Map) it.next();
				Object indexObject = props.get(index);
				indexedMap.put(indexObject, props);
			}
			maps.put(index, indexedMap);
		}
		return indexedMap;
	}

}
