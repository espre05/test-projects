/*
 * Created on 13-feb-2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package della.swaf.extensions.defaultPage;

import java.io.File;
import java.util.*;

import com.sourcesense.stuff.loader.TextLoader;

import della.swaf.application.application.Application;
import della.swaf.application.application.Extension;
import della.swaf.application.gui.structure.AbstractPage;
import della.swaf.application.gui.structure.ApplicationWindow;
import della.swaf.docking.DockablePage;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DefaultPageExtension implements Extension {

   public static final String ID = "DefaultPage";

   private AbstractPage page;

   private ApplicationWindow appWindow;

   public static final String PAGE_ID = ID;

   public void init(Application application) {
      this.appWindow = application.getWindow();

      page = new DockablePage();

      page.setId(PAGE_ID);
      page.setName("default page");

      application.getWindow().registerPage(page.getID(), page);
   }

   protected void reloadViewsStyles() {

      Properties config = loadConfig();

      try {
         appWindow.setNewLookAndFeel(config.getProperty("laf"));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * @return
    */
   private Properties loadConfig() {
      Properties config = new Properties();
      TextLoader loader = new TextLoader();
      String configPath = System.getProperty("user.dir") + File.separator + "config.txt";
      List rowConfig = loader.loadStringList(new File(configPath));
      for (Iterator it = rowConfig.iterator(); it.hasNext();) {
         String line = (String) it.next();
         StringTokenizer tokenizer = new StringTokenizer(line, "=");
         config.setProperty(tokenizer.nextToken(), tokenizer.nextToken());
      }
      return config;
   }

   public String getId() {
      return ID;
   }

}