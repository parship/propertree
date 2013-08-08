
package com.parship.roperty.ui.command;

import com.parship.roperty.ui.navigation.Frames;
import com.parship.roperty.ui.navigation.NavigationRegistry;
import com.parship.roperty.ui.navigation.Views;
import com.parship.roperty.ui.permission.UserManager;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;


/**
 * TODO
 *
 * @author marc
 *         Since 18.07.2013
 */
public class LogoutCommand implements Command {

	private static final long serialVersionUID = 1L;

	@Override
	public void menuSelected(MenuItem selectedItem) {

		UserManager.logout();
		try {
			Navigator navigator = NavigationRegistry.getNavigator(Frames.MAIN);
			navigator.navigateTo(Views.LOGIN.name());
			Notification.show("Successfully logged out", Notification.Type.TRAY_NOTIFICATION);
		} catch (Exception e) {
			Notification.show(e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
		}
	}
}
