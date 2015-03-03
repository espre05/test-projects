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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class SingleTextFieldFormBuilder implements FormBuilder {

	private String bindProperty;

	public SingleTextFieldFormBuilder(String bindProperty) {
		this.bindProperty = bindProperty;
	}

	public DataInputForm buildForm(final PresentationModel presentationModel) {

		FormLayout layout = new FormLayout("100dlu:grow", "pref");
		PanelBuilder panelBuilder = new PanelBuilder(layout);		
		CellConstraints cc = new CellConstraints();

		BufferedValueModel bufferedModel = presentationModel.getBufferedModel(bindProperty);
		final JTextField textField = BasicComponentFactory.createTextField(bufferedModel, false);
		textField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				textField.setCaretPosition(textField.getText().length());
			}
		});			
		textField.requestFocusInWindow();
		
		panelBuilder.add(textField, cc.xy(1, 1));
		DataInputForm form = new DataInputForm(panelBuilder.getPanel(), presentationModel);
		textField.addKeyListener(form);

		return form;
	}


}
