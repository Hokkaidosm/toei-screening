package net.hokkaidosm.precure.toei_screening.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Titleクラスのマッピングテスト
 *
 */
public class TitleTest {
    String testJson = "{\n"
    		+ "  \"title_cd\": \"02846\",\n"
    		+ "  \"title_nm\": \"映画トロピカル～ジュ！プリキュア 雪のプリンセスと奇跡の指輪！\",\n"
    		+ "  \"official_url\": \"https://2021.precure-movie.com/\",\n"
    		+ "  \"release_start_dt\": \"2021-10-22T15:00:00Z\",\n"
    		+ "  \"advance_notice\": \"<font size=\\\"4\\\"><b>ムビチケ前売券（カード）発売決定！</b>（7/22(木祝)より劇場窓口にて）</font><br>\\n<br>\\n<font size=\\\"4\\\">　<b>一般1,500円、小人900円</b></font><br>\\n<br>\\n<font size=\\\"3\\\">2つの限定特典!</font><br>\\n<br>\\n<font size=\\\"3\\\"><a href =https://bookingsystemdevelopment.blob.core.windows.net/pdf/eb186f86-2fb5-4b2d-9a60-31f39cf1dcbb.jpg><b>「プリンセスからのキラキラ招待状シール」</b>2種</a></font><br>\\n<br><font size=\\\"3\\\">どちらか1枚をプレゼント！</font>\",\n"
    		+ "  \"always_notice\": null,\n"
    		+ "  \"barrierfree_notice\": null,\n"
    		+ "  \"barrierfree_disp_flg\": false,\n"
    		+ "  \"title_poster_url\": \"https://bookingsystemdevelopment.blob.core.windows.net/poster/002f7e57-842f-4d9c-9ec3-d3889e0de8af.jpg\",\n"
    		+ "  \"barrierfree_icons\": []\n"
    		+ "}";
    
    @Test
    public void test() throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
    	Title actual = mapper.readValue(testJson, Title.class);
    	assertEquals("02846", actual.getTitleCd());
    	assertEquals("映画トロピカル～ジュ！プリキュア 雪のプリンセスと奇跡の指輪！", actual.getTitleNm());
    }
}
