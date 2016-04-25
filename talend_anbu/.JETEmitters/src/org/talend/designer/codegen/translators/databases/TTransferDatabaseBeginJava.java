package org.talend.designer.codegen.translators.databases;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.List;
import java.util.Map;

public class TTransferDatabaseBeginJava
{
  protected static String nl;
  public static synchronized TTransferDatabaseBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TTransferDatabaseBeginJava result = new TTransferDatabaseBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "java.sql.Connection connectionDatabaseSource_";
  protected final String TEXT_2 = " = (java.sql.Connection)globalMap.get(\"conn_";
  protected final String TEXT_3 = "\");" + NL + "java.sql.Connection connectionDatabaseDestine_";
  protected final String TEXT_4 = " = (java.sql.Connection)globalMap.get(\"conn_";
  protected final String TEXT_5 = "\");" + NL + "" + NL + "if (connectionDatabaseSource_";
  protected final String TEXT_6 = " != null && connectionDatabaseDestine_";
  protected final String TEXT_7 = " != null) {" + NL + "\tString transferSchemaSource = ";
  protected final String TEXT_8 = ";" + NL + "\tString transferSchemaDestine = ";
  protected final String TEXT_9 = ";" + NL + "" + NL + "\tString dbSchemaSource = (String)globalMap.get(\"dbschema_";
  protected final String TEXT_10 = "\");" + NL + "\tString dbSchemaDestine = (String)globalMap.get(\"dbschema_";
  protected final String TEXT_11 = "\");" + NL + "\t" + NL + "\tString schemaSource = (String)globalMap.get(\"schema_";
  protected final String TEXT_12 = "\");" + NL + "\tString schemaDestine = (String)globalMap.get(\"schema_";
  protected final String TEXT_13 = "\");" + NL + "\t" + NL + "\tString tableSchemaSource = (String)globalMap.get(\"tableschema_";
  protected final String TEXT_14 = "\");" + NL + "\tString tableSchemaDestine = (String)globalMap.get(\"tableschema_";
  protected final String TEXT_15 = "\");" + NL + "\t" + NL + "\tif (transferSchemaSource.equals(\"\")) {" + NL + "\t\tif (dbSchemaSource != null) transferSchemaSource = dbSchemaSource;" + NL + "\t\telse if (schemaSource != null) transferSchemaSource = schemaSource;" + NL + "\t\telse if (tableSchemaSource != null) transferSchemaSource = tableSchemaSource;" + NL + "\t}" + NL + "\t" + NL + "\tif (transferSchemaDestine.equals(\"\")) {" + NL + "\t\tif (dbSchemaDestine != null) transferSchemaDestine = dbSchemaDestine;" + NL + "\t\telse if (schemaDestine != null) transferSchemaDestine = schemaDestine;" + NL + "\t\telse if (tableSchemaDestine != null) transferSchemaDestine = tableSchemaDestine;" + NL + "\t}" + NL + "" + NL + "\tcom.devjpcb.transferdatabase.TransferDatabase transferDatabase_";
  protected final String TEXT_16 = " = new com.devjpcb.transferdatabase.TransferDatabase();" + NL + "" + NL + "\ttransferDatabase_";
  protected final String TEXT_17 = ".setConnectionSource(com.devjpcb.transferdatabase.TransferDatabase.createConnection(connectionDatabaseSource_";
  protected final String TEXT_18 = "));" + NL + "    transferDatabase_";
  protected final String TEXT_19 = ".setTableSchemaSource(transferSchemaSource);" + NL + "    " + NL + "    transferDatabase_";
  protected final String TEXT_20 = ".setConnectionDestine(com.devjpcb.transferdatabase.TransferDatabase.createConnection(connectionDatabaseDestine_";
  protected final String TEXT_21 = "));" + NL + "    transferDatabase_";
  protected final String TEXT_22 = ".setTableSchemaDestine(transferSchemaDestine);" + NL + "\t" + NL + "\ttransferDatabase_";
  protected final String TEXT_23 = ".setIdentityOverrideOn(";
  protected final String TEXT_24 = ");" + NL + "\ttransferDatabase_";
  protected final String TEXT_25 = ".setSqlCommentsOn(false);" + NL + "\ttransferDatabase_";
  protected final String TEXT_26 = ".setDelimitedIdentifierModeOn(";
  protected final String TEXT_27 = ");" + NL + "\ttransferDatabase_";
  protected final String TEXT_28 = ".setScriptModeOn(";
  protected final String TEXT_29 = ");" + NL + "\t" + NL + "\ttransferDatabase_";
  protected final String TEXT_30 = ".setDropTablesFirst(";
  protected final String TEXT_31 = ");" + NL + "\ttransferDatabase_";
  protected final String TEXT_32 = ".setContinueOnError(";
  protected final String TEXT_33 = ");" + NL + "\ttransferDatabase_";
  protected final String TEXT_34 = ".setAutoCommit(";
  protected final String TEXT_35 = ");" + NL + "\t" + NL + "\t";
  protected final String TEXT_36 = NL + "\t\t";
  protected final String TEXT_37 = NL + "\t\ttransferDatabase_";
  protected final String TEXT_38 = ".logInfo(\"Copying Schema\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_39 = ".copySchemaToDatabase();" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_40 = ".logInfo(\"Completed Copy Schema\\n\");" + NL + "\t\t";
  protected final String TEXT_41 = NL + "\t\ttransferDatabase_";
  protected final String TEXT_42 = ".logInfo(\"Copying Data\\n\");" + NL + "        transferDatabase_";
  protected final String TEXT_43 = ".copyDataToDatabase();" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_44 = ".logInfo(\"Completed Copy Data\\n\");" + NL + "\t\t";
  protected final String TEXT_45 = NL + "\t\ttransferDatabase_";
  protected final String TEXT_46 = ".logInfo(\"Copying Schema\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_47 = ".copySchemaToDatabase();" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_48 = ".logInfo(\"Completed Copy Schema\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_49 = ".logInfo(\"Copying Data\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_50 = ".copyDataToDatabase();" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_51 = ".logInfo(\"Completed Copy Data\\n\");" + NL + "\t\t";
  protected final String TEXT_52 = NL + "\t";
  protected final String TEXT_53 = NL + "\t\t";
  protected final String TEXT_54 = NL + "\t\ttransferDatabase_";
  protected final String TEXT_55 = ".logInfo(\"Copying Schema\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_56 = ".copySchemaToFile(";
  protected final String TEXT_57 = ", false);" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_58 = ".logInfo(\"Completed Copy Schema\\n\");" + NL + "\t\t";
  protected final String TEXT_59 = NL + "\t\ttransferDatabase_";
  protected final String TEXT_60 = ".logInfo(\"Copying Data\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_61 = ".copyDataToFile(";
  protected final String TEXT_62 = ", false);" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_63 = ".logInfo(\"Completed Copy Data\\n\");" + NL + "\t\t";
  protected final String TEXT_64 = NL + "\t\ttransferDatabase_";
  protected final String TEXT_65 = ".logInfo(\"Copying Schema\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_66 = ".copySchemaToFile(";
  protected final String TEXT_67 = ", false);" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_68 = ".logInfo(\"Completed Copy Schema\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_69 = ".logInfo(\"Copying Data\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_70 = ".copyDataToFile(";
  protected final String TEXT_71 = ", true);" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_72 = ".logInfo(\"Completed Copy Data\\n\");" + NL + "\t\t";
  protected final String TEXT_73 = NL + "\t";
  protected final String TEXT_74 = NL + "\t\ttransferDatabase_";
  protected final String TEXT_75 = ".logInfo(\"Copying Schema\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_76 = ".copySchemaToDDL(";
  protected final String TEXT_77 = ");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_78 = ".logInfo(\"Completed Copy Schema\\n\");" + NL + "\t";
  protected final String TEXT_79 = NL + "\t\ttransferDatabase_";
  protected final String TEXT_80 = ".logInfo(\"Copying Schema\\n\");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_81 = ".copyDDLToDatabase(";
  protected final String TEXT_82 = ");" + NL + "\t\ttransferDatabase_";
  protected final String TEXT_83 = ".logInfo(\"Completed Copy Schema\\n\");" + NL + "\t";
  protected final String TEXT_84 = NL + "\t" + NL + "\t";
  protected final String TEXT_85 = NL + "\tconnectionDatabaseSource_";
  protected final String TEXT_86 = ".close();" + NL + "\tconnectionDatabaseDestine_";
  protected final String TEXT_87 = ".close();" + NL + "\t";
  protected final String TEXT_88 = NL + "} ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument)argument;
    INode node = (INode)codeGenArgument.getArgument();
    String cid = node.getUniqueName();
    
    String transferType = ElementParameterParser.getValue(node,"__TRANSFER_TYPE__");
    String migrationType = ElementParameterParser.getValue(node,"__MIGRATION_TYPE__");
    String connectionDatabaseSource = ElementParameterParser.getValue(node, "__CONNECTION_DATABASE_SOURCE__");
    String schemaDbSource = ElementParameterParser.getValue(node, "__SCHEMA_DB_SOURCE__");
    String connectionDatabaseDestine = ElementParameterParser.getValue(node, "__CONNECTION_DATABASE_DESTINE__");
    String schemaDbDestine = ElementParameterParser.getValue(node, "__SCHEMA_DB_DESTINE__");
    String filename = ElementParameterParser.getValue(node, "__FILENAME__");
    boolean commit = ("true").equals(ElementParameterParser.getValue(node,"__COMMIT__"));
    boolean close = ("true").equals(ElementParameterParser.getValue(node,"__CLOSE__"));
    boolean dropTablesFirst = ("true").equals(ElementParameterParser.getValue(node,"__DROPTABLESFIRST__"));
    boolean continueOnError = ("true").equals(ElementParameterParser.getValue(node,"__CONTINUEONERROR__"));
    boolean identityOverrideOn = ("true").equals(ElementParameterParser.getValue(node,"__IDENTITYOVERRIDEON__"));
    boolean delimitedIdentifierModeOn = ("true").equals(ElementParameterParser.getValue(node,"__DELIMITEDIDENTIFIERMODEON__"));
    
    if (schemaDbSource.equals("")) schemaDbSource = null;
    if (schemaDbDestine.equals("")) schemaDbDestine = null;
    
    boolean scriptModeOn = transferType.equals("DATABASE_TO_FILE");

    stringBuffer.append(TEXT_1);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_2);
    stringBuffer.append( connectionDatabaseSource );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( connectionDatabaseDestine );
    stringBuffer.append(TEXT_5);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_6);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_7);
    stringBuffer.append( schemaDbSource );
    stringBuffer.append(TEXT_8);
    stringBuffer.append( schemaDbDestine );
    stringBuffer.append(TEXT_9);
    stringBuffer.append( connectionDatabaseSource );
    stringBuffer.append(TEXT_10);
    stringBuffer.append( connectionDatabaseDestine );
    stringBuffer.append(TEXT_11);
    stringBuffer.append( connectionDatabaseSource );
    stringBuffer.append(TEXT_12);
    stringBuffer.append( connectionDatabaseDestine );
    stringBuffer.append(TEXT_13);
    stringBuffer.append( connectionDatabaseSource );
    stringBuffer.append(TEXT_14);
    stringBuffer.append( connectionDatabaseDestine );
    stringBuffer.append(TEXT_15);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_16);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_17);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_18);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_19);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_20);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_22);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_23);
    stringBuffer.append( identityOverrideOn );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_25);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_26);
    stringBuffer.append( delimitedIdentifierModeOn );
    stringBuffer.append(TEXT_27);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_28);
    stringBuffer.append( scriptModeOn );
    stringBuffer.append(TEXT_29);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_30);
    stringBuffer.append( dropTablesFirst );
    stringBuffer.append(TEXT_31);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_32);
    stringBuffer.append( continueOnError );
    stringBuffer.append(TEXT_33);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_34);
    stringBuffer.append( commit );
    stringBuffer.append(TEXT_35);
     if (transferType.equals("DATABASE_TO_DATABASE")) { 
    stringBuffer.append(TEXT_36);
     if (migrationType.equals("SCHEMA")) { 
    stringBuffer.append(TEXT_37);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_38);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_39);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_40);
     } else if (migrationType.equals("DATA")) { 
    stringBuffer.append(TEXT_41);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_42);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_43);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_44);
     } else { 
    stringBuffer.append(TEXT_45);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_46);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_47);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_48);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_49);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_50);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_51);
     } 
    stringBuffer.append(TEXT_52);
     } else if (transferType.equals("DATABASE_TO_FILE")) { 
    stringBuffer.append(TEXT_53);
     if (migrationType.equals("SCHEMA")) { 
    stringBuffer.append(TEXT_54);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_55);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_56);
    stringBuffer.append( filename );
    stringBuffer.append(TEXT_57);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_58);
     } else if (migrationType.equals("DATA")) { 
    stringBuffer.append(TEXT_59);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_60);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_61);
    stringBuffer.append( filename );
    stringBuffer.append(TEXT_62);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_63);
     } else { 
    stringBuffer.append(TEXT_64);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_65);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_66);
    stringBuffer.append( filename );
    stringBuffer.append(TEXT_67);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_68);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_69);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_70);
    stringBuffer.append( filename );
    stringBuffer.append(TEXT_71);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_72);
     } 
    stringBuffer.append(TEXT_73);
     } else if (transferType.equals("DATABASE_TO_DDL")) { 
    stringBuffer.append(TEXT_74);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_75);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_76);
    stringBuffer.append( filename );
    stringBuffer.append(TEXT_77);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_78);
     } else if (transferType.equals("DDL_TO_DATABASE")) { 
    stringBuffer.append(TEXT_79);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_80);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_81);
    stringBuffer.append( filename );
    stringBuffer.append(TEXT_82);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_83);
     } 
    stringBuffer.append(TEXT_84);
     if(close){
    stringBuffer.append(TEXT_85);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_86);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_87);
     }
    stringBuffer.append(TEXT_88);
    return stringBuffer.toString();
  }
}
