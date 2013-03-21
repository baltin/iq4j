package com.node.core.persistence.service;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.jboss.seam.international.status.Messages;

import com.iq4j.utils.ClassUtils;
import com.node.core.persistence.api.Entity;
import com.node.core.persistence.qualifiers.EntityCreated;
import com.node.core.persistence.qualifiers.EntityDeleted;
import com.node.core.persistence.qualifiers.EntityUpdated;

@Model
public class PersistenceMessages {

	@Inject
	protected Messages messages;

	protected void message(String messageBundle, Entity<?> entity) {
		messages.info(messageBundle, ClassUtils.getHumanReadableClassName(entity.getClass()));
	}

	public void persistedMessage(@EntityCreated Entity<?> entity) {
		message("{0} successfully created..}", entity);
	}

	public void updatedMessage(@EntityUpdated Entity<?> entity) {
		message("{0} successfully updated.. }", entity);
	}

	public void removedMessage(@EntityDeleted Entity<?> entity) {
		message("{0} successfully deleted..", entity);
	}
	
}
