package della.swaf.docking.defaults;

import java.util.List;

import javax.swing.ActionMap;
import javax.swing.InputMap;

import della.swaf.docking.DockableAbstractView;

public class DefaultDockableView extends DockableAbstractView {

   public DefaultDockableView(String id) {
      setId(id);
   }

   public void removeSelection() {
   }

   public ActionMap getActionMap() {
      return null;
   }

   public InputMap getInputMap() {
      return null;
   }

   public List getSelection() {
      return null;
   }

}
