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

package net.mcube.util;

public class MiscUtils {

	public static String getDecade(int dateAdded) {
		if (dateAdded < 1920)
			return "";
		if (dateAdded < 1930)
			return "20";
		if (dateAdded < 1940)
			return "30";
		if (dateAdded < 1950)
			return "40";
		if (dateAdded < 1960)
			return "50";
		if (dateAdded < 1970)
			return "60";
		if (dateAdded < 1980)
			return "70";
		if (dateAdded < 1990)
			return "80";
		if (dateAdded < 2000)
			return "90";
		if (dateAdded < 2010)
			return "00";
		return "";
	}

}
