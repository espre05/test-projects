package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CThrottlerMainJava
{
  protected static String nl;
  public static synchronized CThrottlerMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CThrottlerMainJava result = new CThrottlerMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.throttle(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL + "\t\t\t.timePeriodMillis(";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = NL + "\t\t\t.asyncDelayed()";
  protected final String TEXT_6 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	Integer messageCount=10;
	if(!("".equals(ElementParameterParser.getValue(node, "__MESSAGE_COUNT__"))))
		 messageCount = Integer.parseInt(ElementParameterParser.getValue(node, "__MESSAGE_COUNT__"));
		
	Integer timePeriod = 1000;
	if(!("".equals(ElementParameterParser.getValue(node, "__TIME_PERIOD__"))))
		timePeriod = Integer.parseInt(ElementParameterParser.getValue(node, "__TIME_PERIOD__"));
	
	String setTimePeriod = ElementParameterParser.getValue(node, "__SET_TIME_PERIOD__");
	String useAsyncDelaying = ElementParameterParser.getValue(node, "__USE_ASYNC_DELAYING__");
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {
		if(messageCount!=null) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(messageCount);
    stringBuffer.append(TEXT_2);
    
		}
		if("true".equals(setTimePeriod)) {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(timePeriod);
    stringBuffer.append(TEXT_4);
    
		}
		if("true".equals(useAsyncDelaying)) {

    stringBuffer.append(TEXT_5);
    
		}
	}

    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
