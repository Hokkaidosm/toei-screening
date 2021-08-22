package net.hokkaidosm.precure.toei_screening;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {

	private static String titleCd = "02846";

	/**
	 * 引数1：作品ID 引数2：対象都道府県コード（2桁）カンマ区切り
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Main.class).web(WebApplicationType.NONE)
				.run(args);
		MainService mainService = context.getBean(MainService.class);
		List<String> prefCodeList = null;

		if (args.length >= 1) {
			titleCd = args[0];
		}
		if (args.length >= 2) {
			String prefCodeStr = args[1];
			String[] prefCodes = prefCodeStr.split(",");
			prefCodeList = Arrays.asList(prefCodes);
		}
		mainService.run(titleCd, prefCodeList);
	}

}
