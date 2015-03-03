package della.swaf.gui.dnd;

import java.util.List;

public interface DropHandler {

   public static DropHandler NULL = new DropHandler() {

      public void handleDrop(List folderList, int rowAtPoint) {
         // intentionally left blank
      }

   };

   void handleDrop(List folderList, int rowAtPoint);

}
