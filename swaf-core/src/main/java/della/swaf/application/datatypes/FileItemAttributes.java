/*
 * Created on 31-ott-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.application.datatypes;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface FileItemAttributes {

	/**
	 * 
	 * @deprecated
	 */
    String PATH = "Path";

    /**
	 * 
	 * @deprecated
	 */
    String FILENAME = "Filename";

    /**
	 * 
	 * @deprecated
	 */
    String EXTENSION = "Extension";
    /**
	 * 
	 * @deprecated
	 */
    String FILE_LAST_MODIFIED = "Last modified";

    String DATE_ADDED = "Date Added";
    /**
	 * 
	 * @deprecated
	 */
    String ID = "id";

    /**
     * Should refer to path relative to one of the library top managed folders
     */
    String RELATIVE_PATH = "relative path";
    /**
	 * 
	 * @deprecated
	 */
    String FILE_SIZE = "File Size";

    String VIRTUAL_PATH = "virtual path";

	String TYPE = "item type";

	String CONTEXT = "context";

	String ABSOLUTE_PATH = "absolute path";

}
