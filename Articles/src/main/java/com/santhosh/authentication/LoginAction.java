package com.santhosh.authentication;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.santhosh.dao.UserDao;
import com.santhosh.model.User;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
	private String emailid;
	private String password;
	private String errorMessage;
	private User user;
	private UserDao userDao = new UserDao();

	@Override
	public String execute() {

		// Checking for Inputs if not Redirecting to Login Page
		if (emailid == null || password == null) {
			return ERROR;
		}

		// Validating the User with Required Details
		String validationResult = userDao.isUserValidated(emailid, password);

		// If User is Validates
		if (validationResult.equals(SUCCESS)) {

			// Retrieving details and Update it to User
			setUser(userDao.retrieveDetails(emailid));

			// Getting the Active Session
			Map<String, Object> session = ActionContext.getContext().getSession();

			// Adding the Required Details in the Session
			session.put("userEmail", emailid);
			session.put("userName", user.getUsername());
			session.put("userId", user.getId());

			return SUCCESS;
		} else {
			// Getting Error Message inCase of Error or User not Found
			setErrorMessage(userDao.getErrorMessage());
			addActionError(errorMessage);
			return validationResult;
		}
	}

	// Redirect the Webpage for Login Page
	public String redirectLogin() {
		return SUCCESS;
	}

	// Redirect the Webpage for Resetting Password
	public String redirectResetPassword() {
		return SUCCESS;
	}

	// Getters and Setters
	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
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