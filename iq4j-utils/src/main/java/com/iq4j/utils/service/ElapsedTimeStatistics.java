package com.iq4j.utils.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;

@Model
public class ElapsedTimeStatistics {
	
	private Map<String, List<Long>> statisticsMap = new HashMap<String, List<Long>>();
	
	public void put (String key, Long value) {
		
		if(statisticsMap.containsKey(key)) {
			statisticsMap.get(key).add(value);
		}
		else {
			statisticsMap.put(key, new ArrayList<Long>(Arrays.asList(value)));
		}
		
	}
	
	public List<Long> get(String key) {
		return statisticsMap.get(key);
	}
	
	public List<String> getKeys() {
		return new ArrayList<String>(statisticsMap.keySet());
	}
	
}
