package net.hokkaidosm.precure.toei_screening.file;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.hokkaidosm.precure.toei_screening.model.Theater;

public interface SaveTheaterService {
	void saveTheaters(List<Theater> theaters, String titleCd) throws JsonProcessingException;
}
