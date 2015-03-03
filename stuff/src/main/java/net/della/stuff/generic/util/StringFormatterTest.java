/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

/*
 * Created on 11-nov-2003
 *
 */
package net.della.stuff.generic.util;

import junit.framework.TestCase;

/**
 * @author Daniele
 */
public class StringFormatterTest extends TestCase {

	public void testFormatEmptyString() {
		String s = "";
		s = StringFormatter.format(s, StringFormatter.ALL_CAPITAL);
		assertEquals("", s);
	}
	
	public void testFormatOneString() {
		String s = "RadioHEAD";
		s = StringFormatter.format(s, StringFormatter.ALL_CAPITAL);
		assertEquals("Radiohead", s);		
	}
	
	public void testFormatFirstLow() {
		String s = "lEd ZeppELin";
		s = StringFormatter.format(s, StringFormatter.ALL_CAPITAL);
		assertEquals("Led Zeppelin", s);		
	}
	
	public void testFormatTwoString() {
		String s = "LEd ZeppELin";
		s = StringFormatter.format(s, StringFormatter.ALL_CAPITAL);
		assertEquals("Led Zeppelin", s);
	}
	
	public void testFormatManyString() {
		String s = "JON speNCer BlUEs explOSIon";
		s = StringFormatter.format(s, StringFormatter.ALL_CAPITAL);
		assertEquals("Jon Spencer Blues Explosion", s);
	}
	
	public void testFormatManyStringFirstCapital() {
		String s = "Cara ti AMo";
		s = StringFormatter.format(s, StringFormatter.FIRST_CAPITAL);
		assertEquals("Cara ti amo", s);
	}
	
	public void testFormatManyStringAllCapitalWithAnd() {
		String s = "Nick cave AnD the Bad seeDS";
		s = StringFormatter.format(s, StringFormatter.ALL_CAPITAL);
		assertEquals("Nick Cave and The Bad Seeds", s);
	}
	
	public void testFormatAllCapitalWithSpecial() {
		String s = "tHE Chemical Brothers featuring the Bad Seeds";
		StringFormatter sf = new StringFormatter();
		sf.addLower("the");
		sf.addLower("and");
		sf.addLower("featuring");
		sf.setStyle(StringFormatter.ALL_CAPITAL);
		sf.setForceFirstCapital(true);
		s = sf.format(s);
		assertEquals("The Chemical Brothers featuring the Bad Seeds", s);
	}
	
	public void testClean() {
		String s = "In a bar, under: /Jesus< sea minor >";
		s = StringFormatter.clean(s);
		assertEquals("In a bar under Jesus sea minor ", s);
	}
	
	public void testCleanPipe() {
		String s = "In a bar, under: /Jesus< sea minor|>";
		s = StringFormatter.clean(s);
		assertEquals("In a bar under Jesus sea minor", s);
	}
	
	public void testCleanQuestionMark() {
		String s = "In a bar, under: /Jesus< sea *minor?>";
		s = StringFormatter.clean(s);
		assertEquals("In a bar under Jesus sea minor", s);
	}
	
	
}
