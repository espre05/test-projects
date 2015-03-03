/*
 * Created on 27-mag-2005 
 * 
 */
package della.swaf.extensions.gui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import net.della.mplatform.application.datatypes.MapItem;


public final class DefaultTableCellRenderer implements TableCellRenderer {
    private final DefaultDataRenderer cellRenderer;

    public DefaultTableCellRenderer(DefaultDataRenderer cellRenderer) {
        super();
        this.cellRenderer = cellRenderer;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        MapItem item = (MapItem) value;
        cellRenderer.setSelected(isSelected);
        return cellRenderer.getComponentFor(item);
    }
}