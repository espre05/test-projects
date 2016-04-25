package ab.lims.actions.test;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ab.lims.service.LimsService;
import ab.lims.util.LimsUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ab/lims/spring/cfg/_common-context.xml")
public class TestPropConfig {
  private static Logger log = LoggerFactory.getLogger(TestSendToLPL.class);
  @Autowired
  private LimsService limsService;
  

  @Test
  public void call_config_afterChange() { // No exceptions expected in
      String propKeyName = "wf.granularCellDep.calcReagentVolume.deadVolume.LYSIS_TROUGH";
      Map<String,String> map =  new HashMap<String, String>(1);
      map.put("propKey", propKeyName);
      map.put("refresh", "someFlag");
      Map<String,String> serverProps = limsService.processAction("SQT_TestPropsAction", map);
      String successVal = serverProps.get("STATUS");
      String propsVal = serverProps.get(propKeyName);
      Assert.assertEquals("Sucess in executing prop", "SUCCESS", successVal);
      Assert.assertEquals("3000", propsVal);
  }
  
}
