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
@ContextConfiguration("classpath:ab/lims/spring/cfg/app.xml")
public class TestSendToLPL {
  private static Logger log = LoggerFactory.getLogger(TestSendToLPL.class);
  @Autowired
  private LimsService limsService;
  

  @Test
  public void send_a_pdf_to_lpl() { // No exceptions expected in
      String testReportFilePathOnServer = "C:/lims_dir/test_files_on_server/lpl/SampleReport_201040.pdf";

      Map<String,String> map =  new HashMap<String, String>(1);
      map.put("keyid1", "S14051401532");
      map.put("uploadedfiles", testReportFilePathOnServer); // this file should be on the server
      map.put("SendEmail", "false");
      
      Map<String,String> serverProps = limsService.processAction("SQ_SendReportToLPL", map);
      String successVal = serverProps.get("STATUS");
      Assert.assertEquals("Sucess in executing SQLPL_ReceiveOrder2", "SUCCESS", successVal);
  }
  
  
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  @Test
  public void send_a_NONpdf_to_lpl() { // No exceptions expected in
	  expectedEx.expect(ab.lims.AbException.class);
	    expectedEx.expectMessage("Server-ERROR=SendToLPL Failed:  Only .pdf reports can be sent to lpl");
	  
      String testReportFilePathOnServer = "C:/lims_dir/test_files_on_server/lpl/SampleReport_201040.txt";

      Map<String,String> map =  new HashMap<String, String>(1);
      map.put("keyid1", "S14051401532");
      map.put("uploadedfiles", testReportFilePathOnServer); // this file should be on the server
      map.put("SendEmail", "false");
      
      Map<String,String> serverProps = limsService.processAction("SQ_SendReportToLPL", map);
//      String successVal = serverProps.get("STATUS");
//      Assert.assertEquals("Send Nonpdf to lpl", "FAIL", successVal);
  }

}
