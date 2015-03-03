package della.swaf.application.datatypes;

import java.util.Comparator;
import java.util.Iterator;

public class FileItemSet extends ItemSet {
    public FileItemSet(Comparator c) {
	super(c);
    }

    public FileItemSet() {
	super();
    }

    public void deleteFromDisk() {
	for (Iterator it = itemsList.iterator(); it.hasNext();) {
	    FileItem item = (FileItem) it.next();
	    item.deleteFromDisk();
	}
    }

    public void renameFileFromData() {
	for (Iterator it = itemsList.iterator(); it.hasNext();) {
	    FileItem item = (FileItem) it.next();
	    item.renameFileFromData();
	}
    }

}
