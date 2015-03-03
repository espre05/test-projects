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

/**
 * 
 */
package net.mcube.gui;

import java.awt.dnd.*;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import net.della.mplatform.application.datatypes.FileItem;
import net.della.stuff.gui.swing.dnd.FileSelection;

// import net.mcube.datatypes.FileItem;

public abstract class ItemDragger implements DragGestureListener, DragSourceListener {

   private DragSource dragSource;

   /**
    * @param dragSource
    */
   public ItemDragger(DragSource dragSource) {
      this.dragSource = dragSource;
   }

   protected void dragFile(final FileSelection transferable, final DragGestureEvent evt, Object file) {
      transferable.addElement(file);
      dragSource.startDrag(evt, DragSource.DefaultLinkDrop, transferable, this);
   }

   protected void dragFileList(FileSelection transferable, DragGestureEvent evt, List items) {
      for (Iterator iter = items.iterator(); iter.hasNext();) {
         FileItem item = (FileItem) iter.next();
         transferable.addElement(new File(item.getPath()));
      }
      dragSource.startDrag(evt, DragSource.DefaultLinkDrop, transferable, this);
   }

   protected void dragTransferable(FileSelection transferable, DragGestureEvent evt) {
      dragSource.startDrag(evt, DragSource.DefaultLinkDrop, transferable, this);
   }

   public void dragEnter(DragSourceDragEvent evt) {
      // Called when the user is dragging this drag source and enters
      // the drop target.
      // LogFactory.getLog(this.getClass()).info("enter");
   }

   public void dragOver(DragSourceDragEvent evt) {
      // Called when the user is dragging this drag source and moves
      // over the drop target.
      // LogFactory.getLog(this.getClass()).info("over");
   }

   public void dragExit(DragSourceEvent evt) {
      // Called when the user is dragging this drag source and leaves
      // the drop target.
      // LogFactory.getLog(this.getClass()).info("exit");
   }

   public void dropActionChanged(DragSourceDragEvent evt) {
      // Called when the user changes the drag action between copy or move.
      // LogFactory.getLog(this.getClass()).info("changed");
   }

   public void dragDropEnd(DragSourceDropEvent evt) {
      // Called when the user finishes or cancels the drag operation.
      // LogFactory.getLog(this.getClass()).info("dropEnd");
   }

}