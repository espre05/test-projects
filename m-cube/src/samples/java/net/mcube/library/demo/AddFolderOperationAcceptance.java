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

package net.mcube.library.demo;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;

import net.mcube.library.LibraryImpl;
import net.mcube.library.operations.AddFolderOperation;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

import della.application.application.DefaultApplicationWindow;
import della.application.gui.AbstractPage;
import della.docking.DockableApplicationWindow;
import della.docking.DockablePage;
import della.util.background.AbstractOperation;
import della.util.swing.WidgetFactory;

public class AddFolderOperationAcceptance {
	
	public static void main(String[] args) {

		final LibraryImpl library = LibraryImpl.newLibrary("/test/");
//		ItemBuilderFactory.getInstance().addBuilder(ItemBuilderFactory.DEFAULT, new DefaultItemBuilder());


		final DefaultApplicationWindow appWindow = new DockableApplicationWindow();
		appWindow.setNewLookAndFeel(new Plastic3DLookAndFeel());
		AbstractPage page = new DockablePage();
		JButton startButton = new JButton("Choose folder...");
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.BLACK);
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = WidgetFactory.getInstance().createFolderChooser();
				fileChooser.setDialogTitle("Scan Folder");
				fileChooser.setApproveButtonText("Scan");
				fileChooser.setApproveButtonToolTipText("Add mp3 in this directory to library");
				if (appWindow.showFileChooser(fileChooser) == JFileChooser.APPROVE_OPTION) {
					File selectedFolder = fileChooser.getSelectedFile();
					AbstractJob op = new AddFolderOperation(library,
							selectedFolder);
					op.setProgressBar(progressBar);
//					op.run();
					fileChooser.setCurrentDirectory(selectedFolder);
				}
			}
		});
		appWindow.addToBottomPanel(startButton);
		appWindow.addToBottomPanel(progressBar);
		appWindow.registerPage(page);
		appWindow.loadPage(page);
		appWindow.setVisible(true);
	}

}
