/*
 * Created on 18-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.application.gui.structure;

import javax.swing.JComponent;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface Page {
    
    public JComponent getComponent();

    public View getFocusedView();

    public String getID();

    public void addView(View view);

    public void setFocusOnPreviousView();

    public void setFocusOnNextView();

    public void setFocusOnPreviousView(View activeView);

    public void setFocusOnNextView(View activeView);
    

    public void revalidateAndRepaint();
    public void removeView(View view);

	public void addView(View view, boolean floating);

}