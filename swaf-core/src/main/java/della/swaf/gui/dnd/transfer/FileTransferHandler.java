package della.swaf.gui.dnd.transfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.UIResource;


/**
 * Data transfer support for the file chooser. Since files are currently
 * presented as a list, the list support is reused with the added flavor of
 * DataFlavor.javaFileListFlavor
 */
public class FileTransferHandler extends TransferHandler implements UIResource {

	/**
	 * Create a Transferable to use as the source for a data transfer.
	 * 
	 * @param c
	 *            The component holding the data to be transfered. This argument
	 *            is provided to enable sharing of TransferHandlers by multiple
	 *            components.
	 * @return The representation of the data to be transfered.
	 * 
	 */
	protected Transferable createTransferable(JComponent c) {
		Object[] values = null;
		if (c instanceof JList) {
			values = ((JList) c).getSelectedValues();
		} else if (c instanceof JTable) {
			JTable table = (JTable) c;
			int[] rows = table.getSelectedRows();
			if (rows != null) {
				values = new Object[rows.length];
				for (int i = 0; i < rows.length; i++) {
					values[i] = table.getValueAt(rows[i], 0);
				}
			}
		}
		if (values == null || values.length == 0) {
			return null;
		}

		StringBuffer plainBuf = new StringBuffer();
		StringBuffer htmlBuf = new StringBuffer();

		htmlBuf.append("<html>\n<body>\n<ul>\n");

		for (int i = 0; i < values.length; i++) {
			Object obj = values[i];
			String val = ((obj == null) ? "" : obj.toString());
			plainBuf.append(val + "\n");
			htmlBuf.append("  <li>" + val + "\n");
		}

		// remove the last newline
		plainBuf.deleteCharAt(plainBuf.length() - 1);
		htmlBuf.append("</ul>\n</body>\n</html>");

		return new FileTransferable(plainBuf.toString(), htmlBuf.toString(), values);
	}

	public int getSourceActions(JComponent c) {
		return COPY;
	}
	

	class FileTransferable extends BasicTransferable {

		Object[] fileData;

		FileTransferable(String plainData, String htmlData, Object[] fileData) {
			super(plainData, htmlData);
			this.fileData = fileData;
		}

		/**
		 * Best format of the file chooser is DataFlavor.javaFileListFlavor.
		 */
		protected DataFlavor[] getRicherFlavors() {
			DataFlavor[] flavors = new DataFlavor[1];
			flavors[0] = DataFlavor.javaFileListFlavor;
			return flavors;
		}

		/**
		 * The only richer format supported is the file list flavor
		 */
		protected Object getRicherData(DataFlavor flavor) {
			if (DataFlavor.javaFileListFlavor.equals(flavor)) {
				ArrayList files = new ArrayList();
				for (int i = 0; i < fileData.length; i++) {
					files.add(fileData[i]);
				}
				return files;
			}
			return null;
		}

	}


}
