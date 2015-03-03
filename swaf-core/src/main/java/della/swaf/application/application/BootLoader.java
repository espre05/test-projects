package della.swaf.application.application;

import java.io.File;

import org.apache.commons.logging.LogFactory;

import com.sourcesense.stuff.file.FileUtils;

import della.swaf.application.gui.structure.ApplicationWindow;

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
         log(e);
      } catch (InstantiationException e) {
         log(e);
      } catch (IllegalAccessException e) {
         log(e);
      }
      return application;
   }

   private void log(Exception e) {
      LogFactory.getLog(getClass()).error(e);
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
