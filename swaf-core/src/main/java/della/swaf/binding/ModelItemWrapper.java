package della.swaf.binding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jgoodies.binding.beans.Model;

import della.swaf.application.datatypes.Item;
import della.swaf.application.datatypes.ItemAccessException;

/**
 * @author della
 * 
 */

@SuppressWarnings("serial")
public class ModelItemWrapper extends Model {

   protected final Item item;
   private final Log log;

   public ModelItemWrapper(Item item) {
      this.item = item;
      log = LogFactory.getLog(getClass());
   }

   protected final void writeProperty(String propertyName, Object value) {
      item.put(propertyName, value);
      firePropertyChange(propertyName, value);
   }

   protected void firePropertyChange(String propertyName, Object value) {
      Object oldValue = item.get(propertyName);
      firePropertyChange(propertyName, oldValue, value);
   }

   protected String readStringProperty(String propertyName) {
      try {
         return item.getString(propertyName);
      } catch (ItemAccessException e) {
         logWarning(propertyName, e);
         return "";
      }
   }

   protected boolean readBooleanProperty(String propertyName) {
      try {
         return item.getBoolean(propertyName);
      } catch (ItemAccessException e) {
         logWarning(propertyName, e);
         return false;
      }
   }

   public Long readLongProperty(String propertyName) {
      try {
         return item.getLong(propertyName);
      } catch (ItemAccessException e) {
         logWarning(propertyName, e);
         return 0L;
      }
   }

   private void logWarning(String propertyName, ItemAccessException e) {
      log
            .debug("There is no "
                  + propertyName
                  + " defined for this item. This is not a real problem, sometimes occur when the Item Schema has been updated and the existing data does not (still) contains a value for the new properties. The wrapper simply ignore this situation and return a default value. The exception is logged anyway. ");
      log.debug(e);
   }

}
