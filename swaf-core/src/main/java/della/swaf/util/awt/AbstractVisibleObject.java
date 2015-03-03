/*
 * Created on 12-mag-2005 
 * 
 */
package della.swaf.util.awt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author della
 * 
 */
public abstract class AbstractVisibleObject implements VisibleObject {

    public static final int VERTICAL = 1;

    public static final int HORIZONTAL = 2;

    private Color overlayColor;

    private Color backgroundColor;

    private Shape shape;

    private final List listeners;
    
//    public void paintObject(Graphics2D g2, Map options) {
//    	paintObject(g2);
//    }
    
    public void paintObject(Graphics2D g2) {
    	g2.setColor(getColor());
		g2.fill(getShape());    	
    }

    public AbstractVisibleObject() {
        backgroundColor = Color.BLACK;
        overlayColor = Color.BLACK;
        listeners = new ArrayList();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        fireChangeEvent();
    }

    public void setOverlayColor(Color overlayColor) {
        this.overlayColor = overlayColor;
        fireChangeEvent();
    }

    /**
     * @return
     */
    public Color getColor() {
        return overlayColor;
    }

    /**
     * @return
     */
    public Shape getShape() {
        return shape;
    }    

    /**
     * @return
     */
    protected Color getBackground() {
        return backgroundColor;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * @param x
     * @param y
     */
    public abstract void setPosition(int x, int y);

    public Point getLocation() {
        return getShape().getBounds().getLocation();
    }

    /**
     * 
     */
    protected void fireChangeEvent() {
        for (Iterator it = listeners.iterator(); it.hasNext();) {
            VisibleObjectListener listener = (VisibleObjectListener) it.next();
            listener.eventHappened();
        }
    }

    /**
     * @param listener
     * 
     */
    public void addListener(VisibleObjectListener listener) {
        listeners.add(listener);
    }

}