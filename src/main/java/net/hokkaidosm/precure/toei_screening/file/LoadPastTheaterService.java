package net.hokkaidosm.precure.toei_screening.file;

import java.util.List;

import net.hokkaidosm.precure.toei_screening.model.Theater;

/**
 * キャッシュ読み込み
 *
 */
public interface LoadPastTheaterService {
	List<Theater> loadPastTheaters(String titleCd);
}
