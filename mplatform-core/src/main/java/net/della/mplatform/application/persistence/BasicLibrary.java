/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.mplatform.application.persistence;

import net.della.mplatform.application.datatypes.Item;

public abstract class BasicLibrary implements OldLibrary {

	private static BasicLibrary instance;

	public static BasicLibrary getDefault() {
		if (instance != null)
			return instance;
		return null;
	}

	public static OldLibrary newLibrary(String homePath, String libraryImplClassName) {
		if (instance != null)
			return instance;
		try {
			Class c = Class.forName(libraryImplClassName);
			BasicLibrary libraryImpl = (BasicLibrary) c.newInstance();
			libraryImpl.init();
			libraryImpl.setLibraryHome(homePath);
			instance = libraryImpl;
			return libraryImpl;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected abstract void setLibraryHome(String homePath);

	protected abstract void init();

	public abstract void update(Item item, String p, Object oldValue, Object newValue);

}
