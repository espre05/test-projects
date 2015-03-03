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
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import net.della.mplatform.application.core.Application;
import net.mcube.library.LibraryImpl;
import net.mcube.library.operations.RescanLibraryOperation;

/*
 * Created on 28-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

public class RescanLibraryAction extends AbstractAction {

	private Application application;

	public RescanLibraryAction(Application application) {
		super("Rescan media folders");
		this.application = application;
	}

	public void actionPerformed(ActionEvent e) {

		RescanLibraryOperation op = new RescanLibraryOperation((LibraryImpl) application.getLibrary());
		application.runOperation(op);
	}

}