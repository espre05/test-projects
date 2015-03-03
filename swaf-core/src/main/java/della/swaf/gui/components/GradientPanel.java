/*
 * Created on 25-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.gui.components;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GradientPanel extends JPanel {

    private int orientation;

    public static final int VERTICAL = 1;

    public static final int HORIZONTAL = 2;

    public GradientPanel() {
    }

    public GradientPanel(Color background) {
        setBackground(background);
    }

    public GradientPanel(LayoutManager lm, Color background) {
        super(lm);
        setBackground(background);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isOpaque()) {
            return;
        }
        Color control = UIManager.getColor("control");
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        Paint storedPaint = g2.getPaint();
        setPaint(control, g2);
        g2.fillRect(0, 0, width, height);
        g2.setPaint(storedPaint);
    }

    private void setPaint(Color control, Graphics2D g2) {
        switch (orientation) {
        case VERTICAL:
            paintVertical(control, g2);
            break;
        case HORIZONTAL:
            paintHorizontal(control, g2);
            break;
        default:
            paintHorizontal(control, g2);
        }
    }

    private void paintHorizontal(Color control, Graphics2D g2) {
        g2.setPaint(new GradientPaint(0, 0, getBackground(), getWidth(), 0, control));
    }

    private void paintVertical(Color control, Graphics2D g2) {
        g2.setPaint(new GradientPaint(0, 0, getBackground(), 0, getHeight(), control));
    }

    public void setOrientation(int gradientPaneOrientation) {
        orientation = gradientPaneOrientation;
    }

}