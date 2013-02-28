package com.iq4j.faces.composites;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;

public class CompositeHelper {
	
	public static void updateSeparatorRendered(UIComponent composit){
		
		HtmlOutputText outputText = (HtmlOutputText)composit.findComponent(GeneralKeys.separatorComponent.toString());
		boolean showLabel = getBooleanValue(composit, GeneralKeys.showLabel.toString());
		boolean labelPositionIsLeft = getStringValue(composit, GeneralKeys.labelPosition.toString()).trim().toLowerCase().equals("left");		
		boolean rendered = showLabel ? labelPositionIsLeft : false;		
		outputText.setRendered(rendered);
	
	}
	
	public static void findChildrens( List<UIComponent> result,  Class<?> componentClass, UIComponent parent) {
		
		if(result == null) 
			result = new ArrayList<UIComponent>();
		
		if(parent == null) 
			return;
		
		if(parent.getClass().equals(componentClass)) {
			result.add(parent);
		}
				
		for (Iterator<UIComponent> iterator = parent.getFacetsAndChildren(); iterator.hasNext();) {
			
			UIComponent uiComponent = (UIComponent) iterator.next();
			findChildrens(result, componentClass, uiComponent);
		}
		
		//return result;
	}
	
//	public static void wireMenuItems (UIComponent compositeComponent, String facetName, UIComponent parent) {
//		
//		UIComponent facet = compositeComponent.getFacet(facetName);
//		
//		List<UIComponent> result = new ArrayList<UIComponent>(0);
//		
//		findChildrens(result, MenuItem.class, facet);
//		
//		for (UIComponent uiComponent : result) {
//			parent.getChildren().add(uiComponent);
//		}
//		
//	}
	
	public static String getStringValue(UIComponent component, String attributeName){
		Object value = getAttributeValue(component, attributeName);
		return value != null ? (String)value : "" ;
	}

	public static Boolean getBooleanValue(UIComponent component, String attributeName){
		Object value = getAttributeValue(component, attributeName);
		return value != null ? new Boolean(value.toString()) : Boolean.FALSE ;
	}
	
	public static Integer getIntegerValue(UIComponent component, String attributeName){
		Object value = getAttributeValue(component, attributeName);
		return value != null ? new Integer(value.toString()) : new Integer(0);
	}
	
	public static Object getAttributeValue(UIComponent component, String attributeName){
		return component.getAttributes().get(attributeName);
	}
	
	public static void putAttributeValue(UIComponent component, String key, Object value){
		component.getAttributes().put(key, value);
	}

}
