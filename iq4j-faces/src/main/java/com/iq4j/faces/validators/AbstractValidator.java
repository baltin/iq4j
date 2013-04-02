package com.iq4j.faces.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.iq4j.faces.composites.components.Input;

public abstract class AbstractValidator implements Validator {

	FacesContext facesContext = FacesContext.getCurrentInstance();

	protected void throwValidatorException(String validationMessage) throws ValidatorException {

		FacesMessage message = new FacesMessage(validationMessage);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(message);

	}

	protected void throwValidatorException(String validationMessage, Input input) throws ValidatorException {
		input.setValid(Boolean.FALSE);
		throwValidatorException(validationMessage);
	}

	protected void throwValidatorException(String validationMessage, UIInput input) throws ValidatorException {
		input.setValid(false);
		throwValidatorException(validationMessage);
	}

	protected void createFacesErrorMessage(String message, UIComponent component) {
		FacesMessage msg = new FacesMessage(message);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		facesContext.addMessage(component.getClientId(), msg);
	}

}
