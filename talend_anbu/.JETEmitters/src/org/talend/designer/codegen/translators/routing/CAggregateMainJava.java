package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CAggregateMainJava {

  protected static String nl;
  public static synchronized CAggregateMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CAggregateMainJava result = new CAggregateMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\t.aggregate().";
  protected final String TEXT_2 = NL + "\t\t\t.aggregate(new ";
  protected final String TEXT_3 = "()).";
  protected final String TEXT_4 = NL + "\t\t\t.aggregationRepository(repo_";
  protected final String TEXT_5 = ")";
  protected final String TEXT_6 = NL + "\t\t\t\t.completionSize(";
  protected final String TEXT_7 = ")";
  protected final String TEXT_8 = NL + "\t\t\t\t.completionTimeout(";
  protected final String TEXT_9 = ")";
  protected final String TEXT_10 = NL + "\t\t\t\t.completionInterval(";
  protected final String TEXT_11 = ")";
  protected final String TEXT_12 = NL + "\t\t\t\t.completionPredicate(";
  protected final String TEXT_13 = ")";
  protected final String TEXT_14 = NL + "\t\t\t.completionFromBatchConsumer()";
  protected final String TEXT_15 = NL + "\t\t\t.eagerCheckCompletion()";
  protected final String TEXT_16 = NL + "\t\t\t\t.closeCorrelationKeyOnCompletion(";
  protected final String TEXT_17 = ")";
  protected final String TEXT_18 = NL + "\t\t\t.ignoreInvalidCorrelationKeys()";
  protected final String TEXT_19 = NL + "\t\t\t.groupExchanges()";
  protected final String TEXT_20 = NL;

	public String getExpression(String language, String expression, String cidd, String useNamespacess, String resultClassType) {
		if ("bean".equals(language)) {
			return "method(" + expression + ")";
		} else if ("correlation".equals(language)) {
			return "simple(\"${in.header.CamelCxfMessage[CorrelationID]}\", String.class)";
		} else if ("xpath".equals(language)) {
			if ("true".equals(useNamespacess)) {
				if (resultClassType != null && !resultClassType.isEmpty()) {
					return language + "(" + expression + ", "  + resultClassType + ", " + cidd + "NSMap)";
				}
				return language + "(" + expression + ", " + cidd + "NSMap)";
			} else {
				if (resultClassType != null && !resultClassType.isEmpty()) {
					return language + "(" + expression + ", "  + resultClassType + ")";
				}
				return language + "(" + expression +")";
			}
		} else if(!"none".equals(language)) {
			return language + "(" + expression + ")";
		}else{
			return expression;
		}
	}
	
	public String getExpressionDefinition(String language, String expression, String cidd, String useNamespacess, String resultClassType) {
		if ("el".equals(language)) {
			return "new org.apache.camel.model.language.ELExpression(" + expression + ")";
		} else if ("groovy".equals(language)) {
			return "new org.apache.camel.model.language.GroovyExpression(" + expression + ")";
		} else if ("javascript".equals(language)) {
			return "new org.apache.camel.model.language.JavaScriptExpression(" + expression + ")";
		} else if ("sql".equals(language)) {
			return "new org.apache.camel.model.language.SqlExpression(" + expression + ")";
		} else if ("jsonpath".equals(language)) {
			return "new org.apache.camel.model.language.JsonPathExpression(" + expression + ")";
		} else if ("jxpath".equals(language)) {
			return "new org.apache.camel.model.language.JXPathExpression(" + expression + ")";
		} else if ("mvel".equals(language)) {
			return "new org.apache.camel.model.language.MvelExpression(" + expression + ")";
		} else if ("ognl".equals(language)) {
			return "new org.apache.camel.model.language.OgnlExpression(" + expression + ")";
		} else if ("php".equals(language)) {			
			return "new org.apache.camel.model.language.PhpExpression(" + expression + ")";
		} else if ("python".equals(language)) {
			return "new org.apache.camel.model.language.PythonExpression(" + expression + ")";
		} else if ("ruby".equals(language)) {		
			return "new org.apache.camel.model.language.RubyExpression(" + expression + ")";
		} else if ("spel".equals(language)) {
			return "new org.apache.camel.model.language.SpELExpression(" + expression + ")";
		} else if ("xpath".equals(language)) {
			if ("true".equals(useNamespacess)) {
				if (resultClassType != null && !resultClassType.isEmpty()) {
					return cidd + "ns.xpath(" + expression + ", "  + resultClassType + ")";
				}
				return cidd + "ns.xpath(" + expression + ")";
			} else {
				return "new org.apache.camel.model.language.XPathExpression(" + expression + ")";
			}
		} else if ("xquery".equals(language)) {
			return "new org.apache.camel.model.language.XQueryExpression(" + expression + ")";
		} else {
			return getExpression(language, expression, cidd, useNamespacess, resultClassType);
		}
	}
	
	public String generate(CodeGeneratorArgument argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String language = ElementParameterParser.getValue(node, "__LANGUAGES__");
	String expression = ElementParameterParser.getValue(node, "__EXPRESSION__");
	String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");
	
	if("correlation".equals(language)){
		language = "simple";
		expression = "\"${in.header.CamelCxfMessage[CorrelationID]}\", String.class";
	}
	
	boolean useAggregationStrategy = true;
//	boolean useAggregationStrategy = "true".equals(ElementParameterParser.getValue(node, "__USE_AGGREGATION_STRATEGY__"));
	String aggregationStrategy = ElementParameterParser.getValue(node, "__AGGREGATION_STRATEGY__");
	
	boolean useCompletionSize = "true".equals(ElementParameterParser.getValue(node, "__USE_COMPLETION_SIZE__"));
	String size = ElementParameterParser.getValue(node, "__SIZE__");
	
	boolean useCompletionTimeout = "true".equals(ElementParameterParser.getValue(node, "__USE_COMPLETION_TIMEOUT__"));
	String timeout = ElementParameterParser.getValue(node, "__TIMEOUT__");
	
	boolean useCompletionInterval = "true".equals(ElementParameterParser.getValue(node, "__USE_COMPLETION_INTERVAL__"));
	String interval = ElementParameterParser.getValue(node, "__INTERVAL__");
	
	boolean useCompletionPredicate = "true".equals(ElementParameterParser.getValue(node, "__USE_COMPLETION_PREDICATE__"));
	String predicate = ElementParameterParser.getValue(node, "__PREDICATE__");
	
	boolean useCompletionBatch = "true".equals(ElementParameterParser.getValue(node, "__USE_COMPLETION_BATCH__"));
	
	boolean eagerCheckCompletion = "true".equals(ElementParameterParser.getValue(node, "__EAGER_CHECK_COMPLETION__"));
	boolean closeCorrelation = "true".equals(ElementParameterParser.getValue(node, "__CLOSE_CORRELATION__"));
	String maxBound = ElementParameterParser.getValue(node, "__MAXIMUM_BOUND__");
	boolean ignoreInvalidKey = "true".equals(ElementParameterParser.getValue(node, "__IGNORE_INVALID_KEY__"));
	boolean groupExchange = "true".equals(ElementParameterParser.getValue(node, "__GROUP_EXCHANGES__"));
	
	if(groupExchange){
		useAggregationStrategy = false;
	}
	
	boolean usePersistence = "true".equals(ElementParameterParser.getValue(node, "__USE_PERSISTENCE__"));
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {
		String exp = getExpression(language, expression, cid, useNamespaces, "");
		if(!useAggregationStrategy) {			

    stringBuffer.append(TEXT_1);
    stringBuffer.append(exp);
    
		} else {			

    stringBuffer.append(TEXT_2);
    stringBuffer.append(aggregationStrategy);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(exp);
    
		}
		
		if(usePersistence) {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
		}
		
		if(useCompletionSize) {
			if(!("".equals(size)) && size!=null) {

    stringBuffer.append(TEXT_6);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_7);
    
			}
		}
		
		if(useCompletionTimeout) {
			if(!("".equals(timeout)) && timeout!=null) {

    stringBuffer.append(TEXT_8);
    stringBuffer.append(timeout);
    stringBuffer.append(TEXT_9);
    
			}
		}
		
		if(useCompletionInterval) {
			if(!("".equals(interval)) && interval!=null) {

    stringBuffer.append(TEXT_10);
    stringBuffer.append(interval);
    stringBuffer.append(TEXT_11);
    
			}
		}
		
		if(useCompletionPredicate) {
			if(!("".equals(predicate)) && size!=predicate) {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(predicate);
    stringBuffer.append(TEXT_13);
    
			}
		}
		
		if(useCompletionBatch) {

    stringBuffer.append(TEXT_14);
    
		}
		
		if(eagerCheckCompletion) {

    stringBuffer.append(TEXT_15);
    
		}
		
		if(closeCorrelation) {
			if(!("".equals(maxBound)) && maxBound!=null) {

    stringBuffer.append(TEXT_16);
    stringBuffer.append(maxBound);
    stringBuffer.append(TEXT_17);
    
			}
		}
		
		if(ignoreInvalidKey) {

    stringBuffer.append(TEXT_18);
    
		}
		
		if(groupExchange) {

    stringBuffer.append(TEXT_19);
    
		}
	}

    stringBuffer.append(TEXT_20);
    return stringBuffer.toString();
  }
}