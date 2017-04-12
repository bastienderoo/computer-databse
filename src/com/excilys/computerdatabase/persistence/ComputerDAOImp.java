package com.excilys.computerdatabase.persistence;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;

import com.excilys.computerdatabase.model.DateException;
import com.excilys.computerdatabase.model.Computer;

/**
 * Classe contenant les méthodes permettant d'effectuer les différentes actions
 * sur la list d'ordinateurs
 * 
 * @author excilys
 *
 */
public class ComputerDAOImp {

	private static final String DELETE_QUERY = "DELETE FROM computer WHERE id=?";
	private static final String ADD_QUERY = "INSERT INTO computer(name,introduced,discontinued,company_id) Values(?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE computer SET name =?, introduced=?, discontinued= ? ,company_id= ? WHERE id= ?";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM computer";

	/**
	 * supression d'un ordinateur par ID (clé)
	 * 
	 * @param id
	 */
	public void deleteComputer(int id) {
		try {
			Connection connect = ConnectionDatabase.getInstance();
			Statement state = connect.createStatement();

			state.executeUpdate(DELETE_QUERY);
			state.close();
			ConnectionDatabase.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * crée la liste des ordinateurs
	 */
	//public <Computer> listComputer() {

//	}

	/**
	 * ajout d'un ordinateur
	 * 
	 * @param name
	 * @param dateIntroduced
	 * @param dateDiscontinued
	 * @param iDCompany
	 */
	/*public void addComputer(String name, String dateIntroduced, String dateDiscontinued, int iDCompany) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = new Date(df.parse(dateIntroduced).getTime());
			d2 = new Date(df.parse(dateDiscontinued).getTime());
			if (d2.after(d1)) {

				Statement state = connect.createStatement();

				state.executeUpdate(ADD_QUERY);
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

	/**
	 * mise à jour d'un ordinateur à partir de son id (clé)
	 * 
	 * @param id
	 * @param name
	 * @param dateIntroduced
	 * @param dateDiscontinued
	 * @param iDCompany
	 */
	/*public void updateComputer(int id, String name, String dateIntroduced, String dateDiscontinued, int iDCompany) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = new Date(df.parse(dateIntroduced).getTime());
			d2 = new Date(df.parse(dateDiscontinued).getTime());
			if (d2.after(d1)) {

				Statement state = connect.createStatement();

				state.executeUpdate(UPDATE_QUERY);
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

	}*/

}