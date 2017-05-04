package com.excilys.computerdatabase.persistence.implementation;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.persistence.ConnectionDatabase;
import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
    private static final String SELECT_ALL_QUERY_PAGE = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?";
    private static final String GET_COMPUTER_BY_ID = "SELECT * From computer WHERE id=?";
    private static final String GET_COMPUTER_BY_NAME = "SELECT * From computer WHERE upper(name) like upper(?)";
    private static final String COUNT_COMPUTER = "SELECT COUNT(*) FROM computer";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    private static final Logger LOGGER = Logger.getLogger(CompanyDAOImp.class.getName());

    public void delete(long id) {
        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Impossible to delete this computer");
            e.printStackTrace();
        }
    }


    public List<Computer> getList(int page, int nbrElements) {
        List<Computer> listComputer = new ArrayList<Computer>();
        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(SELECT_ALL_QUERY_PAGE)) {
            preparedStatement.setInt(1, nbrElements);
            preparedStatement.setInt(2, page * nbrElements);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                Computer computer;
                Company company;
                long id;
                String name;
                LocalDate dateIntroduced;
                LocalDate dateDiscontinued;
                long idCompany;

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
                        String nameCompany = rs.getString(7);
                        company = new Company.Builder(nameCompany).id(idCompany).build();
                    } else {
                        company = null;
                    }
                    computer = new Computer.Builder(name).id(id).dateIntroduced(dateIntroduced)
                            .dateDiscontinued(dateDiscontinued).company(company).build();

                    listComputer.add(computer);
                }
            } catch (SQLException e) {
                LOGGER.info("Can not get the list of computers");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            LOGGER.info("Connection failure");
            e.printStackTrace();

        }
        return listComputer;
    }


    public long add(Computer computer) {
        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement pstmt = connect.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)) {
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
            LOGGER.info("Can not add the computer");
            e.printStackTrace();
            throw new ComputerDatabaseDAOException();

        }

    }


    public void update(Computer computer) {
        System.out.println(computer);
        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, computer.getName());
            if (computer.getDateIntroduced() != null) {
                preparedStatement.setObject(2, computer.getDateIntroduced());
            } else {
                preparedStatement.setString(2, null);
            }

            if (computer.getDateDiscontinued() != null) {
                preparedStatement.setObject(3, computer.getDateDiscontinued());
            } else {
                preparedStatement.setString(3, null);
            }

            if (computer.getcompany() != null) {
                preparedStatement.setLong(4, computer.getcompany().getId());
            } else {
                preparedStatement.setNull(4, java.sql.Types.BIGINT);
            }
            preparedStatement.setLong(5, computer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.info("Can not update the computer");
            e.printStackTrace();
        }

    }


    public Computer getComputerById(long id) {
        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(GET_COMPUTER_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
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
            LOGGER.info("Can not get the computer with this ID");
            e.printStackTrace();
            throw new ComputerDatabaseDAOException();
        }

    }


    public List<Computer> getComputerByName(String name) {
        List<Computer> listComputer = new ArrayList<Computer>();
        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(GET_COMPUTER_BY_NAME)) {
            preparedStatement.setString(1, name + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                Computer computer;
                Company company = null;
                long id;
                LocalDate dateIntroduced = null;
                LocalDate dateDiscontinued = null;
                long idCompany;

                while (rs.next()) {
                    id = rs.getLong(1);

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
                    listComputer.add(computer);
                }
                return listComputer;
            }

        } catch (SQLException e) {
            LOGGER.info("Can not get the computer with this name");
            e.printStackTrace();
            throw new ComputerDatabaseDAOException();
        }

    }

    public int nombreComputer() {
        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(COUNT_COMPUTER);
             ResultSet rs = preparedStatement.executeQuery()) {
            rs.next();
            int nombrecomputer = rs.getInt(1);
            return nombrecomputer;
        } catch (SQLException e) {
            LOGGER.info("Can not get the number of computers");
            e.printStackTrace();
            throw new ComputerDatabaseDAOException();
        }
    }

}