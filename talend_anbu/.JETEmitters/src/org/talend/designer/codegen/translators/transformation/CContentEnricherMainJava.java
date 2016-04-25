package org.talend.designer.codegen.translators.transformation;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CContentEnricherMainJava
{
  protected static String nl;
  public static synchronized CContentEnricherMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CContentEnricherMainJava result = new CContentEnricherMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.pollEnrich(";
  protected final String TEXT_2 = NL + "\t\t\t.enrich(";
  protected final String TEXT_3 = NL + "\t\t\t\t, ";
  protected final String TEXT_4 = NL + "\t\t\t, new ";
  protected final String TEXT_5 = "()";
  protected final String TEXT_6 = NL + "\t\t\t)";
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	
	String resourceURI = ElementParameterParser.getValue(node, "__RESOURCE_URI__");
	boolean isPollEnrich = "true".equals(ElementParameterParser.getValue(node, "__POLLENRICH__"));
	boolean isEnrich = "true".equals(ElementParameterParser.getValue(node, "__ENRICH__"));
	
	boolean useAggregationStrategy = "true".equals(ElementParameterParser.getValue(node, "__USE_AGG_STRATEGY__"));
	String aggregationStrategy = ElementParameterParser.getValue(node, "__AGGREGATION_STRATEGY__");
	
	boolean specifyTimeout = "true".equals(ElementParameterParser.getValue(node, "__SPECIFY_TIMEOUT__"));
	boolean wait = "true".equals(ElementParameterParser.getValue(node, "__WAIT__"));
	boolean immediate = "true".equals(ElementParameterParser.getValue(node, "__IMMEDIATE__"));
	boolean trigger = "true".equals(ElementParameterParser.getValue(node, "__TRIGGER__"));
	
	int timeout = 0;
	
	if(wait)
		timeout = -1;
	else if(immediate)
		timeout = 0;
	else
		timeout = Integer.parseInt(ElementParameterParser.getValue(node, "__TIMEOUT_TRIGGER__"));
	
	if(conns.size()>0) {
		if(isPollEnrich) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(resourceURI);
    
		} else {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(resourceURI);
    
		}
		
		if(isPollEnrich) {
			if(specifyTimeout) {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(timeout);
    
			}
		}
		
		if(useAggregationStrategy) {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(aggregationStrategy);
    stringBuffer.append(TEXT_5);
    
		}
		

    stringBuffer.append(TEXT_6);
    
	}

    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}
