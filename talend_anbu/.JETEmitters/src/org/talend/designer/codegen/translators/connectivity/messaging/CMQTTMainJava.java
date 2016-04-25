package org.talend.designer.codegen.translators.connectivity.messaging;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.CamelEndpointBuilder;
import org.talend.designer.codegen.config.NodeParamsHelper;
import java.util.List;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CMQTTMainJava
{
  protected static String nl;
  public static synchronized CMQTTMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CMQTTMainJava result = new CMQTTMainJava();
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
   	NodeParamsHelper helper = new NodeParamsHelper(node);
//  http://jira.talendforge.org/browse/TESB-5241
	/*
   	 * change to use label + unique to make it unique but readable
   	 */
//	IElementParameter param = node.getElementParameter("LABEL");
//	if(param != null && !"__UNIQUE_NAME__".equals(param.getValue())){
//		cid = (String)param.getValue() +"_"+ cid;	
//	}

    
    CamelEndpointBuilder eb = CamelEndpointBuilder.getBuilder();

	String factoryCompName =  helper.getStringParam("__CONNECTION_FACTORY_CONFIGURATION__");
	eb.setComponent(factoryCompName.replace("_", ""));

	eb.setName("\""+cid+"\"");

	String topicName = helper.getStringParam("__TOPIC_NAME__");
	if(!node.getIncomingConnections().isEmpty()) {
		eb.addParam("publishTopicName", topicName);
	} else {
		eb.addParam("subscribeTopicName", topicName);
	}

	List<Map<String, String>> tableValues = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node, "__URI_OPTIONS__");
	eb.addParams(tableValues);
	String uri = eb.build();
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
