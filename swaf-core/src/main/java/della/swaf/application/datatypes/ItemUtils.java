package della.swaf.application.datatypes;

import java.util.*;

import della.swaf.application.persistence.BasicLibrary;

public class ItemUtils {

	public static void enableCollectionProperty(Item item, String propertyName) {
		Object property = item.get(propertyName);
		if (property == null)
			item.put(propertyName, new HashSet());
	}

	public static void addToCollection(Item item, String propertyName, Object value) {
		Collection collection = (Collection) item.get(propertyName);
		if (value instanceof Collection) {
			collection.addAll((Collection) value);
		} else
			collection.add(value);
//		item.firePropertyChange(propertyName, null, value);
		BasicLibrary.getDefault().update(item, propertyName, value, collection);
	}

	public static void removeFromCollection(Item item, String propertyName, Object value) {
		Collection collection = (Collection) item.get(propertyName);
		if (value instanceof Collection) {
			collection.removeAll((Collection) value);
		} else
			collection.remove(value);
//		item.firePropertyChange(propertyName, value, collection);
		BasicLibrary.getDefault().update(item, propertyName, value, collection);
	}

	public static void replace(Object oldValue, Object text, Item item, String propertyName) {
		Collection collection = (Collection) item.get(propertyName);
		collection.remove(oldValue);
		collection.add(text);
	}

	static Collection pupulate(Item selectedItem, Collection returnList) {
		Collection toExploreList = new ArrayList();
		for (Iterator iter = selectedItem.childIterator(); iter.hasNext();) {
			Item item = (Item) iter.next();
			if (item.countChilds() == 0)
				returnList.add(item);
			else
				toExploreList.add(item);
		}
		return toExploreList;
	}

	public static Collection findLeafs(Item selectedItem) {
		Collection returnList = new ArrayList();
		if (selectedItem.countChilds() == 0)
			returnList.add(selectedItem);
		else {
			List toExplore = new ArrayList();
			toExplore.add(selectedItem);
			while (!toExplore.isEmpty()) {
				toExplore.addAll(ItemUtils.pupulate((Item) toExplore.get(0), returnList));
				toExplore.remove(0);
			}
		}
		return returnList;
	}

}
