package com.iq4j.faces.session;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

@ApplicationScoped
public class EntityTypeStore {

	private Map<String, EntityType<?>> entityTypes;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	protected EntityManager entityManager;
	
	@PostConstruct
	protected void init() {
		
		this.entityTypes = Collections.synchronizedMap( new HashMap<String, EntityType<?>>() );
		
		Set<EntityType<?>> entityTypes = entityManager.getEntityManagerFactory().getMetamodel().getEntities();
		
		for (EntityType<?> entityType : entityTypes) {
			if(entityType.hasSingleIdAttribute()) {				
				this.entityTypes.put(entityType.getName(), entityType);
			}
		}
		
	}
	
	public synchronized Class<?> findType(String entityName) {
		return entityTypes.get(entityName).getJavaType();
	}
	
	public synchronized  Class<?> findIdType( String entityName ) {
		return entityTypes.get(entityName).getIdType().getJavaType();
	}
	
	
}