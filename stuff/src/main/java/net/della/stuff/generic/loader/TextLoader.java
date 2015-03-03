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

package net.della.stuff.generic.loader;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * Created on 26-dic-2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

/**
 * @author Daniele
 */
public class TextLoader {

   public static final String LINE_SEPARATOR = (String) java.security.AccessController
         .doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));
   String lineSeparator = LINE_SEPARATOR;

   public void saveStringList(List list, File saveFile) {
      try {
         FileWriter w = new FileWriter(saveFile.getPath());
         for (Iterator it = list.iterator(); it.hasNext();) {
            w.write((String) it.next());
            w.write(lineSeparator);
         }
         w.flush();
         w.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public List loadStringList(File file) {
      List list = new LinkedList();
      String line = "";
      BufferedReader inputFile = null;
      try {
         inputFile = new BufferedReader(new FileReader(file));
         while ((line = inputFile.readLine()) != null) {
            list.add(line);
         }
         inputFile.close();
         return list;
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return new LinkedList();
   }

   public void saveFileList(List list, File saveFile) {
      try {
         FileWriter w = new FileWriter(saveFile.getPath());
         for (Iterator it = list.iterator(); it.hasNext();) {
            File file = (File) it.next();
            w.write(file.getPath());
            w.write(lineSeparator);
         }
         w.flush();
         w.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public List loadFileList(File file) {
      List list = new LinkedList();
      String line = "";
      BufferedReader inputFile = null;
      try {
         inputFile = new BufferedReader(new FileReader(file.getPath()));
         while ((line = inputFile.readLine()) != null) {
            File newFile = new File(line);
            list.add(newFile);
         }
         inputFile.close();
         return list;
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return new LinkedList();
   }

}