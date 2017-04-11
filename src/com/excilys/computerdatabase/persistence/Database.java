package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Connection conn;
	private String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";


	/**
	 * connection à la database existante avec url et ID
	 */
	private Database(String user, String pwd) {
		try {

			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * établissement de la connection
	 * @return
	 */
	public static Connection getInstance(String user, String pwd) {
		if (conn == null) {
			new Database(user,pwd);
		}
		return conn;
	}

	/**
	 * fermeture de la connection
	 * @return
	 */
	public static Connection closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}