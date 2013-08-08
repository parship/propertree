
package com.parship.roperty.ui.permission;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.parship.roperty.ui.persistence.entity.User;
import com.parship.roperty.ui.LoginUI;
import com.parship.roperty.ui.NavigationViewUI;
import com.parship.roperty.ui.WorkBenchSplitViewUI;
import com.vaadin.navigator.View;

/**
 * TODO
 *
 * @author marc
 *
 * Since 24.07.2013
 */
public class UIPermissionManager {
    
    private static UIPermissionManager instance;
    
    private Map<Class<? extends View>, List<Permissions>> permissionMap;
    
    private UIPermissionManager() {
	this.init();
	instance = this;
    }
    
    public void init() {
	
	this.permissionMap = new HashMap<Class<? extends View>, List<Permissions>>();
	
	/* TODO Make configurable!
	 * Initialize UI Permissions
	 */
	
	//LOGINUI
	List<Permissions> loginUiPermissions = new LinkedList<Permissions>();
	loginUiPermissions.add(Permissions.USERADMIN);
	this.permissionMap.put(LoginUI.class, loginUiPermissions);
	
	//NAVIGATORVIEWUI
	List<Permissions> navigatorViewUiPermissions = new LinkedList<Permissions>();
	navigatorViewUiPermissions.add(Permissions.USERADMIN);
	this.permissionMap.put(NavigationViewUI.class, navigatorViewUiPermissions);
	
	//WORKBENCHSPLITVIEWUI
	List<Permissions> workbenchSplitViewUiPermissions = new LinkedList<Permissions>();
	workbenchSplitViewUiPermissions.add(Permissions.USERADMIN);
	this.permissionMap.put(WorkBenchSplitViewUI.class, workbenchSplitViewUiPermissions);
    }
    
    public boolean hasPermission(User user, Class<? extends View> view) {
	
	if(user == null) {
	    return false;
	}
	
	List<Permissions> permissions = this.permissionMap.get(view);
	List <Permissions> userPermissions = user.getPermissionList();
	
	if(userPermissions.contains(Permissions.SUPERUSER)) {
	    return true;
	}
	
	for(Permissions perm : permissions) {
	    if(userPermissions.contains(perm)) {
		return true;
	    }
	}
	
	return false;
	
    }
    
    public static UIPermissionManager getInstance() {
	if(instance == null) {
	    instance = new UIPermissionManager();
	}
	
	return instance;
    }
    
}
