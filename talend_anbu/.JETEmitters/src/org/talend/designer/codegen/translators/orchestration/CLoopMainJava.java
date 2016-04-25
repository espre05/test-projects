package org.talend.designer.codegen.translators.orchestration;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;

public class CLoopMainJava
{
  protected static String nl;
  public static synchronized CLoopMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CLoopMainJava result = new CLoopMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t.loop(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL + "\t\t\t.loop().header(";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = NL + "\t\t\t\t.loop().";
  protected final String TEXT_6 = "(";
  protected final String TEXT_7 = ", ";
  protected final String TEXT_8 = "NSMap)";
  protected final String TEXT_9 = NL + "\t\t\t\t.loop().";
  protected final String TEXT_10 = "(";
  protected final String TEXT_11 = ")";
  protected final String TEXT_12 = NL + "\t\t\t\t.loop().";
  protected final String TEXT_13 = NL + "\t\t\t.copy()";
  protected final String TEXT_14 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	String type = ElementParameterParser.getValue(node, "__LOOP_TYPE__");
	String value = ElementParameterParser.getValue(node, "__VALUE__");
	String header = ElementParameterParser.getValue(node, "__HEADER__");
	String exp = ElementParameterParser.getValue(node, "__EXPRESSION__");
	String language = ElementParameterParser.getValue(node, "__LANGUAGES__");
	String useNamespaces = ElementParameterParser.getValue(node, "__USE_NAMESPACES__");
	String copy = ElementParameterParser.getValue(node, "__COPY__");
	List< ? extends IConnection> conns = node.getIncomingConnections();
	if(conns.size()>0) {
		if("VALUE_TYPE".equals(type)) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_2);
    
		} else if("HEADER_TYPE".equals(type)) {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(header);
    stringBuffer.append(TEXT_4);
    	
		} else if("EXPRESSION_TYPE".equals(type)) {
			if("xpath".equals(language) && "true".equals(useNamespaces)){

    stringBuffer.append(TEXT_5);
    stringBuffer.append(language);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(exp);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    
			}else if(!("none".equals(language))) {

    stringBuffer.append(TEXT_9);
    stringBuffer.append(language);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(exp);
    stringBuffer.append(TEXT_11);
    
			} else {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(exp);
    
			}
		}
		if ("true".equals(copy)) {

    stringBuffer.append(TEXT_13);
    
		}
	}

    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}
