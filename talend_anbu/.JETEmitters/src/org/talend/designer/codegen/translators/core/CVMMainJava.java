package org.talend.designer.codegen.translators.core;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.CamelEndpointBuilder;
import java.util.List;
import java.util.Map;

public class CVMMainJava
{
  protected static String nl;
  public static synchronized CVMMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CVMMainJava result = new CVMMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t.to(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL + "\t\tfrom(";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	INode consumerNode = node;
	CamelEndpointBuilder builder = CamelEndpointBuilder.getBuilder();
	
	boolean inputName="true".equals(ElementParameterParser.getValue(node, "____INPUT_ENDPOINT_NAME____"));
	boolean asProductor = !node.getIncomingConnections().isEmpty();
	if(asProductor && (!inputName)){
		Object desNode= ElementParameterParser.getValue(node, "____EXIST_ENDPOINT____");
		if(desNode!=null){
			String desNodeName = desNode.toString();
			for(INode aNode : node.getProcess().getGraphicalNodes()){
				if(aNode.getUniqueName().equals(desNodeName)){
					consumerNode=aNode;
					break;
				}
			}
		}
	}
	
	String componentName = node.getComponent().getName();

	builder.setComponent("vm");
	builder.useDoubleSlash(false);
	builder.setName(ElementParameterParser.getValue(consumerNode, "__NAME__"));
	if(!inputName){
		//consumer
		boolean  specifySize = ElementParameterParser.getBooleanValue(node, "__SPECIFY_SIZE__");
		if(specifySize){
			String size = ElementParameterParser.getValue(node, "__SIZE__");
			builder.addParamIfNotDefault("size", size, "0");
		}
		
		String concurCnsmrs = ElementParameterParser.getValue(node, "__CONCURRENT_CONSUMERS__");
		builder.addParamIfNotDefault("concurrentConsumers", concurCnsmrs, "1");

		String waitForTaskToComplete = ElementParameterParser.getValue(node, "__WAIT_FOR_TASK_TO_COMPLETE__");
		builder.addParamIfNotDefault("waitForTaskToComplete", waitForTaskToComplete, "\"IfReplyExpected\"");

		String timeout = ElementParameterParser.getValue(node, "__TIMEOUT__");
		builder.addParamIfNotDefault("timeout", timeout, "30000");

		String multipleConsumers = ElementParameterParser.getValue(node, "__MULTIPLE_CONSUMERS__");
		builder.addParamIfNotDefault("multipleConsumers", multipleConsumers, "false"); 

		String limitConcurrentConsumers = ElementParameterParser.getValue(node, "__LIMIT_CONCURRENT_CONSUMERS__");
		builder.addParamIfNotDefault("limitConcurrentConsumers", limitConcurrentConsumers, "true");

		String blockWhenFull = ElementParameterParser.getValue(node, "__BLOCK_WHEN_FULL__");
		builder.addParamIfNotDefault("blockWhenFull", blockWhenFull, "false");

		String pollTimeout = ElementParameterParser.getValue(node, "__POLLTIMEOUT__");
		builder.addParamIfNotDefault("pollTimeout", pollTimeout, "1000");

		List<Map<String, String>> tableValues = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__ADVARGUMENTS__");
		builder.addParams(tableValues);
	}

	String uri = builder.build();
	if(asProductor) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_2);
    
	} else {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_4);
    
	}

    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
