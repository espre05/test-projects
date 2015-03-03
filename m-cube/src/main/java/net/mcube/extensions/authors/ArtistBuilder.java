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

/*
 * Created on 29-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.authors;

import java.util.*;

import net.della.mplatform.application.datatypes.ItemSet;
import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.stuff.generic.util.StringListUtil;
import net.mcube.datatypes.DefaultItemSetBuilder;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.extensions.songs.comparators.ComparatorFactory;



final class ArtistBuilder extends DefaultItemSetBuilder {

    private Map rawDataMap;
	private ItemSet artistSet;

    protected ItemSet createItemSet() {    	
    	artistSet = new ItemSet(ComparatorFactory.getInstance().createSongSetComparator(
				SongAttributes.ALBUM));
    	return artistSet;
    } 
	public void buildItemSet(ItemSet itemSet) {
        
    	List baseList = itemSet.listChilds();
        rawDataMap = initMap();

        artistSet.setMainAttribute(SongAttributes.USEFUL_ARTIST);
        
        populateRawMapWithSongsInfo(baseList);

        String attribute = "";


        attribute = SongAttributes.ALBUM;
        artistSet.put(attribute, putAllStringFrom((List) rawDataMap.get(attribute)));

        String artist = chooseRightValueFor((List) rawDataMap.get(SongAttributes.ARTIST));
        itemSet.put(SongAttributes.ARTIST, artist);
//        String artist = (String) itemSet.getData(SongAttributes.ARTIST);
//        attribute = SongAttributes.ARTIST;
//        String artist = artistSet.getString(attribute);
//        artist = StringFormatter.format(artist, StringFormatter.ALL_CAPITAL);
//        artistSet.setData(attribute, artist);
        artistSet.put(SongAttributes.USEFUL_ARTIST, removeTheFrom(artist));

        attribute = SongAttributes.TOTAL_TRACKS;
        artistSet.put(attribute, new String(((List) rawDataMap.get(attribute)).size() + ""));

//        return artistSet;
    }

    private void populateRawMapWithSongsInfo(List mp3SongsList) {
        for (Iterator it = mp3SongsList.iterator(); it.hasNext();) {
            ObservableItem song = (ObservableItem) it.next();
            addSongDataToMap(song);
        }
    }

    private static Map initMap() {
        Map newMap = new HashMap();
        newMap.put(SongAttributes.ARTIST, new LinkedList());
        newMap.put(SongAttributes.ALBUM, new LinkedList());
        newMap.put(SongAttributes.TOTAL_TRACKS, new LinkedList());
        return newMap;
    }

    /**
     * populate map with values from this song the map will be like:
     * key="artist" value=["Led Zeppelin", "Pink Floyd", "R.E.M."] it is the row
     * map from where we choose the SongSet real attributes
     */
    private void addSongDataToMap(ObservableItem song) {
        for (Iterator iter = rawDataMap.keySet().iterator(); iter.hasNext();) {
            Object key = iter.next();
            List traks = (List) rawDataMap.get(key);
            traks.add(song.getString((String) key));
        }
        //addDerivedData(map, song);
    }

    private static String chooseRightValueFor(List list) {
        String value1 = "";
        if (StringListUtil.haveAllElementsTheSameValue(list))
            value1 = (String) list.get(0);
        return value1;
    }

    private static String putAllStringFrom(List list) {
        //TODO: optimize: insert any string just one times
        return list.toString();
    }
    
	private static String removeTheFrom(String artist) {
		if (artist.length() < 4)
			return artist;
		if (artist.substring(0, 4).equalsIgnoreCase("the "))
			return artist.substring(4, artist.length());
		return artist;
	}

}