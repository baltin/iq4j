package com.iq4j.utils.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangeCheckValidator.class)
@Documented
public @interface RangeCheck
{
	
   public boolean allowEquality() default true;
   public boolean nullable() default true;
	
   String message() default "Invalid Range";	
   
   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
   
}
