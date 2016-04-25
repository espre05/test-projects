package ab.lims.actions.test;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.bind.JAXBElement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ab.lims.service.LimsService;
import ab.lims.spring.util.CleanupHelper;
import ab.lims.util.LimsUtil;
import ab.xml.MarshallService;
import ab.xml.gen.v1.jaxb.SequentaOrdersType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:ab/lims/spring/cfg/_common-context.xml","classpath:ab/lims/spring/cfg/xml-marshal-test.xml"})
public class TestReceiveOrder {
  private static Logger log = LoggerFactory.getLogger(TestReceiveOrder.class);
  @Autowired
  private LimsService limsService;

	@Autowired
	MarshallService marshallService;

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
  //update order
  //orid 3051898
  //racktube id 302547
  
  // new order
  //Ractube id 302560
  //SeqPlate: ImportSample RackTube Id(s) created for racktube is : 302560 
  
	List<String> undoSqlList = new ArrayList<String>(20);
  //@Test
  public void testDrive(){
	  s01_ReceiveOrdersNew();
	  s02_acceptSample();
	  s03_dnaExtraction_FFPE();
  }
  @Test
  public void s01_ReceiveOrdersNew() { // No exceptions expected in
      log.info("calling limsService Mocking AS/MQ" ); //ab/lims/test/msg/res/analyseSample/TC001_res_analyseSample_S14111802648_CALIBRATION_statPass_calibTrue_clonalTrue.xml
      //String msgBody = LimsUtil.getFileContent("/ab/lims/test/msg/lpl/_template_SequentaOrders_new.xml");
      //Path rakFile = LimsUtil.getResourceAsPath("/ab/lims/env/JUNIT/immunolims_lvtest.rak");
		{// Receive Order - 1sample
			  JAXBElement<SequentaOrdersType> je = marshallService.getSeqOrderTemplate();
			  String xmlSeqOrderString = marshallService.getAsString(je);
			Map<String, String> inReceiveOrderProps = new HashMap<String, String>(1);
			inReceiveOrderProps.put("SequentaOrders", xmlSeqOrderString);
			Map<String, String> outReceiveOrderProps = limsService.processAction("SQLPL_ReceiveOrder",inReceiveOrderProps);
			String successReceiveOrder = outReceiveOrderProps.get("STATUS");
			Assert.assertEquals("Sucess in executing SQLPL_ReceiveOrder2", "SUCCESS", successReceiveOrder);
			String undoReceiveOrder = outReceiveOrderProps.get("UNDO_SQL");
			//CleanupHelper.cleanup(jdbcTemplate, undoSql);
			undoSqlList.addAll(CleanupHelper.getUndoSqlList(undoReceiveOrder));
			Assert.assertNotEquals("Undosql should not have empty '' SQLPL_ReceiveOrder2", null, undoReceiveOrder);
			// http://localhost:8080/immunolims/rc?command=page&page=SQ_InitialSampleList
			// |SEQSAMPLE=SS_150919_00037|REQUEST=50001|SAMPLE=S15091905427|RACKTUBE=50001|LOCCONTENT=Loc-0915-0278842
		}      

    	  
      //http://localhost:8080/immunolims/rc?command=page&page=SQ_CellTubeList

      
      
  }
  
