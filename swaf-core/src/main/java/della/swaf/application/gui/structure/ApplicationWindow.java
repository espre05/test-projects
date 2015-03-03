/*
 * Created on 20-ott-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.application.gui.structure;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import della.swaf.application.gui.KeyEventListener;
import della.swaf.gui.renderer.DataRenderer;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface ApplicationWindow {
   public void setVisible(boolean b);

   public int showFileChooser(JFileChooser fc);

   public void addKeyEventListener(KeyEventListener listener);

   public boolean registerView(View view);

   public View getView(String outlinerID);

   public void registerPage(String id, Page view);

   public Page getPage(String pageId);

   public void setNewLookAndFeel(String laf);

   public void registerDataRenderer(String id, DataRenderer cellPanel);

   public DataRenderer getDataRenderer(String id);

   public Page getCurrentPage();

   public void showDialog(JDialog dialog);

   public void registerTopPanelWidget(ApplicationWidget appWidget);

   public int getX();

   public int getY();

   public void removeKeyEventListener(KeyEventListener listener);

   public void loadPage(Page page);

   public ApplicationWidget getTopPanelWidget(String string);

   public int getWidth();

   public int getHeight();

}