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

package net.della.stuff.generic.file;

import java.io.File;
import java.io.FileFilter;


public class FileFilters {

   public final static FileFilter M3UFileFilter = new BasicFileFilter() {
      public boolean accept(File f) {
         if (!super.accept(f)) {
            return false;
         }
         if (FileUtils.getExtension(f).equals("m3u")) {
            return true;
         }
         return false;
      }
   };
   public static final FileFilter MediaFileFilter = new BasicFileFilter() {
      public boolean accept(File f) {
         if (!super.accept(f)) {
            return false;
         }
         if (FileUtils.getExtension(f).equals("mp3"))
            return true;
         if (FileUtils.getExtension(f).equals("mpc"))
            return true;
         if (FileUtils.getExtension(f).equals("wav"))
            return true;
         if (FileUtils.getExtension(f).equals("ape"))
            return true;
         if (FileUtils.getExtension(f).equals("wma"))
            return true;
         if (FileUtils.getExtension(f).equals("ogg"))
            return true;
         if (FileUtils.getExtension(f).equals("flac"))
            return true;
         return false;
      }
   };
   public static final FileFilter ImageFileFilter = new BasicFileFilter() {
      public boolean accept(File f) {
         if (!super.accept(f)) {
            return false;
         }
         String extension = FileUtils.getExtension(f);
         if ("jpg".equals(extension))
            return true;
         if ("jpeg".equals(extension))
            return true;
         if ("gif".equals(extension))
            return true;
         if ("png".equals(extension))
            return true;
         return false;
      }
   };
   public final static FileFilter AllFileFilter = new BasicFileFilter() {
      public boolean accept(File f) {
         return super.accept(f) && !f.isDirectory();
      }
   };
   public final static FileFilter DirectoryFileFilter = new BasicFileFilter() {
      public boolean accept(File f) {
         return super.accept(f) && f.isDirectory();
      }
   };

}
