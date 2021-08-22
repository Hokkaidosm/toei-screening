package net.hokkaidosm.precure.toei_screening.mail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import net.hokkaidosm.precure.toei_screening.config.MailProperties;

@Service
public class SendMailServiceImpl implements SendMailService {
	@Autowired
	private MailProperties mailProperties;

	@Autowired
	private MailSender mailSender;

	@Override
	public void sendMail(String title, String body) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		String today = LocalDate.now().format(formatter);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(mailProperties.getMailTo());
		msg.setFrom(mailProperties.getMailFrom());
		msg.setSubject(String.format("%s 劇場情報 %s", title, today));
		msg.setText(body);
		mailSender.send(msg);
	}
}
