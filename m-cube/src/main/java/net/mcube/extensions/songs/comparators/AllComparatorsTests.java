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
 * Created on 26-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.songs.comparators;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * @author Daniele
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AllComparatorsTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for net.mcube.songs.comparators");
        //$JUnit-BEGIN$
        suite.addTestSuite(SongComparatorTest.class);
        suite.addTestSuite(TrackNumberSongComparatorTest.class);
        //$JUnit-END$
        return suite;
    }
}
