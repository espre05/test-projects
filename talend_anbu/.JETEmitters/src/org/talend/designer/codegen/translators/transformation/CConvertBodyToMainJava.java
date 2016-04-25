package org.talend.designer.codegen.translators.transformation;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CConvertBodyToMainJava
{
  protected static String nl;
  public static synchronized CConvertBodyToMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CConvertBodyToMainJava result = new CConvertBodyToMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    \t\t.convertBodyTo(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String toClass = ElementParameterParser.getValue(node, "__TOCLASS__");
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {
	
    	if(toClass!=null && toClass.trim().length()!=0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(toClass);
    stringBuffer.append(TEXT_2);
    
    	}
	}

    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
