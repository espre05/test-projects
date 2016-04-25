package org.talend.designer.codegen.translators.technical;

import java.util.List;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TRESTRequestInMainJava
{
  protected static String nl;
  public static synchronized TRESTRequestInMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRESTRequestInMainJava result = new TRESTRequestInMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t\tif (requestMessage_";
  protected final String TEXT_2 = ".containsKey(\"ERROR\")) { // wrong request received";
  protected final String TEXT_3 = NL + "\t\t\t\t\t\tString wrongCallUri_";
  protected final String TEXT_4 = " = (String) requestMessage_";
  protected final String TEXT_5 = ".get(\"URI\");" + NL + "\t\t\t\t\t\tString wrongCallMethod_";
  protected final String TEXT_6 = " = (String) requestMessage_";
  protected final String TEXT_7 = ".get(\"VERB\");" + NL + "\t\t\t\t\t\tString wrongCallError_";
  protected final String TEXT_8 = " = (String) requestMessage_";
  protected final String TEXT_9 = ".get(\"ERROR\");" + NL + "\t\t\t\t\t\tint wrongCallStatus_";
  protected final String TEXT_10 = " = (Integer) requestMessage_";
  protected final String TEXT_11 = ".get(\"STATUS\");" + NL + "" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_12 = " = new ";
  protected final String TEXT_13 = "Struct();" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_14 = ".uri = wrongCallUri_";
  protected final String TEXT_15 = ";" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_16 = ".method = wrongCallMethod_";
  protected final String TEXT_17 = ";" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_18 = ".error = wrongCallError_";
  protected final String TEXT_19 = ";" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_20 = ".status = wrongCallStatus_";
  protected final String TEXT_21 = ";";
  protected final String TEXT_22 = NL + "\t\t\t\t\t\t";
  protected final String TEXT_23 = " = null;";
  protected final String TEXT_24 = NL + "\t\t} else { // non-error (not wrong request)" + NL + "" + NL + "\t\t\tString matchedUriPattern_";
  protected final String TEXT_25 = " = (String) requestMessage_";
  protected final String TEXT_26 = ".get(\"PATTERN\");" + NL + "\t\t\tString matchedFlow_";
  protected final String TEXT_27 = " = (String) requestMessage_";
  protected final String TEXT_28 = ".get(\"OPERATION\");" + NL + "" + NL + "\t\t\tjava.util.Map<String, Object> params_";
  protected final String TEXT_29 = " =" + NL + "\t\t\t\t(java.util.Map<String, Object>) requestMessage_";
  protected final String TEXT_30 = ".get(\"PARAMS\");";
  protected final String TEXT_31 = NL + "\t\t\t\t\t\t";
  protected final String TEXT_32 = " = null;";
  protected final String TEXT_33 = NL + "\t\t\t\t\tif (matchedFlow_";
  protected final String TEXT_34 = ".equals(\"";
  protected final String TEXT_35 = "\")) {" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_36 = " = new ";
  protected final String TEXT_37 = "Struct();";
  protected final String TEXT_38 = NL + "\t\t\t\t\t\t\t\tObject bodyObject_";
  protected final String TEXT_39 = " = requestMessage_";
  protected final String TEXT_40 = ".get(\"BODY\");" + NL + "\t\t\t\t\t\t\t\tif (null != bodyObject_";
  protected final String TEXT_41 = ") {" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_42 = NL + "\t\t\t\t\t\t\t\t\t\troutines.system.Document body_";
  protected final String TEXT_43 = " = new routines.system.Document();" + NL + "\t\t\t\t\t\t\t\t\t\tbody_";
  protected final String TEXT_44 = ".setDocument((org.dom4j.Document) bodyObject_";
  protected final String TEXT_45 = ");" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_46 = ".body = body_";
  protected final String TEXT_47 = ";" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_48 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_49 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_50 = ".body = (";
  protected final String TEXT_51 = ") bodyObject_";
  protected final String TEXT_52 = ";" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_53 = NL + "\t\t\t\t\t\t\t\t}";
  protected final String TEXT_54 = NL + "\t\t\t\t\t\t\t\tif (params_";
  protected final String TEXT_55 = ".containsKey(\"";
  protected final String TEXT_56 = "\")) {" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_57 = NL + "\t\t\t\t\t\t\t\t\t\troutines.system.Document ";
  protected final String TEXT_58 = "_";
  protected final String TEXT_59 = " = new routines.system.Document();" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_60 = "_";
  protected final String TEXT_61 = ".setDocument((org.dom4j.Document) params_";
  protected final String TEXT_62 = ".get(\"";
  protected final String TEXT_63 = "\"));" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_64 = ".";
  protected final String TEXT_65 = " = ";
  protected final String TEXT_66 = "_";
  protected final String TEXT_67 = ";" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_68 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_69 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_70 = ".";
  protected final String TEXT_71 = " = (";
  protected final String TEXT_72 = ") params_";
  protected final String TEXT_73 = ".get(\"";
  protected final String TEXT_74 = "\");" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_75 = NL + "\t\t\t\t\t\t\t\t}";
  protected final String TEXT_76 = NL + "\t\t\t\t\t} else { // non matched flow" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_77 = " = null;" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t";
  protected final String TEXT_78 = NL + "\t\t}";
  protected final String TEXT_79 = NL + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_80 = "_URI\", (String) requestMessage_";
  protected final String TEXT_81 = ".get(\"URI\"));" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_82 = "_URI_BASE\", (String) requestMessage_";
  protected final String TEXT_83 = ".get(\"URI_BASE\"));" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_84 = "_URI_ABSOLUTE\", (String) requestMessage_";
  protected final String TEXT_85 = ".get(\"URI_ABSOLUTE\"));" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_86 = "_URI_REQUEST\", (String) requestMessage_";
  protected final String TEXT_87 = ".get(\"URI_REQUEST\"));" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_88 = "_HTTP_METHOD\", (String) requestMessage_";
  protected final String TEXT_89 = ".get(\"VERB\"));" + NL + "" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_90 = "_ATTACHMENT_HEADERS\", requestMessage_";
  protected final String TEXT_91 = ".get(\"ATTACHMENT_HEADERS\"));" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_92 = "_ATTACHMENT_FILENAMES\", requestMessage_";
  protected final String TEXT_93 = ".get(\"ATTACHMENT_FILENAMES\"));" + NL + "" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_94 = "_PRINCIPAL_NAME\", (String) requestMessage_";
  protected final String TEXT_95 = ".get(\"PRINCIPAL_NAME\"));" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_96 = "_CORRELATION_ID\", (String) requestMessage_";
  protected final String TEXT_97 = ".get(\"CorrelationID\"));";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
cid = cid.replaceAll("_In", "");

List<IMetadataTable> metadatas = node.getMetadataList();
if (null != metadatas && 0 < metadatas.size()) {
	IMetadataTable metadata = metadatas.get(0);
	if (null != metadata) { 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    
			List<? extends IConnection> connsAll = node.getOutgoingSortedConnections();
			if (null != connsAll && !connsAll.isEmpty()) {
				for (IConnection conn : connsAll) {
					if (!conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
						continue;
					}
					String connectionName = conn.getName();
					if ("WRONG_CALLS".equals(conn.getConnectorName())) { // "WRONG_CALL" flow

    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    
					} else { // not "WRONG_CALL" flow

    stringBuffer.append(TEXT_22);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_23);
    
					} // "WRONG_CALLS".equals(conn.getConnectorName()
				} // for (IConnection conn : connsAll)
			} // if (null != connsAll && !connsAll.isEmpty())

    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    
			List<? extends IConnection> conns = node.getOutgoingSortedConnections();
			if (null != conns && !conns.isEmpty()) {
				for (IConnection conn : conns) {
					if (!conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
						continue;
					}

					String connectionName = conn.getName();

					if ("WRONG_CALLS".equals(conn.getConnectorName())) { 
    stringBuffer.append(TEXT_31);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_32);
    
						continue;
					}

    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_37);
    
						for (IMetadataColumn connColumn : conn.getMetadataTable().getListColumns()) {
							String columnName = connColumn.getLabel();
							String schemaFieldType = connColumn.getTalendType();
							String restParameterType = connColumn.getComment();
							if (restParameterType == null || restParameterType.trim().length() == 0) {
								restParameterType = "";
							}

							if ("body".equals(columnName) && "".equals(restParameterType)) {

    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
     if ("id_Document".equals(schemaFieldType)) { 
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
     } else { 
    stringBuffer.append(TEXT_48);
     String javaTypeFull = JavaTypesManager.getTypeToGenerate(schemaFieldType, true); 
    stringBuffer.append(TEXT_49);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append( javaTypeFull);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
     } 
    stringBuffer.append(TEXT_53);
    
							} else {
								restParameterType = ("".equals(restParameterType)) ? "PATH" : restParameterType.trim().toUpperCase();
								String parameterKey = restParameterType + ":" + columnName + ":" + schemaFieldType;

    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(parameterKey);
    stringBuffer.append(TEXT_56);
     if ("id_Document".equals(schemaFieldType)) { 
    stringBuffer.append(TEXT_57);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(parameterKey);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
     } else { 
    stringBuffer.append(TEXT_68);
     String javaTypeFull = JavaTypesManager.getTypeToGenerate(schemaFieldType, true); 
    stringBuffer.append(TEXT_69);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_71);
    stringBuffer.append( javaTypeFull);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(parameterKey);
    stringBuffer.append(TEXT_74);
     } 
    stringBuffer.append(TEXT_75);
    
							} // if ("body".equals(columnName) && "".equals(restParameterType))
						} // for (IMetadataColumn connColumn : conn.getMetadataTable().getListColumns())

    stringBuffer.append(TEXT_76);
    stringBuffer.append(connectionName);
    stringBuffer.append(TEXT_77);
    
				} // for (IConnection conn : conns)
			} // if (null != conns && !conns.isEmpty())

    stringBuffer.append(TEXT_78);
    
	} // end of if (null != metadata)
} // end of if (null != metadatas && 0 < metadatas.size())

    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    return stringBuffer.toString();
  }
}
