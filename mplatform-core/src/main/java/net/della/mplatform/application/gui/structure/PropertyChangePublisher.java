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
 * Created on 9-mag-2005 
 *
 */

package net.della.mplatform.application.gui.structure;

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
