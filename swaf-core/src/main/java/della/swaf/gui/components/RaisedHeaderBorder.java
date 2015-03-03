/**
 * 
 */
package della.swaf.gui.components;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;

public class RaisedHeaderBorder extends AbstractBorder {

   private static final Insets INSETS = new Insets(1, 1, 1, 0);

   public Insets getBorderInsets(Component c) {
      return INSETS;
   }

   public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {

      g.translate(x, y);
      g.setColor(UIManager.getColor("controlLtHighlight"));
      g.fillRect(0, 0, w, 1);
      g.fillRect(0, 1, 1, h - 1);
      g.setColor(UIManager.getColor("controlShadow"));
      g.fillRect(0, h - 1, w, 1);
      g.translate(-x, -y);
   }
}