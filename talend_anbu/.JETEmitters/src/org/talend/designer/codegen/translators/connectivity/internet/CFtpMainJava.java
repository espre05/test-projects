package org.talend.designer.codegen.translators.connectivity.internet;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.CamelEndpointBuilder;
import java.util.List;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CFtpMainJava
{
  protected static String nl;
  public static synchronized CFtpMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CFtpMainJava result = new CFtpMainJava();
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
//  http://jira.talendforge.org/browse/TESB-5241
	/*
   	 * change to use label + unique to make it unique but readable
   	 */
//	IElementParameter param = node.getElementParameter("LABEL");
//	if(param != null && !"__UNIQUE_NAME__".equals(param.getValue())){
//		cid = (String)param.getValue()+"_"+cid;	
//	}

	String type = ElementParameterParser.getValue(node, "__TYPE__");
	String username = ElementParameterParser.getValue(node, "__USERNAME__");
	String server = ElementParameterParser.getValue(node, "__SERVER__");
	String port = ElementParameterParser.getValue(node, "__PORT__");
	String password = ElementParameterParser.getValue(node, "__PASSWORD__");
	String directory = ElementParameterParser.getValue(node, "__DIRECTORY__");

	builder.setComponent(type);
	String host = "";
	if (username != null && !username.isEmpty()) {
		host = username + " + \"@\" + ";
	}
	host = host + server;
	if (port != null && !port.trim().isEmpty()) {
		host = host + " + \":\" + " + port;
	}
	host = host + "+ \"/\" + " + directory;
	builder.setName(host);
	builder.addParam("password", "decryptedPassword_" + cid);

	List<Map<String, String>> tableValues = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__ADVARGUMENTS__");
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
