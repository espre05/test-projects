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

public class CMailMainJava
{
  protected static String nl;
  public static synchronized CMailMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CMailMainJava result = new CMailMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.to(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL + "\t\tfrom(";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    INode node = (INode) codeGenArgument.getArgument();
   	String cid = node.getUniqueName();
//    http://jira.talendforge.org/browse/TESB-5241
	/*
   	 * change to use label + unique to make it unique but readable
   	 */
//  	IElementParameter param = node.getElementParameter("LABEL");
//  	if(param != null && !"__UNIQUE_NAME__".equals(param.getValue())){
//  		cid = (String)param.getValue()+"_"+cid;	
//  	}

	CamelEndpointBuilder builder = CamelEndpointBuilder.getBuilder();
	String protocols = ElementParameterParser.getValue(node, "__PROTOCOLS__");
	builder.setComponent(protocols);

	String host = ElementParameterParser.getValue(node, "__HOST__");
	builder.setName(host);

	String port = ElementParameterParser.getValue(node, "__PORT__");
	if (!"\"\"".equals(port)) {
		builder.setName(host + "+\":\"+" + port);
	}

	String userName = ElementParameterParser.getValue(node, "__USERNAME__");
	builder.addParam("username", userName);
	
	String password = ElementParameterParser.getValue(node, "__PASSWORD__");
	builder.addParam("password", "decryptedPassword_" + cid);

	String subject = ElementParameterParser.getValue(node, "__SUBJECT__");
	builder.addParam("subject", subject);

	String from = ElementParameterParser.getValue(node, "__FROM__");
	builder.addParam("from", from);

	String to = ElementParameterParser.getValue(node, "__TO__");
	builder.addParam("to", to);

	String contentType = ElementParameterParser.getValue(node, "__CONTENTTYPE__");
	builder.addParam("contentType", contentType);

	String cc = ElementParameterParser.getValue(node, "__CC__");
	builder.addParam("CC", cc);

	String bcc = ElementParameterParser.getValue(node, "__BCC__");
	builder.addParam("BCC", bcc);

	List<Map<String, String>> formatValues = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__ARGUMENTS__");
	for (Map<String, String> map : formatValues) {
		String argName = map.get("NAME").trim();
		String argValue = map.get("VALUE").trim();
		builder.addParam("\" + " + argName + "\"+", argValue);
	}

    String uri = builder.build();
	String endpointVar = "mail_" + node.getUniqueName();
    
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
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
