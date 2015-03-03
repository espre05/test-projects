/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

/*
 * Created on 30-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package net.mcube.util.query;

import java.util.concurrent.Executors;

import net.della.mplatform.background.TaskAdapter;
import net.della.mplatform.background.swing.IndeterminateBackgroundTask;
import net.della.mplatform.background.swing.SwingBackgroundTask;

import org.apache.commons.logging.LogFactory;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.TransformedList;
import ca.odell.glazedlists.event.ListEvent;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class QueryList extends TransformedList {

   protected EventList baseList;

   boolean scheduled;

   private int max;

   private EventList cachedList;

   private Query query;

   private String name;

   public QueryList(EventList source, Query query) {
      super(source);
      query.setSource(source);
      source.addListEventListener(this);
      this.query = query;
      name = "anonymoous query list";

      baseList = new BasicEventList();
      cachedList = new BasicEventList();

      max = source.size();
      updateData(true);
      cachedList.addAll(baseList);

   }

   private void updateData(boolean clearCache) {

      LogFactory.getLog(this.getClass()).debug(name + ": update...");
      if (!clearCache && source.size() == max) {
         baseList.clear();
         baseList.addAll(cachedList);
         return;
      }
      try {
         LogFactory.getLog(this.getClass()).info(
               name + " updates: " + " - " + Thread.currentThread().getName());
         long start = System.currentTimeMillis();
         query.execute(baseList);
         long end = System.currentTimeMillis();
         long elapsedTime = end - start;
         LogFactory.getLog(this.getClass()).info(name + ": time elapsed: " + elapsedTime);
      } catch (Exception e) {
         e.printStackTrace();
      }

      if (clearCache || source.size() > max) {
         max = source.size();
         cachedList = new BasicEventList();
         cachedList.addAll(baseList);
      }
   }

   public void setQuery(Query query) {
      this.query = query;
   }

   public void listChanged(ListEvent listChanges) {

      /*
       * if (listChanges.getBlocksRemaining() == 0) return; if (scheduled) {
       * timer.cancel(); scheduled = false; } timer = new Timer(); int delay =
       * 350 + random.nextInt(50); timer.schedule(new RefreshTask(), delay);
       * scheduled = true;
       */

      // RefreshTask refreshTask = new RefreshTask();
      // Executors.newSingleThreadExecutor().execute(refreshTask);
      refresh(false);
   }

   public Object get(int index) {
      return baseList.get(index);
   }

   public int size() {
      return baseList.size();
   }

   private void refreshInThisThread(final boolean clearCache) {
      updates.beginEvent();

      if (baseList.size() > 0)
         updates.addChange(ListEvent.DELETE, 0, baseList.size() - 1);

      baseList.getReadWriteLock().writeLock().lock();

      try {
         updateData(clearCache);
      } catch (Exception e) {
         LogFactory.getLog(this.getClass()).info(e.toString());
      } finally {
         baseList.getReadWriteLock().writeLock().unlock();
      }

      if (baseList.size() != 0)
         updates.addChange(ListEvent.INSERT, 0, baseList.size() - 1);
      // LogFactory.getLog(this.getClass()).info(
      // name + " commit: " + " - " + Thread.currentThread().getName());
      // long start = System.currentTimeMillis();
      updates.commitEvent();
      // long end = System.currentTimeMillis();
      // long time = end - start;
      // LogFactory.getLog(this.getClass()).info(name + ": time elapsed: " +
      // time);
   }

   public void refresh(final boolean clearCache) {
      refreshInThisThread(clearCache);
   }

   private void refreshInNewThread(final boolean clearCache) {
      SwingBackgroundTask refreshTask = new IndeterminateBackgroundTask() {

         protected Object executeIndeterminateOperation() {
            try {
               baseList.getReadWriteLock().writeLock().lock();
               updateData(clearCache);
            } catch (Exception e) {
               LogFactory.getLog(this.getClass()).info(e.toString());
            } finally {
               baseList.getReadWriteLock().writeLock().unlock();
            }
            return null;
         }
      };
      refreshTask.addListener(new TaskAdapter() {

         public void onStart() {
            updates.beginEvent();

            if (baseList.size() > 0)
               updates.addChange(ListEvent.DELETE, 0, baseList.size() - 1);
         }

         public void onTerminate() {
            if (baseList.size() != 0)
               updates.addChange(ListEvent.INSERT, 0, baseList.size() - 1);
            updates.commitEvent();
         }

      });
      Thread thread = new Thread(refreshTask);
      // thread.setPriority(Thread.MIN_PRIORITY);
      Executors.newSingleThreadExecutor().execute(thread);
   }

   public void setName(String name) {
      this.name = name;
   }

   public String toString() {
      return "QueryList: " + name;
   }

}