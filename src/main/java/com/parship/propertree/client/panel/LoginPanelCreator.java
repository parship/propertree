
/*
 * Propertree - A webapplication to administer roperty backed apps
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
package com.parship.propertree.client.panel;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.parship.propertree.client.widget.DefaultTextBox;

/**
 * TODO
 *
 * @author marc
 *
 * Since 13.06.2013
 */
public class LoginPanelCreator {
    
    private Panel panel;
    private Panel credentialPanel;
    
    private Label loginLabel = new Label();
    private TextBox usernameBox = new TextBox();
    private PasswordTextBox passwordBox = new PasswordTextBox();
    private Hyperlink loginButton = new Hyperlink();
    
    public LoginPanelCreator(){
	this.panel = new VerticalPanel();
	this.credentialPanel = new HorizontalPanel();
	this.loginLabel = new Label();
	this.usernameBox = new DefaultTextBox("Username");
	this.passwordBox = new PasswordTextBox();
	this.loginButton = new Hyperlink();
	this.assembleLoginPanel();

    }
    
    private void assembleLoginPanel() {

	// TODO Get text from property files
	this.loginLabel.setText("Please enter your credentials");
	this.loginLabel.addStyleName("loginLabel");
	
	this.usernameBox.setName("user");
	this.usernameBox.addStyleName("largeTextbox");
	
	this.passwordBox.setName("pass");
	this.passwordBox.addStyleName("largeTextbox");
	
	// TODO Get text from property files
	this.loginButton.setText("Login");
	this.loginButton.addStyleName("bigbutton");
	this.loginButton.addStyleName("centered");
	
	
	this.panel.add(this.loginLabel);
	this.credentialPanel.add(this.usernameBox);
	this.credentialPanel.add(this.passwordBox);
	this.credentialPanel.add(this.loginButton);
	
	this.credentialPanel.addStyleName("credentialPanel");
	this.panel.add(this.credentialPanel);
	
	this.panel.addStyleName("loginPanel");
	
    }

    public Panel getPanel() {
        return panel;
    }

}
