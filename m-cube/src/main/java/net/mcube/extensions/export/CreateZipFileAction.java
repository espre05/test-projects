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

package net.mcube.extensions.export;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.datatypes.ItemSet;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.background.AbstractJob;
import net.della.mplatform.background.TaskAdapter;
import net.della.mplatform.background.TaskList;
import net.della.mplatform.gui.dnd.transfer.FileTransferHandler;
import net.della.mplatform.gui.renderer.CellDecorator;
import net.della.mplatform.gui.renderer.DataRenderer;
import net.mcube.extensions.album.AlbumAttributes;
import net.mcube.extensions.album.AlbumDataRenderer;
import della.swaf.extensions.gui.AbstractListView;
import della.swaf.extensions.gui.TableView;

public class CreateZipFileAction extends AbstractAction {

	private TableView view;

	private final Application application;

	public CreateZipFileAction(Application application, final AbstractListView view) {
		this.application = application;
		putValue(Action.NAME, "Create Zip to export");
		this.view = view;
		view.addCellRendererDecorator(new CellDecorator() {

			public void decore(DataRenderer renderer, int row) {
				if (!(renderer instanceof AlbumDataRenderer))
					return;
				AlbumDataRenderer albumRenderer = (AlbumDataRenderer) renderer;
				ObservableItem item = view.getElementAtRow(row);
				String filename = item.getString(AlbumAttributes.ZIP_FILE);
				JLabel notificationLabel = albumRenderer.getNotificationLabel();
				if (!filename.equals("")) {
					notificationLabel.setText("right-drag to export Zip");
//					albumRenderer.setRendererHeight(70);
//					DragSource dragSource = new DragSource();
//					dragSource.createDefaultDragGestureRecognizer(notificationLabel, DnDConstants.ACTION_COPY,
//							new SingleFileItemDragger(dragSource, new File(filename)));
					notificationLabel.setTransferHandler(new FileTransferHandler());
					notificationLabel.addMouseListener(new MouseAdapter() {
				        public void mousePressed(MouseEvent evt) {
				            JComponent comp = (JComponent)evt.getSource();
				            TransferHandler th = comp.getTransferHandler();
				    
				            // Start the drag operation
				            th.exportAsDrag(comp, evt, TransferHandler.COPY);
				        }
				    });
				} else {
					notificationLabel.setText("");
//					albumRenderer.setRendererHeight(55);
				}
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		List selection = view.getSelection();
		// final List items = new ArrayList();
		// for (Iterator it = selection.iterator(); it.hasNext();) {
		// Item item = (Item) it.next();
		// items.addAll(ItemUtils.findLeafs(item));
		// }
		// String zipName;
		// if (selection.size() == 1) {
		// Item selectedItem = (Item) selection.get(0);
		// zipName = selectedItem.getString(selectedItem.getMainAttribute());
		// } else {
		// zipName = "mcube selection";
		// }

		TaskList pool = TaskList.simpleThreadPool();
		for (Iterator it = selection.iterator(); it.hasNext();) {
			final ItemSet item = (ItemSet) it.next();
			final String zipName;
			if (selection.size() == 1) {
				zipName = item.getString(item.getMainAttribute());
			} else {
				zipName = "mcube selection";
			}
			final CreateZipTask task = new CreateZipTask(zipName);
			task.setCollection(item.listChilds());
			task.setPausePeriod(Thread.MIN_PRIORITY);
			task.setName("creating zip...");
			task.setElementsType("items");
			task.addListener(new TaskAdapter() {

				public void onTerminate() {
					item.put(AlbumAttributes.ZIP_FILE, task.getZip().getAbsolutePath());
					view.repaint();
				}
			});
			pool.add(task);
		}
		AbstractJob op = AbstractJob.newScheduledJob(pool);
		application.runOperation(op);

	}
}
