package della.swaf.gui.dnd;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

public class FileSelection extends Vector implements Transferable {
	final static int FILE = 0;

	final static int STRING = 1;

	final static int PLAIN = 2;

	DataFlavor flavors[] = { DataFlavor.javaFileListFlavor};

	public FileSelection(File file) {
		addElement(file);
	}

	public FileSelection() {
	}

	/* Returns the array of flavors in which it can provide the data. */
	public synchronized DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	/* Returns whether the requested flavor is supported by this object. */
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(DataFlavor.javaFileListFlavor);
//		boolean b = false;
//		b |= flavor.equals(flavors[FILE]);
//		b |= flavor.equals(flavors[STRING]);
//		b |= flavor.equals(flavors[PLAIN]);
//		return (b);
	}

	/**
	 * If the data was requested in the "java.lang.String" flavor, return the
	 * String representing the selection.
	 */
	public synchronized Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (flavor.equals(flavors[FILE])) {
			return this;
		} else if (flavor.equals(flavors[PLAIN])) {
			return new StringReader(((File) elementAt(0)).getAbsolutePath());
		} else if (flavor.equals(flavors[STRING])) {
			return ((File) elementAt(0)).getAbsolutePath();
		} else {
			throw new UnsupportedFlavorException(flavor);
		}
	}
}
