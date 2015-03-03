package della.swaf.application.datatypes;

import java.util.*;

import com.sourcesense.stuff.util.MathUtil;
import com.sourcesense.stuff.util.NullComparator;
import com.sourcesense.stuff.util.StringFormatter;

public class ItemSet extends ObservableItem {

   protected final List itemsList;
   protected Comparator c;

   public ItemSet(Comparator c) {
      itemsList = new ArrayList();
      this.c = c;
   }

   public ItemSet() {
      this(new NullComparator());
   }

   public boolean isPersistent() {
      return false;
   }

   public int compare(ItemSet songSet2, String compareField) {
      String value1String = getString(compareField);
      String value2String = songSet2.getString(compareField);
      if (MathUtil.isInteger(value1String) && MathUtil.isInteger(value2String))
         return MathUtil.compareIntValue(value1String, value2String);
      if (MathUtil.isLong(value1String) && MathUtil.isLong(value2String))
         return MathUtil.compareLongValue(value1String, value2String);
      return MathUtil.compareStringValue(value1String, value2String);
   }

   public List listChilds() {
      return itemsList;
   }

   public void addItem(Item item) {
      itemsList.add(item);
      Collections.sort(itemsList, c);
   }

   public int size() {
      return itemsList.size();
   }

   public void formatData() {
      String mainPropertyValue = getString(getMainAttribute());
      put(getMainAttribute(), StringFormatter.format(mainPropertyValue, StringFormatter.ALL_CAPITAL));
   }

   public String getId() {
      return null;
   }

   public void addAll(List items) {
      for (Iterator it = items.iterator(); it.hasNext();) {
         addItem((Item) it.next());
      }
      sort();
   }

   protected void sort() {
      Collections.sort(itemsList, c);
   }

   public int countChilds() {
      return itemsList.size();
   }

   public Item getChild(int index) {
      return (Item) itemsList.get(index);
   }

   public void update() {
      Iterator it = listChilds().iterator();
      while (it.hasNext()) {
         ObservableItem item = (ObservableItem) it.next();
         Map data = getData();
         item.setAllData(data, false);
         item.update();
      }
   }

   public void update(String property) {
      Iterator it = listChilds().iterator();
      Map data = getData();
      while (it.hasNext()) {
         ObservableItem item = (ObservableItem) it.next();
         item.put(property, data.get(property));
         item.update(property);
      }
   }

   public String toString() {
      return getString(getMainAttribute());
   }

   public Iterator childIterator() {
      return itemsList.iterator();
   }

   public boolean hasChild(Item child) {
      return itemsList.contains(child);
   }

   public void setData(String key, Object value, boolean liveUpdate) {
      put(key, value);
      if (liveUpdate)
         update(key);
   }

   public String getType() {
      return null;
   }

}