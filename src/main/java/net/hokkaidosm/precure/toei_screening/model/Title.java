package net.hokkaidosm.precure.toei_screening.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * https://toei-bookingsystem-api-prod.azurewebsites.net/api/hp/titles/02846
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Title {
	@JsonProperty("title_cd")
    private String titleCd;
	@JsonProperty("title_nm")
    private String titleNm;
}
