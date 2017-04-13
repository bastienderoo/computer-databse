package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;

public enum ConnectionDatabase {
	INSTANCE;

	private ConnectionDatabase() {

	}

	private static Connection connection;
	static ResourceBundle bundle = ResourceBundle.getBundle("com.excilys.computerdatabase.properties.config");
	private static String url = bundle.getString("database.url");
	private static String user = bundle.getString("database.user");
	private static String password = bundle.getString("database.password");

	/**
	 * établissement de la connection
	 * 
	 * @return
	 */
	public static Connection getInstance() {

		try {

			return DriverManager.getConnection(url, user, password);
		

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerDatabaseDAOException("conn db",e);
		}
		
	}
		

	/**
	 * fermeture de la connection
	 * 
	 * @return
	 */
	public static Connection closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
