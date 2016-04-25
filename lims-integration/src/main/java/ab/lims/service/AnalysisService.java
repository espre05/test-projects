package ab.lims.service;

import org.springframework.stereotype.Component;

/**
 * @author pnatar
 * 
 * For communicating with analysis server
 */
@Component
public interface AnalysisService {

	public abstract void sendMsg(AnalysisMessageType msgType,  String message);

	String getMqUri(); //used for debug/errorlog purposes only.
	String getReqQueueUri(AnalysisMessageType msgType); // used for testing if msg routes to right Q.

//	String getMQHostName();
//
//	int getMqPort();
//
//	String getResQueueName(AnalysisMessageType msgType);
//
//	String getMQUid();
//
//	String getMQPwd();

	//public abstract String getMsg(AnalysisMessageType msgType, String message);

}