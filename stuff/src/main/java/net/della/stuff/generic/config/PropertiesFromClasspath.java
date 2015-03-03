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

import java.io.InputStream;
import java.util.Properties;

public class PropertiesFromClasspath {

   private Properties props = new Properties();

   public PropertiesFromClasspath(String path) throws Exception {
      try {
         InputStream resource = getClass().getResourceAsStream(path);
         props.load(resource);
      } catch (Exception e) {
         throw new Exception("Cannot read properties from: " + path + " because: " + e);
      }
   }

   public String getProperty(String name) {
      String property = props.getProperty(name);
      return property;
   }

   public Properties all() {
      return props;
   }

}
