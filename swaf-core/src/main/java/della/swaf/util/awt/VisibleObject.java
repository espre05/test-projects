/*
 * Created on 12-mag-2005 
 * 
 */
package della.swaf.util.awt;

import java.awt.*;

/**
 * @author della
 * 
 */
public interface VisibleObject {


    String CHANGED = "changed";
    /**
     * @return
     */
    public Color getColor();

    /**
     * @return
     */
    public Shape getShape();

    public void paintObject(Graphics2D g2);
    
//    public void paintObject(Graphics2D g2, Map options);

    public Point getLocation();

}