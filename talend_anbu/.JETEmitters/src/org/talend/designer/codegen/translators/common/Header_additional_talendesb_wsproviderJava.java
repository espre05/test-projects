package org.talend.designer.codegen.translators.common;

import java.util.Vector;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.ElementParameterParser;

public class Header_additional_talendesb_wsproviderJava
{
  protected static String nl;
  public static synchronized Header_additional_talendesb_wsproviderJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Header_additional_talendesb_wsproviderJava result = new Header_additional_talendesb_wsproviderJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tpublic ESBEndpointInfo getEndpoint() {" + NL + "\t\treturn new ESBEndpointInfo() {" + NL + "\t\t\t@SuppressWarnings(\"serial\")" + NL + "\t\t\tprivate java.util.Map<String, Object> props = new java.util.HashMap<String, Object>() {{" + NL + "\t\t\t\t// \"request-response\" or \"one-way\"" + NL + "\t\t\t\tput(\"COMMUNICATION_STYLE\", \"";
  protected final String TEXT_2 = "\");" + NL + "\t\t\t\tput(\"dataFormat\", \"PAYLOAD\");" + NL + "\t\t\t\tput(\"portName\", \"{";
  protected final String TEXT_3 = "}";
  protected final String TEXT_4 = "\");" + NL + "\t\t\t\tput(\"serviceName\", \"{";
  protected final String TEXT_5 = "}";
  protected final String TEXT_6 = "\");" + NL + "\t\t\t\tput(\"defaultOperationName\", \"";
  protected final String TEXT_7 = "\");" + NL + "\t\t\t\tput(\"defaultOperationNameSpace\", \"";
  protected final String TEXT_8 = "\");" + NL + "\t\t\t\tput(\"publishedEndpointUrl\", \"";
  protected final String TEXT_9 = "\");" + NL + "\t\t\t}};" + NL + "" + NL + "\t\t\tpublic String getEndpointKey() {" + NL + "\t\t\t\treturn \"cxf\";" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\tpublic String getEndpointUri() {" + NL + "\t\t\t\t// projectName + \"_\" + processName" + NL + "\t\t\t\treturn \"";
  protected final String TEXT_10 = "_";
  protected final String TEXT_11 = "\";" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\tpublic java.util.Map<String, Object> getEndpointProperties() {" + NL + "\t\t\t\treturn props;" + NL + "\t\t\t}" + NL + "\t\t};" + NL + "\t}" + NL + "" + NL + "" + NL + "" + NL + "" + NL + "/**" + NL + " * queued message exchange" + NL + " */" + NL + "public class QueuedExchangeContextImpl<IN, OUT> {" + NL + "" + NL + "\t/**" + NL + "\t * Exchange timeout in seconds" + NL + "\t */" + NL + "\tprivate static final long EXCHANGE_TIMEOUT = 50;" + NL + "" + NL + "\tprivate final java.util.concurrent.Exchanger<Exception> exceptionExchange =" + NL + "\t\tnew java.util.concurrent.Exchanger<Exception>();" + NL + "\tprivate final java.util.concurrent.CountDownLatch latch =" + NL + "\t\tnew java.util.concurrent.CountDownLatch(1);" + NL + "" + NL + "\tprivate final IN input;" + NL + "" + NL + "\tprivate OUT output = null;" + NL + "\tprivate Throwable fault = null;" + NL + "" + NL + "\tpublic QueuedExchangeContextImpl(IN inMsg) {" + NL + "\t\tthis.input = inMsg;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Don't forget to call this method when you are done" + NL + "\t * with processing of the {@link QueuedExchangeContext}" + NL + "\t */" + NL + "\tpublic void release() throws Exception {" + NL + "\t\tlatch.countDown();" + NL + "\t\tException exception = exceptionExchange.exchange(null, EXCHANGE_TIMEOUT," + NL + "\t\t\t\t\tjava.util.concurrent.TimeUnit.SECONDS);" + NL + "\t\tif (exception != null) {" + NL + "\t\t\tthrow exception;" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This operation have to be called on the Web Service" + NL + "\t * thread to send response if required" + NL + "\t *" + NL + "\t * @throws InterruptedException" + NL + "\t */" + NL + "\tpublic void completeQueuedProcessing() throws InterruptedException {" + NL + "\t\texceptionExchange.exchange(null);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @throws InterruptedException" + NL + "\t */" + NL + "\tvoid waitForRelease(long timeout, java.util.concurrent.TimeUnit unit) throws InterruptedException {" + NL + "\t\tlatch.await(timeout, unit);" + NL + "\t}" + NL + "" + NL + "\tpublic IN getInputMessage() {" + NL + "\t\treturn input;" + NL + "\t}" + NL + "" + NL + "\tpublic void serveOutputMessage(OUT response) {" + NL + "\t\toutput = response;" + NL + "\t}" + NL + "" + NL + "\tpublic void serveFault(Throwable fault) {" + NL + "\t\tthis.fault = fault;" + NL + "\t}" + NL + "" + NL + "\tpublic boolean isFault() {" + NL + "\t\treturn fault != null;" + NL + "\t}" + NL + "" + NL + "\tpublic OUT getResponse() {" + NL + "\t\treturn output;" + NL + "\t}" + NL + "" + NL + "\tpublic Throwable getFault() {" + NL + "\t\treturn fault;" + NL + "\t}" + NL + "}" + NL + "" + NL + "/**" + NL + " * message exchange controller" + NL + " */" + NL + "public class QueuedMessageHandlerImpl<IN, OUT> implements ESBProviderCallback {" + NL + "\tprivate final int MAX_QUEUE_SIZE = 1000;" + NL + "" + NL + "\tprivate final int WAIT_TIMEOUT_SECONDS = 120;" + NL + "" + NL + "\tprivate final java.util.concurrent.BlockingQueue<QueuedExchangeContextImpl<IN, OUT>> queue =" + NL + "\t\tnew java.util.concurrent.LinkedBlockingQueue<QueuedExchangeContextImpl<IN, OUT>>(MAX_QUEUE_SIZE);" + NL + "" + NL + "\t/**" + NL + "\t * This method add a newly created" + NL + "\t * {@link QueuedExchangeContextImpl} into the internal" + NL + "\t * blocking queue where consumer thread is waiting for it." + NL + "\t * Then it waits until the {@link QueuedExchangeContextImpl}" + NL + "\t * will be completed for request-response operations" + NL + "\t */" + NL + "\tpublic QueuedExchangeContextImpl<IN, OUT> invoke(IN request, boolean wait) {" + NL + "\t\tQueuedExchangeContextImpl<IN, OUT> context = new QueuedExchangeContextImpl<IN, OUT>(request);" + NL + "\t\tboolean inserted = queue.offer(context);" + NL + "\t\tif (!inserted) {" + NL + "\t\t\ttry {" + NL + "\t\t\t\tcontext.release();" + NL + "\t\t\t} catch (Exception e) {" + NL + "\t\t\t\te.printStackTrace();" + NL + "\t\t\t}" + NL + "\t\t\t// context.serveFault(\"job pool overflow exceed\", null);" + NL + "\t\t\tthrow new RuntimeException(" + NL + "\t\t\t\t\t\"Can't queue request, queue size of \" + MAX_QUEUE_SIZE + \" is exceeded\");" + NL + "\t\t} else if (wait) {" + NL + "\t\t\ttry {" + NL + "\t\t\t\tcontext.waitForRelease(WAIT_TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS);" + NL + "\t\t\t} catch (InterruptedException ie) {" + NL + "\t\t\t\t// context.serveFault(\"job execution timeout\", ie);" + NL + "\t\t\t\tthrow new RuntimeException(\"job execution timeout: \" + ie.getMessage());" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn context;" + NL + "\t}" + NL + "" + NL + "\tQueuedExchangeContextImpl<IN, OUT> currentExchangeContext;" + NL + "" + NL + "\tpublic IN getRequest() throws ESBJobInterruptedException {" + NL + "\t\tcurrentExchangeContext = null;" + NL + "\t\ttry {" + NL + "\t\t\tcurrentExchangeContext = queue.take();" + NL + "\t\t} catch (InterruptedException e) {" + NL + "\t\t\tthrow new ESBJobInterruptedException(\"job interrupted\", e);" + NL + "\t\t}" + NL + "\t\treturn currentExchangeContext.getInputMessage();" + NL + "\t}" + NL + "" + NL + "\tpublic void sendResponse(Object output) {" + NL + "\t\tif (null == currentExchangeContext) {" + NL + "\t\t\tthrow new RuntimeException(\"sendResponse() invoked before getRequest()\");" + NL + "\t\t}" + NL + "" + NL + "\t\tif (output instanceof Throwable) {" + NL + "\t\t\t// fault" + NL + "\t\t\tcurrentExchangeContext.serveFault((Throwable) output);" + NL + "\t\t} else {" + NL + "\t\t\t// response" + NL + "\t\t\tcurrentExchangeContext.serveOutputMessage((OUT) output);" + NL + "\t\t}" + NL + "" + NL + "\t\ttry {" + NL + "\t\t\tcurrentExchangeContext.release();" + NL + "\t\t} catch (Exception e) {" + NL + "\t\t\t// e.printStackTrace();" + NL + "\t\t\tthrow new RuntimeException(e);" + NL + "\t\t}" + NL + "\t}" + NL + "}" + NL + "" + NL + "/**" + NL + " * web service provider implementation" + NL + " */" + NL + "@javax.xml.ws.ServiceMode(value = javax.xml.ws.Service.Mode.PAYLOAD)" + NL + "@javax.xml.ws.WebServiceProvider()" + NL + "public class ESBProvider_";
  protected final String TEXT_12 = " implements javax.xml.ws.Provider<javax.xml.transform.Source> {" + NL + "" + NL + "\t@javax.annotation.Resource" + NL + "\tprivate javax.xml.ws.WebServiceContext context;" + NL + "" + NL + "\tprivate QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> messageHandler;" + NL + "" + NL + "\tpublic ESBProvider_";
  protected final String TEXT_13 = "(QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> messageHandler) {" + NL + "\t\tthis.messageHandler = messageHandler;" + NL + "\t}" + NL;
  protected final String TEXT_14 = NL + "\t@javax.jws.Oneway()";
  protected final String TEXT_15 = NL + "\tpublic javax.xml.transform.Source invoke(javax.xml.transform.Source request) {" + NL + "" + NL + "\t\ttry {" + NL + "\t\t\tjava.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();" + NL + "\t\t\torg.apache.cxf.staxutils.StaxUtils.copy(request, os);" + NL + "\t\t\torg.dom4j.Document requestDoc = new org.dom4j.io.SAXReader()" + NL + "\t\t\t\t\t.read(new java.io.ByteArrayInputStream(os.toByteArray()));" + NL + "" + NL + "\t\t\tjava.util.Map<String, Object> esbRequest = new java.util.HashMap<String, Object>();" + NL + "\t\t\tesbRequest.put(ESBProviderCallback.HEADERS_SOAP, context.getMessageContext().get(org.apache.cxf.headers.Header.HEADER_LIST));" + NL + "\t\t\tesbRequest.put(ESBProviderCallback.HEADERS_HTTP, context.getMessageContext().get(javax.xml.ws.handler.MessageContext.HTTP_REQUEST_HEADERS));" + NL + "\t\t\tesbRequest.put(ESBProviderCallback.REQUEST, requestDoc);" + NL + "" + NL + "\t\t\tQueuedExchangeContextImpl<java.util.Map, org.dom4j.Document> messageExchange = messageHandler.invoke(esbRequest, ";
  protected final String TEXT_16 = ");" + NL;
  protected final String TEXT_17 = NL + "\t\t\treturn null;";
  protected final String TEXT_18 = NL + "\t\t\ttry {" + NL + "\t\t\t\tif (messageExchange.isFault()) {" + NL + "\t\t\t\t\tthrow messageExchange.getFault();" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\torg.dom4j.Document responseDoc = messageExchange.getResponse();" + NL + "\t\t\t\t\tif (null == responseDoc) {" + NL + "\t\t\t\t\t\tthrow new RuntimeException(\"no response provided by Talend job\");" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\t// System.out.println(\"response: \" + responseDoc.asXML());" + NL + "" + NL + "\t\t\t\t\tjava.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();" + NL + "\t\t\t\t\tnew org.dom4j.io.XMLWriter(baos).write(responseDoc);" + NL + "\t\t\t\t\treturn new javax.xml.transform.stream.StreamSource(" + NL + "\t\t\t\t\t\t\tnew java.io.ByteArrayInputStream(baos.toByteArray()));" + NL + "\t\t\t\t}" + NL + "\t\t\t} finally {" + NL + "\t\t\t\tmessageExchange.completeQueuedProcessing();" + NL + "\t\t\t}";
  protected final String TEXT_19 = NL + "\t\t} catch (RuntimeException ex) {" + NL + "\t\t\tthrow ex;" + NL + "\t\t} catch (Throwable ex) {" + NL + "\t\t\tex.printStackTrace();" + NL + "\t\t\tthrow new RuntimeException(ex);" + NL + "\t\t}" + NL + "\t}" + NL + "}" + NL + "" + NL + "interface ESBProviderCallbackTalendJobInner extends ESBProviderCallback {" + NL + "\tvoid setCustomProperties(java.util.Map<String, String> props);" + NL + "\tvoid sendFault(Throwable e);" + NL + "\tvoid sendFaultByDefault(Throwable e);" + NL + "\tvoid sendBusinessFault(String faultString, org.dom4j.Document faultDetail);" + NL + "}" + NL + "" + NL + "public class ESBProviderCallbackTalendJobWrapper_";
  protected final String TEXT_20 = " implements ESBProviderCallbackTalendJobInner {" + NL + "" + NL + "\tprivate ESBProviderCallback esbProviderCallback;" + NL + "\tprivate java.util.Map<String, String> customProperty;" + NL + "" + NL + "\tpublic ESBProviderCallbackTalendJobWrapper_";
  protected final String TEXT_21 = "(ESBProviderCallback callback) {" + NL + "\t\tesbProviderCallback = callback;" + NL + "\t}" + NL + "" + NL + "\tpublic Object getRequest() throws ESBJobInterruptedException {" + NL + "\t\treturn esbProviderCallback.getRequest();" + NL + "\t}" + NL + "" + NL + "\tpublic void setCustomProperties(java.util.Map<String, String> props) {" + NL + "\t\tcustomProperty = props;" + NL + "\t}" + NL + "" + NL + "\tprivate void doSendResponse(Object obj){" + NL + "\t\tif ((Boolean) globalMap.get(\"wsRequestReceivedAndResponseSentBack\")) {" + NL + "\t\t\tthrow new RuntimeException(\"Send Response failed! Response already sent with current request.\");" + NL + "\t\t} else {" + NL + "\t\t\tglobalMap.put(\"wsRequestReceivedAndResponseSentBack\", true);" + NL + "\t\t\tesbProviderCallback.sendResponse(obj);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic void sendResponse(Object response) {" + NL + "\t\tdoSendResponse(wrapOutput(response));" + NL + "\t}" + NL + "\tpublic void sendFault(Throwable error) {" + NL + "\t\tRuntimeException talendJobError;" + NL + "\t\tif (error instanceof RuntimeException) {" + NL + "\t\t\ttalendJobError = (RuntimeException) error;" + NL + "\t\t} else {" + NL + "\t\t\tString msg = \"\";" + NL + "\t\t\tif (error != null && error.getMessage() != null) {" + NL + "\t\t\t\tmsg = error.getMessage();" + NL + "\t\t\t}" + NL + "\t\t\ttalendJobError = new RuntimeException(\"Talend job execution error: \" + msg, error);" + NL + "\t\t}" + NL + "\t\tdoSendResponse(talendJobError);" + NL + "\t}" + NL + "" + NL + "\tpublic void sendFaultByDefault(Throwable error){" + NL + "\t\tif (!(Boolean) globalMap.get(\"wsRequestReceivedAndResponseSentBack\")) {" + NL + "\t\t\tsendFault(error);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic void sendBusinessFault(String faultString, org.dom4j.Document faultDetail) {" + NL + "\t\ttry {" + NL + "\t\t\torg.apache.cxf.interceptor.Fault soapFault = new org.apache.cxf.interceptor.Fault(faultString, (java.util.ResourceBundle) null);" + NL + "\t\t\tsoapFault.setFaultCode(new javax.xml.namespace.QName(\"";
  protected final String TEXT_22 = "\", \"businessFault\"));" + NL + "" + NL + "\t\t\tif (null != faultDetail) {" + NL + "\t\t\t\t// wrap details" + NL + "\t\t\t\torg.dom4j.Element rootElement = faultDetail.getRootElement();" + NL + "\t\t\t\torg.dom4j.tree.DefaultElement newRootElement = new org.dom4j.tree.DefaultElement(\"details\");" + NL + "\t\t\t\tfaultDetail.setRootElement(newRootElement);" + NL + "\t\t\t\tnewRootElement.add(rootElement);" + NL + "" + NL + "\t\t\t\torg.w3c.dom.Document faultDetailDom = convertDocument(faultDetail);" + NL + "\t\t\t\tsoapFault.setDetail(faultDetailDom.getDocumentElement());" + NL + "\t\t\t}" + NL + "\t\t\tdoSendResponse(wrapOutput(soapFault));" + NL + "\t\t} catch (Exception e) {" + NL + "\t\t\tthis.sendFault(e);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tprivate org.w3c.dom.Document convertDocument(org.dom4j.Document document) throws Exception{" + NL + "\t\t// A special version of DOMWriter that does not write xmlns:foo attributes" + NL + "\t\torg.dom4j.io.DOMWriter writer = new org.dom4j.io.DOMWriter() {" + NL + "\t\t\tprotected void writeNamespace(org.w3c.dom.Element domElement, org.dom4j.Namespace namespace) {" + NL + "\t\t\t\t// Do nothing" + NL + "\t\t\t}" + NL + "\t\t};" + NL + "\t\treturn writer.write(document);" + NL + "\t}" + NL + "" + NL + "\tprivate Object wrapOutput(Object output) {" + NL + "\t\tif (esbProviderCallback instanceof QueuedMessageHandlerImpl) {" + NL + "\t\t\treturn output;" + NL + "\t\t}" + NL + "\t\treturn wrapPayload(output, customProperty);" + NL + "\t}" + NL + "}" + NL + "" + NL + "class HandlerThread_";
  protected final String TEXT_23 = " extends Thread {" + NL + "" + NL + "\tprivate org.apache.cxf.endpoint.Server server;" + NL + "\tQueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> handler;" + NL + "" + NL + "\tString endpointUrl = \"";
  protected final String TEXT_24 = "\";" + NL + "" + NL + "\tpublic HandlerThread_";
  protected final String TEXT_25 = "(QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> handler) {" + NL + "\t\tthis.handler = handler;" + NL + "\t}" + NL + "" + NL + "\tpublic void run() {" + NL + "" + NL + "\t\t// test for busy" + NL + "\t\tjava.net.URI endpointURI = java.net.URI.create(endpointUrl);" + NL + "\t\tString host = endpointURI.getHost();" + NL + "\t\ttry {" + NL + "\t\t\tif (java.net.InetAddress.getByName(host).isLoopbackAddress()) {" + NL + "\t\t\t\tint port = endpointURI.getPort();" + NL + "\t\t\t\tif (-1 == port) {" + NL + "\t\t\t\t\tport = 80;" + NL + "\t\t\t\t}" + NL + "\t\t\t\tjava.net.ServerSocket ss = null;" + NL + "\t\t\t\ttry {" + NL + "\t\t\t\t\tss = new java.net.ServerSocket(port);" + NL + "\t\t\t\t} catch (IOException e) {" + NL + "\t\t\t\t\t// rethrow exception" + NL + "\t\t\t\t\tthrow new IllegalArgumentException(" + NL + "\t\t\t\t\t\t\t\"Cannot start provider with uri: \" + endpointUrl + \". Port \" + port + \" already in use.\");" + NL + "\t\t\t\t} finally {" + NL + "\t\t\t\t\tif (ss != null) {" + NL + "\t\t\t\t\t\ttry {" + NL + "\t\t\t\t\t\t\tss.close();" + NL + "\t\t\t\t\t\t} catch (IOException e) {" + NL + "\t\t\t\t\t\t\t// ignore" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t\ttry {" + NL + "\t\t\t\t\t// ok, let's doublecheck for silent listeners" + NL + "\t\t\t\t\tjava.net.Socket cs = new java.net.Socket(host, port);" + NL + "\t\t\t\t\t// if succeed - somebody silently listening, fail!" + NL + "\t\t\t\t\tcs.close();" + NL + "\t\t\t\t\t// rethrow exception" + NL + "\t\t\t\t\tthrow new IllegalArgumentException(" + NL + "\t\t\t\t\t\t\t\"Cannot start provider with uri: \" + endpointUrl + \". Port \" + port + \" already in use.\");" + NL + "\t\t\t\t} catch (IOException e) {" + NL + "\t\t\t\t\t//ok, nobody listens, proceed" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t} catch (java.net.UnknownHostException e) {" + NL + "\t\t\t// ignore" + NL + "\t\t}" + NL + "" + NL + "\t\torg.apache.cxf.jaxws.JaxWsServerFactoryBean serverFactory = new org.apache.cxf.jaxws.JaxWsServerFactoryBean();";
  protected final String TEXT_26 = NL + "\t\tserverFactory.setWsdlURL(\"";
  protected final String TEXT_27 = "\");";
  protected final String TEXT_28 = NL + "\t\tserverFactory.setServiceName(new javax.xml.namespace.QName(\"";
  protected final String TEXT_29 = "\", \"";
  protected final String TEXT_30 = "\"));" + NL + "\t\tserverFactory.setEndpointName(new javax.xml.namespace.QName(\"";
  protected final String TEXT_31 = "\", \"";
  protected final String TEXT_32 = "\"));" + NL + "\t\t// in case null - WSDL value used" + NL + "\t\tserverFactory.setAddress(endpointUrl);" + NL + "" + NL + "\t\tserverFactory.setServiceBean(new ESBProvider_";
  protected final String TEXT_33 = "(handler));" + NL + "\t\tif (";
  protected final String TEXT_34 = ") {" + NL + "\t\t\tserverFactory.getFeatures().add(new org.apache.cxf.feature.LoggingFeature());" + NL + "\t\t}" + NL + "\t\tserver = serverFactory.create();" + NL + "" + NL + "\t\tSystem.out.println(\"web service [endpoint: \" + endpointUrl + \"] published\");" + NL + "\t}" + NL + "" + NL + "\tpublic void stopEndpoint() {" + NL + "\t\tif (null != server) {" + NL + "\t\t\tserver.stop();" + NL + "\t\t\tserver.destroy();" + NL + "\t\t\tSystem.out.println(\"web service [endpoint: \" + endpointUrl + \"] unpublished\");" + NL + "\t\t}" + NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_35 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
Vector v = (Vector) codeGenArgument.getArgument();
IProcess process = (IProcess) v.get(0);

if (!process.getNodesOfType("tESBProviderRequest").isEmpty()) { // if ESB provider job

	// Web Service Provider
	INode tESBProviderRequestNode = process.getNodesOfType("tESBProviderRequestLoop").get(0);
	String cid = tESBProviderRequestNode.getUniqueName();
	cid = cid.replaceAll("_Loop", "");

	String projectName = codeGenArgument.getCurrentProjectName();
	String processName = process.getName();

	String endpointUrl = ElementParameterParser.getValue(tESBProviderRequestNode, "__ENDPOINT_URI__");

	String wsdlLocation = ElementParameterParser.getValue(tESBProviderRequestNode, "__WSDL_LOCATION__");

	String serviceNS = ElementParameterParser.getValue(tESBProviderRequestNode, "__SERVICE_NS__");
	String serviceName = ElementParameterParser.getValue(tESBProviderRequestNode, "__SERVICE_NAME__");
	if ("--DEFAULT--".equals(serviceName)) {
		serviceName = projectName + "_" + processName;
	}

	String portNS = ElementParameterParser.getValue(tESBProviderRequestNode, "__PORT_NS__");
	String portName = ElementParameterParser.getValue(tESBProviderRequestNode, "__PORT_NAME__");
	if ("--DEFAULT--".equals(portName)) {
		portName = serviceName + "_Port";
	}

	String operationName = ElementParameterParser.getValue(tESBProviderRequestNode, "__OPERATION_NAME__");
	String operationNS = ElementParameterParser.getValue(tESBProviderRequestNode, "__OPERATION_NS__");
	if (null == operationNS || operationNS.isEmpty()) {
		operationNS = serviceNS;
	}

	String communicationStyle = ElementParameterParser.getValue(tESBProviderRequestNode, "__COMMUNICATION_STYLE__");
	boolean isOneWay = false;
	if (null != communicationStyle && !communicationStyle.isEmpty()) {
		isOneWay = "one-way".equals(communicationStyle);
	} else {
		isOneWay = process.getNodesOfType("tESBProviderFault").isEmpty()
			&& process.getNodesOfType("tESBProviderResponse").isEmpty();
	}

	Boolean logMessages = Boolean.valueOf(ElementParameterParser.getValue(tESBProviderRequestNode, "__LOG_MESSAGES__"));

    stringBuffer.append(TEXT_1);
    stringBuffer.append((isOneWay)?"one-way":"request-response");
    stringBuffer.append(TEXT_2);
    stringBuffer.append(portNS);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(portName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(serviceNS);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(serviceName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(operationName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(operationNS);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(endpointUrl);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(processName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
     if (isOneWay) { 
    stringBuffer.append(TEXT_14);
     } 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(!isOneWay);
    stringBuffer.append(TEXT_16);
     if (isOneWay) { 
    stringBuffer.append(TEXT_17);
     } else { 
    stringBuffer.append(TEXT_18);
     } 
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(serviceNS);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(endpointUrl);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
     if (null != wsdlLocation && !wsdlLocation.trim().isEmpty()) { 
    stringBuffer.append(TEXT_26);
    stringBuffer.append(wsdlLocation);
    stringBuffer.append(TEXT_27);
     } 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(serviceNS);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(serviceName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(serviceNS);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(portName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(logMessages);
    stringBuffer.append(TEXT_34);
     } // end if ESB provider job 
    stringBuffer.append(TEXT_35);
    return stringBuffer.toString();
  }
}
