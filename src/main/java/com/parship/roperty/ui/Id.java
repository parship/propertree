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

/**
 * @author mfinsterwalder
 * @since 2013-08-13 10:33
 */
public class Id implements Comparable<Id> {
	public final String id;

	public Id(final String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		int lastIndex = id.lastIndexOf('/');
		if (lastIndex == -1) {
			return id;
		}
		return id.substring(lastIndex + 1);
	}

	@Override
	public int compareTo(final Id other) {
		return id.compareTo(other.id);
	}
}
