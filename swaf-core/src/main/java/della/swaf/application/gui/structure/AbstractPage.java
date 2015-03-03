/*
 * Created on 11-giu-2005 
 * 
 */
package della.swaf.application.gui.structure;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

/**
 * @author Della
 * 
 */
public abstract class AbstractPage implements Page {
   private View focusedView;

   private final List managedViews;

   private Component focusedComponent;

   private JComponent startComponent;

   private final List topPanelComponentToShow;

   private String id;

   private String name;

   public AbstractPage() {
      managedViews = new ArrayList();
      topPanelComponentToShow = new ArrayList();
   }

   public String getID() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public View getFocusedView() {
      return focusedView;
   }

   public void addView(final View view, boolean floating) {
      managedViews.add(view);
      view.addPropertyChangeListener(new PropertyChangeListener() {

         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals(View.FOCUS_GAINED))
               setFocusedView(view);
         }
      });
   }

   public void addView(final View view) {
      addView(view, false);
   }

   public void removeView(View view) {
      managedViews.remove(view);
   }

   public void setFocusOnPreviousView() {
      int index = managedViews.indexOf(focusedView);
      if (index > 0) {
         focusedView = (View) managedViews.get(index - 1);
         focusedView.requestFocus();
      }
      if (index == 0) {
         giveFocusToStartComponent();
         focusedView = null;
      }

   }

   private void giveFocusToStartComponent() {
      if (startComponent == null)
         return;
      startComponent.requestFocus();
   }

   public void setFocusOnNextView() {
      if (focusedView == null) {
         focusedView = (View) managedViews.get(0);
         focusedView.requestFocus();
         return;
      }

      int index = managedViews.indexOf(focusedView);
      if (index >= 0 && index < managedViews.size() - 1) {
         focusedView = (View) managedViews.get(index + 1);
         focusedView.requestFocus();
      }
   }

   /**
    * 
    * @deprecated
    */
   public void setFocusOnPreviousView(View activeView) {
      int index = managedViews.indexOf(activeView);
      if (index > 0) {
         focusedView = (View) managedViews.get(index - 1);
         focusedView.requestFocus();
      }

   }

   /**
    * 
    * @deprecated
    */
   public void setFocusOnNextView(View activeView) {
      int index = managedViews.indexOf(activeView);
      if (index >= 0 && index < managedViews.size() - 1) {
         focusedView = (View) managedViews.get(index + 1);
         focusedView.requestFocus();
      }
   }

   public void setFocusOnPreviousComponent() {
      int index = managedViews.indexOf(getFocusedView());
      if (index > 0) {
         // focusedComponent.
         focusedComponent = (Component) managedViews.get(index - 1);
         focusedComponent.requestFocus();
      }
   }

   public void setFocusedView(View focusedView) {
      this.focusedView = focusedView;
   }

   public JComponent getStartComponent() {
      return startComponent;
   }

   public void setStartComponent(JComponent startComponent) {
      this.startComponent = startComponent;
   }

   public void useTopPanelComponent(String id) {
      topPanelComponentToShow.add(id);
   }

   public List getTopPanelComponentToShow() {
      return new ArrayList(topPanelComponentToShow);
   }

}
