/*
 * Created on 11-giu-2005 
 * 
 */
package della.swaf.docking;

import java.io.IOException;

import javax.swing.JComponent;

import org.flexdock.docking.Dockable;
import org.flexdock.docking.DockingManager;
import org.flexdock.docking.DockingPort;
import org.flexdock.docking.defaults.DefaultDockingPort;
import org.flexdock.docking.event.DockingEvent;
import org.flexdock.docking.state.PersistenceException;

import della.swaf.application.gui.structure.AbstractPage;
import della.swaf.application.gui.structure.View;

/**
 * @author Della
 * 
 */
public class DockablePage extends AbstractPage {

   private DockingPort dockingPort;

   public DockablePage() {
      dockingPort = new DefaultDockingPort();
      dockingPort.addDockingListener(new DockingAdapter() {

         public void undockingComplete(DockingEvent evt) {
            storeDockingLayout();
         }

         public void dockingComplete(DockingEvent evt) {
            storeDockingLayout();
         }

      });
      System.out.println(dockingPort.getClass().getName() + "@" + Integer.toHexString(hashCode()));

   }

   public void addView(View view, boolean floating) {
      super.addView(view, floating);
      DockableAbstractView dockableView = (DockableAbstractView) view;
      DockingManager.display(dockableView.getId());
   }

   public void removeView(View view) {
      super.removeView(view);
      DockableAbstractView dockableView = (DockableAbstractView) view;
      Dockable dockable = DockingManager.getDockable(dockableView.getId());
      DockingManager.close(dockable);
   }

   public JComponent getComponent() {

      return (JComponent) dockingPort;
   }

   public void revalidateAndRepaint() {
      JComponent c = (JComponent) dockingPort;
      c.revalidate();
      c.repaint();
   }

   void storeDockingLayout() {
      try {
         DockingManager.storeLayoutModel();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (PersistenceException e) {
         e.printStackTrace();
      }
   }
}
