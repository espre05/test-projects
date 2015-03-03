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

public class RuntimeReflectionException extends RuntimeException {

   public RuntimeReflectionException(String message, Throwable cause) {
      super(message, cause);
   }

   public RuntimeReflectionException(String message) {
      super(message);
   }

   public RuntimeReflectionException(Throwable cause) {
      super(cause);
   }

}
