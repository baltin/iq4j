package com.iq4j.faces.converters;


import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.iq4j.faces.session.EntityTypeStore;
import com.iq4j.utils.Strings;

import static com.iq4j.faces.composites.CompositeHelper.getStringValue;
import static com.iq4j.faces.composites.CompositeHelper.getBooleanValue;

/**
 * Converter for JPA entities. Uses the id of the entity for the key.
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 * @author Anatolian (sertac at anadollu dot com) </a>
 */
@Named
@FacesConverter("entityConverter")
@RequestScoped
public class EntityConverter implements javax.faces.convert.Converter{
   
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject 
	EntityManager entityManager;
	
	@Inject EntityTypeStore entityTypeStore;
	


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	
    	String entity = getStringValue(component, "entity");
    	String noSelectionValue = getStringValue(component, "noSelectionValue"); 
    	boolean convertAsProxy = getBooleanValue(component, "convertAsProxy");
    	
		if( !Strings.isEmpty(value) && ( isSelectOne(component) ? !value.equals(noSelectionValue) : true ) ) {	
			
			Class<?> entityType = entityTypeStore.findType(entity);
			Class<?> idType = entityTypeStore.findIdType(entity);
			Object id = convertStringToId(idType, value);
			
			if(convertAsProxy) {				
				return this.entityManager.find(entityType, id);
			} else {
				return this.entityManager.getReference(entityType, id);
			}
		}
		return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    
    	return (value == null) 
    				? null 
    				: findId(value).toString();
    	
    }

    private Serializable findId(Object entity) {        
    	return (Serializable) this.entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

	protected boolean isSelectOne(UIComponent component) {
		return UISelectOne.class.isAssignableFrom(component.getClass());
	}

	protected Object convertStringToId (Class<?> idType, String value) {
		if(Long.class.equals(idType)) {
			return Long.valueOf(value);
		} else if ( String.class.equals(idType)) {
			return value;
		} else if ( Integer.class.equals(idType) ) {
			return Integer.valueOf(value);
		} else {
			throw new RuntimeException(" Couldn't create value of " + idType.getName() );
		}
	}
	
}

