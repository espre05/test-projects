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

/*
 * Created on 24-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageIconScalable extends ImageIcon {

  int width = -1;
  int height = -1;

  public ImageIconScalable() {
    super();
  }

  public ImageIconScalable(byte imageData[]) {
    super(imageData);
  }
  
  public ImageIconScalable(byte imageData[],
                 String description) {
    super(imageData, description);
  }
  
  public ImageIconScalable(Image image) {
    super(image);
  }
  
  public ImageIconScalable(Image image,
                 String description) {
    super(image, description);
  }
  
  public ImageIconScalable(String filename) {
    super(filename);
  }
  
  public ImageIconScalable(String filename,
                 String description) {
    super(filename, description);
  }
               
  public ImageIconScalable(URL location) {
    super(location);
  }
  
  public ImageIconScalable(URL location,
                 String description) {
    super(location, description);
  }

  public int getIconHeight() {
    int returnValue;
    if (height == -1) {
      returnValue = super.getIconHeight();
    } else {
      returnValue = height;
    }
    return returnValue;
  }

  public int getIconWidth() {
    int returnValue;
    if (width == -1) {
      returnValue = super.getIconWidth();
    } else {
      returnValue = width;
    }
    return returnValue;
  }

  public void paintIcon(Component c,
                        Graphics g,
                        int x,
                        int y) {
    if ((width == -1) && (height == -1)) {
      g.drawImage(getImage(), x, y, c);
    } else {
      g.drawImage(getImage(), x, y, width, height, c);
    }
  }

  public void setScaledSize(int width,
                            int height) {
    this.width = width;
    this.height = height;
  }
}
