package com.ai4j.faces.converters;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@RequestScoped
@FacesConverter(value="upperCaseConverter")
public class UpperCaseConverter implements Converter, Serializable {

    private static final long serialVersionUID = 7769131333455732099L;
    
   
    public Object getAsObject(FacesContext ctx, UIComponent component, String val) {
        if(val == null){
            return null;
        }
        return val.trim().toUpperCase();
    }

    public String getAsString(FacesContext ctx, UIComponent component, Object val) {
        String value = (String) val;
        if(value != null){
            return value.toUpperCase();
        }
        return value;
    }
}
