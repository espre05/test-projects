package org.talend.designer.codegen.translators.orchestration;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CDelayerMainJava
{
  protected static String nl;
  public static synchronized CDelayerMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CDelayerMainJava result = new CDelayerMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.delay(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String timeToWait = ElementParameterParser.getValue(node, "__WAIT__");
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {
		if(timeToWait!=null && timeToWait.length()>0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(Integer.parseInt(timeToWait));
    stringBuffer.append(TEXT_2);
    
		}
	}

    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
