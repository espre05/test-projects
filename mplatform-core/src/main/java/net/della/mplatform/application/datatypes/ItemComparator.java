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

package net.della.mplatform.application.datatypes;

import java.util.Comparator;



public abstract class ItemComparator implements Comparator {

  private Comparator comparator;
  protected String propName;

  public ItemComparator(String propName) {
    this.propName = propName;
  }

  public int compare(Object o1, Object o2) {
    return compare((Item) o1, (Item) o2);
  }

  public int compare(Item item1, Item item2) {
    String name1 = item1.getString(propName);
    String name2 = item2.getString(propName);
    return getComparator().compare(name1, name2);
  }

  protected Comparator getComparator() {
    return comparator;
  }

  protected void setComparator(Comparator stringComparator) {
    this.comparator = stringComparator;
  }

}
