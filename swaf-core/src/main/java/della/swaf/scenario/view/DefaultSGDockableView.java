package della.swaf.scenario.view;

import java.util.List;

import javax.swing.ActionMap;
import javax.swing.InputMap;

public class DefaultSGDockableView extends SGDockableAbstractView {

   public DefaultSGDockableView(String id) {
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
