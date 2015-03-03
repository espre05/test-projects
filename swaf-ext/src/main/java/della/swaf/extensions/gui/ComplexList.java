package della.swaf.extensions.gui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.EventSelectionModel;
import ca.odell.glazedlists.swing.EventTableModel;
import della.swaf.application.datatypes.Item;
import della.swaf.extensions.util.glazedlists.*;

public class ComplexList {

   private SortedList sortedList;
   private CustomFilteredList customFilteredList;
   private final FilterList filterList;
   private TextMatcherEditor matcherEditor;
   private EventSelectionModel eventSelectionModel;
   private EventList selectionList;

   public ComplexList(FilterList list, Comparator comparator) {
      this.filterList = list;
      customFilteredList = new SelectionCustomFilteredList(list);
      sortedList = new SortedList(customFilteredList, comparator);
   }

   public TableModel createTableModel(TableFormat tableFormat) {
      EventTableModel tableModel = new EventTableModel(sortedList, tableFormat);
      return tableModel;
   }

   public ListSelectionModel createSelectionModel() {
      eventSelectionModel = new EventSelectionModel(sortedList);
      return eventSelectionModel;
   }

   public void clear() {
      sortedList.clear();
   }

   public void addAll(List childs) {
      sortedList.addAll(childs);
   }

   public Item get(int index) {
      return (Item) sortedList.get(index);
   }

   public int size() {
      return sortedList.size();
   }

   public String getComparatorName() {
      return sortedList.getComparator().toString();
   }

   public void changeComparator(Comparator comparator) {
      sortedList.setComparator(comparator);
   }

   public List actual() {
      return new ArrayList(sortedList);
   }

   protected List getHigherList() {
      return sortedList;
   }

   public void addCustomFilter(CustomFilter filter, boolean forceRefresh) {
      customFilteredList.addCustomFilter(filter, forceRefresh);
   }

   public void addCustomFilter(CustomFilter filter) {
      customFilteredList.addCustomFilter(filter);
   }

   public boolean hasFilters() {
      return customFilteredList.hasFilters();
   }

   public void removeCustomFilter(CustomFilter filter, boolean forceRefresh) {
      customFilteredList.removeCustomFilter(filter, forceRefresh);
   }

   public void removeCustomFilter(CustomFilter filter) {
      customFilteredList.removeCustomFilter(filter);
   }

   public void addListEventListener(ListEventListener listEventListener) {
      customFilteredList.addListEventListener(listEventListener);
   }

   public void addTextFilter(String propTags) {
      PropertyTextFilterator filterator = (PropertyTextFilterator) matcherEditor.getFilterator();
      filterator.addPropertyToFilter(propTags);
   }

   public void setTextMatcher(TextMatcherEditor matcherEditor) {
      this.matcherEditor = matcherEditor;
   }

   public void addSelectionEventListener(ListEventListener listEventListener) {
      selectionList = eventSelectionModel.getSelected();
      selectionList.addListEventListener(listEventListener);
   }

   public List<? extends Item> getSelection() {
      return selectionList;
   }

   /**
    * 
    * @return first item selected
    */
   public Item getSelected() {
      return selectionList.size() == 0 ? null : (Item) selectionList.get(0);
   }

}
