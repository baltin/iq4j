package com.iq4j.persistence.service;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.jboss.seam.international.status.Messages;

import com.iq4j.persistence.api.Entity;
import com.iq4j.persistence.qualifiers.EntityCreated;
import com.iq4j.persistence.qualifiers.EntityDeleted;
import com.iq4j.persistence.qualifiers.EntityUpdated;
import com.iq4j.utils.ClassUtils;

@Model
public class PersistenceMessages {

	@Inject
	protected Messages messages;

	protected void message(String messageBundle, Entity<?> entity) {
		messages.info(messageBundle, ClassUtils.getHumanReadableClassName(entity.getClass()));
	}

	public void persistedMessage(@Observes @EntityCreated Entity<?> entity) {
		message("{0} successfully created..", entity);
	}

	public void updatedMessage(@Observes @EntityUpdated Entity<?> entity) {
		message("{0} successfully updated.. ", entity);
	}

	public void removedMessage(@Observes @EntityDeleted Entity<?> entity) {
		message("{0} successfully deleted..", entity);
	}
	
}
