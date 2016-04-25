package org.talend.designer.codegen.translators.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Header_additional_talendesb_restJava
{
  protected static String nl;
  public static synchronized Header_additional_talendesb_restJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Header_additional_talendesb_restJava result = new Header_additional_talendesb_restJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public void setBus( org.apache.cxf.Bus bus) {" + NL + "\torg.apache.cxf.resource.ResourceManager resourceManager =" + NL + "\t\t\tbus.getExtension(org.apache.cxf.resource.ResourceManager.class);" + NL + "\tif (resourceManager != null) {" + NL + "\t\tresourceManager.addResourceResolver(new org.apache.cxf.resource.ClasspathResolver() {" + NL + "\t\t\t\tpublic java.io.InputStream getAsStream(String resourceName) {" + NL + "\t\t\t\t\tjava.io.InputStream is = super.getAsStream(resourceName);" + NL + "\t\t\t\t\tif(is == null){" + NL + "\t\t\t\t\t\ttry {" + NL + "\t\t\t\t\t\t\tjava.net.URL url = new java.net.URL(resourceName);" + NL + "\t\t\t\t\t\t\tis = url.openStream();" + NL + "\t\t\t\t\t\t} catch (java.lang.Exception e) {" + NL + "\t\t\t\t\t\t\t//e.printStackTrace();" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\treturn is;" + NL + "\t\t\t\t}" + NL + "\t\t\t\tpublic <T> T resolve(String resourceName, Class<T> resourceType) {" + NL + "\t\t\t\t\tT resolve = super.resolve(resourceName, resourceType);" + NL + "\t\t\t\t\tif (resolve == null) {" + NL + "\t\t\t\t\t\ttry {" + NL + "\t\t\t\t\t\t\tjava.net.URL url = new java.net.URL(resourceName);" + NL + "\t\t\t\t\t\t\tif (resourceType.isInstance(url)) {" + NL + "\t\t\t\t\t\t\t\treturn resourceType.cast(url);" + NL + "\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t} catch (java.net.MalformedURLException e) {" + NL + "\t\t\t\t\t\t\t// ignore" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\treturn null;" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t}" + NL + "}";
  protected final String TEXT_2 = NL + NL + "private boolean runInTalendEsbRuntimeContainer = false;" + NL + "" + NL + "public void setRunInTalendEsbRuntimeContainer(boolean flag) {" + NL + "\trunInTalendEsbRuntimeContainer = flag;" + NL + "}" + NL + "" + NL + "private boolean restTalendJobAlreadyStarted = false;" + NL + "" + NL + "/**" + NL + " * REST provider implementation" + NL + " */" + NL + "@javax.ws.rs.Path(\"/\")" + NL + "public static class RestServiceProviderImpl4TalendJob {" + NL + "" + NL + "\t@javax.ws.rs.core.Context" + NL + "\tprivate org.apache.cxf.jaxrs.ext.MessageContext messageContext;" + NL + "" + NL + "\tprivate final ";
  protected final String TEXT_3 = " job;" + NL + "" + NL + "\tpublic RestServiceProviderImpl4TalendJob(";
  protected final String TEXT_4 = " job) {" + NL + "\t\tthis.job = job;" + NL + "\t}" + NL + "" + NL + "\tprivate void populateRequestWithJobContext(java.util.Map<String, Object> requestGlobalMap, ";
  protected final String TEXT_5 = " job) {" + NL + "\t\t// pass job DataSources" + NL + "\t\tjava.util.Map<String, routines.system.TalendDataSource> talendDataSources =" + NL + "\t\t\t(java.util.Map<String, routines.system.TalendDataSource>) job.globalMap.get(KEY_DB_DATASOURCES);" + NL + "\t\tif (null != talendDataSources) {" + NL + "\t\t\tjava.util.Map<String, routines.system.TalendDataSource> restDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();" + NL + "\t\t\tfor (java.util.Map.Entry<String, routines.system.TalendDataSource> talendDataSourceEntry : talendDataSources.entrySet()) {" + NL + "\t\t\t\trestDataSources.put(talendDataSourceEntry.getKey(), new routines.system.TalendDataSource(talendDataSourceEntry.getValue().getRawDataSource()));" + NL + "\t\t\t}" + NL + "\t\t\trequestGlobalMap.put(KEY_DB_DATASOURCES, restDataSources);" + NL + "\t\t}" + NL + "" + NL + "\t\t// pass job shared connections" + NL + "\t\trequestGlobalMap.putAll(job.getSharedConnections4REST());" + NL + "" + NL + "\t\t// pass job concurrent map" + NL + "\t\trequestGlobalMap.put(\"concurrentHashMap\", job.globalMap.get(\"concurrentHashMap\"));" + NL + "\t}" + NL + "" + NL + "\tprivate void closePassedDataSourceConnections(java.util.Map<String, Object> requestGlobalMap) {" + NL + "\t\t// close connections in passed job DataSources" + NL + "\t\ttry {" + NL + "\t\t\tjava.util.Map<String, routines.system.TalendDataSource> restDataSources =" + NL + "\t\t\t\t(java.util.Map<String, routines.system.TalendDataSource>) requestGlobalMap.get(KEY_DB_DATASOURCES);" + NL + "\t\t\tif (null != restDataSources) {" + NL + "\t\t\t\tfor (routines.system.TalendDataSource restDataSource : restDataSources.values()) {" + NL + "\t\t\t\t\trestDataSource.close();" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t} catch (Throwable e) {" + NL + "\t\t\te.printStackTrace(System.err);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tprivate javax.ws.rs.core.Response processRequest(java.util.Map<String, Object> request) {" + NL + "\t\tfinal java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();" + NL + "\t\ttry {" + NL + "\t\t\tglobalMap.put(\"restRequest\", request);" + NL + "" + NL + "\t\t\tpopulateRequestWithJobContext(globalMap, job);" + NL + "" + NL + "\t\t\tjob.";
  protected final String TEXT_6 = "_LoopProcess(globalMap);" + NL + "" + NL + "\t\t\tjava.util.Map<String, Object> response = (java.util.Map<String, Object>) globalMap.get(\"restResponse\");" + NL + "" + NL + "\t\t\tObject responseBody = null;" + NL + "\t\t\tInteger status = null;" + NL + "\t\t\tjava.util.Map<String, String> headers = null;" + NL + "\t\t\tif (null != response) {" + NL + "\t\t\t\tObject dropJsonRootProp = response.get(\"drop.json.root.element\");" + NL + "\t\t\t\tBoolean dropJsonRoot = (null == dropJsonRootProp) ? false : (Boolean) dropJsonRootProp;" + NL + "\t\t\t\tmessageContext.put(\"drop.json.root.element\", dropJsonRoot.toString());" + NL + "" + NL + "\t\t\t\tresponseBody = response.get(\"BODY\");" + NL + "\t\t\t\tstatus = (Integer) response.get(\"STATUS\");" + NL + "\t\t\t\theaders = (java.util.Map<String, String>) response.get(\"HEADERS\");" + NL + "\t\t\t}" + NL + "\t\t\tif (null == status) {" + NL + "\t\t\t\tstatus = (request.containsKey(\"STATUS\")) ? (Integer) request.get(\"STATUS\") : 404;" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\tjavax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.status(status).entity(responseBody);" + NL + "\t\t\tif (headers != null) {" + NL + "\t\t\t\tfor (java.util.Map.Entry<String, String> header : headers.entrySet()) {" + NL + "\t\t\t\t\tresponseBuilder.header(header.getKey(), header.getValue());" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\treturn responseBuilder.build();" + NL + "" + NL + "\t\t} catch (Throwable ex) {" + NL + "\t\t\tex.printStackTrace();" + NL + "\t\t\tthrow new javax.ws.rs.WebApplicationException(ex, 500);" + NL + "\t\t} finally {" + NL + "\t\t\t// close DB connections" + NL + "\t\t\tclosePassedDataSourceConnections(globalMap);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tprivate javax.ws.rs.core.Response processStreamingResponseRequest(final java.util.Map<String, Object> request) {" + NL + "" + NL + "\t\tjavax.ws.rs.core.StreamingOutput streamingOutput = new javax.ws.rs.core.StreamingOutput() {" + NL + "\t\t\tpublic void write(java.io.OutputStream output) {" + NL + "\t\t\t\tfinal java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();" + NL + "\t\t\t\ttry {" + NL + "\t\t\t\t\tglobalMap.put(\"restResponseStream\", output);" + NL + "" + NL + "\t\t\t\t\tglobalMap.put(\"restRequest\", request);" + NL + "" + NL + "\t\t\t\t\tpopulateRequestWithJobContext(globalMap, job);" + NL + "" + NL + "\t\t\t\t\tjob.";
  protected final String TEXT_7 = "_LoopProcess(globalMap);" + NL + "" + NL + "\t\t\t\t\tif (globalMap.containsKey(\"restResponseWrappingClosure\")) {" + NL + "\t\t\t\t\t\toutput.write(((String) globalMap.get(\"restResponseWrappingClosure\")).getBytes());" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t} catch (Throwable ex) {" + NL + "\t\t\t\t\tex.printStackTrace();" + NL + "\t\t\t\t\tthrow new javax.ws.rs.WebApplicationException(ex, 500);" + NL + "\t\t\t\t} finally {" + NL + "\t\t\t\t\t// close DB connections" + NL + "\t\t\t\t\tclosePassedDataSourceConnections(globalMap);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t};" + NL + "" + NL + "\t\treturn javax.ws.rs.core.Response.ok().entity(streamingOutput).build();" + NL + "\t}" + NL;
  protected final String TEXT_8 = NL + "    @org.apache.cxf.jaxrs.ext.";
  protected final String TEXT_9 = "()";
  protected final String TEXT_10 = NL + "    @javax.ws.rs.";
  protected final String TEXT_11 = "()";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "\t@javax.ws.rs.Path(";
  protected final String TEXT_14 = ")";
  protected final String TEXT_15 = "\t@javax.ws.rs.Consumes(";
  protected final String TEXT_16 = ")";
  protected final String TEXT_17 = "\t@javax.ws.rs.Produces(";
  protected final String TEXT_18 = ")";
  protected final String TEXT_19 = "\t@org.apache.cxf.jaxrs.ext.Oneway()";
  protected final String TEXT_20 = NL + "\tpublic javax.ws.rs.core.Response ";
  protected final String TEXT_21 = "(" + NL + "\t\t";
  protected final String TEXT_22 = ", ";
  protected final String TEXT_23 = NL + "\t\t\t\t\t\t@javax.ws.rs.DefaultValue(";
  protected final String TEXT_24 = ") ";
  protected final String TEXT_25 = NL + "\t\t\t\t\t\t@javax.ws.rs.DefaultValue(\"";
  protected final String TEXT_26 = "\") ";
  protected final String TEXT_27 = NL + "\t\t\t\t@";
  protected final String TEXT_28 = "(\"";
  protected final String TEXT_29 = "\") ";
  protected final String TEXT_30 = " ";
  protected final String TEXT_31 = NL + "\t\t";
  protected final String TEXT_32 = NL + "\t\t";
  protected final String TEXT_33 = ", ";
  protected final String TEXT_34 = " body" + NL + "\t\t";
  protected final String TEXT_35 = NL + "\t\t\t) {";
  protected final String TEXT_36 = NL + "\t\t\t\tjava.util.Date ";
  protected final String TEXT_37 = " = null;" + NL + "\t\t\t\t";
  protected final String TEXT_38 = NL + "\t\t\t\t\tif (null != ";
  protected final String TEXT_39 = "_";
  protected final String TEXT_40 = " && 0 != ";
  protected final String TEXT_41 = "_";
  protected final String TEXT_42 = ".trim().length()) {" + NL + "\t\t\t\t\t\ttry {" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_43 = " = new java.text.SimpleDateFormat(";
  protected final String TEXT_44 = ").parse(";
  protected final String TEXT_45 = "_";
  protected final String TEXT_46 = ");" + NL + "\t\t\t\t\t\t} catch (Exception e) {" + NL + "\t\t\t\t\t\t\t// try to parse date by usual way later" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t";
  protected final String TEXT_47 = NL + "\t\t\t\tif (null == ";
  protected final String TEXT_48 = " && null != ";
  protected final String TEXT_49 = "_";
  protected final String TEXT_50 = " && 0 != ";
  protected final String TEXT_51 = "_";
  protected final String TEXT_52 = ".trim().length()) {" + NL + "\t\t\t\t\ttry {" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_53 = " = new java.util.Date(";
  protected final String TEXT_54 = "_";
  protected final String TEXT_55 = ");" + NL + "\t\t\t\t\t} catch (Exception e) {" + NL + "\t\t\t\t\t\t// wrong date parameter passed" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t\t";
  protected final String TEXT_56 = NL + "\t\t\t\t\tif (null == ";
  protected final String TEXT_57 = ") {" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_58 = " = new java.util.Date(); // dummy fake" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t";
  protected final String TEXT_59 = NL + "\t\tjava.util.Map<String, Object> request_";
  protected final String TEXT_60 = " = new java.util.HashMap<String, Object>();" + NL + "\t\trequest_";
  protected final String TEXT_61 = ".put(\"VERB\", \"";
  protected final String TEXT_62 = "\");" + NL + "\t\trequest_";
  protected final String TEXT_63 = ".put(\"OPERATION\", \"";
  protected final String TEXT_64 = "\");" + NL + "\t\trequest_";
  protected final String TEXT_65 = ".put(\"PATTERN\", ";
  protected final String TEXT_66 = ");" + NL + "\t";
  protected final String TEXT_67 = NL + "\t\trequest_";
  protected final String TEXT_68 = ".put(\"BODY\", body);" + NL + "\t";
  protected final String TEXT_69 = NL + NL + "\t\tpopulateRequestInfo(request_";
  protected final String TEXT_70 = ", messageContext);" + NL + "" + NL + "\t\tjava.util.Map<String, Object> parameters_";
  protected final String TEXT_71 = " = new java.util.HashMap<String, Object>();" + NL + "\t\t";
  protected final String TEXT_72 = NL + "\t\t\tparameters_";
  protected final String TEXT_73 = ".put(\"";
  protected final String TEXT_74 = "\", ";
  protected final String TEXT_75 = ");" + NL + "\t\t";
  protected final String TEXT_76 = NL + "\t\trequest_";
  protected final String TEXT_77 = ".put(\"PARAMS\", parameters_";
  protected final String TEXT_78 = ");" + NL;
  protected final String TEXT_79 = NL + "\t\tjava.util.List<String> partNames_";
  protected final String TEXT_80 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_81 = NL + "\t\t\t\tpartNames_";
  protected final String TEXT_82 = ".add(\"";
  protected final String TEXT_83 = "\");";
  protected final String TEXT_84 = NL + "\t\tpopulateMultipartRequestInfo(request_";
  protected final String TEXT_85 = ", messageContext, partNames_";
  protected final String TEXT_86 = ");";
  protected final String TEXT_87 = NL + NL + "\t\treturn process";
  protected final String TEXT_88 = "Request(request_";
  protected final String TEXT_89 = ");" + NL + "\t}";
  protected final String TEXT_90 = NL + "\tpublic javax.ws.rs.core.Response handleWrongRequest(" + NL + "\t\t\torg.apache.cxf.jaxrs.ext.MessageContext context, int status, String error) {" + NL + "" + NL + "\t\t// System.out.println(\"wrong call [uri: \" + context.getUriInfo().getPath() + \" ; method: \" + context.getRequest().getMethod() + \" ; status: \" + status + \" ; error: \" + error + \"]\");" + NL + "" + NL + "\t\tjava.util.Map<String, Object> wrongRequest = new java.util.HashMap<String, Object>();" + NL + "\t\twrongRequest.put(\"ERROR\", error);" + NL + "\t\twrongRequest.put(\"STATUS\", status);" + NL + "\t\twrongRequest.put(\"VERB\", context.getRequest().getMethod());" + NL + "\t\twrongRequest.put(\"URI\", context.getUriInfo().getPath());" + NL + "\t\twrongRequest.put(\"URI_BASE\", context.getUriInfo().getBaseUri().toString());" + NL + "\t\twrongRequest.put(\"URI_ABSOLUTE\", context.getUriInfo().getAbsolutePath().toString());" + NL + "\t\twrongRequest.put(\"URI_REQUEST\", context.getUriInfo().getRequestUri().toString());" + NL + "" + NL + "\t\treturn processRequest(wrongRequest);" + NL + "\t}" + NL + "" + NL + "\tprivate void populateRequestInfo(java.util.Map<String, Object> request, org.apache.cxf.jaxrs.ext.MessageContext context) {" + NL + "\t\tfinal javax.ws.rs.core.UriInfo ui = context.getUriInfo();" + NL + "" + NL + "\t\trequest.put(\"URI\", ui.getPath());" + NL + "\t\trequest.put(\"URI_BASE\", ui.getBaseUri().toString());" + NL + "\t\trequest.put(\"URI_ABSOLUTE\", ui.getAbsolutePath().toString());" + NL + "\t\trequest.put(\"URI_REQUEST\", ui.getRequestUri().toString());" + NL + "" + NL + "\t\trequest.put(\"ALL_HEADER_PARAMS\", context.getHttpHeaders().getRequestHeaders());" + NL + "\t\trequest.put(\"ALL_QUERY_PARAMS\", ui.getQueryParameters());" + NL + "" + NL + "\t\tjavax.ws.rs.core.SecurityContext securityContext = context.getSecurityContext();" + NL + "\t\tif (null != securityContext && null != securityContext.getUserPrincipal()) {" + NL + "\t\t\trequest.put(\"PRINCIPAL_NAME\", securityContext.getUserPrincipal().getName());" + NL + "\t\t}" + NL + "" + NL + "\t\trequest.put(\"CorrelationID\", context.get(\"CorrelationID\"));" + NL + "" + NL + "\t\trequest.put(\"MESSAGE_CONTEXT\", context);" + NL + "\t}" + NL + "" + NL + "\tprivate void populateMultipartRequestInfo(java.util.Map<String, Object> request, org.apache.cxf.jaxrs.ext.MessageContext context," + NL + "\t\t\tjava.util.List<String> partNames) {" + NL + "\t\tjava.util.Map<String, String> attachmentFilenames = new java.util.HashMap<String, String>();" + NL + "" + NL + "\t\tjava.util.Map<String, java.util.Map<String, java.util.List<String>>> attachmentHeaders =" + NL + "\t\t\tnew java.util.HashMap<String, java.util.Map<String, java.util.List<String>>>();" + NL + "" + NL + "\t\tfor (String partName : partNames) {" + NL + "\t\t\torg.apache.cxf.jaxrs.ext.multipart.Attachment attachment = getFirstMatchingPart(context, partName);" + NL + "\t\t\tif (null != attachment) {" + NL + "\t\t\t\tattachmentHeaders.put(partName, attachment.getHeaders());" + NL + "\t\t\t\tif (null != attachment.getContentDisposition()) {" + NL + "\t\t\t\t\tString filename = attachment.getContentDisposition().getParameter(\"filename\");" + NL + "\t\t\t\t\tif (null != filename) {" + NL + "\t\t\t\t\t\tattachmentFilenames.put(partName, filename);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\trequest.put(\"ATTACHMENT_HEADERS\", attachmentHeaders);" + NL + "\t\trequest.put(\"ATTACHMENT_FILENAMES\", attachmentFilenames);" + NL + "\t}" + NL + "" + NL + "\tprivate static org.apache.cxf.jaxrs.ext.multipart.Attachment getFirstMatchingPart(" + NL + "\t\t\torg.apache.cxf.jaxrs.ext.MessageContext context, String partName) {" + NL + "\t\tList<org.apache.cxf.jaxrs.ext.multipart.Attachment> attachments =" + NL + "\t\t\torg.apache.cxf.jaxrs.utils.multipart.AttachmentUtils.getAttachments(context);" + NL + "\t\tfor (org.apache.cxf.jaxrs.ext.multipart.Attachment attachment : attachments) {" + NL + "\t\t\tif (partName.equals(attachment.getContentId())) {" + NL + "\t\t\t\treturn attachment;" + NL + "\t\t\t}" + NL + "\t\t\torg.apache.cxf.jaxrs.ext.multipart.ContentDisposition cd = attachment.getContentDisposition();" + NL + "\t\t\tif (null != cd && partName.equals(cd.getParameter(\"name\"))) {" + NL + "\t\t\t\treturn attachment;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\t// unexpected" + NL + "\t\tthrow new javax.ws.rs.InternalServerErrorException();" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ExceptionMapper4TalendJobRestService extends org.apache.cxf.jaxrs.impl.WebApplicationExceptionMapper {" + NL + "" + NL + "\t@javax.ws.rs.core.Context private org.apache.cxf.jaxrs.ext.MessageContext messageContext;" + NL + "" + NL + "\tprivate RestServiceProviderImpl4TalendJob provider;" + NL + "" + NL + "\tpublic ExceptionMapper4TalendJobRestService(RestServiceProviderImpl4TalendJob provider) {" + NL + "\t\tthis.provider = provider;" + NL + "\t}" + NL + "" + NL + "\tpublic javax.ws.rs.core.Response toResponse(javax.ws.rs.WebApplicationException ex) {" + NL + "\t\tString error = null;" + NL + "\t\tjavax.ws.rs.core.Response response = ex.getResponse();" + NL + "\t\tif (null != response && null != response.getEntity()) {" + NL + "\t\t\terror = response.getEntity().toString();" + NL + "\t\t}" + NL + "\t\tresponse = super.toResponse(ex);" + NL + "\t\tif (null == error) {" + NL + "\t\t\tif (null != response && null != response.getEntity()) {" + NL + "\t\t\t\terror = response.getEntity().toString();" + NL + "\t\t\t} else {" + NL + "\t\t\t\terror = null == ex.getCause() ? ex.getMessage() : ex.getCause().getMessage();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tresponse = provider.handleWrongRequest(messageContext, response.getStatus(), error);" + NL + "" + NL + "\t\tjava.util.List<javax.ws.rs.core.MediaType> accepts = messageContext.getHttpHeaders().getAcceptableMediaTypes();" + NL + "\t\tjavax.ws.rs.core.MediaType responseType = accepts.isEmpty() ? null : accepts.get(0);" + NL + "" + NL + "\t\tif (responseType == null || !responseType.getSubtype().equals(\"xml\") && !responseType.getSubtype().equals(\"json\")) {" + NL + "\t\t\tresponseType = javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;" + NL + "\t\t}" + NL + "" + NL + "\t\treturn javax.ws.rs.core.Response.status(response.getStatus()).entity(response.getEntity()).type(responseType).build();" + NL + "\t}" + NL + "}" + NL + "" + NL + "private String checkEndpointUrl(String url) {";
  protected final String TEXT_91 = NL + "\tfinal String defaultEndpointUrl = \"";
  protected final String TEXT_92 = "\";" + NL + "" + NL + "\tString endpointUrl = url;" + NL + "\tif (null == endpointUrl || endpointUrl.trim().isEmpty()) {" + NL + "\t\tendpointUrl = defaultEndpointUrl;" + NL + "\t} else if (!endpointUrl.contains(\"://\")) { // relative" + NL + "\t\tif (endpointUrl.startsWith(\"/\")) {" + NL + "\t\t\tendpointUrl = endpointUrl.substring(1);" + NL + "\t\t}" + NL + "\t\tendpointUrl = defaultEndpointUrl + endpointUrl;" + NL + "\t}" + NL + "" + NL + "\t// test for busy" + NL + "\tjava.net.URI endpointURI = java.net.URI.create(endpointUrl);" + NL + "\tString host = endpointURI.getHost();" + NL + "\ttry {" + NL + "\t\tif (java.net.InetAddress.getByName(host).isLoopbackAddress()) {" + NL + "\t\t\tint port = endpointURI.getPort();" + NL + "\t\t\tjava.net.ServerSocket ss = null;" + NL + "\t\t\ttry {" + NL + "\t\t\t\tss = new java.net.ServerSocket(port);" + NL + "\t\t\t} catch (IOException e) {" + NL + "\t\t\t\t// rethrow exception" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"Cannot start provider with uri: \" + endpointUrl + \". Port \" + port + \" already in use.\");" + NL + "\t\t\t} finally {" + NL + "\t\t\t\tif (ss != null) {" + NL + "\t\t\t\t\ttry {" + NL + "\t\t\t\t\t\tss.close();" + NL + "\t\t\t\t\t} catch (IOException e) {" + NL + "\t\t\t\t\t\t// ignore" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\ttry {" + NL + "\t\t\t\t// ok, let's doublecheck for silent listeners" + NL + "\t\t\t\tjava.net.Socket cs = new java.net.Socket(host, port);" + NL + "\t\t\t\t// if succeed - somebody silently listening, fail!" + NL + "\t\t\t\tcs.close();" + NL + "\t\t\t\t// rethrow exception" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"Cannot start provider with uri: \" + endpointUrl + \". Port \" + port + \" already in use.\");" + NL + "\t\t\t} catch (IOException e) {" + NL + "\t\t\t\t// ok, nobody listens, proceed" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t} catch (java.net.UnknownHostException e) {" + NL + "\t\t// ignore" + NL + "\t}" + NL + "" + NL + "\treturn endpointUrl;" + NL + "}" + NL + "" + NL + "public static class StreamingDOM4JProvider extends org.apache.cxf.jaxrs.provider.dom4j.DOM4JProvider {" + NL + "" + NL + "\tpublic static final String SUPRESS_XML_DECLARATION = \"supress.xml.declaration\";" + NL + "" + NL + "\tpublic void writeTo(org.dom4j.Document doc, Class<?> cls, java.lang.reflect.Type type," + NL + "\t\t\tjava.lang.annotation.Annotation[] anns, javax.ws.rs.core.MediaType mt," + NL + "\t\t\tjavax.ws.rs.core.MultivaluedMap<String, Object> headers, java.io.OutputStream os)" + NL + "\t\t\t\t\tthrows java.io.IOException, javax.ws.rs.WebApplicationException {" + NL + "\t\tif (mt.getSubtype().contains(\"xml\")) {" + NL + "\t\t\torg.dom4j.io.XMLWriter writer;" + NL + "\t\t\tif (org.apache.cxf.jaxrs.utils.JAXRSUtils.getCurrentMessage().getExchange().containsKey(SUPRESS_XML_DECLARATION)) {" + NL + "\t\t\t\torg.dom4j.io.OutputFormat format = new org.dom4j.io.OutputFormat();" + NL + "\t\t\t\tformat.setSuppressDeclaration(true);" + NL + "\t\t\t\twriter = new org.dom4j.io.XMLWriter(os, format);" + NL + "\t\t\t} else {" + NL + "\t\t\t\twriter = new org.dom4j.io.XMLWriter(os);" + NL + "\t\t\t}" + NL + "\t\t\twriter.write(doc);" + NL + "\t\t\twriter.flush();" + NL + "\t\t} else {" + NL + "\t\t\tsuper.writeTo(doc, cls, type, anns, mt, headers, os);" + NL + "\t\t}" + NL + "\t}" + NL + "}" + NL + "" + NL + "" + NL + "Thread4RestServiceProviderEndpoint thread4RestServiceProviderEndpoint = null;" + NL + "" + NL + "class Thread4RestServiceProviderEndpoint extends Thread {" + NL + "" + NL + "\tprivate final String endpointUrl;" + NL + "" + NL + "\tprivate final ";
  protected final String TEXT_93 = " job;" + NL + "" + NL + "\tprivate org.apache.cxf.endpoint.Server server;" + NL + "" + NL + "\tpublic Thread4RestServiceProviderEndpoint(";
  protected final String TEXT_94 = " job, String endpointUrl) {" + NL + "\t\tthis.job = job;" + NL + "\t\tthis.endpointUrl = endpointUrl;" + NL + "\t}" + NL + "" + NL + "\tpublic void run() {" + NL + "\t\ttry {" + NL + "\t\t\tRestServiceProviderImpl4TalendJob provider = new RestServiceProviderImpl4TalendJob(job);" + NL + "" + NL + "\t\t\torg.apache.cxf.jaxrs.JAXRSServerFactoryBean sf =" + NL + "\t\t\t\t\tnew org.apache.cxf.jaxrs.JAXRSServerFactoryBean();" + NL + "\t\t\tjava.util.List<Object> providers = new java.util.ArrayList<Object>();" + NL + "\t\t\tproviders.add(new ExceptionMapper4TalendJobRestService(provider));" + NL + "\t\t\tproviders.add(new StreamingDOM4JProvider());" + NL + "\t\t\torg.apache.cxf.jaxrs.provider.json.JSONProvider jsonProvider =" + NL + "\t\t\t\t\tnew org.apache.cxf.jaxrs.provider.json.JSONProvider();" + NL + "\t\t\tjsonProvider.setIgnoreNamespaces(true);" + NL + "\t\t\tjsonProvider.setAttributesToElements(true);" + NL + "\t\t\t";
  protected final String TEXT_95 = NL + "\t\t\t\tjsonProvider.setSupportUnwrapped(true);" + NL + "\t\t\t\tjsonProvider.setWrapperName(\"root\");" + NL + "\t\t\t";
  protected final String TEXT_96 = NL + NL + "\t\t\t";
  protected final String TEXT_97 = NL + "\t\t\t\tjsonProvider.setConvertTypesToStrings(true);" + NL + "\t\t\t";
  protected final String TEXT_98 = NL + "\t\t\t\tjsonProvider.setConvertTypesToStrings(false);" + NL + "\t\t\t";
  protected final String TEXT_99 = NL + "\t\t\tproviders.add(jsonProvider);" + NL + "\t\t\tsf.setProviders(providers);" + NL + "\t\t\tsf.setTransportId(\"http://cxf.apache.org/transports/http\");" + NL + "\t\t\tsf.setResourceClasses(RestServiceProviderImpl4TalendJob.class);" + NL + "\t\t\tsf.setResourceProvider(RestServiceProviderImpl4TalendJob.class," + NL + "\t\t\t\t\tnew org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider(provider));" + NL + "\t\t\tsf.setAddress(endpointUrl);" + NL + "" + NL + "\t\t\tfinal java.util.List<org.apache.cxf.feature.Feature> features =" + NL + "\t\t\t\t\tnew java.util.ArrayList<org.apache.cxf.feature.Feature>();" + NL + "" + NL + "\t\t\t";
  protected final String TEXT_100 = NL + "\t\t\t\tsf.setBus(new org.apache.cxf.bus.spring.SpringBusFactory().createBus(\"META-INF/tesb/locator/beans.xml\"));" + NL + "\t\t\t\tsf.setServiceName(new javax.xml.namespace.QName(" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_101 = "," + NL + "\t\t\t\t\t\t";
  protected final String TEXT_102 = "));" + NL + "" + NL + "\t\t\t\torg.talend.esb.servicelocator.cxf.LocatorFeature slFeature =" + NL + "\t\t\t\t\t\tnew org.talend.esb.servicelocator.cxf.LocatorFeature();" + NL + "" + NL + "\t\t\t\t";
  protected final String TEXT_103 = NL + "\t\t\t\t";
  protected final String TEXT_104 = NL + "\t\t\t\t\t\tfinal java.util.Map<String, String> slCustomProps = new java.util.HashMap<String, String>();" + NL + "\t\t\t\t\t";
  protected final String TEXT_105 = NL + "\t\t\t\t\t\tslCustomProps.put(";
  protected final String TEXT_106 = ", ";
  protected final String TEXT_107 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_108 = NL + "\t\t\t\t\t\tslFeature.setAvailableEndpointProperties(slCustomProps);" + NL + "\t\t\t\t";
  protected final String TEXT_109 = NL + NL + "\t\t\t\tfeatures.add(slFeature);" + NL + "\t\t\t";
  protected final String TEXT_110 = NL + NL + "\t\t\t";
  protected final String TEXT_111 = NL + "\t\t\t\torg.springframework.context.support.ClassPathXmlApplicationContext context =" + NL + "\t\t\t\t\t\tnew org.springframework.context.support.ClassPathXmlApplicationContext(\"META-INF/tesb/agent-context.xml\");" + NL + "\t\t\t\torg.talend.esb.sam.agent.feature.EventFeature samEventFeature =" + NL + "\t\t\t\t\t\tcontext.getBean(org.talend.esb.sam.agent.feature.EventFeature.class);" + NL + "" + NL + "\t\t\t\t";
  protected final String TEXT_112 = NL + "\t\t\t\t";
  protected final String TEXT_113 = NL + "\t\t\t\t\torg.talend.esb.sam.common.handler.impl.CustomInfoHandler ciHandler =" + NL + "\t\t\t\t\t\t\tnew org.talend.esb.sam.common.handler.impl.CustomInfoHandler();" + NL + "\t\t\t\t\tjava.util.Map<String, String> samCustomProps_";
  protected final String TEXT_114 = " = new java.util.HashMap<String, String>();" + NL + "\t\t\t\t\t";
  protected final String TEXT_115 = NL + "\t\t\t\t\t\tsamCustomProps_";
  protected final String TEXT_116 = ".put(";
  protected final String TEXT_117 = ", ";
  protected final String TEXT_118 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_119 = NL + "\t\t\t\t\tciHandler.setCustomInfo(samCustomProps_";
  protected final String TEXT_120 = ");" + NL + "\t\t\t\t\tsamEventFeature.setHandler(ciHandler);" + NL + "\t\t\t\t";
  protected final String TEXT_121 = NL + NL + "\t\t\t\tfeatures.add(samEventFeature);" + NL + "\t\t\t";
  protected final String TEXT_122 = NL + NL + "\t\t\t";
  protected final String TEXT_123 = NL + "\t\t\t\tfeatures.add(new org.talend.esb.policy.correlation.feature.CorrelationIDFeature());" + NL + "\t\t\t";
  protected final String TEXT_124 = NL + NL + "\t\t\t";
  protected final String TEXT_125 = NL + "\t\t\t\tfeatures.add(new org.apache.cxf.feature.LoggingFeature());" + NL + "\t\t\t";
  protected final String TEXT_126 = NL + NL + "\t\t\tsf.setFeatures(features);" + NL + "" + NL + "\t\t\tserver = sf.create();" + NL + "" + NL + "\t\t\t// System.out.println(\"REST service [endpoint: \" + endpointUrl + \"] published\");" + NL + "\t\t} catch (Throwable e) {" + NL + "\t\t\te.printStackTrace();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic void stopEndpoint() {" + NL + "\t\tif (null != server) {" + NL + "\t\t\tserver.stop();" + NL + "\t\t\tserver.destroy();" + NL + "\t\t\t// System.out.println(\"REST service [endpoint: \" + endpointUrl + \"] unpublished\");" + NL + "\t\t}" + NL + "\t}" + NL + "}";
  protected final String TEXT_127 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
Vector v = (Vector) codeGenArgument.getArgument();
IProcess process = (IProcess) v.get(0);

List<? extends INode> httpRequestComponentsList = process.getNodesOfType("tRESTRequest");
if (!httpRequestComponentsList.isEmpty()) {

	final class URITemplateUtils {

		public static final String TEMPLATE_PARAMETERS = "jaxrs.template.parameters";
		public static final String LIMITED_REGEX_SUFFIX = "(/.*)?";
		public static final String FINAL_MATCH_GROUP = "FINAL_MATCH_GROUP";
		private static final String DEFAULT_PATH_VARIABLE_REGEX = "([^/]+?)";
		private static final String CHARACTERS_TO_ESCAPE = ".*+";

		private final String template;
		private final List<String> variables = new ArrayList<String>();
		private final List<String> customVariables = new ArrayList<String>();
		private final Pattern templateRegexPattern;
		private final String literals;
		private final List<UriChunk> uriChunks;

		public URITemplateUtils(String theTemplate) {
			template = theTemplate;
			StringBuilder literalChars = new StringBuilder();
			StringBuilder patternBuilder = new StringBuilder();
			CurlyBraceTokenizer tok = new CurlyBraceTokenizer(template);
			uriChunks = new ArrayList<UriChunk>();
			while (tok.hasNext()) {
				String templatePart = tok.next();
				UriChunk chunk = createUriChunk(templatePart);
				uriChunks.add(chunk);
				if (chunk instanceof Literal) {
					String encodedValue = encodePartiallyEncoded(chunk.getValue(), false);
					String substr = escapeCharacters(encodedValue);
					literalChars.append(substr);
					patternBuilder.append(substr);
				} else if (chunk instanceof Variable) {
					Variable var = (Variable)chunk;
					variables.add(var.getName());
					if (var.getPattern() != null) {
						customVariables.add(var.getName());
						patternBuilder.append('(');
						patternBuilder.append(var.getPattern());
						patternBuilder.append(')');
					} else {
						patternBuilder.append(DEFAULT_PATH_VARIABLE_REGEX);
					}
				}
			}
			literals = literalChars.toString();

			int endPos = patternBuilder.length() - 1;
			boolean endsWithSlash = (endPos >= 0) ? patternBuilder.charAt(endPos) == '/' : false;
			if (endsWithSlash) {
				patternBuilder.deleteCharAt(endPos);
			}
			patternBuilder.append(LIMITED_REGEX_SUFFIX);

			templateRegexPattern = Pattern.compile(patternBuilder.toString());
		}



		public String getLiteralChars() {
			return literals;
		}

		public String getValue() {
			return template;
		}

		/**
		 * List of all variables in order of appearance in template.
		 *
		 * @return unmodifiable list of variable names w/o patterns,
		 * e.g. for "/foo/{v1:\\d}/{v2}" returned list is ["v1","v2"].
		 */
		public List<String> getVariables() {
			return Collections.unmodifiableList(variables);
		}

		/**
		 * List of variables with patterns (regexps). List is subset of elements from {@link #getVariables()}.
		 *
		 * @return unmodifiable list of variables names w/o patterns.
		 */
		public List<String> getCustomVariables() {
			return Collections.unmodifiableList(customVariables);
		}

		private String escapeCharacters(String expression) {

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < expression.length(); i++) {
				char ch = expression.charAt(i);
				sb.append(isReservedCharacter(ch) ? "\\" + ch : ch);
			}
			return sb.toString();
		}

		private boolean isReservedCharacter(char ch) {
			return CHARACTERS_TO_ESCAPE.indexOf(ch) != -1;
		}




		/**
		 * Creates object form string.
		 *
		 * @param uriChunk stringified uri chunk
		 * @return If param has variable form then {@link Variable} instance is created,
		 * otherwise chunk is treated as {@link Literal}.
		 */
		public UriChunk createUriChunk(String uriChunk) {
			if (uriChunk == null || "".equals(uriChunk)) {
				throw new IllegalArgumentException("uriChunk is empty");
			}
			UriChunk uriChunkRepresentation = new Variable().create(uriChunk);
			if (uriChunkRepresentation == null) {
				uriChunkRepresentation = new Literal().create(uriChunk);
			}
			return uriChunkRepresentation;
		}

		/**
		 * Stringified part of URI. Chunk is not URI segment; chunk can span over multiple URI segments or one URI
		 * segments can have multiple chunks. Chunk is used to decompose URI of {@link URITemplate} into literals
		 * and variables. Example: "foo/bar/{baz}{blah}" is decomposed into chunks: "foo/bar", "{baz}" and
		 * "{blah}".
		 */
		abstract class UriChunk {

			public abstract String getValue();

			@Override
			public String toString() {
				return getValue();
			}
		}

		final class Literal extends UriChunk {
			private String value;

			private Literal() {
				// empty constructor
			}

			public Literal create(String uriChunk) {
				if (uriChunk == null || "".equals(uriChunk)) {
					throw new IllegalArgumentException("uriChunk is empty");
				}
				Literal literal = new Literal();
				literal.value = uriChunk;
				return literal;
			}

			@Override
			public String getValue() {
				return value;
			}

		}

		/**
		 * Variable of URITemplate. Variable has either "{varname:pattern}" syntax or "{varname}".
		 */
		final class Variable extends UriChunk {
			private final Pattern VARIABLE_PATTERN =
					Pattern.compile("(\\w[-\\w\\.]*[ ]*)(\\:(.+))?");
			private String name;
			private Pattern pattern;

			private Variable() {
				// empty constructor
			}

			/**
			 * Creates variable from stringified part of URI.
			 *
			 * @param uriChunk uriChunk chunk that depicts variable
			 * @return Variable if variable was successfully created; null if uriChunk was not a variable
			 */
			public Variable create(String uriChunk) {
				Variable newVariable = new Variable();
				if (uriChunk == null || "".equals(uriChunk)) {
					return null;
				}
				if (insideBraces(uriChunk)) {
					uriChunk = stripBraces(uriChunk).trim();
					Matcher matcher = VARIABLE_PATTERN.matcher(uriChunk);
					if (matcher.matches()) {
						newVariable.name = matcher.group(1).trim();
						if (matcher.group(2) != null && matcher.group(3) != null) {
							String patternExpression = matcher.group(3).trim();
							newVariable.pattern = Pattern.compile(patternExpression);
						}
						return newVariable;
					}
				}
				return null;
			}

			public String getName() {
				return name;
			}

			public String getPattern() {
				return pattern != null ? pattern.pattern() : null;
			}

			@Override
			public String getValue() {
				if (pattern != null) {
					return "{" + name + ":" + pattern + "}";
				} else {
					return "{" + name + "}";
				}
			}
		}

		/**
		 * Splits string into parts inside and outside curly braces. Nested curly braces are ignored and treated
		 * as part inside top-level curly braces. Example: string "foo{bar{baz}}blah" is split into three tokens,
		 * "foo","{bar{baz}}" and "blah". When closed bracket is missing, whole unclosed part is returned as one
		 * token, e.g.: "foo{bar" is split into "foo" and "{bar". When opening bracket is missing, closing
		 * bracket is ignored and taken as part of current token e.g.: "foo{bar}baz}blah" is split into "foo",
		 * "{bar}" and "baz}blah".
		 * <p>
		 * This is helper class for {@link URITemplate} that enables recurring literals appearing next to regular
		 * expressions e.g. "/foo/{zipcode:[0-9]{5}}/". Nested expressions with closed sections, like open-closed
		 * brackets causes expression to be out of regular grammar (is context-free grammar) which are not
		 * supported by Java regexp version.
		 */
		class CurlyBraceTokenizer {

			private List<String> tokens = new ArrayList<String>();
			private int tokenIdx;

			public CurlyBraceTokenizer(String string) {
				boolean outside = true;
				int level = 0;
				int lastIdx = 0;
				int idx;
				for (idx = 0; idx < string.length(); idx++) {
					if (string.charAt(idx) == '{') {
						if (outside) {
							if (lastIdx < idx) {
								tokens.add(string.substring(lastIdx, idx));
							}
							lastIdx = idx;
							outside = false;
						} else {
							level++;
						}
					} else if (string.charAt(idx) == '}' && !outside) {
						if (level > 0) {
							level--;
						} else {
							if (lastIdx < idx) {
								tokens.add(string.substring(lastIdx, idx + 1));
							}
							lastIdx = idx + 1;
							outside = true;
						}
					}
				}
				if (lastIdx < idx) {
					tokens.add(string.substring(lastIdx, idx));
				}
			}

			public boolean hasNext() {
				return tokens.size() > tokenIdx;
			}

			public String next() {
				if (hasNext()) {
					return tokens.get(tokenIdx++);
				} else {
					throw new IllegalStateException("no more elements");
				}
			}
		}

		/**
		 * Token is enclosed by curly braces.
		 *
		 * @param token text to verify
		 * @return true if enclosed, false otherwise.
		 */
		public boolean insideBraces(String token) {
			return token.charAt(0) == '{' && token.charAt(token.length() - 1) == '}';
		}

		/**
		 * Strips token from enclosed curly braces. If token is not enclosed method
		 * has no side effect.
		 *
		 * @param token text to verify
		 * @return text stripped from curly brace begin-end pair.
		 */
		public String stripBraces(String token) {
			if (insideBraces(token)) {
				return token.substring(1, token.length() - 1);
			} else {
				return token;
			}
		}




		// HttpUtils()

		private final Pattern ENCODE_PATTERN =
				Pattern.compile("%[0-9a-fA-F][0-9a-fA-F]");

		// there are more of such characters, ex, '*' but '*' is not affected by UrlEncode
		private static final String PATH_RESERVED_CHARACTERS = "=@/:";
		private static final String QUERY_RESERVED_CHARACTERS = "?/";

		private String componentEncode(String reservedChars, String value) {

			StringBuilder buffer = new StringBuilder();
			StringBuilder bufferToEncode = new StringBuilder();

			for (int i = 0; i < value.length(); i++) {
				char currentChar = value.charAt(i);
				if (reservedChars.indexOf(currentChar) != -1) {
					if (bufferToEncode.length() > 0) {
						buffer.append(urlEncode(bufferToEncode.toString()));
						bufferToEncode.setLength(0);
					}
					buffer.append(currentChar);
				} else {
					bufferToEncode.append(currentChar);
				}
			}

			if (bufferToEncode.length() > 0) {
				buffer.append(urlEncode(bufferToEncode.toString()));
			}

			return buffer.toString();
		}

		public String queryEncode(String value) {

			return componentEncode(QUERY_RESERVED_CHARACTERS, value);
		}

		public String urlEncode(String value) {

			try {
				value = java.net.URLEncoder.encode(value, "UTF-8");
			} catch (java.io.UnsupportedEncodingException ex) {
				// unlikely to happen
			}

			return value;
		}

		public String pathEncode(String value) {

			String result = componentEncode(PATH_RESERVED_CHARACTERS, value);
			// URLEncoder will encode '+' to %2B but will turn ' ' into '+'
			// We need to retain '+' and encode ' ' as %20
			if (result.indexOf('+') != -1) {
				result = result.replace("+", "%20");
			}
			if (result.indexOf("%2B") != -1) {
				result = result.replace("%2B", "+");
			}

			return result;
		}

		public boolean isPartiallyEncoded(String value) {
			return ENCODE_PATTERN.matcher(value).find();
		}

		/**
		 * Encodes partially encoded string. Encode all values but those matching pattern
		 * "percent char followed by two hexadecimal digits".
		 *
		 * @param encoded fully or partially encoded string.
		 * @return fully encoded string
		 */
		public String encodePartiallyEncoded(String encoded, boolean query) {
			if (encoded.length() == 0) {
				return encoded;
			}
			Matcher m = ENCODE_PATTERN.matcher(encoded);
			StringBuilder sb = new StringBuilder();
			int i = 0;
			while (m.find()) {
				String before = encoded.substring(i, m.start());
				sb.append(query ? queryEncode(before) : pathEncode(before));
				sb.append(m.group());
				i = m.end();
			}
			String tail = encoded.substring(i, encoded.length());
			sb.append(query ? queryEncode(tail) : pathEncode(tail));
			return sb.toString();
		}

	}

	INode node = httpRequestComponentsList.get(0);
	String cid = node.getUniqueName();

if (Boolean.valueOf(ElementParameterParser.getValue(node,"__NEED_AUTH__")) && "SAML".equals(ElementParameterParser.getValue(node,"__AUTH_TYPE__"))) {

    stringBuffer.append(TEXT_1);
     } 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
	// TalendString helper
	final class TalendString {
		private final String name;

		public TalendString(String name) {
			this.name = name;
		}

		// copy-paste from routines.TalendString
		public String getAsciiRandomString(int length) {
			java.util.Random random = new java.util.Random();
			int cnt = 0;
			StringBuffer buffer = new StringBuffer();
			char ch;
			int end = 'z' + 1;
			int start = ' ';
			while (cnt < length) {
				ch = (char) (random.nextInt(end - start) + start);
				if (Character.isLetterOrDigit(ch)) {
					buffer.append(ch);
					cnt++;
				}
			}
			return name + "_" + buffer.toString();
		}
	}

	final class ArrayHelper {
		public String codeGenArray(String[] array) {
			String out = "";
			if (null != array) {
				for (String item : array) {
					if (!out.isEmpty()) {
						out += ", ";
					}
					out += "\"" + item + "\"";
				}
			}
			return "{ " + out + " }";
		}
	}

	/**
	* JAXRS Parameter Representation
	*/
	final class Parameter {

		public static final String JAVA_TYPE_DOCUMENT_ID = "id_Document";

		private final String name;
		private final String javaType;
		private final String paramType;
		private final String defaultValue;
		private final String variableName;
		private final String pattern;
		private final boolean isNullable;

		public Parameter(String paramType, IMetadataColumn column) {
			this(paramType, column, false);
		}

		public Parameter(String paramType, IMetadataColumn column, boolean generateVariableName) {
			this.name = column.getLabel();

			String fieldType = column.getTalendType();
			this.javaType = (JAVA_TYPE_DOCUMENT_ID.equals(fieldType)) ? "org.dom4j.Document"
							: JavaTypesManager.getTypeToGenerate(JavaTypesManager.getJavaTypeFromId(fieldType), true);


			this.paramType = paramType;

			if (JAVA_TYPE_DOCUMENT_ID.equals(fieldType)
				|| JavaTypesManager.LIST.getId().equals(fieldType)
				|| JavaTypesManager.BYTE_ARRAY.getId().equals(fieldType)) {
				this.defaultValue = null;
				this.pattern = "";
			} else {
				this.defaultValue = column.getDefault();
				this.pattern = column.getPattern();
			}

			this.isNullable = column.isNullable();
			this.variableName = (generateVariableName) ? new TalendString(name).getAsciiRandomString(5) : name;
		}

		public Parameter(String name, String paramType, String javaType, boolean generateVariableName) {
			this(name, paramType, javaType, null, "", false, generateVariableName);
		}

		public Parameter(String name, String paramType, String javaType, String defaultValue, String pattern, boolean isNullable, boolean generateVariableName) {
			this.name = name;
			this.javaType = javaType;
			this.paramType = paramType;
			this.defaultValue = defaultValue;
			this.pattern = pattern;
			this.isNullable = isNullable;
			this.variableName = (generateVariableName) ? new TalendString(name).getAsciiRandomString(5) : name;
		}

		public String getName() {
			return name;
		}
		public String getJavaType() {
			return javaType;
		}
		public String getParamType() {
			return paramType;
		}
		public String getDefaultValue() {
			return defaultValue;
		}
		public String getVariableName() {
			return variableName;
		}
		public String getPattern(){
			return pattern;
		}
		public boolean isNullable(){
			return isNullable;
		}
	}

// "body" is reserved name for request body content parameter
final String SCHEMA_FIELD_NAME_4_REQUEST_BODY = "body";

final String javaType4UndeclaredParameter = JavaTypesManager.STRING.getId(); // == "id_String"
final String javaTypeWhole4UndeclaredParameter = JavaTypesManager.getTypeToGenerate(JavaTypesManager.STRING, true);

final Map<String, String[]> contentTypes = new HashMap<String, String[]>() {{
		put("XML", new String[] { "application/xml", "text/xml" });
		put("JSON", new String[] { "application/json" });
		put("XML-JSON", new String[] { "application/xml", "text/xml", "application/json" });
		put("FORM", new String[] { "application/x-www-form-urlencoded" });
		put("MULTIPART", new String[] { "multipart/form-data", "multipart/mixed", "multipart/related" });
		put("HTML", new String[] { "text/html" });
		put("ANY", new String[] { "*/*" });
	}};

final Map<String, String> restParameterTypes = new HashMap<String, String>() {{
		put("PATH", "javax.ws.rs.PathParam");
		put("MATRIX", "javax.ws.rs.MatrixParam");
		put("QUERY", "javax.ws.rs.QueryParam");
		put("HEADER", "javax.ws.rs.HeaderParam");
		put("FORM", "javax.ws.rs.FormParam");
		put("MULTIPART", "org.apache.cxf.jaxrs.ext.multipart.Multipart");
	}};

final Set<String> supportedTypes4Params = new HashSet<String>(Arrays.asList(
		// string
		JavaTypesManager.STRING.getId(),

		// boolean
		JavaTypesManager.BOOLEAN.getId(),

		// byte
		JavaTypesManager.BYTE.getId(),

		// number
		JavaTypesManager.SHORT.getId(),
		JavaTypesManager.INTEGER.getId(),
		JavaTypesManager.LONG.getId(),
		JavaTypesManager.FLOAT.getId(),
		JavaTypesManager.DOUBLE.getId(),
		JavaTypesManager.BIGDECIMAL.getId(),

		// List
		JavaTypesManager.LIST.getId(),

		// date
		JavaTypesManager.DATE.getId()
	));

final Set<String> supportedTypes4Body = new HashSet<String>(Arrays.asList(
		// string
		JavaTypesManager.STRING.getId(),

		// byte array
		JavaTypesManager.BYTE_ARRAY.getId(),

		// xml document
		Parameter.JAVA_TYPE_DOCUMENT_ID
	));

final Set<String> supportedTypes4Part = new HashSet<String>(Arrays.asList(
		// string
		JavaTypesManager.STRING.getId(),

		// boolean
		JavaTypesManager.BOOLEAN.getId(),

		// number
		JavaTypesManager.SHORT.getId(),
		JavaTypesManager.INTEGER.getId(),
		JavaTypesManager.LONG.getId(),
		JavaTypesManager.FLOAT.getId(),
		JavaTypesManager.DOUBLE.getId(),
		JavaTypesManager.BIGDECIMAL.getId(),

		// date
		JavaTypesManager.DATE.getId(),

		// byte
		JavaTypesManager.BYTE.getId(),

		// byte array
		JavaTypesManager.BYTE_ARRAY.getId(),

		// xml document
		Parameter.JAVA_TYPE_DOCUMENT_ID
	));

List<? extends IConnection> conns = node.getOutgoingSortedConnections();
if (null != conns && !conns.isEmpty()) {
	ArrayHelper arrayHelper = new ArrayHelper();
	List<Map<String, String>> mappings = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__SCHEMAS__");
	for (IConnection conn : conns) {
		if (!conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
			continue;
		}

		String connName = conn.getName();
		String httpVerb = null;
		String uriPattern = null;
		String consumes = null;
		String produces = null;
		boolean streaming = false;

		boolean foundMapping = false;
		for (Map<String, String> mapping : mappings) {
			String outputConn = mapping.get("SCHEMA");
			if (connName.equals(outputConn)) {
				foundMapping = true;
				httpVerb = mapping.get("HTTP_VERB");
				uriPattern = mapping.get("URI_PATTERN");
				consumes = mapping.get("CONSUMES");
				produces = mapping.get("PRODUCES");
				streaming = ("true").equals(mapping.get("STREAMING"));
			}
		}
		if (!foundMapping) {
			continue;
		}

		if (null == consumes) {
			consumes = "";
		}
		if (null == produces) {
			produces = "";
		}


		boolean formSupported = "FORM".equals(consumes);
		boolean multipartSupported = "MULTIPART".equals(consumes);
		boolean noBodyRequest = "GET".equals(httpVerb) || "DELETE".equals(httpVerb) || "HEAD".equals(httpVerb) || "OPTIONS".equals(httpVerb);

		// All the parameters specified in the Schema Editor need to be handled.
		Parameter bodyParameter = null;
		Map<String, Parameter> parameters = new HashMap<String, Parameter>();
		if (null != uriPattern) {
			IMetadataTable connMetadata = conn.getMetadataTable();

			// determine request body type
			for (IMetadataColumn column : connMetadata.getListColumns()) {
				if (SCHEMA_FIELD_NAME_4_REQUEST_BODY.equals(column.getLabel())) {
					// rest parameter type
					String restParameterType = column.getComment();
					restParameterType = (null == restParameterType) ? "" : restParameterType.trim().toUpperCase();

					// check that the field is not one of supported rest parameter type
					if (!restParameterTypes.keySet().contains(restParameterType)) {
						if (supportedTypes4Body.contains(column.getTalendType())) {
							bodyParameter = new Parameter(null, column);
						}
					}
					break;
				}
			}

			URITemplateUtils uriTemplate = new URITemplateUtils(uriPattern);
			List<String> uriVariables = uriTemplate.getVariables();
			// we are going to declare all uri template variables ("Path" parameter type by REST)
			for (String uriVariable : uriVariables) {
				Parameter parameter = null;
				String schemaFieldType = javaType4UndeclaredParameter;

				for (IMetadataColumn column : connMetadata.getListColumns()) {
					// schema parameter name
					String schemaFieldName = column.getLabel();
					if (uriVariable.equals(schemaFieldName)) {
						String restParameterType = column.getComment();
						schemaFieldType = column.getTalendType();

						if (restParameterType == null || restParameterType.trim().length() == 0 || "PATH".equalsIgnoreCase(restParameterType.trim())) {
							if (SCHEMA_FIELD_NAME_4_REQUEST_BODY.equals(schemaFieldName) && null != bodyParameter) {
								// "body" field in schema really sute for request content (by rest type and field type)
								// that mean uri parameter "body" is hidden by reserved request content field "body"
								// -> will use generated variable name in rest service method with default String type
								//    (and this uri parameter will not be set to flow field later)
								schemaFieldType = javaType4UndeclaredParameter;
								parameter = new Parameter(uriVariable, "PATH", javaTypeWhole4UndeclaredParameter, true);
							} else if (supportedTypes4Params.contains(schemaFieldType)) {
								parameter = new Parameter("PATH", column);
							} else {
								// defined java type is unsupported
								// -> will define with default String type below
								//    (and this parameter will not be set to flow field later)
							}
						} else {
							// uri parameter hidden by parameter of another type in schema
							// -> will use generated variable name in rest service method with default String type
							//    (and this uri parameter will not be set to flow field later)
							schemaFieldType = javaType4UndeclaredParameter;
							parameter = new Parameter(uriVariable, "PATH", javaTypeWhole4UndeclaredParameter, true);
						}
						break;
					}
				}
				if (null == parameter) {
					// either no uri parameter definition in flow schema
					// (both by uri variable name and schema parameter type = 'Path')
					// or unsupported java type in schema parameter definition
					// so we will declare default java type - String
					// (and this uri parameter will not be set to flow field later)
					schemaFieldType = javaType4UndeclaredParameter;
					parameter = new Parameter(uriVariable, "PATH", javaTypeWhole4UndeclaredParameter, false);
				}
				parameters.put("PATH:" + uriVariable + ":" + schemaFieldType, parameter);
			}

			// now we are going to declare all other (non uri template) variables ("Query", "Form", "Header", "Matrix", "Multipart" parameter types by REST)
			for (IMetadataColumn column : connMetadata.getListColumns()) {
				// rest parameter type
				String restParameterType = column.getComment();
				// skip "Path" parameters as we already collect uri template parameters
				if (restParameterType == null || restParameterType.trim().length() == 0 || "PATH".equalsIgnoreCase(restParameterType.trim())) {
					continue;
				}
				restParameterType = restParameterType.trim().toUpperCase();
				// must be a supported parameter type
				if (!restParameterTypes.keySet().contains(restParameterType)) {
					// skip unsupported (wrong) JAX-RS parameter type
					continue;
				}

				Set<String> supportedTypes = supportedTypes4Params;
				if ("FORM".equals(restParameterType)) {
					if (multipartSupported) {
						continue; // ignore this parameter (as current rest service method configured as form supported)
					}
				} else if ("MULTIPART".equals(restParameterType)) {
					if (formSupported) {
						continue; // ignore this parameter (as current rest service method configured as miltipart supported)
					}
					supportedTypes = supportedTypes4Part;
				}

				// schema parameter name
				String schemaFieldName = column.getLabel();
				// schema parameter type
				String schemaFieldType = column.getTalendType();
				if (supportedTypes.contains(schemaFieldType)) {
					Parameter parameter = new Parameter(restParameterType, column, SCHEMA_FIELD_NAME_4_REQUEST_BODY.equals(schemaFieldName));
					parameters.put(restParameterType + ":" + schemaFieldName + ":" + schemaFieldType, parameter);
				} else {
					// the Java type is unsupported
				}
			}
		}

     if ("PATCH".equals(httpVerb)) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(httpVerb);
    stringBuffer.append(TEXT_9);
    } else {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(httpVerb);
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
     if (null != uriPattern) { 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(uriPattern);
    stringBuffer.append(TEXT_14);
     } 
    
if (!noBodyRequest) {
	String consumesContentTypes = null;
	if (contentTypes.containsKey(consumes)) {
		consumesContentTypes = arrayHelper.codeGenArray(contentTypes.get(consumes));
	} else if (null != bodyParameter) {
		if ("org.dom4j.Document".equals(bodyParameter.getJavaType())) {
			consumesContentTypes = arrayHelper.codeGenArray(contentTypes.get("XML-JSON"));
		} else if ("String".equals(bodyParameter.getJavaType())) {
			consumesContentTypes = arrayHelper.codeGenArray(new String[] {"text/plain", "application/xml", "text/xml", "application/json"});
		} else if ("byte[]".equals(bodyParameter.getJavaType())) {
			consumesContentTypes = arrayHelper.codeGenArray(new String[] {"application/octet-stream"});
		} else {
			consumesContentTypes = arrayHelper.codeGenArray(contentTypes.get("ANY"));
		}
	}
	if (null != consumesContentTypes) { 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(consumesContentTypes);
    stringBuffer.append(TEXT_16);
     }
}
if (contentTypes.containsKey(produces)) {
	String producesContentTypes = arrayHelper.codeGenArray(contentTypes.get(produces));

    stringBuffer.append(TEXT_17);
    stringBuffer.append(producesContentTypes);
    stringBuffer.append(TEXT_18);
     } else if ("ONEWAY".equals(produces)) {

    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_21);
    	boolean paramCommaWritten = false;
			for (Parameter param : parameters.values()) {
				if (!paramCommaWritten) {
					paramCommaWritten = true;
				} else {
					
    stringBuffer.append(TEXT_22);
    
				}
				String javaType = param.getJavaType();
				if ("List".equals(javaType)) {
					javaType = "List<String>";
				}

				String variableName = param.getVariableName();
				if ("java.util.Date".equals(javaType)) {
					javaType = "String";
					variableName += "_" + cid;
				}

				if (param.getDefaultValue() != null && param.getDefaultValue().length() > 0) {
					if ("String".equals(javaType)) { 
    stringBuffer.append(TEXT_23);
    stringBuffer.append(param.getDefaultValue());
    stringBuffer.append(TEXT_24);
    
					} else { 
    stringBuffer.append(TEXT_25);
    stringBuffer.append(param.getDefaultValue());
    stringBuffer.append(TEXT_26);
    
					}
				} 
    stringBuffer.append(TEXT_27);
    stringBuffer.append(restParameterTypes.get(param.getParamType()));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(param.getName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append( javaType);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_31);
    	} // loop thru jax-rs parameters 
    stringBuffer.append(TEXT_32);
     if (!(noBodyRequest || formSupported || multipartSupported) && null != bodyParameter) {
			if (paramCommaWritten) { 
    stringBuffer.append(TEXT_33);
     } 
    stringBuffer.append(bodyParameter.getJavaType());
    stringBuffer.append(TEXT_34);
     } 
    stringBuffer.append(TEXT_35);
    
		for (Parameter param : parameters.values()) {
			String variableName = param.getVariableName();

			if ("java.util.Date".equals(param.getJavaType())) {

    stringBuffer.append(TEXT_36);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_37);
     if (param.getPattern() != null && !"".equals(param.getPattern().trim())) { 
    stringBuffer.append(TEXT_38);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(param.getPattern());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
     } 
    stringBuffer.append(TEXT_47);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
     if (!param.isNullable()) { 
    stringBuffer.append(TEXT_56);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_58);
     } 
    
			}
		} // loop thru jax-rs parameters (for Date params handling)

    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(httpVerb);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(uriPattern);
    stringBuffer.append(TEXT_66);
     if (!(noBodyRequest || formSupported || multipartSupported) && null != bodyParameter) { 
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
     } 
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    	for (java.util.Map.Entry<String, Parameter> paramEntry : parameters.entrySet()) { 
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(paramEntry.getKey());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(paramEntry.getValue().getVariableName());
    stringBuffer.append(TEXT_75);
    	} 
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
     if (multipartSupported) { 
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    		for (Parameter param : parameters.values()) {
			if ("MULTIPART".equals(param.getParamType())) { 
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(param.getVariableName());
    stringBuffer.append(TEXT_83);
    			}
		} 
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
     } 
    stringBuffer.append(TEXT_87);
    stringBuffer.append(((streaming) ? "StreamingResponse" : ""));
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    
	}
}

    stringBuffer.append(TEXT_90);
    
	String defaultUri = (String) System.getProperties().get("restServiceDefaultUri");
	if (null == defaultUri || defaultUri.trim().isEmpty() || !defaultUri.contains("://")) {
		defaultUri = "http://127.0.0.1:8090/";
	} else if (!defaultUri.endsWith("/")) {
		defaultUri = defaultUri + "/";
	}

    stringBuffer.append(TEXT_91);
    stringBuffer.append(defaultUri);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_94);
     if (Boolean.valueOf(ElementParameterParser.getValue(node,"__WRAP_JSON_REQUEST__"))) { 
    stringBuffer.append(TEXT_95);
     } 
    stringBuffer.append(TEXT_96);
     if (Boolean.valueOf(ElementParameterParser.getValue(node,"__CONVERT_JSON_VALUES_TO_STRING__"))) { 
    stringBuffer.append(TEXT_97);
     } else { 
    stringBuffer.append(TEXT_98);
     } 
    stringBuffer.append(TEXT_99);
     if (Boolean.valueOf(ElementParameterParser.getValue(node,"__SERVICE_LOCATOR__"))) { 
    stringBuffer.append(TEXT_100);
    stringBuffer.append(ElementParameterParser.getValue(node,"__SERVICE_NAMESPACE__"));
    stringBuffer.append(TEXT_101);
    stringBuffer.append(ElementParameterParser.getValue(node,"__SERVICE_NAME__"));
    stringBuffer.append(TEXT_102);
     List<Map<String, String>> customProperties = (List<Map<String,String>>)
						ElementParameterParser.getObjectValue(node, "__SERVICE_LOCATOR_CUSTOM_PROPERTIES__"); 
    stringBuffer.append(TEXT_103);
     if (!customProperties.isEmpty()) { 
    stringBuffer.append(TEXT_104);
     for (int k = 0; k < customProperties.size(); k++) { 
    stringBuffer.append(TEXT_105);
    stringBuffer.append(customProperties.get(k).get("PROP_NAME"));
    stringBuffer.append(TEXT_106);
    stringBuffer.append(customProperties.get(k).get("PROP_VALUE"));
    stringBuffer.append(TEXT_107);
     } 
    stringBuffer.append(TEXT_108);
     } 
    stringBuffer.append(TEXT_109);
     } 
    stringBuffer.append(TEXT_110);
     if (Boolean.valueOf(ElementParameterParser.getValue(node,"__SERVICE_ACTIVITY_MONITOR__"))) { 
    stringBuffer.append(TEXT_111);
     List<Map<String, String>> samCustomProperties = (List<Map<String,String>>)
						ElementParameterParser.getObjectValue(node, "__SERVICE_ACTIVITY_MONITOR_CUSTOM_PROPERTIES__"); 
    stringBuffer.append(TEXT_112);
     if (!samCustomProperties.isEmpty()) { 
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
     for (int k = 0; k < samCustomProperties.size(); k++) { 
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(samCustomProperties.get(k).get("PROP_NAME"));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(samCustomProperties.get(k).get("PROP_VALUE"));
    stringBuffer.append(TEXT_118);
     } 
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
     } 
    stringBuffer.append(TEXT_121);
     } 
    stringBuffer.append(TEXT_122);
     if (Boolean.valueOf(ElementParameterParser.getValue(node,"__USE_BUSINESS_CORRELATION__"))) { 
    stringBuffer.append(TEXT_123);
     } 
    stringBuffer.append(TEXT_124);
     if (Boolean.valueOf(ElementParameterParser.getValue(node,"__LOG_MESSAGES__"))) { 
    stringBuffer.append(TEXT_125);
     } 
    stringBuffer.append(TEXT_126);
    
}

    stringBuffer.append(TEXT_127);
    return stringBuffer.toString();
  }
}
