/**
 * 
 */
package della.swaf.extensions.util.glazedlists;

import java.util.List;

import net.della.mplatform.application.datatypes.Item;
import ca.odell.glazedlists.TextFilterator;

public abstract class ItemTextFilterator implements TextFilterator {
   public void getFilterStrings(List baseList, Object element) {
      getFilterStrings(baseList, (Item) element);
   }

   public abstract void getFilterStrings(List baseList, Item element);
}