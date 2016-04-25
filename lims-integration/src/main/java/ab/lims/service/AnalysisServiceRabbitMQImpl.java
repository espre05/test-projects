package ab.lims.service;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.io.Files;

import ab.lims.AbException;
import ab.lims.util.LimsUtil;

/**
 * @author pnatar
 *
 * Note for future change: 
 * Once we find a solution to get spring props using custom SpringApplication context we have to make the following change.
 * 
 * - If we want to use automatic mq callback (LIMS 2min polling constraint) - looks like we have to call action through webservice i.e no more use CheckRabbit extends BaseAction. 
 * - This class would be the ideal choice for reimplementing CheckRabbit.
 */
@Component
public class AnalysisServiceRabbitMQImpl implements AnalysisService {
	private static Logger log = LoggerFactory.getLogger(AnalysisServiceRabbitMQImpl.class);
	@Value("${mq.host}")
	private String mqHostName ;
	@Value("${mq.port}")
	private int mqPort ;
	@Value("${mq.uid}")
	private String mqUid ;
	@Value("${mq.pwd}")
	private String mqPwd ; // not recommended
    @Value("${mq.msgLogBaseDir.path}")
    private String mqMsgLogDir ; 

	
	@Value("${queue.uri.create_run_req}")
	private String queueUriCreateRunReq ;	
	@Value("${queue.uri.create_run_res}")
	private String queueUriCreateRunRes ;	
	
	@Value("${queue.uri.analyse_run_req}")
	private String queueUriAnalyseRunReq ;	
	@Value("${queue.uri.analyse_run_res}")
	private String queueUriAnalyseRunRes ;	
	
	@Value("${queue.uri.analyse_sequence_req}")
	private String queueUriAnalyseSequenceReq;	
	@Value("${queue.uri.analyse_sequence_res}")
	private String queueUriAnalyseSequenceRes;	

	@Value("${queue.uri.analyse_sample_req}")
	private String queueUriAnalyseSampleReq;	
	@Value("${queue.uri.analyse_sample_res}")
	private String queueUriAnalyseSampleRes;	
	
	//Response queues are not using Camel (using CheckRabbit), so no URI, direct Queue names
	//queue names used for logging and other purposes
    @Value("${queue.name.createRunReq}")
    private String queueNameCreateRunReq;   
    @Value("${queue.name.analyseRunReq}")
    private String queueNameAnalyseRunReq;  
    @Value("${queue.name.analyseSequenceReq}")
    private String queueNameAnalyseSequenceReq; 
    @Value("${queue.name.analyseSampleReq}")
    private String queueNameAnalyseSampleReq;   

    @Value("${queue.name.createRunRes}")
	private String queueNameCreateRunRes;	
	@Value("${queue.name.analyseRunRes}")
	private String queueNameAnalyseRunRes;	
	@Value("${queue.name.analyseSequenceRes}")
	private String queueNameAnalyseSequenceRes;	
	@Value("${queue.name.analyseSampleRes}")
	private String queueNameAnalyseSampleRes;	

	private Path msglogBaseDirPath = null;
	@Autowired(required=false)
	private ProducerTemplate producerTemplate;

	@Autowired(required=false)
	private ConsumerTemplate consumerTemplate;
	
	public AnalysisServiceRabbitMQImpl() {
	  
	}
	
	/* send message to MQ
	 * @see ab.lims.service.AnalysisService#sendMsg(ab.lims.service.AnalysisMessageType, java.lang.String)
	 */
	@Override
	public void sendMsg(AnalysisMessageType msgType, String message){
	    msglogBaseDirPath= Paths.get(mqMsgLogDir);
		String queueTo = null;
		queueTo = getReqQueueUri(msgType);
		log.info(String.format("About to send message to q=%s msg=%s:" ,queueTo,message));
		this.logMsg(msgType, message);
		try {
		producerTemplate.sendBody(queueTo,ExchangePattern.InOnly, message.getBytes());
	
		}catch(Exception e) {
			String errMsg = String.format("Error MQ send %s/%s", mqHostName+':'+mqPort ,msgType);
			log.error(errMsg, e);
			throw new AbException(errMsg, e);
		}
	}
	/**
	 *Returns Queue Name for a message type. 
	 *This is exposed as public only for testing if msg sends to right Q. Should not be public
	 * @param msgType
	 * @return - returns the que
	 */
	public String getReqQueueName(AnalysisMessageType msgType){//@formatter:off
      String queueTo = null;
	  switch(msgType){
    	case CREATE_RUN : queueTo = queueNameCreateRunReq; break; 
    	case ANALYSE_RUN : queueTo = queueNameAnalyseRunReq; break;
    	case ANALYSE_SAMPLE : queueTo = queueNameAnalyseSampleReq; break;
    	case ANALYSE_SEQUENCE : queueTo = queueNameAnalyseSequenceReq; break;
    	default : throw new AbException("IllegalArguement: Unknown type of message msg=" + msgType + " QueueName can not be determined");
	  }//@formatter:off
		return queueTo;
	}
	
	   //get the queuename - used for logging
    @Override
    public String getReqQueueUri(AnalysisMessageType msgType){//@formatter:off
      String queueTo = null;
      switch(msgType){
        case CREATE_RUN : queueTo = queueUriCreateRunReq; break; 
        case ANALYSE_RUN : queueTo = queueUriAnalyseRunReq; break;
        case ANALYSE_SAMPLE : queueTo = queueUriAnalyseSampleReq; break;
        case ANALYSE_SEQUENCE : queueTo = queueUriAnalyseSequenceReq; break;
        default : throw new AbException("IllegalArguement: Unknown type of message msg=" + msgType + " QueueName can not be determined");
      }//@formatter:off
        return queueTo;
    }


public void logMsg(AnalysisMessageType msgType, String msgData){
  Path queueFilePath  =msglogBaseDirPath.resolve(getReqQueueName(msgType))// File is written locally in /lims_dir\JUNIT\LV701\mq_msg_log\DEV-WIN-LV701\JUNIT.LV701.CREATERUN_REQ
      .resolve( msgType.name() + '_' +LimsUtil.getTimestampString(new Date()) + ".xml");
  
  File mqFile =   queueFilePath.toFile();
  try{
    Files.createParentDirs(mqFile);
    Files.touch(mqFile);
    Files.write(msgData, mqFile, Charset.defaultCharset());
    log.info("Writing "+msgType+" msg to file" + mqFile.toString() );
  }catch(Exception e){
    log.error("\n\n########################################## \n\t Error Writing msg to file" + mqFile.toString() );
    throw new AbException( "Error Writing msg to file" + mqFile.toString(), e);
  }

}
	//used only for debug purposes
	@Override
	public String getMqUri(){
		return mqHostName +':'+mqPort;
	}

	@Override
	public String toString() {
		return String
				.format("AnalysisServiceRabbitMQImpl [queueUriCreateRunReq=%s, queueUriAnalyseRunReq=%s, queueUriAnalyseSampleReq=%s, producerTemplate=%s]",
						queueUriCreateRunReq, queueUriAnalyseRunReq,
						queueUriAnalyseSampleReq, producerTemplate);
	}
}
