package com.iq4j.faces.composites.components;

import javax.faces.component.FacesComponent;

import com.iq4j.faces.composites.GeneralKeys;


@FacesComponent(IntegerRangeInput.COMPONENT_TYPE)
public class IntegerRangeInput extends Input {
	
	public static final String COMPONENT_TYPE = "com.iq4j.faces.composites.components.IntegerRange";
	
	@Override
	protected void onEncodeBegin() {
		getMessageComponent().setFor(GeneralKeys.validatorComponent.toString());
	}
}
