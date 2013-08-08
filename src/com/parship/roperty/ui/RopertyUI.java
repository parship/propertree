package com.parship.roperty.ui;

import com.parship.roperty.ui.permission.UserManager;
import com.parship.roperty.ui.navigation.RopertyUiMainNavigator;
import com.parship.roperty.ui.navigation.Frames;
import com.parship.roperty.ui.navigation.NavigationRegistry;
import com.parship.roperty.ui.navigation.Views;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * TODO
 *
 * @author marc
 *
 * Since 09.07.2013
 */
@Theme("reindeer")
@SuppressWarnings("serial")
public class RopertyUI extends UI {
    
	@Override
	protected void init(VaadinRequest request) {
	    
	    this.getPage().setTitle("ClouTree - Centralize your app!");
	    
	    RopertyUiMainNavigator navigator = new RopertyUiMainNavigator(this, this);
	    NavigationRegistry.registerNavigator(Frames.MAIN, navigator);
	    
	    if(UserManager.isLoggedIn()){
		navigator.navigateTo(Views.MAIN.name());
	    } else {
		navigator.navigateTo(Views.LOGIN.name());
	    }
	}

}