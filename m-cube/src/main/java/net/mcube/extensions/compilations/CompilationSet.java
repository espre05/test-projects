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

package net.mcube.extensions.compilations;

import java.util.Collection;
import java.util.Iterator;

import jin.collection.core.Criteria;
import jin.collection.core.Iter;
import net.della.mplatform.core.datatypes.Item;
import net.mcube.extensions.songs.SongSet;

public class CompilationSet extends SongSet {
	
	private String name;
	private String oldValue;
	
	public void setData(String key, Object value) {
		super.put(key, value);
		this.oldValue = name;
		this.name = (String) value;
	}
	
	public void update(String propertyName) {
		replace(oldValue, name);
	}

	private void replace(final String oldValue, String text) {
		for (Iterator it = listChilds().iterator(); it.hasNext();) {
			Item item = (Item) it.next();
			Collection compilations = (Collection) item.get(MyCompilations.COMPILATION);
			Collection extractedSet = Iter.extract(compilations, compilationMatchCriteria(oldValue));
			CompilationInfo info = (CompilationInfo) extractedSet.iterator().next();
//			CompilationInfo info = findCompilation(compilations, oldValue);
			info.compilationName = name;
			item.put(MyCompilations.COMPILATION, compilations);
//			ItemUtils.replace(oldValue, text, item, MyCompilations.COMPILATION);
		}
	}

	private Criteria compilationMatchCriteria(final String oldValue) {
		return new Criteria() {
		
			public boolean match(Object element) {
				CompilationInfo info = (CompilationInfo) element;
				if (info.compilationName.equals(oldValue))
					return true;
				return false;
			}			
		};
	}

}
