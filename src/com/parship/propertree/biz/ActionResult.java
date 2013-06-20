package com.parship.propertree.biz;

import java.util.HashMap;
import java.util.Map;

import com.parship.propertree.common.ErrorCode;

public class ActionResult {
	
	private Action action;
	
	private boolean success;
	
	private ErrorCode errorCode;
	
	private Map<String, String> resultParameters;
	
	public ActionResult(Action a) {
		this.setAction(a);
		this.setSuccess(true);
		this.setErrorCode(ErrorCode.SUCCESS);
		this.resultParameters = new HashMap<String, String>();
	}

	public void addResultParameter(String key, String value){
		this.resultParameters.put(key, value);
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public Map<String, String> getResultParameters() {
		return resultParameters;
	}
	
}
