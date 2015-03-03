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

package net.della.mplatform.application.datatypes;

import java.io.File;

import net.della.stuff.generic.file.FileUtils;

public class FileItem extends ObservableItem {

   private String type;

   private String relativePath;

   public void setType(String type) {
      this.type = type;
      put(FileItemAttributes.TYPE, type);

   }

   public String getType() {
      if (type != null)
         return type;
      return getString(FileItemAttributes.TYPE);
   }

   public void renameFileFromData() {

   }

   public void formatData() {

   }

   public String getPath() {
      // String vPath = getString(FileItemAttributes.VIRTUAL_PATH);
      // String rPath = getString(FileItemAttributes.RELATIVE_PATH);
      // Context context = LibraryImpl.getDefault().getContext();
      // String realHome = context.getPath(vPath);
      // return realHome + rPath;
      return "";
   }

   public String getExtension() {
      return FileUtils.getExtension(new File(getPath()));
   }

   public void setExtension(String ext) {
      File file = new File(getPath());
      // FileUtils.setExtension(file, ext);
   }

   public void deleteFromDisk() {
      try {
         File f = new File(getPath());
         f.delete();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // public Profile getProfile(Class key) {
   // Class profileClassName = (Class) get(key.getName());
   // try {
   // Profile newInstance = (Profile) profileClassName.newInstance();
   // newInstance.setItem(this);
   // return newInstance;
   // } catch (InstantiationException e) {
   // e.printStackTrace();
   // } catch (IllegalAccessException e) {
   // e.printStackTrace();
   // }
   // return null;
   // }

   public void setProfile(Class name) {
      put(name.getName(), name);
   }

   public final String getRelativePath() {
      if (relativePath != null)
         return relativePath;
      return getString(FileItemAttributes.RELATIVE_PATH);
   }

   public final void setRelativePath(String relativePath) {
      this.relativePath = relativePath;
      put(FileItemAttributes.RELATIVE_PATH, relativePath);
   }

}
