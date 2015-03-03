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

package net.mcube.library;

import net.mcube.datatypes.Profile;
import net.mcube.extensions.songs.SongAttributes;

public class AlbumProfile extends Profile {

	public String getImagePath() {
		String homePath = LibraryImpl.getDefault().getContext().getHome();
		String rPath = getItem().getString(SongAttributes.ALBUM_COVER_FRONT_PATH);
		return homePath + rPath;
	}

	public String getThumbnailsPath() {
		String homePath = LibraryImpl.getDefault().getContext().getHome();
		String rPath = getItem().getString(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL_PATH);
		return homePath + rPath;
	}

}
