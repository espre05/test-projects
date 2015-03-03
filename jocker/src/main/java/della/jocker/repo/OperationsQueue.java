package della.jocker.repo;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class OperationsQueue {

   protected final LinkedBlockingQueue queue;
   protected Logger logger = LoggerFactory.getLogger(getClass());

   public OperationsQueue() {
      queue = new LinkedBlockingQueue();
   }

   public void schedule() {
      int delay = 150;
      schedule(delay);
   }

   /**
    * 
    * @param delay
    *           : in milliseconds
    */
   public void schedule(int delay) {
      ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
      Runnable processQueue = new Runnable() {
         public void run() {
            processNext();
         }
      };
      Random random = new java.util.Random();
      scheduledExecutor.scheduleWithFixedDelay(processQueue, 1000, delay, TimeUnit.MILLISECONDS);
   }

   public Runnable runner() {
      return new Runnable() {
         public void run() {
            try {
               processNext();
            } catch (Exception e) {
               logger.error("", e);
            }
         }
      };
   }

   public int size() {
      return queue.size();
   }

   public void enqueue(Iterator events) {
      while (events.hasNext()) {
         queue.add(events.next());
      }
   }

   protected void enqueue(Object element) {
      queue.add(element);
   }

   protected Object poll() {
      return queue.poll();
   }

   protected boolean isEmpty() {
      return queue.isEmpty();
   }

   public void processNext() {
      if (isEmpty()) {
         return;
      }
      process();
   }

   public abstract void process();

}
