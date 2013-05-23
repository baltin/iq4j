package com.iq4j.utils.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.validation.ConstraintViolation;

@Model
public class Validation {

	private SortedMap<String, List<String>> errorsMap;

	@PostConstruct
	public void init() {
		errorsMap = new TreeMap<String, List<String>>();
	}

	protected <T> void put(String group, ConstraintViolation<T> constraintViolation) {
		List<String> errors = errorsMap.get(group);
		
		if (errors == null) {
			errors = new ArrayList<String>();
			errorsMap.put(group, errors);
		}
		
		String property = constraintViolation.getPropertyPath() != null 
								? constraintViolation.getPropertyPath().toString()
							    : "";
							
		errors.add(property + " " + constraintViolation.getMessage());
	}

	public List<String> getGroups() {
		return new ArrayList<String>(errorsMap.keySet());
	}

	public List<String> errorsForGroup(String group) {
		return errorsMap.get(group);
	}

	public boolean isHasErrors() {
		return errorsMap.size() > 0;
	}

	public boolean hasErrorsForGroup(String group) {
		boolean result = errorsForGroup(group) != null;
		return result;
	}

	public <T> boolean validate(String group, T instance, Class<?>... groups) {
		Set<ConstraintViolation<T>> violations = ValidationUtil.validate(instance, groups);
		if (violations.size() > 0) {
			for (ConstraintViolation<T> constraintViolation : violations) {
				put(group, constraintViolation);
			}
			return false;
		} else {
			return true;
		}
	}

	public <T> boolean validateProperty(String group, T instance, String propertyName, Class<?>... groups) {
		Set<ConstraintViolation<T>> violations = ValidationUtil.validateProperty(instance, propertyName, groups);
		if (violations.size() > 0) {
			for (ConstraintViolation<T> constraintViolation : violations) {
				put(group, constraintViolation);
			}
			return false;
		} else {
			return true;
		}
	}

}
