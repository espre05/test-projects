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

/*
 * Created on 28-lug-2004
 */

package net.della.mplatform.application.datatypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.Map;

import net.della.mplatform.application.persistence.BasicLibrary;

/**
 * @author Daniele
 */
public class ObservableItem extends NoChildMapItem {

   private String mainAttribute = "";

   private final PropertyChangeSupport propertyChangeSupport;

   private boolean persistent;

   public ObservableItem() {
      propertyChangeSupport = new PropertyChangeSupport(this);
   }

   @Override
   void writeData(String key, Object oldValue, Object newValue) {
      super.writeData(key, oldValue, newValue);
      notifyChanges(key, oldValue, newValue);
   }

   void notifyChanges(String key, Object oldValue, Object value) {
      BasicLibrary library = BasicLibrary.getDefault();
      if (library != null && isPersistent())
         library.update(this, key, oldValue, value);
      // firePropertyChange(key, oldValue, value);
   }

   public boolean isPersistent() {
      return persistent;
   }

   @Override
   public void remove(String key) {
      Object oldValue = get(key);
      super.remove(key);
      notifyChanges(key, oldValue, null);
   }

   public final void setMainAttribute(String mainAttribute) {
      this.mainAttribute = mainAttribute;
   }

   /**
    * @deprecated
    * @param attrs
    *           WARNING: orverwrites all data!
    */
   public final void setAllData(Map attrs) {
      setAllData(attrs, true);
   }

   /**
    * @deprecated
    * @param attrs
    * @param forceWrite
    *           if false, overwrite only data with value that differs from null.
    *           In other words, does not write any property that does not
    *           already exists or that exists but has a null value.
    */
   public final void setAllData(Map attrs, boolean forceWrite) {
      for (Iterator iter = attrs.keySet().iterator(); iter.hasNext();) {
         String key = (String) iter.next();
         Object newValue = attrs.get(key);
         writeSingleData(forceWrite, key, newValue);
      }
   }

   /**
    * 
    * @deprecated
    */
   private void writeSingleData(boolean forceWrite, String key, Object newValue) {
      Object oldValue = get(key);
      if (forceWrite) {
         writeData(key, oldValue, newValue);
         return;
      }
      if (oldValue != null) {
         writeData(key, oldValue, newValue);
      }
   }

   public String getMainAttribute() {
      return mainAttribute;
   }

   public String toString() {
      return "path: " + (String) get("Path");
   }

   /**
    * 
    * @deprecated
    */
   public void update() {
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      propertyChangeSupport.addPropertyChangeListener(listener);
   }

   protected void firePropertyChange(String key, Object oldValue, Object value) {
      propertyChangeSupport.firePropertyChange(key, oldValue, value);
   }

   public void update(String property) {
   }

   public void setPersistent(boolean b) {
      persistent = b;
   }

}