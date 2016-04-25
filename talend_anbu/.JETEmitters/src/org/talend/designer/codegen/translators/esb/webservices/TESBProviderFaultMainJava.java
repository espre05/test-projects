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

public class TESBProviderFaultMainJava
{
  protected static String nl;
  public static synchronized TESBProviderFaultMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TESBProviderFaultMainJava result = new TESBProviderFaultMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tSystem.out.println(\"[WARN] nonsense: tESBProviderFault component used without tESBProviderRequest component on the job\");";
  protected final String TEXT_2 = NL + "\tString esbProviderFaultTitle_";
  protected final String TEXT_3 = " = ";
  protected final String TEXT_4 = " + \" [";
  protected final String TEXT_5 = "]\";" + NL + "\troutines.system.Document esbProviderFaultDoc_";
  protected final String TEXT_6 = " = null;";
  protected final String TEXT_7 = NL + "\tesbProviderFaultDoc_";
  protected final String TEXT_8 = " = ";
  protected final String TEXT_9 = ".payload;";
  protected final String TEXT_10 = NL + "\tESBProviderCallbackTalendJobInner esbProviderCallback_";
  protected final String TEXT_11 = " = (ESBProviderCallbackTalendJobInner) globalMap.get(\"esbHandler\");" + NL + "\tif (null != esbProviderCallback_";
  protected final String TEXT_12 = ") {" + NL + "\t\tjava.util.Map<String, String> customProps_";
  protected final String TEXT_13 = " = null;";
  protected final String TEXT_14 = NL + "\t\t\tcustomProps_";
  protected final String TEXT_15 = " = new java.util.HashMap<String, String>();" + NL + "\t\t\t";
  protected final String TEXT_16 = NL + "\t\t\tcustomProps_";
  protected final String TEXT_17 = ".put(";
  protected final String TEXT_18 = ", ";
  protected final String TEXT_19 = ");" + NL + "\t\t\t";
  protected final String TEXT_20 = NL + "\t\t";
  protected final String TEXT_21 = NL + NL + "\t\tesbProviderCallback_";
  protected final String TEXT_22 = ".setCustomProperties(customProps_";
  protected final String TEXT_23 = ");" + NL + "\t\tesbProviderCallback_";
  protected final String TEXT_24 = ".sendBusinessFault(" + NL + "\t\t\t\tesbProviderFaultTitle_";
  protected final String TEXT_25 = "," + NL + "\t\t\t\t(null == esbProviderFaultDoc_";
  protected final String TEXT_26 = ") ? null" + NL + "\t\t\t\t\t\t: esbProviderFaultDoc_";
  protected final String TEXT_27 = ".getDocument()" + NL + "\t\t\t);" + NL + "\t}";
  protected final String TEXT_28 = NL;

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

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append( ElementParameterParser.getValue(node, "__ESB_FAULT_TITLE__"));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    
					IMetadataColumn column = conn.getMetadataTable().getColumn("payload");
					if (null != column && "id_Document".equals(column.getTalendType())) {

    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_9);
    
					}

    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    
		// set SAM custom properties
		List<Map<String, String>> customProperties = (List<Map<String,String>>)
				ElementParameterParser.getObjectValue(node, "__SERVICE_ACTIVITY_CUSTOM_PROPERTIES__");
		if (!customProperties.isEmpty()) { 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
     for (int k = 0; k < customProperties.size(); k++) { 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(customProperties.get(k).get("PROP_NAME"));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(customProperties.get(k).get("PROP_VALUE"));
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
     } 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    
				} // end if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
			} // end if (null != conns && 0 < conns.size())
		} // end if (null != metadata)
	} // end if (null != metadatas && 0 < metadatas.size())
}

    stringBuffer.append(TEXT_28);
    return stringBuffer.toString();
  }
}
