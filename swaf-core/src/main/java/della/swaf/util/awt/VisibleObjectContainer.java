/*
 * Created on 25-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.util.awt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.util.*;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class VisibleObjectContainer implements GraphicLayer {

    private final List objectsToPaint;

    private Color backgroundColor;

	private final HashMap voidOptions;

    public VisibleObjectContainer() {
        this(Color.BLUE);
    }

    public VisibleObjectContainer(Color background) {
        setBackground(background);
        objectsToPaint = new ArrayList();
		voidOptions = new HashMap();
    }

    /**
     * @param background
     */
    private void setBackground(Color background) {
        this.backgroundColor = background;
    }

    public void addObject(VisibleObject vo) {
        objectsToPaint.add(vo);
    }

    /**
     * 
     */
    public void removeAllObjects() {
        objectsToPaint.clear();
    }

    public void repaint(Graphics2D g2) {
    	Paint storedPaint = g2.getPaint();		
		for (Iterator it = objectsToPaint.iterator(); it.hasNext();) {
			VisibleObject vo = (VisibleObject) it.next();
			vo.paintObject(g2);
		}		
		g2.setPaint(storedPaint);	
    }

    /**
     * @deprecated
     */
    public Color getBackground() {
        return backgroundColor;
    }

//	public void repaint(Graphics2D g2, Map options) {
//		Paint storedPaint = g2.getPaint();		
//		for (Iterator it = objectsToPaint.iterator(); it.hasNext();) {
//			VisibleObject vo = (VisibleObject) it.next();
//			vo.paintObject(g2, options);
//		}		
//		g2.setPaint(storedPaint);		
//	}

}