////////////////////////////////////////////////////////////////////////
//
// DataTypeValidatorDecimal.java
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

package com.altova.text.edi;

import com.altova.text.ITextNode;

public class  DataTypeValidatorDecimal extends DataTypeValidator {

	int mImplicitDecimals;

	public DataTypeValidatorDecimal (int minLength, int maxLength, int implicitDecimals) {
		super (minLength, maxLength, null);
		mImplicitDecimals = implicitDecimals;
	}

	public boolean makeValidOnRead (StringBuffer s, Parser.Context context, Scanner.State beforeRead) {

		char dec = context.getScanner().getServiceChars().getDecimalSeparator();

		int effLen = effectiveLength(s, dec);
		validateLength(effLen, s.toString(), context, beforeRead);

		if (mImplicitDecimals > 0)
		{
			if (effLen < mImplicitDecimals)
				for (int i=0; i< mImplicitDecimals - effLen; ++i)
					s.insert(0, '0');

			s.insert(s.length()- mImplicitDecimals, '.');
			return true;
		}

		for (int i=0; i<s.length(); ++i)
			if (s.charAt(i) == dec || s.charAt(i) == ',')
				s.setCharAt(i, '.');
		if (s.charAt(0) == '.')
			s.insert(0, '0');
		return true;
	}

    public boolean makeValidOnWrite (StringBuffer s, ITextNode node, Writer writer, boolean esc) {
        return makeValidOnWrite(s, node, writer);
    }
    
	public boolean makeValidOnWrite (StringBuffer s, ITextNode node, Writer writer) {
		if (mImplicitDecimals > 0)
		{
			java.math.BigDecimal d = new java.math.BigDecimal(s.toString());
			d = d.setScale(mImplicitDecimals, java.math.BigDecimal.ROUND_HALF_UP);
			s.setLength(0);
			s.append(d.toString());
			s.deleteCharAt(s.indexOf("."));
		}
		else
		{
			int effLen = effectiveLength(s, '.'); 
			if (effLen > getMaxLength())
			{
				if (s.indexOf(".") != -1 && s.indexOf(".") < getMaxLength())
				{
					java.math.BigDecimal d = new java.math.BigDecimal(s.toString());
					int newScale = d.scale() - (effLen - getMaxLength());
					d = d.setScale(newScale, java.math.BigDecimal.ROUND_HALF_UP);
					s.setLength(0);
					s.append(d.toString());
				}
			}
		}

		// remove trailing decimal zeros
		if (s.indexOf(".") != -1)
		{
			while (s.charAt(s.length()-1) == '0')
				s.setLength(s.length()-1);
			if (s.charAt(s.length()-1) == '.')
				s.setLength(s.length()-1);
		}

		int toPad = getMinLength() - effectiveLength(s, writer.getServiceChars().getDecimalSeparator());

		if (toPad >0)
		{
			String spad = new String();
			for (int i=0; i< toPad; ++i)
				spad += '0';
			if (s.length() > 0 && s.charAt(0) == '-')
				s.insert(1, spad);
			else
				s.insert(0, spad);
		}

		if (s.indexOf(".") != -1)
			s.setCharAt(s.indexOf("."), writer.getServiceChars().getDecimalSeparator());
		
		int effLen = effectiveLength(s, '.');
		//report error/warning
		validateLength(effLen, s.toString(), node, writer);
		
		return true;
	}

	int effectiveLength(StringBuffer s, char decimalMark)
	{
		int len = s.length();

		for (int i=0; i< s.length(); ++i)
			if (s.charAt(i) == decimalMark || s.charAt(i) == '.' || s.charAt(i) == ',' || s.charAt(i) == '-')
				--len;

		return len;
	}
}
