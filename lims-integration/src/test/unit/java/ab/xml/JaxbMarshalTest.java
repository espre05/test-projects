package ab.xml;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ab.lims.util.LimsUtil;
import ab.xml.gen.v1.jaxb.ClinicalTestType;
import ab.xml.gen.v1.jaxb.ExperimentType;
import ab.xml.gen.v1.jaxb.ExperimentsType;
import ab.xml.gen.v1.jaxb.LaneType;
import ab.xml.gen.v1.jaxb.ObjectFactory;
import ab.xml.gen.v1.jaxb.OrderType;
import ab.xml.gen.v1.jaxb.PatientType;
import ab.xml.gen.v1.jaxb.RequestType;
import ab.xml.gen.v1.jaxb.RunType;
import ab.xml.gen.v1.jaxb.RunsType;
import ab.xml.gen.v1.jaxb.SequentaOrdersType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ab/lims/spring/cfg/xml-marshal-test.xml")
public class JaxbMarshalTest {
	@Autowired
	MarshallService marshallService;

	@Test
	public void testUnmarshalCreatRunXml() {
		String fileInClassPath = "/ab/lims/test/msg/createRun/req/test.xml";
		Path xmlFilePath = LimsUtil.getResourceAsPath(fileInClassPath);
		JAXBElement<RequestType> je = marshallService.unmarshall(RequestType.class, xmlFilePath);
		marshallService.write(System.out, je);
	}

	@Test
	public void testMarshalCreateRunObjTree() {
		ObjectFactory of = new ObjectFactory();
		RequestType reqT;
		{
			reqT = of.createRequestType();

			RunsType runsT = of.createRunsType();
			reqT.setRuns(runsT);
			RunType runT = of.createRunType();
			runsT.getRun().add(runT);
			// Run Config starts
			runT.setId("MY_RUN_ID");
			LaneType laneT = of.createLaneType();
			runT.setLane(laneT);
			runT.setMsgType("CREATE_RUN");
			// Lane config starts
			ExperimentsType exptsT = of.createExperimentsType();
			ExperimentType exptT = of.createExperimentType();
			exptsT.getExperiment().add(exptT);
			laneT.setExperiments(exptsT);
		}
		JAXBElement<RequestType> je = of.createRequest(reqT);
		marshallService.write(System.out, je);
	}

	@Test
	public void testUnmarshalSequentaOrderXml() throws Exception {
		
		Date nowDate = new Date();
		String nowDateString = LimsUtil.getUSDateAsString(nowDate);

		JAXBElement<SequentaOrdersType> je = marshallService.getSeqOrderTemplate();
		String xmlSeqOrderString = marshallService.getAsString(je);
		Assert.assertTrue("Marshalled/Unmarshaled xml should contain todays date", xmlSeqOrderString.contains(nowDateString));
	}


	@Test
	public void testMarshalOrderObjTree() {
		ObjectFactory of = new ObjectFactory();
		SequentaOrdersType seqOrderT;
		{
			seqOrderT = of.createSequentaOrdersType();
			List<OrderType> orders = seqOrderT.getOrder();
			// order
			OrderType order = of.createOrderType();
			orders.add(order);
			{
				// order -mandatory
				order.setOrderID(new BigInteger("12342342342343"));
				order.setOrderType("test");
				order.setOrderStatus("test");
				
				BigInteger md1 = new BigInteger("100");
				order.setOrderMD1(md1);
				order.setOrderMD1Name("Md1Name");
				order.setRequisitionNumber(new BigInteger("1457"));
				order.setPlacerMedicalRecordNumber("1234");
				order.setTubeType("123");
				order.setTissueType("abc");
				order.setSpecimenType("specmen type");
				order.setNPI(new BigInteger("2222222"));
				order.setOrderSource("ord src");
				order.setReceivedDate("MM/dd/yyyy");
				order.setCollectionDate("MM/dd/yyyy");
				order.setOrderDate("MM/dd/yyyy");
				order.setOrderTime("test");
				order.setClinicalInformation("clin info");
				order.setOrderPriority("none");
		
				// optional

				// children
				// order - patient
				// order - clinical test
				// order - holds

				// patient
				PatientType pt = of.createPatientType();
				order.setPatient(pt);
				{// patient
					pt.setName("Patient Name");
					pt.setDOB("2014-06-24");
					short patientindex = 123;
					pt.setPatientIndex(patientindex);
					pt.setPatientIDStatus("id pass");
				}// patient ends

				// clinicalTest
				ClinicalTestType ct = of.createClinicalTestType();
				order.setClinicalTest(ct);
				{//clinicalTest
					ct.setClinicalTestCode("123");
					ct.setTestCollectionDate("mm/dd/yyyy");
					ct.setDiseaseType("disese type");
					ct.setReceptors("receptors list");
					ct.setCompartment("compartment ");
				}//ends clinicalTest

			}
		}
		JAXBElement<SequentaOrdersType> je = of.createSequentaOrders(seqOrderT);
		marshallService.write(System.out, je);
	}

}
