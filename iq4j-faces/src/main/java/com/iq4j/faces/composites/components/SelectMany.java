package com.iq4j.faces.composites.components;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;

@FacesComponent(SelectMany.COMPONENT_TYPE)
public class SelectMany extends Input {

	public static final String COMPONENT_TYPE = "com.iq4j.faces.composites.components.SelectMany";
	
	@Override
	protected void onEncodeBegin() {
		
		ValueExpression value = getValueExpression("value");
		UIInput selectMany = findInput("i");
		selectMany.setValueExpression("value", value);
		super.onEncodeBegin();
	}
}
