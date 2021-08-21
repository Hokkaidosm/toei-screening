package net.hokkaidosm.precure.toei_screening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.hokkaidosm.precure.toei_screening.model.Title;
import net.hokkaidosm.precure.toei_screening.request.TitleRequestService;

@Service
public class MainService {
	@Autowired
	private TitleRequestService titleRequestSerivice;

	public void run(String titleCd) {
		Title title = null;
		try {
			title = titleRequestSerivice.getTitle(titleCd);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(title.getTitleNm());
	}
}
