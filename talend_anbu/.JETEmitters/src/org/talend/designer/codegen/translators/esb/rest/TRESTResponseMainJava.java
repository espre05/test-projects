package org.talend.designer.codegen.translators.esb.rest;

import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import java.util.Map;

public class TRESTResponseMainJava
{
  protected static String nl;
  public static synchronized TRESTResponseMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRESTResponseMainJava result = new TRESTResponseMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tSystem.err.println(\"[WARN] nonsense: tRESTResponse component used without tRESTRequest component on the job\");";
  protected final String TEXT_2 = NL + "\tjava.io.OutputStream outputStream_";
  protected final String TEXT_3 = " = (java.io.OutputStream) globalMap.get(\"restResponseStream\");" + NL + "\tboolean responseAlreadySent_";
  protected final String TEXT_4 = " = globalMap.containsKey(\"restResponse\");" + NL + "" + NL + "\tif (null == outputStream_";
  protected final String TEXT_5 = " && responseAlreadySent_";
  protected final String TEXT_6 = ") {" + NL + "\t\tthrow new RuntimeException(\"Rest response already sent.\");" + NL + "\t} else if (!globalMap.containsKey(\"restRequest\")) {" + NL + "\t\tthrow new RuntimeException(\"Not received rest request yet.\");" + NL + "\t} else {";
  protected final String TEXT_7 = NL + "\t\tInteger restProviderStatusCode_";
  protected final String TEXT_8 = " = ";
  protected final String TEXT_9 = ";";
  protected final String TEXT_10 = NL + "\t\tInteger restProviderStatusCode_";
  protected final String TEXT_11 = " = ";
  protected final String TEXT_12 = ";";
  protected final String TEXT_13 = NL + NL + "\t\tObject restProviderResponse_";
  protected final String TEXT_14 = " = null;";
  protected final String TEXT_15 = NL + "\t\trestProviderResponse_";
  protected final String TEXT_16 = " = ";
  protected final String TEXT_17 = ".string;";
  protected final String TEXT_18 = NL + "\t\tif (null != ";
  protected final String TEXT_19 = ".body) {" + NL + "\t\t\trestProviderResponse_";
  protected final String TEXT_20 = " = ";
  protected final String TEXT_21 = ".body.getDocument();" + NL + "\t\t}";
  protected final String TEXT_22 = NL + "\t\trestProviderResponse_";
  protected final String TEXT_23 = " = ";
  protected final String TEXT_24 = ".body;";
  protected final String TEXT_25 = NL + NL + "\t\tjava.util.Map<String, String> restProviderResponseHeaders_";
  protected final String TEXT_26 = " = new java.util.TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);";
  protected final String TEXT_27 = NL + "\t\trestProviderResponseHeaders_";
  protected final String TEXT_28 = ".put(";
  protected final String TEXT_29 = ", ";
  protected final String TEXT_30 = ");";
  protected final String TEXT_31 = NL + NL + "        java.util.Map<String, Object> restRequest_";
  protected final String TEXT_32 = " = (java.util.Map<String, Object>) globalMap.get(\"restRequest\");" + NL + "        org.apache.cxf.jaxrs.ext.MessageContext messageContext_";
  protected final String TEXT_33 = " = (org.apache.cxf.jaxrs.ext.MessageContext) restRequest_";
  protected final String TEXT_34 = ".get(\"MESSAGE_CONTEXT\");" + NL + "        ";
  protected final String TEXT_35 = NL + "            messageContext_";
  protected final String TEXT_36 = ".put(\"json.array.keys\",java.util.Arrays.asList(";
  protected final String TEXT_37 = ".split(\" \")));";
  protected final String TEXT_38 = NL + NL + "\t\tif (null == outputStream_";
  protected final String TEXT_39 = ") {" + NL + "\t\t\tjava.util.Map<String, Object> restResponse_";
  protected final String TEXT_40 = " = new java.util.HashMap<String, Object>();" + NL + "\t\t\trestResponse_";
  protected final String TEXT_41 = ".put(\"BODY\", restProviderResponse_";
  protected final String TEXT_42 = ");" + NL + "\t\t\trestResponse_";
  protected final String TEXT_43 = ".put(\"STATUS\", restProviderStatusCode_";
  protected final String TEXT_44 = ");" + NL + "\t\t\trestResponse_";
  protected final String TEXT_45 = ".put(\"HEADERS\", restProviderResponseHeaders_";
  protected final String TEXT_46 = ");" + NL + "\t\t\trestResponse_";
  protected final String TEXT_47 = ".put(\"drop.json.root.element\", Boolean.valueOf(";
  protected final String TEXT_48 = "));" + NL + "\t\t\tglobalMap.put(\"restResponse\", restResponse_";
  protected final String TEXT_49 = ");" + NL + "\t\t\t" + NL + "\t\t} else {" + NL + "" + NL + "\t\t\tjavax.ws.rs.core.MediaType responseMediaType_";
  protected final String TEXT_50 = " = null;" + NL + "\t\t\tif (!responseAlreadySent_";
  protected final String TEXT_51 = ") {" + NL + "\t\t\t\torg.apache.cxf.jaxrs.utils.JAXRSUtils.getCurrentMessage().getExchange().put(StreamingDOM4JProvider.SUPRESS_XML_DECLARATION, true);" + NL + "" + NL + "\t\t\t\tmessageContext_";
  protected final String TEXT_52 = ".put(org.apache.cxf.message.Message.RESPONSE_CODE, restProviderStatusCode_";
  protected final String TEXT_53 = ");" + NL + "\t\t\t\tjavax.ws.rs.core.MultivaluedMap<String, String> headersMultivaluedMap_";
  protected final String TEXT_54 = " = new org.apache.cxf.jaxrs.impl.MetadataMap<String, String>();" + NL + "\t\t\t\tfor (java.util.Map.Entry<String, String> multivaluedHeader : restProviderResponseHeaders_";
  protected final String TEXT_55 = ".entrySet()) {" + NL + "\t\t\t\t\theadersMultivaluedMap_";
  protected final String TEXT_56 = ".putSingle(multivaluedHeader.getKey(), multivaluedHeader.getValue());" + NL + "\t\t\t\t}" + NL + "\t\t\t\tmessageContext_";
  protected final String TEXT_57 = ".put(org.apache.cxf.message.Message.PROTOCOL_HEADERS, headersMultivaluedMap_";
  protected final String TEXT_58 = ");" + NL + "" + NL + "\t\t\t\t// String responseContentType_";
  protected final String TEXT_59 = " = (String) messageContext_";
  protected final String TEXT_60 = ".get(org.apache.cxf.message.Message.CONTENT_TYPE);" + NL + "\t\t\t\tString responseContentType_";
  protected final String TEXT_61 = " = (String) org.apache.cxf.jaxrs.utils.JAXRSUtils.getCurrentMessage().getExchange().get(org.apache.cxf.message.Message.CONTENT_TYPE);" + NL + "\t\t\t\tif (null == responseContentType_";
  protected final String TEXT_62 = ") {" + NL + "\t\t\t\t\t// this should not be needed, just in case. set it to the first value in the sorted list returned from HttpHeaders" + NL + "\t\t\t\t\tresponseMediaType_";
  protected final String TEXT_63 = " = messageContext_";
  protected final String TEXT_64 = ".getHttpHeaders().getAcceptableMediaTypes().get(0);" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\tresponseMediaType_";
  protected final String TEXT_65 = " = org.apache.cxf.jaxrs.utils.JAXRSUtils.toMediaType(responseContentType_";
  protected final String TEXT_66 = ");" + NL + "\t\t\t\t}" + NL + "\t\t\t\tglobalMap.put(\"restResponseMediaType\", responseMediaType_";
  protected final String TEXT_67 = ");" + NL + "" + NL + "\t\t\t\tString responseMediaSubType_";
  protected final String TEXT_68 = " = responseMediaType_";
  protected final String TEXT_69 = ".getSubtype();" + NL + "\t\t\t\tif (responseMediaSubType_";
  protected final String TEXT_70 = ".equals(\"xml\") || responseMediaSubType_";
  protected final String TEXT_71 = ".endsWith(\"+xml\")) {" + NL + "\t\t\t\t\toutputStream_";
  protected final String TEXT_72 = ".write(\"<wrapper>\".getBytes());" + NL + "\t\t\t\t\tglobalMap.put(\"restResponseWrappingClosure\", \"</wrapper>\");" + NL + "\t\t\t\t}" + NL + "\t\t\t\tif (responseMediaSubType_";
  protected final String TEXT_73 = ".equals(\"json\") || responseMediaSubType_";
  protected final String TEXT_74 = ".endsWith(\"+json\")) {" + NL + "\t\t\t\t\toutputStream_";
  protected final String TEXT_75 = ".write(\"[\".getBytes());" + NL + "\t\t\t\t\tglobalMap.put(\"restResponseWrappingClosure\", \"]\");" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\tglobalMap.put(\"restResponse\", true);" + NL + "\t\t\t} else {" + NL + "\t\t\t\tresponseMediaType_";
  protected final String TEXT_76 = " = (javax.ws.rs.core.MediaType) globalMap.get(\"restResponseMediaType\");" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\tif (null != restProviderResponse_";
  protected final String TEXT_77 = ") {" + NL + "\t\t\t\tString responseMediaSubType_";
  protected final String TEXT_78 = " = responseMediaType_";
  protected final String TEXT_79 = ".getSubtype();" + NL + "\t\t\t\tif (responseMediaSubType_";
  protected final String TEXT_80 = ".equals(\"json\") || responseMediaSubType_";
  protected final String TEXT_81 = ".endsWith(\"+json\")) {" + NL + "\t\t\t\t\tif (globalMap.containsKey(\"restResponseJsonStarted\")) {" + NL + "\t\t\t\t\t\toutputStream_";
  protected final String TEXT_82 = ".write(\",\".getBytes());" + NL + "\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\tglobalMap.put(\"restResponseJsonStarted\", true);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\tClass<? extends Object> responseBodyClass_";
  protected final String TEXT_83 = " = restProviderResponse_";
  protected final String TEXT_84 = ".getClass();" + NL + "\t\t\t\tjavax.ws.rs.ext.Providers messageBodyProviders_";
  protected final String TEXT_85 = " = messageContext_";
  protected final String TEXT_86 = ".getProviders();" + NL + "\t\t\t\tjavax.ws.rs.ext.MessageBodyWriter messageBodyWriter_";
  protected final String TEXT_87 = " = messageBodyProviders_";
  protected final String TEXT_88 = ".getMessageBodyWriter(" + NL + "\t\t\t\t\t\tresponseBodyClass_";
  protected final String TEXT_89 = ", responseBodyClass_";
  protected final String TEXT_90 = ", null, responseMediaType_";
  protected final String TEXT_91 = ");" + NL + "\t\t\t\tmessageBodyWriter_";
  protected final String TEXT_92 = ".writeTo(restProviderResponse_";
  protected final String TEXT_93 = ", responseBodyClass_";
  protected final String TEXT_94 = ", responseBodyClass_";
  protected final String TEXT_95 = "," + NL + "\t\t\t\t\t\tnew java.lang.annotation.Annotation[] {}, responseMediaType_";
  protected final String TEXT_96 = ", null, outputStream_";
  protected final String TEXT_97 = ");" + NL + "\t\t\t}" + NL + "\t\t\t// initial variant" + NL + "\t\t\t//outputStream_";
  protected final String TEXT_98 = ".write(String.valueOf(restProviderResponse_";
  protected final String TEXT_99 = ").getBytes());" + NL + "\t\t\toutputStream_";
  protected final String TEXT_100 = ".flush();" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_101 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode) codeGenArgument.getArgument();

if (node.getProcess().getNodesOfType("tRESTRequestLoop").isEmpty()) { 
    stringBuffer.append(TEXT_1);
     } else {

	String cid = node.getUniqueName();
	List<IMetadataTable> metadatas = node.getMetadataList();
	if (null != metadatas && 0 < metadatas.size()) {
		IMetadataTable metadata = metadatas.get(0);
		if (null != metadata) {
			List<? extends IConnection> conns = node.getIncomingConnections();
			if (null != conns && 0 < conns.size()) {
				IConnection conn = conns.get(0);
				if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {


    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    

String selectedStatusCode = ElementParameterParser.getValue(node, "__STATUS_CODE__");
if ("CUSTOM".equals(selectedStatusCode)) { 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append( ElementParameterParser.getValue(node, "__CUSTOM_STATUS_CODE__"));
    stringBuffer.append(TEXT_9);
     } else { 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append( selectedStatusCode);
    stringBuffer.append(TEXT_12);
     } 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    
IMetadataColumn column = conn.getMetadataTable().getColumn("string");
if (null != column && "id_String".equals(column.getTalendType())) { 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_17);
    
}

column = conn.getMetadataTable().getColumn("body");
if (null != column) {
	if ("id_Document".equals(column.getTalendType())) { 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_21);
    	} else { 
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_24);
    	}
} 
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    
List<Map<String, String>> headers = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node,"__RESPONSE_HEADERS__");
for (Map<String, String> header : headers) { 
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(header.get("NAME") );
    stringBuffer.append(TEXT_29);
    stringBuffer.append(header.get("VALUE") );
    stringBuffer.append(TEXT_30);
     } 
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    
        String jsonArrayKeys = ElementParameterParser.getValue(node,"__JSON_ARRAY_KEYS__");
        
        String REP = ElementParameterParser.getValue(node,"__REST_ENDPOINT__");
        
        System.out.println(REP+"<><>");
        
        if(jsonArrayKeys!=null && !jsonArrayKeys.trim().isEmpty()){ 
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(jsonArrayKeys);
    stringBuffer.append(TEXT_37);
     } 
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(ElementParameterParser.getValue(node,"__UNWRAP_JSON_RESPONSE__"));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    
				} // (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
			} // (null != conns && 0 < conns.size())
		} // (null != metadata)
	} // (null != metadatas && 0 < metadatas.size())
}

    stringBuffer.append(TEXT_101);
    return stringBuffer.toString();
  }
}
