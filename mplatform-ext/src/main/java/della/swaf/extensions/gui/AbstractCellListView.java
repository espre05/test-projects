package della.swaf.extensions.gui;

import javax.swing.table.TableCellRenderer;

import net.della.mplatform.gui.renderer.CellDecorator;


public abstract class AbstractCellListView extends AbstractListView {

   private TableCellRenderer cellRenderer;

   public abstract int getSelectedRow();

   public abstract void setRowHeight(int i);

   public abstract void setSelectedRowHeight(int newHeight);

   public abstract void addCellRendererDecorator(CellDecorator decorator);

   public void setCellRenderer(TableCellRenderer renderer) {
      this.cellRenderer = renderer;
   }

   protected TableCellRenderer getCellRenderer() {
      return cellRenderer;
   }

}
