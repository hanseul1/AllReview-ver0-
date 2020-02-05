package com.hs.review.service;

import java.util.List;
import java.util.Map;

public interface MapReduceService {

	/** model 리스트에 대한 리뷰 평점 평균 리턴
	 * @param models 사용자가 선택한 model 리스트
	 */
	Map<String, Object> getRatingAvgByModel(List<String> models);

}