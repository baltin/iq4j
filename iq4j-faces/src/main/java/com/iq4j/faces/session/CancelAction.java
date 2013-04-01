package com.iq4j.faces.session;

import javax.enterprise.inject.Model;

@Model
public class CancelAction {

	public String execute() {
		return "cancel";
	}
	
}
