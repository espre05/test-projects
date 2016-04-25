package org.talend.designer.codegen.translators.common;

import java.util.Vector;
import java.util.Map;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IProcess;

public class Header_additional_talendesb_routerjob_datasJava
{
  protected static String nl;
  public static synchronized Header_additional_talendesb_routerjob_datasJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Header_additional_talendesb_routerjob_datasJava result = new Header_additional_talendesb_routerjob_datasJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "private org.apache.camel.Exchange routerExchange = null;" + NL + "" + NL + "public void setExchange(org.apache.camel.Exchange exchange){" + NL + "\tthis.routerExchange = exchange;" + NL + "}" + NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
Vector v = (Vector) codeGenArgument.getArgument();
IProcess process = (IProcess) v.get(0);

boolean jobHasRouteInputComponent = !process.getNodesOfType("tRouteInput").isEmpty();
boolean jobHasRouteOutputComponent = !process.getNodesOfType("tRouteOutput").isEmpty();
boolean jobHasRouteFaultComponent = !process.getNodesOfType("tRouteFault").isEmpty();

if (jobHasRouteInputComponent || jobHasRouteOutputComponent || jobHasRouteFaultComponent) {
    stringBuffer.append(TEXT_1);
    
}

    return stringBuffer.toString();
  }
}
