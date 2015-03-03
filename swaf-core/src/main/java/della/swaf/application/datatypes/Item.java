package della.swaf.application.datatypes;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public interface Item {

   String TYPE = "type";

   public void put(String key, Object value);

   public Object get(String key);

   public void remove(String key);

   public int countChilds();

   public Item getChild(int index);

   public Iterator childIterator();

   public boolean hasChild(Item child);

   public List listChilds();

   public String getMainAttribute();

   public String getString(String key);

   public Boolean getBoolean(String propertyName);

   public Integer getInteger(String propertyName);

   public Long getLong(String propertyName);

   public Calendar getDate(String propertyName);

   public Timestamp getTimestamp(String propertyName);

   public InputStream getStream(String key);

   public void save();

   public String getType();

   public void setType(String type);

   public String getId();

   public void addChild(Item item);

   public Object getMainAttributeValue();

}