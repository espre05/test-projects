package ab.lims.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ab.lims.AbException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ab/lims/spring/cfg/app.xml"})
public class MailServiceTest {
	private static Logger log = LoggerFactory.getLogger(MailServiceTest.class);

	@Autowired
	private MailService mailService;
	
	private CountDownLatch lock = new CountDownLatch(1);

	//@Test
//	public void testEmail() throws Exception {
//		// Send a composed mail
//		mailService.sendMail("pnatarajan@adaptivebiotech.com", "Test message", "Testing body");
//		lock.await(2000, TimeUnit.MILLISECONDS);
//		log.debug("Test");
//	}

	@Test
	public void testErrorEmail() throws Exception{

		try {
			myErrorMethod();
		} catch (AbException e) {
			mailService.alert(e);
			lock.await(2000, TimeUnit.MILLISECONDS);
			log.debug("Test");
		}
	}

	private void myErrorMethod() {
		try {
			// do something
			throw new NullPointerException("Junit Testing Non-existant Nullpointer");
		} catch (Exception e) {
			AbException ex = new AbException(e);
			ex.setHint("Nothing to do");
			throw ex;
		}

	}

}
