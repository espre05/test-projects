////////////////////////////////////////////////////////////////////////
//
// StructureItem.java
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
import com.altova.text.ITextNodeList;
import com.altova.text.NullTextNode;
import java.io.IOException;

public abstract class StructureItem
{
	protected byte mNodeClass;
	protected String mName;
	protected Particle[] mChildren;

	public byte getNodeClass() {
		return mNodeClass;
	}

	protected boolean readChildren (Parser.Context context, byte separator)
	{
		Scanner scanner = context.getScanner();

		for (int childIndex = 0; childIndex != getChildCount(); ++childIndex)
		{
            Particle currentParticle = mChildren[childIndex];

            if (childIndex != 0 && separator != ServiceChars.None && scanner.isAtSeparator(separator))
                scanner.rawConsumeChar();

            int toRead = currentParticle.getMergedEntries();
            byte repSeparator = separator;
            if (toRead == 1) // no merged entry
            {
                toRead = Integer.MAX_VALUE; // try to read as much as possible -> errors are reported anyways
                if (mNodeClass == ITextNode.Segment)
                {
                    repSeparator = ServiceChars.RepetitionSeparator;
                    if (scanner.getServiceChars().getRepetitionSeparator() == '\0')
                    {
                        toRead = 1;
                        repSeparator = ServiceChars.None;
                    }
                    else if( currentParticle.getName().equals( "MSH-1") &&
                            context.getParser().getEDIKind() == EDISettings.EDIHL7)
                    {
                        toRead = 1;
                        repSeparator = ServiceChars.None;
                    }
                }
                else if (currentParticle.getMaxOccurs() == 1)
                {
                    toRead = 1;
                    repSeparator = ServiceChars.None;
                }
            }
			
            switch( context.mParticle.getNode().getNodeClass() )
            {
            case ITextNode.Segment: context.mParser.incrementDataElementPos(); break;
            case ITextNode.Composite: context.mParser.incrementComponentDataElementPos(); break;
            }

            Parser.Context childContext = context.newContext(context, currentParticle);
            for (int count = 0; count < toRead && !scanner.isAtEnd(); ++count)
            {
				childContext.setOccurence( count + 1 );
                // consume the proper separator. otherwise the children won't find anything to read and fail anyways.
                if (count != 0 && repSeparator != ServiceChars.None && scanner.isAtSeparator(repSeparator))
                    scanner.rawConsumeChar ();

                Scanner.State preservedState = scanner.getCurrentState();
                if (!currentParticle.getNode().read (childContext))
                {
                    //scanner.setCurrentState(preservedState);
                    if (count >= currentParticle.getMinOccurs())
                    {
                        if (count >= currentParticle.getMergedEntries())
                            break; // enough read
                    }
                    else
                    {
                        if (currentParticle.getNode().getNodeClass() == ITextNode.Segment)
                        {
                            childContext.handleError( 
                            	Parser.ErrorType.MissingSegment,
                            	ErrorMessages.GetMissingSegmentMessage( currentParticle.getNode().getName() ),
                            	new ErrorPosition( preservedState )
                            );
                        }
                        else
                        if (currentParticle.getNode().getNodeClass() == ITextNode.Group)
                        {
                            childContext.handleError(
                            	Parser.ErrorType.MissingGroup,
                            	ErrorMessages.GetMissingGroupMessage( currentParticle.getNode().getName() ),
                            	new ErrorPosition( preservedState )
                            );
                        }
                        else
                        	childContext.handleError(
                        		Parser.ErrorType.MissingFieldOrComposite,
                        		ErrorMessages.GetMissingFieldOrCompositeMessage( currentParticle.getNode().getName() ),
                        		new ErrorPosition( preservedState )
                        	);
                    }
                }

                if (currentParticle.getMergedEntries() == 1 && count >= currentParticle.getMaxOccurs())
                    childContext.handleError(
                    	Parser.ErrorType.ExtraRepeat, 
                    	ErrorMessages.GetExtraRepeatMessage( currentParticle.getNode().getName() ),
                    	new ErrorPosition( preservedState )
                    );
                if (repSeparator != ServiceChars.None)
                {
                	String sExtra = scanner.consumeString(repSeparator, true).toString();
                    if ( sExtra.length() > 0)
                        childContext.handleError(
                        	Parser.ErrorType.ExtraData,
                        	ErrorMessages.GetExtraDataMessage(getName(), sExtra),
                        	new ErrorPosition( preservedState )
                        );
                }
            }
		}
		return true;
	}

