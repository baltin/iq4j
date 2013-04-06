package com.iq4j.persistence.service;

import java.lang.annotation.Annotation;

import javax.enterprise.util.AnnotationLiteral;

import com.iq4j.persistence.qualifiers.EntityAllCrudEvents;
import com.iq4j.persistence.qualifiers.EntityCreated;
import com.iq4j.persistence.qualifiers.EntityDeleted;
import com.iq4j.persistence.qualifiers.EntityRead;
import com.iq4j.persistence.qualifiers.EntityUpdated;
import com.iq4j.utils.BeanUtils;


@SuppressWarnings("serial")
public class EntityEvents {

	public static void fire( Object event, Annotation... qualifiers) {
		BeanUtils.getBeanManager().fireEvent(event, qualifiers);
	}
	
	public static void deleted(Object event) {
		fire(event, new AnnotationLiteral<EntityDeleted>(){}, new AnnotationLiteral<EntityAllCrudEvents>() {});
	}
	
	public static void updated(Object event) {
		fire(event, new AnnotationLiteral<EntityUpdated>(){}, new AnnotationLiteral<EntityAllCrudEvents>() {});
	}
	
	public static void created(Object event) {
		fire(event, new AnnotationLiteral<EntityCreated>(){}, new AnnotationLiteral<EntityAllCrudEvents>() {});
	}

	public static void read(Object event) {
		fire(event, new AnnotationLiteral<EntityRead>(){}, new AnnotationLiteral<EntityAllCrudEvents>() {});
	}
	
	
}
