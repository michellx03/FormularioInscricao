package Uteis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

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

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "mail.classisvale.com.br");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");

		session = Session.getInstance(props,  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("", "");
			}
		});

	}

	public String enviarEmail(DadosUsuarioEmail dados) {

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("notificacao@classisvale.com.br"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dados.getEmail()));
			message.setSubject(dados.getSubject());
			message.setText(dados.getMessage());

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return "";

	}

	public String enviarEmailParaMinMesmo(DadosUsuarioEmail dados) {

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("notificacao@classisvale.com.br"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("classivalenegocios@gmail.com"));
			message.setSubject(dados.getSubject());
			message.setText(dados.getMessage());

			Transport.send(message);

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