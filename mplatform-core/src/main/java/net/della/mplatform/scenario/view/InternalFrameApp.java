/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.mplatform.scenario.view;

import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

import net.della.stuff.gui.scenario.TransformComposer;

import com.sun.scenario.animation.Composer;
import com.sun.scenario.scenegraph.JSGPanel;
import com.sun.scenario.scenegraph.SGComponent;

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
