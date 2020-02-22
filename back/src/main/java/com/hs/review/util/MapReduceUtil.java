package com.hs.review.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.hs.review.dto.ValueObject;

@Component
public class MapReduceUtil {
	@Autowired
	private MongoTemplate template;
	
	public Map<String, Object> mapReduce(Query query, String mapJsPath, String reduceJsPath) {
		MapReduceResults<ValueObject> results = performMapReduce(query, mapJsPath, reduceJsPath);
		return makeMap(results);
	}
	
	/** 실제 map function, reduce function 실행해 결과 산출하는 함수 */
	private MapReduceResults<ValueObject> performMapReduce(Query query, String mapJsPath, String reduceJsPath){
		if(query == null) 
			return template.mapReduce("review", mapJsPath, 
									reduceJsPath, ValueObject.class);
		return template.mapReduce(query, "review", 
									mapJsPath, reduceJsPath, ValueObject.class);
	}
	
	/** mapreduce 결과값을 Map 형태로 변환해주는 함수 */
	private Map<String, Object> makeMap(Iterable<ValueObject> results){
		Map<String, Object> map = new HashMap<String, Object>();
		for(ValueObject vo : results) {
			map.put(vo.getId(), vo.getValue());
		}
		
		return map;
	}
}
