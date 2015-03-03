package della.swaf.application.application;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;

import com.sourcesense.stuff.event.Event;
import com.sourcesense.stuff.event.EventListener;

import della.swaf.extensions.InitializationFileParser;

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
