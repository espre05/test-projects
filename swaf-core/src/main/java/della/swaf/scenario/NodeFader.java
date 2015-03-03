package della.swaf.scenario;

import com.sun.scenario.animation.Clip;

public class NodeFader {

   Clip clipOut, clipIn;
   private boolean visible;

   public NodeFader(Clip clipIn, Clip clipOut) {
      this.clipIn = clipIn;
      this.clipOut = clipOut;
   }

   public void fadeIn() {
      clipIn.start();
   }

   public void fadeOut() {
      clipOut.start();
   }

   public void toggle() {
      if (visible) {
         fadeOut();
      } else {
         fadeIn();
      }
      visible = !visible;
   }

   public void setVisible(boolean b) {
      visible = b;
   }

}
