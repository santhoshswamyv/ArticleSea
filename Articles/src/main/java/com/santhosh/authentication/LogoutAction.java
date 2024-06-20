package com.santhosh.authentication;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport {

	@Override
	public String execute() {

		// Removing the Details of the User in Current Session
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session != null) {
			session.clear();
		}
		return SUCCESS;
	}
}
