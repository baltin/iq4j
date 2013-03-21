package com.iq4j.faces.taghandler;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import com.iq4j.faces.util.ExpressionsUtil;

public class EntityConverterHandler extends TagHandler {

	private final TagAttribute entity;
	private final TagAttribute noSelectionValue;
	private final TagAttribute convertAsProxy;
	
	public EntityConverterHandler(TagConfig config) {
		super(config);
		this.entity = getRequiredAttribute("entity");
		this.noSelectionValue = getAttribute("noSelectionValue");
		this.convertAsProxy = getAttribute("convertAsProxy");
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
		
		parent.getAttributes().put("entity", entity.getValue(ctx));
		parent.getAttributes().put("noSelectionValue", noSelectionValue.getValue(ctx));
		parent.getAttributes().put("convertAsProxy", convertAsProxy.getObject(ctx) == null ? Boolean.TRUE : convertAsProxy.getBoolean(ctx));
		parent.setValueExpression("converter", new ExpressionsUtil().createValueExpression("#{entityConverter}")); 
		
	}

}
