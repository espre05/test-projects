/*
 * Created on 4-feb-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package della.swaf.application.application;

import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import com.sourcesense.stuff.event.Event;
import com.sourcesense.stuff.event.EventListener;

import della.swaf.application.gui.ActionFactory;
import della.swaf.application.gui.structure.ApplicationWindow;
import della.swaf.application.persistence.Library;
import della.swaf.background.AbstractJob;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public interface Application {

   public final static String CLOSING = "application.closing";
   public static final String CLOSED = "application.closed";

   public String getApplicationHome();

   public Logger getLogger();

   public Library getLibrary();

   public ApplicationWindow getWindow();

   public void addListener(EventListener listener);

   public void fireEvent(Event evt);

   public void removeListener(EventListener listener);

   public void registerExtension(Extension extension);

   public String getResourcesFolder();

   public String getConfigFolder();

   public String getExtensionsFolder();

   public void runOperation(AbstractJob op);

   public void runScheduledOperation(AbstractJob op);

   public void addPropertyChangeListener(PropertyChangeListener listener);

   public ActionFactory getActionFactory();

}