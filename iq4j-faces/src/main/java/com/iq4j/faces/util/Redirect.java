package com.iq4j.faces.util;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

public class Redirect {

	private String viewId;
	private Map<String, String> params;

	public Redirect() {
		this(null);
	}

	public Redirect(String viewId) {
		super();
		this.params = new HashMap<String, String>();
		if (viewId == null) {
			viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		}
		this.viewId = viewId;
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
			FacesContext.getCurrentInstance().getExternalContext().redirect(outcome());
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
