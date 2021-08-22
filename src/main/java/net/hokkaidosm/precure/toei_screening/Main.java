package net.hokkaidosm.precure.toei_screening;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {

	private static String titleCd = "02846";

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Main.class).web(WebApplicationType.NONE)
				.run(args);
		MainService mainService = context.getBean(MainService.class);

		if (args.length >= 1) {
			titleCd = args[0];
		}
		mainService.run(titleCd);
	}

}
