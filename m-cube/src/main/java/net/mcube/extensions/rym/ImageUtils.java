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

package net.mcube.extensions.rym;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageUtils {

	public static BufferedImage scale(BufferedImage orig, int width, int height) {
		// if (scale == 1.0)
		// return orig; // same size ?
	
		double scale = (double) width / (double) orig.getWidth();
		AffineTransform resizeDefinition = AffineTransform.getScaleInstance(scale, scale);
		AffineTransformOp resizer = new AffineTransformOp(resizeDefinition,
				AffineTransformOp.TYPE_BILINEAR);
		// int width = (int) (orig.getWidth() * scale);
		// int height = (int) (orig.getHeight() * scale);
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		resizer.filter(orig, result);
		return result;
	}

	public static BufferedImage scale(BufferedImage orig, double scale) {
		if (scale == 1.0)
			return orig; // same size ?
	
		AffineTransform resizeDefinition = AffineTransform.getScaleInstance(scale, scale);
		AffineTransformOp resizer = new AffineTransformOp(resizeDefinition,
				AffineTransformOp.TYPE_BILINEAR);
		int width = (int) (orig.getWidth() * scale);
		int height = (int) (orig.getHeight() * scale);
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		resizer.filter(orig, result);
		return result;
	}

}
