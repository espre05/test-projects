// TextTimeFormatter.java
// This file contains generated code and will be overwritten when you rerun code generation.

package com.altova.text;

import com.altova.CoreTypes;
import com.altova.types.*;
import com.altova.typeinfo.ValueFormatter;

public class TextTimeFormatter extends TextFormatter
{
	public String format(DateTime dt)
	{
		return dt.toTimeString("0.0######");
	}
}
