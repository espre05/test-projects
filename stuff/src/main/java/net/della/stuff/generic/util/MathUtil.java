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

package net.della.stuff.generic.util;

public class MathUtil {

    public static int compareStringValue(String value1String, String value2String) {
        return value1String.compareToIgnoreCase(value2String);
    }

    public static int compareIntValue(String value1String, String value2String) {
        return Integer.parseInt(value1String) - Integer.parseInt(value2String);
    }

    public static int compareLongValue(String value1String, String value2String) {
        long value2 = Long.parseLong(value2String);
        long value1 = Long.parseLong(value1String);
        if (value1 < value2)
            return -1;
        if (value1 > value2)
            return 1;
        return 0;
    }

	public static boolean isLong(String s) {
		try {
			Long.parseLong(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * a/b = c/d
	 * @return a = (c/d)*b
	 */
	public static int normalize(int b, int c, int d) {
		float factor = (c / d);
		int a = (int) (factor * b);
		return a;
	}

	/**
	 * 
	 * a/b = c/d
	 * @return a = (c/d)*b
	 */
	public static double normalize(double b, double c, double d) {
		if (d == 0)
			throw new IllegalArgumentException("d parameter cannot be zero");
		double factor = c / d;
		double a = b * factor;
		return a;
	}
	
	/**
	 * 
	 * a/1 = c/d
	 * @return a = (c/d)*1
	 */
	public static double normalize(double c, double d) {
		return normalize(1, c, d);
	}
	

	public static String secondsToMinSec(String length) {
		int l = (new Integer(length)).intValue();
		int min = l / 60;
		int sec = l % 60;
		String minSec = min + ":" + sec;
		return minSec;
	}

}
