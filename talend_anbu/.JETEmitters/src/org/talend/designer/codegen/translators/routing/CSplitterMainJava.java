package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CSplitterMainJava {

  protected static String nl;
  public static synchronized CSplitterMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CSplitterMainJava result = new CSplitterMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t.split(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = ").";
  protected final String TEXT_4 = ".aggregationStrategyRef(";
  protected final String TEXT_5 = ")";
  protected final String TEXT_6 = ".parallelProcessing()";
  protected final String TEXT_7 = ".stopOnException()";
  protected final String TEXT_8 = ".streaming()";
  protected final String TEXT_9 = ".shareUnitOfWork()";
  protected final String TEXT_10 = ".timeout(";
  protected final String TEXT_11 = ")";
  protected final String TEXT_12 = NL;

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
	boolean useResultClassType ="true".equals(ElementParameterParser.getValue(node,"__USE_RESULT_CLASS_TYPE__"));
	String resultClassType = ElementParameterParser.getValue(node,"__RESULT_CLASS_TYPE__");
	String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");
	
	
	boolean useStrategy = "true".equals(ElementParameterParser.getValue(node,"__USE_STRATEGY__"));
	String aggregationStrategy = ElementParameterParser.getValue(node,"__AGGREGATION_STRATEGY__");
	
	boolean arallelProcessing="true".equals(ElementParameterParser.getValue(node, "__PARALLET_PROCESSING__"));
	boolean stopOnException="true".equals(ElementParameterParser.getValue(node, "__STOP_ON_EXCEPTION__"));
	boolean streaming="true".equals(ElementParameterParser.getValue(node, "__STREAMING__"));
	boolean shareUnitOfWork="true".equals(ElementParameterParser.getValue(node, "__SHARE_UNIT_OF_WORK__"));
	
	String timeout=ElementParameterParser.getValue(node, "__TIMEOUT__");
	boolean hasTimeout=(timeout!=null&&timeout.matches("\\s*\\d+\\s*"));
	String exp = "";
	if (useResultClassType) {
		exp = getExpression(language, expression, cid, useNamespaces, resultClassType);
	} else {
		exp = getExpression(language, expression, cid, useNamespaces, "");
	}
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {

    stringBuffer.append(TEXT_1);
    
		if("none".equals(language)){
			//inorder expression for former migrate
			
    stringBuffer.append(exp);
    stringBuffer.append(TEXT_2);
    
		}else{
			//fill postorder expression
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(exp);
    
		}
		if(useStrategy){			
    stringBuffer.append(TEXT_4);
    stringBuffer.append(aggregationStrategy);
    stringBuffer.append(TEXT_5);
    	}
		if(arallelProcessing){		
    stringBuffer.append(TEXT_6);
    								}
		if(stopOnException){		
    stringBuffer.append(TEXT_7);
    									}
		if(streaming){				
    stringBuffer.append(TEXT_8);
    										}
		if(shareUnitOfWork){		
    stringBuffer.append(TEXT_9);
    									}
		if(hasTimeout){				
    stringBuffer.append(TEXT_10);
    stringBuffer.append(timeout);
    stringBuffer.append(TEXT_11);
    								}
	}

    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
}