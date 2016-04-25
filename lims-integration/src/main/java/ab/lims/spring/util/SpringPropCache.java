package ab.lims.spring.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author pnatar
 * Internally usd by SpringPropUtil. Not to be used directly - thats the reason it is not "public class"
 * 
 * This class is not required if spring injection is used normally, i.e without SpringPropUtil. Necessary because Labvantage does not support spring.
 */
@Component 
@ManagedResource(objectName = "AdBio_Lims:name=AppConfig")
final class SpringPropCache {
	@Autowired
    private AbstractBeanFactory beanFactory;

    private final Map<String,String> cache = new ConcurrentHashMap<String, String>(); 

    @ManagedOperation(description = "Reset value")
    public  String resetProperty(String key, String value) {
    	if(cache.containsKey(key)){
    		cache.remove(key); 
    		cache.put(key, value); //TODO  use replace in Jdk 7
        }else {
        	cache.put(key, value);
        }
    	return cache.get(key); 
    	
    }
    
    @ManagedOperation(description = "Returns something.")
    public  String getProperty(String key) { 
        if(cache.containsKey(key)){ 
            return cache.get(key); 
        } 

        String foundProp = null; 
        try { 
            foundProp = beanFactory.resolveEmbeddedValue("${" + key.trim() + "}");        
            cache.put(key,foundProp);        
        } catch (IllegalArgumentException ex) { 
           // ok - property was not found 
        } 

        return foundProp; 
    } 
}