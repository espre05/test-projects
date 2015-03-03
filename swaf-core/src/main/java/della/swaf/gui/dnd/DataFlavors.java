package della.swaf.gui.dnd;

import java.awt.datatransfer.DataFlavor;

public class DataFlavors {

	public static final DataFlavor uriListISO = createConstant(
			"text/uri-list; class=java.io.InputStream; charset=ISO-8859-1",
			"text/uri-list");

	static DataFlavor createConstant(String mimeType, String object) {
		return new DataFlavor(mimeType, object);
	}

}
