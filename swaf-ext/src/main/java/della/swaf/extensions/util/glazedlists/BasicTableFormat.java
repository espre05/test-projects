package della.swaf.extensions.util.glazedlists;

import ca.odell.glazedlists.gui.TableFormat;

/*
 * Created on 24-dic-2003
 * 
 */

/**
 * @author Daniele
 * 
 */

public class BasicTableFormat implements TableFormat {

   public String title;

   public BasicTableFormat(String title) {
      this.title = title;
   }

   public int getColumnCount() {
      return 1;
   }

   public String getColumnName(int column) {
      return title;
   }

   public Object getColumnValue(Object baseObject, int column) {
      // Item item = (Item) baseObject;
      // return item.getData(title);
      return baseObject;
   }
}
