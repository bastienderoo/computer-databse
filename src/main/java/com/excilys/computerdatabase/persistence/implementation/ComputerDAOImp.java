package com.excilys.computerdatabase.persistence.implementation;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.mappers.MapperResultset;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.util.ComputerDatabaseDAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe contenant les méthodes permettant d'effectuer les différentes actions
 * sur la list d'ordinateurs.
 *
 * @author excilys
 */
public class ComputerDAOImp extends JdbcDaoSupport implements ComputerDAO {

    private static final String DELETE_QUERY = "DELETE FROM computer WHERE id=?";
    private static final String ADD_QUERY = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE computer SET name =?, introduced=?, discontinued= ? ,company_id= ? WHERE id= ?";
    private static final String SELECT_ALL_QUERY_PAGE = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?";
    private static final String GET_COMPUTER_BY_ID = "SELECT * From computer WHERE id=?";
    private static final String GET_COMPUTER_BY_NAME = "SELECT * From computer WHERE upper(name) like upper(?)";
    private static final String COUNT_COMPUTER = "SELECT COUNT(*) FROM computer";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImp.class.getName());


    public Computer delete(long id) {

        getJdbcTemplate().update(DELETE_QUERY, new Object[]{id}, new BeanPropertyRowMapper(Computer.class));
        return getComputerById(id);

    }


    public List<Computer> getList(int page, int nbrElements) {

        List<Computer> listComputer = new ArrayList<>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(SELECT_ALL_QUERY_PAGE);
        for (Map row : rows) {
            ComputerDTO computerDTO = new ComputerDTO.Builder((String) row.get("name"))
                    .id((Long) row.get("id"))
                    .dateIntroduced((String) row.get("introduced"))
                    .dateDiscontinued((String) row.get("discontinued"))
                    .idCompany((Long) row.get("company_id"))
                    .build();
            Computer computer = MapperComputer.mapperDTOIntoComputer(computerDTO);
            listComputer.add(computer);
        }
        return listComputer;
    }


    public long add(Computer computer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(ADD_QUERY, new Object[]{computer.getName(), computer.getDateIntroduced(),
                computer.getDateDiscontinued(), computer.getcompany()}, keyHolder, new BeanPropertyRowMapper(Computer.class));


        return keyHolder.getKey().longValue();


    }


    public Computer update(Computer computer) {

        getJdbcTemplate().queryForObject(UPDATE_QUERY, new Object[]{computer.getName(), computer.getDateIntroduced(),
                computer.getDateDiscontinued(), computer.getcompany(), computer.getId()}, new BeanPropertyRowMapper(Computer.class));
        return computer;
    }


    public Computer getComputerById(long id) {
        return (Computer) getJdbcTemplate().queryForObject(GET_COMPUTER_BY_ID, new Object[]{id}, new BeanPropertyRowMapper(Computer.class));

    }


    public List<Computer> getComputerByName(String name) {
        List<Computer> listComputer = new ArrayList<>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(GET_COMPUTER_BY_NAME);
        for (Map row : rows) {
            ComputerDTO computerDTO = new ComputerDTO.Builder((String) row.get("name"))
                    .id((Long) row.get("id"))
                    .dateIntroduced((String) row.get("introduced"))
                    .dateDiscontinued((String) row.get("discontinued"))
                    .idCompany((Long) row.get("company_id"))
                    .build();
            Computer computer = MapperComputer.mapperDTOIntoComputer(computerDTO);
            listComputer.add(computer);
        }
        return listComputer;
    }

    public int numberComputer() {
        return getJdbcTemplate().queryForObject(COUNT_COMPUTER,Integer.class);
    }
}