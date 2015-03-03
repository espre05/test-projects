////////////////////////////////////////////////////////////////////////
//
// DateTimeFormatParser.java
//
// This file was generated by MapForce 2011.
//
// YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
// OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
//
// Refer to the MapForce Documentation for further details.
// http://www.altova.com/mapforce
//
////////////////////////////////////////////////////////////////////////


package com.altova.functions;

import java.util.Calendar;

import com.altova.types.DateTime;

public class DateTimeFormatParser {

	public DateTimeFormatParser( final String sPattern)
	{
		mPattern = sPattern;
		mFieldOpen = '[';
		mFieldClose = ']';
	}
	
	public String formatDateTime( DateTime dt )
	{
		String sOutput = "";

		int nLength = mPattern.length();
		ParseState state = ParseState.NORMAL;
		ArgumentState argState = ArgumentState.COMPONENT;
		FieldInfo fieldInfo = new FieldInfo();
		fieldInfo.mComponent = 0;
		fieldInfo.mModifier = "";
		fieldInfo.mWidth = "";
		for( int i = 0; i < nLength; ++i)
		{
			char cCurrent = mPattern.charAt(i);
			char cNext = i + 1 < nLength ? mPattern.charAt(i + 1) : 0;

			switch( state )
			{
				case NORMAL:
				{
					if( cCurrent == mFieldOpen )
					{
						if( cNext != mFieldOpen )
						{
							state = ParseState.INFIELD;
							argState = ArgumentState.COMPONENT;
							fieldInfo.mComponent = 0;
							fieldInfo.mModifier = "";
							fieldInfo.mWidth = "";
						}
						else
						{
							sOutput += cCurrent;
							i++;
						}
					}
					else if( cCurrent == mFieldClose )
					{
						if( cNext != mFieldClose )
						{
							//error something is wrong with the pattern
							throw new IllegalArgumentException( "Incorrect pattern: '" + mPattern + "'");
						}
						else
						{
							sOutput += cCurrent;
							i++;
						}
					}
					else
					{
						sOutput += cCurrent;
					}
				}
				break;
				case INFIELD:
				{
					if( cCurrent == mFieldClose )
					{
						if( cNext != mFieldClose )
						{
							state = ParseState.NORMAL;

							if( fieldInfo.mComponent > 0 )
							{
								String sValue = getComponentValue(dt, fieldInfo.mComponent);
								sValue = processFormatModifier( dt, sValue, fieldInfo);
								sValue = processWidth( sValue, fieldInfo);

								sOutput += sValue;
							}
						}
						else
						{
							//error ]] in field
							throw new IllegalArgumentException( "Incorrect pattern: '" + mPattern + "'");
						}
					}
					else
					{
						switch( argState )
						{
							case COMPONENT:
							{
								fieldInfo.mComponent = cCurrent;
								argState = ArgumentState.FORMAT;
							}
							break;
							case FORMAT:
							{
								if( cCurrent == ',' )
									argState = ArgumentState.WIDTH;
								else
									fieldInfo.mModifier += cCurrent;
							}
							break;
							case WIDTH:
							{
								fieldInfo.mWidth += cCurrent;
							}
							break;
						}
					}
				}
				break;
			}
		}

		if( state != ParseState.NORMAL )
			throw new IllegalArgumentException( "Incorrect pattern: '" + mPattern + "'");

		return sOutput;
	}
	
	protected String processFormatModifier( 
		final DateTime dtData,
		final String sValue,
		FieldInfo fieldInfo)
	{
		if( fieldInfo.mModifier.isEmpty() )
			return sValue;

		String sResult = sValue;
		int nZeroPad = 0;
		while( fieldInfo.mModifier.charAt(nZeroPad) == '0' ) nZeroPad++;

		if( fieldInfo.mModifier.substring(nZeroPad).equals("1") )
		{
			StringBuffer sbResult = new StringBuffer(sValue);
			if( fieldInfo.mComponent == 'F' )
			{
				sbResult = new StringBuffer();
				int day = dtData.getValue().get( Calendar.DAY_OF_WEEK);
				day = day == 1 ? 7 : day - 1; //strange calendar
				sbResult.append( day );
				fieldInfo.mComponent = 'D'; //prevent processWidth to handle the output as string
			}

			//add padding zeros
			int nLength = sbResult.length();
			if( sbResult.length() < nZeroPad + 1 )
				for( int i = 0; i < nZeroPad + 1 - nLength; i++) sbResult.insert(0, '0');
			sResult = sbResult.toString();
		}
		else if( fieldInfo.mModifier.equals("N") 
				|| fieldInfo.mModifier.equals("n") 
				|| fieldInfo.mModifier.equals("Nn") )
		{
			sResult = getComponentNameValue( dtData, fieldInfo);
			if( fieldInfo.mModifier.equals("N") )
				sResult = sResult.toUpperCase();
			else if( fieldInfo.mModifier.equals("n") )
				sResult = sResult.toLowerCase();
		}
		else
		{
			throw new IllegalArgumentException( "Unknown format modifier: '" + fieldInfo.mModifier + "'");
		}

		return sResult;
	}
	
