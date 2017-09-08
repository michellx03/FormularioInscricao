package Uteis;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarEmail {

	private Session session;

	public EnviarEmail() {

		final String username = "";
		final String password = "";
		// OBS: tem que liberar acesso na conta do gmail, link : https://www.google.com/settings/security/lesssecureapps
		
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

	}

	public String enviarEmail(DadosUsuarioEmail dados) {

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("01fernando.ti@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dados.getEmail()));
			message.setSubject(dados.getSubject());
			message.setText(dados.getMessage());

			Transport.send(message);
			confirmacaoEnvio(dados);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return "";

	}

	public void confirmacaoEnvio(DadosUsuarioEmail dados) {

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("01fernando.ti@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dados.getEmail()));
			message.setSubject("Contato via web site");
			message.setText(
					"Obrigado por nos contactar " + dados.getName() + " Entraremos em contato assim que possivel");

			Transport.send(message);

		} catch (Exception ex) {

		}

	}

}

