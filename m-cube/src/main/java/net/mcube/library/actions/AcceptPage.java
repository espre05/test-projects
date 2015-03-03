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

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AcceptPage extends WizardPage {
	
	public AcceptPage() {
		init();
	}
	
	private void init() {
		final JTable reviewTable = new JTable();
		Vector data = new Vector();
		Vector names = new Vector();
		for (int i = 0; i < getWizardDataKeys().length; i++) {
			Object key = getWizardDataKeys()[i];
			names.add(key);
			data.add(getWizardData(key));
		}
		final DefaultTableModel tableModel = new DefaultTableModel(data, names);
		reviewTable.setModel(tableModel);
		add(reviewTable);
	}

	public static final String getDescription() {
        return "Review and begin import";
    }

}
