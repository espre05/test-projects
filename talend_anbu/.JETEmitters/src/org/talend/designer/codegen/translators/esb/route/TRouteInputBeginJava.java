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

public class TRouteInputBeginJava
{
  protected static String nl;
  public static synchronized TRouteInputBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRouteInputBeginJava result = new TRouteInputBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        \t            \t" + NL + "\t\t            \t\t";
  protected final String TEXT_2 = ".";
  protected final String TEXT_3 = " = ";
  protected final String TEXT_4 = ";        \t            \t" + NL + "\t\t            \t";
  protected final String TEXT_5 = NL + "            \t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode) codeGenArgument.getArgument();
String cid = node.getUniqueName();

String nodeLabel = node.getLabel();
if(nodeLabel == null){
	nodeLabel = cid;
}

List<Map<String, String>> tableValues =
    (List<Map<String,String>>)ElementParameterParser.getObjectValue(
        node,
        "__VALUES__"
    );

List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
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

    
    for(IConnection conn : conns) {
        if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) { 
        	boolean hasJudgedHeaders = false;
        	StringBuilder headersNotNullSb = new StringBuilder();
        	StringBuilder headersIsNullSb = new StringBuilder();
        	headersIsNullSb.append("if(this.routerExchange == null){");
        	headersNotNullSb.append("}else{");
			List<IMetadataColumn> columns = metadata.getListColumns();
			if(columns!=null&&columns.size()>0){
				for(Map<String, String> tableValue : tableValues) {
	            	String label = tableValue.get("SCHEMA_COLUMN");
		            String value = tableValue.get("VALUE");
		            IMetadataColumn column = metadata.getColumn(label);
		            String talendType = column.getTalendType();
		            JavaType javaType = JavaTypesManager.getJavaTypeFromId(talendType);
		            String typeToGenerate = JavaTypesManager.getTypeToGenerate(javaType, true);
		            if(value == null || value.length() == 0){ //use the default value
	            		String defaultValue = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, column.getDefault());
	            		
    stringBuffer.append(TEXT_1);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_4);
    
		            }else {
		            	if("id_Document".equals(talendType)){
		            		headersNotNullSb.append(conn.getName());
		            		headersNotNullSb.append(".");
		            		headersNotNullSb.append(label);
		            		headersNotNullSb.append(" = ParserUtils.parseTo_Document(org.apache.camel.builder.SimpleBuilder.simple(");
		            		headersNotNullSb.append(value);
		            		headersNotNullSb.append(").evaluate(routerExchange, String.class));\n");
		            	}else{
		            		headersNotNullSb.append(conn.getName());
		            		headersNotNullSb.append(".");
		            		headersNotNullSb.append(label);
		            		headersNotNullSb.append(" = org.apache.camel.builder.SimpleBuilder.simple(");
		            		headersNotNullSb.append(value);
		            		headersNotNullSb.append(").evaluate(routerExchange, ");
		            		headersNotNullSb.append(typeToGenerate);
		            		headersNotNullSb.append(".class);\n");
		            	}
		            	headersIsNullSb.append("System.err.println(\"ERROR: No input for ");
		            	headersIsNullSb.append(nodeLabel);
		            	headersIsNullSb.append(", You may need to start it Via a Route instead of running it directly!\");");
		            	headersIsNullSb.append(conn.getName());
		            	headersIsNullSb.append(" = null");
		            	//headersIsNullSb.append(".");
		            	//headersIsNullSb.append(label);
		            	//headersIsNullSb.append(JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, column.getDefault()));
		            	headersIsNullSb.append(";\n");
		            }
	            }
	            headersNotNullSb.append("}");

    stringBuffer.append(TEXT_5);
    stringBuffer.append(headersIsNullSb.toString());
    stringBuffer.append(headersNotNullSb.toString());
    
            }
    }
}

    return stringBuffer.toString();
  }
}
