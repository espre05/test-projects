package della.swaf.application.gui;

import javax.swing.Action;

import della.swaf.application.application.Application;

public class ActionFactory {

   private Application app;

   public final void setApplication(Application app) {
      this.app = app;
   }

   public ShowFileChooserAction newFileChooserAction(Action action) {
      return new ShowFileChooserAction(app, action);
   }

   public ApplicationAction newSimpleAction(Action action) {
      return new ApplicationAction(app, action);
   }

}
