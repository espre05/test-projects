/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package net.mcube.library;

import net.della.mplatform.application.persistence.Context;

public class DatabaseContext implements Context {

   private TopFolders topFolders;
   private String home;

   DatabaseContext(TopFolders topFolders) {
      this.topFolders = topFolders;
   }

   public String getID(String path) {
      return topFolders.getID(path);
   }

   public String getTopFolder(String path) {
      return topFolders.getParentTopFolder(path);
   }

   public String getPath(String id) {
      return topFolders.getPath(id);
   }

   public String getHome() {
      return home;
   }

   public final void setHome(String home) {
      this.home = home;
   }

}
