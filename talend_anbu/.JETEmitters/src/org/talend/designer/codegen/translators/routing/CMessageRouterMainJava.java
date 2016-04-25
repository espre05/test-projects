package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CMessageRouterMainJava
{
  protected static String nl;
  public static synchronized CMessageRouterMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CMessageRouterMainJava result = new CMessageRouterMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t.choice()";
  protected final String TEXT_2 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();

	List< ? extends IConnection> conns = node.getIncomingConnections();
	
	
	if(conns.size()>0) {

    stringBuffer.append(TEXT_1);
    
	}

    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
