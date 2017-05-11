package com.excilys.computerdatabase.mappers;

import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.implementation.CompanyServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import org.apache.commons.lang3.StringUtils;


public class MapperComputer {
    /**
     * Map a computer into a dto computer.
     * @param computer computer
     * @return computer dto
     */

    public static ComputerDTO mapperComputerIntoDTO(Computer computer) {
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

        ComputerDTO computerDTO = new ComputerDTO.Builder(computer.getName()).id(computer.getId())
                .dateIntroduced(dateIntroduced).dateDiscontinued(dateDiscontinued).company(company).idCompany(idCompany)
                .build();
        return computerDTO;
    }

    /**
     * map a dto computer into a computer.
     * @param computerDTO computer dto
     * @return computer
     */
    public static Computer mapperDTOIntoComputer(ComputerDTO computerDTO) {
        CompanyServiceImp companyService = new CompanyServiceImp();
        LocalDate dateDiscontinued;
        LocalDate dateIntroduced;
        Company company;
        if (!StringUtils.isBlank(computerDTO.getDateDiscontinued())) {
            dateDiscontinued = LocalDate.parse(computerDTO.getDateDiscontinued());
        } else {
            dateDiscontinued = null;
        }
        if (!StringUtils.isBlank(computerDTO.getDateIntroduced())) {
            dateIntroduced = LocalDate.parse(computerDTO.getDateIntroduced());
        } else {
            dateIntroduced = null;
        }
        if (computerDTO.getIdCompany() != 0) {
            company = companyService.getCompanyById(computerDTO.getIdCompany());
        } else {
            company = null;
        }

        Computer computer = new Computer.Builder(computerDTO.getName()).id(computerDTO.getId())
                .dateIntroduced(dateIntroduced).dateDiscontinued(dateDiscontinued).company(company).build();
        return computer;
    }

    /**
     * map a list of computers int o a list of dto computers.
     * @param computer list of computers
     * @return list of dto computers
     */
    public static List<ComputerDTO> mapperComputerIntoDTO(List<Computer> computer) {
        List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO>();

        for (Computer c : computer) {
            ComputerDTO computerDTO = mapperComputerIntoDTO(c);
            listComputerDTO.add(computerDTO);
        }

        return listComputerDTO;
    }
}
