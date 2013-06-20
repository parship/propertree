package com.parship.propertree.ui;

import com.parship.propertree.controller.impl.PropertreeApplicationController;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class PropertreeUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		final MainPanelUI layout = new MainPanelUI();
		
		new PropertreeApplicationController(this);
		setContent(new LoginScreenUI());
		// setContent(layout);
	}

}