	protected String processWidth(
		final String sValue,
		FieldInfo fieldInfo)
	{
		if( fieldInfo.mWidth.isEmpty() )
			return sValue;

		StringBuffer sResult = new StringBuffer(sValue);
		int nMin, nMax = -1;

		int minusPos = fieldInfo.mWidth.indexOf( '-' );
		int nLength = sResult.length();
		if( minusPos == -1 )
		{
			try {
				nMin = Integer.parseInt( fieldInfo.mWidth );
			}
			catch (NumberFormatException e) {
				nMin = 0;
			}
		}
		else
		{
			try {
				nMin = Integer.parseInt( fieldInfo.mWidth.substring(0, minusPos) );
			}
			catch (NumberFormatException e) {
				nMin = 0;
			}
			try {
				nMax = Integer.parseInt( fieldInfo.mWidth.substring( minusPos + 1 ) );	
			}
			catch (NumberFormatException e) {
				nMax = 0;
			}
		}

		if( nMax > 0 )
		{
			if( nMax >= nMin )
			{
				if( nLength > nMax )
				{
					if( fieldInfo.mComponent != 'Y' )
						sResult.delete( nMax, nLength );
					else
						sResult.delete( 0, nLength - nMax );
				}
			}
		}

		if( nMin > nLength)
		{
			if( fieldInfo.mComponent != 'F' ) // F day of week, is currently the only text and should be padded with ' '
				for( int i = 0; i < nMin - nLength; i++) sResult.insert(0, '0');
			else
				for( int i = 0; i < nMin - nLength; i++) sResult.append(' ');
		}

		return sResult.toString();
	}

	protected String getComponentNameValue(
		DateTime dtData,
		FieldInfo fieldInfo)
	{
		String sValue;

		switch( fieldInfo.mComponent )
		{
			case 'M': 
				sValue = MonthNames[dtData.getMonth() - 1]; 
				fieldInfo.mComponent = 'F'; //this allows the process width to handle the result as Text
			break;
			case 'D':
				sValue = DayNames[ dtData.getValue().get( Calendar.DAY_OF_WEEK) ]; 
				fieldInfo.mComponent = 'F'; //this allows the process width to handle the result as Text	
			break;
			default:
				sValue = getComponentValue(dtData, fieldInfo.mComponent);
		}

		return sValue;
	}

	protected String getComponentValue( DateTime dtData, char cComponent)
	{
		StringBuffer sValue = new StringBuffer();

		switch( cComponent )
		{
			case 'd': sValue.append( dtData.getValue().get( Calendar.DAY_OF_YEAR) ); break;
			case 'D': sValue.append( dtData.getDay() ); break;
			case 'F': sValue.append( DayNames[ dtData.getValue().get( Calendar.DAY_OF_WEEK) ] ); break;
			case 'M': sValue.append( dtData.getMonth() ); break;
			case 'Y': sValue.append( dtData.getYear() ); break;
			case 'W': 
			{
				Calendar cal = dtData.getValue();
				cal.setMinimalDaysInFirstWeek( 4);
				cal.setFirstDayOfWeek(Calendar.MONDAY);
				sValue.append(cal.get( Calendar.WEEK_OF_YEAR) );
			}
			break;
			case 'w':
			{
				Calendar cal = dtData.getValue();
				cal.setFirstDayOfWeek(Calendar.MONDAY);
				sValue.append(cal.get( Calendar.WEEK_OF_MONTH) );
			}
			break;
			case 'P': sValue.append( dtData.getHour() < 13 ? "A.M." : "P.M." ); break;
			case 'H': sValue.append( dtData.getHour() ); break;
			case 'h': sValue.append( ( dtData.getHour() > 12 ? dtData.getHour() - 12 : dtData.getHour() ) ); break;
			case 'm': 
			{
				sValue.append( dtData.getMinute() );
				if( sValue.length() < 2)
					sValue.insert( 0, '0' );
			}
			break;
			case 's':
			{
				sValue.append( dtData.getSecond() );
				if( sValue.length() < 2)
					sValue.insert( 0, '0' );
			}
			break;
			case 'f':
			{
				sValue.append( (int)(dtData.getPartSecond() * 1000 ));
			}
			break;
			case 'z': sValue.append( "GMT" );
			case 'Z':
			{
				if( dtData.hasTimezone() != DateTime.TZ_MISSING)
				{
					StringBuffer hour = new StringBuffer();
					hour.append( Math.abs(dtData.getTimezoneOffset() / 60) );
					StringBuffer mins = new StringBuffer();
					mins.append( Math.abs(dtData.getTimezoneOffset() % 60) );
					sValue.append( dtData.getTimezoneOffset() >= 0 ? '+' : '-' );
					sValue.append( hour.length() < 2 ? '0' + hour.toString() : hour.toString() );
					sValue.append(':');
					sValue.append(mins.length() < 2 ? '0' + mins.toString() : mins.toString() );
				}
				else
					sValue.append("+00:00");
			}
			break;
			default:
				throw new IllegalArgumentException( "Unknown component specifier: '" + cComponent + "'");
		}
		return sValue.toString();
	}
	
	private String[] DayNames = {
		"",
		"Sunday",
		"Monday",
		"Tuesday",
		"Wednesday",
		"Thursday",
		"Friday",
		"Saturday"
	};

	private String[] MonthNames = {
		"January",
		"February",
		"March",
		"April",
		"May",
		"June",
		"July",
		"August",
		"September",
		"October",
		"November",
		"December"
	};
	
	protected enum ParseState {
		NORMAL,
		INFIELD
	}
	
	protected enum ArgumentState {
		COMPONENT,
		FORMAT,
		WIDTH
	}
	
	private class FieldInfo {
		public char mComponent;
		public String mModifier;
		public String mWidth;
	}
	
	private String mPattern;
	private char mFieldOpen;
	private char mFieldClose;
}

