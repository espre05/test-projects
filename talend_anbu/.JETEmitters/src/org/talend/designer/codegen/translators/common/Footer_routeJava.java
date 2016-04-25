package org.talend.designer.codegen.translators.common;

import org.talend.core.model.process.IProcess;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.utils.JavaResourcesHelper;
import java.util.Map;
import org.talend.core.model.process.ElementParameterParser;

public class Footer_routeJava {

  protected static String nl;
  public static synchronized Footer_routeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Footer_routeJava result = new Footer_routeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t}" + NL + "" + NL + "\tprivate org.apache.camel.main.Main main;" + NL + "" + NL + "    private void run() throws java.lang.Exception {" + NL + "        main = new org.apache.camel.main.Main() {" + NL + "" + NL + "            protected CamelContext createContext() {" + NL + "                final org.apache.camel.impl.DefaultCamelContext camelContext =";
  protected final String TEXT_2 = NL + "                    new org.apache.camel.spring.SpringCamelContext(" + NL + "                        new org.springframework.context.support.ClassPathXmlApplicationContext(\"META-INF/spring/";
  protected final String TEXT_3 = ".xml\"));" + NL + "                camelContext.setName(\"";
  protected final String TEXT_4 = "\");" + NL + "                // add notifier" + NL + "                java.util.Collection<org.apache.camel.management.JmxNotificationEventNotifier> jmxEventNotifiers = camelContext" + NL + "                        .getRegistry().findByType(" + NL + "                                org.apache.camel.management.JmxNotificationEventNotifier.class);" + NL + "                if (jmxEventNotifiers != null && !jmxEventNotifiers.isEmpty()) {" + NL + "                    camelContext.getManagementStrategy().addEventNotifier(" + NL + "                            jmxEventNotifiers.iterator().next());" + NL + "                }";
  protected final String TEXT_5 = NL + "                    new org.apache.camel.impl.DefaultCamelContext();";
  protected final String TEXT_6 = NL + "                // add statistics which shows on the connection" + NL + "                final routines.system.RunStat runStat = new routines.system.RunStat();" + NL + "                runStat.openSocket(true);" + NL + "                runStat.setAllPID(pid, pid, pid, \"";
  protected final String TEXT_7 = "\");" + NL + "                try {" + NL + "                    runStat.startThreadStat(clientHost, portStats);" + NL + "                } catch (Exception e) {" + NL + "                    throw new RuntimeException(e);" + NL + "                }" + NL + "                runStat.updateStatOnJob(routines.system.RunStat.JOBSTART, null);" + NL + "" + NL + "                final Map<String, String> targetNodeToConnectionMap = new HashMap<String, String>();";
  protected final String TEXT_8 = NL + "                targetNodeToConnectionMap.put(\"";
  protected final String TEXT_9 = "_";
  protected final String TEXT_10 = "\", \"";
  protected final String TEXT_11 = "\");";
  protected final String TEXT_12 = NL + "                for (String connection : targetNodeToConnectionMap.values()) {" + NL + "                    runStat.updateStatOnConnection(connection, routines.system.RunStat.BEGIN, 0);" + NL + "                }" + NL + "                camelContext.addInterceptStrategy(new org.apache.camel.spi.InterceptStrategy() {" + NL + "                    public org.apache.camel.Processor wrapProcessorInInterceptors(CamelContext context, final org.apache.camel.model.ProcessorDefinition<?> node," + NL + "                        final org.apache.camel.Processor target, org.apache.camel.Processor nextTarget) throws Exception {" + NL + "                        return new org.apache.camel.processor.DelegateAsyncProcessor(target) {" + NL + "                            public boolean process(org.apache.camel.Exchange exchange, org.apache.camel.AsyncCallback callback) {" + NL + "                                final String connection = targetNodeToConnectionMap.get(node.getId());" + NL + "                                if (null != connection) {" + NL + "                                    runStat.updateStatOnConnection(targetNodeToConnectionMap.get(node.getId()), routines.system.RunStat.RUNNING, 1);" + NL + "                                }" + NL + "                                return super.process(exchange, callback);" + NL + "                            }" + NL + "                        };" + NL + "                    }" + NL + "                });";
  protected final String TEXT_13 = NL + "                return camelContext;" + NL + "            }" + NL + "        };" + NL + "" + NL + "        //add route" + NL + "        main.addRouteBuilder(this);";
  protected final String TEXT_14 = NL + "        main.addRouteBuilder(" + NL + "            (org.apache.camel.builder.RouteBuilder) Class.forName(\"";
  protected final String TEXT_15 = "\").newInstance());";
  protected final String TEXT_16 = NL + "        main.run();" + NL + "    }" + NL + "" + NL + "\tpublic void stop() throws java.lang.Exception {" + NL + "\t\tif(main != null) {" + NL + "\t\t\tmain.stop();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic void shutdown() throws java.lang.Exception {" + NL + "\t\tif(main != null) {" + NL + "\t\t\tmain.shutdown();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic static class ContextProperties extends Properties {" + NL + "" + NL + "\t\tpublic void synchronizeContext() {" + NL + "\t\t";
  protected final String TEXT_17 = NL + "\t\t\tif(";
  protected final String TEXT_18 = " != null){" + NL + "\t\t\t";
  protected final String TEXT_19 = NL + "\t\t\t\tString pattern_";
  protected final String TEXT_20 = " = \"yyyy-MM-dd HH:mm:ss\";" + NL + "\t\t\t\tString value_";
  protected final String TEXT_21 = " = \"";
  protected final String TEXT_22 = "\";" + NL + "\t\t\t\tString[] parts_";
  protected final String TEXT_23 = " = value_";
  protected final String TEXT_24 = ".split(\";\");" + NL + "\t\t\t\tif (parts_";
  protected final String TEXT_25 = ".length > 1) {" + NL + "\t\t\t\t\tpattern_";
  protected final String TEXT_26 = " = parts_";
  protected final String TEXT_27 = "[0];" + NL + "\t\t\t\t\tthis.setProperty(\"";
  protected final String TEXT_28 = "\", pattern_";
  protected final String TEXT_29 = " + \";\" + FormatterUtils.format_Date(";
  protected final String TEXT_30 = ", pattern_";
  protected final String TEXT_31 = "));" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\tthis.setProperty(\"";
  protected final String TEXT_32 = "\", FormatterUtils.format_Date(";
  protected final String TEXT_33 = ", pattern_";
  protected final String TEXT_34 = "));" + NL + "\t\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_35 = NL + "\t\t\t\tthis.setProperty(\"";
  protected final String TEXT_36 = "\", ";
  protected final String TEXT_37 = ".toString());" + NL + "\t\t\t";
  protected final String TEXT_38 = NL + "\t\t\t}" + NL + "\t\t";
  protected final String TEXT_39 = NL + "\t\t}" + NL;
  protected final String TEXT_40 = NL + "\t\tpublic String ";
  protected final String TEXT_41 = ";" + NL + "\t\tpublic String get";
  protected final String TEXT_42 = "() {" + NL + "\t\t\treturn this.";
  protected final String TEXT_43 = ";" + NL + "\t\t}";
  protected final String TEXT_44 = NL + "\t\tpublic ";
  protected final String TEXT_45 = " ";
  protected final String TEXT_46 = ";" + NL + "\t\tpublic ";
  protected final String TEXT_47 = " get";
  protected final String TEXT_48 = "() {" + NL + "\t\t\treturn this.";
  protected final String TEXT_49 = ";" + NL + "\t\t}";
  protected final String TEXT_50 = NL + "\t}" + NL + "" + NL + "\tprivate String contextStr = \"";
  protected final String TEXT_51 = "\";" + NL + "\tprivate final ContextProperties context = new ContextProperties();" + NL;
  protected final String TEXT_52 = NL + "\tprivate int portStats = -1;" + NL + "\tprivate String clientHost = \"localhost\";" + NL + "\tprivate String pid;";
  protected final String TEXT_53 = NL + NL + "\tprivate final Properties context_param = new Properties();" + NL + "" + NL + "    public static void main(String[] args){" + NL + "        int exitCode = new ";
  protected final String TEXT_54 = "().runJobInTOS(args);" + NL + "        if(exitCode != 0) {" + NL + "            System.exit(exitCode);" + NL + "        }" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public String[][] runJob(String[] args) {" + NL + "\t\tint exitCode = runJobInTOS(args);" + NL + "\t\treturn new String[][] { { Integer.toString(exitCode) } };" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public int runJobInTOS(String[] args) {" + NL + "\t\tString lastStr = \"\";" + NL + "        for (String arg : args) {" + NL + "        \tif (arg.equalsIgnoreCase(\"--context_param\")) {" + NL + "                lastStr = arg;" + NL + "            } else if (lastStr.equals(\"\")) {" + NL + "                evalParam(arg);" + NL + "            } else {" + NL + "                evalParam(lastStr + \" \" + arg);" + NL + "                lastStr = \"\";" + NL + "            }" + NL + "        }" + NL;
  protected final String TEXT_55 = NL + "    \tif(pid == null) {" + NL + "\t    \tpid = TalendString.getAsciiRandomString(6);" + NL + "\t    }";
  protected final String TEXT_56 = NL + "\t\ttry {" + NL + "\t\t\trun();" + NL + "\t\t} catch (java.lang.Exception e) {" + NL + "\t\t\tSystem.err.println(e.getMessage());" + NL + "\t\t\te.printStackTrace();" + NL + "\t\t\treturn 1;" + NL + "\t\t}" + NL + "\t\treturn 0;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t* read context values from specified context" + NL + "\t* @parameter contextName : the name of context while will be used" + NL + "\t*/" + NL + "\tprivate void readContextValues(String contextName){" + NL + "\t\ttry {" + NL + "\t\t\tjava.io.InputStream inContext = ";
  protected final String TEXT_57 = ".class.getClassLoader().getResourceAsStream(\"";
  protected final String TEXT_58 = "/contexts/\"+contextName+\".properties\");" + NL + "" + NL + "\t\t\tif (inContext!=null) {" + NL + "\t    \t\tcontext.load(inContext);" + NL + "    \t\t\tinContext.close();" + NL + "\t    \t}else{" + NL + "\t    \t\t//print info and job continue to run, for case: context_param is not empty." + NL + "\t    \t\tSystem.err.println(\"Could not find the context \" + contextName);" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\tif(!context_param.isEmpty()) {" + NL + "\t\t\t    context.putAll(context_param);" + NL + "\t\t\t}";
  protected final String TEXT_59 = NL + "            \t\tString pwd_";
  protected final String TEXT_60 = "_value = context.getProperty(\"";
  protected final String TEXT_61 = "\");" + NL + "            \t\tcontext.";
  protected final String TEXT_62 = " = null;" + NL + "            \t\tif(pwd_";
  protected final String TEXT_63 = "_value!=null) {" + NL + "            \t\t\tif(context_param.containsKey(\"";
  protected final String TEXT_64 = "\")) {//no need to decrypt if it come from program argument or parent job runtime" + NL + "            \t\t\t\tcontext.";
  protected final String TEXT_65 = " = pwd_";
  protected final String TEXT_66 = "_value;" + NL + "            \t\t\t} else if (!pwd_";
  protected final String TEXT_67 = "_value.isEmpty()) {" + NL + "            \t\t\t\ttry {" + NL + "            \t\t\t\t\tcontext.";
  protected final String TEXT_68 = " = routines.system.PasswordEncryptUtil.decryptPassword(pwd_";
  protected final String TEXT_69 = "_value);" + NL + "            \t\t\t\t\tcontext.put(\"";
  protected final String TEXT_70 = "\",context.";
  protected final String TEXT_71 = ");" + NL + "            \t\t\t\t} catch (java.lang.RuntimeException e) {" + NL + "            \t\t\t\t\t//do nothing" + NL + "            \t\t\t\t}" + NL + "            \t\t\t}" + NL + "            \t\t}";
  protected final String TEXT_72 = NL + "\t\t\ttry{" + NL + "\t\t\t\tString context_";
  protected final String TEXT_73 = "_value = context.getProperty(\"";
  protected final String TEXT_74 = "\");" + NL + "\t\t\t\tif (context_";
  protected final String TEXT_75 = "_value == null){" + NL + "\t\t\t\t\tcontext_";
  protected final String TEXT_76 = "_value = \"\";" + NL + "\t\t\t\t}" + NL + "\t\t\t\tint context_";
  protected final String TEXT_77 = "_pos = context_";
  protected final String TEXT_78 = "_value.indexOf(\";\");" + NL + "\t\t\t\tString context_";
  protected final String TEXT_79 = "_pattern =  \"yyyy-MM-dd HH:mm:ss\";" + NL + "\t\t\t\tif(context_";
  protected final String TEXT_80 = "_pos > -1){" + NL + "\t\t\t\t\tcontext_";
  protected final String TEXT_81 = "_pattern = context_";
  protected final String TEXT_82 = "_value.substring(0, context_";
  protected final String TEXT_83 = "_pos);" + NL + "\t\t\t\t\tcontext_";
  protected final String TEXT_84 = "_value = context_";
  protected final String TEXT_85 = "_value.substring(context_";
  protected final String TEXT_86 = "_pos + 1);" + NL + "\t\t\t\t}" + NL + "\t\t\t\t" + NL + "\t\t\t    context.";
  protected final String TEXT_87 = "=(java.util.Date)(new java.text.SimpleDateFormat(context_";
  protected final String TEXT_88 = "_pattern).parse(context_";
  protected final String TEXT_89 = "_value));" + NL + "\t\t\t   " + NL + "\t\t\t}catch(java.text.ParseException e)" + NL + "\t\t\t{" + NL + "\t\t\t    context.";
  protected final String TEXT_90 = "=null;" + NL + "\t\t\t}";
  protected final String TEXT_91 = NL + "\t\t\t    \tcontext.";
  protected final String TEXT_92 = "=(";
  protected final String TEXT_93 = ") context.getProperty(\"";
  protected final String TEXT_94 = "\");";
  protected final String TEXT_95 = NL + "\t\t\t \tcontext.";
  protected final String TEXT_96 = "= new java.text.StringCharacterIterator(context.getProperty(\"";
  protected final String TEXT_97 = "\")).first();\t\t\t ";
  protected final String TEXT_98 = " " + NL + "\t\t\t \t\ttry{" + NL + "\t\t\t     \t\tcontext.";
  protected final String TEXT_99 = "=routines.system.ParserUtils.parseTo_";
  protected final String TEXT_100 = " (context.getProperty(\"";
  protected final String TEXT_101 = "\"));" + NL + "\t\t\t \t\t}catch(NumberFormatException e){" + NL + "\t\t\t     \t\tcontext.";
  protected final String TEXT_102 = "=null;" + NL + "\t\t\t \t\t}";
  protected final String TEXT_103 = NL + "    \t} catch (java.io.IOException ie) {" + NL + "    \t\tSystem.err.println(\"Could not load context \"+contextName);" + NL + "    \t\tie.printStackTrace();" + NL + "    \t}" + NL + "\t}" + NL + "" + NL + "\tprivate void evalParam(String arg) {" + NL + "        if (arg.startsWith(\"--context=\")) {" + NL + "            contextStr = arg.substring(10);" + NL + "        }else if (arg.startsWith(\"--context_param\")) {" + NL + "            String keyValue = arg.substring(16);" + NL + "            int index = -1;" + NL + "            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {" + NL + "                context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));" + NL + "            }" + NL + "        }";
  protected final String TEXT_104 = NL + "        else if (arg.startsWith(\"--stat_port=\")) {" + NL + "\t\t\tString portStatsStr = arg.substring(12);" + NL + "\t\t\tif (portStatsStr != null && !portStatsStr.equals(\"null\")) {" + NL + "\t\t\t\tportStats = Integer.parseInt(portStatsStr);" + NL + "\t\t\t}" + NL + "    \t} else if (arg.startsWith(\"--client_host=\")) {" + NL + "    \t\tclientHost = arg.substring(14);" + NL + "    \t} else if (arg.startsWith(\"--pid=\")) {" + NL + "    \t\tpid = arg.substring(6);" + NL + "    \t}";
  protected final String TEXT_105 = NL + "\t}" + NL + "}";
  protected final String TEXT_106 = NL;

    public static java.util.Collection<String> getRoutelets(final org.talend.core.model.process.IProcess process) {
        java.util.Collection<String> routelets = new java.util.HashSet<String>();
        addRoutelets(routelets, process);
        return routelets;
    }

    private static void addRoutelets(final java.util.Collection<String> routelets, final org.talend.core.model.process.IProcess process) {
        for (org.talend.core.model.process.INode node : process.getGeneratingNodes()) {
            if ("Routelets".equals(node.getComponent().getOriginalFamilyName())) {
                org.talend.core.model.process.IProcess2 routelet = (org.talend.core.model.process.IProcess2) node.getComponent().getProcess();
                final String clazz = org.talend.core.model.utils.JavaResourcesHelper.getJobClassName(routelet);
                if (routelets.add(clazz)) {
                    addRoutelets(routelets, routelet);
                }
            }
        }
    }

    public String generate(CodeGeneratorArgument argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    Vector v = (Vector) codeGenArgument.getArgument();
    IProcess process = (IProcess) v.get(0);
    Boolean hasSpring = (Boolean) v.get(1);

	List<? extends INode> processNodes = (List<? extends INode>) process.getGeneratingNodes();
	List<IContextParameter> params = process.getContextManager().getDefaultContext().getContextParameterList();

	final boolean stats = codeGenArgument.isStatistics();

    stringBuffer.append(TEXT_1);
    
                if (hasSpring) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(process.getName().toLowerCase());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_4);
    
                } else { // Routelet

    stringBuffer.append(TEXT_5);
    
                }
    if (stats) {

    stringBuffer.append(TEXT_6);
    stringBuffer.append(codeGenArgument.getJobName() );
    stringBuffer.append(TEXT_7);
    
                for (INode node : processNodes) {
                    if (node.isActivate()) {
                        for (org.talend.core.model.process.IConnection conn : node.getIncomingConnections()) {

    stringBuffer.append(TEXT_8);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(conn.getUniqueName());
    stringBuffer.append(TEXT_11);
    
                        }
                    }
                }

    stringBuffer.append(TEXT_12);
    
} //if stats

    stringBuffer.append(TEXT_13);
    
for (String routeletClass : getRoutelets(process)) {

    stringBuffer.append(TEXT_14);
    stringBuffer.append(routeletClass );
    stringBuffer.append(TEXT_15);
    
}

    stringBuffer.append(TEXT_16);
     for (IContextParameter ctxParam : params){
			String cParaName = ctxParam.getName(); 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_18);
     if(ctxParam.getType().equals("id_Date")){ 
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_21);
    stringBuffer.append(ctxParam.getValue() );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_34);
     } else { 
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_37);
     } 
    stringBuffer.append(TEXT_38);
     } 
    stringBuffer.append(TEXT_39);
    
for (IContextParameter ctxParam : params) {
	if(ctxParam.getType().equals("id_List Of Value") || ctxParam.getType().equals("id_File") || ctxParam.getType().equals("id_Directory")) { 
    stringBuffer.append(TEXT_40);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(Character.toUpperCase(ctxParam.getName().charAt(0)) + ctxParam.getName().substring(1));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_43);
    	} else { 
    stringBuffer.append(TEXT_44);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true));
    stringBuffer.append(TEXT_47);
    stringBuffer.append(Character.toUpperCase(ctxParam.getName().charAt(0)) + ctxParam.getName().substring(1));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_49);
    	}
}

    stringBuffer.append(TEXT_50);
    stringBuffer.append(codeGenArgument.getContextName() );
    stringBuffer.append(TEXT_51);
    
    if(stats) {

    stringBuffer.append(TEXT_52);
    
    }

    stringBuffer.append(TEXT_53);
    stringBuffer.append(process.getName() );
    stringBuffer.append(TEXT_54);
    
    if (stats) {

    stringBuffer.append(TEXT_55);
    
    }

    stringBuffer.append(TEXT_56);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(JavaResourcesHelper.getJobClassPackageFolder(process) );
    stringBuffer.append(TEXT_58);
     
			//for bug TDI-22398
			for (IContextParameter ctxParam :params){ //start for

            	if (ctxParam.getType().equals("id_Password")) {
            
    stringBuffer.append(TEXT_59);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_71);
    
            		continue;
            	}						
			
			    String typeToGenerate ="String";
			    if( !(ctxParam.getType().equals("id_File") || ctxParam.getType().equals("id_Directory") ||ctxParam.getType().equals("id_List Of Value")))
			    {
			       typeToGenerate=JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true);
			    }
			    if(typeToGenerate.equals("java.util.Date")){ // start if
			        

    stringBuffer.append(TEXT_72);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_90);
    
		    	}else if(typeToGenerate.equals("Object")||typeToGenerate.equals("String")||typeToGenerate.equals("java.lang.String") ){

    stringBuffer.append(TEXT_91);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_94);
    
				}else if(typeToGenerate.equals("Character")&&ctxParam.getName()!=null){

    stringBuffer.append(TEXT_95);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_97);
    
            	}else{

    stringBuffer.append(TEXT_98);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_102);
    
			    } //end if
			} //end for

    stringBuffer.append(TEXT_103);
    
    if (stats) {

    stringBuffer.append(TEXT_104);
    
    }

    stringBuffer.append(TEXT_105);
    stringBuffer.append(TEXT_106);
    return stringBuffer.toString();
  }
}