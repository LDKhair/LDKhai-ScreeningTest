package Helper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHelper {
	public static void sendMail(String email, String contentMail) {
		// đăng nhập vào email
		final String username = "khaildps37419@fpt.edu.vn";
		final String password = "stth dwqe bbpi pqoe";

		String subject = "no-reply";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message mess = new MimeMessage(session);
			mess.setFrom(new InternetAddress(username));
			mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

			mess.setSubject(subject);
			mess.setContent(contentMail, "text/html");
			mess.setReplyTo(mess.getFrom());
			Transport.send(mess);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
