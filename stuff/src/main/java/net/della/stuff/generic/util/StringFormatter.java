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

/*
 * Created on 11-nov-2003
 *  
 */
package net.della.stuff.generic.util;

import java.util.*;

/**
 * @author Daniele
 */
public class StringFormatter {

	/** First letter of each word Capital, other LowerCase */
	public static final int ALL_CAPITAL = 0;
	/** First letter of first word Capital, other LowerCase */
	public static final int FIRST_CAPITAL = 1;
	private final List lower = new LinkedList();
	private int style;
	private boolean firstOfStringCapital;

	public void addLower(String conj) {
		lower.add(conj);
	}

	/**
	 * default format (ALL_CAPITAL)
	 */
	public static String _format(String s) {
		return format(s, ALL_CAPITAL);
	}

	/**
	 * @param flag:
	 *            use StringFormatter constants
	 */
	public static String format(String s, int flag) {
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(s, " ");
		switch (flag) {
			case ALL_CAPITAL :
				formatAllCapital(sb, st);
				break;
			case FIRST_CAPITAL :
				formatFirstCapital(sb, st);
				break;
			default :
				break;
		}
		return sb.toString().trim();
	}

	public String format(String s) {
		if (s.equals(""))
			return s;
		switch (style) {
			case ALL_CAPITAL :
				return formatAllCapital(s);
			default :
				return s;
		}
	}

	private String formatAllCapital(String s) {
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(s, " ");
		if (firstOfStringCapital && st.hasMoreTokens()) {
			String token = st.nextToken();
			token = firstCapitalOtherLowerCase(token);
			sb.append(token + " ");
		}
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (isForcedLower(token))
				token = token.toLowerCase();
			else
				token = firstCapitalOtherLowerCase(token);
			sb.append(token + " ");
		}
		return sb.toString().trim();
	}

	private boolean isForcedLower(String token) {
		for (Iterator it = lower.iterator(); it.hasNext();) {
			String s = (String) it.next();
			if (token.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	private static void formatFirstCapital(StringBuffer sb, StringTokenizer st) {
		String token = st.nextToken();
		token = firstCapitalOtherLowerCase(token);
		sb.append(token + " ");
		while (st.hasMoreTokens()) {
			token = st.nextToken();
			if (token.equalsIgnoreCase("ost"))
				token = token.toUpperCase();
			else
				token = token.toLowerCase();
			sb.append(token + " ");
		}
	}

	private static void formatAllCapital(StringBuffer sb, StringTokenizer st) {
		String token;
		while (st.hasMoreTokens()) {
			token = st.nextToken();
			if (token.equalsIgnoreCase("and"))
				token = handleSpecialString(token);
			//if (conjunctions.contains(token))
			//	token = token.toUpperCase();
			else
				token = firstCapitalOtherLowerCase(token);
			sb.append(token + " ");
		}
	}

	private static String handleSpecialString(String token) {
		token = token.toLowerCase();
		return token;
	}

	private static String firstCapitalOtherLowerCase(String token) {
		String wordWOfirstChar = token.substring(1);
		String firstChar = token.substring(0, 1);
		firstChar = firstChar.toUpperCase();
		wordWOfirstChar = wordWOfirstChar.toLowerCase();
		return firstChar + wordWOfirstChar;
	}

	/**
	 * @return the original String cleaned up from those simbols , :< >/ | ? *
	 *  
	 */
	public static String clean(String s) {
		return clean(s, "");
	}

	/**
	 * @param substitute:
	 *            the String that will replace the simbbls
	 * @return the original String cleaned up from those simbols , :< >/ | ? *
	 */
	public static String clean(String s, String substitute) {
		s = s.replaceAll(",", substitute);
		s = s.replaceAll(":", substitute);
		s = s.replaceAll("<", substitute);
		s = s.replaceAll(">", substitute);
		s = s.replaceAll("/", substitute);
		s = s.replaceAll("\\|", substitute);
		s = s.replaceAll("\\?", substitute);
		s = s.replaceAll("\\*", substitute);
		return s;
	}

	public abstract class StringHandler {
		private String s;
		public String getS() {
			return s;
		}

		public void setS(String s) {
			this.s = s;
		}

	}

	public void setStyle(int style) {
		this.style = style;
	}

	public void setForceFirstCapital(boolean firstCapital) {
		this.firstOfStringCapital = firstCapital;
	}

}
