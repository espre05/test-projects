package org.talend.designer.codegen.translators.connectivity.services;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.NodeParamsHelper;
import org.talend.designer.codegen.config.CamelEndpointBuilder;
import java.util.List;

public class CCXFRSMainJava
{
  protected static String nl;
  public static synchronized CCXFRSMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CCXFRSMainJava result = new CCXFRSMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\tfrom(";
  protected final String TEXT_2 = ")" + NL + "\t\t.process(new org.apache.camel.Processor() {" + NL + "\t\t\t\tpublic void process(org.apache.camel.Exchange exchange) throws Exception {" + NL + "\t\t\t\t\torg.apache.camel.Message inMessage = exchange.getIn();" + NL + "\t\t\t\t\tinMessage.setHeader(\"http_query\"," + NL + "\t\t\t\t\t\torg.apache.cxf.jaxrs.utils.JAXRSUtils.getStructuredParams((String) inMessage.getHeader(org.apache.camel.Exchange.HTTP_QUERY), \"&\", false, false));" + NL + "\t\t\t\t}" + NL + "\t\t\t})";
  protected final String TEXT_3 = NL + "\t\t.setHeader(org.apache.camel.Exchange.HTTP_PATH, ";
  protected final String TEXT_4 = ")" + NL + "\t\t.setHeader(org.apache.camel.Exchange.HTTP_METHOD, constant(\"";
  protected final String TEXT_5 = "\"))";
  protected final String TEXT_6 = NL + "\t\t.setHeader(org.apache.camel.Exchange.ACCEPT_CONTENT_TYPE, constant(\"";
  protected final String TEXT_7 = "\"))" + NL + "\t\t.setHeader(org.apache.camel.component.cxf.common.message.CxfConstants.CAMEL_CXF_RS_RESPONSE_CLASS, constant(";
  protected final String TEXT_8 = "))";
  protected final String TEXT_9 = NL + "\t\t.setHeader(org.apache.camel.Exchange.CONTENT_TYPE, constant(\"";
  protected final String TEXT_10 = "\"))";
  protected final String TEXT_11 = NL + "\t\t.setHeader(org.apache.camel.component.cxf.common.message.CxfConstants.CAMEL_CXF_RS_USING_HTTP_API, constant(Boolean.FALSE))" + NL + "\t\t.setHeader(org.apache.camel.component.cxf.common.message.CxfConstants.OPERATION_NAME, ";
  protected final String TEXT_12 = ")";
  protected final String TEXT_13 = NL + "\t\t.process(new org.apache.camel.Processor() {" + NL + "\t\t\t\tpublic void process(org.apache.camel.Exchange exchange) throws Exception {" + NL + "\t\t\t\t\tcorrelationIDCallbackHandler_";
  protected final String TEXT_14 = ".setCorrelationId(simple(";
  protected final String TEXT_15 = ").evaluate(exchange, String.class));" + NL + "\t\t\t\t}" + NL + "\t\t\t})";
  protected final String TEXT_16 = NL + "\t\t.inOut(";
  protected final String TEXT_17 = ")";
  protected final String TEXT_18 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();

	NodeParamsHelper paramsHelper = new NodeParamsHelper(node);
	CamelEndpointBuilder builder = CamelEndpointBuilder.getBuilder();

	builder.setComponent("cxfrs://bean");
	builder.setName("\"" + node.getUniqueName() + "\"");

    builder.addParam("loggingFeatureEnabled", "\"" + (String) ElementParameterParser.getValue(node, "__LOG_MESSAGES__") + "\"");

    java.util.List<java.util.Map<String, String>> tableValues = (java.util.List<java.util.Map<String, String>>) ElementParameterParser.getObjectValue(node, "__ADVARGUMENTS__");
    for (java.util.Map<String, String> map : tableValues) {
        String argName = map.get("NAME").trim();
        String argValue = map.get("VALUE").trim();
        if(argName.startsWith("\"") && argName.endsWith("\"") && argName.length() >= 2) {
			argName = argName.substring(1, argName.length() - 1);
		}
		builder.addParam(argName, argValue);
    }

    String uri = builder.build();

	if (node.getIncomingConnections().isEmpty()) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_2);
    
	} else {
		if ("MANUAL".equals(ElementParameterParser.getValue(node, "__SERVICE_TYPE__"))) {
			String acceptType = paramsHelper.getVisibleStringParam("__ACCEPT_TYPE__");
			String responseBean = paramsHelper.getVisibleStringParam("__RESPONSE_BEAN__");
			String responseClass = "*/*".equals(acceptType) ? "String.class" : "org.w3c.dom.Document.class";
			responseClass = responseBean == null || responseBean.isEmpty() ? responseClass : responseBean + ".class";
			String contentType = paramsHelper.getVisibleStringParam("__CONTENT_TYPE__");

    stringBuffer.append(TEXT_3);
    stringBuffer.append(ElementParameterParser.getValue(node, "__PATH__"));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(ElementParameterParser.getValue(node, "__HTTP_METHOD__"));
    stringBuffer.append(TEXT_5);
     if (!acceptType.isEmpty()) { 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(acceptType);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(responseClass);
    stringBuffer.append(TEXT_8);
     } 
     if (!contentType.isEmpty()) { 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(contentType);
    stringBuffer.append(TEXT_10);
     }
} else { // RESOURCECLASS

    stringBuffer.append(TEXT_11);
    stringBuffer.append(ElementParameterParser.getValue(node, "__RESOURCE_OPERATION__"));
    stringBuffer.append(TEXT_12);
     } 
     if (paramsHelper.getBoolParam("__ENABLE_CORRELATION__")) { 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(paramsHelper.getVisibleStringParam("__CORRELATION_VALUE__"));
    stringBuffer.append(TEXT_15);
     } 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_17);
    
	}

    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
