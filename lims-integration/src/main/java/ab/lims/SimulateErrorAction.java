package ab.lims;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import sapphire.SapphireException;
import sapphire.xml.PropertyList;
/**
 * @author pnatar
 * 
 *For simulating error. Also as a sample/demo class showing user of AbstractBaseAction. 
 */
@Component
public class SimulateErrorAction extends AbstractBaseAction{
	private static Logger log = LoggerFactory.getLogger(SimulateErrorAction.class);

	@Override
	public void doCustomAction(PropertyList properties) {
		try{
			myErrorMethod();
		}catch(Exception e){
		log.info("SimulateErrorAction : test");
		throw new AbException("Simulating error",e, "No action required, just simulating" );
		}
	}
	
	private void myErrorMethod() throws SapphireException{
		Exception ex = new NullPointerException("Embedd a null");
		throw new SapphireException("Hey catch this precious Sapphire", ex);
	}

}
