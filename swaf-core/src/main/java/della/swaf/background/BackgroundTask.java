package della.swaf.background;

import java.beans.PropertyChangeListener;

public interface BackgroundTask extends Runnable {

   public static final String NAME = "name";
   public static final String ELEMENTS_TYPE = "elementsType";

   public void addListener(TaskAdapter adapter);

   public void setName(String name);

   public String getName();

   public void setElementsType(String elementsType);

   public int getPriority();

   public void setPriority(int priority);

   public void addPropertyChangeListener(PropertyChangeListener listener);

   void run();

}