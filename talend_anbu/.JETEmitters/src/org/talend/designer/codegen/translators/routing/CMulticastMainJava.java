package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.Map;
import java.util.List;

public class CMulticastMainJava
{
  protected static String nl;
  public static synchronized CMulticastMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CMulticastMainJava result = new CMulticastMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t.multicast(";
  protected final String TEXT_2 = NL + "\t\t\tnew ";
  protected final String TEXT_3 = "()";
  protected final String TEXT_4 = NL + "\t\t)";
  protected final String TEXT_5 = NL + "\t\t\t.parallelProcessing()";
  protected final String TEXT_6 = NL + "\t\t\t\t.timeout(";
  protected final String TEXT_7 = ")";
  protected final String TEXT_8 = ".stopOnException()";
  protected final String TEXT_9 = NL + "\t\t\t\t\t.to(";
  protected final String TEXT_10 = NL + "\t\t\t\t\t,";
  protected final String TEXT_11 = NL + "\t\t\t)";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	List<Map<String, String>> uris = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__URIS__");
	boolean useAggregationStrategy  = "true".equals(ElementParameterParser.getValue(node, "__USE_AGGREGATION_STRATEGY__"));
	String aggregationStrategy = ElementParameterParser.getValue(node, "__AGGREGATION_STRATEGY__");
	boolean parallelProcessing  = "true".equals(ElementParameterParser.getValue(node, "__PARALLEL_PROCESSING__"));
	String timeout  = ElementParameterParser.getValue(node, "__TIMEOUT__");
	boolean useTimeout = "true".equals(ElementParameterParser.getValue(node, "__USE_TIMEOUT__"));
	boolean stopOnException="true".equals(ElementParameterParser.getValue(node, "__STOP_ON_EXCEPTION__"));
	int urisCount = uris.size();
			
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {

    stringBuffer.append(TEXT_1);
    
		if(useAggregationStrategy) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(aggregationStrategy);
    stringBuffer.append(TEXT_3);
    
		}
		//Below : we close the multicast bracket

    stringBuffer.append(TEXT_4);
    
		if(parallelProcessing) {

    stringBuffer.append(TEXT_5);
    
			if(useTimeout) {

    stringBuffer.append(TEXT_6);
    stringBuffer.append(timeout);
    stringBuffer.append(TEXT_7);
    
			}
		}
		if(stopOnException){
			
    stringBuffer.append(TEXT_8);
    
		
		}
		

		if(urisCount>0) {
			boolean isFirstUri = true;
			for(Map<String, String> anUri : uris) {
			
				if(isFirstUri) {
					isFirstUri=false;

    stringBuffer.append(TEXT_9);
    stringBuffer.append(anUri.get("URI"));
    
				} else {

    stringBuffer.append(TEXT_10);
    stringBuffer.append(anUri.get("URI"));
    
				}
			}

    stringBuffer.append(TEXT_11);
    
		}
	}

    return stringBuffer.toString();
  }
}
