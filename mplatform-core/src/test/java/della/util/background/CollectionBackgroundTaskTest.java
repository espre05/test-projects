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

package della.util.background;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import junit.framework.TestCase;
import net.della.mplatform.background.swing.CollectionBackgroundTask;

public class CollectionBackgroundTaskTest extends TestCase {

   private CollectionBackgroundTask task;

   private Executor exec;

   protected void setUp() throws Exception {
      super.setUp();
      exec = Executors.newSingleThreadScheduledExecutor();
   }

   void simulateLongTaskOperation(int taskDuration) {
      try {
         Thread.sleep(taskDuration);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public void testDifferentThread() {
      final String mainThreadName = Thread.currentThread().getName();
      task = new CollectionBackgroundTask() {

         protected void applyTo(Object singleElement) {

         }
      };
      task.addPropertyChangeListener(new PropertyChangeListener() {

         public void propertyChange(PropertyChangeEvent evt) {
            // System.out.println(evt.getPropertyName() + " " +
            // evt.getOldValue() + " "
            // + evt.getNewValue());
            String threadName = Thread.currentThread().getName();
            // System.out.println(threadName);
            // System.out.println(op.getProgress());
            assertNotSame(mainThreadName, threadName);
         }
      });
      exec.execute(task);
   }

   public void _testProgressUpdate() {
      final ArrayList list = new ArrayList();
      final int numberOfElements = 8;
      for (int j = 0; j < numberOfElements; j++) {
         list.add(new Object());
      }
      task = new CollectionBackgroundTask() {

         protected void applyTo(Object singleElement) {
            simulateLongTaskOperation(100);
         }
      };
      task.setCollection(list);
      final Counter callCounter = new Counter();
      task.addPropertyChangeListener(new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            // assertEquals(100 * callCounter.countTotal() /
            // numberOfElements, op.getProgress());
            System.out.println(evt.getPropertyName() + " " + evt.getOldValue() + " " + evt.getNewValue());
            if ("progress".equals(evt.getPropertyName()))
               callCounter.tick();
         }
      });
      task.execute();
      try {
         Thread.sleep(1400);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      assertEquals(numberOfElements, callCounter.countTotal());
   }

   public void testGet() throws Exception {
      task = new CollectionBackgroundTask() {

         protected void applyTo(Object singleElement) {
         }
      };
      task.setCollection(new ArrayList());
      task.execute();
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      assertNull(task.get());
      assertTrue(task.isDone());

   }

   private class Counter {

      int i = 0;

      public void tick() {
         i++;
      }

      public int countTotal() {
         return i;
      }
   }
}
