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

/**
 * 
 */
package net.della.mplatform.gui.dnd;

import java.awt.Component;
import java.awt.dnd.*;
import java.util.Iterator;
import java.util.List;

import net.della.mplatform.application.datatypes.Item;
import net.della.stuff.gui.dnd.FileSelection;

// import net.mcube.datatypes.FileItem;

public abstract class ItemDragger implements DragGestureListener, DragSourceListener {

   private DragSource dragSource;

   /**
    * @param dragSource
    * @param component
    */
   public ItemDragger(DragSource dragSource, Component component) {
      this.dragSource = dragSource;
      dragSource.createDefaultDragGestureRecognizer(component, DnDConstants.ACTION_COPY, this);
   }

   protected void dragFile(final FileSelection transferable, final DragGestureEvent evt, Object file) {
      transferable.addElement(file);
      dragSource.startDrag(evt, DragSource.DefaultLinkDrop, transferable, this);
   }

   protected void dragFileList(FileSelection transferable, DragGestureEvent evt, List items) {
      for (Iterator iter = items.iterator(); iter.hasNext();) {
         Item item = (Item) iter.next();
         transferable.addElement(item);
      }
      dragSource.startDrag(evt, DragSource.DefaultLinkDrop, transferable, this);
   }

   protected void dragTransferable(FileSelection transferable, DragGestureEvent evt) {
      dragSource.startDrag(evt, DragSource.DefaultLinkDrop, transferable, this);
   }

   /**
    * Called when the user is dragging this drag source and enters the drop
    * target.
    */
   public void dragEnter(DragSourceDragEvent evt) {
      // 
      // LogFactory.getLog(this.getClass()).info("enter");
   }

   /**
    * Called when the user is dragging this drag source and moves over the drop
    * target.
    */
   public void dragOver(DragSourceDragEvent evt) {
      // LogFactory.getLog(this.getClass()).info("over");
   }

   /**
    * Called when the user is dragging this drag source and leaves the drop
    * target.
    */
   public void dragExit(DragSourceEvent evt) {
      // LogFactory.getLog(this.getClass()).info("exit");
   }

   /**
    * Called when the user changes the drag action between copy or move.
    */
   public void dropActionChanged(DragSourceDragEvent evt) {
      // LogFactory.getLog(this.getClass()).info("changed");
   }

   /**
    * Called when the user finishes or cancels the drag operation.
    */
   public void dragDropEnd(DragSourceDropEvent evt) {
      // LogFactory.getLog(this.getClass()).info("dropEnd");
   }

}