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

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	static SimpleDateFormat yearMonthDayFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String formatYearMonthDay(Timestamp timestamp) {
		return yearMonthDayFormat.format(timestamp);
	}

	public static Timestamp createTimestamp(int year, int month, int day) {
		return toTimestamp(createDate(year, month, day));
	}

	private static Date createDate(int year, int month, int day) {
		return new GregorianCalendar(year, month-1, day).getTime();
	}

	public static Timestamp parseYearMonthDay(String dateAsText) throws ParseException {
		Date date = yearMonthDayFormat.parse(dateAsText);
		return toTimestamp(date);
	}

	private static Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}
