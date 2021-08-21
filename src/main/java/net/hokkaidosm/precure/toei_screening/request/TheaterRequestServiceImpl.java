package net.hokkaidosm.precure.toei_screening.request;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.hokkaidosm.precure.toei_screening.model.Theater;

@Service
public class TheaterRequestServiceImpl implements TheaterRequestService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Theater> getTheaters(String titleCd) throws JsonMappingException, JsonProcessingException {
		String j = restTemplate.getForObject(getUri(titleCd), String.class, Map.of());
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper.readValue(j, new TypeReference<List<Theater>>() {
		});
	}

	@Override
	public String getUri(String titleCd) {
		return String.format("https://toei-bookingsystem-api-prod.azurewebsites.net/api/hp/theaters/screening/%s",
				titleCd);
	}

}
