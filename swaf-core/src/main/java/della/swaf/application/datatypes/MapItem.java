package della.swaf.application.datatypes;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

import com.sourcesense.stuff.util.ListParser;

public abstract class MapItem implements Item {

   private final Map props;

   public MapItem() {
      props = new HashMap();
   }

   public void put(String key, Object value) {
      Object oldValue = get(key);
      writeData(key, oldValue, value);
   }

   public Object get(String key) {
      return props.get(key);
   }

   public void remove(String key) {
      props.remove(key);
   }

   void writeData(String key, Object oldValue, Object newValue) {
      if (newValue instanceof Collection)
         writeCollection(key, (Collection) newValue);
      else
         writeObject(key, newValue);
   }

   private void writeObject(String key, Object newValue) {
      props.put(key, newValue);
   }

   private void writeCollection(String key, Collection newValue) {
      Collection tags = (Collection) get(key);
      if (tags == null) {
         tags = ListParser.newCollection(newValue.getClass());
         props.put(key, tags);
      }
      tags.addAll(newValue);
   }

   @Override
   public String toString() {
      StringBuffer sb = new StringBuffer();
      Iterator it = props.keySet().iterator();
      sb.append("Item [");
      while (it.hasNext()) {
         String key = (String) it.next();
         sb.append(key);
         sb.append(": ");
         sb.append(props.get(key));
         sb.append(", ");
      }
      sb.replace(sb.length() - 2, sb.length(), "");
      sb.append("]");
      return sb.toString();
   }

   public void setType(String type) {
   }

   /**
    * This is a convenience method for Object getData(String)
    */
   public Property getProperty(String key) {
      return (Property) props.get(key);
   }

   public Properties getDataAsProperties() {
      Properties copy = new Properties();
      copy.putAll(props);
      return copy;
   }

   protected Map getData() {
      return props;
   }

   public boolean isEmpty() {
      return props.isEmpty();
   }

   public Boolean getBoolean(String propertyName) {
      return (Boolean) props.get(propertyName);
   }

   public Integer getInteger(String propertyName) {
      // TODO Auto-generated method stub
      return null;
   }

   public Long getLong(String propertyName) {
      // TODO Auto-generated method stub
      return null;
   }

   public String getMainAttribute() {
      // TODO Auto-generated method stub
      return null;
   }

   public InputStream getStream(String key) {
      // TODO Auto-generated method stub
      return null;
   }

   public String getType() {
      // TODO Auto-generated method stub
      return null;
   }

   public void save() {
      // TODO Auto-generated method stub

   }

   /**
    * This is a convenience method for Object getData(String)
    */
   public String getString(String key) {
      if (props.containsKey(key))
         return (String) props.get(key);
      return "";
   }

   public Calendar getDate(String key) {
      if (props.containsKey(key))
         return (Calendar) props.get(key);
      Calendar calendar = Calendar.getInstance();
      calendar.set(0, 0, 0);
      return calendar;
   }

   public Timestamp getTimestamp(String key) {
      if (props.containsKey(key)) {
         return (Timestamp) props.get(key);
      }
      return new Timestamp(0);
   }
}
