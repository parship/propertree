
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
