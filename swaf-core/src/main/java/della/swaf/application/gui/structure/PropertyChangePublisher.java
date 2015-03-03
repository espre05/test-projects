/*
 * Created on 9-mag-2005 
 *
 */

package della.swaf.application.gui.structure;

import java.beans.PropertyChangeListener;


/**
 * @author della
 */

public interface PropertyChangePublisher {
    
    public void addPropertyChangeListener(PropertyChangeListener listener);

//    public void addPropertyChangeListener(String propertyName,
//            PropertyChangeListener listener);

    public void removePropertyChangeListener(PropertyChangeListener listener);

//    public void removePropertyChangeListener(String propertyName,
//            PropertyChangeListener listener);

}
