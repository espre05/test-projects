package org.talend.designer.codegen.translators.custom;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CProcessorMainJava
{
  protected static String nl;
  public static synchronized CProcessorMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CProcessorMainJava result = new CProcessorMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t.process(new org.apache.camel.Processor(){" + NL + "\t\tpublic void process(org.apache.camel.Exchange exchange) throws Exception{" + NL + "\t\t\t";
  protected final String TEXT_2 = NL + "\t\t}" + NL + "\t\t" + NL + "\t})" + NL + "\t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String rawCode = ElementParameterParser.getValue(node, "__CODE__");
	
	String code = null;
	String[] splitted = rawCode.trim().split("\n");
	String lastLine = splitted[splitted.length - 1];
	
	if ( lastLine.endsWith(";") ||     // Maybe there is a semicolon already 
	     lastLine.contains("//") ||    // If last line is comment -- then it's OK, we don't need
	     lastLine.endsWith("*/") ) {   // to search for semicolon.
		code = rawCode;
	} else  {
		code = rawCode + ";";
	}
		
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(code);
    stringBuffer.append(TEXT_2);
    }
    return stringBuffer.toString();
  }
}
