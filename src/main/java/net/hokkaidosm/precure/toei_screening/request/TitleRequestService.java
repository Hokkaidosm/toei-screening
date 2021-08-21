package net.hokkaidosm.precure.toei_screening.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.hokkaidosm.precure.toei_screening.model.Title;

/**
 * Titleのリクエスト
 *
 */
public interface TitleRequestService {
	Title getTitle(String titleCd) throws JsonMappingException, JsonProcessingException;

	String getUri(String titleCd);
}
