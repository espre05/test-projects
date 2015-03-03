/**
 * 
 */
package della.swaf.gui.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import della.swaf.util.Command;

public final class PanelMediator implements ActionListener {

    private Map commands;

    private JPanel panel;

    public PanelMediator(JPanel panel) {
        commands = new HashMap();
        this.panel = panel;

    }

    public void addComponent(JComponent component, Command command) {
        if (component instanceof AbstractButton)
            addButton((AbstractButton) component, command);
        else
            panel.add(component);
    }

    private void addButton(AbstractButton button, Command command) {
        panel.add(button);
        button.addActionListener(this);
        commands.put(button.getActionCommand(), command);
    }
    
    public void removeComponent(JComponent component) {
        if (component instanceof AbstractButton)
            removeButton((AbstractButton) component);
        else
            panel.remove(component);
    }

    private void removeButton(AbstractButton button) {
        panel.remove(button);
        button.removeActionListener(this);
        commands.remove(button.getActionCommand());
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        for (Iterator it = commands.keySet().iterator(); it.hasNext();) {
            String command = (String) it.next();
            if (cmd.equals(command)) {
                Command exec = (Command) commands.get(cmd);
                exec.run();
                return;
            }
        }
    }

    public void removeAll() {
        panel.removeAll();
    }

	public Component getComponent() {
		return panel;
	}

}