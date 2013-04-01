package com.iq4j.faces.session;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;



public class RequestContextPath {
	
	@Produces
	@Named("root")
	@Root
	public String getRoot() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
	
	
}
