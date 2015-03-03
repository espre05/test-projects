package della.swaf.application.application;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.*;
import java.util.logging.Logger;

import com.sourcesense.stuff.event.*;
import com.sourcesense.stuff.event.EventListener;
import com.sourcesense.stuff.file.FileUtils;

import della.swaf.application.gui.ActionFactory;
import della.swaf.application.gui.structure.ApplicationWindow;
import della.swaf.application.persistence.Library;
import della.swaf.background.AbstractJob;
import della.swaf.background.BackgroundManager;

public class DefaultApplication implements Application {

   private ApplicationWindow mainWindow;

   protected final EventMulticaster multicaster;

   protected final List extensions;

   protected String homeFolderPath;

   protected final String extensionsFolderPath;

   protected final String configFolderPath;

   protected final String resourcesFolderPath;

   protected final BackgroundManager backgroundOperationManager;

   private DefaultTerminatorHandler terminatorHandler;

   private Library library;

   public DefaultApplication() {

      Logger.getLogger(this.getClass().getName()).info("starting: " + new Date());

      extensions = new ArrayList();

      multicaster = new EventMulticaster();

      backgroundOperationManager = new BackgroundManager();
      // URL location =
      // FileUtils.class.getProtectionDomain().getCodeSource().getLocation();
      // homeFolderPath = location.toString();
      homeFolderPath = FileUtils.getCurrentFolderAbsolutePath();

      Logger.getLogger(this.getClass().getName()).info("home: " + homeFolderPath);
      extensionsFolderPath = homeFolderPath + File.separator + "extensions" + File.separator;
      configFolderPath = homeFolderPath + File.separator + "config" + File.separator;
      resourcesFolderPath = homeFolderPath + File.separator + "resources" + File.separator;
      createFolders();
      terminatorHandler = new DefaultTerminatorHandler();

   }

   protected void createFolders() {

      File homeFolder = new File(homeFolderPath);
      if (!homeFolder.exists())
         homeFolder.mkdir();
      File pluginsFolder = new File(extensionsFolderPath);
      if (!pluginsFolder.exists())
         pluginsFolder.mkdir();
      File configFolder = new File(configFolderPath);
      if (!configFolder.exists())
         configFolder.mkdir();
   }

   public String getApplicationHome() {
      return homeFolderPath;
   }

   protected void setMainWindow(ApplicationWindow appWindow) {
      this.mainWindow = appWindow;
   }

   public ApplicationWindow getWindow() {
      return mainWindow;
   }

   public void addListener(EventListener listener) {
      multicaster.addListener(listener);
   }

   public void addListener(EventListener listener, EventFilter filter) {
      multicaster.addListener(listener, filter);
   }

   public void fireEvent(Event evt) {
      multicaster.notifyEvent(evt);
   }

   public void removeListener(EventListener listener) {
      multicaster.removeListener(listener);
   }

   public Logger getLogger() {
      return Logger.getLogger("mcube");
   }

   public void registerExtension(Extension extension) {
      extensions.add(extension);
   }

   public String getConfigFolder() {
      return configFolderPath;
   }

   public String getExtensionsFolder() {
      return extensionsFolderPath;
   }

   public String getResourcesFolder() {
      return resourcesFolderPath;
   }

   public void runOperation(AbstractJob op) {
      backgroundOperationManager.runSimple(op);
   }

   public void runScheduledOperation(AbstractJob op) {
      backgroundOperationManager.runScheduled(op);
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      backgroundOperationManager.addPropertyChangeListener(listener);
   }

   public Library getLibrary() {
      return library;
   }

   public void setLibrary(Library library) {
      this.library = library;
   }

   public DefaultTerminatorHandler getTerminatorHandler() {
      return terminatorHandler;
   }

   public void setTerminatorHandler(DefaultTerminatorHandler handler) {
      this.terminatorHandler = handler;
   }

   public ActionFactory getActionFactory() {
      ActionFactory actionFactory = new ActionFactory();
      actionFactory.setApplication(this);
      return actionFactory;
   }

   public void initExtensions() {
      for (Iterator it = extensions.iterator(); it.hasNext();) {
         Extension extension = (Extension) it.next();
         extension.init(this);
      }
   }

   public void reload() {
      initExtensions();
      getWindow().getCurrentPage().revalidateAndRepaint();
   }

}
