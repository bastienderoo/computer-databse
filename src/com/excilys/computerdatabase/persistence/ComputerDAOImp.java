package com.excilys.computerdatabase.persistence;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe contenant les méthodes permettant d'effectuer les différentes actions
 * sur la list d'ordinateurs
 * 
 * @author excilys
 *
 */
public class ComputerDAOImp implements ComputerDAO {

	private static final String DELETE_QUERY = "DELETE FROM computer WHERE id=?";
	private static final String ADD_QUERY = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE computer SET name =?, introduced=?, discontinued= ? ,company_id= ? WHERE id= ?";
	private static final String SELECT_ALL_QUERY_PAGE10 = "SELECT * FROM computer LIMIT 10 OFFSET ?";
	private static final String GET_COMPUTER_BY_ID = "SELECT * From computer WHERE id=?";
	private static final String GET_COMPUTER_BY_NAME = "SELECT * From computer WHERE name=?";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	/**
	 * supression d'un ordinateur par ID (clé)
	 * 
	 * @param id
	 */
	public void delete(long id) {
		try (Connection connect = ConnectionDatabase.getInstance();
				PreparedStatement pstmt = connect.prepareStatement(DELETE_QUERY);) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * crée la liste des ordinateurs
	 */
	public List<Computer> getList(int page10) {
		List<Computer> listComputer = new ArrayList<Computer>();
		try (Connection connect = ConnectionDatabase.getInstance();
				PreparedStatement pstmt = connect.prepareStatement(SELECT_ALL_QUERY_PAGE10);) {
			pstmt.setInt(1, page10*10);
			try (ResultSet rs = pstmt.executeQuery();) {
				Computer computer;
				Company company=null;
				long id;
				String name;
				LocalDate dateIntroduced = null;
				LocalDate dateDiscontinued = null;
				long idCompany = 0l;

				while (rs.next()) {
					id = rs.getLong(1);
					name = rs.getString(2);
					if (rs.getString(3) != null) {
						dateIntroduced = LocalDate.parse(rs.getString(3),formatter);
					}
					if (rs.getString(4) != null) {
						dateDiscontinued = LocalDate.parse(rs.getString(4),formatter);
					}
					if (rs.getLong(5) != 0l) {
						idCompany = rs.getLong(5);
						company = (new CompanyDAOImp()).getCompanyById(idCompany);
					} 
					computer = new Computer.Builder(name).id(id).dateIntroduced(dateIntroduced)
							.dateDiscontinued(dateDiscontinued).company(company).build();
					listComputer.add(computer);
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listComputer;
	}

	/**
	 * ajout d'un ordinateur
	 * 
	 * @param name
	 * @param dateIntroduced
	 * @param dateDiscontinued
	 * @param iDCompany
	 */
	public void add(Computer computer) {
		try {
			Connection connect = ConnectionDatabase.getInstance();
			PreparedStatement pstmt = connect.prepareStatement(ADD_QUERY);
			pstmt.setString(1, computer.getName());
			pstmt.setObject(2, computer.getDateIntroduced());
			pstmt.setObject(3, computer.getDateDiscontinued());
			pstmt.setLong(4, computer.getcompany().getId());
			pstmt.executeUpdate();
			ConnectionDatabase.closeConnection();

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

	public void update(Computer computer) {
		try {
			Connection connect = ConnectionDatabase.getInstance();
			PreparedStatement pstmt = connect.prepareStatement(UPDATE_QUERY);
			pstmt.setString(1, computer.getName());
			pstmt.setObject(2, computer.getDateIntroduced());
			pstmt.setObject(3, computer.getDateDiscontinued());
			pstmt.setObject(4, computer.getcompany());
			pstmt.setLong(5, computer.getId());
			pstmt.executeUpdate(UPDATE_QUERY);
			ConnectionDatabase.closeConnection();

		} catch (SQLException e) { // TODO Auto-generated
			e.printStackTrace();
		}
		
	}

	public Computer getComputerById(long id) {
		try (Connection connect = ConnectionDatabase.getInstance();
				PreparedStatement pstmt = connect.prepareStatement(GET_COMPUTER_BY_ID);) {
			pstmt.setLong(1, id);
			try (ResultSet rs = pstmt.executeQuery();) {
				Computer computer;
				Company company=null;
				String name;
				LocalDate dateIntroduced = null;
				LocalDate dateDiscontinued = null;
				long idCompany = 0l;
				
				
				rs.next();
				name = rs.getString(2);
				
				if (rs.getString(3) != null) {
					dateIntroduced = LocalDate.parse(rs.getString(3),formatter);
				}
				if (rs.getString(4) != null) {
					dateDiscontinued = LocalDate.parse(rs.getString(4),formatter);
				}
				if (rs.getLong(5) != 0l) {
					idCompany = rs.getLong(5);
					company = (new CompanyDAOImp()).getCompanyById(idCompany);
				} 
				computer = new Computer.Builder(name).id(id).dateIntroduced(dateIntroduced)
						.dateDiscontinued(dateDiscontinued).company(company).build();
				return computer;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ComputerDatabaseDAOException("pas de company pour cet ID",e);
		}

	}
	
	public Computer getComputerByName(String name) {
		try (Connection connect = ConnectionDatabase.getInstance();
				PreparedStatement pstmt = connect.prepareStatement(GET_COMPUTER_BY_NAME);) {
			pstmt.setString(1,name);
			try (ResultSet rs = pstmt.executeQuery();) {
				Computer computer;
				Company company=null;
				long id;
				LocalDate dateIntroduced = null;
				LocalDate dateDiscontinued = null;
				long idCompany = 0l;
				
				
				rs.next();
				id = rs.getLong(1);
				
				if (rs.getString(3) != null) {
					dateIntroduced = LocalDate.parse(rs.getString(3),formatter);
				}
				if (rs.getString(4) != null) {
					dateDiscontinued = LocalDate.parse(rs.getString(4),formatter);
				}
				if (rs.getLong(5) != 0l) {
					idCompany = rs.getLong(5);
					company = (new CompanyDAOImp()).getCompanyById(idCompany);
				} 
				computer = new Computer.Builder(name).id(id).dateIntroduced(dateIntroduced)
						.dateDiscontinued(dateDiscontinued).company(company).build();
				return computer;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ComputerDatabaseDAOException("pas de company pour cet ID",e);
		}

	}
}