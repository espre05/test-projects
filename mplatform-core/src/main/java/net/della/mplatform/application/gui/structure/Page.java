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