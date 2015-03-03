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

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import javax.swing.JCheckBox;

public class BeanTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new BeanTester().testBean();
	}

	private void testBean() {
		try {
			BeanInfo bi = Introspector.getBeanInfo(JCheckBox.class);
			PropertyDescriptor[] pds = bi.getPropertyDescriptors();
			for (int i = 0; i < pds.length; i++) {
				// Get property name
				String propName = pds[i].getName();
				System.out.println(propName);
			}
			// class, prop1, prop2, PROP3
		} catch (java.beans.IntrospectionException e) {
		}
	}

}