  //@Test
  public void s02_acceptSample(){
	  {//Accept sample
		  String acceptSampleSql = "update s_sample s set s.samplestatus= 'Accepted' where s.requestid =  '50001'";
		  jdbcTemplate.execute(acceptSampleSql);
	      Map<String,String> inAcceptSampleProps =  new HashMap<String, String>(1);
	      String sampleRacktubeid = "50001";
	      inAcceptSampleProps.put("sourcebarcode", sampleRacktubeid);
	      inAcceptSampleProps.put("stepname", "Aliquot Tube");
	      Map<String,String> outAcceptSampleProps = limsService.processAction("SQ_ChangeConState", inAcceptSampleProps);
	      String successAcceptSample= outAcceptSampleProps.get("STATUS");
	      Assert.assertEquals("Sucess SQ_ChangeConState", "SUCCESS", successAcceptSample);
	      log.info(outAcceptSampleProps.toString());
	      //http://localhost:8080/immunolims/rc?command=page&page=SQ_FFPEList
	  }
  }
  public void s03_dnaExtraction_FFPE(){
	  //queries used  - can't find action that does that. Will check if javascript calls action.
	  String racktubForDnaExt = "Insert into S_RACKTUBE "
	  		+ "(S_RACKTUBEID,RACKTUBEDESC,HOLDERZONEID"//
	  		+ "  ,AUDITSEQUENCE,AUDITDEFERFLAG,TRACELOGID"//
	  		+ "  ,CREATEBY,CREATETOOL,MODBY" //
	  		+ "  ,MODTOOL,TEMPLATEFLAG,ACTIVEFLAG"//
	  		+ "  ,U_WORKFLOWSTATEID,SECURITYUSER,SECURITYDEPARTMENT)"//
	  		+ "values ('S15092005431_DNA','Aliquot Tube',null" //
	  		+ "  ,3,'N',null" //
	  		+ "  ,'admin','AddSDI','admin'" //
	  		+ "  ,'EditSDI','N','Y'" //
	  		+ "  ,'62','admin','CLIA')";

	  
	  String transferContents = "Insert into S_LOCCONTENT (S_LOCCONTENTID,LOCCONTENTDESC    ,LOCSDCID  ,LOCKEYID1         ,CONTSDCID ,CONTKEYID1,AMOUNT,AMOUNTUNITS,TRANSFERAMOUNT,TRANSFERUNITS,LOCSTATUS,CONTENTSTATUS,CREATEBY,CREATETOOL,MODBY  ,MODTOOL,TEMPLATEFLAG,ACTIVEFLAG) "
	  		                               + "values ('Loc-0915-0278848','Transfer Contents','RackTube','S15092005431_DNA','RackTube','50001'   ,null  ,null       ,5             ,'ml'         ,null     ,'Plasma Tube','admin'  ,'AddSDI' ,'admin','AddSDI','N','Y')";

	  String sampleContents = "Insert into S_LOCCONTENT (S_LOCCONTENTID,LOCCONTENTDESC  ,LOCSDCID  ,LOCKEYID1         ,CONTSDCID,CONTKEYID1   ,AMOUNT,AMOUNTUNITS,TRANSFERAMOUNT,TRANSFERUNITS,LOCSTATUS,CONTENTSTATUS,CREATEBY,CREATETOOL,MODBY  ,MODTOOL,TEMPLATEFLAG,ACTIVEFLAG) "
	  		                              +"values ('Loc-0915-0278847','Sample Contents','RackTube','S15092005431_DNA','Sample','S15092005431',null  ,'ml'       ,null          ,null         ,null     ,null         ,'admin'  ,'AddSDI'  ,'admin','AddSDI','N','Y')";

		Map<String,String> plasmaTubeProps = new HashMap<String,String>();
	  	
	  	limsService.processAction("SQ_AliquotPlasmaVol", plasmaTubeProps);
		//SeqUtil.addUndoSqlToProp(globalProps, plasmaTubeProps.getProperty("UNDO_SQL"));
	  	
	  	Map<String,String> cellTubeProps = new HashMap<String,String>();
	  	
		limsService.processAction("SQ_AliquotPlasmaVol", cellTubeProps);
		//SeqUtil.addUndoSqlToProp(globalProps, cellTubeProps.getProperty("UNDO_SQL"));

  }

  
  
  
//  @Test
  public void test_ReceiveOrdersUpdate() { // No exceptions expected in
      log.info("calling limsService Mocking AS/MQ" ); //ab/lims/test/msg/res/analyseSample/TC001_res_analyseSample_S14111802648_CALIBRATION_statPass_calibTrue_clonalTrue.xml
      String msgBody = LimsUtil.getFileContent("/ab/lims/test/msg/lpl/SequentaOrders.update.xml");

      Map<String,String> map =  new HashMap<String, String>(1);
      map.put("SequentaOrders", msgBody);
      Map<String,String> serverProps = limsService.processAction("SQLPL_ReceiveOrder", map);
      String successVal = serverProps.get("STATUS");
      Assert.assertEquals("Sucess in executing SQLPL_ReceiveOrder2", "SUCCESS", successVal);
  }

  
}


