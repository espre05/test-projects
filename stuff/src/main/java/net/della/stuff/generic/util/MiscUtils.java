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

package net.della.stuff.generic.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

public class MiscUtils {

   public static File classFolder(Class klass) {
      return FileUtils.toFile(klass.getResource("."));
   }

   public static String dotToSlashNotation(String path) {
      return path.replaceAll("\\.", "/");
   }

   public static File getResourceAsFile(Class klass, String resourceName) {
      return FileUtils.toFile(klass.getResource(resourceName));
   }

   public static void closeResources(InputStream... streams) {
      for (int i = 0; i < streams.length; i++) {
         InputStream stream = streams[i];
         try {
            stream.close();
         } catch (IOException e) {
            throw new RuntimeException("Unable to free some resources", e);
         }
      }
   }

}
