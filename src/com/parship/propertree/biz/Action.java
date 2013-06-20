package com.parship.propertree.biz;

import java.util.Map;

public interface Action {

	public String getName();
	
	public ActionResult execute(Map<String, String> parameters);
	
}
