package com.excilys.computerdatabase.persistence;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe contenant les méthodes permettant d'effectuer les différentes actions
 * sur la list d'ordinateurs.
 * 
 * @author excilys
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
     * supression d'un ordinateur par ID (clé).
     * 
     * @param id
     *            id
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
     * crée la liste des ordinateurs.
     * 
     * @param page10
     *            page10
     * @return listcomputer
     */
    public List<Computer> getList(int page10) {
        List<Computer> listComputer = new ArrayList<Computer>();
        try (Connection connect = ConnectionDatabase.getInstance();
                PreparedStatement pstmt = connect.prepareStatement(SELECT_ALL_QUERY_PAGE10);) {
            pstmt.setInt(1, page10 * 10);
            try (ResultSet rs = pstmt.executeQuery();) {
                Computer computer;
                Company company = null;
                long id;
                String name;
                LocalDate dateIntroduced = null;
                LocalDate dateDiscontinued = null;
                long idCompany = 0L;

                while (rs.next()) {
                    id = rs.getLong(1);
                    name = rs.getString(2);
                    if (rs.getString(3) != null) {
                        dateIntroduced = LocalDate.parse(rs.getString(3), formatter);
                    } else {
                        dateIntroduced = null;
                    }
                    if (rs.getString(4) != null) {
                        dateDiscontinued = LocalDate.parse(rs.getString(4), formatter);
                    } else {
                        dateDiscontinued = null;
                    }
                    if (rs.getLong(5) != 0L) {
                        idCompany = rs.getLong(5);
                        company = (new CompanyDAOImp()).getCompanyById(idCompany);
                    } else {
                        company = null;
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
     * ajout d'un ordinateur.
     * 
     * @param computer
     *            computer
     * @return id
     */
    public long add(Computer computer) {
        try (Connection connect = ConnectionDatabase.getInstance();
                PreparedStatement pstmt = connect.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, computer.getName());
            pstmt.setObject(2, computer.getDateIntroduced());
            pstmt.setObject(3, computer.getDateDiscontinued());
            if (computer.getcompany() != null) {
                pstmt.setLong(4, computer.getcompany().getId());
            } else {
                pstmt.setNull(4, java.sql.Types.BIGINT);
            }
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ComputerDatabaseDAOException("pas de company pour cet ID", e);

        }

    }

    /**
     * mise à jour d'un ordinateur à partir de son id (clé).
     * 
     * @param computer
     *            computer
     */
    public void update(Computer computer) {
        System.out.println(computer);
        try (Connection connect = ConnectionDatabase.getInstance();
                PreparedStatement pstmt = connect.prepareStatement(UPDATE_QUERY);) {
            pstmt.setString(1, computer.getName());
            pstmt.setObject(2, computer.getDateIntroduced());
            pstmt.setObject(3, computer.getDateDiscontinued());
            if (computer.getcompany() != null) {
                pstmt.setLong(4, computer.getcompany().getId());
            } else {
                pstmt.setNull(4, java.sql.Types.BIGINT);
            }
            pstmt.setLong(5, computer.getId());
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) { // TODO Auto-generated
            e.printStackTrace();
        }

    }

    /**
     * get computer by id.
     * 
     * @param id
     *            id
     * @return computer
     */
    public Computer getComputerById(long id) {
        try (Connection connect = ConnectionDatabase.getInstance();
                PreparedStatement pstmt = connect.prepareStatement(GET_COMPUTER_BY_ID);) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                Computer computer;
                Company company = null;
                String name;
                LocalDate dateIntroduced = null;
                LocalDate dateDiscontinued = null;
                long idCompany = 0L;

                rs.next();
                name = rs.getString(2);

                if (rs.getString(3) != null) {
                    dateIntroduced = LocalDate.parse(rs.getString(3), formatter);
                }
                if (rs.getString(4) != null) {
                    dateDiscontinued = LocalDate.parse(rs.getString(4), formatter);
                }
                if (rs.getLong(5) != 0L) {
                    idCompany = rs.getLong(5);
                    company = (new CompanyDAOImp()).getCompanyById(idCompany);
                }
                computer = new Computer.Builder(name).id(id).dateIntroduced(dateIntroduced)
                        .dateDiscontinued(dateDiscontinued).company(company).build();
                return computer;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new ComputerDatabaseDAOException("pas de company pour cet ID");
        }

    }

    /**
     * get computer by name.
     * 
     * @param name
     *            name
     * @return computer
     */
    public Computer getComputerByName(String name) {
        try (Connection connect = ConnectionDatabase.getInstance();
                PreparedStatement pstmt = connect.prepareStatement(GET_COMPUTER_BY_NAME);) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery();) {
                Computer computer;
                Company company = null;
                long id;
                LocalDate dateIntroduced = null;
                LocalDate dateDiscontinued = null;
                long idCompany = 0L;

                rs.next();
                id = rs.getLong(1);

                if (rs.getString(3) != null) {
                    dateIntroduced = LocalDate.parse(rs.getString(3), formatter);
                }
                if (rs.getString(4) != null) {
                    dateDiscontinued = LocalDate.parse(rs.getString(4), formatter);
                }
                if (rs.getLong(5) != 0L) {
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
            throw new ComputerDatabaseDAOException("pas de company pour cet ID", e);
        }

    }

}