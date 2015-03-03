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

/*
 * Created on 13-feb-2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package net.della.mplatform.application.extensions;

import java.io.File;
import java.util.*;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.gui.structure.AbstractPage;
import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.mplatform.docking.DockablePage;
import net.della.stuff.generic.loader.TextLoader;

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