/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.mplatform.application.gui.structure;

import javax.swing.JComponent;

import net.della.mplatform.util.NullCommand;
import net.della.stuff.generic.util.Command;

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
