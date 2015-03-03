/*
 * Created on 10-feb-2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package della.swaf.application.application;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.*;

import della.swaf.application.gui.KeyEventListener;
import della.swaf.application.gui.structure.*;
import della.swaf.gui.renderer.DataRenderer;
import della.swaf.gui.util.PanelMediator;
import della.swaf.gui.util.SwingPersistence;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DefaultApplicationWindow implements ApplicationWindow {

   private JFrame frame;

   private final List keyListeners;

   final Map views;

   private final Map pages;

   private final Map dataWidgets;

   private AbstractPage currentPage;

   private final Map topPanelComponents;

   private JPanel applicationRelatedComponentPanel;

   private PanelMediator perspectivePanelMediator;

   private PropertyChangeListener propertyChangeListener;

   private JPanel topPanel;
   private JPanel bottomPanel;

   public DefaultApplicationWindow() {
      keyListeners = new LinkedList();
      views = new HashMap();
      pages = new HashMap();
      dataWidgets = new HashMap();
      topPanelComponents = new HashMap();
   }

   public void setVisible(boolean b) {

      frame.invalidate();
      // frame.pack();
      frame.setLocationRelativeTo(null);

      frame.setVisible(b);

      KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
      focusManager.addKeyEventDispatcher(new KeyEventDispatcher() {

         public boolean dispatchKeyEvent(KeyEvent e) {

            dispatchToListeners(e);

            return false;
         }
      });

   }

   public void loadPage(Page newPage) {

      if (currentPage != null)
         frame.remove(currentPage.getComponent());

      currentPage = (AbstractPage) newPage;
      JComponent component = currentPage.getComponent();
      frame.getContentPane().add(component, BorderLayout.CENTER);
      // currentPerspective.revalidate();
      // frame.repaint();
      currentPage.revalidateAndRepaint();
      // frame.getContentPane().repaint();

      List list = currentPage.getTopPanelComponentToShow();
      perspectivePanelMediator.removeAll();
      for (Iterator it = list.iterator(); it.hasNext();) {
         String id = (String) it.next();
         ApplicationWidget appWidget = (ApplicationWidget) topPanelComponents.get(id);
         perspectivePanelMediator.addComponent(appWidget.getComponent(), appWidget.getCommand());
      }

   }

   void dispatchToListeners(KeyEvent e) {
      for (Iterator it = keyListeners.iterator(); it.hasNext();) {
         KeyEventListener listener = (KeyEventListener) it.next();
         listener.keyEventHappened(e);
      }
   }

   public int showFileChooser(JFileChooser fc) {
      return fc.showDialog(frame, null);
   }

   public void addKeyEventListener(KeyEventListener listener) {
      keyListeners.add(listener);
   }

   public boolean registerView(View view) {
      String id = view.getId();
      if (views.containsKey(id))
         return false;
      view.addPropertyChangeListener(propertyChangeListener);
      views.put(id, view);
      return true;
   }

   public View getView(String outlinerID) {
      return (View) views.get(outlinerID);
   }

   public void registerPage(String id, Page newPage) {
      pages.put(id, newPage);
   }

   public Page getPage(String pageId) {
      return (Page) pages.get(pageId);
   }

   public void showDialog(JDialog dialog) {
      // SwingUtil.locateOnScreenCenter(dialog);
      dialog.pack();
      dialog.setVisible(true);
      boolean focusResponse = dialog.requestFocusInWindow();
      Logger.getLogger(this.getClass().getName()).info("dialog focus response: " + focusResponse);
   }

   public void setNewLookAndFeel(String laf) {
      try {
         UIManager.setLookAndFeel(laf);
      } catch (Exception e) {
         e.printStackTrace();
      }
      SwingUtilities.updateComponentTreeUI(frame);
   }

   public void setNewLookAndFeel(LookAndFeel laf) {
      try {
         UIManager.setLookAndFeel(laf);
      } catch (Exception e) {
         e.printStackTrace();
      }
      SwingUtilities.updateComponentTreeUI(frame);
   }

   public void registerDataRenderer(String id, DataRenderer cellPanel) {
      dataWidgets.put(id, cellPanel);
   }

   public DataRenderer getDataRenderer(String widgetID) {
      return (DataRenderer) dataWidgets.get(widgetID);
   }

   public Page getCurrentPage() {
      return currentPage;
   }

   public void addWindowListener(WindowListener listener) {
      frame.addWindowListener(listener);
   }

   public void addToBottomPanel(JComponent c) {
      bottomPanel.add(c);
   }

   public String[] getPagesIdsArray() {
      String[] idsArray = new String[pages.size()];
      int i = 0;
      for (Iterator it = pages.keySet().iterator(); it.hasNext(); i++) {
         idsArray[i] = (String) it.next();
      }
      return idsArray;

   }

   public void addTopPanelPermanentComponent(JComponent component) {
      applicationRelatedComponentPanel.add(component);
   }

   public void registerTopPanelWidget(ApplicationWidget appWidget) {
      topPanelComponents.put(appWidget.getId(), appWidget);
   }

   public void registerPage(Page basicPage) {
      pages.put(basicPage.getID(), basicPage);
   }

   public void setTitle(String title) {
      frame.setTitle(title);
   }

   public void addViews(List<View> allViews) {
      for (Iterator it = allViews.iterator(); it.hasNext();) {
         View view = (View) it.next();
         registerView(view);
      }
   }

   public int getX() {
      return frame.getX();
   }

   public int getY() {
      return frame.getY();
   }

   public void removeKeyEventListener(KeyEventListener listener) {
      keyListeners.remove(listener);
   }

   protected void setApplicationRelatedComponentPanel(JPanel applicationRelatedComponentPanel) {
      this.applicationRelatedComponentPanel = applicationRelatedComponentPanel;
      topPanel.add(applicationRelatedComponentPanel, FlowLayout.CENTER);
   }

   protected void setPerspectivePanelMediator(PanelMediator perspectivePanelMediator) {
      this.perspectivePanelMediator = perspectivePanelMediator;
      topPanel.add(perspectivePanelMediator.getComponent(), FlowLayout.LEFT);
   }

   public void enablePersistence(final File saveFile) {
      try {
         SwingPersistence.loadFrameBounds(frame, new FileInputStream(saveFile));
      } catch (FileNotFoundException e1) {
      } catch (Exception e1) {
         Logger.getLogger(this.getClass().getName()).info("Unable to load data from file: " + saveFile);
         Logger.getLogger(this.getClass().getName()).info("It is corrupted, I will delete it");
         saveFile.delete();
      }
      frame.addWindowListener(new WindowAdapter() {

         public void windowClosing(WindowEvent e) {
            SwingPersistence.saveFrameBounds(frame, saveFile);
         }
      });
   }

   public void addCloseHandler(WindowAdapter adapter) {
      frame.addWindowListener(adapter);
   }

   public void revalidateAndRepaint() {
      frame.repaint();
   }

   public boolean isVisible() {
      return frame.isVisible();
   }

   public ApplicationWidget getTopPanelWidget(String widgetId) {
      return (ApplicationWidget) topPanelComponents.get(widgetId);
   }

   public int getWidth() {
      return frame.getWidth();
   }

   public int getHeight() {
      return frame.getHeight();
   }

   void useTopPanel() {
      topPanel = new JPanel();
      getContentPane().add(topPanel, BorderLayout.NORTH);
   }

   void useBottonPanel() {
      bottomPanel = new JPanel();
      getContentPane().add(bottomPanel, BorderLayout.SOUTH);
   }

   protected Container getContentPane() {
      return frame.getContentPane();
   }

   public void setFrame(JFrame frame) {
      this.frame = frame;
   }

   public void setContentPane(JComponent contentPane) {
      frame.setContentPane(contentPane);
   }

}