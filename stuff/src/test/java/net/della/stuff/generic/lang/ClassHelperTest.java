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

package net.della.stuff.generic.lang;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.della.stuff.generic.lang.ClassHelper;

import org.junit.Test;

public class ClassHelperTest {

   @org.junit.Test
   public void shouldExtractFieldWithPrefixFilter() throws Exception {
      ClassHelper classHelper = new ClassHelper(HelperTestClass.class);
      List<Field> fields = classHelper.listFields("PREFIX_");
      assertEquals(2, fields.size());
      Field field = fields.get(1);
      assertEquals(Integer.class, field.getGenericType());
      assertEquals("PREFIX_FIELD1", fields.get(0).getName());
      assertEquals(3, classHelper.get(field));
   }

   @Test
   public void shouldFindMethod() throws Exception {
      ClassHelper classHelper = new ClassHelper(java.io.File.class);
      Method method = classHelper.findMethod("getName");
   }

}
