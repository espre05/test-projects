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
 * Created on 6-gen-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.mcube.extensions.songs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

/**
 * @author Daniele
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SongGuesserTest extends TestCase {
	
	String filename;
	
	public void testLearningRegex() {
		Pattern p = Pattern.compile("\\d*");
		Matcher m = p.matcher("801");
		assertTrue(m.matches());
		
		m = p.matcher("801 collection");
		//assertTrue(m.matches());
		
		p = Pattern.compile("\\d{1,2}");
		m = p.matcher("801");
		assertFalse(m.matches());
		
		p = Pattern.compile("\\d{1,2}");
		m = p.matcher("the 80s collection");
		assertFalse(m.matches());
		m = p.matcher("08");
		assertTrue(m.matches());
		
		m = p.matcher("Noir desir & 16 horsepower");
		assertFalse(m.matches());
		
		m = p.matcher("02");
		assertTrue(m.matches());
		
		p = Pattern.compile("\\d{1,4}");
		m = p.matcher("1987");
		assertTrue(m.matches());
		
		p = Pattern.compile("\\d{4}");
		m = p.matcher("1987");
		assertTrue(m.matches());
		
		p = Pattern.compile("\\d{4}");
		m = p.matcher("198");
		assertFalse(m.matches());
	}

}
