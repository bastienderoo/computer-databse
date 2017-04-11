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


public class ComputerDAO {
	private Connection connect = null;
	public ComputerDAO(Connection db)
	{
	this.connect=db;	
	}


	public void deleteComputer( String name) {
		try {
			Statement state = connect.createStatement();

			state.executeUpdate("DELETE FROM computer WHERE name='" + name + "'");
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listComputer() {
		try {
			Statement state = connect.createStatement();

			ResultSet result = state.executeQuery("SELECT * FROM computer");
			// On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();

			System.out.println("\n**********************************");
			// On affiche le nom des colonnes
			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

			System.out.println("\n**********************************");

			while (result.next()) {
				for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
					if (result.getObject(i) != null) {
						System.out.print("\t" + result.getString(i) + "\t |");
					} else {
						System.out.print("\t" + "******" + "\t |");
					}
				}
				System.out.println("\n---------------------------------");

			}

			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addComputer( String name, String dateIntroduced, String dateDiscontinued, int iDCompany) {

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

	public void updateComputer( String name1, String dateIntroduced1, String dateDiscontinued1, int iDCompany1,
			String name, String dateIntroduced, String dateDiscontinued, int iDCompany) {

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



}