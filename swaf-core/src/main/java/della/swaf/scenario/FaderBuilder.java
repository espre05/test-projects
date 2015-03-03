package della.swaf.scenario;

import com.sun.scenario.animation.Clip;
import com.sun.scenario.scenegraph.SGNode;

public class FaderBuilder {

   private final int duration;
   private final float minOpacity;
   private final float maxOpacity;

   public FaderBuilder(int fadeDur, float minOpacity, float maxOpacity) {
      this.duration = fadeDur;
      this.minOpacity = minOpacity;
      this.maxOpacity = maxOpacity;
   }

   public Clip fadeIn(SGNode node) {
      return Clip.create(duration, node, "opacity", minOpacity, maxOpacity);
   }

   public Clip fadeOut(SGNode node) {
      return Clip.create(duration, node, "opacity", maxOpacity, minOpacity);
   }

}
