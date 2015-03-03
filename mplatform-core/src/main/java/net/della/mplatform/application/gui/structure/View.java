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

/*
 * Created on 18-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.della.mplatform.application.gui.structure;

import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;


/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface View extends FocusListener, PropertyChangePublisher {

	public static final String FOCUS_GAINED = "focus gained";

	public static final String FOCUS_LOST = "focus lost";

	public static final String SELECTION_CHANGED = "Selection Changed";

	public static final String CONTEXT_MENU = null;

	public String getName();

	public String getId();

	public void requestFocus();

	public ActionMap getActionMap();

	public InputMap getInputMap();

	public void addMouseListener(MouseListener listener);

	public List getSelection();

	public void removeSelection();

	public void addToolbarButton(AbstractButton button);

	public void addToPopup(String popupId, JComponent c);

	public void setEnabled(boolean b);
	
	public boolean isEnabled();

}