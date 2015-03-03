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

/**
 * 
 */
package net.mcube.extensions.tags;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;

import net.della.stuff.gui.swing.dnd.FileSelection;



final class CosmoComponentController {

	/**
	 * 
	 */
	private final TagCosmos cosmos;

	private final String id;

	CosmoComponentController(final TagCosmos cosmos, final String id, JLabel label) {
		super();
//		new CosmoComponent(graphElem, label);
		this.cosmos = cosmos;
		this.id = id;
		label.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {	
				if (e.getButton() != MouseEvent.BUTTON1)
					return;
				if (e.getModifiersEx() == 0)
					cosmos.setSelection(id);
				else if (e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK)
					cosmos.addToSelection(id);
				else if (e.getModifiersEx() == InputEvent.ALT_DOWN_MASK)					
					cosmos.setExclusion(id);
				else if (e.getModifiersEx() == InputEvent.ALT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK)
					cosmos.addToExclusion(id);				
				
			}
		});
		new DropTarget(label, new DropTargetAdapter() {

			public void drop(DropTargetDropEvent dtde) {
				Transferable transferable = dtde.getTransferable();
				// TODO: se in fase di drag mettessi anche le item potrei
				// recuperarle prendendo dal
				// transferable il dataFlavors giusto, eviterei la query alla
				// Library
				DataFlavor dataFlavor = dtde.getCurrentDataFlavors()[0];
				try {
					FileSelection selection = (FileSelection) transferable
							.getTransferData(dataFlavor);
					cosmos.tagSelection(selection, id);
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	final TagCosmos getCosmos() {
		return cosmos;
	}

	final String getId() {
		return id;
	}	


}