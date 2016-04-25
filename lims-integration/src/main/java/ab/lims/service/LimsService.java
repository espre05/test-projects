package ab.lims.service;

import java.util.Map;

import sapphire.action.BaseAction;

/**
 * @author pnatar
 * 
 *         For communicating with lims service
 */
public interface LimsService {
	// for accepting specific mq response
	public void acceptAnalyseSampleResponse(String msgBody);

	// for accepting generic MQ Response
	public void acceptMQResponse(LimsAction limsAction, String msgBody);

	// Execute a generic Lims action
	public Map<String, String> processAction(String actionName, Map<String, String> map);

	public void execActionBlock(String actionName, final Map<String, String> map);

	public void execActionBlock(Class<? extends BaseAction> actionClass, final Map<String, String> map);

	// util for test - only for LV7
	public String getConnectionId();

	public void releaseConnectionId(String connectionId);

}
