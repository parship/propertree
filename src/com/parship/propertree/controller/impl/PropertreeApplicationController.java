package com.parship.propertree.controller.impl;

import com.parship.propertree.controller.ApplicationController;
import com.parship.propertree.controller.event.Event;
import com.parship.propertree.controller.event.EventTarget;
import com.parship.propertree.ui.PropertreeUI;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class PropertreeApplicationController implements ApplicationController {

	//Singleton - SORRY!
	private static PropertreeApplicationController instance;
	
	private PropertreeUI entryPoint;
	
	public PropertreeApplicationController() {
		instance = this;
	}
	
	public PropertreeApplicationController(PropertreeUI entryPoint) {
		this.setEntryPoint(entryPoint);
		instance = this;
	}
	
	@Override
	public UI getEntryPoint() {
		return this.entryPoint;
	}

	@Override
	public void setEntryPoint(UI entryPoint) {
		this.entryPoint = (PropertreeUI)entryPoint;
	}

	@Override
	public void executeEvent(Event event) {
		event.execute();
		this.loadEventResult(event);
		
		if (event.getActionResult().isSuccess() && event.getSuccessNotification() != null){
			event.getSuccessNotification().show(Page.getCurrent());
		}
		
		if (!event.getActionResult().isSuccess() && event.getErrorNotification() != null){
			event.getErrorNotification().show(Page.getCurrent());
		}
	}
	
	private void loadEventResult(Event e){
		
		if(e.getTarget().equals(EventTarget.MAIN_PANEL)){
			this.entryPoint.setContent(e.getResultComponent());
		}
		
		
	}

	public static PropertreeApplicationController getInstance() {
		if(instance == null) {
			instance = new PropertreeApplicationController();
		}
		
		return instance;
		
	}

}
