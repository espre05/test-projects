package della.swaf.background;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class BackgroundManager {

   private final Collection listeners;

   public BackgroundManager() {
      listeners = new ArrayList();
   }

   public void runSimple(AbstractJob operation) {
      Executor singleThreadExecutor = Executors.newSingleThreadExecutor();
      run(operation, singleThreadExecutor);
   }

   private void run(AbstractJob job, Executor singleThreadExecutor) {
      TaskList taskList = job.getThreadPool();
      taskList.addListeners(listeners);
      taskList.process(singleThreadExecutor);
   }

   public void runScheduled(AbstractJob operation) {
      ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
      run(operation, singleThreadScheduledExecutor);
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      listeners.add(listener);
   }

}
