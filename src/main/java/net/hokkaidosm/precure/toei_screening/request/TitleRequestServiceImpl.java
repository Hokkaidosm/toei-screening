package net.hokkaidosm.precure.toei_screening.request;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.hokkaidosm.precure.toei_screening.model.Title;

@Service
public class TitleRequestServiceImpl implements TitleRequestService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Title getTitle(String titleCd) throws JsonMappingException, JsonProcessingException {
		String j = restTemplate.getForObject(getUri(titleCd), String.class, Map.of());
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(j, Title.class);
	}

	@Override
	public String getUri(String titleCd) {
		return String.format("https://toei-bookingsystem-api-prod.azurewebsites.net/api/hp/titles/%s", titleCd);
	}

}
