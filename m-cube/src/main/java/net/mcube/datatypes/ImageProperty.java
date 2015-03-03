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

package net.mcube.datatypes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import net.della.mplatform.application.datatypes.Property;
import net.della.mplatform.application.persistence.BasicLibrary;
import net.mcube.library.LibraryImpl;

import org.apache.commons.logging.LogFactory;

public class ImageProperty implements Property {

   private String path;

   // private ImageCache imageCache;

   static public final String DEFAULT_CD_COVER_FILE_NAME = "empty_case.jpg";

   public static final String DEFAULT_CD_COVER_THUMBNAIL_FILE_NAME = "empty_case_thumbnail.jpg";

   // private int maxCacheSize;

   // private boolean useCache;

   private static ImageProperty instance;

   // private ImageProperty(String frontImagePath) {
   // this.path = frontImagePath;
   // File file = new File(path);
   // if (!file.exists()) {
   // LogFactory.getLog(this.getClass()).info(
   // "creata una ImageProperty per un file non esistente: " + path);
   // }
   // useCache = true;
   // maxCacheSize = 25;
   // }

   public static ImageProperty getInstance() {
      if (instance == null)
         instance = new ImageProperty();
      return instance;
   }

   //	
   // public static ImageProperty getInstance() {
   // if (instance == null)
   // throw new IllegalStateException("image cache is missing, use
   // ImageProperty.setCache()");
   // return instance;
   // }

   public Image getValue(Object key) {
      Image rawImage = null;
      LibraryImpl library = (LibraryImpl) BasicLibrary.getDefault();
      String picturesHome = library.getPicturesHome();
      try {
         rawImage = Java2dHelper.loadCompatibleImage(new File(picturesHome + File.separator + key));
      } catch (IOException e) {
         LogFactory.getLog(this.getClass()).info("file not found: '" + path + "'");
         e.printStackTrace();
         throw new IllegalArgumentException("image does not exists in archive");
      }
      return rawImage;
   }

   // public Image _getValue(Object key) {
   //
   // String path = (String) key;
   // Image rawImage = imageCache.getDefault();
   // if (!useCache) {
   // try {
   // rawImage = Java2dHelper.loadCompatibleImage(new File(path));
   // } catch (IOException e) {
   // LogFactory.getLog(this.getClass()).info("file not found: " + path);
   // e.printStackTrace();
   // throw new IllegalArgumentException("image does not exists in archive");
   // }
   // return rawImage;
   // }
   //
   // if (imageCache.containsKey(path))
   // return imageCache.get(path);
   //
   // try {
   // rawImage = Java2dHelper.loadCompatibleImage(new File(path));
   // } catch (IOException e) {
   // LogFactory.getLog(this.getClass()).info("file not found: " + path);
   // e.printStackTrace();
   // }
   // cleanCacheIfNeeded();
   // imageCache.put(path, rawImage);
   // return rawImage;
   // }

   // private void cleanCacheIfNeeded() {
   // if (imageCache.size() > maxCacheSize) {
   // Iterator it = imageCache.keySet().iterator();
   // for (int i = 0; i < 5; i++) {
   // String key = (String) it.next();
   // it.remove();
   // LogFactory.getLog(this.getClass()).debug("removing image from cache: " +
   // key);
   // }
   // }
   // }

   public void setValue(Object imagePath) {
      this.path = (String) imagePath;
   }

   // public void setUseCache(boolean useCache) {
   // this.useCache = useCache;
   // }

}
