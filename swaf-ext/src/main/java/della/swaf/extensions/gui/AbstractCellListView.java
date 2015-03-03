package della.swaf.extensions.gui;


import javax.swing.table.TableCellRenderer;

import della.swaf.gui.renderer.CellDecorator;

public abstract class AbstractCellListView extends AbstractListView {

   public abstract int getSelectedRow();

   public abstract void setRowHeight(int i);

   public abstract void setSelectedRowHeight(int newHeight);

   public abstract void setCellRenderer(TableCellRenderer renderer);

   public abstract void addCellRendererDecorator(CellDecorator decorator);

}
