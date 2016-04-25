package org.talend.designer.codegen.translators.exceptionhandling;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IConnection;
import java.util.List;

public class CErrorHandlerMainJava
{
  protected static String nl;
  public static synchronized CErrorHandlerMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CErrorHandlerMainJava result = new CErrorHandlerMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t\t.";
  protected final String TEXT_3 = NL + "\t\t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	
	String errorHandler = ElementParameterParser.getValue(node, "__ERROR_HANDLER__");
	String deadLetter = ElementParameterParser.getValue(node, "__DEAD_LETTER__");
	String logHandler = ElementParameterParser.getValue(node, "__LOG_HANDLER__");
	
	StringBuilder sb = new StringBuilder();
	sb.append("errorHandler(");
	if("true".equals(errorHandler) || "true".equals(deadLetter)){
		if("true".equals(errorHandler)){
			sb.append("defaultErrorHandler()");
		}else if("true".equals(deadLetter)){
			String deadLetterUri = ElementParameterParser.getValue(node, "__DEAD_LETTER_URI__");
			sb.append("deadLetterChannel(");
			sb.append(deadLetterUri);
			sb.append(")");
		}
		String useMaxRedeliveries = ElementParameterParser.getValue(node, "__USE_MAX_REDELIVERIES__");
		if("true".equals(useMaxRedeliveries)){
			String maxRedeliveries = ElementParameterParser.getValue(node, "__MAX_REDELIVERIES__");
			sb.append(".maximumRedeliveries(");
			sb.append(maxRedeliveries);
			sb.append(")");
		}
		
		String useRedeliveryDelay = ElementParameterParser.getValue(node, "__USE_REDELIVERY_DELAY__");
		if("true".equals(useRedeliveryDelay)){
			String redeliveryDelay = ElementParameterParser.getValue(node, "__REDELIVERY_DELAY__");
			sb.append(".redeliveryDelay(");
			sb.append(redeliveryDelay);
			sb.append(")");
		}
		
		String useRetryAttempLogLevel = ElementParameterParser.getValue(node, "__USE_RETRY_ATTEMP_LOG_LEVEL__");
		if("true".equals(useRetryAttempLogLevel)){
			String attempLogLevel = ElementParameterParser.getValue(node, "__RETRY_ATTEMP_LOG_LEVEL__");
			sb.append(".retryAttemptedLogLevel(");
			sb.append("org.apache.camel.LoggingLevel.");
			sb.append(attempLogLevel);
			sb.append(")");
		}
		
		String asyncDelayedRedelivery = ElementParameterParser.getValue(node, "__ASYNC_DELAYED_REDELIVERY__");
		if("true".equals(asyncDelayedRedelivery)){
			sb.append(".asyncDelayedRedelivery()");
		}
		
		String useOriginalMessage = ElementParameterParser.getValue(node, "__USE_ORIGINAL_MESSAGE__");
		if("true".equals(useOriginalMessage) &&  "true".equals(deadLetter)){
			sb.append(".useOriginalMessage()");
		}
		
		String moreConfigurations = ElementParameterParser.getValue(node, "__MORE_CONFIGURATION__");
		if("true".equals(moreConfigurations)){
			String code = ElementParameterParser.getValue(node, "__CODE__");
			sb.append(code);
		}
	}else if("true".equals(logHandler)){
		sb.append("loggingErrorHandler()");
		String useLogName = ElementParameterParser.getValue(node, "__USE_LOG_NAME__");
		if("true".equals(useLogName)){
			String logName = ElementParameterParser.getValue(node, "__LOG_NAME__");
			sb.append(".logName(");
			sb.append(logName);
			sb.append(")");
		}
		String useLogLevel = ElementParameterParser.getValue(node, "__USE_LOG_LEVEL__");
		if("true".equals(useLogLevel)){
			String logLevel = ElementParameterParser.getValue(node, "__LOG_LEVEL__");
			sb.append(".level(");
			sb.append("org.apache.camel.LoggingLevel.");
			sb.append(logLevel);
			sb.append(")");
		}
	}
	sb.append("\n)");
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(sb.toString());
    
	} else {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(sb.toString());
    
	}

    return stringBuffer.toString();
  }
}
