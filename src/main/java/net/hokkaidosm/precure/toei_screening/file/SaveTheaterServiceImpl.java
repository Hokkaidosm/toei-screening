package net.hokkaidosm.precure.toei_screening.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.hokkaidosm.precure.toei_screening.model.Theater;

@Service
public class SaveTheaterServiceImpl implements SaveTheaterService {

	@Override
	public void saveTheaters(List<Theater> theaters, String titleCd) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		Path file = Paths.get(String.format("%s.json", titleCd)).toAbsolutePath();
		Charset charset = Charset.forName("UTF-8");
		List<String> list = List.of(mapper.writeValueAsString(theaters));
		try {
			if (!Files.exists(file)) {
				Files.createFile(file);
			}
			Files.write(file, list, charset, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
