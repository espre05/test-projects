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

package net.mcube.library.actions;

import java.awt.Color;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import net.della.mplatform.util.WidgetFactory;
import net.mcube.library.operations.AddFolderOperation;

import org.jdesktop.swingx.JXDatePicker;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.EventListModel;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


public class FolderChoicePage extends WizardPage {

	private final class FolderDropTarget implements DropTargetListener {
		public void dragEnter(DropTargetDragEvent evt) {
			// Called when the user is dragging and enters this drop target.
		}

		public void dragOver(DropTargetDragEvent evt) {
			// Called when the user is dragging and moves over this drop target.
		}

		public void dragExit(DropTargetEvent evt) {
			// Called when the user is dragging and leaves this drop target.
		}

		public void dropActionChanged(DropTargetDragEvent evt) {
			// Called when the user changes the drag action between copy or move.
		}

		public void drop(DropTargetDropEvent evt) {
			try {
	            Transferable t = evt.getTransferable();
	    
	            if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                List folderList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
	                evt.getDropTargetContext().dropComplete(true);
	                addFolder((File) folderList.get(0));
	            } else {
	                evt.rejectDrop();
	            }
	        } catch (IOException e) {
	            evt.rejectDrop();
	        } catch (UnsupportedFlavorException e) {
	            evt.rejectDrop();
	        }
		}
	}

	private JFileChooser fc;

//	private Application application;

	private final EventList folderList;

	public FolderChoicePage() {
		folderList = new BasicEventList();
//		this.application = application;
		init();
	}

	private void init() {
		final JButton button = new JButton("Select Folder");
		fc = WidgetFactory.getInstance().createFolderChooser();
		EventListModel model = new EventListModel(folderList);
		folderList.add(0, "Drop here the folder to add");
		JList viewList = new JList(model);
		new DropTarget(viewList, new FolderDropTarget());

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				showFileChooser();
			}

		});
		FormLayout layout = new FormLayout("left:pref, 100dlu:grow, 3dlu, left:pref, ",
				"pref, pref, pref, pref, pref, pref, pref, pref");
		PanelBuilder panelBuilder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		panelBuilder.add(new JLabel("Selected folder: "), cc.xy(1, 1));
		panelBuilder.add(viewList, cc.xy(2, 1));
		panelBuilder.add(button, cc.xy(1, 2));

		final JCheckBox useTextFileCheckBox = new JCheckBox("Use text file info", false);
		putWizardData(AddFolderOperation.USE_TEXT_FILE, useTextFileCheckBox.isSelected());
		useTextFileCheckBox
				.addItemListener(createPropertyChangeAdapter(AddFolderOperation.USE_TEXT_FILE));
		
		final JCheckBox tagWithDecadeCheckBox = new JCheckBox("Tag with decade", true);
		putWizardData(AddFolderOperation.TAG_WITH_DECADE, tagWithDecadeCheckBox.isSelected());
		tagWithDecadeCheckBox
				.addItemListener(createPropertyChangeAdapter(AddFolderOperation.TAG_WITH_DECADE));		
		
		final JCheckBox tagWithDateCheckBox = new JCheckBox("Tag with release year", true);
		putWizardData(AddFolderOperation.TAG_WITH_DATE, tagWithDateCheckBox.isSelected());
		tagWithDateCheckBox
				.addItemListener(createPropertyChangeAdapter(AddFolderOperation.TAG_WITH_DATE));

		final JTextField tagTextField = new JTextField();
		tagTextField.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent e) {
				putWizardData(AddFolderOperation.USER_TAG, tagTextField.getText());
			}
		});
		putWizardData(AddFolderOperation.USER_TAG, "");
		panelBuilder.add(new JLabel("tag: "), cc.xy(1, 3));
		panelBuilder.add(tagTextField, cc.xy(2, 3));
		String tagTooltip = "<html>"
				+ "Each song loaded in this session will be tagged whit the Tag you write here."
				+ "<br>"
				+ "You can set only one tag here. Later you will be free to add more tag and also to remove existing ones."
				+ "<html>";

		panelBuilder.add(createWhatIsThisLabel(tagTooltip), cc.xy(4, 3));
		panelBuilder.add(tagWithDateCheckBox, cc.xy(1, 4));
		panelBuilder.add(tagWithDecadeCheckBox, cc.xy(1, 5));
		panelBuilder.add(useTextFileCheckBox, cc.xy(1, 6));
		final JXDatePicker datePicker = new JXDatePicker();
		putWizardData(AddFolderOperation.DATE_ADDED, datePicker.getDateInMillis());
		datePicker.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				putWizardData(AddFolderOperation.DATE_ADDED, datePicker.getDateInMillis());
			}

		});
		panelBuilder.add(datePicker, cc.xy(1, 7));
		tagTooltip = "<html>"
				+ "mCube keeps track of historical information by setting the date you added songs to library."
				+ "<br>"
				+ "If you want the songs you are adding now to be set with a different date than today, you can change it here"
				+ "<html>";
		panelBuilder.add(createWhatIsThisLabel(tagTooltip), cc.xy(4, 6));

		add(panelBuilder.getPanel());

		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(20);
	}
	

	void addFolder(File newFolder) {
		folderList.clear();
		folderList.add(0, newFolder);
		putWizardData(AddFolderOperation.NEW_FOLDER, newFolder);
	}

	private JLabel createWhatIsThisLabel(String tooltipText) {
		JLabel whatIsLabel = new JLabel("What is this?");
		whatIsLabel.setForeground(Color.BLUE);
		whatIsLabel.setToolTipText(tooltipText);
		return whatIsLabel;
	}

	private ItemListener createPropertyChangeAdapter(final String bindProperty) {
		return new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				boolean b;
				if (e.getStateChange() == ItemEvent.SELECTED)
					b = true;
				else
					b = false;
				putWizardData(bindProperty, b);
			}
		};
	}

	public static final String getDescription() {
		return "Choose folder to add to mCube Library";
	}

	protected String validateContents(Component component, Object o) {
		return null;
	}

	private void showFileChooser() {
		fc.setDialogTitle("Select folder to add");
		fc.setApproveButtonText("Select");
		fc.setApproveButtonToolTipText("Add mp3 in this directory to library");

		if (fc.showDialog(null, "Select") == JFileChooser.APPROVE_OPTION) {
			File newFolder = fc.getSelectedFile();
			fc.setCurrentDirectory(newFolder);
			addFolder(newFolder);
		}
	}

}
