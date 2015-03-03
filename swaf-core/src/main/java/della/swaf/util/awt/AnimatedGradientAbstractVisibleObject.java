package della.swaf.util.awt;

import java.awt.*;


public abstract class AnimatedGradientAbstractVisibleObject extends AbstractVisibleObject {

    private int orientation;

    private Point startPoint;

    private Point endPoint;

    public AnimatedGradientAbstractVisibleObject() {
        super();
        endPoint = new Point();
        startPoint = new Point();
    }

    public void paintObject(Graphics2D g2) {
        // if (!painted || forceRepaint)
        setPaint(g2);
        g2.fill(getShape());
    }

    protected void setPaint(Graphics2D g2) {
        switch (getOrientation()) {
        case VERTICAL:
            paintVertical(g2);
            break;
        case HORIZONTAL:
            paintHorizontal(g2);
            break;
        default:
            paintHorizontal(g2);
        }
    }

    private int getOrientation() {
        return orientation;
    }

    private void paintHorizontal(Graphics2D g2) {
        g2.setPaint(getHorizontalGradientPaint());
    }

    protected GradientPaint getHorizontalGradientPaint() {
        return new GradientPaint(getStartPoint().x, getStartPoint().y, getColor(),
                getEndPoint().x, getEndPoint().y, getBackground());
    }

    private void paintVertical(Graphics2D g2) {
        g2.setPaint(getVerticalGradientPaint());
    }

    protected GradientPaint getVerticalGradientPaint() {
        return new GradientPaint(getStartPoint().x, getStartPoint().y, getColor(),
                getEndPoint().x, getEndPoint().y, getBackground());
    }

    protected Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int x, int y) {
        this.endPoint.x = x;
        this.endPoint.y = y;
    }

    public void setEndPoint(Point endPoint) {
        setEndPoint(endPoint.x, endPoint.y);
    }

    public void setStartPoint(int x, int y) {
        this.startPoint.x = x;
        this.startPoint.y = y;
    }

    public void setShape(Shape shape) {
        super.setShape(shape);
        Rectangle bounds = shape.getBounds();
        setStartPoint(bounds.x, bounds.y);
        setEndPoint(bounds.x + bounds.width, bounds.y + bounds.height);
    }

    public void setStartPoint(Point p) {
        setStartPoint(p.x, p.y);
    }

}
