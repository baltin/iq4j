package com.iq4j.persistence.service;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.jboss.seam.persistence.FlushModeManager;
import org.jboss.seam.persistence.FlushModeType;
import org.jboss.solder.servlet.event.Initialized;

public class FlushModeSetup {

	@Inject
	FlushModeManager flushModeManager;
	
	public void setup(@Observes @Initialized ServletContext ctx) {
		flushModeManager.setFlushModeType(FlushModeType.MANUAL);
	}
	
}
