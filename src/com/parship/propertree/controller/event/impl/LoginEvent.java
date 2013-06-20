package com.parship.propertree.controller.event.impl;

import java.util.HashMap;
import java.util.Map;

import com.parship.propertree.biz.Action;
import com.parship.propertree.biz.ActionResult;
import com.parship.propertree.biz.impl.LoginAction;
import com.parship.propertree.controller.event.Event;
import com.parship.propertree.controller.event.EventTarget;
import com.parship.propertree.ui.LoginScreenUI;
import com.parship.propertree.ui.MainPanelUI;
import com.vaadin.server.Page;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

public class LoginEvent implements Event {
	
	private Component result;
	private Action action;
	private Map<String, String> parameters;
	private ActionResult actionResult;
	
	public LoginEvent() {
		this.parameters = new HashMap<String, String>();
		this.action = new LoginAction();
	}
	
	@Override
	public void execute() {
		
		// Execute Action
		ActionResult actionResult = this.action.execute(parameters);
		this.actionResult = actionResult;
		
		if(actionResult.isSuccess()){
			this.result = new MainPanelUI();
		} else {
			LoginScreenUI loginScreen = new LoginScreenUI();
			this.result = loginScreen;
		}
	}

	@Override
	public Component getResultComponent() {
		return this.result;
	}

	@Override
	public EventTarget getTarget() {
		return EventTarget.MAIN_PANEL;
	}

	@Override
	public void setTarget(EventTarget target) {
		// Nothing to do
	}

	@Override
	public String getName() {
		return "LOGIN";
	}

	@Override
	public Action getAction() {
		return this.action;
	}

	@Override
	public void addEventParameter(String key, String value) {
		this.parameters.put(key, value);
	}

	public ActionResult getActionResult() {
		return actionResult;
	}

	@Override
	public Notification getSuccessNotification() {
		if(this.getActionResult() != null && this.getActionResult().getErrorCode() != null) {
			Notification not = new Notification("Successfully logged in!", Notification.TYPE_TRAY_NOTIFICATION);
			not.setDelayMsec(3000);
			return not;
		}
		return null;
	}

	@Override
	public Notification getErrorNotification() {
		if(this.getActionResult() != null && this.getActionResult().getErrorCode() != null) {
			Notification not = new Notification(this.getActionResult().getErrorCode().toString(), Notification.TYPE_ERROR_MESSAGE);
			not.setDelayMsec(1000);
			return not;
		}
		return null;
	}
}
