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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jin.collection.core.Criteria;
import jin.collection.core.Iter;

public class ClassHelper {

   private final Class klass;
   private Object instance;

   public ClassHelper(Class classToLoad) {
      this.klass = classToLoad;
   }

   public Object createObject() throws ReflectionException {
      try {
         instance = Class.forName(klass.getName()).newInstance();
         return instance;
      } catch (InstantiationException e) {
         throw new ReflectionException(e);
      } catch (IllegalAccessException e) {
         throw new ReflectionException(e);
      } catch (ClassNotFoundException e) {
         throw new ReflectionException(e);
      }
   }

   // public Object createObject(Object... params) throws ReflectionException {
   // Object object = createObject();
   // Method setter = findMethod("set" + params[0].getClass().getSimpleName());
   // invoke(setter, params[0]);
   // return object;
   // }

   public Method findMethod(String methodName) {
      Method[] methods = klass.getMethods();
      Method realMethod = null;
      for (int i = 0; i < methods.length; i++) {
         Method method = methods[i];
         if (method.getName().equals(methodName)) {
            realMethod = method;
            return realMethod;
         }
      }
      throw new RuntimeReflectionException("Unable to find methond named: " + methodName
            + " on class of type: " + klass.getName());
   }

   public Object invoke(Method targetMethod, Object... object) throws ReflectionException {
      try {
         return targetMethod.invoke(instance, object);
      } catch (IllegalArgumentException e) {
         throw new ReflectionException(detailedReport(targetMethod, object), e);
      } catch (IllegalAccessException e) {
         throw new ReflectionException("Problems in mapping while invoking: "
               + targetMethod.getName(), e);
      } catch (InvocationTargetException e) {
         throw new ReflectionException("Problems in mapping while invoking: "
               + targetMethod.getName(), e);
      }
   }

   private String detailedReport(Method targetMethod, Object[] insertObj) {
      StringBuffer sb = new StringBuffer("Problems while invoking: ");
      sb.append(targetMethod.getName());
      sb.append("( ");
      for (int i = 0; i < insertObj.length; i++) {
         Object param = insertObj[i];
         sb.append(param.getClass().getName());
         sb.append(", ");
      }
      sb.replace(sb.length() - 2, sb.length() - 1, "");
      sb.append(" )");
      return sb.toString();
   }

   public List<Field> listFields(final String prefixPattern) {
      Field[] fields = klass.getFields();
      ArrayList asList = new ArrayList();
      asList.addAll(Arrays.asList(fields));
      return (List) Iter.extract(asList, new Criteria() {
         public boolean match(Object element) {
            Field field = (Field) element;
            return (field.getName().startsWith(prefixPattern));
         }
      });
   }

   public Object get(Field field) throws ReflectionException {
      try {
         return field.get(instance);
      } catch (IllegalArgumentException e) {
         throw new ReflectionException(e);
      } catch (IllegalAccessException e) {
         throw new ReflectionException(e);
      }
   }

}
