package com.jakecrane.p2pchat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static final String DRIVER = System.getenv("JDBC_DRIVER");
	private static final String CONNECTION = System.getenv("DATABASE_CONNECTION");
	private static final String USERNAME = System.getenv("DATABASE_USERNAME");
	private static final String PASSWORD = System.getenv("DATABASE_PASSWORD");

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Missing JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		try {
			return DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
