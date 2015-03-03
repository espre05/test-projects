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

package net.della.mplatform.scenario.view;

import net.della.mplatform.docking.DockableAbstractView;

import com.sun.scenario.scenegraph.SGGroup;


public abstract class SGDockableAbstractView extends DockableAbstractView {
   private static final String CLOSE_VIEW = "close view";
   private SGGroup rootNode;

   public void setId(String id) {
      rootNode = new SGGroup();
      SGDockInternalFrame panel = new SGDockInternalFrame(id);
      panel.setScene(rootNode);
      setViewComponent(panel);
      init();
      super.setId(id);
   }

}
