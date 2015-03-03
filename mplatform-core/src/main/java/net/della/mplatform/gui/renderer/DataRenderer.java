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
 * Created on 23-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.della.mplatform.gui.renderer;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.della.mplatform.application.datatypes.Item;


/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface DataRenderer {
   public JComponent getComponentFor(Item item);

   public void setSelected(boolean b);

   public void setIcon(String id, Icon icon);

   public void setIcon(String id, Icon icon, String toolTipText);

   public void removeIcon(String string);

   public int getRendererHeight();

}