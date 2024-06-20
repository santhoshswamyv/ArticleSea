package com.santhosh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

import org.mindrot.jbcrypt.BCrypt;

import com.santhosh.model.User;
import com.santhosh.utils.Constants;
import com.santhosh.utils.DBConnection;

public class UserDao {
	private String errorMessage;

	private boolean isEmailRegistered(String emailid) {
		String query = "SELECT password FROM users WHERE email = ?";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emailid);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean doSignUp(String username, String emailid, String contact, String password) {

		if (isEmailRegistered(emailid)) {
			setErrorMessage("This email-id is already registered");
			return false;
		}

		String query = "INSERT INTO users (username, email, contact, password) VALUES (?, ?, ?, ?)";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			pstmt.setString(1, username);
			pstmt.setString(2, emailid);
			pstmt.setString(3, contact);
			pstmt.setString(4, hashedPassword);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorMessage("An unexpected error occurred during sign-up.");
		}
		return false;
	}

	public String isUserValidated(String emailid, String password) {
		String query = "SELECT password FROM users WHERE email = ?";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emailid);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String hashedPassword = rs.getString("password");
					if (BCrypt.checkpw(password, hashedPassword)) {
						return Constants.SUCCESS;
					} else {
						setErrorMessage("Incorrect password.");
						return Constants.INCPASS;
					}
				} else {
					setErrorMessage("This email is not registered.");
					return Constants.ERROR;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorMessage("An unexpected error occurred while checking the email registration.");
		}
		return Constants.UNXERROR;
	}

	public String changePassword(String emailid, String password) {
		String query = "SELECT * FROM users WHERE email = ?";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emailid);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
					query = "UPDATE users SET password = ? WHERE email = ?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, hashedPassword);
					pstmt.setString(2, emailid);
					if (pstmt.executeUpdate() > 0) {
						setErrorMessage("Password is Updated");
						return Constants.SUCCESS;
					} else {
						setErrorMessage("Password is not updated.");
						return Constants.ERROR;
					}
				} else {
					setErrorMessage("This email is not registered.");
					return Constants.ERROR;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorMessage("An unexpected error occurred .");
		}
		return Constants.UNXERROR;
	}

	public User retrieveDetails(String emailid) {
		User user = null;
		String query = "SELECT * FROM users WHERE email = ?";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emailid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setContact(rs.getString("contact"));
				Timestamp timestamp = rs.getTimestamp("created_at");
				LocalDate createdDate = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				user.setCreatedAt(createdDate);
			} else {
				setErrorMessage("User not Found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorMessage("An unexpected error occurred .");
		}
		return user;
	}

	public String updateProfile(int id, String username, String emailid, String contact) {
		String query = "UPDATE users SET username = ?, email = ?, contact = ? WHERE id = ?";
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, emailid);
			pstmt.setString(3, contact);
			pstmt.setInt(4, id);

			if (pstmt.executeUpdate() == 1) {
				return Constants.SUCCESS;
			} else {
				setErrorMessage("Profile update failed.");
				return Constants.ERROR;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorMessage("An unexpected error occurred .");
		}
		return Constants.UNXERROR;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
