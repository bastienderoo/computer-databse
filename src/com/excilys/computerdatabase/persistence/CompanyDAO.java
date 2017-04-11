package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDAO {
	private Connection connect = null;

	public CompanyDAO(Connection db) {
		this.connect = db;
	}

	public void listCompany() {
		try {
			Statement state = connect.createStatement();

			ResultSet result = state.executeQuery("SELECT * FROM company");
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
}
