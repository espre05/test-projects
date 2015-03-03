/**
 * 
 */
package della.swaf.gui.dnd;

import java.awt.Component;
import java.awt.dnd.*;
import java.util.Iterator;
import java.util.List;

import della.swaf.application.datatypes.Item;

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