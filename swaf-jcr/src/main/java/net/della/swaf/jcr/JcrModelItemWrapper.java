package net.della.swaf.jcr;

import java.util.Calendar;

import della.swaf.binding.ModelItemWrapper;

public class JcrModelItemWrapper extends ModelItemWrapper {

   public JcrModelItemWrapper(NodeItem item) {
      super(item);
   }

   private NodeItem getItem() {
      return (NodeItem) item;
   }

   protected void writeString(String propertyName, String value) {
      getItem().putString(propertyName, value);
      super.firePropertyChange(propertyName, value);
   }

   protected void writeDate(String propertyName, Calendar value) {
      getItem().putDate(propertyName, value);
      super.firePropertyChange(propertyName, value);
   }

   protected void writeLong(String propertyName, Long value) {
      getItem().putLong(propertyName, value);
      super.firePropertyChange(propertyName, value);
   }

   protected void writeBoolean(String propertyName, Boolean value) {
      getItem().putBoolean(propertyName, value);
      super.firePropertyChange(propertyName, value);
   }

}
