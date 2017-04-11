package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe contenant la méthode permettant d'afficher la liste des entreprises
 * @author excilys
 *
 */
public class CompanyDAO {
	private Connection connect = null;
	private String[][] listcomp = new String[0][0];

	/**
	 *  Utilisation de la connection établie au préalable
	 * @param db
	 */
	public CompanyDAO(Connection db) {
		this.connect = db;
	}

	/**
	 * affichage de la liste des entreprises
	 */
	public String[][] listCompany() {
		try {
			Statement state = connect.createStatement();

			ResultSet nbresult = state.executeQuery("SELECT COUNT(*) FROM company");
			// On récupère les MetaData
			int raw = 0;

			while (nbresult.next()) {
				raw = nbresult.getInt(1);
			}
			int j = 0;

			ResultSet result = state.executeQuery("SELECT * FROM company");
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
}
