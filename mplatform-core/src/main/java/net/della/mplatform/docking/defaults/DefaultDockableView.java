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

package net.della.mplatform.docking.defaults;

import java.util.List;

import javax.swing.ActionMap;
import javax.swing.InputMap;

import net.della.mplatform.docking.DockableAbstractView;


public class DefaultDockableView extends DockableAbstractView {

   public DefaultDockableView(String id) {
      setId(id);
   }

   public void removeSelection() {
   }

   public ActionMap getActionMap() {
      return null;
   }

   public InputMap getInputMap() {
      return null;
   }

   public List getSelection() {
      return null;
   }

}
