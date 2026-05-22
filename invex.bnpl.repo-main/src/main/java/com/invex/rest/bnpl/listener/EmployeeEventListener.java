package com.invex.rest.bnpl.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.invex.rest.bnpl.entity.Employee;
//import com.invex.rest..bnpl.entity.Linea;

@Component
public class EmployeeEventListener {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeEventListener.class);

	@Async
	@EventListener
	public void handleEmployeeCreatedEvent(Employee event) {
		LOGGER.info("Employee {} created.", event);
	}
	
}
