////////////////////////////////////////////////////////////////////////
//
// Scanner.java
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

import java.lang.StringBuffer;

public class Scanner	{

	public class State  {
		public int CurrentLine = 0;
		public int LineStart = 0;
		public int Current = 0;

		public boolean equals (Object other)
		{
			State s = (State) other;
			if (s == null)
				return false;
			return Current == s.Current;
		}

		public Object clone () {
			State c = new State ();
			c.LineStart = LineStart;
			c.Current = Current;
			c.CurrentLine = CurrentLine;
			return c;
		}
	}

	State mState = null;
	String mText = null;
	ServiceChars mServiceChars = null;

	public ServiceChars getServiceChars() {
		return mServiceChars;
	}

	public Scanner (String text, ServiceChars serviceChars) {
		this.mText = text;
		this.mServiceChars = serviceChars;
		this.mState = new State ();
	}

	public Scanner ( Scanner scanner, State state) {
		this.mText = scanner.mText;
		this.mServiceChars = scanner.mServiceChars;
		this.mState = state;
	}

	private char getNextChar() {
		if( mState.Current + 1 < mText.length())
			return mText.charAt(mState.Current + 1);
		else
			return 0;
	}

	public State getCurrentState() {
		 return (State) mState.clone();
	}

	void setCurrentState (State s) {
		mState = s;
	}

	public char getCurrentChar() {
		return mText.charAt(mState.Current);
	}

	public boolean isAtEnd() {
		return mState.Current >= mText.length();
	}

	public boolean isAtSeparator (byte separator) {
		return getCurrentChar() == mServiceChars.getSeparator(separator);
	}

	public boolean isAtAnySeparator () {
		return
			isAtSeparator(ServiceChars.ComponentSeparator) ||
			isAtSeparator(ServiceChars.DataElementSeparator) ||
			isAtSeparator(ServiceChars.RepetitionSeparator) ||
			isAtSeparator(ServiceChars.SegmentTerminator) ||
            isAtSeparator(ServiceChars.SubComponentSeparator);
	}

	public int getPosition() {
		return mState.Current;
	}

	public int getLine() {
		return mState.CurrentLine + 1;
	}

	public int getColumn() {
		return mState.Current - mState.LineStart;
	}

	public char rawConsumeChar ()	{
		if (isAtEnd()) return '\0';
		if (getCurrentChar() == '\n' || (getCurrentChar() == '\r' && getNextChar() != '\n'))
		{
			++mState.CurrentLine;
			mState.LineStart = mState.Current + 1;
		}
		return mText.charAt(mState.Current++);
	}

	public boolean isIgnorableWhitespace()	{
		char current = getCurrentChar();
		return (current == '\t' || current == '\r'  || current == '\n') && !isAtAnySeparator();
	}

	public byte getSeparatorType() {
		if (isAtSeparator(ServiceChars.ComponentSeparator))
			return ServiceChars.ComponentSeparator;
		if (isAtSeparator(ServiceChars.DataElementSeparator))
			return ServiceChars.DataElementSeparator;
		if (isAtSeparator(ServiceChars.ReleaseCharacter))
			return ServiceChars.ReleaseCharacter;
		if (isAtSeparator(ServiceChars.RepetitionSeparator))
			return ServiceChars.RepetitionSeparator;
		if (isAtSeparator(ServiceChars.SegmentTerminator))
			return ServiceChars.SegmentTerminator;
        if (isAtSeparator(ServiceChars.SubComponentSeparator))
            return ServiceChars.SubComponentSeparator;
		return ServiceChars.None;
	}

	public void skipWhitespace()	{
		while (!isAtEnd() && Character.isWhitespace(getCurrentChar()) && !isAtAnySeparator())
			rawConsumeChar();
	}


	public boolean moveToNextSignificantChar () {
		while (!isAtEnd())
		{
			if (!isIgnorableWhitespace())
				return true;
			rawConsumeChar();
		}
		return false;
	}



	static final int separatorPrecedence[] = {
			-1, // ServiceChars.None
			0,  // ServiceChars.ComponentSeparator
			2,  // ServiceChars.DataElementSeparator
			4,  // ServiceChars.SegmentTerminator
			-1, // ServiceChars.ReleaseCharacter
			-1, // ServiceChars.DecimalMark
			1,  // ServiceChars.RepetitionSeparator
            3,  // ServiceChars.SubcomponentSeparator
	};

	public StringBuffer consumeString(byte stopAtSeparator, boolean wantResult) {
		StringBuffer bld = new StringBuffer();

		int stopSeparatorPrecedence = separatorPrecedence[(int) stopAtSeparator];

		while (moveToNextSignificantChar())
		{
			byte sc = getSeparatorType();
			if (sc == ServiceChars.ReleaseCharacter)
			{
				rawConsumeChar();
				if (!moveToNextSignificantChar())
					break;
			}
			else
			{
				int foundPrecedence = separatorPrecedence[(int) sc];
				if (foundPrecedence >= stopSeparatorPrecedence)
					break;
			}

			if (wantResult)
				bld.append(getCurrentChar());
			rawConsumeChar();
		}
		return bld;
	}

	public boolean readUNA () {
		mServiceChars.setComponentSeparator(rawConsumeChar());
		mServiceChars.setDataElementSeparator(rawConsumeChar());
		mServiceChars.setDecimalSeparator(rawConsumeChar());
		mServiceChars.setReleaseCharacter(rawConsumeChar());
		mServiceChars.setRepetitionSeparator(rawConsumeChar());
		mServiceChars.setSegmentTerminator(rawConsumeChar());
		// space means no release character at all
		if (mServiceChars.getReleaseCharacter() == ' ')
			mServiceChars.setReleaseCharacter('\0');

		// space means an old syntax without repeating elements is in use
		if (mServiceChars.getRepetitionSeparator() == ' ')
			mServiceChars.setRepetitionSeparator('\0');
		mServiceChars.setSubComponentSeparator( '\0');
		return true;
	}

	public boolean readISASegmentStart () {
		mServiceChars.setDataElementSeparator(getCurrentChar());
		mServiceChars.setComponentSeparator('\0');
		mServiceChars.setDecimalSeparator('.');
		mServiceChars.setReleaseCharacter('\0');
		mServiceChars.setRepetitionSeparator('\0');
		mServiceChars.setSegmentTerminator('\0');
		mServiceChars.setSubComponentSeparator( '\0');
		return true;
	}

	public boolean readISASegmentEnd () {
		mServiceChars.setSegmentTerminator(getCurrentChar());
		return true;
	}

	public String forwardToSegmentTerminator () {
		return consumeString(ServiceChars.SegmentTerminator, false).toString();
	}
}
