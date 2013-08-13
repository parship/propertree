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

import com.parship.roperty.Roperty;
import org.junit.Test;


/**
 * @author mfinsterwalder
 * @since 2013-08-13 09:07
 */
public class RopertyPropertyTreeContainerTest {

	private Roperty r = new Roperty();

	@Test
	public void createHierarchy() {
		r.set("/key1", "value_1", "desc");
		r.set("/key1/subkey1", "value_1_1", "desc");
		r.set("/key1/subkey2", "value_1_2", "desc");
		r.set("/key2", "value_2", "desc");
		r.set("/key2/subkey1", "value_1_1", "desc");
		r.set("/key2/subkey2", "value_1_2", "desc");
		r.set("plain", "value", "desc");
		RopertyPropertyTreeContainer container = new RopertyPropertyTreeContainer(r);
	}
}
