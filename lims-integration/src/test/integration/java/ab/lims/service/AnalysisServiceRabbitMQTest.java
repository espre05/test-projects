package ab.lims.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ab.lims.spring.util.SpringPropUtil;

@RunWith(SpringJUnit4ClassRunner.class)
//@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration("classpath:ab/lims/spring/cfg/app.xml")
public class AnalysisServiceRabbitMQTest {
  private static Logger log = LoggerFactory.getLogger(AnalysisServiceRabbitMQTest.class);
  @Autowired
  private AnalysisService analysisService;

  private String createRunMsg = "<run id='1234' analysisType='CREATE_RUN' > blablabla </run>";
  private String analyseRunMsg = "<run id='1234' analysisType='ANALYSE_RUN' > blablabla </run>";

  @Test
  public void sendCreateRunToMQ() { // No exceptions expected in
    for (int i = 1; i <= 1; i++) {
      log.info("calling analysisService #" + i);
      log.info("\nsample mq.host" + SpringPropUtil.getProperty("mq.host") + "\n");
      log.info("\nsample lims.app.rootDir.path=" + SpringPropUtil.getProperty("lims.app.rootDir.path") + "\n");
      log.info("\nsample lims.app.baseDir.path=" + SpringPropUtil.getProperty("lims.app.baseDir.path") + "\n");
      // just send without errors
      analysisService.sendMsg(AnalysisMessageType.CREATE_RUN, createRunMsg);
      analysisService.sendMsg(AnalysisMessageType.ANALYSE_RUN, analyseRunMsg);
      analysisService.sendMsg(AnalysisMessageType.ANALYSE_SEQUENCE, analyseRunMsg);
      analysisService.sendMsg(AnalysisMessageType.ANALYSE_SAMPLE, analyseRunMsg);
    }
  }
 
}
