package com.santhosh.service;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.santhosh.dao.UserDao;
import com.santhosh.model.User;
import com.santhosh.utils.Constants;

@SuppressWarnings("serial")
public class ProfileService extends ActionSupport {
	private String username;
	private String emailid;
	private String contact;
	private String password;
	private String errorMessage;
	private User user;
	private UserDao userDao = new UserDao();

	@Override
	public String execute() {

		if (!Constants.checkAuthentication()) {
			return Constants.UNAUTH;
		}

		retrieveProfile();
		String status = Constants.ERROR;
		if (user != null) {
			status = userDao.updateProfile(user.getId(), username, emailid, contact);
		}
		setErrorMessage(userDao.getErrorMessage());
		if (errorMessage != null) {
			addActionError(errorMessage);
		} else {
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("userEmail", emailid);
			session.put("userName", user.getUsername());
		}
		return status;
	}

	public String retrieveProfile() {

		if (!Constants.checkAuthentication()) {
			return Constants.UNAUTH;
		}

		Map<String, Object> session = ActionContext.getContext().getSession();
		setUser(userDao.retrieveDetails((String) session.get("userEmail")));
		if (user != null) {
			return SUCCESS;
		}
		setErrorMessage(userDao.getErrorMessage());
		if (errorMessage != null) {
			addActionError(errorMessage);
		}
		return ERROR;
	}

	public String resetPassword() {

		if (!Constants.checkAuthentication()) {
			return Constants.UNAUTH;
		}

		String status = userDao.changePassword(emailid, password);
		setErrorMessage(userDao.getErrorMessage());
		addActionError(errorMessage);
		return status;
	}

	public String redirectProfile() {

		if (!Constants.checkAuthentication()) {
			return Constants.UNAUTH;
		}

		retrieveProfile();
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
