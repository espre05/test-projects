package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class TRESTRequestLoopBeginJava
{
  protected static String nl;
  public static synchronized TRESTRequestLoopBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRESTRequestLoopBeginJava result = new TRESTRequestLoopBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\tif (true) {" + NL + "\t\tthrow new RuntimeException(\"cannot instantiate REST service: job contains more than one tRESTRequest component\");" + NL + "\t}";
  protected final String TEXT_3 = NL + "\tif (true) {" + NL + "\t\tthrow new RuntimeException(\"cannot instantiate service: job contains both tESBProviderRequest and tRESTRequest components\");" + NL + "\t}";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "\t\t\t\t\t";
  protected final String TEXT_6 = " = null;" + NL + "\t\t\t";
  protected final String TEXT_7 = NL + "\tif (true) {" + NL + "\t\tthrow new RuntimeException(\"cannot instantiate REST service: tRESTRequest component has no any output connections\");" + NL + "\t}";
  protected final String TEXT_8 = NL + NL + "\tint nb_line_";
  protected final String TEXT_9 = " = 0;" + NL + "" + NL + "\ttry {" + NL + "" + NL + "\t\tjava.util.Map<String, Object> requestMessage_";
  protected final String TEXT_10 = " =" + NL + "\t\t\t\t(java.util.Map<String, Object>) globalMap.get(\"restRequest\");" + NL + "" + NL + "\t\tif (null == requestMessage_";
  protected final String TEXT_11 = ") {" + NL + "" + NL + "\t\t\tif (restTalendJobAlreadyStarted) {" + NL + "\t\t\t\tthrow new RuntimeException(\"request is not provided\");" + NL + "\t\t\t} else {" + NL + "\t\t\t\tif (!runInTalendEsbRuntimeContainer && null == thread4RestServiceProviderEndpoint) {" + NL + "\t\t\t\t\tString endpointUrl_";
  protected final String TEXT_12 = " = checkEndpointUrl(";
  protected final String TEXT_13 = ");" + NL + "\t\t\t\t\t// *** external thread for endpoint initialization" + NL + "\t\t\t\t\tthread4RestServiceProviderEndpoint = new Thread4RestServiceProviderEndpoint(this, endpointUrl_";
  protected final String TEXT_14 = ");" + NL + "\t\t\t\t\tthread4RestServiceProviderEndpoint.start();" + NL + "\t\t\t\t\t// *** external thread for endpoint initialization" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\trestTalendJobAlreadyStarted = true;" + NL + "" + NL + "\t\t\t\tThread.currentThread();" + NL + "\t\t\t\ttry {" + NL + "\t\t\t\t\twhile(true) {" + NL + "\t\t\t\t\t\tThread.sleep(60000);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t} catch (InterruptedException e_";
  protected final String TEXT_15 = ") {" + NL + "\t\t\t\t\t// e_";
  protected final String TEXT_16 = ".printStackTrace();" + NL + "\t\t\t\t\t// throw new TalendException(e_";
  protected final String TEXT_17 = ", \"wholeJob\", globalMap);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\treturn;" + NL + "\t\t}";
  protected final String TEXT_18 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode) codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	cid = cid.replaceAll("_Loop", "");

    stringBuffer.append(TEXT_1);
     if (node.getProcess().getNodesOfType("tRESTRequestLoop").size() > 1) { 
    stringBuffer.append(TEXT_2);
     } 
     if (node.getProcess().getNodesOfType("tESBProviderRequestLoop").size() > 0) { 
    stringBuffer.append(TEXT_3);
     } 
    stringBuffer.append(TEXT_4);
    
	List<? extends IConnection> conns = node.getProcess().getNodesOfType("tRESTRequestIn").get(0).getOutgoingSortedConnections();
	boolean hasOutputConnection = false;
	if (null != conns && !conns.isEmpty()) {
		for (int i = 0; i < conns.size(); i++) {
			IConnection connTemp = conns.get(i);
			if (connTemp.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
				hasOutputConnection = true; 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(connTemp.getName() );
    stringBuffer.append(TEXT_6);
    
			}
		}
	}

     if (!hasOutputConnection) { 
    stringBuffer.append(TEXT_7);
     } 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(ElementParameterParser.getValue(node, "__REST_ENDPOINT__"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
