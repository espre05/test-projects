package org.talend.designer.codegen.translators.core;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.CamelEndpointBuilder;
import java.util.List;
import java.util.Map;

public class CDirectMainJava
{
  protected static String nl;
  public static synchronized CDirectMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CDirectMainJava result = new CDirectMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.to(";
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

	boolean asProductor = !node.getIncomingConnections().isEmpty();
	if(asProductor){
		Object desNode= ElementParameterParser.getValue(node, "____EXIST_ENDPOINT____");
		if(desNode!=null){
			String desNodeName = desNode.toString();	
			for(INode aNode : node.getProcess().getGraphicalNodes()) {
				if(aNode.getUniqueName().equals(desNodeName)){
					consumerNode=aNode;
					break;
				}
			}
		}
	}
	
	String directName = ElementParameterParser.getValue(consumerNode, "__NAME__");

	builder.setComponent("direct");
	builder.setName(directName);
	builder.useDoubleSlash(false);
	
	List<Map<String, String>> tableValues = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__ADVARGUMENTS__");
	builder.addParams(tableValues);

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
