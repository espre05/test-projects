package della.swaf.gui.util;

import java.awt.event.KeyEvent;

public class KeyEventUtils {

	public static boolean isEnter(KeyEvent e) {
		if (e.getKeyCode() != KeyEvent.VK_ENTER)
			return false;
		return true;
	}

	public static boolean isEsc(KeyEvent e) {
		if (e.getKeyCode() != KeyEvent.VK_ESCAPE)
			return false;
		return true;
	}

	public static boolean isKeyReleased(KeyEvent e) {
		if (e.getID() != KeyEvent.KEY_RELEASED)
			return false;
		return true;
	}

	public static boolean isCtrlPlusReleased(KeyEvent e) {
		if (!e.isControlDown())
			return false;
		if (e.getKeyCode() != KeyEvent.VK_PLUS)
			return false;
		if (e.getID() != KeyEvent.KEY_RELEASED)
			return false;
		return true;
	}

	public static boolean isCtrlTReleased(KeyEvent e) {
		if (!e.isControlDown())
			return false;
		if (e.getKeyCode() != KeyEvent.VK_T)
			return false;
		if (e.getID() != KeyEvent.KEY_RELEASED)
			return false;
		return true;
	}

	/**
	 * @param e
	 * @return
	 */
	public static boolean isTabReleased(KeyEvent e) {
		return e.getKeyCode() == KeyEvent.VK_TAB && e.getID() == KeyEvent.KEY_RELEASED;
	}

}
