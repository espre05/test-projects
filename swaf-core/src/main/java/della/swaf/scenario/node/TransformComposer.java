package della.swaf.scenario.node;


import java.awt.geom.AffineTransform;

import com.sun.scenario.animation.Composer;

/**
 * Note that this Composer implementation is only useful for this particular
 * demo (which uses only scaling and translation) and does not work for all
 * general transforms.
 * 
 * @author Chet
 */
public class TransformComposer extends Composer<AffineTransform> {

   public TransformComposer() {
      super(6);
   }

   public double[] decompose(AffineTransform o, double[] v) {
      o.getMatrix(v);
      return v;
   }

   public AffineTransform compose(double[] v) {
      return new AffineTransform(v);
   }
}
