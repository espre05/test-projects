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

package db4o;

import java.util.HashMap;
import java.util.Map;

public class BeanItem {

	private Map dataMap;

	private String type;

	private String id;

	private String value;

	public final String getId() {
		if (id != null)
			return id;
		return (String) dataMap.get("idKey");
	}

	public final void setId(String id) {
		this.id = id;
		dataMap.put("idKey", id);
	}

	public BeanItem() {
		dataMap = new HashMap();
	}

	public void setType(String type) {
		this.type = type;
		dataMap.put("typeKey", type);
	}

	public String getType() {
		if (type != null)
			return type;
		return (String) dataMap.get("typeKey");
	}

	public void setValue(String value) {
		this.value = value;
		dataMap.put("valueKey", type);
	}

	public final String getValue() {
		if (value!= null)
			return value;
		return (String) dataMap.get("valueKey");
	}

}
