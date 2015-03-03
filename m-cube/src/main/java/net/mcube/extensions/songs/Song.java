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
 * Created on 8-ago-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package net.mcube.extensions.songs;

import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.stuff.generic.util.StringFormatter;


/**
 * @author Daniele
 *
 */
public interface Song {

	public String getPath();

	public void removeID3v1();

	public void removeTag();

	public void updateID3Tag(boolean ID3v2Only);

	public void formatData(StringFormatter sf);

	public String getTitle();

	public String getAlbum();

	public String getArtist();

	public String getTrack();

	public String getYear();

	public String getDisc();

	public String isLive();
	
	public String getBitRate();

	public int compare(ObservableItem song, String compareField);
	

}