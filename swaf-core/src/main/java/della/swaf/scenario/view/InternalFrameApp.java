package della.swaf.scenario.view;

import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

import com.sun.scenario.animation.Composer;
import com.sun.scenario.scenegraph.JSGPanel;
import com.sun.scenario.scenegraph.SGComponent;

import della.swaf.scenario.node.TransformComposer;

public class InternalFrameApp {

   static {
      Composer.register(AffineTransform.class, TransformComposer.class);
   }

   public static void main(String[] args) {
      new InternalFrameApp().start();
   }

   private void start() {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);

      JSGPanel panel = new JSGPanel();
      SGComponent component = new SGComponent();
      component.setComponent(new SGDockInternalFrame("SGDockFrame"));
      panel.setScene(component);
      frame.setContentPane(panel);

      frame.invalidate();
      // frame.pack();
      frame.setLocationRelativeTo(null);

      frame.setVisible(true);
   }

}
