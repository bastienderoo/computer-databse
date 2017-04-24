package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;

public enum ConnectionDatabase {
<<<<<<< HEAD
    INSTANCE;
    /**
     * connection.
     */
    ConnectionDatabase() {

    }

    private static Connection connection;
    static ResourceBundle bundle = ResourceBundle.getBundle("config");
    private static String url = bundle.getString("database.url");
    private static String user = bundle.getString("database.user");
    private static String password = bundle.getString("database.password");

    /**
     * établissement de la connection.
     * 
     * @return connexion
     */
    public static Connection getInstance() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
            throw new ComputerDatabaseDAOException("Connection failed");
        }

    }

    /**
     * fermeture de la connection.
     * 
     * @return fermeture connexion
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
=======
	INSTANCE;

	private ConnectionDatabase() {

	}

	private static Connection connection;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
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
>>>>>>> 73e0817a3d35b4bced2c74c09de8d96d764a7303

}
