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
 * Created on 31-ott-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.datatypes;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface ItemAttributes {

	/**
	 * 
	 * @deprecated
	 */
    String PATH = "Path";

    /**
	 * 
	 * @deprecated
	 */
    String FILENAME = "Filename";

    /**
	 * 
	 * @deprecated
	 */
    String EXTENSION = "Extension";
    /**
	 * 
	 * @deprecated
	 */
    String FILE_LAST_MODIFIED = "Last modified";

    String DATE_ADDED = "Date Added";
    /**
	 * 
	 * @deprecated
	 */
    String ID = "id";

    /**
     * Should refer to path relative to one of the library top managed folders
     */
    String RELATIVE_PATH = "relative path";
    /**
	 * 
	 * @deprecated
	 */
    String FILE_SIZE = "File Size";

    String VIRTUAL_PATH = "virtual path";

	String TYPE = "item type";

	String CONTEXT = "context";

	String ABSOLUTE_PATH = "absolute path";

}
