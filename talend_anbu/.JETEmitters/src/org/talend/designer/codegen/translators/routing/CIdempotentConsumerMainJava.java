package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CIdempotentConsumerMainJava {

  protected static String nl;
  public static synchronized CIdempotentConsumerMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CIdempotentConsumerMainJava result = new CIdempotentConsumerMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.idempotentConsumer(";
  protected final String TEXT_2 = ",";
  protected final String TEXT_3 = NL + "\t\t\t\t.idempotentConsumer(";
  protected final String TEXT_4 = ",";
  protected final String TEXT_5 = NL + "\t\t\torg.apache.camel.processor.idempotent.MemoryIdempotentRepository.memoryIdempotentRepository(";
  protected final String TEXT_6 = ")";
  protected final String TEXT_7 = NL + "\t\t\torg.apache.camel.processor.idempotent.FileIdempotentRepository.fileIdempotentRepository(new java.io.File(";
  protected final String TEXT_8 = "), ";
  protected final String TEXT_9 = ")";
  protected final String TEXT_10 = NL + "\t\t).eager(";
  protected final String TEXT_11 = ").skipDuplicate(";
  protected final String TEXT_12 = ")";

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
	String expression = ElementParameterParser.getValue(node, "__EXPRESSION__");
	String useLanguage = ElementParameterParser.getValue(node, "__USE_LANGUAGE__");
	String language = ElementParameterParser.getValue(node, "__LANGUAGES__");
	String predicate = ElementParameterParser.getValue(node, "__PREDICATE__");
	String repository = ElementParameterParser.getValue(node, "__REPOSITORY_TYPE__");
	String cacheSize = ElementParameterParser.getValue(node, "__CACHE_SIZE__");
	String fileStore = ElementParameterParser.getValue(node, "__FILE_STORE__");
	String eager = ElementParameterParser.getValue(node, "__EAGER__");
	String skipDuplicate = ElementParameterParser.getValue(node, "__SKIP_DUPLICATE__");
	String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");
	
	boolean isEager = false;
	boolean isSkip = false;
	
	if("true".equals(eager)){
		isEager = true;
	}
	
	if("true".equals(skipDuplicate)){
		isSkip = true;
	}
	
	
	if(conns.size()>0) { //BEGIN 1
		if("false".equals(useLanguage)) { //BEGIN 2

    stringBuffer.append(TEXT_1);
    stringBuffer.append(expression);
    stringBuffer.append(TEXT_2);
    
		} else {
			String exp = getExpressionDefinition(language, predicate, cid, useNamespaces, "");

    stringBuffer.append(TEXT_3);
    stringBuffer.append(exp);
    stringBuffer.append(TEXT_4);
    
		} //END 2
		
		if("MEMORY".equals(repository)) { //BEGIN 2

    stringBuffer.append(TEXT_5);
    stringBuffer.append((cacheSize.length()>0 && !("".equals(cacheSize)))?Integer.parseInt(cacheSize):200);
    stringBuffer.append(TEXT_6);
    
		} else if("FILE".equals(repository)) {

    stringBuffer.append(TEXT_7);
    stringBuffer.append(fileStore);
    stringBuffer.append(TEXT_8);
    stringBuffer.append((cacheSize.length()>0 && !("".equals(cacheSize)))?Integer.parseInt(cacheSize):200);
    stringBuffer.append(TEXT_9);
    
		} //END 2

    stringBuffer.append(TEXT_10);
    stringBuffer.append(isEager);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(isSkip);
    stringBuffer.append(TEXT_12);
    
	} //END 1

    return stringBuffer.toString();
  }
}