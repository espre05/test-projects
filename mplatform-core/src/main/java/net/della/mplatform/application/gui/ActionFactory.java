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

package net.della.mplatform.application.gui;

import javax.swing.Action;

import net.della.mplatform.application.core.Application;


public class ActionFactory {

   private Application app;

   public final void setApplication(Application app) {
      this.app = app;
   }

   public ShowFileChooserAction newFileChooserAction(Action action) {
      return new ShowFileChooserAction(app, action);
   }

   public ApplicationAction newSimpleAction(Action action) {
      return new ApplicationAction(app, action);
   }

}
