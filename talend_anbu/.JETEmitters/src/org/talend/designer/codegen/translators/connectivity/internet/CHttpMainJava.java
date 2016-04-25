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

public class CHttpMainJava
{
  protected static String nl;
  public static synchronized CHttpMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CHttpMainJava result = new CHttpMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.to(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL + "\t\t\tfrom(";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = NL + "\t\t";
  protected final String TEXT_6 = ".to(";
  protected final String TEXT_7 = ")";
  protected final String TEXT_8 = NL;
  protected final String TEXT_9 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    INode node = (INode) codeGenArgument.getArgument();
    CamelEndpointBuilder builder = CamelEndpointBuilder.getBuilder();
    String isServer = ElementParameterParser.getValue(node, "__SERVER__");
    
    String cid = node.getUniqueName();
    //  http://jira.talendforge.org/browse/TESB-5241
    /*
     * change to use label + unique to make it unique but readable
     */
//    IElementParameter param = node.getElementParameter("LABEL");
//    if(param != null && !"__UNIQUE_NAME__".equals(param.getValue())){
//    	cid = (String)param.getValue()+"_"+cid;	
//    }
    
	String resourceUri = ElementParameterParser.getValue(node, "__URI__");
	
	StringBuffer sb = new StringBuffer();


	boolean hasParam = resourceUri==null?false:resourceUri.contains("?");
	String uri = "";
	if("true".equals(isServer)){
		builder.setComponent("jetty");
		builder.setName(resourceUri);
		String disableStream = ElementParameterParser.getValue(node, "__DISABLE_STREAM__");
		String sessionSupport = ElementParameterParser.getValue(node, "__SESSION_SUPPORT__");
		String chunked = ElementParameterParser.getValue(node, "__CHUNKED__");
		String enableJmx = ElementParameterParser.getValue(node, "__ENABLEJMX__");
		String matchOnUriPrefix = ElementParameterParser.getValue(node, "__MATCH_ON_URI_PREFIX__");
		String traceEnable = ElementParameterParser.getValue(node, "__TRACE_ENABLE__");
		String useContinuation = ElementParameterParser.getValue(node, "__USE_CONTINUATION__");
		
		if("true".equals(disableStream)){
			builder.addParam("disableStreamCache", "\"true\"");
		}
		
		if("true".equals(sessionSupport)){
			builder.addParam("sessionSupport", "\"true\"");
		}
		
		if("false".equals(chunked)){
			builder.addParam("chunked", "\"false\"");
		}
		
		if("true".equals(enableJmx)){
			builder.addParam("enableJmx", "\"true\"");
		}
		
		if("true".equals(matchOnUriPrefix)){
			builder.addParam("matchOnUriPrefix", "\"true\"");
		}
		
		if("true".equals(traceEnable)){
			builder.addParam("traceEnable", "\"true\"");
		}
		
		if("false".equals(useContinuation)){
			builder.addParam("useContinuation", "\"false\"");
		}
		
		List<Map<String, String>> arguments = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__HEADERS__");
		for (Map<String, String> map : arguments) {
			String argName = map.get("KEY").trim();
			String argValue = map.get("VALUE").trim();
			if(argName.startsWith("\"") && argName.endsWith("\"") && argName.length()>=2){
				argName = argName.substring(1, argName.length() - 1);
			} else {
				argName = "\" + " + argName + "+ \"";
			}
			builder.addParam(argName, argValue);
		}
		uri = builder.build();
	}else{
		sb.append(resourceUri);
		String method = ElementParameterParser.getValue(node, "__METHOD__");
		if("GET".equals(method)||"HEAD".equals(method) || "DELETE".equals(method)){
			List<Map<String, String>> parameters = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__PARAMETERS__");
			String encodeCharset = ElementParameterParser.getValue(node, "__ENCODER_CHARSET__");

			for (Map<String, String> map : parameters) {
				String argName = map.get("KEY").trim();
				String argValue = map.get("VALUE").trim();
				try{
					if(argName.startsWith("\"")&&argName.endsWith("\"")&&argName.length()>=2){
						argName = URLEncoder.encode(argName.substring(1, argName.length()-1),encodeCharset);
						argName = "\""+argName+"\"";
					}else{
						argName = URLEncoder.encode(argName,encodeCharset);
					}
					if(argValue.startsWith("\"")&&argValue.endsWith("\"")&&argValue.length()>=2){
						argValue = URLEncoder.encode(argValue.substring(1, argValue.length()-1),encodeCharset);
						argValue = "\""+argValue+"\"";
					}else{
						argValue = URLEncoder.encode(argValue,encodeCharset);
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if (hasParam) {
					sb.append("+\"&\"+" + argName + "+\"=\"+" + argValue);
				} else {
					sb.append("+\"?\"+" + argName + "+\"=\"+" + argValue);
					hasParam = true;
				}
			}
		}
		uri = sb.toString();
	}
    
    if("true".equals(isServer)){
    	List< ? extends IConnection> conns = node.getIncomingConnections();
    	if(conns.size()>0){

    stringBuffer.append(TEXT_1);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_2);
    
    	}else{

    stringBuffer.append(TEXT_3);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_4);
    
    	}
    	
    }else{
		StringBuilder sb1 = new StringBuilder();
		String method = ElementParameterParser.getValue(node, "__METHOD__");
		String contentType = ElementParameterParser.getValue(node, "__CONTENT_TYPE__");
		
		sb1.append(".setHeader(\"");
		sb1.append("CamelHttpMethod");
		sb1.append("\",");
		sb1.append("constant(\"");
		sb1.append(method);
		sb1.append("\"))");
		
		String formStyle = ElementParameterParser.getValue(node, "__FORM_CONTENT__");
		String plainTextStyle = ElementParameterParser.getValue(node, "__PLAIN_TEXT__");
		String noContentStyle = ElementParameterParser.getValue(node, "__NO_CONTENT__");
		String manualContentType = ElementParameterParser.getValue(node, "__MANUAL_CONTENT_TYPE__");
		
		if (contentType.equals("other")) {
			contentType=manualContentType;
		}else{
			contentType = "\""+contentType+"\"";
		}
		if(("POST".equals(method)||"PUT".equals(method))){
			if("true".equals(plainTextStyle)||"true".equals(noContentStyle)){
				sb1.append(".setHeader(\"Content-Type\", constant("+ contentType + "))");
				if("true".equals(plainTextStyle)){
					String plainTextContent = ElementParameterParser.getValue(node, "__PLAIN_TEXT_CONTENT__");
					sb1.append(".setBody(");
					sb1.append("constant(");
					sb1.append(plainTextContent);
					sb1.append("))");
				}
			}else if("true".equals(formStyle)){
				// content-type of form is "application/x-www-form-urlencoded"
				sb1.append(".setHeader(\"Content-Type\", constant(\"application/x-www-form-urlencoded\"))");
				List<Map<String, String>> parameters = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__PARAMETERS__");
				String encodeCharset = ElementParameterParser.getValue(node, "__ENCODER_CHARSET__");
				hasParam = false;
				sb1.append(".setBody(");
				sb1.append("constant(");
				for (Map<String, String> map : parameters) {
					String argName = map.get("KEY").trim();
					String argValue = map.get("VALUE").trim();
					try{
						if(argName.startsWith("\"")&&argName.endsWith("\"")&&argName.length()>=2){
							argName = URLEncoder.encode(argName.substring(1, argName.length()-1),encodeCharset);
							argName = "\""+argName+"\"";
						}else{
							argName = URLEncoder.encode(argName,encodeCharset);
						}
						if(argValue.startsWith("\"")&&argValue.endsWith("\"")&&argValue.length()>=2){
							argValue = URLEncoder.encode(argValue.substring(1, argValue.length()-1),encodeCharset);
							argValue = "\""+argValue+"\"";
						}else{
							argValue = URLEncoder.encode(argValue,encodeCharset);
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if (hasParam) {
						sb1.append("+\"&\"+" + argName + "+\"=\"+" + argValue);
					} else {
						sb1.append(argName + "+\"=\"+" + argValue);
						hasParam = true;
					}
				}
				sb1.append("))");
			}
		}
		
		
		List<Map<String, String>> arguments = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__HEADERS__");
		for (Map<String, String> map : arguments) {
			String argName = map.get("KEY").trim();
			String argValue = map.get("VALUE").trim();
			sb1.append(".setHeader(");
			sb1.append(argName);
			sb1.append(",");
			sb1.append("constant(");
			sb1.append(argValue);
			sb1.append("))");
		}



    stringBuffer.append(TEXT_5);
    stringBuffer.append(sb1.toString());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_7);
    
    }

    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
