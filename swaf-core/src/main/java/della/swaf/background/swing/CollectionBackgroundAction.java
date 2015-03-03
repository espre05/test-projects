package della.swaf.background.swing;

import java.util.List;

import della.swaf.application.datatypes.MapItem;

public abstract class CollectionBackgroundAction extends BackgroundAction {

   protected SwingBackgroundTask createBackgroundTask() {
      return new CollectionBackgroundTask(getCollection()) {

         protected void applyTo(Object singleElement) {
            CollectionBackgroundAction.this.execute((MapItem) singleElement);
         }
      };
   }

   protected abstract List getCollection();

   /**
    * 
    * This code will be executed on each selected item when the action is runned
    */
   protected abstract void execute(MapItem item);

}
