/*
 * Created on 22-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.extensions.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.della.mplatform.application.datatypes.Item;
import net.della.mplatform.gui.renderer.CellDecorator;
import net.della.mplatform.gui.renderer.DataRenderer;
import net.della.mplatform.gui.renderer.DecorableTableCellRenderer;


/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SwitcherTableCellRenderer implements DecorableTableCellRenderer, ListSelectionListener {

   private List decorators;

   private DataRenderer extendedCellRenderer;

   private DataRenderer simpleCellRenderer;

   private Timer timer;

   private boolean useExtendedCellPanel;

   public SwitcherTableCellRenderer(final AbstractCellListView view, DataRenderer simpleCellPanel,
         DataRenderer extendedCellPanel) {

      this(simpleCellPanel);
      this.extendedCellRenderer = extendedCellPanel;

      timer = new Timer(10, new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (!useExtendedCellPanel || view.getSelection().size() > 1)
               return;
            view.setSelectedRowHeight(extendedCellRenderer.getRendererHeight());
            view.centerOnSelection();
         }
      });
      timer.setRepeats(false);

      view.setCellRenderer(this);
      view.addListSelectionListener(this);
      view.setRowHeight(30);

   }

   public SwitcherTableCellRenderer(DataRenderer cellRenderer) {
      decorators = new LinkedList();
      this.simpleCellRenderer = cellRenderer;
   }

   public void valueChanged(ListSelectionEvent e) {
      if (e.getValueIsAdjusting())
         return;
      resetTimer();
   }

   private void resetTimer() {
      if (timer == null)
         return;
      if (timer.isRunning())
         timer.stop();
      timer.start();

   }

   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
         boolean hasFocus, int row, int column) {

      Item item = (Item) value;

      DataRenderer dataRenderer = getCellRenderer(isSelected, row, table);
      for (Iterator it = decorators.iterator(); it.hasNext();) {
         CellDecorator decorator = (CellDecorator) it.next();
         decorator.decore(dataRenderer, row);
      }
      JComponent c = dataRenderer.getComponentFor(item);
      return c;

   }

   private DataRenderer getCellRenderer(boolean isSelected, int row, JTable table) {
      DataRenderer renderer;
      if (isSelected) {
         renderer = getSelectedRenderer(row, table);
         // return renderer;
      } else {
         renderer = simpleCellRenderer;
         renderer.setSelected(false);
         if (extendedCellRenderer != null
               && table.getRowHeight(row) == extendedCellRenderer.getRendererHeight())
            table.setRowHeight(row, renderer.getRendererHeight());
      }

      return renderer;
   }

   private DataRenderer getSelectedRenderer(int row, JTable table) {
      if (extendedCellRenderer != null && useExtendedCellPanel)
         if (table.getRowHeight(row) == extendedCellRenderer.getRendererHeight()) {
            extendedCellRenderer.setSelected(true);
            return extendedCellRenderer;
         }
      simpleCellRenderer.setSelected(true);
      return simpleCellRenderer;
   }

   public void addDecorator(CellDecorator decorator) {
      decorators.add(decorator);
   }

   public void removeDecorator(CellDecorator decorator) {
      decorators.remove(decorator);
   }

   public void setUseExtendedCellPanel(boolean useExtendedCellPanel) {
      this.useExtendedCellPanel = useExtendedCellPanel;
   }

   public boolean isUseExtendedCellPanel() {
      return useExtendedCellPanel;
   }

}