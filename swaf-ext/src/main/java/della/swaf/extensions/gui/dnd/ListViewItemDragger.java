/**
 * 
 */
package della.swaf.extensions.gui.dnd;

import java.awt.Component;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import della.swaf.application.datatypes.Item;
import della.swaf.application.datatypes.ItemUtils;
import della.swaf.extensions.gui.AbstractListView;
import della.swaf.gui.dnd.FileSelection;
import della.swaf.gui.dnd.ItemDragger;

public final class ListViewItemDragger extends ItemDragger {

   private AbstractListView listView;

   public ListViewItemDragger(AbstractListView view, DragSource dragSource, Component component) {
      super(dragSource, component);
      listView = view;
   }

   public void dragGestureRecognized(DragGestureEvent evt) {
      FileSelection transferable = new FileSelection();
      List selection = listView.getSelection();

      // InputEvent triggerEvent = evt.getTriggerEvent();
      // if (triggerEvent.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK) {
      // Logger.getLogger(this.getClass().getName()).info("export as zip...");
      // List zips = new ArrayList();
      // for (Iterator iter = selection.iterator(); iter.hasNext();) {
      // ItemSet itemSet = (ItemSet) iter.next();
      // String zipPath = itemSet.getString(AlbumAttributes.ZIP_FILE);
      // if (!zipPath.equals("")) {
      // zips.add(zipPath);
      // Logger.getLogger(this.getClass().getName()).info("adding: " + zipPath);
      // transferable.addElement(new File(zipPath));
      // }
      // }
      // Logger.getLogger(this.getClass().getName()).info("number of zips: " +
      // zips.size());
      // dragTransferable(transferable, evt);
      // return;
      // }

      final List items = new ArrayList();
      for (Iterator it = selection.iterator(); it.hasNext();) {
         Item item = (Item) it.next();
         items.addAll(ItemUtils.findLeafs(item));
      }
      dragFileList(transferable, evt, items);

   }
}