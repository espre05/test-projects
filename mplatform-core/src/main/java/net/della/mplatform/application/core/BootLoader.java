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

package net.della.mplatform.application.core;

import java.io.File;

import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.stuff.generic.file.FileUtils;

import org.slf4j.LoggerFactory;

public class BootLoader {

   private ApplicationWindowBuilder applicationWindowBuilder = null;

   private ApplicationBuilder applicationBuilder = null;

   /**
    * Convenience method to use Builders from local package with standard names
    * 
    * @return
    */
   public Application loadApplication() {

      DefaultApplication application = create();

      init();

      post();

      return application;
   }

   /**
    * This method calls in a sequence: {@link #create()}, {@link #init()} and
    * {@link #post()}
    * 
    * @param appBuilderClassName
    * @param windowBuilderClassName
    * @return the application as created by {@link #create()}
    */
   public Application loadApplication(String appBuilderClassName, String windowBuilderClassName) {

      DefaultApplication application = create(appBuilderClassName, windowBuilderClassName);

      init();

      post();

      return application;

   }

   public void post() {
      applicationWindowBuilder.postLoading();
      applicationBuilder.postLoading();
   }

   public void init() {
      initSystemEnvironment(getOSSpecificLibPath());
      applicationWindowBuilder.initMainWindow();
      applicationBuilder.initExtensions();
      applicationWindowBuilder.showMainWindow();
   }

   public DefaultApplication create() {
      String packageName = this.getClass().getPackage().getName();
      String appBuilderName = packageName + ".ApplicationBuilder";
      String windowBuilderName = packageName + ".ApplicationWindowBuilder";
      return create(appBuilderName, windowBuilderName);
   }

   public DefaultApplication create(String appBuilderClassName, String windowBuilderClassName) {
      DefaultApplication application = null;
      try {
         Class c = Class.forName(appBuilderClassName);
         applicationBuilder = (ApplicationBuilder) c.newInstance();
         Class windowClass = Class.forName(windowBuilderClassName);
         applicationWindowBuilder = (ApplicationWindowBuilder) windowClass.newInstance();

         applicationBuilder.createApplication();
         applicationBuilder.loadApplication();
         application = applicationBuilder.getApplication();
         applicationWindowBuilder.createWindow();
         ApplicationWindow appWindow = applicationWindowBuilder.getApplicationWindow();
         application.setMainWindow(appWindow);
         applicationWindowBuilder.loadWindow(application);

         applicationBuilder.loadExtensions();

      } catch (ClassNotFoundException e) {
         logError(e);
      } catch (InstantiationException e) {
         logError(e);
      } catch (IllegalAccessException e) {
         logError(e);
      }
      return application;
   }

   private void logError(Exception e) {
      LoggerFactory.getLogger(getClass()).error("Error during application boot", e);
   }

   private static void initSystemEnvironment(String nativeLibPath) {
      String sysProperty = "java.library.path";
      String oldLibraryPath = System.getProperty(sysProperty);
      String pathSeparator = System.getProperty("path.separator");
      String newPath = oldLibraryPath + pathSeparator + nativeLibPath;
      System.setProperty(sysProperty, newPath);
   }

   public static String getOSSpecificLibPath() {
      String mcubeLibPath = FileUtils.getCurrentFolderAbsolutePath() + File.separator + "lib"
            + File.separator;
      String os = System.getProperty("os.name");
      if (os.indexOf("Windows") != -1)
         mcubeLibPath += "win32";
      if (os.indexOf("Linux") != -1)
         mcubeLibPath += "linux";
      if (os.indexOf("Mac") != -1)
         mcubeLibPath += "macosx";
      return mcubeLibPath;
   }

}
