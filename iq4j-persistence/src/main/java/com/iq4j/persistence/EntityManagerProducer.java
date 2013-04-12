package com.iq4j.persistence;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.FlushMode;
import org.hibernate.Session;

public class EntityManagerProducer {

	@PersistenceUnit
	EntityManagerFactory entityManagerFactory;

	private EntityManager entityManager;
	
	@PostConstruct
	protected void init() {
		entityManager = entityManagerFactory.createEntityManager();
		Session session = (Session) entityManager.getDelegate();
		session.setFlushMode(FlushMode.MANUAL);
		
	}

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() throws Exception {		
		return new EntityManagerTxEnlistDecorator(entityManager);
	}
	
	public void dispose(@Disposes  EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
	
}
