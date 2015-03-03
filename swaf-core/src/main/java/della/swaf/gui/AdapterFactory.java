package della.swaf.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;

public class AdapterFactory {

	public static final class ShowMenuAdapter extends MouseAdapter {
		private final JPopupMenu popup;
		private int button;

		ShowMenuAdapter(JPopupMenu popup) {
			super();
			this.popup = popup;
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == button) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}

		public void setButton(int button) {
			this.button = button;
		}
	}	

	public static ShowMenuAdapter newShowMenuAdapter(JPopupMenu popup) {
		return newShowMenuAdapter(popup, MouseEvent.BUTTON1);
	}

	public static ShowMenuAdapter newShowMenuAdapter(JPopupMenu menu, int button) {
		ShowMenuAdapter showMenuAdapter = new ShowMenuAdapter(menu);
		showMenuAdapter.setButton(button);
		return showMenuAdapter;
	}
	

	public static ShowComponent newShowComponentAdapter(JDialog dialog) {
		ShowComponent showComponentAdapter = new ShowComponent(dialog);
		showComponentAdapter .setButton(MouseEvent.BUTTON1);
		return showComponentAdapter;
	}
	
	public static final class ShowComponent extends MouseAdapter {
		private final JDialog dialog;
		private int button;

		ShowComponent(JDialog dialog) {
			super();
			this.dialog = dialog;
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == button) {
				dialog.setVisible(true);
			}
		}

		public void setButton(int button) {
			this.button = button;
		}
	}

}
