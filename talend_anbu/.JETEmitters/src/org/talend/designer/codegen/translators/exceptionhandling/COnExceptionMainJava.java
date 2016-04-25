package org.talend.designer.codegen.translators.exceptionhandling;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import java.util.Map;

public class COnExceptionMainJava
{
  protected static String nl;
  public static synchronized COnExceptionMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    COnExceptionMainJava result = new COnExceptionMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tonException(";
  protected final String TEXT_2 = NL + "\t\t\t\t,";
  protected final String TEXT_3 = NL + "\t\t\t";
  protected final String TEXT_4 = ".class";
  protected final String TEXT_5 = NL + "\t)" + NL + "\t";
  protected final String TEXT_6 = NL + "\t\t.useOriginalMessage()";
  protected final String TEXT_7 = NL + "\t\t.handled(true)";
  protected final String TEXT_8 = NL + "\t\t.continued(true)";
  protected final String TEXT_9 = NL + "\t\t\t.maximumRedeliveries(";
  protected final String TEXT_10 = ")";
  protected final String TEXT_11 = NL + "\t\t.asyncDelayedRedelivery()";
  protected final String TEXT_12 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	List<Map<String, String>> exceptions = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__EXCEPTIONS__");

	boolean nonBlockingRedelivery = "true".equals(ElementParameterParser.getValue(node, "__ASYNC_REDELIVERY__"));
	
	boolean setRedeliveringMax = "true".equals(ElementParameterParser.getValue(node, "__SET_MAX_REDELIVERY__"));
	String maxRedelivering = ElementParameterParser.getValue(node, "__MAX_REDELIVERY__");	
	
	boolean handle = "true".equals(ElementParameterParser.getValue(node, "__HANDLE__"));
	boolean continued = "true".equals(ElementParameterParser.getValue(node, "__CONTINUE__"));
	boolean none = "true".equals(ElementParameterParser.getValue(node, "__NONE__"));
	
	boolean useOriginalBody = "true".equals(ElementParameterParser.getValue(node, "__USE_ORIGINAL_MESSAGE__"));
	

    stringBuffer.append(TEXT_1);
    
	boolean isFirstException = true;
	if(exceptions.size()>0) {
		for(Map<String, String> anException : exceptions) {
			if(!isFirstException) {

    stringBuffer.append(TEXT_2);
    
			}
			isFirstException=false;

    stringBuffer.append(TEXT_3);
    stringBuffer.append(anException.get("EXCEPTION"));
    stringBuffer.append(TEXT_4);
    
		}
	}

    stringBuffer.append(TEXT_5);
    
	if(useOriginalBody) {

    stringBuffer.append(TEXT_6);
    
	}
	if(handle) {

    stringBuffer.append(TEXT_7);
    
	}
	if(continued) {

    stringBuffer.append(TEXT_8);
    
	}

		if(setRedeliveringMax) {
		if(null!=maxRedelivering && !("".equals(maxRedelivering)) && maxRedelivering.length()>0) {

    stringBuffer.append(TEXT_9);
    stringBuffer.append(maxRedelivering);
    stringBuffer.append(TEXT_10);
    
		}
	}
	if(nonBlockingRedelivery) {

    stringBuffer.append(TEXT_11);
    
	}

    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
}
