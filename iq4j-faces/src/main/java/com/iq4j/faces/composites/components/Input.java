package com.iq4j.faces.composites.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.component.UIMessage;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import org.primefaces.extensions.component.tooltip.Tooltip;

import com.iq4j.faces.composites.CompositeHelper;
import com.iq4j.faces.composites.GeneralKeys;

@FacesComponent(Input.COMPONENT_TYPE)
public class Input extends UIComponentBase implements NamingContainer {
	
	public static final String COMPONENT_TYPE = "com.iq4j.faces.composites.components.Input";

	protected List<UIInput> validInputs = new ArrayList<UIInput>(0);
	
	protected List<UIInput> invalidInputs = new ArrayList<UIInput>(0);
	
	private boolean valid = true ;
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		
		if (!isRendered()) {
			return;
		}
		super.encodeBegin(context);
		findUIInputs();
		CompositeHelper.updateSeparatorRendered(this);
		CompositeHelper.putAttributeValue(this, "valid", valid && invalidInputs.size() == 0 );
		onEncodeBegin();
	}
		
	protected void onEncodeBegin(){
		
		UIInput invalidInput = getFirstInvalidInput();
		if(invalidInput != null)
		{
			if(CompositeHelper.getBooleanValue(this, GeneralKeys.showErrorMsg.toString())){				
				getMessageComponent().setFor(invalidInput.getId());
			}
			else 
			{
				getTooltipComponent().setFor(invalidInput.getId());
			}
			
		}
		
	}
	
	protected UIInput findInput(String name) {
		UIComponent compositeFacet = getFacet(UIComponent.COMPOSITE_FACET_NAME);
		UIComponent inputComponent = compositeFacet.findComponent(name);
		if(inputComponent instanceof UIInput) {
			return (UIInput)inputComponent;
		} else {
			return null;
		}
	}
	
	private void findUIInputs(){
		findUIInputs(getFacet(UIComponent.COMPOSITE_FACET_NAME));
	}
	
	private void findUIInputs(UIComponent component)
	{
		if(component instanceof UIInput){
			UIInput input = (UIInput)component;
			if(input.isValid()){
				validInputs.add(input);
			}
			else {
				invalidInputs.add(input);
			}
			return;
		}
		
		for (Iterator<UIComponent> iterator = component.getFacetsAndChildren(); iterator.hasNext();) {	
			findUIInputs(iterator.next());	
		}
				
	}
	
	
	@Override
	public String getFamily() {
		return UINamingContainer.COMPONENT_FAMILY;
	}

	public UIInput getFirstInvalidInput(){
		return invalidInputs.size() > 0 
				? invalidInputs.get(0)
				: null;
	}
	
	public UIMessage getMessageComponent(){
		return (UIMessage)findComponent(GeneralKeys.messageComponent.toString());
	}

	public Tooltip getTooltipComponent(){
		return (Tooltip)findComponent(GeneralKeys.tooltipComponent.toString());
	}
	
	public List<UIInput> getInvalidInputs() {
		return invalidInputs;
	}
	
	public List<UIInput> getValidInputs() {
		return validInputs;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	
}
