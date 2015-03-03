/*
 * Created on 3-feb-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package della.swaf.application.gui.structure;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class BasicPage extends AbstractPage {

    private JComponent mainComponent;
    
    public BasicPage() {
    	super();
    	mainComponent = createMainComponent();
	}

	private JComponent createMainComponent() {
        JPanel mainComponent = new JPanel();
        mainComponent.setLayout(new BorderLayout());
        mainComponent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return mainComponent;        
    }  

    public void revalidateAndRepaint() {
        mainComponent.revalidate();
        mainComponent.repaint();
    }


	public JComponent getComponent() {
		return mainComponent;
	}

	public void addPanel(JComponent component) {
		mainComponent.add(component, BorderLayout.CENTER);
	}

	public int getY() {
		return mainComponent.getY();
	}

}
