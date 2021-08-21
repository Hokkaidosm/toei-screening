package net.hokkaidosm.precure.toei_screening;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.hokkaidosm.precure.toei_screening.file.LoadPastTheaterService;
import net.hokkaidosm.precure.toei_screening.model.Theater;
import net.hokkaidosm.precure.toei_screening.model.Title;
import net.hokkaidosm.precure.toei_screening.request.TheaterRequestService;
import net.hokkaidosm.precure.toei_screening.request.TitleRequestService;

@Service
public class MainService {
	@Autowired
	private TitleRequestService titleRequestService;
	@Autowired
	private TheaterRequestService theaterRequestService;
	@Autowired
	private LoadPastTheaterService loadPastTheaterService;

	public void run(String titleCd) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		Title title = null;
		try {
			title = titleRequestService.getTitle(titleCd);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		List<Theater> theaters = null;
		try {
			theaters = theaterRequestService.getTheaters(titleCd);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		List<String> theaterIds = theaters.stream().map(t -> t.getTheaterCd()).collect(Collectors.toList());
		System.out.println(title.getTitleNm());

		// 前回のデータを確認する
		List<Theater> pastTheaters = loadPastTheaterService.loadPastTheaters(titleCd);
		List<String> pastTheaterIds = pastTheaters.stream().map(t -> t.getTheaterCd()).collect(Collectors.toList());

		// 追加分を確認する
		List<Theater> addedTheaters = checkAddedTheaters(theaters, pastTheaterIds);

		// 削除分を確認する
		List<Theater> deletedTheaters = checkDeletedTheaters(pastTheaters, theaterIds);

		// 差分を取る
		List<Theater> diffTheaters = checkDiffTheaters(theaters, pastTheaters, theaterIds, pastTheaterIds);

		String mailBody = mailList(addedTheaters, deletedTheaters, diffTheaters);
		System.out.println(mailBody);
	}

	protected List<Theater> checkAddedTheaters(List<Theater> theaters, List<String> pastTheaterIds) {
		return theaters.stream().filter(t -> !pastTheaterIds.contains(t.getTheaterCd())).collect(Collectors.toList());
	}

	protected List<Theater> checkDeletedTheaters(List<Theater> pastTheaters, List<String> theaterIds) {
		return pastTheaters.stream().filter(t -> !theaterIds.contains(t.getTheaterCd())).collect(Collectors.toList());
	}

	protected List<Theater> checkDiffTheaters(List<Theater> theaters, List<Theater> pastTheaters,
			List<String> theaterIds, List<String> pastTheaterIds) {
		List<String> intersectionIds = ListUtils.intersection(theaterIds, pastTheaterIds);
		List<Theater> diffTheaters = new ArrayList<>();
		for (String id : intersectionIds) {
			Theater theater = theaters.stream().filter(t -> id.equals(t.getTheaterCd())).findFirst().get();
			Theater pastTheater = pastTheaters.stream().filter(t -> id.equals(t.getTheaterCd())).findFirst().get();
			if (!theater.equals(pastTheater)) {
				diffTheaters.add(theater);
			}
		}
		return diffTheaters;
	}

	protected String mailList(List<Theater> addedTheaters, List<Theater> deletedTheaters, List<Theater> diffTheaters) {
		StringBuilder sb = new StringBuilder();
		if (!ObjectUtils.isEmpty(addedTheaters)) {
			sb.append("=============================== 追加 ===============================\n");
			List<String> collect = addedTheaters.stream().map(t -> convertTheater(t)).collect(Collectors.toList());
			sb.append(String.join("\n\n", collect));
			sb.append("\n\n\n\n\n\n");
		}
		if (!ObjectUtils.isEmpty(diffTheaters)) {
			sb.append("=============================== 変更 ===============================\n");
			List<String> collect = diffTheaters.stream().map(t -> convertTheater(t)).collect(Collectors.toList());
			sb.append(String.join("\n\n", collect));
			sb.append("\n\n\n\n\n\n");
		}
		if (!ObjectUtils.isEmpty(deletedTheaters)) {
			sb.append("=============================== 削除 ===============================\n");
			List<String> collect = deletedTheaters.stream().map(t -> convertTheater(t)).collect(Collectors.toList());
			sb.append(String.join("\n\n", collect));
			sb.append("\n\n\n\n\n\n");
		}
		return sb.toString().trim();
	}

	protected String convertTheater(Theater theater) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		StringBuilder sb = new StringBuilder();
		sb.append("都道府県：");
		sb.append(theater.getPrefecturesNm());
		sb.append("\n");

		sb.append("劇場名：");
		sb.append(theater.getTheaterNm() + " " + theater.getTheaterUrl());
		sb.append("\n");

		sb.append("TEL：");
		sb.append(theater.getHpTel());
		sb.append("\n");

		if (!ObjectUtils.isEmpty(theater.getMovieTicketFlg())) {
			sb.append("ムビチケ取り扱い：");
			sb.append(theater.getMovieTicketFlg() ? "あり" : "なし");
			sb.append("\n");
		}

		if (!ObjectUtils.isEmpty(theater.getFirstStartDt())) {
			sb.append("上映開始日：");
			sb.append(theater.getFirstStartDt().withZoneSameInstant(ZoneId.of("Asia/Tokyo")).format(formatter));
			sb.append("\n");
		}

		if (!ObjectUtils.isEmpty(theater.getLastEndDt())) {
			sb.append("上映終了日：");
			sb.append(theater.getLastEndDt().withZoneSameInstant(ZoneId.of("Asia/Tokyo")).format(formatter));
			sb.append("\n");
		}

		if (!ObjectUtils.isEmpty(theater.getTitleReleaseStartDt())) {
			sb.append("公開日：");
			sb.append(theater.getTitleReleaseStartDt().withZoneSameInstant(ZoneId.of("Asia/Tokyo")).format(formatter));
			sb.append("\n");
		}

		return sb.toString().trim();
	}
}
