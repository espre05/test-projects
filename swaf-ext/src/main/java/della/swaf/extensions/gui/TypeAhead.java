/*
 * Created on 29-lug-2004
 */

package della.swaf.extensions.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ListSelectionModel;

import della.swaf.application.datatypes.Item;

final class TypeAhead extends KeyAdapter {

   private List list;

   private final ListSelectionModel selectionModel;

   private AbstractCellListView view;

   public TypeAhead(AbstractCellListView view, ListSelectionModel selectionModel, List list) {
      this.list = list;
      this.selectionModel = selectionModel;
      this.view = view;

   }

   public void keyTyped(KeyEvent e) {

      char searchedChar = e.getKeyChar();
      String searchedString = searchedChar + "";
      searchedString = searchedString.toLowerCase();

      int actualIndex = selectionModel.getMinSelectionIndex();
      int rowIndex = findRow(searchedString, actualIndex + 1);
      if (rowIndex == -1)
         rowIndex = findRow(searchedString, 0);
      if (rowIndex == -1)
         return;
      view.setSelectionInterval(rowIndex, rowIndex);
      view.scroll(actualIndex, rowIndex);

   }

   private int findRow(String searchedString, int startIndex) {
      int index = startIndex;

      List subList = list.subList(startIndex, list.size());
      for (Iterator it = subList.iterator(); it.hasNext();) {
         Item item = (Item) it.next();
         String mainAttributeValue = item.getString(item.getMainAttribute());
         if (mainAttributeValue.length() > 0) {
            String thisString = mainAttributeValue.substring(0, 1);
            thisString = thisString.toLowerCase();

            if (thisString.startsWith(searchedString)) {
               return index;
            }
         }
         index++;
      }
      return -1;
   }
}