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

/**
 * Classe contenant les méthodes permettant d'effectuer les différentes actions
 * sur la list d'ordinateurs
 * 
 * @author excilys
 *
 */
public class ComputerDAO {
	/**
	 * init connection
	 */
	private Connection connect = null;
	/**
	 * init list computer
	 */
	private String[][] listcomp = new String[0][0];

	/**
	 * Utilisation de la connection établie au préalable
	 * 
	 * @param db
	 */
	public ComputerDAO(Connection db) {
		this.connect = db;
	}

	/**
	 * supression d'un ordinateur par ID (clé)
	 * 
	 * @param id
	 */
	public void deleteComputer(int id) {
		try {
			Statement state = connect.createStatement();

			state.executeUpdate("DELETE FROM computer WHERE id=" + id);
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * crée la liste des ordinateurs
	 */
	public String[][] listComputer() {
		try {
			Statement state = connect.createStatement();

			ResultSet nbresult = state.executeQuery("SELECT COUNT(*) FROM computer");
			// On récupère les MetaData
			int raw = 0;

			while (nbresult.next()) {
				raw = nbresult.getInt(1);
			}
			int j = 0;

			ResultSet result = state.executeQuery("SELECT * FROM computer");
			ResultSetMetaData resultMeta = result.getMetaData();
			listcomp = new String[resultMeta.getColumnCount()][raw];
			while (result.next()) {
				for (int i = 1; i < resultMeta.getColumnCount()+1; i++) {
					listcomp[i - 1][j] = result.getString(i);
				}
				j++;
			}

			result.close();
			return listcomp;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return listcomp;
		}
	}

	/**
	 * ajout d'un ordinateur
	 * 
	 * @param name
	 * @param dateIntroduced
	 * @param dateDiscontinued
	 * @param iDCompany
	 */
	public void addComputer(String name, String dateIntroduced, String dateDiscontinued, int iDCompany) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = new Date(df.parse(dateIntroduced).getTime());
			d2 = new Date(df.parse(dateDiscontinued).getTime());
			if (d2.after(d1)) {

				Statement state = connect.createStatement();

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

	/**
	 * mise à jour d'un ordinateur à partir de son id (clé)
	 * 
	 * @param id
	 * @param name
	 * @param dateIntroduced
	 * @param dateDiscontinued
	 * @param iDCompany
	 */
	public void updateComputer(int id, String name, String dateIntroduced, String dateDiscontinued, int iDCompany) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = new Date(df.parse(dateIntroduced).getTime());
			d2 = new Date(df.parse(dateDiscontinued).getTime());
			if (d2.after(d1)) {

				Statement state = connect.createStatement();

				state.executeUpdate("UPDATE computer SET name ='" + name + "',introduced='" + dateIntroduced
						+ "',discontinued='" + dateDiscontinued + "',company_id='" + iDCompany + "'WHERE id=" + id);
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