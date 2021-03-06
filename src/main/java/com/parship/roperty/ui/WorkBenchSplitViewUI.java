
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
import com.parship.roperty.ui.permission.UIPermissionManager;
import com.parship.roperty.ui.permission.UserManager;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Tree;


/**
 * TODO
 *
 * @author marc
 *         Since 22.07.2013
 */
public class WorkBenchSplitViewUI extends CustomComponent implements View {

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private HorizontalSplitPanel splitPanel;

	private static final long serialVersionUID = 1L;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public WorkBenchSplitViewUI() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		this.splitPanel.setSplitPosition(20);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		UIPermissionManager.getInstance().hasPermission(UserManager.getCurrentUser(), this.getClass());
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// splitPanel
		splitPanel = new HorizontalSplitPanel();
		splitPanel.setImmediate(false);
		splitPanel.setWidth("100.0%");
		splitPanel.setHeight("100.0%");
		mainLayout.addComponent(splitPanel,
			"top:0.0px;right:0.0px;bottom:0.0px;left:0.0px;");

		Roperty r = new Roperty();
		r.set("/key1", "value_1", "desc");
		r.set("/key1/subkey1", "value_1_1", "desc");
		r.set("/key1/subkey2", "value_1_2", "desc");
		r.set("/key1/subkey2/subsub1", "value_1_2_1", "desc");
		r.set("/key1/subkey2/subsub2", "value_1_2_2", "desc");
		r.set("/key1/subkey2/subsub2/subsubsub1", "value_1_2_2_1", "desc");
		r.set("/key2", "value_2", "desc");
		r.set("/key2/subkey1", "value_1_1", "desc");
		r.set("/key2/subkey2", "value_1_2", "desc");
		r.set("plain", "plainValue", "desc");
		r.set("plain/plainsub1", "plainValue_1", "desc");
		r.set("plain/plainsub2", "plainValue_2", "desc");
		r.set("plain/plainsub3", "plainValue_3", "desc");
		Tree tree = new Tree("Properties", new RopertyPropertyTreeContainer(r));
		splitPanel.setFirstComponent(tree);

		return mainLayout;
	}
}
