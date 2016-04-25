package ab.lims.service;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.camel.util.IOHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sapphire.accessor.ActionException;
import sapphire.action.BaseAction;
import sapphire.util.ActionBlock;
import sapphire.xml.PropertyList;
import ab.lims.AbException;
import ab.lims.AbFatalException;
import ab.lims.util.LimsUtil;

import com.labvantage.sapphire.webservices.transport.ActionBlockTransportBean;
import com.labvantage.sapphire.webservices.transport.PropertyListCollectionTransportBean;
import com.labvantage.sapphire.webservices.transport.PropertyListTransportBean;
import com.labvantage.sapphire.webservices.transport.PropertyValueTransportBean;

/**
 * @author pnatar
 * 
 *         For communicating with lims service
 */
public class LimsServiceLV7Impl implements LimsService {
  private static Log log = LogFactory.getLog(LimsServiceLV7Impl.class);
  @Value("${lims.labvantage.db.name}")
  private String limsDBName;
  @Value("${lims.labvantage.db.user}")
  private String limsDbUser;
  @Value("${lims.labvantage.db.pwd}")
  private String limsDbPwd; // not recommended
  @Value("${lims.labvantage.wsdl.endpoint}")
  String endpoint;

//  public LimsServiceLV7Impl() {
//  }

  public final void acceptCreateRunResponse(String msgBody) {
    acceptMQResponse(LimsAction.CREATE_RUN, msgBody); // runs_for_lims
  }

  public final void acceptAnalyseRunResponse(String msgBody) {
    acceptMQResponse(LimsAction.ANALYSE_RUN, msgBody); // runs_for_lims
  }

  public final void acceptAnalyseSequenceResponse(String msgBody) {
    acceptMQResponse(LimsAction.ANALYSE_SEQUENCE, msgBody); // seqs_for_lims
  }

  @Override
  public final void acceptAnalyseSampleResponse(String msgBody) {
    acceptMQResponse(LimsAction.ANALYSE_SAMPLE, msgBody); // patients_for_lims
  }


