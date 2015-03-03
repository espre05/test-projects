package della.swaf.scenario;

import com.sun.scenario.animation.Clip;
import com.sun.scenario.scenegraph.SGNode;

public class ClipFactory {

   public static Clip makeNonVisible(SGNode componentNode) {
      return Clip.create(0, componentNode, "visible", false);
   }

   public static Clip makeVisible(SGNode componentNode) {
      return Clip.create(0, componentNode, "visible", true);
   }

}
