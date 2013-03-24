package com.iq4j.persistence.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;

import org.jboss.seam.transaction.Transactional;

@Transactional
public class PersistenceUtil {
	
	@Inject
	protected transient EntityManager entityManager;
	
	public PersistenceUtil() {}
	
	public PersistenceUtil(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public <T> void create(final T entity) {
		getEntityManager().persist(entity);
	}

	public <T> void delete(final T entity) throws NoResultException {
		getEntityManager().remove(entity);
	}

	public <T> T deleteById(final Class<T> type, final Object id) throws NoResultException {
		T object = findById(type, id);
		delete(object);
		return object;
	}

	@SuppressWarnings("unchecked")
	public <T> T findById(final Class<T> type, final Object id) throws NoResultException {
		Class<?> clazz = getObjectClass(type);
		T result = (T) getEntityManager().find(clazz, id);
		if (result == null) {
			throw new NoResultException("No object of type: " + type + " with ID: " + id);
		}
		return result;
	}

	public <T> void save(final T entity) {
		getEntityManager().merge(entity);
	}

	public <T> void refresh(final T entity) {
		getEntityManager().refresh(entity);
	}

	protected Class<?> getObjectClass(final Object type) throws IllegalArgumentException {
		Class<?> clazz = null;
		if (type == null) {
			throw new IllegalArgumentException("Null has no type. You must pass an Object");
		} else if (type instanceof Class<?>) {
			clazz = (Class<?>) type;
		} else {
			clazz = type.getClass();
		}
		return clazz;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByNamedQuery(final String namedQueryName) {
		return getEntityManager().createNamedQuery(namedQueryName).getResultList();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByNamedQuery(final String namedQueryName, final Object... params) {
		Query query = getEntityManager().createNamedQuery(namedQueryName);
		int i = 1;
		for (Object p : params) {
			query.setParameter(i++, p);
		}
		return query.getResultList();
	}

	
	public <T> List<T> findAll(final Class<T> type) {
		CriteriaQuery<T> query = query(type);
		query.from(type);
		return getEntityManager().createQuery(query).getResultList();
	}

	public <T> List<T> findAll(final Class<T> type, Order... orderBy) {
		CriteriaQuery<T> query = query(type);
		query.from(type);
		query.orderBy(orderBy);
		return getEntityManager().createQuery(query).getResultList();
	}

	protected <T> CriteriaQuery<T> query(final Class<T> type) {
		return getEntityManager().getCriteriaBuilder().createQuery(type);
	}

	@SuppressWarnings("unchecked")
	public <T> T findUniqueByNamedQuery(final String namedQueryName) throws NoResultException {
		return (T) getEntityManager().createNamedQuery(namedQueryName).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public <T> T findUniqueByNamedQuery(final String namedQueryName, final Object... params) throws NoResultException {
		Query query = getEntityManager().createNamedQuery(namedQueryName);
		int i = 1;
		for (Object p : params) {
			query.setParameter(i++, p);
		}
		return (T) query.getSingleResult();
	}

	public <T> int count(Class<T> type) {
		CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
		javax.persistence.criteria.Root<T> rt = cq.from(type);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> type, int firstResult, int maxResults) {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(type);
		cq.select(cq.from(type));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(maxResults);
		q.setFirstResult(firstResult);
		return q.getResultList();
	}

	public <T> boolean isManaged(T instance) {
		return instance != null && getEntityManager().contains(instance);
	}

}
