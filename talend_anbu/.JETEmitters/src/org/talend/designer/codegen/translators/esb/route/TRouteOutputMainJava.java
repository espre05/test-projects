package org.talend.designer.codegen.translators.esb.route;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.List;
import java.util.Map;

public class TRouteOutputMainJava
{
  protected static String nl;
  public static synchronized TRouteOutputMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRouteOutputMainJava result = new TRouteOutputMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + NL + "// tRouterOutput code" + NL;
  protected final String TEXT_2 = NL + "\t\t                routerExchange.getIn().setBody(";
  protected final String TEXT_3 = ".";
  protected final String TEXT_4 = ".getDocument().asXML(), org.w3c.dom.Document.class);" + NL + "\t\t            \t";
  protected final String TEXT_5 = NL + "\t\t                routerExchange.getIn().setBody(";
  protected final String TEXT_6 = ".";
  protected final String TEXT_7 = ", ";
  protected final String TEXT_8 = ".class);" + NL + "\t\t            \t";
  protected final String TEXT_9 = NL + "\t            \trouterExchange.getIn().setHeader(";
  protected final String TEXT_10 = ", ";
  protected final String TEXT_11 = ".";
  protected final String TEXT_12 = ");" + NL + "\t            \t";
  protected final String TEXT_13 = NL + "\t            \trouterExchange.setProperty(";
  protected final String TEXT_14 = ", ";
  protected final String TEXT_15 = ".";
  protected final String TEXT_16 = ");" + NL + "\t            \t";
  protected final String TEXT_17 = NL + "\t            \tSystem.setProperty(";
  protected final String TEXT_18 = ", ";
  protected final String TEXT_19 = ".";
  protected final String TEXT_20 = " == null? null:";
  protected final String TEXT_21 = ".";
  protected final String TEXT_22 = ".toString());" + NL + "\t            \t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode) codeGenArgument.getArgument();
String cid = node.getUniqueName();

List<Map<String, String>> tableValues =
    (List<Map<String,String>>)ElementParameterParser.getObjectValue(
        node,
        "__VALUES__"
    );

List< ? extends IConnection> conns = node.getIncomingConnections();
String firstConnName = "";	

for(IConnection conn : conns) {
    if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
 		firstConnName = conn.getName();
 		break;
    }
}

List<IMetadataTable> metadatas = node.getMetadataList();
IMetadataTable metadata = null;
if ((metadatas!=null)&&(metadatas.size()>0)) {
	metadata = metadatas.get(0);    
}

    stringBuffer.append(TEXT_1);
    
    for(IConnection conn : conns) { // 1
        if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {  // 2
        	boolean hasJudgedHeaders = false;
        	StringBuilder bodySb = new StringBuilder();
        	
            for(Map<String, String> tableValue : tableValues) { // 3
            	String label = tableValue.get("SCHEMA_COLUMN");
	            String value = tableValue.get("VALUE");
	            String type = tableValue.get("TYPE");
	            //System.out.println(type);
	            IMetadataColumn column = metadata.getColumn(label);
	            String talendType = column.getTalendType();
	            JavaType javaType = JavaTypesManager.getJavaTypeFromId(talendType);
	            String typeToGenerate = JavaTypesManager.getTypeToGenerate(javaType, true);
	            if("Body".equals(type)){
	                // http://camel.apache.org/using-getin-or-getout-methods-on-exchange.html
	            	if("id_Document".equals(talendType)){
	            		
    stringBuffer.append(TEXT_2);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    
	            	}else{
	            		
    stringBuffer.append(TEXT_5);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_8);
    
	            	}
	            }else if("Header".equals(type)){
	            	
    stringBuffer.append(TEXT_9);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_12);
    
	            }else if("Property".equals(type)){
	            	
    stringBuffer.append(TEXT_13);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_16);
    
	            }else if("System".equals(type)){
	            	
    stringBuffer.append(TEXT_17);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_22);
    
	            }
        } // 3
    } // 2
} // 1


    return stringBuffer.toString();
  }
}
