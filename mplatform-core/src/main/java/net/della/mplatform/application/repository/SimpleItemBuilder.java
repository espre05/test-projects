package net.della.mplatform.application.repository;

import net.della.mplatform.application.datatypes.FileItem;
import net.della.mplatform.application.datatypes.FileItemAttributes;
import net.della.mplatform.application.datatypes.ObservableItem;

public class SimpleItemBuilder {

   public static ObservableItem createItemFromPath(String itemPath) {
      FileItem nullItem = new FileItem();
      nullItem.put(FileItemAttributes.ABSOLUTE_PATH, itemPath);
      return nullItem;
   }

}
