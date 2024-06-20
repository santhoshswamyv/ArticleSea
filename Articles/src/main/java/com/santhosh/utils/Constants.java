package com.santhosh.utils;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Constants {
	public static final String SUCCESS = "success";
	public static final String INCPASS = "incpwd";
	public static final String ERROR = "error";
	public static final String UNXERROR = "unxerror";
	public static final String UNAUTH = "unauth";

	// Checking if the User is Authorized or Not
	public static boolean checkAuthentication() {
		Map<String, Object> session = ActionContext.getContext().getSession();

		if (session.get("userEmail") == null) {
			return false;
		}

		return true;
	}
}
