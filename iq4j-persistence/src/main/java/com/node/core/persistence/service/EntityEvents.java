package com.node.core.persistence.service;

import java.lang.annotation.Annotation;

import javax.enterprise.util.AnnotationLiteral;

import com.iq4j.utils.BeanUtils;
import com.node.core.persistence.qualifiers.EntityCreated;
import com.node.core.persistence.qualifiers.EntityDeleted;
import com.node.core.persistence.qualifiers.EntityRead;
import com.node.core.persistence.qualifiers.EntityUpdated;


@SuppressWarnings("serial")
public class EntityEvents {

	public static void fire( Object event, Annotation... qualifiers) {
		BeanUtils.getBeanManager().fireEvent(event, qualifiers);
	}
	
	public static void deleted(Object event) {
		fire(event, new AnnotationLiteral<EntityDeleted>(){});
	}
	
	public static void updated(Object event) {
		fire(event, new AnnotationLiteral<EntityUpdated>(){});
	}
	
	public static void created(Object event) {
		fire(event, new AnnotationLiteral<EntityCreated>(){});
	}

	public static void read(Object event) {
		fire(event, new AnnotationLiteral<EntityRead>(){});
	}
	
	
}
