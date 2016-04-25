package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CRoutingSlipMainJava
{
  protected static String nl;
  public static synchronized CRoutingSlipMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CRoutingSlipMainJava result = new CRoutingSlipMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t.routingSlip(header(";
  protected final String TEXT_2 = "), ";
  protected final String TEXT_3 = ")";
  protected final String TEXT_4 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String header = ElementParameterParser.getValue(node, "__HEADER__");
	String delimiter = ElementParameterParser.getValue(node, "__DELIMITER__");
	//String ignoreInvalidEndpoint = ElementParameterParser.getValue(node, "__IGNORE_INVALID__");
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(header);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(delimiter);
    stringBuffer.append(TEXT_3);
    
	}

    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}
