package com.excilys.computerdatabase.mappers;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperComputer {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

    private final Logger LOGGER = LoggerFactory.getLogger(MapperComputer.class.getName());

    @Autowired
    private CompanyService companyServiceImp;



    /**
     * Map a computer into a dto computer.
     *
     * @param computer computer
     * @return computer dto
     */

    public ComputerDTO mapperComputerIntoDTO(Computer computer) {
        String dateDiscontinued;
        String dateIntroduced;
        String company;
        if (computer.getDateDiscontinued() != null) {
            dateDiscontinued = computer.getDateDiscontinued().toString();
        } else {
            dateDiscontinued = "";
        }
        if (computer.getDateIntroduced() != null) {
            dateIntroduced = computer.getDateIntroduced().toString();
        } else {
            dateIntroduced = "";
        }
        if (computer.getcompany() != null) {
            company = computer.getcompany().getName();
        } else {
            company = "";
        }

        Long idCompany = computer.getId();

        ComputerDTO computerDTO = new ComputerDTO.Builder().id(computer.getId()).name(computer.getName())
                .dateIntroduced(dateIntroduced).dateDiscontinued(dateDiscontinued).company(company).idCompany(idCompany)
                .build();
        return computerDTO;
    }

    /**
     * map a dto computer into a computer.
     *
     * @param computerDTO computer dto
     * @return computer
     */
    public Computer mapperDTOIntoComputer(ComputerDTO computerDTO) {
        LocalDate dateDiscontinued;
        LocalDate dateIntroduced;
        Company company;
        if (!StringUtils.isBlank(computerDTO.getDateDiscontinued())) {
            dateDiscontinued = LocalDate.parse(computerDTO.getDateDiscontinued(),formatter);
        } else {
            dateDiscontinued = null;
        }
        if (!StringUtils.isBlank(computerDTO.getDateIntroduced())) {
            dateIntroduced = LocalDate.parse(computerDTO.getDateIntroduced(),formatter);
        } else {
            dateIntroduced = null;
        }
        if (computerDTO.getIdCompany() != 0) {

            company = companyServiceImp.getCompanyById(computerDTO.getIdCompany());
        } else {
            company = null;
        }

        Computer computer = new Computer.Builder(computerDTO.getName()).id(computerDTO.getId())
                .dateIntroduced(dateIntroduced).dateDiscontinued(dateDiscontinued).company(company).build();
        return computer;
    }

    /**
     * map a list of computers into a list of dto computers.
     *
     * @param computer list of computers
     * @return list of dto computers
     */
    public List<ComputerDTO> mapperComputerIntoDTO(List<Computer> computer) {
        List<ComputerDTO> list = new ArrayList<>();
        for (Computer computer1 : computer) {
            ComputerDTO computerDTO = mapperComputerIntoDTO(computer1);
            list.add(computerDTO);
        }
        return list;
    }
}
