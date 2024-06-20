package com.santhosh.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/articles";
	private final String USERNAME = "root";
	private final String PASSWORD = "sandy";
	private static DBConnection database = null;
	private Connection connection = null;

	// While Creating Instance Database Connection Is been Establishes
	private DBConnection() {
		makeConnection();
	}

	// Is Used to Make Connection to Database
	private void makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connected to the database!");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found!");
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
		}
	}

	// Getting Instance of Current Class or Create a New Instance
	public static DBConnection getInstance() {
		if (database == null) {
			database = new DBConnection();
		}

		return database;
	}

	// Getters and Setters
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}
}
