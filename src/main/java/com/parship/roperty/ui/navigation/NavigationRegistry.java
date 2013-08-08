
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

package com.parship.roperty.ui.navigation;

import com.vaadin.navigator.Navigator;

import java.util.HashMap;
import java.util.Map;


/**
 * TODO
 *
 * @author marc
 *         Since 23.07.2013
 */
public class NavigationRegistry {

	private static Map<Frames, Navigator> navigators = new HashMap<Frames, Navigator>();

	public static Navigator getNavigator(Frames frame) throws Exception {
		Navigator result = navigators.get(frame);

		if (result == null) {
			throw new Exception("No Navigator found for frame " + frame);
		}

		return result;
	}

	public static void registerNavigator(Frames frame, Navigator navigator) {
		navigators.put(frame, navigator);
	}

}
