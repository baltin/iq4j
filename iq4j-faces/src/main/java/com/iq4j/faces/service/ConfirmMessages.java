package com.iq4j.faces.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

@Model
public class ConfirmMessages {

	private Map<String, String> messages = new HashMap<String, String>();
	
	@PostConstruct
	private void init() {
		messages.put("delete", "Are you sure you want to delete this record ?");
		messages.put("default", "Are you sure about this action?");
	}
	
	public String get(String key) {
		return messages.get(key);
	}
	
}
