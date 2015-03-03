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

import javax.swing.JDialog;

import net.della.mplatform.application.datatypes.ObservableItem;

import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;


public class JFormPane {

	public static void showDialog(DataInputForm form, String dialogTitle, FormDialogBuilder dialogBuilder) {
		JDialog dialog = dialogBuilder.createFormDialog(form);
		dialog.setTitle(dialogTitle);
		dialog.setAlwaysOnTop(true);
		dialog.pack();
		dialog.setVisible(true);
	}

	public static DataInputForm newForm(final String bindProperty, final ObservableItem fileItem, ModelBuilder modelBuilder, PropertyListenerFactory itemPropertyListenerFactory) {
		PresentationModelBuilder presentationModelBuilder = new PresentationModelBuilder(
				itemPropertyListenerFactory, modelBuilder);
		PresentationModel presentationModel = presentationModelBuilder
				.createPresentationModel(fileItem);
	
		FormBuilder formBuilder = new SingleTextFieldFormBuilder(bindProperty);
		DataInputForm form = formBuilder.buildForm(presentationModel);
		form.setBackground(form.getBackground().darker());
		return form;
	}

}
