package net.hokkaidosm.precure.toei_screening.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("mail")
public class MailProperties {
	/** メール送信元 */
	private String mailFrom;

	/** メール送信先 */
	private String mailTo;
}
