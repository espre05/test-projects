package ab.lims.spring.util;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import ab.lims.AbException;

/**
 * @author pnatar
 * 
 * This util class is used to fetch spring properties. 
 * This is a workaround class as labvantage does not support spring.
 */
@Component
public class SpringPropUtil implements ApplicationContextAware {
  private static Logger log = LoggerFactory.getLogger(SpringPropUtil.class);

  @Autowired
  private SpringPropCache propAccessor;

  private static ApplicationContext CONTEXT;

  @Override
  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    CONTEXT = ctx;
    StringBuilder sb = new StringBuilder(1000);
    sb.append("\n\n\t##############################################\n\t")
        .append("Spring Context set on this class  ---> ").append("\t\t\tlims.app.rootDir.path=")
        .append(getProperty("lims.app.rootDir.path"))
        .append("\n\t##############################################\n");
    log.info(sb.toString());

  }

  public final Object getBean(final String beanName) {
    return CONTEXT.getBean(beanName);
  }

  public static final <T> T getBean(final Class<T> requiredType) {
    return CONTEXT.getBean(requiredType);
  }

  public static final String getProperty(String propertyKey) {
    SpringPropCache propAccessor = CONTEXT.getBean(SpringPropCache.class);
    String propValue = propAccessor.getProperty(propertyKey);
    createDirsIfMissing(propertyKey, propValue);
    return propValue;
  }

  private static void createDirsIfMissing(String propKey, String propValue) {
    if (propValue == null || StringUtils.isEmpty(propValue)) {
      return;
    }
    final String DIR_PATH_SUFFIX = "Dir.path";
    final String FILE_PATH_SUFFIX = "File.path";
    final String FILE_PARTIAL_PATH_SUFFIX = "File.partial";
    Path dirToCreate = null;
    if (propKey.endsWith(DIR_PATH_SUFFIX)) {//direct DIR
      dirToCreate = Paths.get(propValue);
    } else if (propKey.endsWith(FILE_PATH_SUFFIX)) {//A file path, create parent dirs
      dirToCreate = Paths.get(propValue).getParent();
    } else if (propKey.endsWith(FILE_PARTIAL_PATH_SUFFIX)) {//A file path, create parent dirs
      dirToCreate = Paths.get(propValue).getParent(); 
    } else {
      // Ignore other endings
      return;
    }
    if (dirToCreate != null && Files.notExists(dirToCreate)) {
      log.warn("\n\n\t##################### Creating missing directoy dir="+ dirToCreate + "   ##########################");
      try {
        Files.createDirectories(dirToCreate);
      } catch (Exception e) {
        throw new AbException("Error while creating missing directory. Check PropKey="+propKey + " path:" + propValue, e);
      }
    }


  }

  public String getSpringProperty(String propertyKey) {
    // SpringPropAccessor propAccessor = CONTEXT.getBean(SpringPropAccessor.class);
    return propAccessor.getProperty(propertyKey);
  }

}
