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
 * Created on 29-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.della.stuff.generic.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Daniele
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StringListUtil {

    public static boolean haveAllElementsTheSameValue(List list) {
        String attr = (String) list.get(0);
        for (Iterator it = list.iterator(); it.hasNext();) {
            String value = (String) it.next();
            if (!attr.equals(value))
                return false;
        }
        return true;
    }
    
    public static boolean areAllElementsEmpty(Collection list) {
        String attr = "";
        for (Iterator it = list.iterator(); it.hasNext();) {
            String value = (String) it.next();
            if (!attr.equals(value))
                return false;
        }
        return true;
    }
    

    public static boolean doListContains(List list, String searchText) {
        for (Iterator it = list.iterator(); it.hasNext();) {
            String path = (String) it.next();
            if (path.indexOf(searchText) != -1)
                return true;
        }
        return false;
    }

}
