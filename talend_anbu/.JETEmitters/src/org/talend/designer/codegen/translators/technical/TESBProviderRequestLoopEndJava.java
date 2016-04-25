package org.talend.designer.codegen.translators.technical;

import java.util.List;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TESBProviderRequestLoopEndJava
{
  protected static String nl;
  public static synchronized TESBProviderRequestLoopEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TESBProviderRequestLoopEndJava result = new TESBProviderRequestLoopEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t} catch (ESBJobInterruptedException e_";
  protected final String TEXT_2 = ") {" + NL + "\t\t\t// job interrupted from outside" + NL + "\t\t\tbreak;" + NL + "\t\t} catch (Throwable e_";
  protected final String TEXT_3 = ") {" + NL + "\t\t\t// handle exception by 'On Component Error' trigger or/and tLogCatcher\t\t" + NL + "\t\t\tif (e_";
  protected final String TEXT_4 = " instanceof Exception) {" + NL + "\t\t\t\tnew TalendException((Exception) e_";
  protected final String TEXT_5 = ", currentComponent, globalMap).printStackTrace();" + NL + "\t\t\t} else {" + NL + "\t\t\t\tnew TalendException(new RuntimeException (e_";
  protected final String TEXT_6 = "), currentComponent, globalMap).printStackTrace();" + NL + "\t\t\t}" + NL + "\t\t\t((ESBProviderCallbackTalendJobInner) globalMap.get(\"esbHandler\")).sendFaultByDefault(e_";
  protected final String TEXT_7 = ");" + NL + "\t\t} finally {" + NL + "\t\t\t// close DB connections" + NL + "\t\t\ttry {" + NL + "\t\t\t\tjava.util.Map<String, routines.system.TalendDataSource> talendDataSources = " + NL + "\t\t\t\t\t(java.util.Map<String, routines.system.TalendDataSource>) globalMap.get(KEY_DB_DATASOURCES);" + NL + "\t\t\t\tif (null != talendDataSources) {" + NL + "\t\t\t\t\tfor (routines.system.TalendDataSource talendDataSource : talendDataSources.values()) {" + NL + "\t\t\t\t\t\ttalendDataSource.close();" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t} catch (Throwable e_";
  protected final String TEXT_8 = ") {" + NL + "\t\t\t    e_";
  protected final String TEXT_9 = ".printStackTrace(System.err);" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\t// Exit from this loop is made by the configuring \"Keep listening\"" + NL + "\t\t\t// parameter to false. Then we will have a break before." + NL + "\t\t\tif (\"false\".equals(\"";
  protected final String TEXT_10 = "\")) {" + NL + "\t\t\t\tbreak;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tnb_line_";
  protected final String TEXT_11 = "++;" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_12 = "_NB_LINE\", nb_line_";
  protected final String TEXT_13 = ");" + NL + "\t} // This is the end of the ESB Service Provider loop" + NL + "} finally {";
  protected final String TEXT_14 = NL + "\t// for \"keep listening\" == false web service need a time to serve response" + NL + "\tif (null == this.callback) {" + NL + "\t\t// only for execution in Studio" + NL + "\t\tThread.currentThread();" + NL + "\t\tThread.sleep(500);" + NL + "\t}";
  protected final String TEXT_15 = NL + "\t// unsubscribe" + NL + "\tif (null != handlerThread_";
  protected final String TEXT_16 = ") {" + NL + "\t\t// stop endpoint in case it was opened by job" + NL + "\t\thandlerThread_";
  protected final String TEXT_17 = ".stopEndpoint();" + NL + "\t}" + NL + "}";
  protected final String TEXT_18 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode) codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	cid = cid.replaceAll("_Loop", "");

	Boolean keepListening = Boolean.valueOf(ElementParameterParser.getValue(node, "__KEEPLISTENING__"));

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
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
    stringBuffer.append(keepListening);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
     if (!keepListening) { 
    stringBuffer.append(TEXT_14);
     } 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
