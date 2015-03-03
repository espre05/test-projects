package della.swaf.application.datatypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoChildMapItem extends MapItem {

   private final ArrayList childs;
   private String id;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public NoChildMapItem() {
      childs = new ArrayList();
   }

   public Iterator childIterator() {
      return childs.iterator();
   }

   public int countChilds() {
      return 0;
   }

   public Item getChild(int index) {
      return null;
   }

   public boolean hasChild(Item child) {
      return false;
   }

   public List listChilds() {
      return childs;
   }

   public void addChild(Item item) {
      // intentionally left blank
   }

   @Override
   public String getMainAttributeValue() {
      return getString(getMainAttribute());
   }

}
