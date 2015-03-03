package della.swaf.background;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class TaskList {

   protected final Collection<BackgroundTask> runnables;
   private Collection listeners;

   TaskList() {
      runnables = new ArrayList();
   }

   public void add(BackgroundTask runnable) {
      runnables.add(runnable);
   }

   public final void process(Executor executor) {
      attachListeners();
      for (Iterator it = runnables.iterator(); it.hasNext();) {
         BackgroundTask task = (BackgroundTask) it.next();
         execute(executor, task);
      }
   }

   protected abstract void execute(Executor executor, Runnable runnable);

   // protected void execute(Executor executor, Runnable runnable) {
   // executor.execute(runnable);
   // }

   private void attachListeners() {
      for (Iterator iter = runnables.iterator(); iter.hasNext();) {
         BackgroundTask task = (BackgroundTask) iter.next();
         for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
            PropertyChangeListener listener = (PropertyChangeListener) iterator.next();
            task.addPropertyChangeListener(listener);
         }
      }
   }

   public void addAll(TaskList threadPool) {
      runnables.addAll(threadPool.getThreads());
   }

   private Collection getThreads() {
      return runnables;
   }

   public void addListeners(Collection listeners) {
      this.listeners = listeners;
   }

   public static TaskList scheduledThreadPool(final int initialDelay, final int delay) {
      return new TaskList() {
         protected void execute(Executor executor, Runnable runnable) {
            ScheduledExecutorService scheduledExecutor = (ScheduledExecutorService) executor;
            scheduledExecutor.scheduleWithFixedDelay(runnable, initialDelay, delay, TimeUnit.MILLISECONDS);
         }
      };
   }

   public static TaskList scheduledThreadPool(final int delay) {
      return new TaskList() {
         protected void execute(Executor executor, Runnable runnable) {
            ScheduledExecutorService scheduledExecutor = (ScheduledExecutorService) executor;
            scheduledExecutor.schedule(runnable, delay, TimeUnit.MILLISECONDS);
         }
      };
   }

   public static TaskList simpleThreadPool() {
      return new TaskList() {
         protected void execute(Executor executor, Runnable runnable) {
            executor.execute(runnable);
         }
      };
   }

}
