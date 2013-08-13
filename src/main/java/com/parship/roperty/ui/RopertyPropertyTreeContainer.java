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
import com.parship.roperty.Roperty;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author mfinsterwalder
 * @since 2013-08-13 08:14
 */
public class RopertyPropertyTreeContainer implements Container.Hierarchical {

	private Map<Id, TreeContainerEntry> treeMap = new TreeMap<>();

	public RopertyPropertyTreeContainer(Roperty roperty) {
		createTree(roperty);
	}

	private void createTree(final Roperty roperty) {
		for (Map.Entry<String, KeyValues> entry : roperty.getKeyValues().entrySet()) {
			Id entryKey = new Id(entry.getKey());
			TreeContainerEntry currentEntry = getTreeContainerEntry(entryKey);
			currentEntry.setKeyValues(entry.getValue());
		}
	}

	private TreeContainerEntry getTreeContainerEntry(Id id) {
		String entryKey = id.id;
		if (entryKey.startsWith("/"))
			entryKey = entryKey.substring(1);
		String[] splitKey = entryKey.split("/");
		Map<Id, TreeContainerEntry> currentNode = treeMap;
		TreeContainerEntry currentEntry = null;
		StringBuilder keyPrefix = new StringBuilder();
		for (String key : splitKey) {
			keyPrefix.append(key);
			Id keyPrefixNow = new Id(keyPrefix.toString());
			currentEntry = currentNode.get(keyPrefixNow);
			if (currentEntry == null) {
				currentEntry = new TreeContainerEntry(keyPrefixNow);
				currentNode.put(keyPrefixNow, currentEntry);
			}
			currentNode = currentEntry.getChildren();
			keyPrefix.append("/");
		}
		return currentEntry;
	}

	@Override
	public Collection<?> getChildren(final Object itemId) {
		return getTreeContainerEntry((Id)itemId).getChildren().keySet();
	}

	@Override
	public Object getParent(final Object itemId) {
		return stripLast((Id)itemId);
	}

	private Id stripLast(final Id itemId) {
		int lastIndex = itemId.id.lastIndexOf('/');
		if (lastIndex == -1) {
			return null;
		}
		return new Id(itemId.id.substring(1, lastIndex));
	}

	@Override
	public Collection<?> rootItemIds() {
		return treeMap.keySet();
	}

	@Override
	public boolean setParent(final Object itemId, final Object newParentId) throws UnsupportedOperationException {
		return false; // TODO implement
	}

	@Override
	public boolean areChildrenAllowed(final Object itemId) {
		return hasChildren(itemId);
	}

	@Override
	public boolean setChildrenAllowed(final Object itemId, final boolean areChildrenAllowed) throws UnsupportedOperationException {
		return false; // TODO implement
	}

	@Override
	public boolean isRoot(final Object itemId) {
		return !((Id)itemId).id.contains("/");
	}

	@Override
	public boolean hasChildren(final Object itemId) {
		return getTreeContainerEntry((Id)itemId).hasChildren();
	}

	@Override
	public Item getItem(final Object itemId) {
		return null;
	}

	@Override
	public Collection<?> getContainerPropertyIds() {
		return null; // TODO implement
	}

	@Override
	public Collection<?> getItemIds() {
		return null; // TODO implement
	}

	@Override
	public Property getContainerProperty(final Object itemId, final Object propertyId) {
		return null; // TODO implement
	}

	@Override
	public Class<?> getType(final Object propertyId) {
		return null; // TODO implement
	}

	@Override
	public int size() {
		return 0; // TODO implement
	}

	@Override
	public boolean containsId(final Object itemId) {
		String entryKey = ((Id)itemId).id;
		if (entryKey.startsWith("/"))
			entryKey = entryKey.substring(1);
		String[] splitKey = entryKey.split("/");
		Map<Id, TreeContainerEntry> currentNode = treeMap;
		TreeContainerEntry currentEntry = null;
		StringBuilder keyPrefix = new StringBuilder();
		for (String key : splitKey) {
			keyPrefix.append(key);
			Id keyPrefixNow = new Id(keyPrefix.toString());
			currentEntry = currentNode.get(keyPrefixNow);
			if (currentEntry == null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Item addItem(final Object itemId) throws UnsupportedOperationException {
		return null; // TODO implement
	}

	@Override
	public Object addItem() throws UnsupportedOperationException {
		return null; // TODO implement
	}

	@Override
	public boolean removeItem(final Object itemId) throws UnsupportedOperationException {
		return false; // TODO implement
	}

	@Override
	public boolean addContainerProperty(final Object propertyId, final Class<?> type, final Object defaultValue) throws UnsupportedOperationException {
		return false; // TODO implement
	}

	@Override
	public boolean removeContainerProperty(final Object propertyId) throws UnsupportedOperationException {
		return false; // TODO implement
	}

	@Override
	public boolean removeAllItems() throws UnsupportedOperationException {
		treeMap.clear();
		return true;
	}
}
