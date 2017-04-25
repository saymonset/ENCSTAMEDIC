package ve.org.bcv.mail;

import javax.ejb.Local;
@Local
public interface MandarMail {
	void send(String from,String to,String asunto, String messageBody,String filename,String smtpUser,String smtpPassword)	throws Exception;
}
