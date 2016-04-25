package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TESBProviderRequestLoopBeginJava
{
  protected static String nl;
  public static synchronized TESBProviderRequestLoopBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TESBProviderRequestLoopBeginJava result = new TESBProviderRequestLoopBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tif (true) {" + NL + "\t\tthrow new RuntimeException(\"cannot instantiate Web Service: job contains more than one tESBProviderRequest component\");" + NL + "\t}";
  protected final String TEXT_2 = NL + "\tif (true) {" + NL + "\t\tthrow new RuntimeException(\"cannot instantiate service: job contains both tESBProviderRequest and tRESTRequest components\");" + NL + "\t}";
  protected final String TEXT_3 = NL + NL + "//*** external processor(s) initialization" + NL + "\tESBProviderCallbackTalendJobInner providerCallback_";
  protected final String TEXT_4 = ";" + NL + "\tHandlerThread_";
  protected final String TEXT_5 = " handlerThread_";
  protected final String TEXT_6 = " = null;" + NL + "\tif (null == this.callback) {" + NL + "\t\tfinal QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> handler_";
  protected final String TEXT_7 = " =" + NL + "\t\t\tnew QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document>();" + NL + "\t\thandlerThread_";
  protected final String TEXT_8 = " =" + NL + "\t\t\tnew HandlerThread_";
  protected final String TEXT_9 = "(handler_";
  protected final String TEXT_10 = "); //" + NL + "\t\thandlerThread_";
  protected final String TEXT_11 = ".start();" + NL + "\t\tproviderCallback_";
  protected final String TEXT_12 = " = new ESBProviderCallbackTalendJobWrapper_";
  protected final String TEXT_13 = "(handler_";
  protected final String TEXT_14 = ");" + NL + "\t} else {" + NL + "\t\tproviderCallback_";
  protected final String TEXT_15 = " = new ESBProviderCallbackTalendJobWrapper_";
  protected final String TEXT_16 = "(this.callback);" + NL + "\t}" + NL + "\tglobalMap.put(\"esbHandler\", providerCallback_";
  protected final String TEXT_17 = ");" + NL + "//*** external processor(s) initialization finish" + NL + "" + NL + "int nb_line_";
  protected final String TEXT_18 = " = 0;" + NL + "" + NL + "try {" + NL + "\t// This is a beginning of the ESB provider request component cycle" + NL + "\tglobalMap.put(\"wsRequestReceivedAndResponseSentBack\", true);" + NL + "\twhile(true) {" + NL + "\t\ttry {" + NL + "\t\t\tESBProviderCallbackTalendJobInner esbHandler_";
  protected final String TEXT_19 = " =" + NL + "\t\t\t\t(ESBProviderCallbackTalendJobInner) globalMap.get(\"esbHandler\");" + NL;
  protected final String TEXT_20 = NL + "\t\t\tif (!(Boolean) globalMap.get(\"wsRequestReceivedAndResponseSentBack\")) {" + NL + "\t\t\t\t// esbHandler_";
  protected final String TEXT_21 = ".sendFault(new RuntimeException(\"response/fault is not provided by Talend job\"));" + NL + "\t\t\t\tesbHandler_";
  protected final String TEXT_22 = ".sendResponse(org.dom4j.DocumentHelper.createDocument());" + NL + "\t\t\t}";
  protected final String TEXT_23 = NL + NL + "            java.util.Map<String, Object> request_";
  protected final String TEXT_24 = " = (java.util.Map<String, Object>) esbHandler_";
  protected final String TEXT_25 = ".getRequest();" + NL + "            globalMap.put(\"";
  protected final String TEXT_26 = "_\" + ESBProviderCallback.HEADERS_SOAP, request_";
  protected final String TEXT_27 = ".get(ESBProviderCallback.HEADERS_SOAP));" + NL + "            globalMap.put(\"";
  protected final String TEXT_28 = "_\" + ESBProviderCallback.HEADERS_HTTP, request_";
  protected final String TEXT_29 = ".get(ESBProviderCallback.HEADERS_HTTP));" + NL + "            java.util.Collection<org.apache.cxf.headers.Header> existingSoapHeaders_";
  protected final String TEXT_30 = " = (java.util.Collection<org.apache.cxf.headers.Header>) request_";
  protected final String TEXT_31 = ".get(ESBProviderCallback.HEADERS_SOAP);" + NL + "            if (null != existingSoapHeaders_";
  protected final String TEXT_32 = ") {" + NL + "                for (org.apache.cxf.headers.Header existingHeader : existingSoapHeaders_";
  protected final String TEXT_33 = ") {" + NL + "                    if (org.apache.wss4j.common.WSS4JConstants.WSSE_NS.equals(existingHeader.getName().getNamespaceURI())" + NL + "                            && org.apache.wss4j.common.WSS4JConstants.WSSE_LN.equals(existingHeader.getName().getLocalPart())) {" + NL + "                        org.w3c.dom.NodeList nl = ((org.w3c.dom.Element) existingHeader.getObject()).getElementsByTagNameNS(org.apache.wss4j.common.WSS4JConstants.SAML2_NS, org.apache.wss4j.common.WSS4JConstants.ASSERTION_LN);" + NL + "                        if (nl.getLength() > 0) {" + NL + "                            globalMap.put(\"";
  protected final String TEXT_34 = "_SECURITY_TOKEN\", nl.item(0));" + NL + "                            break;" + NL + "                        }" + NL + "                    }" + NL + "                }" + NL + "            }" + NL + "            globalMap.put(\"";
  protected final String TEXT_35 = "_\"+ \"CORRELATION_ID\", request_";
  protected final String TEXT_36 = ".get(\"CorrelationID\"));" + NL + "            org.dom4j.Document requestMessage_";
  protected final String TEXT_37 = " = (org.dom4j.Document) request_";
  protected final String TEXT_38 = ".get(ESBProviderCallback.REQUEST);" + NL;
  protected final String TEXT_39 = NL + "\t\t\tglobalMap.put(\"wsRequestReceivedAndResponseSentBack\", false);";
  protected final String TEXT_40 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
cid = cid.replaceAll("_Loop", "");

boolean isOneWay = (node.getProcess().getNodesOfType("tESBProviderFault").isEmpty()
	&& node.getProcess().getNodesOfType("tESBProviderResponse").isEmpty());

if (node.getProcess().getNodesOfType("tESBProviderRequestLoop").size() > 1) { 
    stringBuffer.append(TEXT_1);
    
}
if (node.getProcess().getNodesOfType("tRESTRequestLoop").size() > 0) {

    stringBuffer.append(TEXT_2);
    
}

    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
     if (!isOneWay) { 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
     } 
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
     if (!isOneWay) { 
    stringBuffer.append(TEXT_39);
     } 
    stringBuffer.append(TEXT_40);
    return stringBuffer.toString();
  }
}
