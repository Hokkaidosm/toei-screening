package net.hokkaidosm.precure.toei_screening;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.hokkaidosm.precure.toei_screening.model.Theater;

@SpringBootTest
public class MainServiceTest {
	@Autowired
	private MainService service;
	ObjectMapper mapper;

	@BeforeEach
	public void beforeEach() {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
	}

	@Test
	public void test_convertTheater1() throws Exception {
		//@formatter:off
		String theaterJson = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		//@formatter:on
		Theater theater = mapper.readValue(theaterJson, Theater.class);
		//@formatter:off
		String expected = "都道府県：東京都\n"
				+ "劇場名：渋谷TOEI https://toeitheaters.com/theaters/shibuya/\n"
				+ "TEL：03-5467-5773\n"
				+ "ムビチケ取り扱い：あり\n"
				+ "上映開始日：2021/10/24\n"
				+ "上映終了日：2021/10/25\n"
				+ "公開日：2021/10/23";
		//@formatter:off
		String actual = service.convertTheater(theater);
		assertEquals(expected, actual);
	}

	@Test
	public void test_convertTheater2() throws Exception {
		//@formatter:off
		String theaterJson = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": false,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		//@formatter:on
		Theater theater = mapper.readValue(theaterJson, Theater.class);
		//@formatter:off
		String expected = "都道府県：東京都\n"
				+ "劇場名：渋谷TOEI https://toeitheaters.com/theaters/shibuya/\n"
				+ "TEL：03-5467-5773\n"
				+ "ムビチケ取り扱い：なし";
		//@formatter:off
		String actual = service.convertTheater(theater);
		assertEquals(expected, actual);
	}

	@Test
	public void test_convertTheater3() throws Exception {
		//@formatter:off
		String theaterJson = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": null,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		//@formatter:on
		Theater theater = mapper.readValue(theaterJson, Theater.class);
		//@formatter:off
		String expected = "都道府県：東京都\n"
				+ "劇場名：渋谷TOEI https://toeitheaters.com/theaters/shibuya/\n"
				+ "TEL：03-5467-5773";
		//@formatter:off
		String actual = service.convertTheater(theater);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_mailList1() {
		List<Theater> addedTheaters = List.of();
		List<Theater> deletedTheaters = List.of();
		List<Theater> diffTheaters = List.of();
		String expected = "";
		String actual = service.mailList(addedTheaters, deletedTheaters, diffTheaters);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_mailList2() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		String theaterJson2 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": false,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		String theaterJson3 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": null,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		String expected = "=============================== 追加 ===============================\n"
				+ "都道府県：東京都\n"
				+ "劇場名：渋谷TOEI https://toeitheaters.com/theaters/shibuya/\n"
				+ "TEL：03-5467-5773\n"
				+ "ムビチケ取り扱い：あり\n"
				+ "上映開始日：2021/10/24\n"
				+ "上映終了日：2021/10/25\n"
				+ "公開日：2021/10/23\n"
				+ "\n"
				+ "都道府県：東京都\n"
				+ "劇場名：渋谷TOEI https://toeitheaters.com/theaters/shibuya/\n"
				+ "TEL：03-5467-5773\n"
				+ "ムビチケ取り扱い：あり\n"
				+ "上映開始日：2021/10/24\n"
				+ "上映終了日：2021/10/25\n"
				+ "公開日：2021/10/23\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "=============================== 変更 ===============================\n"
				+ "都道府県：東京都\n"
				+ "劇場名：渋谷TOEI https://toeitheaters.com/theaters/shibuya/\n"
				+ "TEL：03-5467-5773\n"
				+ "ムビチケ取り扱い：なし\n"
				+ "\n"
				+ "都道府県：東京都\n"
				+ "劇場名：渋谷TOEI https://toeitheaters.com/theaters/shibuya/\n"
				+ "TEL：03-5467-5773\n"
				+ "ムビチケ取り扱い：なし\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "=============================== 削除 ===============================\n"
				+ "都道府県：東京都\n"
				+ "劇場名：渋谷TOEI https://toeitheaters.com/theaters/shibuya/\n"
				+ "TEL：03-5467-5773\n"
				+ "\n"
				+ "都道府県：東京都\n"
				+ "劇場名：渋谷TOEI https://toeitheaters.com/theaters/shibuya/\n"
				+ "TEL：03-5467-5773";
		//@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		Theater theater2 = mapper.readValue(theaterJson2, Theater.class);
		Theater theater3 = mapper.readValue(theaterJson3, Theater.class);
		List<Theater> addedTheaters = List.of(theater1, theater1);
		List<Theater> deletedTheaters = List.of(theater3, theater3);
		List<Theater> diffTheaters = List.of(theater2, theater2);
		String actual = service.mailList(addedTheaters, deletedTheaters, diffTheaters);
		assertEquals(expected, actual);
	}

	@Test
	public void test_checkAddedTheater_差分なし() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		//@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		List<Theater> actual = service.checkAddedTheaters(List.of(theater1), List.of(theater1.getTheaterCd()));
		assertEquals(0, actual.size());
	}

