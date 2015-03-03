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
 * Created on 22-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.songs;

import java.util.Properties;

/**
 * @author Daniele
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class SongAttributesAliases {
    
    private Properties map;
    private static SongAttributesAliases instance;
    
    private SongAttributesAliases() {
        map = new Properties();
        map.put(SongAttributes.TRACK_NUMBER, "#");
        map.put(SongAttributes.USEFUL_ARTIST, "Artist Name");
    }
    
    public void addAlias(String key, String alias) {
    	map.put(key, alias);
	}
    
    public String aliasFor(String key) {
        return map.getProperty(key, key);
    }
    
    public static SongAttributesAliases getInstance() {
        if (instance == null)
            instance = new SongAttributesAliases();
        return instance;
    }

}
