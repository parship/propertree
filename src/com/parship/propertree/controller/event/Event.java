package com.parship.propertree.controller.event;

import com.parship.propertree.biz.Action;
import com.parship.propertree.biz.ActionResult;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

public interface Event {
	
	public void execute();
		
	public Component getResultComponent();
	
	public EventTarget getTarget();
	
	public void setTarget(EventTarget target);
	
	public String getName();
	
	public Action getAction();
	
	public void addEventParameter(String key, String value);
	
	public ActionResult getActionResult();
	
	public Notification getSuccessNotification();
	
	public Notification getErrorNotification();
	
}
