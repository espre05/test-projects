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

package net.della.mplatform.binding;

import net.della.mplatform.application.datatypes.Item;
import net.della.mplatform.application.datatypes.ItemAccessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jgoodies.binding.beans.Model;


/**
 * @author della
 * 
 */

@SuppressWarnings("serial")
public class ModelItemWrapper extends Model {

   protected final Item item;
   private final Logger log;

   public ModelItemWrapper(Item item) {
      this.item = item;
      log = LoggerFactory.getLogger(getClass());
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
            .debug(
                  "There is no "
                        + propertyName
                        + " defined for this item. This is not a real problem, sometimes occur when the Item Schema has been updated and the existing data does not (still) contains a value for the new properties. The wrapper simply ignore this situation and return a default value. The exception is logged anyway. ",
                  e);
   }

}
