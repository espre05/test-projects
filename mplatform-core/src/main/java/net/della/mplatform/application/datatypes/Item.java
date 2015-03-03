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

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public interface Item {

   String TYPE = "type";

   public void put(String key, Object value);

   public Object get(String key);

   public void remove(String key);

   public int countChilds();

   public Item getChild(int index);

   public Iterator childIterator();

   public boolean hasChild(Item child);

   public List listChilds();

   public String getMainAttribute();

   public String getString(String key);

   public Boolean getBoolean(String propertyName);

   public Integer getInteger(String propertyName);

   public Long getLong(String propertyName);

   public Calendar getDate(String propertyName);

   public Timestamp getTimestamp(String propertyName);

   public InputStream getStream(String key);

   public void save();

   public String getType();

   public void setType(String type);

   public String getId();

   public void addChild(Item item);

   public Object getMainAttributeValue();

}