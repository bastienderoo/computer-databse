package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.excilys.computerdatabase.model.DateException;

public class DataBase {

	private static Connection conn;

	private DataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver O.K.");
			String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("Connexion effective !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteComputer() {

	}

	public static void addComputer(String name, String dateIntroduced, String dateDiscontinued, int iDCompany) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = new Date(df.parse(dateIntroduced).getTime());
			d2 = new Date(df.parse(dateDiscontinued).getTime());
			if (d2.after(d1)) {

				Statement state = conn.createStatement();

				state.executeUpdate("INSERT INTO computer(name,introduced,discontinued,company_id) Values('" + name
						+ "','" + dateIntroduced + "','" + dateDiscontinued + "','" + iDCompany + "')");
				state.close();

			} else {
				throw new DateException("La date d'introduction doit être antérieure à la date d'arret");
			}
		} catch (DateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
