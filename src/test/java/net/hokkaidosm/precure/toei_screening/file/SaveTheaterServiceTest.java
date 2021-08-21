package net.hokkaidosm.precure.toei_screening.file;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.hokkaidosm.precure.toei_screening.model.Theater;

@SpringBootTest
public class SaveTheaterServiceTest {
	@Autowired
	private SaveTheaterService service;
	@Autowired
	private LoadPastTheaterService loadPastTheaterService;
	DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

	private String titleCd = "dummy";

	private List<Theater> theaters;
	ObjectMapper mapper;

	@BeforeEach
	private void beforeEach() throws Exception {
		//@formatter:off
	    String testJson = "[\n"
	    		+ "  {\n"
	    		+ "    \"title_cd\": \"02846\",\n"
	    		+ "    \"hp_area_cd\": \"003\",\n"
	    		+ "    \"hp_area_nm\": \"関東甲信越\",\n"
	    		+ "    \"theater_cd\": \"0503\",\n"
	    		+ "    \"theater_nm\": \"渋谷TOEI\",\n"
	    		+ "    \"prefectures_cd\": \"13\",\n"
	    		+ "    \"prefectures_nm\": \"東京都\",\n"
	    		+ "    \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
	    		+ "    \"hp_tel\": \"03-5467-5773\",\n"
	    		+ "    \"movie_ticket_flg\": true,\n"
	    		+ "    \"title_branch_nms\": [],\n"
	    		+ "    \"event_nm1\": null,\n"
	    		+ "    \"event_color1\": null,\n"
	    		+ "    \"event_nm2\": null,\n"
	    		+ "    \"event_color2\": null,\n"
	    		+ "    \"event_nm3\": null,\n"
	    		+ "    \"event_color3\": null,\n"
	    		+ "    \"event_nm4\": null,\n"
	    		+ "    \"event_color4\": null,\n"
	    		+ "    \"first_start_dt\": null,\n"
	    		+ "    \"last_end_dt\": null,\n"
	    		+ "    \"fax\": \"03-5467-5776\",\n"
	    		+ "    \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
	    		+ "    \"remarks\": [],\n"
	    		+ "    \"disp_order\": 1010\n"
	    		+ "  },\n"
	    		+ "  {\n"
	    		+ "    \"title_cd\": \"02846\",\n"
	    		+ "    \"hp_area_cd\": \"003\",\n"
	    		+ "    \"hp_area_nm\": \"関東甲信越\",\n"
	    		+ "    \"theater_cd\": \"4861\",\n"
	    		+ "    \"theater_nm\": \"新宿バルト9\",\n"
	    		+ "    \"prefectures_cd\": \"13\",\n"
	    		+ "    \"prefectures_nm\": \"東京都\",\n"
	    		+ "    \"theater_url\": \"https://tjoy.jp/shinjuku_wald9\",\n"
	    		+ "    \"hp_tel\": \"03-5369-4955\",\n"
	    		+ "    \"movie_ticket_flg\": true,\n"
	    		+ "    \"title_branch_nms\": [],\n"
	    		+ "    \"event_nm1\": null,\n"
	    		+ "    \"event_color1\": null,\n"
	    		+ "    \"event_nm2\": null,\n"
	    		+ "    \"event_color2\": null,\n"
	    		+ "    \"event_nm3\": null,\n"
	    		+ "    \"event_color3\": null,\n"
	    		+ "    \"event_nm4\": null,\n"
	    		+ "    \"event_color4\": null,\n"
	    		+ "    \"first_start_dt\": null,\n"
	    		+ "    \"last_end_dt\": null,\n"
	    		+ "    \"fax\": \"03-5369-4956\",\n"
	    		+ "    \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
	    		+ "    \"remarks\": [],\n"
	    		+ "    \"disp_order\": 1020\n"
	    		+ "  }\n"
	    		+ "]";
	    //@formatter:on
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		theaters = mapper.readValue(testJson, new TypeReference<List<Theater>>() {
		});
	}

	@AfterEach
	public void afterEach() throws IOException {
		Files.delete(Paths.get(String.format("%s.json", titleCd)).toAbsolutePath());
	}

	@Test
	public void test() throws Exception {
		service.saveTheaters(theaters, titleCd);

		List<Theater> actual = loadPastTheaterService.loadPastTheaters(titleCd);

		assertEquals(2, actual.size());

		assertEquals("02846", actual.get(0).getTitleCd());
		assertEquals("003", actual.get(0).getHpAreaCd());
		assertEquals("関東甲信越", actual.get(0).getHpAreaNm());
		assertEquals("0503", actual.get(0).getTheaterCd());
		assertEquals("渋谷TOEI", actual.get(0).getTheaterNm());
		assertEquals("13", actual.get(0).getPrefecturesCd());
		assertEquals("東京都", actual.get(0).getPrefecturesNm());
		assertEquals("https://toeitheaters.com/theaters/shibuya/", actual.get(0).getTheaterUrl());
		assertEquals("03-5467-5773", actual.get(0).getHpTel());
		assertEquals(true, actual.get(0).getMovieTicketFlg());
		assertEquals(null, actual.get(0).getFirstStartDt());
		assertEquals(null, actual.get(0).getLastEndDt());
		assertEquals(
				ZonedDateTime.parse("2021-10-22T15:00:00Z", formatter).withZoneSameInstant(ZoneId.of("Asia/Tokyo")),
				actual.get(0).getTitleReleaseStartDt().withZoneSameInstant(ZoneId.of("Asia/Tokyo")));

		assertEquals("02846", actual.get(1).getTitleCd());
		assertEquals("003", actual.get(1).getHpAreaCd());
		assertEquals("関東甲信越", actual.get(1).getHpAreaNm());
		assertEquals("4861", actual.get(1).getTheaterCd());
		assertEquals("新宿バルト9", actual.get(1).getTheaterNm());
		assertEquals("13", actual.get(1).getPrefecturesCd());
		assertEquals("東京都", actual.get(1).getPrefecturesNm());
		assertEquals("https://tjoy.jp/shinjuku_wald9", actual.get(1).getTheaterUrl());
		assertEquals("03-5369-4955", actual.get(1).getHpTel());
		assertEquals(true, actual.get(1).getMovieTicketFlg());
		assertEquals(null, actual.get(1).getFirstStartDt());
		assertEquals(null, actual.get(1).getLastEndDt());
		assertEquals(
				ZonedDateTime.parse("2021-10-22T15:00:00Z", formatter).withZoneSameInstant(ZoneId.of("Asia/Tokyo")),
				actual.get(1).getTitleReleaseStartDt().withZoneSameInstant(ZoneId.of("Asia/Tokyo")));
	}
}