	@Test
	public void test_checkAddedTheater_追加() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		String theaterJson2 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0504\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI2\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": false,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
	    //@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		Theater theater2 = mapper.readValue(theaterJson2, Theater.class);
		List<Theater> actual = service.checkAddedTheaters(List.of(theater1, theater2),
				List.of(theater1.getTheaterCd()));
		assertEquals(1, actual.size());
		assertEquals(theater2, actual.get(0));
	}

	@Test
	public void test_checkAddedTheater_削除() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		String theaterJson2 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0504\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI2\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": false,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
	    //@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		Theater theater2 = mapper.readValue(theaterJson2, Theater.class);
		List<Theater> actual = service.checkAddedTheaters(List.of(theater1),
				List.of(theater1.getTheaterCd(), theater2.getTheaterCd()));
		assertEquals(0, actual.size());
	}

	@Test
	public void test_checkDeletedTheater_差分なし() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		//@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		List<Theater> actual = service.checkDeletedTheaters(List.of(theater1), List.of(theater1.getTheaterCd()));
		assertEquals(0, actual.size());
	}

	@Test
	public void test_checkDeletedTheater_追加() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		String theaterJson2 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0504\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI2\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": false,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
	    //@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		Theater theater2 = mapper.readValue(theaterJson2, Theater.class);
		List<Theater> actual = service.checkDeletedTheaters(List.of(theater1),
				List.of(theater1.getTheaterCd(), theater2.getTheaterCd()));
		assertEquals(0, actual.size());
	}

	@Test
	public void test_checkDeletedTheater_削除() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		String theaterJson2 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0504\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI2\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": false,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
	    //@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		Theater theater2 = mapper.readValue(theaterJson2, Theater.class);
		List<Theater> actual = service.checkDeletedTheaters(List.of(theater1, theater2),
				List.of(theater1.getTheaterCd()));
		assertEquals(1, actual.size());
		assertEquals(theater2, actual.get(0));
	}

	@Test
	public void test_checkDiffTheater_差分なし() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		//@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		List<Theater> actual = service.checkDiffTheaters(List.of(theater1), List.of(theater1),
				List.of(theater1.getTheaterCd()), List.of(theater1.getTheaterCd()));
		assertEquals(0, actual.size());
	}

	@Test
	public void test_checkDiffTheater_追加() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		String theaterJson2 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0504\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI2\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": false,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
	    //@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		Theater theater2 = mapper.readValue(theaterJson2, Theater.class);
		List<Theater> actual = service.checkDiffTheaters(List.of(theater1, theater2), List.of(theater1),
				List.of(theater1.getTheaterCd(), theater2.getTheaterCd()), List.of(theater1.getTheaterCd()));
		assertEquals(0, actual.size());
	}

	@Test
	public void test_checkDiffTheater_削除() throws Exception {
		//@formatter:off
		String theaterJson1 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0503\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": true,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": \"2021-10-23T15:00:00Z\",\n"
				+ "  \"last_end_dt\": \"2021-10-24T15:00:00Z\",\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
		String theaterJson2 = "{\n"
				+ "  \"title_cd\": \"02846\",\n"
				+ "  \"hp_area_cd\": \"003\",\n"
				+ "  \"hp_area_nm\": \"関東甲信越\",\n"
				+ "  \"theater_cd\": \"0504\",\n"
				+ "  \"theater_nm\": \"渋谷TOEI2\",\n"
				+ "  \"prefectures_cd\": \"13\",\n"
				+ "  \"prefectures_nm\": \"東京都\",\n"
				+ "  \"theater_url\": \"https://toeitheaters.com/theaters/shibuya/\",\n"
				+ "  \"hp_tel\": \"03-5467-5773\",\n"
				+ "  \"movie_ticket_flg\": false,\n"
				+ "  \"title_branch_nms\": [],\n"
				+ "  \"event_nm1\": null,\n"
				+ "  \"event_color1\": null,\n"
				+ "  \"event_nm2\": null,\n"
				+ "  \"event_color2\": null,\n"
				+ "  \"event_nm3\": null,\n"
				+ "  \"event_color3\": null,\n"
				+ "  \"event_nm4\": null,\n"
				+ "  \"event_color4\": null,\n"
				+ "  \"first_start_dt\": null,\n"
				+ "  \"last_end_dt\": null,\n"
				+ "  \"fax\": \"03-5467-5776\",\n"
				+ "  \"title_release_start_dt\": null,\n"
				+ "  \"remarks\": [],\n"
				+ "  \"disp_order\": 1010\n"
				+ "}";
	    //@formatter:on
		Theater theater1 = mapper.readValue(theaterJson1, Theater.class);
		Theater theater2 = mapper.readValue(theaterJson2, Theater.class);
		List<Theater> actual = service.checkDiffTheaters(List.of(theater1), List.of(theater1, theater2),
				List.of(theater1.getTheaterCd()), List.of(theater1.getTheaterCd(), theater2.getTheaterCd()));
		assertEquals(0, actual.size());
	}

	@Test
	public void test_checkDiffTheater_差分() throws Exception {
		//@formatter:off
	    String testJson1 = "[\n"
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
	    String testJson2 = "[\n"
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
	    		+ "    \"theater_nm\": \"新宿バルト9-1\",\n"
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
		List<Theater> pastTheaters = mapper.readValue(testJson1, new TypeReference<List<Theater>>() {
		});
		List<Theater> theaters = mapper.readValue(testJson2, new TypeReference<List<Theater>>() {
		});
		List<Theater> actual = service.checkDiffTheaters(theaters, pastTheaters, List.of("0503", "4861"),
				List.of("0503", "4861"));
		assertEquals(1, actual.size());
		assertEquals("4861", actual.get(0).getTheaterCd());
		assertEquals("新宿バルト9-1", actual.get(0).getTheaterNm());
	}

}
