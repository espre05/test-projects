/*
 * Created on 18-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.application.persistence;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Collection;

import della.swaf.application.datatypes.ObservableItem;

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