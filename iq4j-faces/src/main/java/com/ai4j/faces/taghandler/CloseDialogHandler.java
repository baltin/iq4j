package com.ai4j.faces.taghandler;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

public class CloseDialogHandler extends TagHandler {

	private final TagAttribute enabledAttribute;
	private final TagAttribute considerFacesValidationAttribute;
	private final TagAttribute conditionAttribute;
	
	public CloseDialogHandler(TagConfig config) {
		super(config);
		enabledAttribute = getAttribute("enabled");
		conditionAttribute = getAttribute("condition");
		considerFacesValidationAttribute = getAttribute("considerFacesValidation");
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
		
		if(enabledAttribute != null && !enabledAttribute.getBoolean(ctx)) {
			return;
		}
		
		if( parent != null && !ComponentHandler.isNew(parent)) {
			String sourceClientId = getSourceClientId(ctx);
			if(parent.getClientId().equals(sourceClientId) && facesValidationPassed(ctx) && conditionValidationPassed(ctx) ) {
				String dialogWidgetVar = findDialogWidgetVar(parent);
				if(null != dialogWidgetVar) {					
					RequestContext rc = RequestContext.getCurrentInstance();
					rc.execute(dialogWidgetVar + ".hide()");
				}
			}			
		}
	}
	
	
	protected boolean facesValidationPassed (FaceletContext ctx) {
		boolean cfv = considerFacesValidationAttribute == null ? false : considerFacesValidationAttribute.getBoolean(ctx);
		return cfv ? !ctx.getFacesContext().isValidationFailed() : true;
	}
	
	protected Boolean conditionValidationPassed(FaceletContext ctx) {
		
		return  conditionAttribute == null 
						   ? Boolean.TRUE 
						   : (Boolean) conditionAttribute.getValueExpression(ctx, Boolean.class).getValue(ctx.getFacesContext().getELContext());
		
	}
	
	protected String findDialogWidgetVar(UIComponent component) {
		
		if(component instanceof Dialog) {
			return ((Dialog)component).getWidgetVar();
		} else if ( component.getParent() != null) {
			return findDialogWidgetVar(component.getParent());
		} else {			
			return null;
		}
		
	}
	
	protected String getSourceClientId(FaceletContext ctx) {
		return ctx.getFacesContext().getExternalContext().getRequestParameterMap().get("javax.faces.source");
	}
	
}
