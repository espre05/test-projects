package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import java.util.Map;

public class CLoadBalancerMainJava {

  protected static String nl;
  public static synchronized CLoadBalancerMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CLoadBalancerMainJava result = new CLoadBalancerMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.loadBalance().";
  protected final String TEXT_2 = "(";
  protected final String TEXT_3 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.ELExpression(";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.GroovyExpression(";
  protected final String TEXT_6 = ")";
  protected final String TEXT_7 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.JavaScriptExpression(";
  protected final String TEXT_8 = ")";
  protected final String TEXT_9 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.SqlExpression(";
  protected final String TEXT_10 = ")";
  protected final String TEXT_11 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.JsonPathExpression(";
  protected final String TEXT_12 = ")";
  protected final String TEXT_13 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.JXPathExpression(";
  protected final String TEXT_14 = ")";
  protected final String TEXT_15 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.MvelExpression(";
  protected final String TEXT_16 = ")";
  protected final String TEXT_17 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.OgnlExpression(";
  protected final String TEXT_18 = ")";
  protected final String TEXT_19 = "\t\t\t" + NL + "\t\t\t\t\tnew org.apache.camel.model.language.PhpExpression(";
  protected final String TEXT_20 = ")";
  protected final String TEXT_21 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.PythonExpression(";
  protected final String TEXT_22 = ")";
  protected final String TEXT_23 = "\t\t\t" + NL + "\t\t\t\t\tnew org.apache.camel.model.language.RubyExpression(";
  protected final String TEXT_24 = ")";
  protected final String TEXT_25 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.SpELExpression(";
  protected final String TEXT_26 = ")";
  protected final String TEXT_27 = NL + "\t\t\t\t\t";
  protected final String TEXT_28 = "ns.xpath(";
  protected final String TEXT_29 = ")";
  protected final String TEXT_30 = NL + "\t\t\t\t\tnew org.apache.camel.model.language.XQueryExpression(";
  protected final String TEXT_31 = ")";
  protected final String TEXT_32 = NL + "\t\t\t\t\t";
  protected final String TEXT_33 = NL + "\t\t\t\t\t\t\t\t\t,";
  protected final String TEXT_34 = NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_35 = ".class";
  protected final String TEXT_36 = NL + "\t\t\t\t\t\t\t-1";
  protected final String TEXT_37 = NL + "\t\t\t\t\t\t\t0";
  protected final String TEXT_38 = NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_39 = NL + "\t\t\t\t\t\t, ";
  protected final String TEXT_40 = ", ";
  protected final String TEXT_41 = "\t\t\t" + NL + "\t\t\t)";
  protected final String TEXT_42 = NL + "\t\t\t.loadBalance(new ";
  protected final String TEXT_43 = "())";

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
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	
	String strategy = ElementParameterParser.getValue(node, "__STRATEGY__");
	String language = ElementParameterParser.getValue(node, "__LANGUAGES__");
	String expression = ElementParameterParser.getValue(node, "__EXPRESSION__");
	String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");
	
	String exceptionMode = ElementParameterParser.getValue(node, "__EXCEPTION_MODE__");
	String roundRobinMode = ElementParameterParser.getValue(node, "__ROUND_ROBIN_MODE__");
	
	String maxFailAttempt = ElementParameterParser.getValue(node, "__MAXFAILATTEMPT__");
	
	String inherit = ElementParameterParser.getValue(node, "__INHERIT_ERROR_HANDLER__");
	String useRoundRobin = ElementParameterParser.getValue(node, "__USE_ROUND_ROBIN__");
	
	String attemptNumber = ElementParameterParser.getValue(node, "__ATTEMPT_NUMBER__");
	
	String customBalancer = ElementParameterParser.getValue(node, "__CUSTOM_LOAD_BALANCER__");
	
	List<Map<String, String>> exceptions = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__EXCEPTIONS__");
	
		
	if(conns.size()>0) { //BEGIN 1
		if(!("custom".equals(strategy))) { //BEGIN 2

    stringBuffer.append(TEXT_1);
    stringBuffer.append(strategy);
    stringBuffer.append(TEXT_2);
    
				if("sticky".equals(strategy)) { //BEGIN STICKY
					if ("el".equals(language)) {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_4);
    
					} else if ("groovy".equals(language)) {

    stringBuffer.append(TEXT_5);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_6);
    
					} else if ("javascript".equals(language)) {

    stringBuffer.append(TEXT_7);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_8);
    
					} else if ("sql".equals(language)) {

    stringBuffer.append(TEXT_9);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_10);
    
					} else if ("jsonpath".equals(language)) {

    stringBuffer.append(TEXT_11);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_12);
    
					} else if ("jxpath".equals(language)) {

    stringBuffer.append(TEXT_13);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_14);
    
					} else if ("mvel".equals(language)) {

    stringBuffer.append(TEXT_15);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_16);
    
					} else if ("ognl".equals(language)) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_18);
    
					} else if ("php".equals(language)) {

    stringBuffer.append(TEXT_19);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_20);
    
					} else if ("python".equals(language)) {

    stringBuffer.append(TEXT_21);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_22);
    
					} else if ("ruby".equals(language)) {

    stringBuffer.append(TEXT_23);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_24);
    
					} else if ("spel".equals(language)) {

    stringBuffer.append(TEXT_25);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_26);
    
					} else if ("xpath".equals(language)) {

    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_29);
    
					} else if ("xquery".equals(language)) {

    stringBuffer.append(TEXT_30);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_31);
    
					} else {
						String exp = getExpression(language, expression, cid, useNamespaces, ""); 

    stringBuffer.append(TEXT_32);
    stringBuffer.append(exp);
    
					}
				} // END STICKY
				if("failover".equals(strategy)) { //BEGIN FAILOVER
					if("true".equals(exceptionMode)) {
						boolean isFirstException = true;
						if(exceptions.size()>0) {
							for(Map<String, String> anException : exceptions) {
								if(!isFirstException) {

    stringBuffer.append(TEXT_33);
    
								}
								isFirstException=false;

    stringBuffer.append(TEXT_34);
    stringBuffer.append(anException.get("EXCEPTION"));
    stringBuffer.append(TEXT_35);
    
							}
						}
					}
					if("true".equals(roundRobinMode)) {
						if("ALWAYS".equals(maxFailAttempt)) {

    stringBuffer.append(TEXT_36);
    
						}
						if("NEVER".equals(maxFailAttempt)) {

    stringBuffer.append(TEXT_37);
    
						}
						if("SOMETIMES".equals(maxFailAttempt)) {

    stringBuffer.append(TEXT_38);
    stringBuffer.append(Integer.parseInt(attemptNumber));
    
						}

    stringBuffer.append(TEXT_39);
    stringBuffer.append("true".equals(inherit)?true:false);
    stringBuffer.append(TEXT_40);
    stringBuffer.append("true".equals(useRoundRobin)?true:false);
    
						
					}
				} //END FAILOVER
					
			

    stringBuffer.append(TEXT_41);
    
		} else {

    stringBuffer.append(TEXT_42);
    stringBuffer.append(customBalancer);
    stringBuffer.append(TEXT_43);
    
		} //END 2
	} //END 1

    return stringBuffer.toString();
  }
}