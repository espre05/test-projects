package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TRESTRequestLoopEndJava
{
  protected static String nl;
  public static synchronized TRESTRequestLoopEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRESTRequestLoopEndJava result = new TRESTRequestLoopEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t} catch (Throwable e_";
  protected final String TEXT_2 = ") {" + NL + "        if (e_";
  protected final String TEXT_3 = " instanceof Exception) {" + NL + "\t\t\tnew TalendException((Exception) e_";
  protected final String TEXT_4 = ", currentComponent, globalMap).printStackTrace();" + NL + "        } else {" + NL + "\t\t\tnew TalendException(new RuntimeException (e_";
  protected final String TEXT_5 = "), currentComponent, globalMap).printStackTrace();" + NL + "       \t}" + NL + "\t\tif(!globalMap.containsKey(\"restResponse\")){" + NL + "\t\t\tjava.util.Map<String, Object> restFault_";
  protected final String TEXT_6 = " = new java.util.HashMap<String, Object>();" + NL + "\t\t\trestFault_";
  protected final String TEXT_7 = ".put(\"STATUS\", 500);" + NL + "\t\t\trestFault_";
  protected final String TEXT_8 = ".put(\"BODY\", e_";
  protected final String TEXT_9 = ".getMessage());" + NL + "\t\t\tglobalMap.put(\"restResponse\", restFault_";
  protected final String TEXT_10 = ");" + NL + "\t\t}" + NL + "\t\treturn;" + NL + "\t}" + NL + "\tnb_line_";
  protected final String TEXT_11 = "++;" + NL + "\tglobalMap.put(\"";
  protected final String TEXT_12 = "_NB_LINE\", nb_line_";
  protected final String TEXT_13 = ");";
  protected final String TEXT_14 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode) codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	cid = cid.replaceAll("_Loop", "");

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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}
