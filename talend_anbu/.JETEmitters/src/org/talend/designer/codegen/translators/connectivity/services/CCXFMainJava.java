package org.talend.designer.codegen.translators.connectivity.services;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class CCXFMainJava
{
  protected static String nl;
  public static synchronized CCXFMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CCXFMainJava result = new CCXFMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        from(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = "                  " + NL + "\t\t.process(new org.apache.camel.Processor() {" + NL + "\t\t\t\tpublic void process(org.apache.camel.Exchange exchange) throws Exception {" + NL + "\t\t\t\t\tcorrelationIDCallbackHandler_";
  protected final String TEXT_4 = ".setCorrelationId(simple(";
  protected final String TEXT_5 = ").evaluate(exchange, String.class));" + NL + "\t\t\t\t}" + NL + "\t\t\t})";
  protected final String TEXT_6 = NL + "            .to(";
  protected final String TEXT_7 = ")";
  protected final String TEXT_8 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();

	String formatType = ElementParameterParser.getValue(node, "__DATAFORMAT__");

	boolean isStudioEEVersion = org.talend.core.PluginChecker.isTIS();

	boolean useRegistry = false;
	if(!"RAW".equals(formatType) && !"CXF_MESSAGE".equals(formatType) && isStudioEEVersion){
		useRegistry = Boolean.parseBoolean(ElementParameterParser.getValue(node, "__ENABLE_REGISTRY__"));
	}

	boolean useCorrelation = false;
	if(!"RAW".equals(formatType)) {
		useCorrelation = Boolean.parseBoolean(ElementParameterParser.getValue(node, "__ENABLE_CORRELATION__"));
	}
	String correlationValue = ElementParameterParser.getValue(node, "__CORRELATION_VALUE__");

	String endpointVar = "endpoint_" + cid;
	if (node.getIncomingConnections().isEmpty()) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_2);
    
	} else {
            if (useRegistry || useCorrelation) {
                if(!"".equals(correlationValue) && !"\"\"".equals(correlationValue)){

    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(correlationValue);
    stringBuffer.append(TEXT_5);
                  }
            }

    stringBuffer.append(TEXT_6);
    stringBuffer.append(endpointVar);
    stringBuffer.append(TEXT_7);
    
	}

    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}
