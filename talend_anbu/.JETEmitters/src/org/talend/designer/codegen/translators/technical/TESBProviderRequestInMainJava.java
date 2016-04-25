package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class TESBProviderRequestInMainJava
{
  protected static String nl;
  public static synchronized TESBProviderRequestInMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TESBProviderRequestInMainJava result = new TESBProviderRequestInMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t\t\t// esbHandler_";
  protected final String TEXT_3 = ".sendResponse(null);";
  protected final String TEXT_4 = NL + NL + "\t\t\troutines.system.Document talendDocument_";
  protected final String TEXT_5 = " = new routines.system.Document();" + NL + "\t\t\ttalendDocument_";
  protected final String TEXT_6 = ".setDocument(requestMessage_";
  protected final String TEXT_7 = ");" + NL + "\t\t\t";
  protected final String TEXT_8 = ".payload = talendDocument_";
  protected final String TEXT_9 = ";";
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
cid = cid.replaceAll("_In", "");

boolean isOneWay = (node.getProcess().getNodesOfType("tESBProviderFault").isEmpty()
	&& node.getProcess().getNodesOfType("tESBProviderResponse").isEmpty());

List<IMetadataTable> metadatas = node.getMetadataList();
if (null != metadatas && 0 < metadatas.size()) {
	IMetadataTable metadata = metadatas.get(0);
	if (null != metadata) {
		List<? extends IConnection> conns = node.getOutgoingSortedConnections();
		if(null != conns && 0 < conns.size()) {
			for(IConnection conn : conns) {
				if(!conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
					continue;
				}

    stringBuffer.append(TEXT_1);
     if (isOneWay) { 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
     } 
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    
			}
		}
	}
}

    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
