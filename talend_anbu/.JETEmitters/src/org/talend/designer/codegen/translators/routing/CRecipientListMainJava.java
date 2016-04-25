package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import org.talend.designer.codegen.config.NodeParamsHelper;

public class CRecipientListMainJava {

  protected static String nl;
  public static synchronized CRecipientListMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CRecipientListMainJava result = new CRecipientListMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL;
  protected final String TEXT_2 = NL + "\t\t\t.recipientList(";
  protected final String TEXT_3 = NL + "\t\t\t";
  protected final String TEXT_4 = NL + "\t\t\t).";
  protected final String TEXT_5 = ".aggregationStrategyRef(";
  protected final String TEXT_6 = ")\t\t\t\t";
  protected final String TEXT_7 = ".parallelProcessing()\t\t\t\t\t\t\t";
  protected final String TEXT_8 = ".executorService(";
  protected final String TEXT_9 = ")\t\t\t";
  protected final String TEXT_10 = ".stopOnException()\t\t\t\t\t\t\t\t";
  protected final String TEXT_11 = ".ignoreInvalidEndpoints()\t\t\t\t\t\t\t";
  protected final String TEXT_12 = ".streaming()\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_13 = ".timeout( ";
  protected final String TEXT_14 = " )\t\t\t\t\t\t\t\t";
  protected final String TEXT_15 = ".onPrepareRef(";
  protected final String TEXT_16 = ")\t\t";
  protected final String TEXT_17 = ".shareUnitOfWork()\t\t\t\t\t\t\t\t";
  protected final String TEXT_18 = NL;

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
    stringBuffer.append(TEXT_1);
     
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	NodeParamsHelper paramsHelper = new NodeParamsHelper(node);

	String cid = node.getUniqueName();
	
	String language = paramsHelper.getVisibleStringParam("__LANGUAGES__");
	String expression = paramsHelper.getVisibleStringParam("__EXPRESSION__");
	boolean useResultClassType = paramsHelper.getBoolParam("__USE_RESULT_CLASS_TYPE__");
	String resultClassType = paramsHelper.getVisibleStringParam("__RESULT_CLASS_TYPE__");
	boolean useNamespaces = paramsHelper.getBoolParam( "__USE_NAMESPACES__");

	boolean useSpecifyDelimiter = paramsHelper.getBoolParam( "__USE_DELIMITER__");
	String delimiter = paramsHelper.getVisibleStringParam( "__DELIMITER__");
	
	boolean useStrategy = paramsHelper.getBoolParam( "__USE_STRATEGY__");
	String strategyRef = paramsHelper.getVisibleStringParam( "__STRATEGY_REF__");
	
	
	boolean parallelProcessing = paramsHelper.getBoolParam( "__PARELLEL_PROCESS__");
	boolean useExecutorService = parallelProcessing && paramsHelper.getBoolParam( "__USE_EXECUTOR_SERVICE__");
	String executorService = paramsHelper.getVisibleStringParam( "__EXECUTOR_SERVICE__");
	
	
	boolean stopOnException = paramsHelper.getBoolParam( "__STOP_ON_EXCEPTION__");
	boolean  ignoreInvalid = paramsHelper.getBoolParam( "__IGNORE_INVALID__");
	boolean streaming = paramsHelper.getBoolParam( "__STREAMING__");
	String timeout = paramsHelper.getVisibleStringParam( "__TIMEOUT__");
	boolean useTimeout = ((timeout!=null)&&(timeout.equals("\\s*\\d+\\s*")));
	
	boolean useOnPrepareProcessor = paramsHelper.getBoolParam("__USE_ON_PREPARE_PROCESSOR__");
	String onPrepareProcessor = paramsHelper.getVisibleStringParam("__ON_PREPARE_PROCESSOR__");
	
	boolean shareUnitOfWork = paramsHelper.getBoolParam( "__SHARE_UNIT_OF_WORK__");
	
	
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {
		String exp = getExpression(language, expression, cid, new Boolean(useNamespaces).toString(), resultClassType);

    stringBuffer.append(TEXT_2);
    			if(useSpecifyDelimiter) {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(delimiter);
    			}

    stringBuffer.append(TEXT_4);
    stringBuffer.append(exp);
    	
			//then append suffix settings.
			if(useStrategy){
    stringBuffer.append(TEXT_5);
    stringBuffer.append(strategyRef);
    stringBuffer.append(TEXT_6);
    }
			if(parallelProcessing){
    stringBuffer.append(TEXT_7);
    }
			if(useExecutorService){
    stringBuffer.append(TEXT_8);
    stringBuffer.append(executorService);
    stringBuffer.append(TEXT_9);
    }
			if(stopOnException){
    stringBuffer.append(TEXT_10);
    }
			if(ignoreInvalid){
    stringBuffer.append(TEXT_11);
    }
			if(streaming){
    stringBuffer.append(TEXT_12);
    }
			if(useTimeout){
    stringBuffer.append(TEXT_13);
    stringBuffer.append(timeout);
    stringBuffer.append(TEXT_14);
    }
			if(useOnPrepareProcessor){
    stringBuffer.append(TEXT_15);
    stringBuffer.append(onPrepareProcessor);
    stringBuffer.append(TEXT_16);
    }
			if(shareUnitOfWork){
    stringBuffer.append(TEXT_17);
    }
			
		
	}//finish all suffix settings.

    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}