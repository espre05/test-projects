////////////////////////////////////////////////////////////////////////
//
// Condition.java
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

public class Condition extends Command {
	
	private String value;
	private int type;
	public static final int ContentStartWith = 0;
	public static final int ContentContains = 1;
	
	public Condition(String name, int type, String value) {
		super(name);
		this.value = value;
		this.type = type; 
	}
	
	public boolean evaluate(DocumentReader doc) {
		Range range = doc.getRange();
		if (type == ContentStartWith)
			return range.startsWith(value);
		else if (type == ContentContains)
			return range.contains(value);
		
		else 
			return false;
	}
	
	public boolean readText(DocumentReader doc) {
		if (!evaluate(doc))
			return false;
		return super.readText(doc);
	}
	
	public boolean writeText(DocumentWriter doc) {
		return super.writeText(doc);
	}
}
