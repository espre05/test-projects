package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CDynamicRouterMainJava
{
  protected static String nl;
  public static synchronized CDynamicRouterMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CDynamicRouterMainJava result = new CDynamicRouterMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.dynamicRouter(method(";
  protected final String TEXT_2 = "))";
  protected final String TEXT_3 = NL + "\t\t\t.dynamicRouter(method(";
  protected final String TEXT_4 = ", ";
  protected final String TEXT_5 = "))";
  protected final String TEXT_6 = NL + "\t\t\t.ignoreInvalidEndpoints()";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String beanClass = ElementParameterParser.getValue(node, "__BEAN__");
	String specifyMethod = ElementParameterParser.getValue(node, "__SPECIFY_METHOD__");
	String method = ElementParameterParser.getValue(node, "__METHOD__");
	String ignore = ElementParameterParser.getValue(node, "__IGNORE_INVALID_ENDPOINTS__");
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {
		if("false".equals(specifyMethod)) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(beanClass);
    stringBuffer.append(TEXT_2);
    
		} else {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(beanClass);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(method);
    stringBuffer.append(TEXT_5);
    
		}
		if("true".equals(ignore)) {

    stringBuffer.append(TEXT_6);
    
		}
	}

    return stringBuffer.toString();
  }
}
