package com.iq4j.faces.listener;

import javax.enterprise.event.Observes;
import javax.faces.event.PhaseEvent;

public class DebugListener {

	public void allPhaseListener(@Observes PhaseEvent event) {
		System.out.println(event.getPhaseId());
	}
	

}
