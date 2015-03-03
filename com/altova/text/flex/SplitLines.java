////////////////////////////////////////////////////////////////////////
//
// SplitLines.java
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

package com.altova.text.flex;

public class SplitLines extends Splitter {
	private int nLines;
	private boolean removeDelimiter;
	
	public SplitLines(int nLines, boolean removeDelimiter) {
		this.nLines = nLines;
		this.removeDelimiter = removeDelimiter;
	}
	public SplitLines(int nLines) {
		this(nLines,  false);
	}
	
	public Range split(Range range) {
		String content = range.getContent();
		Range result = new Range(content, range.start, range.start);
		int p = 0;
		
		if (nLines >= 0) {
			// count from top
			p = result.end;
			for (int nLinesLeft = nLines; nLinesLeft > 0 && p != range.end; ++p) {
				if (content.charAt(p) == CR || content.charAt(p) == LF) {
					if (content.charAt(p) == CR && p != range.end-1 && content.charAt(p+1) == LF)
						++p;
					--nLinesLeft;
				}
			}
		} else {
			// count from bottom
			result.end = range.end;
			p = result.end;
			int nLinesLeft = -nLines;
			if (result.isValid() && (content.charAt(p-1) == CR || content.charAt(p-1) == LF))
				nLinesLeft++;

			for (; p > range.start; --p)
			{
				if (content.charAt(p-1) == CR || content.charAt(p-1) == LF)
				{
					if (--nLinesLeft == 0)
						break;
					if (nLinesLeft > 0 && content.charAt(p-1) == LF && p > range.start+1 && content.charAt(p-2) == CR)
						--p;
				}
			}
		}
		result.end = p;
		range.start = result.end;
		if (removeDelimiter) {
			if (result.endsWith(LF))
				--result.end;
			if (result.endsWith(CR))
				--result.end;
		}
		return result;
	}
	
	public void appendDelimiter(Appender output) {
		if (removeDelimiter)
			output.appendText("\r\n");
	}

	public void prepareUpper(StringBuffer buffer) 
	{ 
		if (nLines >= 0)
		{
			makeBufferNLines(buffer, nLines);
		}			
	}
	
	public void prepareLower(StringBuffer buffer) 
	{ 	
		if (nLines < 0)
		{
			makeBufferNLines(buffer, -nLines);
		}
	}

	static void makeBufferNLines(StringBuffer buffer, int n)
	{
		int p = 0;
		for (int nLinesLeft = n; nLinesLeft > 0;)
		{
			if (p < buffer.length())
			{
				if (buffer.charAt(p) == CR || buffer.charAt(p) == LF)
				{
					if (buffer.charAt(p) == CR && p != buffer.length()-1 && buffer.charAt(p+1) == LF)
						++p;
					--nLinesLeft;
					if (nLinesLeft == 0) // remove anything yet following
					{
						buffer.delete(p+1, buffer.length());
						break;
					}
				}
				++p;
			}
			else
			{
				buffer.append(CR);
				buffer.append(LF);
				--nLinesLeft;
			}
		}								
	}
		
}
