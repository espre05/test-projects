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
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.ldodds.musicbrainz.Album;
import com.ldodds.musicbrainz.BeanPopulator;
import com.ldodds.musicbrainz.MusicBrainzImpl;
import com.ldodds.musicbrainz.ReleaseDate;
import com.ldodds.musicbrainz.Track;

public class MusicBrainzAlbumTest {

	void test() {
		MusicBrainzImpl server = new MusicBrainzImpl();
		List albums = null;
		try {
			System.out.println("querying remote db...");
			Model model = server.findAlbumByName("The Girl In The Other Room", 4, 3);
			System.out.println("data retrived!");
			System.out.println("");
			albums = BeanPopulator.getAlbums(model);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String id;
		for (Iterator iter = albums.iterator(); iter.hasNext();) {
			Album element = (Album) iter.next();
			id = element.getId();
			System.out.println(element.getName());
			System.out.println(element.getArtist());
			printReleaseDates(element.getReleaseDates());
			System.out.println("rel type: " + element.getReleaseType());
			System.out.println("rel status: " + element.getReleaseStatus());

			System.out.println("id: " + element.getId());
			System.out.println("cd ids: " + element.getCdindexids());
			System.out.println("amazon id: " + element.getAmazonId());
			System.out.println("number of track: " + element.getTracks().size());
			printTracks(element.getTracks());
			System.out.println();
		}
	}

	static void printTracks(List tracks) {
		if (tracks == null) {
			System.out.println("no tracks info");
			return;
		}
		for (Iterator it = tracks.iterator(); it.hasNext();) {
			Track track = (Track) it.next();
			System.out.println(track.getNumber() + " " + track.getName() + " "
					+ track.getDuration());
		}
	}

	static void printReleaseDates(List releaseDates) {
		if (releaseDates == null) {
			System.out.println("no release date info");
			return;
		}
		for (Iterator it = releaseDates.iterator(); it.hasNext();) {
			ReleaseDate date = (ReleaseDate) it.next();
			System.out.println("rel date:" + date.getDate());
		}
	}

	public static void main(String[] args) {
		new MusicBrainzAlbumTest().test();
	}

}
