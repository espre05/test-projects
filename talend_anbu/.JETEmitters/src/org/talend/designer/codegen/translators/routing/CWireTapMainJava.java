package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class CWireTapMainJava {

  protected static String nl;
  public static synchronized CWireTapMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CWireTapMainJava result = new CWireTapMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t.streamCaching().wireTap(";
  protected final String TEXT_2 = ").copy(true)";
  protected final String TEXT_3 = NL + "\t\t.wireTap(";
  protected final String TEXT_4 = ").copy(false)";
  protected final String TEXT_5 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.ELExpression(";
  protected final String TEXT_6 = "))";
  protected final String TEXT_7 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.GroovyExpression(";
  protected final String TEXT_8 = "))";
  protected final String TEXT_9 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.JavaScriptExpression(";
  protected final String TEXT_10 = "))";
  protected final String TEXT_11 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.SqlExpression(";
  protected final String TEXT_12 = "))";
  protected final String TEXT_13 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.JsonPathExpression(";
  protected final String TEXT_14 = "))";
  protected final String TEXT_15 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.JXPathExpression(";
  protected final String TEXT_16 = "))";
  protected final String TEXT_17 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.MvelExpression(";
  protected final String TEXT_18 = "))";
  protected final String TEXT_19 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.OgnlExpression(";
  protected final String TEXT_20 = "))";
  protected final String TEXT_21 = "\t\t\t" + NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.PhpExpression(";
  protected final String TEXT_22 = "))";
  protected final String TEXT_23 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.PythonExpression(";
  protected final String TEXT_24 = "))";
  protected final String TEXT_25 = "\t\t\t" + NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.RubyExpression(";
  protected final String TEXT_26 = "))";
  protected final String TEXT_27 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.SpELExpression(";
  protected final String TEXT_28 = "))";
  protected final String TEXT_29 = NL + "\t\t\t.newExchangeBody(";
  protected final String TEXT_30 = "ns.xpath(";
  protected final String TEXT_31 = "))";
  protected final String TEXT_32 = NL + "\t\t\t.newExchangeBody(new org.apache.camel.model.language.XQueryExpression(";
  protected final String TEXT_33 = "))";
  protected final String TEXT_34 = NL + "\t\t\t.newExchangeBody(";
  protected final String TEXT_35 = ")";
  protected final String TEXT_36 = NL + "\t\t\t.newExchange(new org.apache.camel.Processor() {" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\tpublic void process(org.apache.camel.Exchange exchange) throws Exception {" + NL + "\t\t\t\t\t\t// TODO Auto-generated method stub" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_37 = NL + "\t\t\t\t\t}" + NL + "\t\t\t\t})" + NL + "\t\t\t\t";
  protected final String TEXT_38 = NL;

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
	String URI = ElementParameterParser.getValue(node, "__URI__");
	boolean new_exchange  = "true".equals(ElementParameterParser.getValue(node, "__NEW_EXCHANGE__"));
	boolean copy_original_message  = "true".equals(ElementParameterParser.getValue(node, "__COPY_ORIGINAL_MESSAGE__"));
	String expression  = ElementParameterParser.getValue(node, "__EXPRESSION__");
	String processor  = ElementParameterParser.getValue(node, "__PROCESSOR__");
	String code = ElementParameterParser.getValue(node, "__CODE__");
	String language  = ElementParameterParser.getValue(node, "__LANGUAGES__");
	String expressiontxt  = ElementParameterParser.getValue(node, "__EXPRESSIONTXT__");
	String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");

	if(copy_original_message){

    stringBuffer.append(TEXT_1);
    stringBuffer.append(URI);
    stringBuffer.append(TEXT_2);
    
	}else{

    stringBuffer.append(TEXT_3);
    stringBuffer.append(URI);
    stringBuffer.append(TEXT_4);
    	
	}

	if(new_exchange){
		if("true".equals(expression)){
			if ("el".equals(language)) {

    stringBuffer.append(TEXT_5);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_6);
    
			} else if ("groovy".equals(language)) {

    stringBuffer.append(TEXT_7);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_8);
    
			} else if ("javascript".equals(language)) {

    stringBuffer.append(TEXT_9);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_10);
    
			} else if ("sql".equals(language)) {

    stringBuffer.append(TEXT_11);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_12);
    
			} else if ("jsonpath".equals(language)) {

    stringBuffer.append(TEXT_13);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_14);
    
			} else if ("jxpath".equals(language)) {

    stringBuffer.append(TEXT_15);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_16);
    
			} else if ("mvel".equals(language)) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_18);
    
			} else if ("ognl".equals(language)) {

    stringBuffer.append(TEXT_19);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_20);
    
			} else if ("php".equals(language)) {

    stringBuffer.append(TEXT_21);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_22);
    
			} else if ("python".equals(language)) {

    stringBuffer.append(TEXT_23);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_24);
    
			} else if ("ruby".equals(language)) {

    stringBuffer.append(TEXT_25);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_26);
    
			} else if ("spel".equals(language)) {

    stringBuffer.append(TEXT_27);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_28);
    
			} else if ("xpath".equals(language)) {

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_31);
    
			} else if ("xquery".equals(language)) {

    stringBuffer.append(TEXT_32);
    stringBuffer.append(expressiontxt);
    stringBuffer.append(TEXT_33);
    
			} else {
				String exp = getExpression(language, expressiontxt, cid, useNamespaces, ""); 

    stringBuffer.append(TEXT_34);
    stringBuffer.append(exp);
    stringBuffer.append(TEXT_35);
    
			}
		}else if("true".equals(processor)){

    stringBuffer.append(TEXT_36);
    stringBuffer.append(code);
    stringBuffer.append(TEXT_37);
    		
		}
}

    stringBuffer.append(TEXT_38);
    return stringBuffer.toString();
  }
}