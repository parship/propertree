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

import com.parship.roperty.KeyValues;

import java.util.Map;
import java.util.TreeMap;


/**
 * @author mfinsterwalder
 * @since 2013-08-13 08:51
 */
public class TreeContainerEntry {
	private Id key;
	private KeyValues keyValues;
	private Map<Id, TreeContainerEntry> children = new TreeMap<>();

	public TreeContainerEntry(final Id key) {
		this.key = key;
	}

	public Map<Id, TreeContainerEntry> getChildren() {
		return children;
	}

	public KeyValues getKeyValues() {
		return keyValues;
	}

	public void setKeyValues(KeyValues keyValues) {
		this.keyValues = keyValues;
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}
}
