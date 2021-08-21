package net.hokkaidosm.precure.toei_screening.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.hokkaidosm.precure.toei_screening.model.Theater;

@Service
public class LoadPastTheaterServiceImpl implements LoadPastTheaterService {

	@Override
	public List<Theater> loadPastTheaters(String titleCd) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		Path file = Paths.get(String.format("%s.json", titleCd)).toAbsolutePath();
		String pastJson = null;
		try {
			pastJson = Files.readString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Theater> pastTheaters = List.of();
		if (!ObjectUtils.isEmpty(pastJson)) {
			try {
				pastTheaters = mapper.readValue(pastJson, new TypeReference<List<Theater>>() {
				});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return pastTheaters;
	}

}
