/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package old.form;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

import net.della.stuff.gui.swing.util.KeyEventUtils;

import com.jgoodies.binding.PresentationModel;


public class DataInputForm extends JPanel implements KeyListener {

	private Collection listeners;
	private String name;
	private final PresentationModel presentationModel;

	public DataInputForm(JPanel panel, PresentationModel presentationModel) {
		this.presentationModel = presentationModel;
		listeners = new LinkedList();
		add(panel);
		this.name = "";
	}
	
	private void fireOkKeyPressedEvent() {
		for (Iterator it = listeners.iterator(); it.hasNext();) {
			FormListener listener = (FormListener) it.next();
			listener.okEventHappened();
		}
	}

	private void fireCancelKeyPressedEvent() {
		for (Iterator it = listeners.iterator(); it.hasNext();) {
			FormListener listener = (FormListener) it.next();
			listener.cancelEventHappened();
		}
	}

	public void addListener(FormListener listener) {
		listeners.add(listener);
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		if (KeyEventUtils.isEsc(e)) {
			fireCancelKeyPressedEvent();
		}
		if (KeyEventUtils.isEnter(e)) {
			fireOkKeyPressedEvent();
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void triggerCommit() {
		presentationModel.triggerCommit();
	}

	public void triggerFlush() {
		presentationModel.triggerFlush();
	}

}
