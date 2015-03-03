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

package net.mcube.extensions.musicbrainz;

import java.io.IOException;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.ldodds.musicbrainz.BeanPopulator;
import com.ldodds.musicbrainz.MusicBrainzImpl;

public class MusicBrainzTrackTest {
	
	void test() {
		MusicBrainzImpl server = new MusicBrainzImpl();
		List albums = null;
		try {
			System.out.println("querying remote db...");
			String trmId = "";
			Model model = server.getTrackByTRMId(trmId, 4);
			System.out.println("data retrived!");
			System.out.println("");
			albums = BeanPopulator.getAlbums(model);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new MusicBrainzTrackTest().test();
	}

}
