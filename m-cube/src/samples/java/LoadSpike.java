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

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LoadSpike {

	public static void main(String[] args) {
		new LoadSpike().start();
		new Thread(new Runnable() {

			public void run() {
				// application runtime...
				while (true) {
					System.out.println("il programma � in esecuzione...");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();
	}

	private void start() {
		List list = loadList("songs.mml");
	}

	private List loadList(String filename) {
		try {
			List list = new ArrayList();
			FileInputStream in = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(in);
			ObjectInputStream s = new ObjectInputStream(bis);
			Object o = s.readObject();
			while (o != null) {
				Map props = (Map) o;
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

}
