package com.iq4j.faces.util;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

public class Redirect {

	private String viewId;
	private Map<String, String> params;

	public Redirect() {
		this(null);
	}

	public Redirect(String viewId) {
		
		this.params = new HashMap<String, String>();
		
		if (viewId == null) {
			viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		}  
		
		if(!viewId.startsWith("/")){
			viewId = "/" + viewId;
		}

		this.viewId = viewId;
	}

	public static void to(String viewId) {
		Redirect.instance(viewId).facesRedirect().to();
	}
	
	public static String outcome(String viewId) {
		return Redirect.instance(viewId).facesRedirect().outcome();
	}
	
	public static Redirect instance(String viewId) {
		return new Redirect(viewId);
	}
	
	public Redirect param(String paramName, Object paramValue) {
		if(paramValue != null) {			
			return param(paramName, paramValue.toString());
		}
		return this;
	}
	
	public Redirect param(String paramName, String paramValue) {
		this.params.put(paramName, paramValue);
		return this;
	}

	public Redirect facesRedirect() {
		return param("faces-redirect", "true");
	}

	public Redirect includeViewParams() {
		return param("includeViewParams", "true");
	}

	public Redirect flashInfo(String message, Object... params) {
		 Messages.addFlashGlobalInfo(message, params);
		 return this;
	}
	
	public Redirect flashWarn(String message, Object... params) {
		Messages.addFlashGlobalWarn(message, params);
		return this;
	}
	
	public Redirect flashError(String message, Object... params) {
		Messages.addFlashGlobalError(message, params);
		return this;
	}
	
	public Redirect flashFatal(String message, Object... params) {
		Messages.addFlashGlobalFatal(message, params);
		return this;
	}
	
	public void to() {
		try {			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect( externalContext.getRequestContextPath() + outcome() );
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String outcome() {

		String separator = "?";
		StringBuilder sb = new StringBuilder(viewId);
		for (Map.Entry<String, String> paramNameValue : params.entrySet()) {
			sb.append(separator).append(paramNameValue.getKey()).append("=").append(paramNameValue.getValue());
			separator = "&";
		}
		return sb.toString();
	}

	public String getOutcome() {
		return outcome();
	}
	
	@Override
	public String toString() {
		return outcome();
	}

}
