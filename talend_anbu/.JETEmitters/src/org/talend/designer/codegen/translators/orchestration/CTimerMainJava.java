package org.talend.designer.codegen.translators.orchestration;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.CamelEndpointBuilder;
import org.talend.core.model.components.IComponent;
import java.util.List;
import java.util.Map;

public class CTimerMainJava
{
  protected static String nl;
  public static synchronized CTimerMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CTimerMainJava result = new CTimerMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.to(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL + "\t\tfrom(";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
   	String cid = node.getUniqueName();
   	CamelEndpointBuilder builder = CamelEndpointBuilder.getBuilder();
//  http://jira.talendforge.org/browse/TESB-5241
	/*
   	 * change to use label + unique to make it unique but readable
   	 */
//	IElementParameter param = node.getElementParameter("LABEL");
//	if(param != null && !"__UNIQUE_NAME__".equals(param.getValue())){
//		cid = (String)param.getValue()+"_"+cid;	
//	}
	builder.setComponent("timer");
	builder.useDoubleSlash(false);
	builder.setName("\"" + node.getUniqueName() + "\"");
	
	String period = ElementParameterParser.getValue(node, "__PERIOD__");
	builder.addParamIfNotDefault("period", period, "1000");
	
	String repeat = ElementParameterParser.getValue(node, "__REPEATCOUNT__");
	builder.addParamIfNotDefault("repeatCount", repeat, "0");
	
	String delay = ElementParameterParser.getValue(node, "__DELAY__");
	builder.addParam("delay", delay);
	
	String fixedRate = ElementParameterParser.getValue(node, "__FIXEDRATE__");
	if("true".equals(fixedRate)){
		builder.addParam("fixedRate", "\"" + fixedRate + "\"");
	}
	
	String daemon = ElementParameterParser.getValue(node, "__DAEMON__");
	if("false".equals(daemon)){
		builder.addParam("daemon", "\"" + daemon + "\"");
	}
	
	String setTime = ElementParameterParser.getValue(node, "__SET_TIME__");
	if("true".equals(setTime)){
		String time = ElementParameterParser.getValue(node, "__TIME__");
		builder.addParam("time", time);
	}
	
	String uri = builder.build();
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_2);
    
	} else {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_4);
    
	}

    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
