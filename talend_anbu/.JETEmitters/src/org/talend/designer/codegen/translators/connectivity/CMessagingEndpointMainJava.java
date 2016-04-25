package org.talend.designer.codegen.translators.connectivity;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.components.IComponent;
import java.util.List;
import java.util.Map;

public class CMessagingEndpointMainJava
{
  protected static String nl;
  public static synchronized CMessagingEndpointMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CMessagingEndpointMainJava result = new CMessagingEndpointMainJava();
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
   	String cid = node.getUniqueName();
//  http://jira.talendforge.org/browse/TESB-5241
	/*
   	 * change to use label + unique to make it unique but readable
   	 */
//	IElementParameter param = node.getElementParameter("LABEL");
//	if(param != null && !"__UNIQUE_NAME__".equals(param.getValue())){
//		cid = (String)param.getValue()+"_"+cid;	
//	}
	String uri = ElementParameterParser.getValue(node, "__URI__");
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {

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
