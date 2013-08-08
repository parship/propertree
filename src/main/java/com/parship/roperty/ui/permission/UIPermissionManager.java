
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

package com.parship.roperty.ui.permission;

import com.parship.roperty.ui.LoginUI;
import com.parship.roperty.ui.NavigationViewUI;
import com.parship.roperty.ui.WorkBenchSplitViewUI;
import com.parship.roperty.ui.persistence.entity.User;
import com.vaadin.navigator.View;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * TODO
 *
 * @author marc
 *         Since 24.07.2013
 */
public class UIPermissionManager {

	private static UIPermissionManager instance = new UIPermissionManager();

	private Map<Class<? extends View>, List<Permissions>> permissionMap;

	private UIPermissionManager() {
		this.init();
	}

	public void init() {

		this.permissionMap = new HashMap<Class<? extends View>, List<Permissions>>();

		/* TODO Make configurable!
		 * Initialize UI Permissions
		 */

		//LOGINUI
		List<Permissions> loginUiPermissions = new LinkedList<Permissions>();
		loginUiPermissions.add(Permissions.USERADMIN);
		this.permissionMap.put(LoginUI.class, loginUiPermissions);

		//NAVIGATORVIEWUI
		List<Permissions> navigatorViewUiPermissions = new LinkedList<Permissions>();
		navigatorViewUiPermissions.add(Permissions.USERADMIN);
		this.permissionMap.put(NavigationViewUI.class, navigatorViewUiPermissions);

		//WORKBENCHSPLITVIEWUI
		List<Permissions> workbenchSplitViewUiPermissions = new LinkedList<Permissions>();
		workbenchSplitViewUiPermissions.add(Permissions.USERADMIN);
		this.permissionMap.put(WorkBenchSplitViewUI.class, workbenchSplitViewUiPermissions);
	}

	public boolean hasPermission(User user, Class<? extends View> view) {

		if (user == null) {
			return false;
		}

		List<Permissions> permissions = this.permissionMap.get(view);
		List<Permissions> userPermissions = user.getPermissionList();

		if (userPermissions.contains(Permissions.SUPERUSER)) {
			return true;
		}

		for (Permissions perm : permissions) {
			if (userPermissions.contains(perm)) {
				return true;
			}
		}

		return false;
	}

	public static UIPermissionManager getInstance() {
		return instance;
	}
}
