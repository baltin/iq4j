package com.iq4j.persistence.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.jboss.seam.international.status.Messages;

import com.iq4j.persistence.api.Entity;
import com.iq4j.persistence.api.EntityInstance;
import com.iq4j.persistence.service.EntityEvents;
import com.iq4j.utils.ClassUtils;
import com.iq4j.utils.impl.InstanceImpl;
import com.iq4j.utils.validation.Validation;

public class EntityInstanceImpl<E extends Entity<ID>, ID extends Serializable> extends InstanceImpl<E> implements EntityInstance<E, ID> {

	private static final long serialVersionUID = -182482728119528685L;

	@Inject
	Messages messages;

	@Inject
	Validation validation;

	@Inject 
	EntityManager entityManager;
	
	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	@Override
	public Class<E> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<E>) ClassUtils.findFirstGenericParameterType(getClass());
			if (entityClass == null) {
				throw new RuntimeException("Cannot find entity class by reflection");
			}
		}
		return entityClass;
	}

	public void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public E getInstance() {
		if (instance == null) {
			this.instance = newInstance();
		}
		return instance;
	}

	@Override
	public ID getId() {
		return getInstance().getId();
	}

	@Override
	public void setId(ID id) {
		setInstance(find(id));
	}

	// --- Utility methods

	public boolean isManaged() {
		return getInstance().isManaged();
	}

	@Override
	public void refresh() {
		setId(getId());
	}

	public String getHumanReadableName() {
		return ClassUtils.getHumanReadableClassName(getEntityClass());
	}

	protected E newInstance() {
		try {
			return getEntityClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cannot create " + getInstance().getClass().getName() + " instance by reflection");
		}
	}

	protected E find(ID id) {

		E result = getEntityManager().find(getEntityClass(), id);

		if (result == null) {
			messages.warn("Can not find {0} instance for Id: {1}", getHumanReadableName(), getId());
		}

		return result;
	}

	@Override
	public boolean validate() {
		return validation.validate(ClassUtils.shortName(getEntityClass()), getInstance());
	}

	// --- Persistence Methods
	@Override
	public boolean persist() {
		if (validate()) {
			getEntityManager().joinTransaction();
			onPersist();
			getEntityManager().persist(getInstance());
			getEntityManager().flush();
			EntityEvents.created(getInstance());
			return true;
		}
		return false;
	}

	@Override
	public boolean update() {
		if (validate()) {
			getEntityManager().joinTransaction();
			onUpdate();
			instance = getEntityManager().merge(getInstance());
			if( ((Session) getEntityManager().getDelegate()).isDirty()) {				
				getEntityManager().flush();
				EntityEvents.updated(getInstance());
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean remove() {
		
		if (isManaged()) {
			getEntityManager().joinTransaction();
			refresh();
			onRemove();
			getEntityManager().remove(getInstance());
			getEntityManager().flush();
			EntityEvents.deleted(getInstance());
			clean();
			return true;
		}
		return false;
	}

	@Override
	public boolean save() {
		if (isManaged()) {
			return update();
		} else {
			return persist();
		}
	}

	// --- Persistence Interceptor methods
	
    protected void onPersist() {};
	protected void onUpdate() {};
	protected void onRemove() {};

}
