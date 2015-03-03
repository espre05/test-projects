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


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JProgressBar;

import net.mcube.library.LibraryImpl;
import net.mcube.library.operations.AddFolderOperation;
import net.mcube.library.operations.CleanLibraryOperation;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.sourcesense.stuff.FileUtils;

import della.application.application.DefaultApplicationWindow;
import della.application.gui.AbstractPage;
import della.docking.DockableApplicationWindow;
import della.docking.DockablePage;
import della.util.background.AbstractOperation;

public class CleanOperationAcceptance {

	public static void main(String[] args) {

		final LibraryImpl library = LibraryImpl.newLibrary("/test/");

		AbstractJob addOp = new AddFolderOperation(library, new File(FileUtils
				.getCurrentFolderAbsolutePath()
				+ "/test/resources/mp3/"));
//		addOp.run();

		// ItemBuilderFactory.getInstance().addBuilder(ItemBuilderFactory.DEFAULT,
		// new DefaultItemBuilder());

		final DefaultApplicationWindow appWindow = new DockableApplicationWindow();
		appWindow.setNewLookAndFeel(new Plastic3DLookAndFeel());
		AbstractPage page = new DockablePage();
		final JProgressBar progressBar = new JProgressBar();
		JButton startButton = new JButton("Clean Library...");
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CleanLibraryOperation op = new CleanLibraryOperation(library);
				op.setProgressBar(progressBar);
//				op.run();
			}

		});

		appWindow.addToBottomPanel(startButton);
		appWindow.addToBottomPanel(progressBar);
		appWindow.registerPage(page);
		appWindow.loadPage(page);
		appWindow.setVisible(true);

	}

}
