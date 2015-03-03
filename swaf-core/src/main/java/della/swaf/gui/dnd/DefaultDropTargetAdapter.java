package della.swaf.gui.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

public final class DefaultDropTargetAdapter extends DropTargetAdapter {
	

	public void drop(DropTargetDropEvent dtde) {
		try {
			Transferable t = dtde.getTransferable();
	
			if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				List files = (List) t
						.getTransferData(DataFlavor.javaFileListFlavor);
				dtde.dropComplete(true);
				Logger.getAnonymousLogger().info("javaFileList catched");
			} else if (t.isDataFlavorSupported(DataFlavors.uriListISO)) {
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				Object transferData = t
						.getTransferData(DataFlavors.uriListISO);
				InputStream stream = (InputStream) transferData;
				InputStreamReader isr = new InputStreamReader(stream);
				BufferedReader reader = new BufferedReader(isr);
				String uriString = reader.readLine();
				dtde.dropComplete(true);
				URI uri = new URI(uriString);
				Logger.getAnonymousLogger().info("uriListISO catched");
			} else {
				dtde.rejectDrop();
			}
		} catch (IOException e) {
			dtde.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			dtde.rejectDrop();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
