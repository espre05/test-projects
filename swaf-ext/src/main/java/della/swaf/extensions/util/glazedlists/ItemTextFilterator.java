/**
 * 
 */
package della.swaf.extensions.util.glazedlists;

import java.util.List;

import ca.odell.glazedlists.TextFilterator;
import della.swaf.application.datatypes.Item;

public abstract class ItemTextFilterator implements TextFilterator {
   public void getFilterStrings(List baseList, Object element) {
      getFilterStrings(baseList, (Item) element);
   }

   public abstract void getFilterStrings(List baseList, Item element);
}