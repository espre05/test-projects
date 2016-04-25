package ab.lims.service;

import java.text.MessageFormat;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ab.lims.AbException;

/**
 * @author pnatar
 *
 *         For mailing critical info, errors
 */
@Service("mailService")
public class MailService
{
	private static Logger log = LoggerFactory.getLogger(MailService.class);

	@Autowired
	private JavaMailSender mailSender;

	@Value("${lims.admin.email.to}")
	private String adminEmailTo;
	@Value("${lims.admin.email.subject.prefix}")
	private String subjectPrefix;
	private final String adminEmailFrom = "noreploy@adaptivebiotech.com";

	/**
	 * This method will send compose and send the message
	 * */
	@Async
	public void sendMail(String to, String subject, String body)
	{
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(adminEmailFrom);
			helper.setTo(to);
			helper.setSubject(subjectPrefix + subject);
			helper.setText(body, true);
			mailSender.send(message);
		} catch (Exception e) {
			// log and swallow
			log.error("Error while sending Email", e);
		}
	}

	private final String errorHtmlTemplate = "<html><table border=1 bgcolor='white' style='background-color: white;'>  <tr style='background-color: #FF8533;'>     <th>Error/Info: </th>	<td > {0}</td>  </tr>  <tr>    <th align='left' valign='top' style='background-color: #99FF99;'>Hint: </th>	<td > <font color='grey'> {1} </font></td>  </tr>  <tr>    <th align=left valign='top' style='background-color: #99FF99;'>Details:</th>	<td> <font color='grey'><pre> {2} </pre></font></td>  </tr></table></html>";

	@Async
	public void alert(String message, AbException ex) {
		adminEmailTo = "pnatarajan@adaptivebiotech.com";
		String subject = message;
		String hint = ex.getHint();
		String stackStrace = ExceptionUtils.getStackTrace(ex);
		String msgBody = MessageFormat.format(errorHtmlTemplate, subject, hint, stackStrace);
		sendMail(adminEmailTo, subject, msgBody);
	}

	@Async
	public void alert(AbException ex) {
		alert(ex.getMessage(), ex);
	}

	private final String infoHtmlTemplate = "<html><table border=1 bgcolor='white' style='background-color: white;'>  <tr style='background-color: #FF8533;'>     <th>Info: </th>	<td > {0}</td>  </tr>  <tr>    <th align='left' valign='top' style='background-color: #99FF99;'>Reason: </th>	<td > <font color='grey'> {1} </font></td>  </tr>  <tr>    <th align=left valign='top' style='background-color: #99FF99;'>Details:</th>	<td> <font color='grey'><pre> {2} </pre></font></td>  </tr></table></html>";

	@Async
	public void alert(String message, String reason) {
		String subject = message;
		String msgBody = MessageFormat.format(infoHtmlTemplate, subject, reason, "No Details");
		sendMail(adminEmailTo, subject, msgBody);
	}

	public String getAdminEmail() {
		return adminEmailTo;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmailTo = adminEmail;
	}

}