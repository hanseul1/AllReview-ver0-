package com.hs.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.hs.review.util.MapReduceUtil;

@Service
public class MapReduceServiceImpl implements MapReduceService {
	@Autowired
	private MapReduceUtil mapReduceUtil;
	
	/** model 리스트에 대한 리뷰 평점 평균 리턴
	 * @param models 사용자가 선택한 model 리스트
	 */
	public Map<String,Object> getRatingAvgByModel(List<String> models){
		if(models.size() != 0) {
			// {"model": {"$in": [model list, ...] }}
			Query query = new Query(Criteria.where("model").in(models));
			return mapReduceUtil.calculateAvgByModel(query);
		}
		return mapReduceUtil.calculateAvgByModel(null);
	}
}
