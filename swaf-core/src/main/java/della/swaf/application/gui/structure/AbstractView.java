/*
 * Created on 29-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.application.gui.structure;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import della.swaf.gui.components.InternalFrame;
import della.swaf.gui.components.WidgetFactory;
import della.swaf.gui.util.PopupListener;
import della.swaf.gui.util.ToolbarMediator;
import della.swaf.util.Command;
import della.swaf.util.NullCommand;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstractView implements View {

   private String name;

   private InternalFrame iFrame;

   private final PropertyChangeSupport propertyChangeSupport;

   private ToolbarMediator toolbarMediator;

   private String id;

   protected final Map menus;

   private boolean enabled;

   private JComponent mainComponent;

   protected AbstractView() {
      menus = new HashMap();
      propertyChangeSupport = new PropertyChangeSupport(this);
      addPopupMenu(View.CONTEXT_MENU, new JPopupMenu());
   }

   protected void init() {
      iFrame.setToolBar(WidgetFactory.buildToolBar());
      toolbarMediator = new ToolbarMediator(iFrame.getToolBar());
      iFrame.setSelected(false);
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
      setName(id);
   }

   public void setName(String name) {
      this.name = name;
      iFrame.setTitle(name);
   }

   public final String getName() {
      return name;
   }

   public JComponent getViewComponent() {
      return (JComponent) iFrame;
   }

   public void focusGained(FocusEvent e) {
      iFrame.setSelected(true);
      firePropertyChange(View.FOCUS_GAINED, false, true);
   }

   public void focusLost(FocusEvent e) {
      iFrame.setSelected(false);
      // List subComponents = SwingUtil.listSubComponents(iFrame, true);
      // for (Iterator it = subComponents.iterator(); it.hasNext();) {
      // JComponent c = (JComponent) it.next();
      // if (c.hasFocus())
      // return;
      // }
      // firePropertyChange(View.FOCUS_LOST, true, false);
   }

   public void enablePopupForMain() {
      JComponent c = getMainComponent();
      c.addFocusListener(this);
      JPopupMenu contextPopup = (JPopupMenu) menus.get(View.CONTEXT_MENU);
      c.addMouseListener(new PopupListener(contextPopup));
   }

   public abstract void removeSelection();

   public final void addToolbarButton(AbstractButton button) {
      iFrame.getToolBar().add(button);
   }

   public void revalidateAndRepaint() {
      iFrame.revalidateAndRepaint();
   }

   public void setContent(JComponent mainContainer) {
      iFrame.setContent(mainContainer);
      mainContainer.addMouseListener(new MouseAdapter() {

         public void mouseReleased(MouseEvent e) {
            System.out.println("click");
         }

      });
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      propertyChangeSupport.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      propertyChangeSupport.removePropertyChangeListener(listener);
   }

   public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
      propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
   }

   protected void firePropertyChange(String propertyName, boolean b, boolean c) {
      propertyChangeSupport.firePropertyChange(propertyName, b, c);
   }

   /**
    * @return
    */
   protected JComponent getContent() {
      return (JComponent) iFrame.getContent();
   }

   public void addButton(AbstractButton startButton, Command command) {
      toolbarMediator.addButton(startButton, command);
   }

   public void addButton(AbstractButton button) {
      addButton(button, new NullCommand());
   }

   public void requestFocus() {
      iFrame.getContent().requestFocus();
   }

   public void addMouseListener(MouseListener listener) {
      iFrame.getContent().addMouseListener(listener);
   }

   protected void firePropertyChange(PropertyChangeEvent event) {
      propertyChangeSupport.firePropertyChange(event);
   }

   public void addToPopup(String popupId, JComponent c) {
      JPopupMenu menu = (JPopupMenu) menus.get(popupId);
      menu.add(c);
   }

   public void addPopupMenu(String id, JPopupMenu menu) {
      menus.put(id, menu);
   }

   public void addToolbarComponent(JComponent c) {
      iFrame.getToolBar().add(c);
   }

   public void setEnabled(boolean enable) {
      this.enabled = enable;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public void addMenuItem(Action action, String label, int key) {
      InputMap inputMap = getInputMap();
      ActionMap actionMap = getActionMap();
      action.putValue(Action.NAME, label);
      inputMap.put(KeyStroke.getKeyStroke(key, 0), action.getValue(Action.NAME));
      actionMap.put(action.getValue(Action.NAME), action);
      addToPopup(View.CONTEXT_MENU, new JMenuItem(action));
   }

   public int getWidth() {
      return getViewComponent().getWidth();
   }

   protected void setViewComponent(InternalFrame dockableInternalFrame) {
      iFrame = dockableInternalFrame;
   }

   public void addButton(Action action) {
      iFrame.getToolBar().add(WidgetFactory.createButton(action));
   }

   protected void setMainComponent(JComponent component) {
      this.mainComponent = component;
   }

   protected JComponent getMainComponent() {
      return mainComponent;
   }

}