	protected void writeChildren (Writer writer, ITextNode node, byte separator) throws IOException {
		int structChildren = getChildCount();

		for ( int childPos = 0; childPos < structChildren; ++childPos)
		{
			Particle currentParticle = mChildren[childPos];
			int nToWrite = currentParticle.getMergedEntries();
			byte actSeparator = separator;
			if (currentParticle.getMaxOccurs() > 1 || getNodeClass() == ITextNode.Group)
			{
				//	be tolerant for maxOccurs overruns, but don't eat all nodes for non-repeating items:
				nToWrite = Integer.MAX_VALUE;

				if (getNodeClass() == ITextNode.Segment)
				{
					actSeparator = ServiceChars.RepetitionSeparator;
					if (writer.getServiceChars().getSeparator(actSeparator) == '\0')
						nToWrite = 1; // no separator -> need to suppress extra repetitions
					if ( writer.getEDIKind() == EDISettings.EDIFixed )
						nToWrite = 1; //write exactly 1 data segment in fixed configs
				}
			}

			ITextNodeList children = node.getChildren().filterByName(currentParticle.getNameOverride());

			if( children.size() > java.lang.Math.max( 
					currentParticle.mMaxOccurs,
					currentParticle.mMergedEntries) )
			{
				//report error/warning
				writer.handleError(
					node,
					Parser.ErrorType.ExtraRepeat,
					ErrorMessages.GetExtraRepeatMessage( currentParticle.getName() ) 
				);
			}

			for (int nCount = 0; nCount < nToWrite; ++nCount)
			{
				if (nCount < children.size())
					currentParticle.getNode().write(writer, children.getAt(nCount));
				else
				{
					// if it's a fixed config, always write <Data> elements to keep the format
					if( writer.getEDIKind() == EDISettings.EDIFixed  && getNodeClass() == ITextNode.Segment )
					{
						currentParticle.getNode().write(writer, NullTextNode.getInstance());
					}
					if (nCount < currentParticle.getMinOccurs())
					{
						//report error/warning
						writer.handleError(
							node,
							Parser.ErrorType.MissingFieldOrComposite,
							ErrorMessages.GetMissingFieldOrCompositeMessage(currentParticle.getName())
						);
						if (getNodeClass() == ITextNode.Group)
							currentParticle.getNode().write(writer, NullTextNode.getInstance());
					}
					else
						if (nCount >= currentParticle.getMergedEntries())
							break;
				}
                if( !(currentParticle.getName().equals( "MSH-1") &&
                    writer.getEDIKind() == EDISettings.EDIHL7))
                    writer.addSeparator(actSeparator);
			}
			if (actSeparator != separator)
			{
				writer.clearPendingSeparators(actSeparator);
                writer.addSeparator(separator);
			}
		}
		writer.clearPendingSeparators(separator);
	}


	protected boolean isAtGroup (Parser.Context context)
	{
		StructureItem node = this;
		Particle particle = node.child(0);
		while (particle.getNode().getNodeClass() == ITextNode.Group)
		{
			node = particle.getNode();
			particle = node.child(0);
		}

		// for the special Interchange and Envelope groups different behavior is needed.
		for (int nIndex = 0;nIndex < node.getChildCount(); ++nIndex)
		{
			particle = node.child(nIndex);
			
			StructureItem child = particle.getNode();
			if (child.getNodeClass() == ITextNode.Segment)
			{
				// try to find out whether this segment appears here.
				Scanner.State preservedState = context.getScanner().getCurrentState();
				boolean result = child.isSegmentStarting (context);
				context.getScanner().setCurrentState(preservedState);
				if (result)
					return true;
			}
			else // shouldn't be anything else.
			{
				if (child.isAtGroup (context))
					return true;
			}

			// the segment is mandatory -> the group cannot occur here.
			if (particle.getMinOccurs() > 0)
				return false;
		}
		// this could happen in cases where groups have no indicator segment.
		return false;
	}

	protected String readSegmentTag (Parser.Context context)
	{
		StringBuffer sRet = new StringBuffer();
		Scanner scanner = context.getScanner();
		scanner.skipWhitespace(); // skip whitespace before/between segments

		if (mName.equals("UNA") || mName.equals("ISA") || mName.equals("MSH") ||
			context.getParser().getEDIKind() == EDISettings.EDIFixed)
		{
			// read character by character because separators are not known yet
			for (int i = 0; i < mName.length(); ++i)
				sRet.append( scanner.rawConsumeChar() );
		}
		else
			sRet.append( scanner.consumeString(ServiceChars.ComponentSeparator, true).toString() );
		
		return sRet.toString();	
	}

	protected boolean isSegmentStarting (Parser.Context context)
	{
		return mName.equals(readSegmentTag( context));
	}

	public abstract boolean read(Parser.Context context);

	public abstract void write(Writer writer, ITextNode node) throws IOException;

	public String getName() {
		return mName;
	}

	public Particle child(int i) {
		return mChildren[i];
	}

	public int getChildCount() {
		return (mChildren == null) ? 0:mChildren.length;
	}

	protected StructureItem (String name, byte cls) {
		this.mName = name;
		this.mChildren = null;
		this.mNodeClass = cls;
	}

	protected StructureItem (String name, byte cls, Particle[] children) {
		this.mName = name;
		this.mChildren = children;
		this.mNodeClass = cls;
	}
}
