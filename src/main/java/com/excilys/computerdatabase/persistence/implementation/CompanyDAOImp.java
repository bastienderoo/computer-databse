package com.excilys.computerdatabase.persistence.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.mappers.MapperResultset;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ConnectionDatabase;
import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CompanyDAOImp implements CompanyDAO {
    private static final String SELECT_COMPANY_BY_ID = "SELECT * FROM company WHERE id=? ";
    private static final String SELECT_COMPANY_BY_NAME = "SELECT * FROM company WHERE nameCompany=? ";
    private static final String SELECT_ALL_QUERY_PAGE10 = "SELECT * FROM company";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImp.class.getName());

    public List<Company> getList() {

        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(SELECT_ALL_QUERY_PAGE10)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {

                return MapperResultset.mapperCompanyList(rs);

            }
        } catch (SQLException e) {
            LOGGER.info("Impossible to create the list of companies");
            e.printStackTrace();
            throw new ComputerDatabaseDAOException();
        }

    }


    public Company getCompanyById(long id) {
        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(SELECT_COMPANY_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();

                return MapperResultset.mapperCompany(rs);
            }

        } catch (SQLException e) {
            LOGGER.info("Impossible to get the company with this ID");
            e.printStackTrace();
            throw new ComputerDatabaseDAOException();
        }

    }

    public Company getCompanyByName(String name) {
        try (Connection connect = ConnectionDatabase.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connect.prepareStatement(SELECT_COMPANY_BY_NAME)) {
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();
                return MapperResultset.mapperCompany(rs);
            }

        } catch (SQLException e) {
            LOGGER.info("Impossible to get the company with this name");
            e.printStackTrace();
            throw new ComputerDatabaseDAOException();
        }

    }


}
