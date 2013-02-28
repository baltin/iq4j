package com.iq4j.faces.composites.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import com.iq4j.faces.composites.CompositeHelper;

@FacesComponent(Output.COMPONENT_TYPE)
public class Output extends UIComponentBase implements	NamingContainer {
	
	public static final String COMPONENT_TYPE = "com.ai4j.faces.composites.components.Output";

	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		
		if (!isRendered()) {
			return;
		}
		super.encodeBegin(context);
		CompositeHelper.updateSeparatorRendered(this);		
		onEncodeBegin();
	}
		
	protected void onEncodeBegin(){
		
	}	
	
	@Override
	public String getFamily() {
		return UINamingContainer.COMPONENT_FAMILY;
	}

	
}
