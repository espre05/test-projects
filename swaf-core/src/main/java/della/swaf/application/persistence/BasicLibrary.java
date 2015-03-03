package della.swaf.application.persistence;

import della.swaf.application.datatypes.Item;

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
