package ve.org.bcv.mail;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import ve.org.bcv.servicesImpl.MandarMailType1;
@MandarMailType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MandarMailImpl implements MandarMail {

	public void send(String from,String to,String asunto, String messageBody,String filename,String smtpUser,String smtpPassword)	throws Exception{
		Properties props = new Properties();
		 
		
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input  =  this.getClass().getResourceAsStream("/config.properties");

			// load a properties file
			prop.load(input);
			
			/**
			 *      mail.smtp.host=mail.intra.bcv
					ttls=true
					mail.smtp.port=25
					mail.smtp.user=ProveedoresGSI
					mail.user=
					mail.passwd=proveedoresgsi

			 * */

			// get the property value and print it out
			props.setProperty("mail.smtp.host", prop.getProperty("mail.smtp.host"));
			props.put("mail.smtp.starttls.enable", true); // added this line
			boolean isTtls = new Boolean(prop.getProperty("mail.smtp.ttls"));
			if (isTtls) {
				props.put("mail.smtp.starttls.enable", true); // added this line
			}

			props.setProperty("mail.smtp.port", prop.getProperty("mail.smtp.port"));
			props.setProperty("mail.smtp.user",  smtpUser);
			props.setProperty("mail.smtp.auth", "true");
			// Preparamos la sesion
			Session session = Session.getDefaultInstance(props, null);

			// Construimos el mensaje
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(asunto);
//
//			StringBuilder messageBodyWithUrl = new StringBuilder(messageBody.toString());
//			message.setText(messageBodyWithUrl.toString());
			
			
			
			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(messageBody);

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			FileDataSource fds = new FileDataSource(filename);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			// add the Multipart to the message
			message.setContent(mp);
			
			
			
			// Lo enviamos.
			Transport t = session.getTransport("smtp");
			t.connect(smtpUser, smtpPassword);
			t.sendMessage(message, message.getAllRecipients());

			t.close();
			
		} catch (IOException ex) {
			throw new Exception();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
 

			

			

			
 



		 
	}

}
