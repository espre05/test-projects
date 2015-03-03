/**
 * 
 */
package della.swaf.extensions.util.glazedlists;

import java.util.*;

import della.swaf.application.datatypes.Item;

public class PropertyTextFilterator extends ItemTextFilterator {
   private List<String> propertyNames;

   public PropertyTextFilterator(String... propertyNames) {
      this.propertyNames = new ArrayList<String>(Arrays.asList(propertyNames));
   }

   public void getFilterStrings(List baseList, Item item) {
      for (Iterator it = propertyNames.iterator(); it.hasNext();) {
         try {
            String value = (String) it.next();
            baseList.add(item.getString(value));
         } catch (RuntimeException e) {
            // LoggerFactory.getLogger(getClass()).debug("No value for filtered
            // property. ", e);
         }
      }
   }

   public void addPropertyToFilter(String property) {
      propertyNames.add(property);
   }
}