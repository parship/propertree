
package com.parship.roperty.ui.navigation;

import com.parship.roperty.ui.ErrorUI;
import com.parship.roperty.ui.LoginUI;
import com.parship.roperty.ui.NavigationViewUI;
import com.vaadin.navigator.NavigationStateManager;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;

/**
 * TODO
 *
 * @author marc
 *
 * Since 19.07.2013
 */
public class RopertyUiMainNavigator extends Navigator {

    private static final long serialVersionUID = 1L;
    
    public RopertyUiMainNavigator(UI ui, NavigationStateManager stateManager,
	    ViewDisplay display) {
	super(ui, stateManager, display);
	this.registerViews();
    }

    public RopertyUiMainNavigator(UI ui, SingleComponentContainer container) {
	super(ui, container);
	this.registerViews();
    }

    public RopertyUiMainNavigator(UI ui, ViewDisplay display) {
	super(ui, display);
	this.registerViews();
    }

    public RopertyUiMainNavigator(UI ui, ComponentContainer container) {
	super(ui, container);
	this.registerViews();
    }
    
    private void registerViews() {
	this.addView(Views.ERROR.name(), ErrorUI.class);
	this.addView(Views.LOGIN.name(), LoginUI.class);
	this.addView(Views.MAIN.name(), NavigationViewUI.class);
    }
    
}
