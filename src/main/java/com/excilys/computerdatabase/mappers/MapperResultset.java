package com.excilys.computerdatabase.mappers;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.util.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by excilys on 10/05/17.
 */
public class MapperResultset {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

    public static Company mapperCompany(ResultSet rs) {
        try {
            return new Company.Builder(rs.getString(2)).id(rs.getLong(1)).build();

        } catch (SQLException e) {
            throw new MapperException();
        }
    }

    public static List<Company> mapperCompanyList(ResultSet rs) {
        List<Company> listCompany = new ArrayList<>();
        try {
            while (rs.next()) {
                listCompany.add(new Company.Builder(rs.getString(2)).id(rs.getLong(1)).build());

            }
            return listCompany;
        } catch (SQLException e) {
            throw new MapperException();
        }
    }

    public static Computer mapperComputer(ResultSet rs) {


        try

        {
            Company company = new Company.Builder(rs.getString(7)).id(rs.getLong(5)).build();
            return new Computer.Builder(rs.getString(2))
                    .id(rs.getLong(1))
                    .dateIntroduced(LocalDate.parse(rs.getString(3), formatter))
                    .dateDiscontinued(LocalDate.parse(rs.getString(4), formatter))
                    .company(company)
                    .build();

        } catch (SQLException e)

        {
            throw new MapperException();
        }
    }

    public static List<Computer> mapperComputerList(ResultSet rs) {
        List<Computer> listComputer = new ArrayList<>();
        try
        {
            while (rs.next()) {
                Company company = new Company.Builder(rs.getString(7)).id(rs.getLong(5)).build();
                listComputer.add(new Computer.Builder(rs.getString(2))
                        .id(rs.getLong(1))
                        .dateIntroduced(LocalDate.parse(rs.getString(3), formatter))
                        .dateDiscontinued(LocalDate.parse(rs.getString(4), formatter))
                        .company(company)
                        .build());

            }
            return listComputer;
        } catch (SQLException e)

        {
            throw new MapperException();
        }
    }

}
