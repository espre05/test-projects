package org.talend.designer.codegen.translators.esb.webservices;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import java.util.Map;

public class TESBProviderResponseMainJava
{
  protected static String nl;
  public static synchronized TESBProviderResponseMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TESBProviderResponseMainJava result = new TESBProviderResponseMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tSystem.out.println(\"[WARN] nonsense: tESBProviderResponse component used without tESBProviderRequest component on the job\");";
  protected final String TEXT_2 = NL + "\troutines.system.Document esbProviderResponseDoc_";
  protected final String TEXT_3 = " = ";
  protected final String TEXT_4 = ".payload;" + NL + "" + NL + "\tESBProviderCallbackTalendJobInner esbProviderCallback_";
  protected final String TEXT_5 = " = (ESBProviderCallbackTalendJobInner) globalMap.get(\"esbHandler\");" + NL + "\tif (null != esbProviderCallback_";
  protected final String TEXT_6 = ") {" + NL + "\t\tjava.util.Map<String, String> customProps_";
  protected final String TEXT_7 = " = null;";
  protected final String TEXT_8 = NL + "\t\t\tcustomProps_";
  protected final String TEXT_9 = " = new java.util.HashMap<String, String>();" + NL + "\t\t\t";
  protected final String TEXT_10 = NL + "\t\t\tcustomProps_";
  protected final String TEXT_11 = ".put(";
  protected final String TEXT_12 = ", ";
  protected final String TEXT_13 = ");" + NL + "\t\t\t";
  protected final String TEXT_14 = NL + "\t\t";
  protected final String TEXT_15 = NL + NL + "\t\tesbProviderCallback_";
  protected final String TEXT_16 = ".setCustomProperties(customProps_";
  protected final String TEXT_17 = ");" + NL + "\t\tesbProviderCallback_";
  protected final String TEXT_18 = ".sendResponse(esbProviderResponseDoc_";
  protected final String TEXT_19 = ".getDocument());" + NL + "\t}";
  protected final String TEXT_20 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();

if (node.getProcess().getNodesOfType("tESBProviderRequestLoop").isEmpty()) { 
    stringBuffer.append(TEXT_1);
     } else {
	String cid = node.getUniqueName();
	List<IMetadataTable> metadatas = node.getMetadataList();
	if (null != metadatas && 0 < metadatas.size()) {
		IMetadataTable metadata = metadatas.get(0);
		if (null != metadata) {
			List<? extends IConnection> conns = node.getIncomingConnections();
			if (null != conns && 0 < conns.size()) {
				IConnection conn = conns.get(0);
				if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
					IMetadataColumn column = conn.getMetadataTable().getColumn("payload");
					if (null != column && "id_Document".equals(column.getTalendType())) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
		// set SAM custom properties
		List<Map<String, String>> customProperties = (List<Map<String,String>>)
				ElementParameterParser.getObjectValue(node, "__SERVICE_ACTIVITY_CUSTOM_PROPERTIES__");
		if (!customProperties.isEmpty()) { 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
     for (int k = 0; k < customProperties.size(); k++) { 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(customProperties.get(k).get("PROP_NAME"));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(customProperties.get(k).get("PROP_VALUE"));
    stringBuffer.append(TEXT_13);
     } 
    stringBuffer.append(TEXT_14);
     } 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    
					} // end if (null != column && "id_Document".equals(column.getTalendType()))
				} // end if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
			} // end if (null != conns && 0 < conns.size())
		} // end if (null != metadata)
	} // end if (null != metadatas && 0 < metadatas.size())
}

    stringBuffer.append(TEXT_20);
    return stringBuffer.toString();
  }
}
