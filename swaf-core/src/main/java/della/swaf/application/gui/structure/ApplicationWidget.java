package della.swaf.application.gui.structure;

import javax.swing.JComponent;

import della.swaf.util.Command;
import della.swaf.util.NullCommand;

public class ApplicationWidget {

    private String id;
    private JComponent component;
    private Command command;

    public ApplicationWidget(String id, JComponent component, Command command) {
        this.id = id;
        this.component = component;
        this.command = command;
    }

    public ApplicationWidget(String id, JComponent component) {
        this(id, component, new NullCommand());
    }

    public Command getCommand() {
        return command;
    }

    public JComponent getComponent() {
        return component;
    }

    public String getId() {
        return id;
    }


    
}
