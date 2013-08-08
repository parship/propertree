
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

import com.parship.roperty.ui.persistence.entity.User;
import com.parship.roperty.ui.persistence.service.RopertyUiSession;
import com.parship.roperty.ui.persistence.service.UserService;
import com.parship.roperty.ui.util.PasswordService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


/**
 * TODO
 *
 * @author marc
 *         Since 16.07.2013
 */
public class UserManager {

	public static boolean isLoggedIn() {
		return RopertyUiSession.isLoggedIn();
	}

	public static User getCurrentUser() {
		return RopertyUiSession.getUser();
	}

	public static boolean login(String username, String password, boolean rememberMe) {
		PasswordService passwordService = new PasswordService();
		UserService userService = new UserService();

		User user = userService.getUserByUserName(username);

		if (user == null || !user.getActive()) {
			return false;
		} else {
			boolean authenticated;

			try {
				authenticated = passwordService.authenticate(password, user.getPassword(), user.getSalt());
			} catch (Exception e) {
				return false;
			}

			if (authenticated) {
				RopertyUiSession.login(user, rememberMe);
				return true;
			}
			return false;
		}
	}

	public static void logout() {
		RopertyUiSession.logout();
	}

	public static boolean createUser(String username, String password) {
		User user = new User();
		UserService userService = new UserService();
		byte[] encryptedPassword;
		byte[] salt;
		PasswordService passwordService = new PasswordService();

		user.setUsername(username);
		try {
			salt = passwordService.generateSalt();
		} catch (NoSuchAlgorithmException e) {
			// TODO
			return false;
		}

		try {
			encryptedPassword = passwordService.getEncryptedPassword(password, salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO
			return false;
		} catch (InvalidKeySpecException e) {
			// TODO
			return false;
		}

		user.setSalt(salt);
		user.setPassword(encryptedPassword);
		user.setActive(true);

		return userService.storeUser(user);
	}
}
