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

package net.della.stuff.generic.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PropertiesFromClasspathTest {

   @Test
   public void testLoadNotExistent() throws Exception {
      try {
         PropertiesFromClasspath props = new PropertiesFromClasspath("/notExistent.properties");
         fail("Should throw an exception if property file doesn't exists");
      } catch (Exception e) {
      }
   }

   @Test
   public void testLoad() throws Exception {
      PropertiesFromClasspath props = new PropertiesFromClasspath(
            "/net/della/stuff/generic/config/some.properties");
      assertEquals("Should find property file ", "bar", props.getProperty("foo"));
   }

}
