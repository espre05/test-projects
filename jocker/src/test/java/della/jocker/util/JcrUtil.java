package della.jocker.util;

import javax.jcr.*;

public class JcrUtil {

   public static void printProperties(PropertyIterator properties) throws ValueFormatException,
         RepositoryException {
      while (properties.hasNext()) {
         Property prop = (Property) properties.next();
         System.out.println(prop.getName());
      }
   }

}
