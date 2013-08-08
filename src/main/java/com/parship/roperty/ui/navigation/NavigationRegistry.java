
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
