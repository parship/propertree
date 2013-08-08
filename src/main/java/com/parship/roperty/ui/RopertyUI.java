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

package com.parship.roperty.ui;

import com.parship.roperty.ui.navigation.Frames;
import com.parship.roperty.ui.navigation.NavigationRegistry;
import com.parship.roperty.ui.navigation.RopertyUiMainNavigator;
import com.parship.roperty.ui.navigation.Views;
import com.parship.roperty.ui.permission.UserManager;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;


/**
 * TODO
 *
 * @author marc
 *         Since 09.07.2013
 */
@Theme("reindeer")
@SuppressWarnings("serial")
public class RopertyUI extends UI {

	@Override
	protected void init(VaadinRequest request) {

		this.getPage().setTitle("Roperty - The UI!");

		RopertyUiMainNavigator navigator = new RopertyUiMainNavigator(this, this);
		NavigationRegistry.registerNavigator(Frames.MAIN, navigator);

		if (UserManager.isLoggedIn()) {
			navigator.navigateTo(Views.MAIN.name());
		} else {
			navigator.navigateTo(Views.LOGIN.name());
		}
	}
}