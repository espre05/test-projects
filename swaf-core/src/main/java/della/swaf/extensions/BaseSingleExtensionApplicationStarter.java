package della.swaf.extensions;

import della.swaf.application.application.BootLoader;
import della.swaf.application.application.DefaultApplication;
import della.swaf.application.application.Extension;
import della.swaf.docking.application.DockingBootLoader;

public abstract class BaseSingleExtensionApplicationStarter implements Extension {

   public void start() throws Exception {
      BootLoader bootLoader = new DockingBootLoader();
      DefaultApplication application = bootLoader.create();
      application.registerExtension(this);
      bootLoader.init();
      bootLoader.post();
   }

}
