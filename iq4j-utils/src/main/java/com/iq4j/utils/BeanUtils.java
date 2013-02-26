package com.iq4j.utils;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.jboss.solder.beanManager.BeanManagerLocator;

public class BeanUtils {

	public static <T> T getBeanInstance(final Class<T> type) {
		return getBeanInstance(type, RequestScoped.class);
	}
	
	public static <T> T getBeanInstance(final Class<T> type, final Class<? extends Annotation> scope) {
		return getBeanInstance(type, scope, getBeanManager());
	}
	
	public static <T> T getBeanInstance(final Class<T> type, final Class<? extends Annotation> scope, final BeanManager beanManager)
	{
	   final Context context = beanManager.getContext(scope);
	   final Set<Bean<?>> beans = beanManager.getBeans(type);
	   @SuppressWarnings("unchecked")
	   final Bean<T> bean = (Bean<T>) beanManager.resolve(beans);
	   final CreationalContext<T> creationalContext = beanManager.createCreationalContext(bean);
	 
	   return context.get(bean, creationalContext);
	}
	
	
	public static BeanManager getBeanManager() {
		return new BeanManagerLocator().getBeanManager();
	}
	
}
