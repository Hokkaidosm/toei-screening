package net.hokkaidosm.precure.toei_screening.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendMailServiceTest {
	@Autowired
	private SendMailService service;

	@Test
	public void test() {
		String title = "testTitle";
		String body = "testBody";
		service.sendMail(title, body);
	}
}
