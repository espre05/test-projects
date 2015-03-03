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

package net.mcube.extensions.musicbrainz;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class OkCancelButtonBar extends JToolBar {
	
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * 
	 * @param dialog: the window that will be dispose after pressing OK or Cancel button
	 */
	public OkCancelButtonBar(final Window window) {
		okButton = new JButton("Ok");
		add(okButton);		
		cancelButton = new JButton("Cancel");
		add(cancelButton);
		ActionListener disposeAction = new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}
		
		};
		okButton.addActionListener(disposeAction);
		cancelButton.addActionListener(disposeAction);
	}

	public void addOkActionListener(ActionListener listener) {
		okButton.addActionListener(listener);
	}

}
