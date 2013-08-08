
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

package com.parship.roperty.ui.persistence.service;

import com.parship.roperty.ui.persistence.entity.User;
import com.vaadin.server.VaadinService;

import javax.servlet.http.Cookie;


/**
 * TODO
 *
 * @author marc
 *         Since 18.07.2013
 */
public class RopertyUiSession {

	public static void login(User user, boolean rememberMe) {
		VaadinService.getCurrentRequest().getWrappedSession().setAttribute("user", user);
		setLoginCookies(user, rememberMe);
	}

	public static void logout() {
		VaadinService.getCurrentRequest().getWrappedSession().removeAttribute("user");
		invalidateLoginCookies();
	}

	public static boolean isLoggedIn() {
		User user = (User)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("user");

		if (user != null) {
			return true;
		}

		Cookie cookie = getCookieByName("ClouTree.user");

		if (cookie != null && cookie.getValue() != null && cookie.getValue().equals("true")) {
			return true;
		}
		return false;

	}

	public static User getUser() {
		User user = (User)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("user");

		if (user != null) {
			return user;
		}
		return null;
	}

	public static String getPreviousUserName() {
		Cookie cookie = getCookieByName("ClouTree.user");

		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	public static String wasRememberMe() {
		Cookie cookie = getCookieByName("ClouTree.rememberMe");

		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	private static void setLoginCookies(User user, boolean rememberMe) {

		if (rememberMe) {
			createCookie("ClouTree.loggedIn", "true", 86400);
			createCookie("ClouTree.user", user.getUsername(), (86400 * 14));
			createCookie("ClouTree.rememberMe", "true", (86400 * 14));
		} else {
			invalidateAllCookies();
		}
	}

	private static void invalidateLoginCookies() {

		Cookie loginCookie = getCookieByName("ClouTree.loggedIn");

		if (loginCookie != null) {
			loginCookie.setValue("false");
			loginCookie.setMaxAge(0);
		}

	}

	private static void invalidateAllCookies() {
		Cookie[] cookies = VaadinService.getCurrentRequest()
			.getCookies();

		// Iterate to invalidate
		for (Cookie cookie : cookies) {
			if (cookie.getName().startsWith("ClouTree.")) {
				cookie.setValue(null);
				cookie.setMaxAge(0);
			}
		}
	}

	private static void createCookie(String key, String value, int ttl) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(ttl);
		cookie.setPath(VaadinService.getCurrentRequest().getContextPath());
		VaadinService.getCurrentResponse().addCookie(cookie);
	}

	private static Cookie getCookieByName(String name) {
		// Fetch all cookies from the request
		Cookie[] cookies = VaadinService.getCurrentRequest()
			.getCookies();

		// Iterate to find cookie by its name
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}
}
