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

package net.della.mplatform.application.extensions;

import net.della.mplatform.application.core.BootLoader;
import net.della.mplatform.application.core.DefaultApplication;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.docking.application.DockingBootLoader;

public abstract class BaseSingleExtensionApplicationStarter implements Extension {

   public void start() throws Exception {
      BootLoader bootLoader = new DockingBootLoader();
      DefaultApplication application = bootLoader.create();
      application.registerExtension(this);
      bootLoader.init();
      bootLoader.post();
   }

}
