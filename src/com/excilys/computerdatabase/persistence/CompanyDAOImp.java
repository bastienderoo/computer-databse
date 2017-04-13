package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;

/**
 * Classe contenant la méthode permettant d'afficher la liste des entreprises
 * 
 * @author excilys
 *
 */
public class CompanyDAOImp implements CompanyDAO {
	private static final String SELECT_COMPANY_BY_ID = "SELECT * FROM company WHERE id=? ";
	private static final String SELECT_ALL_QUERY_PAGE10 = "SELECT * FROM company LIMIT 10 OFFSET ?";

	/**
	 * affichage de la liste des entreprises
	 */

	public List<Company> getList(int page10) {
		List<Company> listCompany = new ArrayList<Company>();
		try (Connection connect = ConnectionDatabase.getInstance();
				PreparedStatement pstmt = connect.prepareStatement(SELECT_ALL_QUERY_PAGE10);) {
			pstmt.setInt(1, page10*10);
			try (ResultSet rs = pstmt.executeQuery();) {
				Company company;
				long id;
				String name;


				while (rs.next()) {
					id = rs.getLong(1);
					name = rs.getString(2);

					company = new Company.Builder(name).id(id).build();
					listCompany.add(company);
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCompany;
	}
	
	public Company getCompanyById(long id) {
		try (Connection connect = ConnectionDatabase.getInstance();
				PreparedStatement pstmt = connect.prepareStatement(SELECT_COMPANY_BY_ID);) {
			pstmt.setLong(1, id);
			try (ResultSet rs = pstmt.executeQuery();) {
				rs.next();
				Company company;
				String name = rs.getString(2);
				company = new Company.Builder(name).id(id).build();
				return company;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ComputerDatabaseDAOException("pas de company pour cet ID",e);
		}

	}
}
