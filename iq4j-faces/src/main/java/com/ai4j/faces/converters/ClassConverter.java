package com.ai4j.faces.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(value="classConverter")
@RequestScoped
@Named
public class ClassConverter implements Converter {


	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {		 
		try {
			return (value != null) ? Class.forName(value) : null;			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {		
		return (value != null) ? ((Class<?>) value).getName() : null;
	}
	
	
	
}
