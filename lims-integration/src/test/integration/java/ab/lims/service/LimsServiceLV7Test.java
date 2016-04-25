package ab.lims.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ab.lims.util.LimsUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ab/lims/spring/cfg/app.xml")
public class LimsServiceLV7Test {
  private static Logger log = LoggerFactory.getLogger(LimsServiceLV7Test.class);
  @Autowired
  private LimsService limsService;
  
  @Test
  public void testMultiMRD_AnalyseSampleRes() { // No exceptions expected in
      log.info("calling limsService Mocking AS/MQ" ); //ab/lims/test/msg/res/analyseSample/TC001_res_analyseSample_S14111802648_CALIBRATION_statPass_calibTrue_clonalTrue.xml
      String msgBody = LimsUtil.getFileContent("/ab/lims/test/msg/analyseSample/res/TC001_res_analyseSample_S14060501638_CALIBRATION_statPass_calibTrue_clonalTrue.xml");
      
      //Path rakFile = LimsUtil.getResourceAsPath("/ab/lims/env/JUNIT/immunolims_lvtest.rak");
      // just send without errors - the system should be up.
      //limsService.acceptAnalyseSampleResponse(msgBody);
      
      
      Map<String,String> map =  new HashMap<String, String>(1);
      map.put("ExperimentList", msgBody);
      Map<String,String> serverProps = limsService.processAction("SQ_UpdateSampStatus", map);
      String successVal = serverProps.get("STATUS");
      Assert.assertEquals("Sucess in executing SQ_UpdateSampStatus", "SUCCESS", successVal);
  }
  
 
  //@Test
  public void mockCallActionClass() { // No exceptions expected in
      log.info("calling limsService Mocking AS/MQ" ); //ab/lims/test/msg/res/analyseSample/TC001_res_analyseSample_S14111802648_CALIBRATION_statPass_calibTrue_clonalTrue.xml
      Map<String,String> map =  new HashMap<String, String>(2);
//      map.put("prop1", "prop1Val");
//      map.put("prop2", "prop2Val");
      
      Map<String,String> serverProps = limsService.processAction("SQ_DemoAction", map);
      String successVal = serverProps.get("STATUS");
      Assert.assertEquals("Sucess in executing SQ_DemoAction", "SUCCESS", successVal);
      log.debug(serverProps.toString());
  }  
  
}
