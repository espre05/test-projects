/*
 * Created on 19-feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.background.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.SwingWorker;

import della.swaf.background.BackgroundTask;
import della.swaf.background.TaskAdapter;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class SwingBackgroundTask extends SwingWorker<Object, Object> implements BackgroundTask {

   private final class ChangeForwarder implements PropertyChangeListener {
      public void propertyChange(PropertyChangeEvent evt) {
         if ("state".equals(evt.getPropertyName())) {
            Object newValue = evt.getNewValue();
            if (SwingWorker.StateValue.STARTED.equals(newValue)) {
               for (Iterator iter = listeners.iterator(); iter.hasNext();) {
                  TaskAdapter adapter = (TaskAdapter) iter.next();
                  adapter.onStart();
               }
            }
            if (SwingWorker.StateValue.DONE.equals(newValue)) {
               for (Iterator iter = listeners.iterator(); iter.hasNext();) {
                  TaskAdapter adapter = (TaskAdapter) iter.next();
                  adapter.onTerminate();
               }
            }
         }
      }
   }

   private final Collection listeners;

   protected String name;

   private String elementsType;

   private int priority;

   public SwingBackgroundTask() {
      listeners = new LinkedList();
      priority = Thread.NORM_PRIORITY;
      addPropertyChangeListener(new ChangeForwarder());
   }

   protected final Object doInBackground() throws Exception {
      // LogFactory.getLog(this.getClass()).info(
      // getName() + " - " + Thread.currentThread().getName() + ". Started at: "
      // + new Date());
      firePropertyChange(NAME, "", name);
      firePropertyChange(ELEMENTS_TYPE, "", elementsType);
      Object result = executeInBackground();
      firePropertyChange(NAME, name, "");
      firePropertyChange(ELEMENTS_TYPE, elementsType, "");
      // LogFactory.getLog(this.getClass()).info(
      // getName() + " - " + Thread.currentThread().getName() + ". completed at:
      // "
      // + new Date());
      return result;
   }

   protected abstract Object executeInBackground() throws Exception;

   public void addListener(TaskAdapter adapter) {
      listeners.add(adapter);
   }

   public final void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setElementsType(String elementsType) {
      this.elementsType = elementsType;
   }

   public int getPriority() {
      return priority;
   }

   public void setPriority(int priority) {
      this.priority = priority;
   }

}
