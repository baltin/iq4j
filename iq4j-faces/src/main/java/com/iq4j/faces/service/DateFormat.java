package com.iq4j.faces.service;

import javax.enterprise.inject.Model;

@Model
public class DateFormat {

	private String value = "dd.MM.yyyy";
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
