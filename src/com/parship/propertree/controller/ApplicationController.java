package com.parship.propertree.controller;

import com.parship.propertree.controller.event.Event;
import com.vaadin.ui.UI;

public interface ApplicationController {

	public UI getEntryPoint();
	
	public void setEntryPoint (UI entryPoint);
	
	public void executeEvent(Event event);
	
}
