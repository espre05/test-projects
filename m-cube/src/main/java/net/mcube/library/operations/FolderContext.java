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

/**
 * 
 */
package net.mcube.library.operations;

import java.util.List;


class FolderContext {

	List files;

	Tracklist tracklist;

	CoverInfo coverInfo;

	void setFiles(List files) {
		this.files = files;
	}

	public void setCoverInfo(CoverInfo info) {
		this.coverInfo = info;
	}

	public Tracklist getTracklist() {
		return tracklist;
	}

	void setTracklist(Tracklist trackList) {
		this.tracklist = trackList;
	}

	public CoverInfo getCoverInfo() {
		return coverInfo;
	}

	public List getFiles() {
		return files;
	}
}