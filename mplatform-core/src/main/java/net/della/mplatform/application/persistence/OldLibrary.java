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

/*
 * Created on 18-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.della.mplatform.application.persistence;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Collection;

import net.della.mplatform.application.datatypes.ObservableItem;


/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface OldLibrary {

	public static final String DATA_MODIFIED = "Data Modified";

	public static final String ITEM_ADDED = "File added";

	public static final String ITEM_REMOVED = "file removed";

	void removeAll(Collection itemsToRemove);

	void addAll(Collection<ObservableItem> newItemsList);

	/**
	 * 
	 * @deprecated
	 */
	Context getContext();

	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * 
	 * @deprecated
	 */
	boolean isFolderManaged(File folderToAdd);

	/**
	 * 
	 * @deprecated
	 */
	void addTopFolder(String absolutePath);

	/**
	 * 
	 * @deprecated
	 */
	void addManagedFolder(String absolutePath);
	
	boolean contains(String absolutePath);

	void load(String dbFolder);

	void load();

	void close();
	
}