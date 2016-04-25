package ab.lims.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// @BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration("classpath:ab/lims/spring/cfg/app.xml")
public class AnalysisServiceTest {

  @Autowired
  private AnalysisService analysisService;

  // @Autowired
  // protected CamelContext camelContext;

  // @EndpointInject(uri = "mock:${queue.uri.create_run_req}")
  // protected MockEndpoint createRunEndPoint;

  static final String qPrefix = "rabbitmq://DEV-WIN-LV701:5672/ex1?connectionFactory=#customConnectionFactory&autoDelete=false&autoAck=false&BridgeEndpoint=true&queue=";
  static final String expectedQCreateRun = qPrefix+"JUNIT.LV701.CREATERUN_REQ&routingKey=JUNIT.LV701.CREATERUN_REQ";
  static final String expectedQAnalyseRun = qPrefix+"JUNIT.LV701.ANALYSERUN_REQ&routingKey=JUNIT.LV701.ANALYSERUN_REQ";
  static final String expectedQAnalyseSequence = qPrefix+"JUNIT.LV701.ANALYSESEQUENCE_REQ&routingKey=JUNIT.LV701.ANALYSESEQUENCE_REQ";
  static final String expectedQAnalyseSample = qPrefix+"JUNIT.LV701.ANALYSESAMPLE_REQ&routingKey=JUNIT.LV701.ANALYSESAMPLE_REQ";

  @Test
  // @DirtiesContext
  public void messageRoutedToRightQueue() throws Exception {

    String qCreateRun = analysisService.getReqQueueUri(AnalysisMessageType.CREATE_RUN);
    Assert.assertEquals("CreateRun msg sending to CreateRunQ", qCreateRun,expectedQCreateRun);

    String qAnalyseRun = analysisService.getReqQueueUri(AnalysisMessageType.ANALYSE_RUN);
    Assert.assertEquals("AnalyseRun msg sending to AnalyseRunQ", qAnalyseRun,expectedQAnalyseRun);

    String qAnalyseSequence = analysisService.getReqQueueUri(AnalysisMessageType.ANALYSE_SEQUENCE);
    Assert.assertEquals("AnalyseSequence msg sending to AnalyseSequenceQ", qAnalyseSequence,expectedQAnalyseSequence);

    String qAnalyseSample = analysisService.getReqQueueUri(AnalysisMessageType.ANALYSE_SAMPLE);
    Assert.assertEquals("AnalyseSample msg sending to AnalyseSampleQ", qAnalyseSample,expectedQAnalyseSample);
  }

}
