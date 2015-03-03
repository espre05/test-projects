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

/*
 * Created on 2-feb-2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

package net.mcube.library.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;

import javax.swing.AbstractAction;

import net.della.mplatform.application.core.Application;
import net.mcube.library.operations.AddFolderOperation;

public class AddFoldersAction extends AbstractAction {

	static Application application;

	public AddFoldersAction(Application application) {
		super("Add Folders...");
		AddFoldersAction.application = application;
	}

	public void actionPerformed(ActionEvent e) {
		// launchFileChooser();
		launchWizard();
	}

	private void launchWizard() {
		Class[] pages = new Class[] { FolderChoicePage.class, AcceptPage.class };

		// Use the utility method to compose a Wizard
		Wizard wizard = WizardPage.createWizard(pages, ResuotProducerFactory.ADD_FOLDER_OP);

		// And show it onscreen
		WizardDisplayer.showWizard(wizard);
	}

	private static class ResuotProducerFactory implements WizardResultProducer {

		public Object finish(Map wizardData) throws WizardException {
			return null;
		}

		static final WizardResultProducer ADD_FOLDER_OP = new WizardResultProducer() {
			public Object finish(Map wizardData) {
				File newFolder = (File) wizardData.get(AddFolderOperation.NEW_FOLDER);
				AddFolderOperation op = new AddFolderOperation(application.getLibrary(), newFolder);
				op.setTrustTextFile((Boolean) wizardData.get(AddFolderOperation.USE_TEXT_FILE));
				op.setDateTagFlagEnabled((Boolean) wizardData.get(AddFolderOperation.TAG_WITH_DATE));
				op.setDecadeTagFlagEnabled((Boolean) wizardData.get(AddFolderOperation.TAG_WITH_DECADE));
				op.setUserTag((String) wizardData.get(AddFolderOperation.USER_TAG));
				op.setDateAdded((Long) wizardData.get(AddFolderOperation.DATE_ADDED));
				application.runOperation(op);
				return null;
			}
		};

	}
}
