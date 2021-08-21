package net.hokkaidosm.precure.toei_screening.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * https://toei-bookingsystem-api-prod.azurewebsites.net/api/hp/theaters/screening/02846
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class Theater {
	/** 作品ID */
	@JsonProperty("title_cd")
	private String titleCd;
	
	/** エリアID */
	@JsonProperty("hp_area_cd")
	private String hpAreaCd;
	
	/** エリア名 */
	@JsonProperty("hp_area_nm")
	private String hpAreaNm;
	
	/** 劇場ID */
	@JsonProperty("theater_cd")
	private String theaterCd;
	
	/** 劇場名 */
	@JsonProperty("theater_nm")
	private String theaterNm;
	
	/** 都道府県ID */
	@JsonProperty("prefectures_cd")
	private String prefecturesCd;
	
	/** 都道府県 */
	@JsonProperty("prefectures_nm")
	private String prefecturesNm;
	
	/** URL */
	@JsonProperty("theater_url")
	private String theaterUrl;
	
	/** TEL */
	@JsonProperty("hp_tel")
	private String hpTel;
	
	/** ムビチケ取り扱い */
	@JsonProperty("movie_ticket_flg")
	private Boolean movieTicketFlg;
	
	/** 上映開始日 */
	@JsonProperty("first_start_dt")
	private ZonedDateTime firstStartDt;
	
	/** 上映終了日 */
	@JsonProperty("last_end_dt")
	private ZonedDateTime lastEndDt;
	
	/** 公開日 */
	@JsonProperty("title_release_start_dt")
	private ZonedDateTime titleReleaseStartDt;
}
