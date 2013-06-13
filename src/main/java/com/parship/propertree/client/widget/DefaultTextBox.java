
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
package com.parship.propertree.client.widget;

import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO
 *
 * @author marc
 *
 * Since 13.06.2013
 */
public class DefaultTextBox extends TextBox implements FocusListener { 
    String defaultText; 
    boolean defaultTextMode = false; 

    public DefaultTextBox(String defaultText) { 
            setDefaultText(defaultText); 
            setDefaultTextMode(); 
            addFocusListener(this); 
    } 

    public String getDefaultText() { 
            return defaultText; 
    } 

    public String getText() { 
            if (!defaultTextMode) { 
                    return super.getText(); 
            } else { 
                    return ""; 
            } 
    } 

    public void onFocus(Widget sender) { 
            if (defaultTextMode) { 
                    setNormalTextMode(); 
            } 
    } 

    public void onLostFocus(Widget sender) { 
            if (getText().length() == 0) { 
                    setDefaultTextMode(); 
            } 
    } 

    public void setDefaultText(String defaultText) { 
            this.defaultText = defaultText; 
            if (defaultTextMode) { 
                    setDefaultTextMode();        // Refresh 
            } 
    } 

    void setDefaultTextMode() { 
            assert super.getText().length() == 0; 
            super.setText(defaultText); 
            addStyleDependentName("default"); 
            defaultTextMode = true; 
    } 

    void setNormalTextMode() { 
            assert super.getText().length() != 0; 
            super.setText(""); 
            removeStyleDependentName("default"); 
            defaultTextMode = false; 
    } 

    public void setText(String text) { 
            super.setText(text); 
            if (text.length() == 0) { 
                    setDefaultTextMode(); 
            } else { 
                    setNormalTextMode(); 
            } 
    } 
} 