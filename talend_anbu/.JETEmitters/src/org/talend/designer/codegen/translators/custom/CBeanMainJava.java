package org.talend.designer.codegen.translators.custom;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CBeanMainJava
{
  protected static String nl;
  public static synchronized CBeanMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CBeanMainJava result = new CBeanMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\t.beanRef(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL + "\t\t\t\t.beanRef(";
  protected final String TEXT_4 = ", ";
  protected final String TEXT_5 = ")";
  protected final String TEXT_6 = NL + "\t\t\t\t.bean(";
  protected final String TEXT_7 = ")";
  protected final String TEXT_8 = NL + "\t\t\t\t.bean(";
  protected final String TEXT_9 = ", ";
  protected final String TEXT_10 = ")";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String fromRegistry = ElementParameterParser.getValue(node, "__FROM_REGISTRY__");
	String id = ElementParameterParser.getValue(node, "__REF_ID__");
	
	String beanClass = ElementParameterParser.getValue(node, "__BEAN__");
	
	String specifyMethod = ElementParameterParser.getValue(node, "__SPECIFY_METHOD__");
	String method = ElementParameterParser.getValue(node, "__METHOD__");
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {
		if("true".equals(fromRegistry)){
			if("false".equals(specifyMethod)){

    stringBuffer.append(TEXT_1);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_2);
    
			}else{

    stringBuffer.append(TEXT_3);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(method);
    stringBuffer.append(TEXT_5);
    
			}
		}else{
			if("false".equals(specifyMethod)) {

    stringBuffer.append(TEXT_6);
    stringBuffer.append(beanClass);
    stringBuffer.append(TEXT_7);
    
			} else {

    stringBuffer.append(TEXT_8);
    stringBuffer.append(beanClass);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(method);
    stringBuffer.append(TEXT_10);
    
			}
		}
	}

    return stringBuffer.toString();
  }
}
