package della.swaf.form;

import com.jgoodies.binding.PresentationModel;

public class Form {

   private final PresentationModel presentationModel;

   Form(PresentationModel presentationModel) {
      this.presentationModel = presentationModel;
   }

   public void save() {
      presentationModel.triggerCommit();
   }

   public void reset() {
      presentationModel.triggerFlush();
   }

}
