package com.ai4j.faces.composites;

public enum GeneralKeys{
	
	id,
	valid,
	label,
	rendered,
	style,
	styleClass,
	errorStyleClass,
	showErrorMsg,
	showLabel,
	labelPosition,
	labelComponent("lbl"),
	messageComponent("msg"),
	tooltipComponent("tt"),
	separatorComponent("sp"),
	validatorComponent("validator"),
	contentPanelGroupComponent("ctx");
	
	String toString;
	
	GeneralKeys(String toString) {
		this.toString = toString;
	}

	GeneralKeys() {
	}

	public String toString() {
		return ((this.toString != null) ? this.toString : super.toString());
	}
	
}