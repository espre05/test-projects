/*
 * Created on 23-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.gui.renderer;

import javax.swing.Icon;
import javax.swing.JComponent;

import della.swaf.application.datatypes.Item;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface DataRenderer {
   public JComponent getComponentFor(Item item);

   public void setSelected(boolean b);

   public void setIcon(String id, Icon icon);

   public void setIcon(String id, Icon icon, String toolTipText);

   public void removeIcon(String string);

   public int getRendererHeight();

}