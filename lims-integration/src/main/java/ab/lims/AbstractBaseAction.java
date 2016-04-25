package ab.lims;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sapphire.xml.PropertyList;
import ab.lims.service.MailService;
import ab.lims.spring.util.SpringPropUtil;
import sapphire.SapphireException;
// import ab.lims.spring.util.SpringPropAccessor;
import sapphire.action.BaseAction;

/**
 * @author pnatar
 * 
 * Abstraction for every action. Automatically takes care of error propagation and logging.
 * 
 *
 */
@Component
public abstract class AbstractBaseAction extends BaseAction {// implements ApplicationContextAware
  private static Log log = LogFactory.getLog(AbstractBaseAction.class);


  public abstract void doCustomAction(PropertyList properties);
  @Autowired
  protected MailService mailService;

  @Override
  public void processAction(PropertyList properties) throws SapphireException {
    try {
      checkSpringInfra();//Experimental
      doCustomAction(properties); //call the implementation method of action.
      properties.setProperty("STATUS", "SUCCESS");
    } catch (Exception e) {
      String msg = "Error while processing action at:" + this.getClass().getName();
      this.setError(msg, e); // for UI
      
      log.error(msg, e); // for log
      mailService.alert(msg, new AbException(e));//for ops
      properties.setProperty("STATUS", "FAIL");
      properties.setProperty("ERROR_MSG", e.getMessage());
      //TODO check if throw required for system function.
      // If error thrown you won't get the property message.
      // throw e;
    }

  }
  
  //Experimental
  private void checkSpringInfra(){
    try {
      Class<? extends AbstractBaseAction> classType = this.getClass();
      String className = classType.getName(); // queryProcessor = getQueryProcessor();
      mailService =  SpringPropUtil.getBean(MailService.class);
      AbstractBaseAction bean = (AbstractBaseAction) SpringPropUtil.getBean(Class.forName(className));
      if (null == bean){
        log.error("Bean not found for class =" + className);
      }
      log.info("Succefuly retrieved bean for class =" + className);
      //bean.doCustomAction(properties);processSpringAction(bean, properties);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    
  }

}

//public final Object getBean(final String beanName) {
//return CONTEXT.getBean(beanName);
//}
//
//public static final <T> T getBean(final Class<T> requiredType) {
//return CONTEXT.getBean(requiredType);
//}
//
//public String getSpringProperty(String propertyKey) {
//// SpringPropAccessor propAccessor = CONTEXT.getBean(SpringPropAccessor.class);
//return SpringPropUtil.getProperty(propertyKey);
//}
//@Autowired
//// private SpringPropAccessor propAccessor;
//private static ApplicationContext CONTEXT;

// @Override
// public void setApplicationContext(ApplicationContext ctx) throws BeansException {
// log.info("Setting context in this class");
// CONTEXT = ctx;
// }
// @Override
// public void processAction(PropertyList properties) throws SapphireException {
// try {
// Class classType = this.getClass();
// String className = classType.getName(); // queryProcessor = getQueryProcessor();
//
// bean = (DefaultSpringAction) SpringPropUtil.getBean(Class.forName(className));
// if (null == bean)
// throw new Exception("Bean not found for class =" + className);
// logger.info("Succefuly retrieved bean for class =" + className);
// bean.processSpringAction(bean, properties);
// } catch (Exception ex) {
// throw new RuntimeException(ex);
// }
// }
