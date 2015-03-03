/**
 * 
 */
package della.swaf.extensions.util.glazedlists;

import net.della.mplatform.application.datatypes.MapItem;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.TransformedList;
import ca.odell.glazedlists.event.ListEvent;

final class WrapperList extends TransformedList {
   private WrapperList(EventList source) {
      super(source);
      source.addListEventListener(this);
   }

   public void listChanged(ListEvent listChanges) {
      updates.forwardEvent(listChanges);
   }

   public Object get(int index) {
      MapItem item = (MapItem) super.get(index);
      return item.get(item.getMainAttribute());
   }
}