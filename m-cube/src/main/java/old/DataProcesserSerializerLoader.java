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

package old;

import java.io.*;
import java.util.*;

import net.della.mplatform.application.datatypes.ObservableItem;


/**
 * 
 * @deprecated
 */
public abstract class DataProcesserSerializerLoader extends SerializerLoader {

	private final Map propsToSave;
	private final String fileName;

	public DataProcesserSerializerLoader(String filename) {
		propsToSave = new HashMap();
		this.fileName = filename;
	}

	public List loadList(String filename) {
		try {
			FileInputStream in = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(in);
			ObjectInputStream s = new ObjectInputStream(bis);
			Object o = s.readObject();
			in.close();
			bis.close();
			s.close();
			if (o instanceof List)
				return loadList1(filename);
			if (o instanceof Map)
				return loadList2(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List loadList1(String filename) {
		List list = super.loadList(filename);
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map props = (Map) it.next();
			processDataOnLoad(props);
		}
		return list;
	}

	private List loadList2(String filename) {
		try {
			List list = new ArrayList();
			FileInputStream in = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(in);
			ObjectInputStream s = new ObjectInputStream(bis);
			Object o = s.readObject();
			while (o != null) {
				Map props = (Map) o;
				processDataOnLoad(props);
				list.add(props);
				try {
					o = s.readObject();
				} catch (EOFException e) {
					o = null;
				}
			}
			s.close();
			bis.close();
			in.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new LinkedList();

	}

	public void save(Object object, String filename) throws LoaderException {
			try {
				FileOutputStream out = new FileOutputStream(filename);
				BufferedOutputStream bos = new BufferedOutputStream(out);
				ObjectOutputStream s = new ObjectOutputStream(bos);
				List list = (List) object;
				for (Iterator it = list.iterator(); it.hasNext();) {
					ObservableItem item = (ObservableItem) it.next();
					if (isToSave(item)) {
						propsToSave.clear();
						propsToSave.putAll(item.getData());
						processDataOnSave(propsToSave);
						s.writeUnshared(propsToSave);
						s.flush();
					}
				}
				s.close();
				out.close();
				bos.close();
			} catch (FileNotFoundException e) {
				throw new LoaderException(e);
			} catch (IOException e) {
				throw new LoaderException(e);
			}
	}

	protected abstract boolean isToSave(ObservableItem item);

	protected abstract void processDataOnLoad(Map props);

	protected abstract void processDataOnSave(Map props);

	public String getFileName() {
		return fileName;
	}


}
