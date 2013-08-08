
/*
 * Roperty - An advanced property management and retrival system
 * Copyright (C) 2013 PARSHIP GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
