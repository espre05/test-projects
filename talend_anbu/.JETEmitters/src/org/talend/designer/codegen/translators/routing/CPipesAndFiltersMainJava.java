package org.talend.designer.codegen.translators.routing;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import java.util.Map;

public class CPipesAndFiltersMainJava
{
  protected static String nl;
  public static synchronized CPipesAndFiltersMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CPipesAndFiltersMainJava result = new CPipesAndFiltersMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t.pipeline(";
  protected final String TEXT_2 = NL + "\t\t\t\t\t";
  protected final String TEXT_3 = NL + "\t\t\t\t\t,";
  protected final String TEXT_4 = NL + "\t\t\t)";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	List<Map<String, String>> uris = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__URIS__");
	int urisCount = uris.size();
	
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {

    stringBuffer.append(TEXT_1);
    
		if(urisCount>0) {
			boolean isFirstUri = true;
			for(Map<String, String> anUri : uris) {
			
				if(isFirstUri) {
					isFirstUri=false;

    stringBuffer.append(TEXT_2);
    stringBuffer.append(anUri.get("URI"));
    
				} else {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(anUri.get("URI"));
    
				}
			}
		}

    stringBuffer.append(TEXT_4);
    
	} 

    return stringBuffer.toString();
  }
}
