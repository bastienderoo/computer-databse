package com.excilys.computerdatabase.mappers;

import com.excilys.computerdatabase.model.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import org.apache.commons.lang3.StringUtils;


public class MapperComputer {
    public static ComputerDTO mapperComputerIntoDTO(Computer computer) {

        String dateDiscontinued = computer.getDateDiscontinued().toString();
        String dateIntroduced = computer.getDateIntroduced().toString();
        String company = computer.getcompany().getName();
        Long idCompany = computer.getId();

        ComputerDTO computerDTO = new ComputerDTO.Builder(computer.getName()).id(computer.getId())
                .dateIntroduced(dateIntroduced).dateDiscontinued(dateDiscontinued).company(company).idCompany(idCompany)
                .build();
        return computerDTO;
    }

    public static Computer mapperDTOIntoComputer(ComputerDTO computerDTO) {
        CompanyServiceImp companyService = new CompanyServiceImp();
        LocalDate dateDiscontinued;
        LocalDate dateIntroduced;
        Company company;
        if (StringUtils.isBlank(computerDTO.getDateDiscontinued())) {
            dateDiscontinued = LocalDate.parse(computerDTO.getDateDiscontinued());
        } else {
            dateDiscontinued = null;
        }
        if (StringUtils.isBlank(computerDTO.getDateIntroduced())) {
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

    public static List<ComputerDTO> mapperComputerIntoDTO(List<Computer> computer) {
        List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO>();

        for (Computer c : computer) {
            ComputerDTO computerDTO = mapperComputerIntoDTO(c);
            listComputerDTO.add(computerDTO);
        }

        return listComputerDTO;
    }
}
