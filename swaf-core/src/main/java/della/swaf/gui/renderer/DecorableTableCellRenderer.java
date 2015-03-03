/*
 * Created on 22-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.gui.renderer;

import javax.swing.table.TableCellRenderer;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface DecorableTableCellRenderer extends TableCellRenderer {
	public void addDecorator(CellDecorator decorator);

	public void removeDecorator(CellDecorator decorator);
}