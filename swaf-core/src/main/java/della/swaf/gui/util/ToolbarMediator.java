/**
 * 
 */
package della.swaf.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import della.swaf.util.Command;

public final class ToolbarMediator implements ActionListener {

    private Map commands;

    private JToolBar toolBar;

    private ButtonGroup group;

    public ToolbarMediator(JToolBar toolBar) {

        this(toolBar, false);
    }

    public ToolbarMediator(JToolBar toolBar, boolean makeGroup) {
        if (makeGroup)
            group = new ButtonGroup();
        commands = new HashMap();
        this.toolBar = toolBar;
    }

    /**
     * 
     * @deprecated
     * 
     */
    public void addComponent(JComponent component, Command command) {
        if (component instanceof AbstractButton)
            addButton((AbstractButton) component, command);
        else
            toolBar.add(component);
    }

    public void addButton(AbstractButton button, Command command) {
        toolBar.add(button);
        button.addActionListener(this);
        commands.put(button.getActionCommand(), command);
        if (group != null)
            group.add(button);
    }

    /**
     * 
     * @deprecated
     * 
     */
    public void removeComponent(JComponent component) {
        if (component instanceof AbstractButton)
            removeButton((AbstractButton) component);
        else
            toolBar.remove(component);
    }

    public void removeButton(AbstractButton button) {
        toolBar.remove(button);
        button.removeActionListener(this);
        commands.remove(button.getActionCommand());
        if (group != null)
            group.remove(button);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        Command command = (Command) commands.get(cmd);
        command.run();
//        for (Iterator it = commands.keySet().iterator(); it.hasNext();) {
//            String command = (String) it.next();
//            if (cmd.equals(command)) {
//                Command exec = (Command) commands.get(cmd);
//                exec.run();
//                return;
//            }
//        }
    }

    public void removeAll() {
        toolBar.removeAll();
    }

}