/*
 * Created on 25-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.UIManager;

import della.swaf.util.awt.VisibleObject;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdvancedGradientPanel extends JPanel {

   private int orientation;

   public static final int VERTICAL = 1;

   public static final int HORIZONTAL = 2;

   private Point startPoint;

   private Point endPoint;

   private Color overlayColor;

   private Shape shape;

   private final List objectsToPaint;

   public AdvancedGradientPanel() {
      this(Color.BLUE);
   }

   public AdvancedGradientPanel(Color background) {
      this(background, UIManager.getColor("control"));
   }

   public AdvancedGradientPanel(Color background, Color overlay) {
      setBackground(background);
      overlayColor = overlay;
      // setOpaque(false);
      objectsToPaint = new ArrayList();
   }

   public AdvancedGradientPanel(LayoutManager lm, Color background) {
      this(background);
      setLayout(lm);
   }

   public void addObject(VisibleObject vo) {
      objectsToPaint.add(vo);
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (!isOpaque()) {
         return;
      }

      Graphics2D g2 = (Graphics2D) g;
      Paint storedPaint = g2.getPaint();

      setPaint(overlayColor, g2);
      g2.fill(shape);

      g2.setPaint(storedPaint);
   }

   private void setPaint(Color overlayColor, Graphics2D g2) {
      switch (orientation) {
      case VERTICAL:
         paintVertical(overlayColor, g2);
         break;
      case HORIZONTAL:
         paintHorizontal(overlayColor, g2);
         break;
      default:
         paintHorizontal(overlayColor, g2);
      }
   }

   private void paintHorizontal(Color color, Graphics2D g2) {
      g2.setPaint(new GradientPaint(startPoint.x, startPoint.y, color, endPoint.x, endPoint.y,
            getBackground()));
   }

   private void paintVertical(Color color, Graphics2D g2) {
      g2.setPaint(new GradientPaint(startPoint.x, startPoint.y, color, endPoint.x, endPoint.y,
            getBackground()));
   }

   public void setOrientation(int gradientPaneOrientation) {
      orientation = gradientPaneOrientation;
   }

   public void setStartPoint(Point p) {
      this.startPoint.x = p.x;
      this.startPoint.y = p.y;
   }

   public void setStartPoint(int x, int y) {
      this.startPoint.x = x;
      this.startPoint.y = y;
   }

   public Point getStartPoint() {
      return startPoint;
   }

   public void setShape(Shape shape) {
      this.shape = shape;
      Rectangle bounds = shape.getBounds();
      startPoint = new Point(bounds.x, bounds.y);
      endPoint = new Point(bounds.x + bounds.width, bounds.y + bounds.height);
   }

   public Point getEndPoint() {
      return endPoint;
   }

   public void setEndPoint(int x, int y) {
      this.endPoint.x = x;
      this.endPoint.y = y;
   }

   public void setEndPoint(Point endPoint) {
      this.endPoint = endPoint;
   }

}