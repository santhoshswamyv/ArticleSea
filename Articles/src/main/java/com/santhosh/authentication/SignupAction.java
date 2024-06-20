package com.santhosh.authentication;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.santhosh.dao.UserDao;
import com.santhosh.model.User;

@SuppressWarnings("serial")
public class SignupAction extends ActionSupport {
	private String username;
	private String emailid;
	private String contact;
	private String password;
	private String errorMessage;
	private User user;
	private UserDao userDao = new UserDao();

	@Override
	public String execute() {
		// Checking for Inputs if not Redirecting to Signup Page
		if (username == null || emailid == null || contact == null || password == null) {
			return ERROR;
		}

		// Trying to Create a Account for new User
		boolean signUpSuccess = userDao.doSignUp(username, emailid, contact, password);

		// If Account Created
		if (signUpSuccess) {
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
			// Getting Error Message inCase of Error or Account is Not Created
			errorMessage = userDao.getErrorMessage();
			addActionError(errorMessage);
			return ERROR;
		}
	}

	// Redirect the Webpage for Signup Page
	public String redirectSignup() {
		return SUCCESS;
	}

	// Getters and Setters for username, email, contact, password, errorMessage
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String email) {
		this.emailid = email;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
