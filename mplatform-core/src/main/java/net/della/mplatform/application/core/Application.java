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

/*
 * Created on 4-feb-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.della.mplatform.application.core;

import java.beans.PropertyChangeListener;

import net.della.mplatform.application.gui.ActionFactory;
import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.mplatform.application.persistence.Library;
import net.della.mplatform.background.AbstractJob;
import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventListener;

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

   public org.slf4j.Logger getLogger();

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