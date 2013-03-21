package com.iq4j.utils.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.BeanDescriptor;

public class ValidationUtil {

	protected static final Validator validator;

	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public static Validator getValidator() {
		return validator;
	}

	public static <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
		return getValidator().validateValue(beanType, propertyName, value, groups);
	}

	public static <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
		return validator.validate(object, groups);
	}

	public static <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
		return validator.validateProperty(object, propertyName, groups);
	}

	public static BeanDescriptor getConstraintsForClass(Class<?> clazz) {
		return validator.getConstraintsForClass(clazz);
	}

	public static <T> T unwrap(Class<T> type) {
		return validator.unwrap(type);
	}

}
