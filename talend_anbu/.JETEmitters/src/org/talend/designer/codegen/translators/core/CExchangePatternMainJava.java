package org.talend.designer.codegen.translators.core;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CExchangePatternMainJava
{
  protected static String nl;
  public static synchronized CExchangePatternMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CExchangePatternMainJava result = new CExchangePatternMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t.setExchangePattern(org.apache.camel.ExchangePattern.";
  protected final String TEXT_2 = ")";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String patterns  = ElementParameterParser.getValue(node, "__EXCHANGE_PATTERNS__");
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(patterns);
    stringBuffer.append(TEXT_2);
    
	}

    return stringBuffer.toString();
  }
}
