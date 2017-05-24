package com.excilys.computerdatabase.mappers;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.implementation.CompanyDAOImp;
import com.excilys.computerdatabase.util.MapperException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by excilys on 10/05/17.
 */
@Component
public class MapperResultset {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImp.class.getName());
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

    private static CompanyDAO companyDAOImp;

    @Autowired
    public void setCompanyDAOImp(CompanyDAO companyDAOImp) {
        MapperResultset.companyDAOImp = companyDAOImp;
    }

    /**
     * .
     */
    private MapperResultset() {

    }

    /**
     * mapper of resultset company.
     *
     * @param rs reultset
     * @return company
     */
    public static Company mapperCompany(ResultSet rs) {
        try {
            return new Company.Builder(rs.getString(2)).id(rs.getLong(1)).build();
        } catch (SQLException e) {
            LOGGER.error("Can not map company resultset");
            throw new MapperException();
        }
    }

    /**
     * mapper of result set list companies.
     *
     * @param rs result set
     * @return list of companies
     */
    public static List<Company> mapperCompanyList(ResultSet rs) {
        List<Company> listCompany = new ArrayList<>();
        try {
            while (rs.next()) {
                listCompany.add(new Company.Builder(rs.getString(2)).id(rs.getLong(1)).build());
            }
            return listCompany;
        } catch (SQLException e) {
            LOGGER.error("Can not map list of companies resultset");
            throw new MapperException();
        }
    }

    /**
     * mapper of resultset computer.
     *
     * @param rs reultset
     * @return computer
     */
    public static Computer mapperComputer(ResultSet rs) {
        LocalDate dateIntroduced = null;
        LocalDate dateDiscontinued = null;
        Company company;
        try {
            if (!StringUtils.isBlank(rs.getString(3))) {
                dateIntroduced = LocalDate.parse(rs.getString(3), formatter);
            } else {
                dateIntroduced = null;
            }
            if (!StringUtils.isBlank(rs.getString(4))) {
                dateDiscontinued = LocalDate.parse(rs.getString(4), formatter);
            } else {
                dateDiscontinued = null;
            }
            if (rs.getLong(5) != 0L) {
                company = companyDAOImp.getCompanyById(rs.getLong(5));
            } else {
                company = null;
            }
            return new Computer.Builder(rs.getString(2))
                    .id(rs.getLong(1))
                    .dateIntroduced(dateIntroduced)
                    .dateDiscontinued(dateDiscontinued)
                    .company(company)
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Can not map computer resultset");
            e.printStackTrace();
            throw new MapperException();

        }
    }

    /**
     * mapper of result set list computers.
     *
     * @param rs result set
     * @return list of computers
     */
    public static List<Computer> mapperComputerList(ResultSet rs) {
        List<Computer> listComputer = new ArrayList<>();
        LocalDate dateIntroduced = null;
        LocalDate dateDiscontinued = null;
        Company company = null;
        try {
            while (rs.next()) {
                if (!StringUtils.isBlank(rs.getString(3))) {
                    dateIntroduced = LocalDate.parse(rs.getString(3), formatter);
                } else {
                    dateIntroduced = null;
                }

                if (!StringUtils.isBlank(rs.getString(4))) {
                    dateDiscontinued = LocalDate.parse(rs.getString(4), formatter);
                } else {
                    dateDiscontinued = null;
                }
                if (rs.getLong(5) != 0L) {
                    company = companyDAOImp.getCompanyById(rs.getLong(5));
                } else {
                    company = null;
                }
                listComputer.add(new Computer.Builder(rs.getString(2))
                        .id(rs.getLong(1))
                        .dateIntroduced(dateIntroduced)
                        .dateDiscontinued(dateDiscontinued)
                        .company(company)
                        .build());
            }
            return listComputer;
        } catch (SQLException e) {
            LOGGER.error("Can not map list of computers resultset");
            throw new MapperException();
        }
    }

}