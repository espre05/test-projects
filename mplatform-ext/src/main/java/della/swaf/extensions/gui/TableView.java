/*
 * Created on 17-gen-2004
 *
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package della.swaf.extensions.gui;

import java.awt.Rectangle;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.*;
import java.util.Comparator;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import net.della.mplatform.application.gui.structure.ViewChangeListener;
import net.della.mplatform.gui.renderer.CellDecorator;
import net.della.mplatform.gui.renderer.DecorableTableCellRenderer;
import net.della.stuff.generic.util.NullComparator;
import net.della.stuff.gui.components.WidgetFactory;
import net.della.stuff.gui.dnd.DropHandler;
import net.della.stuff.gui.swing.SwingPersistence;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import della.swaf.extensions.util.glazedlists.BasicTableFormat;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class TableView extends AbstractCellListView {

   private JTable table;
   private TableComparatorChooser sortChooser;

   public TableView() {
      dropHandler = DropHandler.NULL;
      table = WidgetFactory.createTable();
      setMainComponent(table);
   }

   public void loadFromDisk(String filename) {
      File saveFile = new File(filename);
      InputStream inputStream = null;
      try {
         inputStream = new FileInputStream(saveFile);
      } catch (FileNotFoundException e) {
      }

      try {
         SwingPersistence.loadColumnModel(table, inputStream);
      } catch (Exception e) {
         Logger.getLogger(this.getClass().getName()).info("Unable to load data from file: " + filename);
         Logger.getLogger(this.getClass().getName()).info("It is corrupted, I will delete it");
         saveFile.delete();
      }

   }

   public void setComparator(int columnIndex, Comparator comparator) {
      sortChooser.getComparatorsForColumn(columnIndex).clear();
      sortChooser.getComparatorsForColumn(columnIndex).add(comparator);
   }

   public boolean isColumnReverse(int column) {
      return sortChooser.isColumnReverse(column);
   }

   public void addMouseListener(MouseListener listener) {
      table.addMouseListener(listener);
   }

   public void removeMouseListener(MouseListener listener) {
      table.removeMouseListener(listener);
   }

   /**
    * @deprecated: need to wrap mapping
    */
   public ActionMap getActionMap() {
      return table.getActionMap();
   }

   /**
    * @deprecated: need to wrap mapping
    */
   public InputMap getInputMap() {
      return table.getInputMap();
   }

   public void addCellRendererDecorator(CellDecorator decorator) {
      for (int i = 0; i < table.getColumnCount(); i++) {
         DecorableTableCellRenderer renderer = (DecorableTableCellRenderer) table.getCellRenderer(0, i);
         renderer.addDecorator(decorator);
      }
   }

   void setTableModel(TableModel tableModel) {

      table.setModel(tableModel);

      ListSelectionModel listSelectionModel = initSelectionList();
      table.setSelectionModel(listSelectionModel);

      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setBorder(BorderFactory.createEmptyBorder());
      setContent(scrollPane);
      revalidateAndRepaint();

   }

   public void addChangeListener(final ViewChangeListener listener) {
      table.getModel().addTableModelListener(new TableModelListener() {
         public void tableChanged(TableModelEvent e) {
            listener.viewContentChanged();
         }
      });
   }

   public void requestFocus() {
      int selectedRowIndex = table.getSelectedRow();
      // if (selectedRowIndex == -1)
      // selectedRowIndex = 0;
      table.requestFocus();
      setSelectionInterval(selectedRowIndex, selectedRowIndex);
      centerOnSelection();

   }

   public void addKeyListener(KeyListener listener) {
      table.addKeyListener(listener);
   }

   public void removeCellRendererDecorator(CellDecorator decorator) {
      for (int i = 0; i < table.getColumnCount(); i++) {
         TableCellRenderer cellRenderer = table.getCellRenderer(0, i);
         DecorableTableCellRenderer renderer = (DecorableTableCellRenderer) cellRenderer;
         renderer.removeDecorator(decorator);
      }
   }

   public void repaint() {
      table.repaint();
   }

   public String toString() {
      return getName();
   }

   public int getSelectedRow() {
      return table.getSelectedRow();
   }

   protected int rowAtPoint(MouseEvent e) {
      return table.rowAtPoint(e.getPoint());
   }

   public int rowAtPoint(DropTargetDropEvent evt) {
      return table.rowAtPoint(evt.getLocation());
   }

   public String getComparatorName() {
      if (sortChooser != null)
         return sortChooser.getComparatorsForColumn(0).get(0).toString();
      return super.getComparatorName();
   }

   public void enableSorter() {
      sortChooser = new TableComparatorChooser(table, (SortedList) getSourceList(), false);
   }

   public void enableTypeAhed() {
      table.addKeyListener(new TypeAhead(this, getSelectionModel(), getSourceList()));
   }

   protected ListSelectionModel getSelectionModel() {
      return table.getSelectionModel();
   }

   public void changeSelectedRowHeight(int newHeight) {
      int selectedRow = table.getSelectedRow();
      if (selectedRow == -1)
         return;
      table.setRowHeight(selectedRow, newHeight);

   }

   public void setRowHeight(int i) {
      table.setRowHeight(i);

   }

   public void centerOnSelection() {
      if (getSelectedRow() == -1)
         return;
      int decentramento = 0;
      Rectangle cellRect = table.getCellRect(getSelectedRow() + decentramento, 0, true);
      table.scrollRectToVisible(cellRect);
   }

   public void setTableHeader(JTableHeader tableHeader) {
      table.setTableHeader(tableHeader);
   }

   public void setSelectedRowHeight(int newHeight) {
      int selectedRow = table.getSelectedRow();
      if (selectedRow == -1)
         return;
      table.setRowHeight(selectedRow, newHeight);
   }

   public void setCellRenderer(TableCellRenderer renderer) {
      super.setCellRenderer(renderer);
      table.getColumnModel().getColumn(0).setCellRenderer(renderer);
   }

   public void saveToDisk(String filename) {
      File saveFile = new File(filename);
      SwingPersistence.saveColumnModel(table, saveFile);
   }

   public void focusGained(FocusEvent e) {
      super.focusGained(e);
      if (e.getSource().equals(table))
         return;
      setSelectionInterval(0, 0);
      centerOnSelection();
   }

   @Override
   public void scroll(int oldIndex, int newIndex) {
      if (getSelectedRow() == -1)
         return;
      int decentramento = 3;
      int signus = 1;
      if (oldIndex > newIndex)
         signus = -1;
      Rectangle cellRect = table.getCellRect(getSelectedRow() + decentramento * signus, 0, true);
      table.scrollRectToVisible(cellRect);
   }

   /**
    * @param field
    */
   public void setCellEditor(JTextField field) {
      DefaultCellEditor defaultCellEditor = new DefaultCellEditor(field);
      defaultCellEditor.setClickCountToStart(5);
      table.setCellEditor(defaultCellEditor);
   }

   /**
    * @param i
    * @param renderer
    */
   public void setCellRenderer(int i, TableCellRenderer renderer) {
      table.getColumnModel().getColumn(i).setCellRenderer(renderer);
   }

   public Rectangle getSelectedRectangle() {
      return table.getCellRect(getSelectedRow(), 0, true);
   }

   public void build(EventList list) {
      setDataStructure(new ComplexList(list, new NullComparator()));
   }

   public void pack(TableCellRenderer cellRenderer) {
      setTableModel(getDataStructure().createTableModel(new BasicTableFormat(getId())));
      enableTypeAhed();

      setCellRenderer(cellRenderer);
   }

   public void pack() {
      pack(getCellRenderer());
   }

}