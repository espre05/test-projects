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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoChildMapItem extends MapItem {

   private final ArrayList childs;
   private String id;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public NoChildMapItem() {
      childs = new ArrayList();
   }

   public Iterator childIterator() {
      return childs.iterator();
   }

   public int countChilds() {
      return 0;
   }

   public Item getChild(int index) {
      return null;
   }

   public boolean hasChild(Item child) {
      return false;
   }

   public List listChilds() {
      return childs;
   }

   public void addChild(Item item) {
      // intentionally left blank
   }

   @Override
   public String getMainAttributeValue() {
      return getString(getMainAttribute());
   }

}