  // for use when action name is defined in LimsAction
  @Override
  public void acceptMQResponse(LimsAction limsAction, String msgBody) {
    log.debug(String.format("AboutTo Receive %s_RES body[%s] ", limsAction.toString() ,msgBody));
    //prepare map
    Map<String,String> map = new HashMap<String,String>(1);
    map.put("ExperimentList", msgBody);
    //call
    //acceptMQResponse(limsAction.actionName(), map); // patients_for_lims
    //execActionBlock(limsAction.actionName(), map);
    processAction(limsAction.actionName(), map);
    log.debug(String.format("Processed %s_RES body[%s] ", limsAction.toString() ,msgBody));
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Map<String,String> processAction(String actionName, Map<String, String> map) {
    log.debug("RunWebService started...");
    log.info("Web service URL = " + endpoint);
    PropertyList propList = LimsUtil.toPropList(map);
    String connId = getConnectionId();
    log.debug("Got connection id ..." + connId);
    Call callForAction = getCallObjectForProcessAction();

    // finally run the method converting back and forth from actionblock to
    // bean transport
    Map<String,String> returnMapProps = new HashMap<String,String>(5);
    try {
      String retProp = (String) callForAction.invoke( new Object[] { connId, actionName, "1", propList.toXMLString() } );
      PropertyList propListFromServer = new PropertyList();
      propListFromServer.setPropertyList(retProp);
      returnMapProps = propListFromServer;

    } catch (Exception e) {
      Throwable causingEx = LimsUtil.getCause(e);
      String errMsg = "LimsService Error calling ActionBlock for endpoint: " + endpoint;
      log.error(errMsg, causingEx);
      throw new AbFatalException(errMsg, e);
    }
    finally{
      releaseConnectionId(connId);//VERY IMPORTANT!
    }
    String status = returnMapProps.get("STATUS");
    if(status!= null && status.equals("SUCCESS")){ // no exception happened
      log.info("Acction call successful actionName="+actionName);  
    }else{
      String serverSideError = returnMapProps.get("ERROR_MSG");
      throw new AbException("Error calling actionName="+actionName+" Server-ERROR="+serverSideError);
    }
    return returnMapProps;
  } 

  private Service service = new Service(); // check if this can be cached, for

  /* For invoking multiple actions
   * (non-Javadoc)
   * @see ab.lims.service.LimsService#execActionBlock(java.lang.String, java.util.Map)
   */
  @Override
  public void execActionBlock(String actionName,  Map<String,String> map) {
    //PropertyList propList = LimsUtil.toPropList(map);
    ActionBlock ab = new ActionBlock();
    try {
      ab.setAction(actionName, actionName, "1");
    } catch (ActionException e) {
      throw new AbException("LimsService Error crating Action() for endpoint: " + endpoint, e);
    }
    invokeActionBlock(ab, map);
  }
  
  
  /* For invoking multiple actions using a action class name
   * (non-Javadoc)
   * @see ab.lims.service.LimsService#execActionBlock(java.lang.Class, java.util.Map)
   */
  @Override
  public void execActionBlock(Class<? extends BaseAction> actionClass, final Map<String,String> map) {
    //PropertyList propList = LimsUtil.toPropList(map);
    ActionBlock ab = new ActionBlock();
    try {
      ab.setActionClass(actionClass.toString(), actionClass.toString());
    } catch (ActionException e) {
      throw new AbException("LimsService Error crating Action() for endpoint: " + endpoint, e);
    }
    invokeActionBlock(ab, map);
  }

  private void invokeActionBlock(ActionBlock ab, Map<String, String> map) {
    log.info("RunWebService started...");
    log.info("Web service URL = " + endpoint);
    PropertyList propList = LimsUtil.toPropList(map);
    String connId = getConnectionId();
    log.info("Got connection id ..." + connId);
    Call callForAction = getCallObjectForActionBlock();

    // finally run the method converting back and forth from actionblock to
    // bean transport
    try {
      ab.setBlockProperties(propList);
      
      ab = ((ActionBlockTransportBean) callForAction.invoke(new Object[] {connId, new ActionBlockTransportBean(ab)})).toActionBlock();
    } catch (Exception e) {
      Throwable causingEx = LimsUtil.getCause(e);
      String errMsg = "LimsService Error calling ActionBlock for endpoint: " + endpoint;
      log.error(errMsg, causingEx);
      throw new AbFatalException(errMsg, e);
    }
    finally{
      releaseConnectionId(connId);//VERY IMPORTANT!
    }
    log.debug("Sent....action block");
    log.debug("New Sample: " + ab.getBlockProperty("newkeyid1"));
  }
  
  


  // how long
 // private String cachedConnectionid;

  @Override
  public String getConnectionId() {//Should be used only for tests - potential for leaking connection
    String returnConnId = null;
//    Call call = getCallObject();
//    if(cachedConnectionid == null || (isConnectionValid(cachedConnectionid) == false) ) {
//      returnConnId = cachedConnectionid = getNewConnection();
//    }else{// connection valid
//          returnConnId = cachedConnectionid;
//    }
    returnConnId = getNewConnection();
    return returnConnId;
  }
  
  private String getNewConnection(){
    Call call = getCallObject();
    String connectionId;
    try {
      
      call.setOperationName(new QName("http://soapinterop.org/", "getConnectionId"));
      connectionId = (String) call.invoke(new Object[]{limsDBName, limsDbUser, limsDbPwd});

    } catch (Exception ex) {
      log.error("LimsService Error getting connectionId for endpoint: " + endpoint, ex);
      Throwable causingEx = LimsUtil.getCause(ex);
      throw new AbFatalException("LimsService Error getting connectionId for endpoint: " + endpoint, causingEx);
    }
    return connectionId;
  }
//  private boolean isConnectionValid(String connectionId){
//    boolean isConnValid =  false;
//    try {
//      Call call = getCallObject();
//      call.setOperationName(new QName("http://soapinterop.org/", "checkConnection"));
//      isConnValid = (Boolean) call.invoke(new Object[]{connectionId});
//    } catch (RemoteException e) {
//
//    }
//    return isConnValid;
//  }

  private Call getCallObject() {
    try {
      Call call;
      call = (Call) service.createCall();
      call.setTargetEndpointAddress(new java.net.URL(endpoint));
      return call;
    } catch (Exception ex) {
      Throwable causingEx = LimsUtil.getCause(ex);
      throw new AbFatalException("LimsService Error making Call() object for endpoint: " + endpoint, causingEx);
    }
  }

  private Call getCallObjectForActionBlock() {
    try {
      Call call = getCallObject();
      call.setOperationName(new QName("http://soapinterop.org/", "processActionBlock"));
      // R5.2 web services use Beans to transport real objects accross
      // soap. In .NET you then just see the real object returned, however
      // in Axis you need to register the type mapping for the bean. You
      // can also use WSDLToJava to build the stubs instead of manual type
      // mapping
      QName actionBlockTransportBeanQname = new QName("com.labvantage.sapphire.webservices", "ActionBlockTransport");
      Class<?> actionBlockTransportBeanClass = ActionBlockTransportBean.class;
      call.registerTypeMapping(actionBlockTransportBeanClass, actionBlockTransportBeanQname, BeanSerializerFactory.class, BeanDeserializerFactory.class);
      // As the action block also uses property lists we also need
      // to register the propertylist bean. Not needed if u use wsdl
      QName propertyListTransportBeanQname = new QName("com.labvantage.sapphire.webservices", "PropertyListTransport");
      Class<?> propertyListTransportBeanClass = PropertyListTransportBean.class;
      call.registerTypeMapping(propertyListTransportBeanClass, propertyListTransportBeanQname, BeanSerializerFactory.class, BeanDeserializerFactory.class);
      QName propertyListCollectionTransportBeanQname = new QName("com.labvantage.sapphire.webservices", "PropertyListCollectionTransport");
      Class<PropertyListCollectionTransportBean> propertyListCollectionTransportBeanClass = PropertyListCollectionTransportBean.class;
      call.registerTypeMapping(propertyListCollectionTransportBeanClass, propertyListCollectionTransportBeanQname, BeanSerializerFactory.class, BeanDeserializerFactory.class);
      QName propertyValueTransportBeanQname = new QName("com.labvantage.sapphire.webservices", "PropertyValueTransport");
      Class<PropertyValueTransportBean> propertyValueTransportBeanClass = PropertyValueTransportBean.class;
      call.registerTypeMapping(propertyValueTransportBeanClass, propertyValueTransportBeanQname, BeanSerializerFactory.class, BeanDeserializerFactory.class);

      // now need to specify the types of the parameters and
      // return object
      call.setReturnType(actionBlockTransportBeanQname);
      call.addParameter("connectionId", org.apache.axis.Constants.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
      call.addParameter("actionBlock", actionBlockTransportBeanQname, javax.xml.rpc.ParameterMode.IN);

      return call;
    } catch (Exception ex) {
      throw new AbException("LimsService Error making Call() object for endpoint: " + endpoint, ex);
    }
  }
  
  private Call getCallObjectForProcessAction(){
    Call call = getCallObject();
    call.setOperationName(new QName("http://soapinterop.org/", "processAction"));
    return call;
  }
  
  
  
  @Override
  public void releaseConnectionId(String connectionId){
    try {
      Call call = getCallObject();
      call.setOperationName(new QName("http://soapinterop.org/", "clearConnection"));
      call.invoke(new Object[]{connectionId});
    } catch (RemoteException e) {
      throw new AbException("Error clearing connection id="+connectionId, e);
    }
  }

}
