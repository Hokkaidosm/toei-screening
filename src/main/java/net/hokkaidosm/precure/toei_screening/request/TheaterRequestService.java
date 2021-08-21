package net.hokkaidosm.precure.toei_screening.request;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.hokkaidosm.precure.toei_screening.model.Theater;

/**
 * Theaterのリクエスト
 *
 */
public interface TheaterRequestService {
	List<Theater> getTheaters(String titleCd) throws JsonMappingException, JsonProcessingException;

	String getUri(String titleCd);
}
