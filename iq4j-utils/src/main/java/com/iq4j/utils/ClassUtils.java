package com.iq4j.utils;

import java.beans.Introspector;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassUtils {
	
	public static String shortName(Class<?> clazz) {		
		String className = clazz.getName();
		return className.lastIndexOf(".") > 0 && className.lastIndexOf(".") < className.length() ? className.substring(className.lastIndexOf(".") + 1, className.length()) : className;
	} 
	
	public static String getHumanReadableClassName(Class<?> clazz){
		return Strings.splitCamelCase(shortName(clazz));
	}
	
	public static Type findFirstGenericParameterType(Class<?> clazz){
		Type firstParameterType = null;
		/**
		 * Loop is necessary if SecurityInterceptor (and probably others) are turned on and this method is invoked on some proxy
		 */
		while (firstParameterType == null && !Object.class.equals(clazz.getSuperclass())) {
			Type type = clazz.getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				if (paramType.getActualTypeArguments().length > 0 ) {
					firstParameterType = paramType.getActualTypeArguments()[0];
				}
			}
			clazz = clazz.getSuperclass();
		}
		return firstParameterType;
	}
	
	public static String decapitalizedClassName(Class<?> clazz) {		
		return Introspector.decapitalize(shortName(clazz));
	}
	
}
