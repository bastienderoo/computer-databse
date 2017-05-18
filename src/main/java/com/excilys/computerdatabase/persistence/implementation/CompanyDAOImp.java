package com.excilys.computerdatabase.persistence.implementation;


import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyDAOImp implements CompanyDAO {
    private static final String SELECT_COMPANY_BY_ID = "SELECT * FROM company WHERE id=? ";
    private static final String SELECT_COMPANY_BY_NAME = "SELECT * FROM company WHERE name=? ";
    private static final String SELECT_ALL_QUERY_PAGE10 = "SELECT * FROM company";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImp.class.getName());


    private JdbcTemplate jdbcTemplate;

    public CompanyDAOImp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Company> getList() {

        List<Company> listCompany = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_QUERY_PAGE10);
        for (Map row : rows) {
            Company company = new Company.Builder((String) row.get("name"))
                    .id((Long) row.get("id"))
                    .build();
            listCompany.add(company);
        }
        return listCompany;
    }

    @Override
    public Company getCompanyById(long id) {
        ;
        return jdbcTemplate.queryForObject(SELECT_COMPANY_BY_ID, new Object[]{id}, new CompanyMapper());

    }

    @Override
    public Company getCompanyByName(String name) {
        return jdbcTemplate.queryForObject(SELECT_COMPANY_BY_NAME, new Object[]{name}, new CompanyMapper());
    }


    private static final class CompanyMapper implements RowMapper<Company> {
        public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Company.Builder(rs.getString("name")).id(rs.getLong("id")).build();
        }
    }
}


