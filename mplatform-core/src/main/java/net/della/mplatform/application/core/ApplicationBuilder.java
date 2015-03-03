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
import java.util.*;
import java.util.logging.Logger;

import net.della.mplatform.application.extensions.InitializationFileParser;
import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventListener;

public class ApplicationBuilder {

   protected DefaultApplication application;

   protected void createApplication() {
      application = new DefaultApplication();
   }

   protected DefaultApplication getApplication() {
      return application;
   }

   public void loadApplication() {
      application.addListener(new EventListener() {

         public void eventHappened(Event e) {
            if (e.get(Event.TYPE).equals(Application.CLOSING))
               application.getTerminatorHandler().windowClosing();
            if (e.get(Event.TYPE).equals(Application.CLOSED)) {
               application.getTerminatorHandler().windowClosed();
            }
         }
      });
   }

   protected void postLoading() {
   }

   void initExtensions() {
      application.initExtensions();
   }

   // TODO: this method should return void
   Collection loadExtensions() {

      InitializationFileParser inputFileReader = createPlainStringInputFileReader();
      String defaultFileName = "extensions.properties";
      String fileName = application.getExtensionsFolder() + defaultFileName;
      File file = new File(fileName);
      Collection extensions = new ArrayList();
      if (!file.exists()) {
         // throw new IllegalStateException("file '" + fileName + "' does not
         // exist. Please, create one");
         Logger.getLogger(this.getClass().getName()).info("no extension file found");
         return extensions;
      }
      List extensionsClassNames = inputFileReader.load(fileName);
      for (Iterator it = extensionsClassNames.iterator(); it.hasNext();) {
         String element = (String) it.next();
         if (element.charAt(0) != '#')
            try {
               Class c = Class.forName(element);
               Extension extension = (Extension) c.newInstance();
               extensions.add(extension);
               application.registerExtension(extension);
            } catch (ClassNotFoundException e) {
               e.printStackTrace();
            } catch (InstantiationException e) {
               e.printStackTrace();
            } catch (IllegalAccessException e) {
               e.printStackTrace();
            }
      }
      return extensions;

   }

   static InitializationFileParser createPlainStringInputFileReader() {
      return new InitializationFileParser() {
         public Object parseLine(String line) {
            return line;
         }
      };
   }

}
