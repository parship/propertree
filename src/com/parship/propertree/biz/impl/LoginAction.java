package com.parship.propertree.biz.impl;

import java.util.Map;

import com.parship.propertree.biz.Action;
import com.parship.propertree.biz.ActionResult;
import com.parship.propertree.common.ErrorCode;

public class LoginAction implements Action {

	@Override
	public String getName() {
		return "LOGIN_ACTION";
	}

	@Override
	public ActionResult execute(Map<String, String> parameters) {
		ActionResult result = new ActionResult(this);
		String user = parameters.get("user");
		String pass = parameters.get("pass");
		
		if(user == null || pass == null){
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.LOGIN_FAILED);
			return result;
		}
		
		// TODO Just a test. A user service should be implemented here
		if(user.equals("marc") || pass.equals("test")) {
			result.setErrorCode(ErrorCode.SUCCESS);
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.LOGIN_FAILED);
		}
		
		return result;
	}
	
}
