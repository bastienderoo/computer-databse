package com.excilys.computerdatabase.persistence.implementation;

import com.excilys.computerdatabase.mappers.MapperComputer;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.Serializable;
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
@Repository
public class ComputerDAOImp implements ComputerDAO {

    private static final String DELETE_QUERY = "DELETE FROM computer WHERE id=?";
    private static final String ADD_QUERY = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE computer SET name =?, introduced=?, discontinued= ? ,company_id= ? WHERE id= ?";
    private static final String SELECT_ALL_QUERY_PAGE = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.id AS company2_id, company.name AS company_name FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?";
    private static final String GET_COMPUTER_BY_ID = "SELECT * FROM computer WHERE id=?";
    private static final String GET_COMPUTER_BY_NAME = "SELECT * FROM computer WHERE upper(name) LIKE upper(?)";
    private static final String COUNT_COMPUTER = "SELECT COUNT(*) FROM computer";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImp.class.getName());
    private JdbcTemplate jdbcTemplate;

    public ComputerDAOImp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Computer delete(long id) {

        jdbcTemplate.update(DELETE_QUERY, new Object[]{id}, new BeanPropertyRowMapper(Computer.class));
        return getComputerById(id);

    }


    public List<Computer> getList(int page, int nbrElements) {
        List<Computer> listComputer = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_QUERY_PAGE, new Object[]{nbrElements, page * nbrElements});
        for (Map row : rows) {
            ComputerDTO computerDTO = new ComputerDTO.Builder()
                    .name((String) row.get("name"))
                    .id((long) row.get("id"))
                    .dateIntroduced((row.get("introduced") != null) ? row.get("introduced").toString() : null)
                    .dateDiscontinued((row.get("discontinued") != null) ? row.get("discontinued").toString() : null)
                    .idCompany((row.get("company_id") != null) ? (long) row.get("company_id") : 0L)
                    .build();
            Computer computer = MapperComputer.mapperDTOIntoComputer(computerDTO);
            listComputer.add(computer);
        }
        return listComputer;
    }


    public long add(Computer computer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(ADD_QUERY, new Object[]{computer.getName(), computer.getDateIntroduced(),
                computer.getDateDiscontinued(), computer.getcompany().getId()}, keyHolder);


        return keyHolder.getKey().longValue();


    }


    public Computer update(Computer computer) {
        jdbcTemplate.update(UPDATE_QUERY, new Object[]{computer.getName(), computer.getDateIntroduced(),
                computer.getDateDiscontinued(), (computer.getcompany() != null) ? computer.getcompany().getId() : null, computer.getId()}, new BeanPropertyRowMapper(Computer.class));
        return computer;
    }


    public Computer getComputerById(long id) {
        return (Computer) jdbcTemplate.queryForObject(GET_COMPUTER_BY_ID, new Object[]{id}, new BeanPropertyRowMapper(Computer.class));

    }


    public List<Computer> getComputerByName(String name) {
        List<Computer> listComputer = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_COMPUTER_BY_NAME, new Object[]{name+"%"});
        for (Map row : rows) {
            ComputerDTO computerDTO = new ComputerDTO.Builder()
                    .name((String) row.get("name"))
                    .id((Long) row.get("id"))
                    .dateIntroduced((row.get("introduced") != null) ? row.get("introduced").toString() : null)
                    .dateDiscontinued((row.get("discontinued") != null) ? row.get("discontinued").toString() : null)
                    .idCompany((row.get("company_id") != null) ? (long) row.get("company_id") : 0L)
                    .build();
            Computer computer = MapperComputer.mapperDTOIntoComputer(computerDTO);
            listComputer.add(computer);
        }
        return listComputer;
    }

    public int numberComputer() {
        return jdbcTemplate.queryForObject(COUNT_COMPUTER, Integer.class);
    }
}