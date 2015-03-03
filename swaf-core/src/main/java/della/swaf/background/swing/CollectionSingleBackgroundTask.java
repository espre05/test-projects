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


/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class CollectionSingleBackgroundTask extends SwingBackgroundTask {

   protected Collection col;

   // protected AdvancedIter iter;

   public CollectionSingleBackgroundTask() {
   }

   public CollectionSingleBackgroundTask(Collection collection) {
      this.col = collection;
   }

   protected Object executeInBackground() throws Exception {

      // TODO: remove comments
      // ForwarderListener forwarderListener = new ForwarderListener();
      // iter = new AdvancedIter();
      // iter.addPropertyChangeListener(forwarderListener);
      // apply();
      // iter.removePropertyChangeListener(forwarderListener);
      return null;
   }

   protected abstract void apply();

   public final void setCollection(Collection col) {
      this.col = col;
   }

   void setProgress(PropertyChangeEvent evt) {
      setProgress((Integer) evt.getNewValue());
   }

   private final class ForwarderListener implements PropertyChangeListener {
      public void propertyChange(PropertyChangeEvent evt) {
         setProgress(evt);
      }
   }

}
