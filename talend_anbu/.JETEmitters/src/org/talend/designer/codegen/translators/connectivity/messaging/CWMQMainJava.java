package org.talend.designer.codegen.translators.connectivity.messaging;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.CamelEndpointBuilder;
import java.util.List;
import java.util.Map;

public class CWMQMainJava
{
  protected static String nl;
  public static synchronized CWMQMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CWMQMainJava result = new CWMQMainJava();
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
    INode node = (INode) codeGenArgument.getArgument();
   	String cid = node.getUniqueName();
	CamelEndpointBuilder builder = CamelEndpointBuilder.getBuilder();

	String factoryName = ElementParameterParser.getValue(node, "__CONNECTION_FACTORY_CONFIGURATION__");
	String type = ElementParameterParser.getValue(node, "__TYPE__");
	String destination = ElementParameterParser.getValue(node, "__DESTINATION__").trim();

	//builder.setComponent(name.replace("_", ""));
	builder.setComponent("wmq");
	builder.useDoubleSlash(false);
	builder.setName(destination);
	
	String argType = "destinationType";
	String argTopic = "topic";
	
	
	if (type.equals(argTopic)) {
		builder.addParam(argType, "\""+type+"\"");
	}
	for (INode pNode : node.getProcess().getNodesOfType("cMQConnectionFactory")) {
			if (pNode.getUniqueName().trim().equals(factoryName)) {
                    String wmqnName = ElementParameterParser.getValue(pNode, "__WMQ_N_NAME__");
                    String wmqnServer = ElementParameterParser.getValue(pNode, "__WMQ_N_SEVER__");
                    String wmqnPort = ElementParameterParser.getValue(pNode, "__WMQ_N_PORT__");
                    String wmqnChannel = ElementParameterParser.getValue(pNode, "__WMQ_N_CHANNEL__");
					if (!wmqnName.trim().equals("")) {
						builder.addParam("queueManagerName", wmqnName);					
					}
                    if (!wmqnServer.trim().equals("") && !wmqnPort.trim().equals("") && !wmqnChannel.trim().equals("")) {
						builder.addParam("queueManagerPort", wmqnPort);										
						builder.addParam("queueManagerHostname", wmqnServer);					
						builder.addParam("queueManagerChannel ", wmqnChannel);										
					}
					if ("true".equals(ElementParameterParser.getValue(pNode, "__WMQ_N_AUTH__"))) {
						builder.addParam("queueManagerUserID", ElementParameterParser.getValue(pNode, "__WMQ_N_USERNAME__"));
						builder.addParam("queueManagerPassword", ElementParameterParser.getValue(pNode, "__WMQ_N_PASSWORD__"));					
						builder.addParam("queueManagerCCSID ", ElementParameterParser.getValue(pNode, "__WMQ_N_CCSID__"));										
					}
            }
		}
	

	List<Map<String, String>> tableValues = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__URI_OPTIONS__");
	for (Map<String, String> map : tableValues) {
		String argName = map.get("NAME").trim();
		String argValue = map.get("VALUE").trim();
		if(argName.startsWith("\"") && argName.endsWith("\"") && argName.length() >= 2) {
			argName = argName.substring(1, argName.length() - 1);
		}
		builder.addParam(argName, argValue);
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
